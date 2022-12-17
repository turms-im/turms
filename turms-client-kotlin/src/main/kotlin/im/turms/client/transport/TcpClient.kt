/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.client.transport

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.CertificatePinner
import okhttp3.internal.tls.OkHostnameVerifier
import java.io.EOFException
import java.io.InputStream
import java.io.OutputStream
import java.lang.reflect.Method
import java.net.InetAddress
import java.net.Socket
import java.nio.ByteBuffer
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SNIHostName
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLParameters
import javax.net.ssl.SSLPeerUnverifiedException
import javax.net.ssl.SSLProtocolException
import javax.net.ssl.SSLSocket
import javax.net.ssl.X509TrustManager
import kotlin.concurrent.thread
import kotlin.coroutines.CoroutineContext

/**
 * @author James Chen
 */
class TcpClient(override val coroutineContext: CoroutineContext) : CoroutineScope {

    var port: Int? = null
    lateinit var host: String

    private lateinit var socket: Socket
    private lateinit var input: InputStream
    private lateinit var output: OutputStream
    private lateinit var readBuffer: ByteBuffer

    var isOpen = false
    var isReading = false
    var metrics = TcpMetrics()

    var onClose: ((Throwable?) -> Unit)? = null

    // Lifecycle

    suspend fun connect(
        host: String,
        port: Int,
        useTls: Boolean,
        trustManager: X509TrustManager? = null,
        serverName: String? = null,
        hostname: String? = null,
        pins: List<Pin>? = null
    ) {
        if (isOpen) {
            throw RuntimeException("The TCP client has connected")
        }
        withContext(coroutineContext) {
            var start = System.currentTimeMillis()
            val address = InetAddress.getByName(host)
            metrics.addressResolverTime = (System.currentTimeMillis() - start).toInt()
            start = System.currentTimeMillis()
            val socket = Socket(address, port)
            metrics.connectTime = (System.currentTimeMillis() - start).toInt()
            socket.tcpNoDelay = true
            socket.reuseAddress = true
            if (useTls) {
                try {
                    start = System.currentTimeMillis()
                    connectTls(socket, host, port, trustManager, serverName, hostname, pins)
                    metrics.tlsHandshakeTime = (System.currentTimeMillis() - start).toInt()
                } catch (e: Exception) {
                    try {
                        socket.close()
                    } catch (e: Exception) {
                        // ignored
                    }
                    throw e
                }
            }
            this@TcpClient.socket = socket
            input = socket.getInputStream()
            output = socket.getOutputStream()
        }
        this.host = host
        this.port = port
        readBuffer = ByteBuffer.allocate(INITIAL_READ_BUFFER_CAPACITY)
        isOpen = true
    }

    private fun connectTls(
        socket: Socket,
        host: String,
        port: Int,
        trustManager: X509TrustManager?,
        serverName: String?,
        hostname: String?,
        pins: List<Pin>?
    ) {
        val sslContext = getSslContext("TLSv1.3")
            ?: getSslContext("TLSv1.2")
            ?: throw SSLProtocolException("TLSv1.2 and TLSv1.3 are not supported")
        sslContext.init(null, trustManager?.let { arrayOf(it) }, SecureRandom())
        val socketFactory = sslContext.socketFactory
        val ssl = socketFactory.createSocket(socket, host, port, true) as SSLSocket
        val sslParameters = sslContext.supportedSSLParameters

        // SNI
        if (setServerNames != null) {
            setServerNames.invoke(sslParameters, listOf(SNIHostName(serverName)))
        } else {
            setHostname?.invoke(socket, hostname)
        }

        ssl.sslParameters = sslParameters

        ssl.startHandshake()

        val session = ssl.session
        val peerCertificates = session.peerCertificates

        // Hostname Verification
        val verified = hostname?.let { OkHostnameVerifier.verify(hostname, session) }
        if (verified == false) {
            if (peerCertificates.isNotEmpty()) {
                val cert = peerCertificates[0] as X509Certificate
                throw SSLPeerUnverifiedException(
                    """
              |Hostname $host not verified:
              |    certificate: ${CertificatePinner.pin(cert)}
              |    DN: ${cert.subjectDN.name}
              |    subjectAltNames: ${OkHostnameVerifier.allSubjectAltNames(cert)}
                    """.trimMargin()
                )
            } else {
                throw SSLPeerUnverifiedException(
                    "Hostname $host not verified (no certificates)"
                )
            }
        }

        // SSL Pinning Verification
        if (pins?.isNotEmpty() == true) {
            val builder = CertificatePinner.Builder()
            pins.forEach {
                val algorithm = when (it.algorithm) {
                    PinHashAlgorithm.SHA1 -> "sha1"
                    PinHashAlgorithm.SHA256 -> "sha256"
                }
                builder.add(it.pattern, "$algorithm/${it.hash}")
            }
            builder.build().check(hostname ?: host, peerCertificates.toList())
        }
    }

    suspend fun close(t: Throwable? = null) {
        try {
            withContext(coroutineContext) {
                socket.close()
            }
        } finally {
            onClose(t)
        }
    }

    private fun onClose(t: Throwable? = null) {
        if (isOpen) {
            try {
                onClose?.invoke(t)
            } catch (e: Exception) {
                // TODO: log
            }
        }
        readBuffer.clear()
        metrics = TcpMetrics()
        isOpen = false
    }

    // Read and Write

    fun startReading(consumer: () -> Unit) {
        if (isReading) {
            throw RuntimeException("The TCP client has been reading")
        }
        isReading = true
        try {
            thread(name = "turms-tcp-read") {
                consumer()
            }
        } finally {
            isReading = false
        }
    }

    fun read(exact: Int, consumer: (ByteBuffer) -> Unit) {
        try {
            repeat(exact) {
                val data = input.read()
                if (data == -1) {
                    onClose()
                    return
                }
                metrics.dataReceived++
                if (readBuffer.remaining() > 0) {
                    readBuffer.put(data.toByte())
                } else if (readBuffer.capacity() >= MAX_READ_BUFFER_CAPACITY) {
                    val exception =
                        RuntimeException("The read buffer has exceeded the maximum size $MAX_READ_BUFFER_CAPACITY")
                    try {
                        launch {
                            close(exception)
                        }
                    } catch (e: Exception) {
                        // ignored
                    }
                    throw exception
                } else {
                    readBuffer = ByteBuffer.allocate(readBuffer.capacity() * 2)
                        .put(readBuffer.flip() as ByteBuffer)
                        .put(data.toByte())
                }
            }
            readBuffer.flip()
            consumer(readBuffer)
            readBuffer.clear()
        } catch (e: Exception) {
            onClose(e)
        }
    }

    fun readVarInt(): Int {
        try {
            var result = 0U
            for (shift in 0 until 4 * 7 step 7) {
                val data = input.read()
                if (data == -1) {
                    throw EOFException()
                }
                metrics.dataReceived++
                val current = data.toUInt()
                result = result or current.and(0x7FU).shl(shift)
                if (current and 0x80U == 0U) {
                    return result.toInt()
                }
            }
            throw IllegalStateException("VarInt input too big")
        } catch (e: Exception) {
            onClose(e)
            throw e
        }
    }

    suspend fun write(src: ByteArray) = withContext(coroutineContext) {
        try {
            synchronized(output) {
                output.write(src)
                metrics.dataSent += src.size
            }
        } catch (e: Exception) {
            onClose(e)
        }
    }

    suspend fun writeVarIntLengthAndBytes(bytes: ByteArray) = withContext(coroutineContext) {
        var currentValue = bytes.size.toUInt()
        synchronized(output) {
            while (true) {
                val maskedValue = currentValue.and(0x7FU)
                currentValue = currentValue.shr(7)
                if (currentValue == 0U) {
                    output.write(maskedValue.toInt())
                    output.write(bytes)
                    metrics.dataSent += bytes.size + 1
                    return@withContext
                } else {
                    output.write(maskedValue.or(0x80U).toInt())
                    metrics.dataSent++
                }
            }
        }
    }

    private fun getSslContext(protocol: String): SSLContext? {
        return try {
            SSLContext.getInstance(protocol)
        } catch (e: NoSuchAlgorithmException) {
            null
        }
    }

    companion object {
        const val INITIAL_READ_BUFFER_CAPACITY: Int = 1024 * 1024
        const val MAX_READ_BUFFER_CAPACITY: Int = 8 * 1024 * 1024
        val setServerNames: Method? = try {
            SSLParameters::class.java.getMethod("setServerNames", List::class.java)
        } catch (exception: NoSuchMethodException) {
            null
        }
        val setHostname: Method? = try {
            SSLSocket::class.java.getMethod("setHostname", String::class.java)
        } catch (exception: NoSuchMethodException) {
            null
        }
    }
}

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
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket
import java.nio.ByteBuffer
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SNIHostName
import javax.net.ssl.SSLContext
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

    var isOpen: Boolean = false
    var isReading: Boolean = false

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
        this.host = host
        this.port = port

        withContext(coroutineContext) {
            socket = Socket(host, port)
            socket.tcpNoDelay = true
            socket.reuseAddress = true
            if (useTls) {
                try {
                    connectTls(host, port, trustManager, serverName, hostname, pins)
                } catch (e: Exception) {
                    try {
                        socket.close()
                    } catch (e: Exception) {
                    }
                    throw e
                }
            }
            input = socket.getInputStream()
            output = socket.getOutputStream()
        }
        readBuffer = ByteBuffer.allocate(INITIAL_READ_BUFFER_CAPACITY)
        isOpen = true
    }

    private fun connectTls(
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
        val ssl = sslContext.socketFactory.createSocket(socket, host, port, true) as SSLSocket
        val sslParameters = sslContext.supportedSSLParameters

        // SNI
        try {
            sslParameters.serverNames = listOf(SNIHostName(serverName))
        } catch (e: Exception) {
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
        } catch (e: Exception) {
            tryCallOnClose(t)
        } finally {
            tryCallOnClose(t)
        }
    }

    private fun tryCallOnClose(t: Throwable? = null) {
        if (isOpen) {
            onClose?.invoke(t)
        }
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
                    tryCallOnClose()
                    return
                }
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
                    }
                    throw exception
                } else {
                    val buf = ByteBuffer.allocate(readBuffer.capacity() * 2)
                    readBuffer.flip()
                    buf.put(readBuffer)
                    buf.put(data.toByte())
                    readBuffer = buf
                }
            }
            readBuffer.flip()
            consumer(readBuffer)
            readBuffer.clear()
        } catch (e: Exception) {
            tryCallOnClose(e)
        }
    }

    fun readVarInt(): Int {
        var result = 0U
        (0 until 4 * 7 step 7).forEach { shift ->
            val current = input.read().toUInt()
            result = result or current.and(0x7FU).shl(shift)
            if (current and 0x80U == 0U) {
                return result.toInt()
            }
        }
        throw IllegalStateException("VarInt input too big")
    }

    suspend fun writeVarInt(value: Int) = withContext(coroutineContext) {
        var currentValue = value.toUInt()
        synchronized(output) {
            while (true) {
                val maskedValue = currentValue.and(0x7FU)
                currentValue = currentValue.shr(7)
                if (currentValue == 0U) {
                    output.write(maskedValue.toInt())
                    return@withContext
                } else {
                    output.write(maskedValue.or(0x80U).toInt())
                }
            }
        }
    }

    suspend fun write(src: ByteArray) = withContext(coroutineContext) {
        try {
            synchronized(output) {
                output.write(src)
            }
        } catch (e: Exception) {
            tryCallOnClose(e)
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
    }

}
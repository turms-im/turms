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
package im.turms.client.driver.service

import im.turms.client.driver.StateStore
import im.turms.client.exception.ResponseException
import im.turms.client.model.ResponseStatusCode
import im.turms.client.transport.Pin
import im.turms.client.transport.TcpClient
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import java.net.SocketException
import java.nio.ByteBuffer
import java.util.LinkedList
import java.util.concurrent.ConcurrentLinkedQueue
import javax.net.ssl.X509TrustManager
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume

/**
 * @author James Chen
 */
class ConnectionService(
    coroutineContext: CoroutineContext,
    stateStore: StateStore,
    host: String?,
    port: Int?,
    connectTimeoutMillis: Int?
) : BaseService(coroutineContext, stateStore) {
    private val initialHost = host ?: "127.0.0.1"
    private val initialPort = port ?: 11510
    private val initialConnectTimeout =
        if (connectTimeoutMillis == null || connectTimeoutMillis <= 0) 30 * 1000 else connectTimeoutMillis
    private val disconnectFutures = ConcurrentLinkedQueue<Continuation<Throwable?>>()
    private val onConnectedListeners: MutableList<() -> Unit> = LinkedList()
    private val onDisconnectedListeners: MutableList<(Throwable?) -> Unit> = LinkedList()
    private val messageListeners: MutableList<(ByteBuffer) -> Unit> = LinkedList()

    private fun resetStates() {
        completeDisconnectFutures()
    }

    // Listeners
    fun addOnConnectedListener(listener: () -> Unit) {
        onConnectedListeners.add(listener)
    }

    fun addOnDisconnectedListener(listener: (Throwable?) -> Unit) {
        onDisconnectedListeners.add(listener)
    }

    fun addMessageListener(listener: (ByteBuffer) -> Unit) {
        messageListeners.add(listener)
    }

    fun removeOnConnectedListener(listener: () -> Unit) {
        onConnectedListeners.remove(listener)
    }

    fun removeOnDisconnectedListener(listener: (Throwable?) -> Unit) {
        onDisconnectedListeners.remove(listener)
    }

    fun removeMessageListener(listener: (ByteBuffer) -> Unit) {
        messageListeners.remove(listener)
    }

    private fun notifyOnConnectedListeners() {
        for (listener in onConnectedListeners) {
            listener()
        }
    }

    private fun notifyOnDisconnectedListeners(t: Throwable?) {
        for (listener in onDisconnectedListeners) {
            listener(t)
        }
    }

    private fun notifyMessageListeners(message: ByteBuffer) {
        for (listener in messageListeners) {
            listener(message)
        }
    }

    private fun completeDisconnectFutures(t: Throwable? = null) {
        while (true) {
            disconnectFutures.poll()?.resume(t) ?: return
        }
    }

    // Connection
    suspend fun connect(
        host: String? = initialHost,
        port: Int? = 11510,
        connectTimeoutMillis: Int? = initialConnectTimeout,
        useTls: Boolean? = false,
        trustManager: X509TrustManager? = null,
        serverName: String? = null,
        hostname: String? = null,
        pins: List<Pin>? = null
    ) {
        if (stateStore.isConnected) {
            if (host == stateStore.tcp?.host && port == stateStore.tcp?.port) {
                return
            } else {
                throw ResponseException.from(ResponseStatusCode.CLIENT_SESSION_ALREADY_ESTABLISHED)
            }
        }
        resetStates()
        val tcp = TcpClient(coroutineContext)
        tcp.onClose = {
            onSocketClosed(it)
        }
        if (connectTimeoutMillis == null) {
            tcp.connect(
                host ?: initialHost,
                port ?: initialPort,
                useTls ?: false,
                trustManager,
                serverName,
                hostname,
                pins
            )
        } else {
            try {
                withTimeout(connectTimeoutMillis.toLong()) {
                    tcp.connect(
                        host ?: initialHost,
                        port ?: initialPort,
                        useTls ?: false,
                        trustManager,
                        serverName,
                        hostname,
                        pins
                    )
                }
            } catch (e: TimeoutCancellationException) {
                tcp.close()
                throw e
            }
        }
        stateStore.tcp = tcp
        onSocketOpened()
        tcp.startReading {
            while (stateStore.isConnected) {
                val length = try {
                    tcp.readVarInt()
                } catch (e: SocketException) {
                    return@startReading
                }
                if (length == 0) {
                    notifyMessageListeners(EMPTY_BUFFER)
                } else {
                    tcp.read(length) {
                        notifyMessageListeners(it)
                    }
                }
            }
        }
    }

    suspend fun disconnect() {
        if (stateStore.isConnected) {
            stateStore.isConnected = false
            stateStore.tcp?.close()
        }
    }

    // Lifecycle hooks
    private fun onSocketOpened() {
        stateStore.isConnected = true
        notifyOnConnectedListeners()
    }

    private fun onSocketClosed(t: Throwable?) {
        stateStore.isConnected = false
        completeDisconnectFutures(t)
        notifyOnDisconnectedListeners(t)
    }

    override suspend fun close() {
        disconnect()
    }

    override fun onDisconnected(throwable: Throwable?) {
    }

    companion object {
        private val EMPTY_BUFFER = ByteBuffer.allocate(0)
    }
}

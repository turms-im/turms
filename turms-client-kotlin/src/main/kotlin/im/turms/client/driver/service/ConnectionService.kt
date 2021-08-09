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

import im.turms.client.constant.TurmsStatusCode
import im.turms.client.driver.StateStore
import im.turms.client.exception.TurmsBusinessException
import im.turms.client.extension.tryResumeWithException
import im.turms.client.model.ConnectionDisconnectInfo
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.internal.ws.RealWebSocket
import okio.ByteString
import java.nio.ByteBuffer
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.TimeUnit
import kotlin.coroutines.*

/**
 * @author James Chen
 */
class ConnectionService(
    coroutineContext: CoroutineContext,
    stateStore: StateStore,
    wsUrl: String?,
    connectTimeout: Int?
) : BaseService(coroutineContext, stateStore) {
    private val initialWsUrl: String = wsUrl ?: DEFAULT_WEBSOCKET_URL
    private val initialConnectTimeout: Int =
        if (connectTimeout == null || connectTimeout <= 0) DEFAULT_CONNECT_TIMEOUT else connectTimeout
    private var httpClient: OkHttpClient = OkHttpClient().newBuilder().build()
    private val disconnectFutures = ConcurrentLinkedQueue<Continuation<Unit>>()
    private val onConnectedListeners: MutableList<() -> Unit> = LinkedList()
    private val onDisconnectedListeners: MutableList<(ConnectionDisconnectInfo) -> Unit> = LinkedList()
    private val messageListeners: MutableList<(ByteBuffer) -> Unit> = LinkedList()

    private fun resetStates() {
        completeDisconnectFutures()
    }

    // Listeners
    fun addOnConnectedListener(listener: () -> Unit) {
        onConnectedListeners.add(listener)
    }

    fun addOnDisconnectedListener(listener: (ConnectionDisconnectInfo) -> Unit) {
        onDisconnectedListeners.add(listener)
    }

    fun addMessageListener(listener: (ByteBuffer) -> Unit) {
        messageListeners.add(listener)
    }

    fun removeOnConnectedListener(listener: () -> Unit) {
        onConnectedListeners.remove(listener)
    }

    fun removeOnDisconnectedListener(listener: (ConnectionDisconnectInfo) -> Unit) {
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

    private fun notifyOnDisconnectedListeners(info: ConnectionDisconnectInfo) {
        for (listener in onDisconnectedListeners) {
            listener(info)
        }
    }

    private fun notifyMessageListeners(message: ByteBuffer) {
        for (listener in messageListeners) {
            listener(message)
        }
    }

    private fun completeDisconnectFutures() {
        while (true) {
            disconnectFutures.poll()?.resume(Unit) ?: return
        }
    }

    // Connection
    suspend fun connect(
        wsUrl: String? = initialWsUrl,
        connectTimeout: Int? = initialConnectTimeout
    ) = suspendCoroutine<Unit> { cont ->
        val wsUrl = wsUrl ?: initialWsUrl
        val connectTimeout = connectTimeout ?: initialConnectTimeout
        if (stateStore.isConnected) {
            if (wsUrl.toHttpUrl() == stateStore.websocket?.request()?.url) {
                cont.resume(Unit)
            } else {
                cont.resumeWithException(TurmsBusinessException(TurmsStatusCode.CLIENT_SESSION_ALREADY_ESTABLISHED))
            }
            return@suspendCoroutine
        }
        resetStates()
        val isConnectTimeoutChanged = connectTimeout != httpClient.connectTimeoutMillis
        if (isConnectTimeoutChanged) {
            httpClient = httpClient.newBuilder()
                .connectTimeout(connectTimeout.toLong(), TimeUnit.MILLISECONDS)
                .build()
        }
        val request = Request.Builder()
            .url(wsUrl)
            .build()
        stateStore.websocket = httpClient.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                onWebSocketOpen()
                cont.resume(Unit)
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                notifyMessageListeners(bytes.asByteBuffer())
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                stateStore.isConnected = false
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                onWebSocketClose(code, reason, null)
                cont.tryResumeWithException(IllegalStateException())
            }

            /**
             * @param response is not null when it failed at handshake stage
             */
            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                onWebSocketClose(1006, null, t)
                cont.tryResumeWithException(t)
            }
        }) as RealWebSocket
    }

    suspend fun disconnect(): Unit = suspendCoroutine { cont ->
        if (stateStore.isConnected) {
            // close is a synchronous method
            val wasEnqueued = stateStore.websocket!!.close(1000, null)
            if (wasEnqueued) {
                disconnectFutures.offer(cont)
            } else {
                cont.resumeWithException(TurmsBusinessException(TurmsStatusCode.MESSAGE_IS_REJECTED))
            }
        } else {
            cont.resume(Unit)
        }
    }

    // Lifecycle hooks
    private fun onWebSocketOpen() {
        stateStore.isConnected = true
        notifyOnConnectedListeners()
    }

    private fun onWebSocketClose(
        code: Int,
        reason: String?,
        throwable: Throwable?
    ): ConnectionDisconnectInfo {
        val wasConnected = stateStore.isConnected
        val url = stateStore.websocket!!.request().url.toUrl()
        completeDisconnectFutures()
        val disconnectInfo = ConnectionDisconnectInfo(wasConnected, url, code, reason, throwable)
        notifyOnDisconnectedListeners(disconnectInfo)
        return disconnectInfo
    }

    companion object {
        private const val DEFAULT_WEBSOCKET_URL = "ws://localhost:10510"
        private const val DEFAULT_CONNECT_TIMEOUT = 30 * 1000
    }

    override suspend fun close() {
        disconnect()
    }

    override fun onDisconnected() {
    }

}
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
package im.turms.client.driver

import com.google.protobuf.Descriptors
import com.google.protobuf.InvalidProtocolBufferException
import com.google.protobuf.Message
import im.turms.client.driver.service.ConnectionService
import im.turms.client.driver.service.HeartbeatService
import im.turms.client.driver.service.MessageService
import im.turms.client.extension.camelToSnakeCase
import im.turms.client.transport.Pin
import im.turms.common.model.dto.notification.TurmsNotification
import im.turms.common.model.dto.request.TurmsRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.nio.ByteBuffer
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.logging.Level
import java.util.logging.Logger
import javax.net.ssl.X509TrustManager
import kotlin.coroutines.CoroutineContext

/**
 * @author James Chen
 */
class TurmsDriver(
    host: String?,
    port: Int?,
    connectTimeoutMillis: Int?,
    requestTimeoutMillis: Int?,
    minRequestIntervalMillis: Int?,
    heartbeatIntervalMillis: Int?,
    context: CoroutineContext?
) : CoroutineScope {

    override val coroutineContext: CoroutineContext =
        context ?: ScheduledThreadPoolExecutor(2) { r -> Thread(r, "turms-scheduler") }.asCoroutineDispatcher()
    internal val stateStore: StateStore = StateStore()

    private val connectionService: ConnectionService =
        ConnectionService(coroutineContext, stateStore, host, port, connectTimeoutMillis).apply {
            addOnDisconnectedListener { onConnectionDisconnected() }
            addMessageListener { byteBuffer: ByteBuffer -> onMessage(byteBuffer) }
        }
    private val heartbeatService: HeartbeatService =
        HeartbeatService(coroutineContext, stateStore, heartbeatIntervalMillis)
    private val messageService: MessageService =
        MessageService(coroutineContext, stateStore, requestTimeoutMillis, minRequestIntervalMillis)

    // Close

    suspend fun close() = coroutineScope {
        val closeConnectionService = async { connectionService.close() }
        val closeHeartbeatService = async { heartbeatService.close() }
        val closeMessageService = async { messageService.close() }
        closeConnectionService.await()
        closeHeartbeatService.await()
        closeMessageService.await()
    }

    // Heartbeat Service

    fun startHeartbeat() = heartbeatService.start()

    fun stopHeartbeat() = heartbeatService.stop()

    suspend fun sendHeartbeat() = heartbeatService.send()

    val isHeartbeatRunning: Boolean
        get() = heartbeatService.isRunning

    // Connection Service

    suspend fun connect(
        host: String? = null,
        port: Int? = null,
        connectTimeoutMillis: Int? = null,
        useTls: Boolean? = null,
        trustManager: X509TrustManager? = null,
        serverName: String? = null,
        hostname: String? = null,
        pins: List<Pin>? = null
    ) = connectionService.connect(host, port, connectTimeoutMillis, useTls, trustManager, serverName, hostname, pins)

    suspend fun disconnect() = connectionService.disconnect()

    val isConnected: Boolean
        get() = stateStore.isConnected

    // Connection Listeners

    fun addOnConnectedListener(listener: () -> Unit) =
        connectionService.addOnConnectedListener(listener)

    fun addOnDisconnectedListener(listener: () -> Unit) =
        connectionService.addOnDisconnectedListener(listener)

    fun removeOnConnectedListener(listener: () -> Unit) =
        connectionService.removeOnConnectedListener(listener)

    fun removeOnDisconnectedListener(listener: () -> Unit) =
        connectionService.removeOnDisconnectedListener(listener)

    // Message Service

    suspend fun send(requestBuilder: TurmsRequest.Builder): TurmsNotification {
        val notification = messageService.sendRequest(requestBuilder)
        if (requestBuilder.hasCreateSessionRequest()) {
            heartbeatService.start()
        }
        return notification
    }

    suspend fun send(builder: Message.Builder): TurmsNotification {
        val descriptor: Descriptors.Descriptor = builder.descriptorForType
        val fieldName = descriptor.name.camelToSnakeCase()
        val requestBuilder: TurmsRequest.Builder = TurmsRequest.newBuilder()
        val requestDescriptor: Descriptors.Descriptor = requestBuilder.descriptorForType
        val fieldDescriptor: Descriptors.FieldDescriptor = requestDescriptor.findFieldByName(fieldName)
        requestBuilder.setField(fieldDescriptor, builder.build())
        return send(requestBuilder)
    }

    fun addNotificationListener(listener: (TurmsNotification) -> Unit) =
        messageService.addNotificationListener(listener)

    fun removeNotificationListener(listener: (TurmsNotification) -> Unit) =
        messageService.removeNotificationListener(listener)

    // Intermediary functions as a mediator between services

    private fun onConnectionDisconnected() {
        stateStore.reset()
        heartbeatService.onDisconnected()
        messageService.onDisconnected()
    }

    private fun onMessage(byteBuffer: ByteBuffer) {
        if (byteBuffer.hasRemaining()) {
            val notification: TurmsNotification = try {
                TurmsNotification.parseFrom(byteBuffer)
            } catch (e: InvalidProtocolBufferException) {
                LOGGER.log(Level.SEVERE, "Failed to parse TurmsNotification", e)
                return
            }
            if (heartbeatService.rejectHeartbeatPromisesIfFail(notification)) {
                return
            }
            val isSessionInfo = notification.hasData() && notification.data.hasUserSession()
            if (isSessionInfo) {
                stateStore.sessionId = notification.data.userSession.sessionId
                stateStore.serverId = notification.data.userSession.serverId
            } else if (notification.hasCloseStatus()) {
                stateStore.isSessionOpen = false
            }
            messageService.didReceiveNotification(notification)
        } else {
            heartbeatService.completeHeartbeatFutures()
        }
    }

    companion object {
        private val LOGGER = Logger.getLogger(TurmsDriver::class.java.name)
    }
}
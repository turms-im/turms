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
import im.turms.client.extension.isSuccessful
import im.turms.client.extension.tryResumeWithException
import im.turms.common.model.dto.notification.TurmsNotification
import im.turms.common.model.dto.request.TurmsRequest
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ThreadLocalRandom
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author James Chen
 */

class MessageService(
    coroutineContext: CoroutineContext,
    stateStore: StateStore,
    requestTimeout: Int?,
    minRequestInterval: Int?
) : BaseService(coroutineContext, stateStore) {
    private val requestTimeout = requestTimeout ?: 60 * 1000
    private val minRequestInterval = minRequestInterval ?: 0
    private val notificationListeners: MutableList<(TurmsNotification) -> Unit> = LinkedList()
    private val requestMap = ConcurrentHashMap<Long, TurmsRequestContext>(256)

    // Listeners
    fun addNotificationListener(listener: (TurmsNotification) -> Unit) {
        notificationListeners.add(listener)
    }

    fun removeNotificationListener(listener: (TurmsNotification) -> Unit) {
        notificationListeners.remove(listener)
    }

    private fun notifyNotificationListeners(notification: TurmsNotification) {
        notificationListeners.forEach { it(notification) }
    }

    // Request and notification

    suspend fun sendRequest(requestBuilder: TurmsRequest.Builder): TurmsNotification = suspendCoroutine { cont ->
        if (requestBuilder.hasCreateSessionRequest()) {
            if (stateStore.isSessionOpen) {
                cont.resumeWithException(TurmsBusinessException(TurmsStatusCode.CLIENT_SESSION_ALREADY_ESTABLISHED))
                return@suspendCoroutine
            }
        } else if (!stateStore.isConnected || !stateStore.isSessionOpen) {
            cont.resumeWithException(TurmsBusinessException(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED))
            return@suspendCoroutine
        }
        val now = Date()
        val difference = now.time - stateStore.lastRequestDate
        val isFrequent = minRequestInterval > 0 && difference <= minRequestInterval
        if (isFrequent) {
            cont.resumeWithException(TurmsBusinessException(TurmsStatusCode.CLIENT_REQUESTS_TOO_FREQUENT))
            return@suspendCoroutine
        }
        stateStore.lastRequestDate = now.time
        while (true) {
            val requestId = generateRandomId()
            val request = requestBuilder
                .setRequestId(requestId)
                .build()
            val requestContext = TurmsRequestContext(request, cont, null)
            val wasRequestAbsent = requestMap.putIfAbsent(requestId, requestContext) == null
            if (wasRequestAbsent) {
                launch {
                    try {
                        val payload = request.toByteArray()
                        val tcp = stateStore.tcp!!
                        tcp.writeVarInt(payload.size)
                        tcp.write(payload)
                    } catch (e: Exception) {
                        cont.resumeWithException(e)
                    }
                }
                if (requestTimeout > 0) {
                    requestContext.timeoutDeferred = async {
                        delay(requestTimeout.toLong())
                        requestMap.remove(requestId)?.let {
                            if (!requestContext.timeoutDeferred!!.isCompleted) {
                                cont.tryResumeWithException(TurmsBusinessException(TurmsStatusCode.REQUEST_TIMEOUT))
                            }
                        }
                    }
                }
                return@suspendCoroutine
            }
        }
    }

    fun didReceiveNotification(notification: TurmsNotification) {
        val isResponse = !notification.hasRelayedRequest() && notification.hasRequestId()
        if (isResponse) {
            requestMap.remove(notification.requestId)?.let {
                val cont = it.cont
                if (notification.hasCode()) {
                    it.timeoutDeferred?.cancel()
                    if (notification.isSuccessful()) {
                        cont.resume(notification)
                    } else {
                        cont.resumeWithException(TurmsBusinessException.get(notification))
                    }
                } else {
                    cont.resumeWithException(
                        TurmsBusinessException(
                            TurmsStatusCode.INVALID_NOTIFICATION,
                            "The code is missing"
                        )
                    )
                }
            }
        }
        notifyNotificationListeners(notification)
    }

    private fun generateRandomId(): Long {
        var id: Long
        do {
            id = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE)
        } while (requestMap.containsKey(id))
        return id
    }

    private fun rejectRequests(e: TurmsBusinessException) {
        val iterator = requestMap.iterator()
        while (iterator.hasNext()) {
            iterator.next().value.cont.tryResumeWithException(e)
        }
    }

    override suspend fun close() = onDisconnected()

    override fun onDisconnected() =
        rejectRequests(TurmsBusinessException(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED))

    private data class TurmsRequestContext(
        val request: TurmsRequest,
        val cont: Continuation<TurmsNotification>,
        var timeoutDeferred: Deferred<*>?
    )
}
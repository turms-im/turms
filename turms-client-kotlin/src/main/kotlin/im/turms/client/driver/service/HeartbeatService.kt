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
import im.turms.client.model.proto.notification.TurmsNotification
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author James Chen
 */
class HeartbeatService(
    coroutineContext: CoroutineContext,
    stateStore: StateStore,
    heartbeatIntervalMillis: Int?
) : BaseService(coroutineContext, stateStore) {
    private val heartbeatInterval = heartbeatIntervalMillis ?: (120 * 1000)
    private val heartbeatTimerInterval = 1L.coerceAtLeast(this.heartbeatInterval / 10L)
    private var lastHeartbeatRequestDate = 0L
    private var heartbeatTimerDeferred: Deferred<*>? = null
    private val heartbeatContinuationQueue = ConcurrentLinkedQueue<Continuation<Unit>>()

    val isRunning: Boolean get() = heartbeatTimerDeferred?.isActive == true

    @Synchronized
    fun start() {
        if (isRunning) {
            return
        }
        heartbeatTimerDeferred = async {
            while (isActive) {
                val now = System.currentTimeMillis()
                val difference = (now - stateStore.lastRequestDate)
                    .coerceAtMost(now - lastHeartbeatRequestDate)
                if (difference > heartbeatInterval) {
                    send()
                    lastHeartbeatRequestDate = now
                }
                delay(heartbeatTimerInterval)
            }
        }
    }

    fun stop(throwable: Throwable? = null) {
        heartbeatTimerDeferred?.cancel(CancellationException(throwable))
    }

    suspend fun send() = suspendCoroutine { cont ->
        if (!stateStore.isConnected || !stateStore.isSessionOpen) {
            cont.resumeWithException(ResponseException.from(ResponseStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED))
            return@suspendCoroutine
        }
        launch {
            try {
                stateStore.tcp!!.write(HEARTBEAT)
            } catch (e: Exception) {
                cont.resumeWithException(e)
            }
        }
        heartbeatContinuationQueue.offer(cont)
    }

    fun completeHeartbeatFutures() {
        while (true) {
            heartbeatContinuationQueue.poll()?.resume(Unit) ?: return
        }
    }

    fun rejectHeartbeatPromisesIfFail(notification: TurmsNotification): Boolean {
        if (notification.hasRequestId() && notification.requestId == HEARTBEAT_FAILURE_REQUEST_ID) {
            rejectHeartbeatRequests(ResponseException.from(notification))
            return true
        }
        return false
    }

    private fun rejectHeartbeatRequests(e: ResponseException) {
        while (true) {
            heartbeatContinuationQueue.poll()?.resumeWithException(e) ?: return
        }
    }

    override suspend fun close() {
        onDisconnected()
    }

    override fun onDisconnected(throwable: Throwable?) {
        stop(throwable)
        rejectHeartbeatRequests(
            ResponseException.from(ResponseStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED, cause = throwable)
        )
    }

    companion object {
        private const val HEARTBEAT_FAILURE_REQUEST_ID = -100L
        private val HEARTBEAT = byteArrayOf(0)
    }
}

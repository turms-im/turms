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
import kotlinx.coroutines.*
import okio.ByteString
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.coroutines.*

/**
 * @author James Chen
 */
class HeartbeatService(
    coroutineContext: CoroutineContext,
    stateStore: StateStore,
    heartbeatInterval: Int?
) : BaseService(coroutineContext, stateStore) {
    private val heartbeatInterval = heartbeatInterval ?: 120 * 1000
    private val heartbeatTimerInterval = 1L.coerceAtLeast(this.heartbeatInterval / 10L)
    private var lastHeartbeatRequestDate = 0L
    private var heartbeatTimerDeferred: Deferred<*>? = null
    private val heartbeatContinuationQueue = ConcurrentLinkedQueue<Continuation<Unit>>()

    @Synchronized
    fun start() {
        if (!isRunning) {
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
    }

    val isRunning: Boolean get() = heartbeatTimerDeferred?.isCompleted == false

    fun stop() {
        heartbeatTimerDeferred?.cancel()
    }

    suspend fun send() = suspendCoroutine<Unit> { cont ->
        if (!stateStore.isConnected || !stateStore.isSessionOpen) {
            cont.resumeWithException(TurmsBusinessException(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED))
            return@suspendCoroutine
        }
        val wasEnqueued = stateStore.websocket!!.send(ByteString.EMPTY)
        if (wasEnqueued) {
            heartbeatContinuationQueue.offer(cont)
        } else {
            cont.resumeWithException(TurmsBusinessException(TurmsStatusCode.MESSAGE_IS_REJECTED))
        }
    }

    fun completeHeartbeatFutures() {
        while (true) {
            heartbeatContinuationQueue.poll()?.resume(Unit) ?: return
        }
    }

    private fun rejectHeartbeatRequests(e: TurmsBusinessException) {
        while (true) {
            heartbeatContinuationQueue.poll()?.resumeWithException(e) ?: return
        }
    }

    override suspend fun close() {
        onDisconnected()
    }

    override fun onDisconnected() {
        stop()
        rejectHeartbeatRequests(TurmsBusinessException(TurmsStatusCode.CLIENT_SESSION_HAS_BEEN_CLOSED))
    }

}
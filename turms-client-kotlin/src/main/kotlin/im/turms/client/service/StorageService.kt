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
package im.turms.client.service

import im.turms.client.TurmsClient
import im.turms.client.constant.TurmsStatusCode
import im.turms.client.exception.TurmsBusinessException
import im.turms.client.extension.tryResumeWithException
import im.turms.common.constant.ContentType
import im.turms.common.model.dto.request.TurmsRequest
import im.turms.common.model.dto.request.storage.DeleteResourceRequest
import im.turms.common.model.dto.request.storage.QuerySignedGetUrlRequest
import im.turms.common.model.dto.request.storage.QuerySignedPutUrlRequest
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author James Chen
 */
class StorageService(private val turmsClient: TurmsClient, storageServerUrl: String?) {
    private val httpClient: OkHttpClient = OkHttpClient().newBuilder().build()
    var serverUrl = storageServerUrl ?: "http://localhost:9000"

    // Profile picture
    fun queryProfilePictureUrlForAccess(userId: Long): String =
        String.format("%s/%s/%d", serverUrl, getBucketName(ContentType.PROFILE), userId)

    suspend fun queryProfilePicture(userId: Long): ByteArray =
        getBytesFromGetUrl(queryProfilePictureUrlForAccess(userId))

    suspend fun queryProfilePictureUrlForUpload(pictureSize: Long): String {
        val userId: Long? = turmsClient.userService.userInfo?.userId
        return if (userId != null) {
            getSignedPutUrl(ContentType.PROFILE, pictureSize, null, userId)
        } else {
            throw TurmsBusinessException(TurmsStatusCode.QUERY_PROFILE_URL_TO_UPDATE_BEFORE_LOGIN)
        }
    }

    suspend fun uploadProfilePicture(bytes: ByteArray): String {
        val url = queryProfilePictureUrlForUpload(bytes.size.toLong())
        return upload(url, bytes)
    }

    suspend fun deleteProfile() = deleteResource(ContentType.PROFILE, null, null)

    // Group profile picture
    fun queryGroupProfilePictureUrlForAccess(groupId: Long): String =
        String.format("%s/%s/%d", serverUrl, getBucketName(ContentType.GROUP_PROFILE), groupId)

    suspend fun queryGroupProfilePicture(groupId: Long): ByteArray {
        val url = queryGroupProfilePictureUrlForAccess(groupId)
        return getBytesFromGetUrl(url)
    }

    suspend fun queryGroupProfilePictureUrlForUpload(pictureSize: Long, groupId: Long): String =
        getSignedPutUrl(ContentType.GROUP_PROFILE, pictureSize, null, groupId)

    suspend fun uploadGroupProfilePicture(bytes: ByteArray, groupId: Long): String {
        val url = queryGroupProfilePictureUrlForUpload(bytes.size.toLong(), groupId)
        return upload(url, bytes)
    }

    suspend fun deleteGroupProfile(groupId: Long) =
        deleteResource(ContentType.GROUP_PROFILE, null, groupId)

    // Message attachment
    suspend fun queryAttachmentUrlForAccess(messageId: Long, name: String? = null): String =
        getSignedGetUrl(ContentType.ATTACHMENT, name, messageId)

    suspend fun queryAttachment(messageId: Long, name: String? = null): ByteArray {
        val url = queryAttachmentUrlForAccess(messageId, name)
        return getBytesFromGetUrl(url)
    }

    suspend fun queryAttachmentUrlForUpload(messageId: Long, attachmentSize: Long): String =
        getSignedPutUrl(ContentType.ATTACHMENT, attachmentSize, null, messageId)

    suspend fun uploadAttachment(messageId: Long, bytes: ByteArray): String {
        val url = queryAttachmentUrlForUpload(messageId, bytes.size.toLong())
        return upload(url, bytes)
    }

    // Base
    private suspend fun getSignedGetUrl(
        contentType: ContentType,
        keyStr: String? = null,
        keyNum: Long? = null
    ): String {
        val urlBuilder: QuerySignedGetUrlRequest.Builder = QuerySignedGetUrlRequest.newBuilder()
            .setContentType(contentType)
        if (keyStr != null) {
            urlBuilder.keyStr = keyStr
        }
        if (keyNum != null) {
            urlBuilder.keyNum = keyNum
        }
        val urlRequest: QuerySignedGetUrlRequest = urlBuilder.build()
        val builder: TurmsRequest.Builder = TurmsRequest.newBuilder()
            .setQuerySignedGetUrlRequest(urlRequest)
        return turmsClient.driver.send(builder).data.url
    }

    private suspend fun getSignedPutUrl(
        contentType: ContentType,
        size: Long,
        keyStr: String? = null,
        keyNum: Long? = null
    ): String {
        val urlBuilder: QuerySignedPutUrlRequest.Builder = QuerySignedPutUrlRequest.newBuilder()
            .setContentLength(size)
            .setContentType(contentType)
        if (keyStr != null) {
            urlBuilder.keyStr = keyStr
        }
        if (keyNum != null) {
            urlBuilder.keyNum = keyNum
        }
        val urlRequest: QuerySignedPutUrlRequest = urlBuilder.build()
        val builder: TurmsRequest.Builder = TurmsRequest.newBuilder()
            .setQuerySignedPutUrlRequest(urlRequest)
        return turmsClient.driver.send(builder).data.url
    }

    private suspend fun deleteResource(contentType: ContentType, keyStr: String? = null, keyNum: Long? = null) {
        val requestBuilder: DeleteResourceRequest.Builder = DeleteResourceRequest.newBuilder()
            .setContentType(contentType)
        if (keyStr != null) {
            requestBuilder.keyStr = keyStr
        }
        if (keyNum != null) {
            requestBuilder.keyNum = keyNum
        }
        val request: DeleteResourceRequest = requestBuilder.build()
        val builder: TurmsRequest.Builder = TurmsRequest.newBuilder()
            .setDeleteResourceRequest(request)
        return turmsClient.driver
            .send(builder)
            .run {}
    }

    private suspend fun getBytesFromGetUrl(url: String) = suspendCoroutine<ByteArray> {
        val request: Request = Request.Builder()
            .url(url)
            .build()
        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                it.tryResumeWithException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body
                if (body != null) {
                    it.resume(body.bytes())
                } else {
                    it.resumeWithException(TurmsBusinessException(TurmsStatusCode.INVALID_RESPONSE))
                }
            }
        })
    }

    private suspend fun upload(url: String, bytes: ByteArray) = suspendCoroutine<String> {
        val request: Request = Request.Builder()
            .url(url)
            .put(bytes.toRequestBody())
            .build()
        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                it.resumeWithException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body
                if (body != null) {
                    it.resume(body.string())
                } else {
                    it.resumeWithException(TurmsBusinessException(TurmsStatusCode.INVALID_RESPONSE))
                }
            }
        })
    }

    private fun getBucketName(contentType: ContentType): String {
        return contentType.name.lowercase().replace("_", "-")
    }

}
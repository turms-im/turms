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
import im.turms.client.exception.ResponseException
import im.turms.client.extension.toMap
import im.turms.client.extension.toResponse
import im.turms.client.extension.tryResumeWithException
import im.turms.client.model.Response
import im.turms.client.model.ResponseStatusCode
import im.turms.client.model.StorageResource
import im.turms.client.model.StorageUploadResult
import im.turms.client.model.proto.constant.StorageResourceType
import im.turms.client.model.proto.request.TurmsRequest
import im.turms.client.model.proto.request.storage.DeleteResourceRequest
import im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest
import im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest
import okhttp3.Call
import okhttp3.Callback
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.net.URI
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author James Chen
 */
class StorageService(private val turmsClient: TurmsClient, storageServerUrl: String?) {
    private val httpClient: OkHttpClient = OkHttpClient().newBuilder().build()
    var serverUrl = storageServerUrl ?: "http://localhost:9000"

    // User profile picture

    suspend fun uploadUserProfilePicture(
        mediaType: String,
        data: ByteArray,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageUploadResult> {
        val type = parseMediaType(mediaType)
        if (data.isEmpty()) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "The data of user profile picture must not be empty"
            )
        }
        val userId = turmsClient.userService.userInfo?.userId ?: throw ResponseException.from(
            ResponseStatusCode.UPLOAD_USER_PROFILE_PICTURE_BEFORE_LOGIN
        )
        val uploadInfo = queryUserProfilePictureUploadInfo()
        val responseData = uploadInfo.data.toMutableMap()
        val url = getAndRemoveResourceUrl(responseData, urlKeyName)
        return upload(url, responseData, userId.toString(), type, data)
    }

    suspend fun deleteUserProfilePicture(): Response<Unit> =
        deleteResource(StorageResourceType.USER_PROFILE_PICTURE)

    suspend fun queryUserProfilePicture(
        userId: Long,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageResource> {
        val downloadInfo = queryUserProfilePictureDownloadInfo(
            userId,
            urlKeyName = urlKeyName
        )
        val url = getResourceUrl(downloadInfo.data, urlKeyName)
        return queryResource(url)
    }

    suspend fun queryUserProfilePictureUploadInfo(): Response<Map<String, String>> {
        val userId = turmsClient.userService.userInfo?.userId
        return if (userId == null) {
            throw ResponseException.from(ResponseStatusCode.QUERY_USER_PROFILE_PICTURE_BEFORE_LOGIN)
        } else {
            queryResourceUploadInfo(StorageResourceType.USER_PROFILE_PICTURE)
        }
    }

    suspend fun queryUserProfilePictureDownloadInfo(
        userId: Long,
        fetch: Boolean = false,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<Map<String, String>> {
        if (fetch) {
            return queryResourceDownloadInfo(
                StorageResourceType.USER_PROFILE_PICTURE,
                keyNum = userId
            )
        }
        return Response.value(
            mapOf(urlKeyName to "$serverUrl/${getBucketName(StorageResourceType.USER_PROFILE_PICTURE)}/$userId")
        )
    }

    // Group profile picture

    suspend fun uploadGroupProfilePicture(
        groupId: Long,
        mediaType: String,
        data: ByteArray,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageUploadResult> {
        val type = parseMediaType(mediaType)
        if (data.isEmpty()) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "The data of group profile picture must not be empty"
            )
        }
        val uploadInfo = queryGroupProfilePictureUploadInfo(groupId)
        val responseData = uploadInfo.data.toMutableMap()
        val url = getAndRemoveResourceUrl(responseData, urlKeyName)
        return upload(url, responseData, groupId.toString(), type, data)
    }

    suspend fun deleteGroupProfilePicture(groupId: Long) =
        deleteResource(StorageResourceType.GROUP_PROFILE_PICTURE, keyNum = groupId)

    suspend fun queryGroupProfilePicture(
        groupId: Long,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageResource> {
        val downloadInfo = queryGroupProfilePictureDownloadInfo(groupId, urlKeyName = urlKeyName)
        val url = getResourceUrl(downloadInfo.data, urlKeyName)
        return queryResource(url)
    }

    suspend fun queryGroupProfilePictureUploadInfo(groupId: Long): Response<Map<String, String>> =
        queryResourceUploadInfo(StorageResourceType.GROUP_PROFILE_PICTURE, keyNum = groupId)

    suspend fun queryGroupProfilePictureDownloadInfo(
        groupId: Long,
        fetch: Boolean = false,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<Map<String, String>> {
        if (fetch) {
            return queryResourceDownloadInfo(StorageResourceType.GROUP_PROFILE_PICTURE, keyNum = groupId)
        }
        return Response.value(
            mapOf(urlKeyName to "$serverUrl/${getBucketName(StorageResourceType.GROUP_PROFILE_PICTURE)}/$groupId")
        )
    }

    // Message attachment

    suspend fun uploadMessageAttachment(
        messageId: Long,
        mediaType: String,
        data: ByteArray,
        name: String? = null,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageUploadResult> {
        val type = parseMediaType(mediaType)
        if (data.isEmpty()) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "The data of message attachment must not be empty"
            )
        }
        val uploadInfo = queryMessageAttachmentUploadInfo(messageId, name)
        val responseData = uploadInfo.data.toMutableMap()
        val url = getAndRemoveResourceUrl(responseData, urlKeyName)
        val resourceName = if (name == null) messageId.toString() else "$messageId/$name"
        return upload(url, responseData, resourceName, type, data)
    }

    suspend fun queryMessageAttachment(
        messageId: Long,
        name: String? = null,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageResource> {
        val downloadInfo = queryMessageAttachmentDownloadInfo(messageId, name)
        val url = getResourceUrl(downloadInfo.data, urlKeyName)
        return queryResource(url)
    }

    suspend fun queryMessageAttachmentUploadInfo(messageId: Long, name: String?): Response<Map<String, String>> =
        queryResourceUploadInfo(StorageResourceType.MESSAGE_ATTACHMENT, name, messageId)

    suspend fun queryMessageAttachmentDownloadInfo(messageId: Long, name: String? = null): Response<Map<String, String>> =
        queryResourceDownloadInfo(StorageResourceType.MESSAGE_ATTACHMENT, name, messageId)

    // Base

    private suspend fun upload(
        url: String,
        formData: Map<String, String>,
        resourceName: String,
        mediaType: MediaType,
        data: ByteArray
    ) = suspendCoroutine {
        if (data.isEmpty()) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "The data of resource must not be empty"
            )
        }
        val httpUrl: HttpUrl
        val uri: URI
        try {
            httpUrl = url.toHttpUrl()
            uri = httpUrl.toUri()
        } catch (e: Exception) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "The URL is illegal: $url",
                e
            )
        }
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .apply {
                for (entry in formData.entries) {
                    addFormDataPart(entry.key, entry.value)
                }
            }
            .addFormDataPart("key", resourceName)
            .addFormDataPart("Content-Type", mediaType.toString())
            .addFormDataPart("file", resourceName, data.toRequestBody(mediaType))
            .build()
        val request: Request = Request.Builder()
            .url(httpUrl)
            .post(requestBody)
            .build()
        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                it.resumeWithException(
                    ResponseException.from(
                        ResponseStatusCode.HTTP_ERROR,
                        "Caught an error while sending an HTTP POST request to update the resource",
                        e
                    )
                )
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    try {
                        val responseData = response.body?.string() ?: ""
                        it.resume(
                            Response.value(
                                StorageUploadResult(
                                    uri,
                                    response.headers.toMap(),
                                    responseData
                                )
                            )
                        )
                    } catch (e: Exception) {
                        it.resumeWithException(
                            ResponseException.from(
                                ResponseStatusCode.INVALID_RESPONSE,
                                "Failed to get the response body as a string",
                                e
                            )
                        )
                    }
                } else {
                    it.resumeWithException(
                        ResponseException.from(
                            ResponseStatusCode.HTTP_NOT_SUCCESSFUL_RESPONSE,
                            "Failed to retrieve the resource because the HTTP response status code is: ${response.code}"
                        )
                    )
                }
            }
        })
    }

    private suspend fun deleteResource(
        type: StorageResourceType,
        keyStr: String? = null,
        keyNum: Long? = null
    ): Response<Unit> {
        val request = TurmsRequest.newBuilder()
            .setDeleteResourceRequest(DeleteResourceRequest.newBuilder()
                .setType(type)
                .apply {
                    keyStr?.let { this.keyStr = it }
                    keyNum?.let { this.keyNum = it }
                }
                .build())
        return turmsClient.driver
            .send(request)
            .toResponse()
    }

    private suspend fun queryResource(url: String) = suspendCoroutine {
        val httpUrl: HttpUrl
        try {
            httpUrl = url.toHttpUrl()
        } catch (e: Exception) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "The URL is illegal: $url",
                e
            )
        }
        val request = Request.Builder()
            .url(httpUrl)
            .build()
        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                it.tryResumeWithException(
                    ResponseException.from(
                        ResponseStatusCode.HTTP_ERROR,
                        "Caught an error while sending an HTTP GET request to retrieve the resource",
                        e
                    )
                )
            }

            override fun onResponse(call: Call, response: okhttp3.Response) {
                return if (response.isSuccessful) {
                    it.resume(
                        Response.value(
                            StorageResource(httpUrl.toString(), response.headers.toMap(), response.body!!.bytes())
                        )
                    )
                } else {
                    it.resumeWithException(
                        ResponseException.from(
                            ResponseStatusCode.HTTP_NOT_SUCCESSFUL_RESPONSE,
                            "Failed to retrieve the resource because the HTTP response status code is: ${response.code}"
                        )
                    )
                }
            }
        })
    }

    private suspend fun queryResourceUploadInfo(
        type: StorageResourceType,
        keyStr: String? = null,
        keyNum: Long? = null
    ): Response<Map<String, String>> {
        val request = TurmsRequest.newBuilder()
            .setQueryResourceUploadInfoRequest(
                QueryResourceUploadInfoRequest.newBuilder()
                    .setType(type)
                    .apply {
                        keyStr?.let { this.keyStr = it }
                        keyNum?.let { this.keyNum = it }
                    }
                    .build())
        return turmsClient.driver.send(request)
            .toResponse { it.stringsWithVersion.stringsList.toMap() }
    }

    private suspend fun queryResourceDownloadInfo(
        type: StorageResourceType,
        keyStr: String? = null,
        keyNum: Long? = null
    ): Response<Map<String, String>> {
        val request: TurmsRequest.Builder = TurmsRequest.newBuilder()
            .setQueryResourceDownloadInfoRequest(QueryResourceDownloadInfoRequest.newBuilder()
                .setType(type)
                .apply {
                    keyStr?.let { this.keyStr = it }
                    keyNum?.let { this.keyNum = it }
                }
                .build())
        return turmsClient.driver.send(request)
            .toResponse { it.stringsWithVersion.stringsList.toMap() }
    }

    private fun getBucketName(resourceType: StorageResourceType): String {
        return RESOURCE_TYPE_TO_BUCKET_NAME[resourceType]!!
    }

    private fun parseMediaType(mediaType: String): MediaType {
        try {
            return mediaType.toMediaType()
        } catch (e: Exception) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "The media type is illegal",
                e
            )
        }
    }

    private fun getResourceUrl(data: Map<String, String>, urlKeyName: String): String {
        return data[urlKeyName]
            ?: throw ResponseException.from(
                ResponseStatusCode.INVALID_RESPONSE,
                "Cannot get the resource URL because the key \"$urlKeyName\" doesn\'t exist"
            )
    }

    private fun getAndRemoveResourceUrl(data: MutableMap<String, String>, urlKeyName: String): String {
        return data.remove(urlKeyName)
            ?: throw ResponseException.from(
                ResponseStatusCode.INVALID_RESPONSE,
                "Cannot get the resource URL because the key \"$urlKeyName\" doesn\'t exist"
            )
    }

    companion object {
        private const val DEFAULT_URL_KEY_NAME = "url"
        private val RESOURCE_TYPE_TO_BUCKET_NAME = StorageResourceType.values()
            .filter { it !== StorageResourceType.UNRECOGNIZED }
            .associateBy({ it }, { it.name.lowercase().replace('_', '-') })
    }

}
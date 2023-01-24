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
import im.turms.client.model.proto.model.storage.StorageResourceInfo
import im.turms.client.model.proto.request.storage.DeleteResourceRequest
import im.turms.client.model.proto.request.storage.QueryMessageAttachmentInfosRequest
import im.turms.client.model.proto.request.storage.QueryResourceDownloadInfoRequest
import im.turms.client.model.proto.request.storage.QueryResourceUploadInfoRequest
import im.turms.client.model.proto.request.storage.UpdateMessageAttachmentInfoRequest
import im.turms.client.util.Validator
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
import java.util.Date
import java.util.EnumMap
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
        data: ByteArray,
        name: String? = null,
        mediaType: String? = null,
        extra: Map<String, String>? = null,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageUploadResult> {
        if (data.isEmpty()) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "The data of user profile picture must not be empty"
            )
        }
        val type = if (mediaType == null) null else parseMediaType(mediaType)
        val uploadInfo = queryUserProfilePictureUploadInfo(name, mediaType, extra)
        val responseData = uploadInfo.data.toMutableMap()
        val url = getAndRemoveResourceUrl(responseData, urlKeyName)
        val id = responseData.remove(RESOURCE_ID_KEY_NAME)
            ?: throw ResponseException.from(
                code = ResponseStatusCode.DATA_NOT_FOUND,
                reason = "Could not get the resource ID because the key \"$RESOURCE_ID_KEY_NAME\" does not exist in the data: ${uploadInfo.data}"
            )
        return upload(url, responseData, data, id, name, type)
    }

    suspend fun deleteUserProfilePicture(extra: Map<String, String>? = null): Response<Unit> =
        deleteResource(StorageResourceType.USER_PROFILE_PICTURE, extra = extra)

    suspend fun queryUserProfilePicture(
        userId: Long,
        extra: Map<String, String>? = null,
        fetchDownloadInfo: Boolean = false,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageResource> {
        val downloadInfo = queryUserProfilePictureDownloadInfo(
            userId,
            extra,
            fetchDownloadInfo,
            urlKeyName
        )
        val url = getResourceUrl(downloadInfo.data, urlKeyName)
        return queryResource(url)
    }

    suspend fun queryUserProfilePictureUploadInfo(
        name: String? = null,
        mediaType: String? = null,
        extra: Map<String, String>? = null
    ): Response<Map<String, String>> {
        return queryResourceUploadInfo(
            StorageResourceType.USER_PROFILE_PICTURE,
            name = name,
            mediaType = mediaType,
            extra = extra
        )
    }

    suspend fun queryUserProfilePictureDownloadInfo(
        userId: Long,
        extra: Map<String, String>? = null,
        fetch: Boolean = false,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<Map<String, String>> {
        if (fetch) {
            return queryResourceDownloadInfo(
                StorageResourceType.USER_PROFILE_PICTURE,
                idNum = userId,
                extra = extra
            )
        }
        return Response.value(
            mapOf(urlKeyName to "$serverUrl/${getBucketName(StorageResourceType.USER_PROFILE_PICTURE)}/$userId")
        )
    }

    // Group profile picture

    suspend fun uploadGroupProfilePicture(
        groupId: Long,
        data: ByteArray,
        name: String? = null,
        mediaType: String? = null,
        extra: Map<String, String>? = null,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageUploadResult> {
        if (data.isEmpty()) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "The data of group profile picture must not be empty"
            )
        }
        val type = if (mediaType == null) null else parseMediaType(mediaType)
        val uploadInfo = queryGroupProfilePictureUploadInfo(groupId, name, mediaType, extra)
        val responseData = uploadInfo.data.toMutableMap()
        val url = getAndRemoveResourceUrl(responseData, urlKeyName)
        val id = responseData.remove(RESOURCE_ID_KEY_NAME)
            ?: throw ResponseException.from(
                code = ResponseStatusCode.DATA_NOT_FOUND,
                reason = "Could not get the resource ID because the key \"$RESOURCE_ID_KEY_NAME\" does not exist in the data: ${uploadInfo.data}"
            )
        return upload(url, responseData, data, id, name, type)
    }

    suspend fun deleteGroupProfilePicture(
        groupId: Long,
        extra: Map<String, String>? = null
    ) = deleteResource(StorageResourceType.GROUP_PROFILE_PICTURE, idNum = groupId, extra = extra)

    suspend fun queryGroupProfilePicture(
        groupId: Long,
        extra: Map<String, String>? = null,
        fetchDownloadInfo: Boolean = false,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageResource> {
        val downloadInfo = queryGroupProfilePictureDownloadInfo(groupId, extra, fetchDownloadInfo, urlKeyName)
        val url = getResourceUrl(downloadInfo.data, urlKeyName)
        return queryResource(url)
    }

    suspend fun queryGroupProfilePictureUploadInfo(
        groupId: Long,
        name: String? = null,
        mediaType: String? = null,
        extra: Map<String, String>? = null
    ): Response<Map<String, String>> =
        queryResourceUploadInfo(
            StorageResourceType.GROUP_PROFILE_PICTURE,
            idNum = groupId,
            name = name,
            mediaType = mediaType,
            extra = extra
        )

    suspend fun queryGroupProfilePictureDownloadInfo(
        groupId: Long,
        extra: Map<String, String>? = null,
        fetch: Boolean = false,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<Map<String, String>> {
        if (fetch) {
            return queryResourceDownloadInfo(
                StorageResourceType.GROUP_PROFILE_PICTURE,
                idNum = groupId,
                extra = extra
            )
        }
        return Response.value(
            mapOf(urlKeyName to "$serverUrl/${getBucketName(StorageResourceType.GROUP_PROFILE_PICTURE)}/$groupId")
        )
    }

    // Message attachment

    suspend fun uploadMessageAttachment(
        data: ByteArray,
        name: String? = null,
        mediaType: String? = null,
        extra: Map<String, String>? = null,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageUploadResult> {
        return uploadMessageAttachment0(
            data,
            name = name,
            mediaType = mediaType,
            extra = extra,
            urlKeyName = urlKeyName
        )
    }

    suspend fun uploadMessageAttachmentInPrivateConversation(
        userId: Long,
        data: ByteArray,
        name: String? = null,
        mediaType: String? = null,
        extra: Map<String, String>? = null,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageUploadResult> {
        return uploadMessageAttachment0(
            data,
            userId = userId,
            name = name,
            mediaType = mediaType,
            extra = extra,
            urlKeyName = urlKeyName
        )
    }

    suspend fun uploadMessageAttachmentInGroupConversation(
        groupId: Long,
        data: ByteArray,
        name: String? = null,
        mediaType: String? = null,
        extra: Map<String, String>? = null,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageUploadResult> {
        return uploadMessageAttachment0(
            data,
            groupId = groupId,
            name = name,
            mediaType = mediaType,
            extra = extra,
            urlKeyName = urlKeyName
        )
    }

    private suspend fun uploadMessageAttachment0(
        data: ByteArray,
        userId: Long? = null,
        groupId: Long? = null,
        name: String? = null,
        mediaType: String? = null,
        extra: Map<String, String>? = null,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageUploadResult> {
        if (data.isEmpty()) {
            throw ResponseException.from(
                ResponseStatusCode.ILLEGAL_ARGUMENT,
                "The data of message attachment must not be empty"
            )
        }
        val type = if (mediaType == null) null else parseMediaType(mediaType)
        val uploadInfo: Response<Map<String, String>>
        if (userId == null && groupId == null) {
            uploadInfo = queryMessageAttachmentUploadInfo(
                name,
                mediaType,
                extra
            )
        } else if (userId != null) {
            if (groupId != null) {
                throw ResponseException.from(
                    code = ResponseStatusCode.ILLEGAL_ARGUMENT,
                    reason = "The user ID and the group ID must not both be non-null"
                )
            }
            uploadInfo = queryMessageAttachmentUploadInfoInPrivateConversation(
                userId,
                name,
                mediaType,
                extra
            )
        } else {
            uploadInfo = queryMessageAttachmentUploadInfoInGroupConversation(
                groupId!!,
                name,
                mediaType,
                extra
            )
        }
        val responseData = uploadInfo.data.toMutableMap()
        val url = getAndRemoveResourceUrl(responseData, urlKeyName)
        val id = responseData.remove(RESOURCE_ID_KEY_NAME)
            ?: throw ResponseException.from(
                code = ResponseStatusCode.DATA_NOT_FOUND,
                reason = "Could not get the resource ID because the key \"$RESOURCE_ID_KEY_NAME\" does not exist in the data: ${uploadInfo.data}"
            )
        return upload(url, responseData, data, id, name, type)
    }

    suspend fun deleteMessageAttachment(
        attachmentIdNum: Long? = null,
        attachmentIdStr: String? = null,
        extra: Map<String, String>? = null
    ): Response<Unit> {
        if (Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr)) {
            throw ResponseException.from(
                code = ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason = "One and only one attachment ID must be specified"
            )
        }
        return deleteResource(StorageResourceType.MESSAGE_ATTACHMENT, attachmentIdNum, attachmentIdStr, extra)
    }

    suspend fun shareMessageAttachmentWithUser(
        userId: Long,
        attachmentIdNum: Long? = null,
        attachmentIdStr: String? = null
    ): Response<Unit> {
        if (Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr)) {
            throw ResponseException.from(
                code = ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason = "One and only one attachment ID must be specified"
            )
        }
        val request = UpdateMessageAttachmentInfoRequest.newBuilder()
            .apply {
                attachmentIdNum?.let { this.attachmentIdNum = it }
                attachmentIdStr?.let { this.attachmentIdStr = it }
                userIdToShareWith = userId
            }
        return turmsClient.driver.send(request)
            .toResponse()
    }

    suspend fun shareMessageAttachmentWithGroup(
        groupId: Long,
        attachmentIdNum: Long? = null,
        attachmentIdStr: String? = null
    ): Response<Unit> {
        if (Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr)) {
            throw ResponseException.from(
                code = ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason = "One and only one attachment ID must be specified"
            )
        }
        val request = UpdateMessageAttachmentInfoRequest.newBuilder()
            .apply {
                attachmentIdNum?.let { this.attachmentIdNum = it }
                attachmentIdStr?.let { this.attachmentIdStr = it }
                groupIdToShareWith = groupId
            }
        return turmsClient.driver.send(request)
            .toResponse()
    }

    suspend fun unshareMessageAttachmentWithUser(
        userId: Long,
        attachmentIdNum: Long? = null,
        attachmentIdStr: String? = null
    ): Response<Unit> {
        if (Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr)) {
            throw ResponseException.from(
                code = ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason = "One and only one attachment ID must be specified"
            )
        }
        val request = UpdateMessageAttachmentInfoRequest.newBuilder()
            .apply {
                attachmentIdNum?.let { this.attachmentIdNum = it }
                attachmentIdStr?.let { this.attachmentIdStr = it }
                userIdToUnshareWith = userId
            }
        return turmsClient.driver.send(request)
            .toResponse()
    }

    suspend fun unshareMessageAttachmentWithGroup(
        groupId: Long,
        attachmentIdNum: Long? = null,
        attachmentIdStr: String? = null
    ): Response<Unit> {
        if (Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr)) {
            throw ResponseException.from(
                code = ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason = "One and only one attachment ID must be specified"
            )
        }
        val request = UpdateMessageAttachmentInfoRequest.newBuilder()
            .apply {
                attachmentIdNum?.let { this.attachmentIdNum = it }
                attachmentIdStr?.let { this.attachmentIdStr = it }
                groupIdToUnshareWith = groupId
            }
        return turmsClient.driver.send(request)
            .toResponse()
    }

    suspend fun queryMessageAttachment(
        attachmentIdNum: Long? = null,
        attachmentIdStr: String? = null,
        extra: Map<String, String>? = null,
        fetchDownloadInfo: Boolean = false,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<StorageResource> {
        if (Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr)) {
            throw ResponseException.from(
                code = ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason = "One and only one attachment ID must be specified"
            )
        }
        val downloadInfo = queryMessageAttachmentDownloadInfo(attachmentIdNum, attachmentIdStr, extra, fetchDownloadInfo, urlKeyName)
        val url = getResourceUrl(downloadInfo.data, urlKeyName)
        return queryResource(url)
    }

    suspend fun queryMessageAttachmentUploadInfo(
        name: String? = null,
        mediaType: String? = null,
        extra: Map<String, String>? = null
    ): Response<Map<String, String>> =
        queryResourceUploadInfo(StorageResourceType.MESSAGE_ATTACHMENT, name = name, mediaType = mediaType, extra = extra)

    suspend fun queryMessageAttachmentUploadInfoInPrivateConversation(
        userId: Long,
        name: String? = null,
        mediaType: String? = null,
        extra: Map<String, String>? = null
    ): Response<Map<String, String>> = queryResourceUploadInfo(
        StorageResourceType.MESSAGE_ATTACHMENT,
        userId,
        name = name,
        mediaType = mediaType,
        extra = extra
    )

    suspend fun queryMessageAttachmentUploadInfoInGroupConversation(
        groupId: Long,
        name: String? = null,
        mediaType: String? = null,
        extra: Map<String, String>? = null
    ): Response<Map<String, String>> = queryResourceUploadInfo(
        StorageResourceType.MESSAGE_ATTACHMENT,
        -groupId,
        name = name,
        mediaType = mediaType,
        extra = extra
    )

    suspend fun queryMessageAttachmentDownloadInfo(
        attachmentIdNum: Long? = null,
        attachmentIdStr: String? = null,
        extra: Map<String, String>? = null,
        fetch: Boolean = false,
        urlKeyName: String = DEFAULT_URL_KEY_NAME
    ): Response<Map<String, String>> {
        if (Validator.areAllNullOrNonNull(attachmentIdNum, attachmentIdStr)) {
            throw ResponseException.from(
                code = ResponseStatusCode.ILLEGAL_ARGUMENT,
                reason = "One and only one attachment ID must be specified"
            )
        }
        if (fetch) {
            return queryResourceDownloadInfo(StorageResourceType.MESSAGE_ATTACHMENT, attachmentIdNum, attachmentIdStr, extra)
        }
        return Response.value(
            mapOf(urlKeyName to "$serverUrl/${getBucketName(StorageResourceType.MESSAGE_ATTACHMENT)}/${attachmentIdNum ?: attachmentIdStr}")
        )
    }

    suspend fun queryMessageAttachmentInfosUploadedByMe(
        creationDateStart: Date? = null,
        creationDateEnd: Date? = null
    ): Response<List<StorageResourceInfo>> {
        val n = turmsClient.driver.send(
            QueryMessageAttachmentInfosRequest.newBuilder()
                .apply {
                    creationDateStart?.let { this.creationDateStart = it.time }
                    creationDateEnd?.let { this.creationDateEnd = it.time }
                }
        )
        return n.toResponse { it.storageResourceInfos.infosList }
    }

    suspend fun queryMessageAttachmentInfosInPrivateConversations(
        userIds: Set<Long>,
        areSharedByMe: Boolean? = null,
        creationDateStart: Date? = null,
        creationDateEnd: Date? = null
    ): Response<List<StorageResourceInfo>> {
        val n = turmsClient.driver.send(
            QueryMessageAttachmentInfosRequest.newBuilder()
                .addAllUserIds(userIds)
                .apply {
                    areSharedByMe?.let { this.areSharedByMe = it }
                    creationDateStart?.let { this.creationDateStart = it.time }
                    creationDateEnd?.let { this.creationDateEnd = it.time }
                }
        )
        return n.toResponse { it.storageResourceInfos.infosList }
    }

    suspend fun queryMessageAttachmentInfosInGroupConversations(
        groupIds: Set<Long>,
        userIds: Set<Long>? = null,
        creationDateStart: Date? = null,
        creationDateEnd: Date? = null
    ): Response<List<StorageResourceInfo>> {
        val n = turmsClient.driver.send(
            QueryMessageAttachmentInfosRequest.newBuilder()
                .addAllGroupIds(groupIds)
                .apply {
                    userIds?.let {
                        addAllUserIds(it)
                    }
                    creationDateStart?.let { this.creationDateStart = it.time }
                    creationDateEnd?.let { this.creationDateEnd = it.time }
                }
        )
        return n.toResponse { it.storageResourceInfos.infosList }
    }

    // Base

    private suspend fun upload(
        url: String,
        formData: Map<String, String>,
        data: ByteArray,
        id: String,
        name: String? = null,
        mediaType: MediaType? = null
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
                mediaType?.let {
                    addFormDataPart("Content-Type", it.toString())
                }
            }
            .addFormDataPart("key", id)
            .addFormDataPart("file", name ?: id, data.toRequestBody(mediaType))
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
                        val idNum = id.toLongOrNull()
                        val idStr = if (idNum == null) id else null
                        it.resume(
                            Response.value(
                                StorageUploadResult(
                                    uri,
                                    response.headers.toMap(),
                                    responseData,
                                    resourceIdNum = idNum,
                                    resourceIdStr = idStr
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
        idNum: Long? = null,
        idStr: String? = null,
        extra: Map<String, String>? = null
    ): Response<Unit> {
        val request = DeleteResourceRequest.newBuilder()
            .setType(type)
            .apply {
                idNum?.let { this.idNum = it }
                idStr?.let { this.idStr = it }
                extra?.let { extraMap.putAll(extra) }
            }
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
        idNum: Long? = null,
        idStr: String? = null,
        name: String? = null,
        mediaType: String? = null,
        extra: Map<String, String>? = null
    ): Response<Map<String, String>> {
        val request = QueryResourceUploadInfoRequest.newBuilder()
            .setType(type)
            .apply {
                idNum?.let { this.idNum = it }
                idStr?.let { this.idStr = it }
                name?.let { this.name = it }
                mediaType?.let { this.mediaType = it }
                extra?.let { extraMap.putAll(it) }
            }
        return turmsClient.driver.send(request)
            .toResponse { it.stringsWithVersion.stringsList.toMap() }
    }

    private suspend fun queryResourceDownloadInfo(
        type: StorageResourceType,
        idNum: Long? = null,
        idStr: String? = null,
        extra: Map<String, String>? = null
    ): Response<Map<String, String>> {
        val request = QueryResourceDownloadInfoRequest.newBuilder()
            .setType(type)
            .apply {
                idNum?.let { this.idNum = it }
                idStr?.let { this.idStr = it }
                extra?.let { extraMap.putAll(it) }
            }
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
                ResponseStatusCode.DATA_NOT_FOUND,
                "Could not get the resource URL because the key \"$urlKeyName\" does not exist in the data: $data"
            )
    }

    private fun getAndRemoveResourceUrl(data: MutableMap<String, String>, urlKeyName: String): String {
        return data.remove(urlKeyName)
            ?: throw ResponseException.from(
                ResponseStatusCode.DATA_NOT_FOUND,
                "Could not get the resource URL because the key \"$urlKeyName\" does not exist in the data: $data"
            )
    }

    companion object {
        private const val RESOURCE_ID_KEY_NAME = "id"
        private const val DEFAULT_URL_KEY_NAME = "url"
        private val RESOURCE_TYPE_TO_BUCKET_NAME = EnumMap(
            StorageResourceType.values()
                .filter { it !== StorageResourceType.UNRECOGNIZED }
                .associateBy({ it }, { it.name.lowercase().replace('_', '-') })
        )
    }
}

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

import com.google.protobuf.ByteString
import im.turms.client.TurmsClient
import im.turms.client.exception.ResponseException
import im.turms.client.extension.getLongOrThrow
import im.turms.client.extension.toResponse
import im.turms.client.model.BuiltinSystemMessageType
import im.turms.client.model.MessageAddition
import im.turms.client.model.Response
import im.turms.client.model.ResponseStatusCode
import im.turms.client.model.proto.model.file.AudioFile
import im.turms.client.model.proto.model.file.File
import im.turms.client.model.proto.model.file.ImageFile
import im.turms.client.model.proto.model.file.VideoFile
import im.turms.client.model.proto.model.message.Message
import im.turms.client.model.proto.model.message.MessagesWithTotal
import im.turms.client.model.proto.model.user.UserLocation
import im.turms.client.model.proto.request.TurmsRequest
import im.turms.client.model.proto.request.message.CreateMessageRequest
import im.turms.client.model.proto.request.message.QueryMessagesRequest
import im.turms.client.model.proto.request.message.UpdateMessageRequest
import im.turms.client.util.Validator
import java.nio.ByteBuffer
import java.util.Date
import java.util.LinkedList
import java.util.regex.Pattern

/**
 * @author James Chen
 */
class MessageService(private val turmsClient: TurmsClient) {
    private var mentionedUserIdsParser: ((Message) -> Set<Long>)? = null
    private var messageListeners: MutableList<(Message, MessageAddition) -> Unit> = LinkedList()

    fun addMessageListener(listener: (Message, MessageAddition) -> Unit) = messageListeners.add(listener)

    fun removeMessageListener(listener: (Message, MessageAddition) -> Unit) = messageListeners.remove(listener)

    suspend fun sendMessage(
        isGroupMessage: Boolean,
        targetId: Long,
        deliveryDate: Date? = null,
        text: String? = null,
        records: List<ByteBuffer>? = null,
        burnAfter: Int? = null,
        preMessageId: Long? = null
    ): Response<Long> {
        if (text == null && records == null) {
            throw ResponseException.from(ResponseStatusCode.ILLEGAL_ARGUMENT, "\"text\" and \"records\" must not all be null")
        }
        return turmsClient.driver
            .send(
                CreateMessageRequest.newBuilder().apply {
                    if (isGroupMessage) {
                        this.groupId = targetId
                    } else {
                        this.recipientId = targetId
                    }
                    deliveryDate?.let { this.deliveryDate = it.time }
                    text?.let { this.text = it }
                    records?.let { this.addAllRecords(it.map { buffer -> ByteString.copyFrom(buffer) }) }
                    burnAfter?.let { this.burnAfter = it }
                    preMessageId?.let { this.preMessageId = it }
                }
            ).toResponse {
                it.getLongOrThrow()
            }
    }

    suspend fun forwardMessage(
        messageId: Long,
        isGroupMessage: Boolean,
        targetId: Long
    ): Response<Long> = turmsClient.driver
        .send(
            CreateMessageRequest.newBuilder().apply {
                this.messageId = messageId
                if (isGroupMessage) {
                    groupId = targetId
                } else {
                    recipientId = targetId
                }
            }
        ).toResponse {
            it.getLongOrThrow()
        }

    suspend fun updateSentMessage(
        messageId: Long,
        text: String? = null,
        records: List<ByteBuffer>? = null
    ): Response<Unit> = if (Validator.areAllFalsy(text, records)) {
        Response.unitValue()
    } else {
        turmsClient.driver
            .send(
                UpdateMessageRequest.newBuilder().apply {
                    this.messageId = messageId
                    text?.let { this.text = it }
                    records?.let { this.addAllRecords(it.map { buffer -> ByteString.copyFrom(buffer) }) }
                }
            )
            .toResponse()
    }

    suspend fun queryMessages(
        ids: Set<Long>? = null,
        areGroupMessages: Boolean? = null,
        areSystemMessages: Boolean? = null,
        fromIds: Set<Long>? = null,
        deliveryDateStart: Date? = null,
        deliveryDateEnd: Date? = null,
        maxCount: Int = 50,
        descending: Boolean? = null
    ): Response<List<Message>> = turmsClient.driver
        .send(
            QueryMessagesRequest.newBuilder().apply {
                ids?.let { this.addAllIds(it) }
                areGroupMessages?.let { this.areGroupMessages = it }
                areSystemMessages?.let { this.areSystemMessages = it }
                fromIds?.let { this.addAllFromIds(it) }
                deliveryDateStart?.let { this.deliveryDateStart = it.time }
                deliveryDateEnd?.let { this.deliveryDateEnd = it.time }
                this.maxCount = maxCount
                descending?.let { this.descending = it }
                withTotal = false
            }
        ).toResponse {
            it.messages.messagesList
        }

    suspend fun queryMessagesWithTotal(
        ids: Set<Long?>? = null,
        areGroupMessages: Boolean? = null,
        areSystemMessages: Boolean? = null,
        fromIds: Set<Long>? = null,
        deliveryDateStart: Date? = null,
        deliveryDateEnd: Date? = null,
        maxCount: Int = 1,
        descending: Boolean? = null
    ): Response<List<MessagesWithTotal>> = turmsClient.driver
        .send(
            QueryMessagesRequest.newBuilder().apply {
                ids?.let { this.addAllIds(it) }
                areGroupMessages?.let { this.areGroupMessages = it }
                areSystemMessages?.let { this.areSystemMessages = it }
                fromIds?.let { this.addAllFromIds(it) }
                deliveryDateStart?.let { this.deliveryDateStart = it.time }
                deliveryDateEnd?.let { this.deliveryDateEnd = it.time }
                this.maxCount = maxCount
                descending?.let { this.descending = it }
                withTotal = true
            }
        ).toResponse {
            it.messagesWithTotalList.messagesWithTotalListList
        }

    suspend fun recallMessage(messageId: Long, recallDate: Date = Date()): Response<Unit> = turmsClient.driver
        .send(
            UpdateMessageRequest.newBuilder().apply {
                this.messageId = messageId
                this.recallDate = recallDate.time
            }
        )
        .toResponse()

    val isMentionEnabled: Boolean
        get() = mentionedUserIdsParser != null

    fun enableMention() {
        if (mentionedUserIdsParser == null) {
            mentionedUserIdsParser = DEFAULT_MENTIONED_USER_IDS_PARSER
        }
    }

    fun enableMention(mentionedUserIdsParser: (Message) -> Set<Long>) {
        this.mentionedUserIdsParser = mentionedUserIdsParser
    }

    private fun parseMessageAddition(message: Message): MessageAddition {
        val mentionedUserIds: Set<Long> = mentionedUserIdsParser?.invoke(message) ?: emptySet()
        val isMentioned = mentionedUserIds.contains(turmsClient.userService.userInfo?.userId)
        val records = message.recordsList
        var systemMessageType: BuiltinSystemMessageType? = null
        if (message.isSystemMessage && records.isNotEmpty()) {
            val bytes = records[0]
            if (bytes.size() > 0) {
                systemMessageType = BuiltinSystemMessageType[bytes.byteAt(0).toInt()]
            }
        }
        val recalledMessageIds: Set<Long> = if (systemMessageType === BuiltinSystemMessageType.RECALL_MESSAGE) {
            mutableSetOf<Long>().apply {
                for (i in 1 until size) {
                    val id = message.getRecords(i).asReadOnlyByteBuffer().long
                    add(id)
                }
            }
        } else {
            emptySet()
        }
        return MessageAddition(isMentioned, mentionedUserIds, recalledMessageIds)
    }

    private fun createMessageRequest2Message(requesterId: Long, request: CreateMessageRequest): Message =
        Message.newBuilder()
            .run {
                if (request.hasMessageId()) {
                    id = request.messageId
                }
                isSystemMessage = request.isSystemMessage
                deliveryDate = request.deliveryDate
                if (request.hasText()) {
                    text = request.text
                }
                if (request.recordsCount > 0) {
                    addAllRecords(request.recordsList)
                }
                senderId = requesterId
                if (request.hasGroupId()) {
                    groupId = request.groupId
                }
                if (request.hasRecipientId()) {
                    recipientId = request.recipientId
                }
                return build()
            }

    companion object {
        /**
         * Format: "@{userId}"
         * Example: "@{123}", "I need to talk with @{123} and @{321}"
         * Note that some IDEs complain that "\\" before "}" is redundant,
         * but do NOT remove it otherwise Android will fail to compile it.
         */
        private val REGEX = Pattern.compile("@\\{(\\d+?)\\}")
        private val DEFAULT_MENTIONED_USER_IDS_PARSER: (Message) -> Set<Long> = {
            if (it.hasText()) {
                val text = it.text
                val matcher = REGEX.matcher(text)
                val userIds: MutableSet<Long> = LinkedHashSet()
                while (matcher.find()) {
                    val group = matcher.group(1)
                    try {
                        userIds.add(group.toLong())
                    } catch (e: NumberFormatException) {
                    }
                }
                userIds
            } else {
                emptySet()
            }
        }

        @JvmStatic
        fun generateLocationRecord(
            latitude: Float,
            longitude: Float,
            details: Map<String, String>? = null
        ): ByteBuffer = UserLocation.newBuilder().run {
            setLatitude(latitude)
            setLongitude(longitude)
            details?.let { putAllDetails(it) }
            build().toByteString().asReadOnlyByteBuffer()
        }

        @JvmStatic
        fun generateAudioRecordByDescription(
            url: String,
            duration: Int? = null,
            format: String? = null,
            size: Int? = null
        ): ByteBuffer = AudioFile.newBuilder().run {
            setDescription(
                AudioFile.Description.newBuilder().apply {
                    this.url = url
                    duration?.let { this.duration = it }
                    format?.let { this.format = it }
                    size?.let { this.size = it }
                }
            )
                .build()
                .toByteString()
                .asReadOnlyByteBuffer()
        }

        @JvmStatic
        fun generateAudioRecordByData(data: ByteArray): ByteBuffer = AudioFile.newBuilder().run {
            setData(ByteString.copyFrom(data))
                .build()
                .toByteString()
                .asReadOnlyByteBuffer()
        }

        @JvmStatic
        fun generateVideoRecordByDescription(
            url: String,
            duration: Int? = null,
            format: String? = null,
            size: Int? = null
        ): ByteBuffer = VideoFile.newBuilder().run {
            setDescription(
                VideoFile.Description.newBuilder().apply {
                    this.url = url
                    duration?.let { this.duration = it }
                    format?.let { this.format = it }
                    size?.let { this.size = it }
                }
            )
                .build()
                .toByteString()
                .asReadOnlyByteBuffer()
        }

        @JvmStatic
        fun generateVideoRecordByData(data: ByteArray): ByteBuffer = VideoFile.newBuilder()
            .setData(ByteString.copyFrom(data))
            .build()
            .toByteString()
            .asReadOnlyByteBuffer()

        @JvmStatic
        fun generateImageRecordByData(data: ByteArray): ByteBuffer = ImageFile.newBuilder()
            .setData(ByteString.copyFrom(data))
            .build()
            .toByteString()
            .asReadOnlyByteBuffer()

        @JvmStatic
        fun generateImageRecordByDescription(
            url: String,
            fileSize: Int? = null,
            imageSize: Int? = null,
            original: Boolean? = null
        ): ByteBuffer = ImageFile.newBuilder()
            .setDescription(
                ImageFile.Description.newBuilder().apply {
                    setUrl(url)
                    fileSize?.let { this.fileSize = it }
                    imageSize?.let { this.imageSize = it }
                    original?.let { this.original = it }
                }
            )
            .build()
            .toByteString()
            .asReadOnlyByteBuffer()

        @JvmStatic
        fun generateFileRecordByDate(data: ByteArray): ByteBuffer = File.newBuilder()
            .setData(ByteString.copyFrom(data))
            .build()
            .toByteString()
            .asReadOnlyByteBuffer()

        @JvmStatic
        fun generateFileRecordByDescription(
            url: String,
            format: String? = null,
            size: Int? = null
        ): ByteBuffer = File.newBuilder()
            .setDescription(
                File.Description.newBuilder().apply {
                    setUrl(url)
                    format?.let { this.format = it }
                    size?.let { this.size = it }
                }
            )
            .build()
            .toByteString()
            .asReadOnlyByteBuffer()
    }

    init {
        this.turmsClient.driver
            .addNotificationListener { notification ->
                if (messageListeners.isEmpty() || !notification.hasRelayedRequest()) {
                    return@addNotificationListener
                }
                val relayedRequest: TurmsRequest = notification.relayedRequest
                if (!relayedRequest.hasCreateMessageRequest()) {
                    return@addNotificationListener
                }
                val createMessageRequest: CreateMessageRequest = relayedRequest.createMessageRequest
                val requesterId: Long = notification.requesterId
                val message = createMessageRequest2Message(requesterId, createMessageRequest)
                val addition: MessageAddition = parseMessageAddition(message)
                messageListeners.forEach { listener -> listener(message, addition) }
            }
    }
}

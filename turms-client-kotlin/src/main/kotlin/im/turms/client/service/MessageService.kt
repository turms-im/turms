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

    /**
     * Add a message listener that will be triggered when a new message arrives.
     * Note: To listen to notifications excluding messages,
     * use [NotificationService.addNotificationListener] instead.
     */
    fun addMessageListener(listener: (Message, MessageAddition) -> Unit) = messageListeners.add(listener)

    /**
     * Remove a message listener.
     */
    fun removeMessageListener(listener: (Message, MessageAddition) -> Unit) = messageListeners.remove(listener)

    /**
     * Send a message.
     *
     * Common Scenarios:
     * * A client can call [addMessageListener] to listen to incoming new messages.
     *
     * Authorization:
     *
     * For private messages,
     * * If the server property `turms.service.message.allow-send-messages-to-oneself`
     *   is true (false by default), the logged-in user can send messages to itself.
     *   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.SENDING_MESSAGES_TO_ONESELF_IS_DISABLED].
     * * If the server property `turms.service.message.allow-send-messages-to-stranger`
     *   is true (true by default), the logged-in user can send messages to strangers
     *   (has no relationship with the logged-in user).
     * * If the logged-in user has blocked the target user,
     *   throws [ResponseException] with the code [ResponseStatusCode.BLOCKED_USER_SEND_PRIVATE_MESSAGE].
     *
     * For group messages,
     * * If the logged-in user has blocked the target group,
     *   throws [ResponseException] with the code [ResponseStatusCode.BLOCKED_USER_SEND_GROUP_MESSAGE].
     * * If the logged-in user is not a group member, and the group does NOT allow non-members to send messages,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_SPEAKABLE_GROUP_GUEST_TO_SEND_MESSAGE].
     * * If the logged-in user has been muted,
     *   throws [ResponseException] with the code [ResponseStatusCode.MUTED_GROUP_MEMBER_SEND_MESSAGE].
     * * If the target group has been deleted,
     *   throws [ResponseException] with the code [ResponseStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP].
     * * If the target group has been muted,
     *   throws [ResponseException] with the code [ResponseStatusCode.SEND_MESSAGE_TO_MUTED_GROUP].
     *
     * Notifications:
     * * If the server property `turms.service.notification.message-created.notify-message-recipients`
     *   is true (true by default), a new message notification will be sent to the message recipients actively.
     * * If the server property `turms.service.notification.message-created.notify-requester-other-online-sessions`
     *   is true (true by default), a new message notification will be sent to all other online sessions of the logged-in user actively.
     *
     * @param isGroupMessage whether the message is a group message.
     * @param targetId The target ID.
     * If [isGroupMessage] is true, the target ID is the group ID.
     * If [isGroupMessage] is false, the target ID is the user ID.
     * @param deliveryDate The delivery date.
     * Note that [deliveryDate] will only work if the server property
     * `turms.service.message.time-type` is `client_time` (`local_server_time` by default).
     * @param text the message text.
     * [text] can be anything you want. e.g. Markdown, base64 encoded bytes.
     * Note that if [text] is null, [records] must not be null.
     * @param records the message records.
     * [records] can be anything you want. e.g. base64 encoded images (it is highly not recommended).
     * Note that if [records] is null, [text] must not be null.
     * @param burnAfter The burn after the specified time.
     * Note that Turms server and client do NOT implement the `burn after` feature,
     * and they just store and pass [burnAfter] between server and clients.
     * @param preMessageId The pre-message ID.
     * [preMessageId] is mainly used to arrange the order of messages on UI.
     * If what you want is to ensure every message will not be lost, even if the server crashes,
     * you can set the server property `turms.service.message.use-conversation-id` to true
     * (false by default).
     * @return the message ID.
     * @throws ResponseException if an error occurs.
     */
    suspend fun sendMessage(
        isGroupMessage: Boolean,
        targetId: Long,
        deliveryDate: Date? = null,
        text: String? = null,
        records: List<ByteBuffer>? = null,
        burnAfter: Int? = null,
        preMessageId: Long? = null,
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
                },
            ).toResponse {
                it.getLongOrThrow()
            }
    }

    /**
     * Forward a message.
     * In other words, create and send a new message based on a existing message
     * to the target user or group.
     *
     * Authorization:
     * * If the logged-in user is not allowed to view the message with [messageId],
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_MESSAGE_RECIPIENT_OR_SENDER_TO_FORWARD_MESSAGE].
     *
     * Notifications:
     * * If the server property `turms.service.notification.message-created.notify-message-recipients`
     *   is true (true by default), a new message notification will be sent to the message recipients actively.
     * * If the server property `turms.service.notification.message-created.notify-requester-other-online-sessions`
     *   is true (true by default), a new message notification will be sent to all other online sessions of the logged-in user actively.
     *
     * @param messageId the message ID for copying.
     * @param isGroupMessage whether the message is a group message.
     * @param targetId the target ID.
     * If [isGroupMessage] is true, the target ID is the group ID.
     * If [isGroupMessage] is false, the target ID is the user ID.
     * @return the message ID.
     * @throws ResponseException if an error occurs.
     */
    suspend fun forwardMessage(
        messageId: Long,
        isGroupMessage: Boolean,
        targetId: Long,
    ): Response<Long> = turmsClient.driver
        .send(
            CreateMessageRequest.newBuilder().apply {
                this.messageId = messageId
                if (isGroupMessage) {
                    groupId = targetId
                } else {
                    recipientId = targetId
                }
            },
        ).toResponse {
            it.getLongOrThrow()
        }

    /**
     * Update a sent message.
     *
     * Authorization:
     * * If the server property `turms.service.message.allow-send-messages-to-oneself`
     *   is true (true by default), the logged-in user can update sent messages.
     *   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.UPDATING_MESSAGE_BY_SENDER_IS_DISABLED].
     * * If the message is not sent by the logged-in user,
     *   throws [ResponseException] with the code [ResponseStatusCode.NOT_SENDER_TO_UPDATE_MESSAGE].
     * * If the message is group message, and is sent by the logged-in user but the group
     *   has been deleted,
     *   throws [ResponseException] with the code [ResponseStatusCode.UPDATE_MESSAGE_OF_NONEXISTENT_GROUP].
     * * If the message is group message, and the group type has disabled updating messages,
     *   throws [ResponseException] with the code [ResponseStatusCode.UPDATING_GROUP_MESSAGE_BY_SENDER_IS_DISABLED].
     *
     * Notifications:
     * * If the server property `turms.service.notification.message-updated.notify-message-recipients`
     *   is true (true by default), a message update notification will be sent to the message recipients actively.
     * * If the server property `turms.service.notification.message-updated.notify-requester-other-online-sessions`
     *   is true (true by default), a message update notification will be sent to all other online sessions of the logged-in user actively.
     *
     * @param messageId The sent message ID.
     * @param text The new message text.
     * If null, the message text will not be changed.
     * @param records The new message records.
     * If null, the message records will not be changed.
     * @throws ResponseException if an error occurs.
     */
    suspend fun updateSentMessage(
        messageId: Long,
        text: String? = null,
        records: List<ByteBuffer>? = null,
    ): Response<Unit> = if (Validator.areAllFalsy(text, records)) {
        Response.unitValue()
    } else {
        turmsClient.driver
            .send(
                UpdateMessageRequest.newBuilder().apply {
                    this.messageId = messageId
                    text?.let { this.text = it }
                    records?.let { this.addAllRecords(it.map { buffer -> ByteString.copyFrom(buffer) }) }
                },
            )
            .toResponse()
    }

    /**
     * Find messages.
     *
     * @param ids the message IDs for querying.
     * @param areGroupMessages whether the messages are group messages.
     * If the logged-in user is not a group member,
     * throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_MEMBER_TO_QUERY_GROUP_MESSAGES].
     * TODO: guest users of some group types should be able to query messages.
     * @param areSystemMessages whether the messages are system messages.
     * @param fromIds the from IDs.
     * If [areGroupMessages] is true, the from ID is the group ID.
     * If [areGroupMessages] is false, the from ID is the user ID.
     * @param deliveryDateStart the start delivery date for querying.
     * @param deliveryDateEnd the end delivery date for querying.
     * @param maxCount the maximum count for querying.
     * @param descending whether the messages are sorted in descending order.
     * @return list of messages.
     * Note that the list only contains messages in which the logged-in user
     * has permission to view.
     * If the logged-in user has no permission to view specified messages,
     * these messages will be filtered out on the server, and no error will be thrown,
     * except for [ResponseStatusCode.NOT_GROUP_MEMBER_TO_QUERY_GROUP_MESSAGES] mentioned above.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryMessages(
        ids: Set<Long>? = null,
        areGroupMessages: Boolean? = null,
        areSystemMessages: Boolean? = null,
        fromIds: Set<Long>? = null,
        deliveryDateStart: Date? = null,
        deliveryDateEnd: Date? = null,
        maxCount: Int = 50,
        descending: Boolean? = null,
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
            },
        ).toResponse {
            it.messages.messagesList
        }

    /**
     * Find the pair of messages and the total count for each conversation.
     *
     * @param ids the message IDs for querying.
     * @param areGroupMessages whether the messages are group messages.
     * @param areSystemMessages whether the messages are system messages.
     * If the logged-in user is not a group member,
     * throws [ResponseException] with the code [ResponseStatusCode.NOT_GROUP_MEMBER_TO_QUERY_GROUP_MESSAGES].
     * TODO: guest users of some group types should be able to query messages.
     * @param fromIds The from IDs.
     * If [areGroupMessages] is true, the from ID is the group ID.
     * If [areGroupMessages] is false, the from ID is the user ID.
     * @param deliveryDateStart The start delivery date for querying.
     * @param deliveryDateEnd The end delivery date for querying.
     * @param maxCount The maximum count for querying.
     * @param descending Whether the messages are sorted in descending order.
     * @return list of the pair of messages and the total count for each conversation.
     * Note that the list only contains messages in which the logged-in user
     * has permission to view.
     * If the logged-in user has no permission to view specified messages,
     * these messages will be filtered out on the server, and no error will be thrown,
     * except for [ResponseStatusCode.NOT_GROUP_MEMBER_TO_QUERY_GROUP_MESSAGES] mentioned above.
     * @throws ResponseException if an error occurs.
     */
    suspend fun queryMessagesWithTotal(
        ids: Set<Long?>? = null,
        areGroupMessages: Boolean? = null,
        areSystemMessages: Boolean? = null,
        fromIds: Set<Long>? = null,
        deliveryDateStart: Date? = null,
        deliveryDateEnd: Date? = null,
        maxCount: Int = 1,
        descending: Boolean? = null,
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
            },
        ).toResponse {
            it.messagesWithTotalList.messagesWithTotalListList
        }

    /**
     * Recall a message.
     *
     * Authorization:
     * * If the server property `turms.service.message.allow-recall-message`
     *   is true (true by default), the logged-in user can recall sent messages.
     *   Otherwise, throws [ResponseException] with the code [ResponseStatusCode.RECALLING_MESSAGE_IS_DISABLED].
     * * If the message does not exist,
     *   throws [ResponseException] with the code [ResponseStatusCode.RECALL_NONEXISTENT_MESSAGE].
     * * If the message is group message, but the group has been deleted,
     *   throws [ResponseException] with the code [ResponseStatusCode.RECALL_MESSAGE_OF_NONEXISTENT_GROUP].
     *
     * Common Scenarios:
     * * A client can call [addMessageListener] to listen to recalled messages.
     *   The listener will receive a non-empty [MessageAddition.recalledMessageIds] when a message is recalled.
     *
     * @param messageId the message ID.
     * @param recallDate the recall date.
     * If null, the current date will be used.
     * @throws ResponseException if an error occurs.
     */
    suspend fun recallMessage(messageId: Long, recallDate: Date = Date()): Response<Unit> = turmsClient.driver
        .send(
            UpdateMessageRequest.newBuilder().apply {
                this.messageId = messageId
                this.recallDate = recallDate.time
            },
        )
        .toResponse()

    /**
     * Check if the mention feature is enabled.
     */
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
         * Format: `@{userId}`
         * Example: `@{123}`, `I need to talk with @{123} and @{321}`
         * Note that some IDEs complain that `\\` before `}` is redundant,
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
            details: Map<String, String>? = null,
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
            size: Int? = null,
        ): ByteBuffer = AudioFile.newBuilder().run {
            setDescription(
                AudioFile.Description.newBuilder().apply {
                    this.url = url
                    duration?.let { this.duration = it }
                    format?.let { this.format = it }
                    size?.let { this.size = it }
                },
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
            size: Int? = null,
        ): ByteBuffer = VideoFile.newBuilder().run {
            setDescription(
                VideoFile.Description.newBuilder().apply {
                    this.url = url
                    duration?.let { this.duration = it }
                    format?.let { this.format = it }
                    size?.let { this.size = it }
                },
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
            original: Boolean? = null,
        ): ByteBuffer = ImageFile.newBuilder()
            .setDescription(
                ImageFile.Description.newBuilder().apply {
                    setUrl(url)
                    fileSize?.let { this.fileSize = it }
                    imageSize?.let { this.imageSize = it }
                    original?.let { this.original = it }
                },
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
            size: Int? = null,
        ): ByteBuffer = File.newBuilder()
            .setDescription(
                File.Description.newBuilder().apply {
                    setUrl(url)
                    format?.let { this.format = it }
                    size?.let { this.size = it }
                },
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

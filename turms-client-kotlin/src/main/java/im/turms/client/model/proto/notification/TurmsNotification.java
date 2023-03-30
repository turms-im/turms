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

package im.turms.client.model.proto.notification;

/**
 * Protobuf type {@code im.turms.proto.TurmsNotification}
 */
public final class TurmsNotification extends
        com.google.protobuf.GeneratedMessageLite<TurmsNotification, TurmsNotification.Builder>
        implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.TurmsNotification)
        TurmsNotificationOrBuilder {
    private TurmsNotification() {
        reason_ = "";
    }

    public interface DataOrBuilder extends
            // @@protoc_insertion_point(interface_extends:im.turms.proto.TurmsNotification.Data)
            com.google.protobuf.MessageLiteOrBuilder {

        /**
         * <pre>
         * Common
         * </pre>
         * 
         * <code>int64 long = 1;</code>
         *
         * @return Whether the long field is set.
         */
        boolean hasLong();

        /**
         * <pre>
         * Common
         * </pre>
         * 
         * <code>int64 long = 1;</code>
         *
         * @return The long.
         */
        long getLong();

        /**
         * <code>string string = 2;</code>
         *
         * @return Whether the string field is set.
         */
        boolean hasString();

        /**
         * <code>string string = 2;</code>
         *
         * @return The string.
         */
        java.lang.String getString();

        /**
         * <code>string string = 2;</code>
         *
         * @return The bytes for string.
         */
        com.google.protobuf.ByteString getStringBytes();

        /**
         * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
         *
         * @return Whether the longsWithVersion field is set.
         */
        boolean hasLongsWithVersion();

        /**
         * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
         *
         * @return The longsWithVersion.
         */
        im.turms.client.model.proto.model.common.LongsWithVersion getLongsWithVersion();

        /**
         * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
         *
         * @return Whether the stringsWithVersion field is set.
         */
        boolean hasStringsWithVersion();

        /**
         * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
         *
         * @return The stringsWithVersion.
         */
        im.turms.client.model.proto.model.common.StringsWithVersion getStringsWithVersion();

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.Conversations conversations = 5;</code>
         *
         * @return Whether the conversations field is set.
         */
        boolean hasConversations();

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.Conversations conversations = 5;</code>
         *
         * @return The conversations.
         */
        im.turms.client.model.proto.model.conversation.Conversations getConversations();

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.Messages messages = 6;</code>
         *
         * @return Whether the messages field is set.
         */
        boolean hasMessages();

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.Messages messages = 6;</code>
         *
         * @return The messages.
         */
        im.turms.client.model.proto.model.message.Messages getMessages();

        /**
         * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
         *
         * @return Whether the messagesWithTotalList field is set.
         */
        boolean hasMessagesWithTotalList();

        /**
         * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
         *
         * @return The messagesWithTotalList.
         */
        im.turms.client.model.proto.model.message.MessagesWithTotalList getMessagesWithTotalList();

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.UserSession user_session = 8;</code>
         *
         * @return Whether the userSession field is set.
         */
        boolean hasUserSession();

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.UserSession user_session = 8;</code>
         *
         * @return The userSession.
         */
        im.turms.client.model.proto.model.user.UserSession getUserSession();

        /**
         * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
         *
         * @return Whether the userInfosWithVersion field is set.
         */
        boolean hasUserInfosWithVersion();

        /**
         * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
         *
         * @return The userInfosWithVersion.
         */
        im.turms.client.model.proto.model.user.UserInfosWithVersion getUserInfosWithVersion();

        /**
         * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
         *
         * @return Whether the userOnlineStatuses field is set.
         */
        boolean hasUserOnlineStatuses();

        /**
         * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
         *
         * @return The userOnlineStatuses.
         */
        im.turms.client.model.proto.model.user.UserOnlineStatuses getUserOnlineStatuses();

        /**
         * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
         *
         * @return Whether the userFriendRequestsWithVersion field is set.
         */
        boolean hasUserFriendRequestsWithVersion();

        /**
         * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
         *
         * @return The userFriendRequestsWithVersion.
         */
        im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion getUserFriendRequestsWithVersion();

        /**
         * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
         *
         * @return Whether the userRelationshipGroupsWithVersion field is set.
         */
        boolean hasUserRelationshipGroupsWithVersion();

        /**
         * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
         *
         * @return The userRelationshipGroupsWithVersion.
         */
        im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion getUserRelationshipGroupsWithVersion();

        /**
         * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
         *
         * @return Whether the userRelationshipsWithVersion field is set.
         */
        boolean hasUserRelationshipsWithVersion();

        /**
         * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
         *
         * @return The userRelationshipsWithVersion.
         */
        im.turms.client.model.proto.model.user.UserRelationshipsWithVersion getUserRelationshipsWithVersion();

        /**
         * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
         *
         * @return Whether the nearbyUsers field is set.
         */
        boolean hasNearbyUsers();

        /**
         * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
         *
         * @return The nearbyUsers.
         */
        im.turms.client.model.proto.model.user.NearbyUsers getNearbyUsers();

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
         *
         * @return Whether the groupInvitationsWithVersion field is set.
         */
        boolean hasGroupInvitationsWithVersion();

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
         *
         * @return The groupInvitationsWithVersion.
         */
        im.turms.client.model.proto.model.group.GroupInvitationsWithVersion getGroupInvitationsWithVersion();

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
         *
         * @return Whether the groupJoinQuestionAnswerResult field is set.
         */
        boolean hasGroupJoinQuestionAnswerResult();

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
         *
         * @return The groupJoinQuestionAnswerResult.
         */
        im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult getGroupJoinQuestionAnswerResult();

        /**
         * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
         *
         * @return Whether the groupJoinRequestsWithVersion field is set.
         */
        boolean hasGroupJoinRequestsWithVersion();

        /**
         * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
         *
         * @return The groupJoinRequestsWithVersion.
         */
        im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion getGroupJoinRequestsWithVersion();

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
         *
         * @return Whether the groupJoinQuestionsWithVersion field is set.
         */
        boolean hasGroupJoinQuestionsWithVersion();

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
         *
         * @return The groupJoinQuestionsWithVersion.
         */
        im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion getGroupJoinQuestionsWithVersion();

        /**
         * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
         *
         * @return Whether the groupMembersWithVersion field is set.
         */
        boolean hasGroupMembersWithVersion();

        /**
         * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
         *
         * @return The groupMembersWithVersion.
         */
        im.turms.client.model.proto.model.group.GroupMembersWithVersion getGroupMembersWithVersion();

        /**
         * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
         *
         * @return Whether the groupsWithVersion field is set.
         */
        boolean hasGroupsWithVersion();

        /**
         * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
         *
         * @return The groupsWithVersion.
         */
        im.turms.client.model.proto.model.group.GroupsWithVersion getGroupsWithVersion();

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
         *
         * @return Whether the storageResourceInfos field is set.
         */
        boolean hasStorageResourceInfos();

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
         *
         * @return The storageResourceInfos.
         */
        im.turms.client.model.proto.model.storage.StorageResourceInfos getStorageResourceInfos();

        im.turms.client.model.proto.notification.TurmsNotification.Data.KindCase getKindCase();
    }

    /**
     * Protobuf type {@code im.turms.proto.TurmsNotification.Data}
     */
    public static final class Data
            extends com.google.protobuf.GeneratedMessageLite<Data, Data.Builder> implements
            // @@protoc_insertion_point(message_implements:im.turms.proto.TurmsNotification.Data)
            DataOrBuilder {
        private Data() {
        }

        private int kindCase_ = 0;
        private java.lang.Object kind_;

        public enum KindCase {
            LONG(1),
            STRING(2),
            LONGS_WITH_VERSION(3),
            STRINGS_WITH_VERSION(4),
            CONVERSATIONS(5),
            MESSAGES(6),
            MESSAGES_WITH_TOTAL_LIST(7),
            USER_SESSION(8),
            USER_INFOS_WITH_VERSION(9),
            USER_ONLINE_STATUSES(10),
            USER_FRIEND_REQUESTS_WITH_VERSION(11),
            USER_RELATIONSHIP_GROUPS_WITH_VERSION(12),
            USER_RELATIONSHIPS_WITH_VERSION(13),
            NEARBY_USERS(14),
            GROUP_INVITATIONS_WITH_VERSION(15),
            GROUP_JOIN_QUESTION_ANSWER_RESULT(16),
            GROUP_JOIN_REQUESTS_WITH_VERSION(17),
            GROUP_JOIN_QUESTIONS_WITH_VERSION(18),
            GROUP_MEMBERS_WITH_VERSION(19),
            GROUPS_WITH_VERSION(20),
            STORAGE_RESOURCE_INFOS(50),
            KIND_NOT_SET(0);

            private final int value;

            KindCase(int value) {
                this.value = value;
            }

            /**
             * @deprecated Use {@link #forNumber(int)} instead.
             */
            @java.lang.Deprecated
            public static KindCase valueOf(int value) {
                return forNumber(value);
            }

            public static KindCase forNumber(int value) {
                switch (value) {
                    case 1:
                        return LONG;
                    case 2:
                        return STRING;
                    case 3:
                        return LONGS_WITH_VERSION;
                    case 4:
                        return STRINGS_WITH_VERSION;
                    case 5:
                        return CONVERSATIONS;
                    case 6:
                        return MESSAGES;
                    case 7:
                        return MESSAGES_WITH_TOTAL_LIST;
                    case 8:
                        return USER_SESSION;
                    case 9:
                        return USER_INFOS_WITH_VERSION;
                    case 10:
                        return USER_ONLINE_STATUSES;
                    case 11:
                        return USER_FRIEND_REQUESTS_WITH_VERSION;
                    case 12:
                        return USER_RELATIONSHIP_GROUPS_WITH_VERSION;
                    case 13:
                        return USER_RELATIONSHIPS_WITH_VERSION;
                    case 14:
                        return NEARBY_USERS;
                    case 15:
                        return GROUP_INVITATIONS_WITH_VERSION;
                    case 16:
                        return GROUP_JOIN_QUESTION_ANSWER_RESULT;
                    case 17:
                        return GROUP_JOIN_REQUESTS_WITH_VERSION;
                    case 18:
                        return GROUP_JOIN_QUESTIONS_WITH_VERSION;
                    case 19:
                        return GROUP_MEMBERS_WITH_VERSION;
                    case 20:
                        return GROUPS_WITH_VERSION;
                    case 50:
                        return STORAGE_RESOURCE_INFOS;
                    case 0:
                        return KIND_NOT_SET;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }
        }

        @java.lang.Override
        public KindCase getKindCase() {
            return KindCase.forNumber(kindCase_);
        }

        private void clearKind() {
            kindCase_ = 0;
            kind_ = null;
        }

        public static final int LONG_FIELD_NUMBER = 1;

        /**
         * <pre>
         * Common
         * </pre>
         * 
         * <code>int64 long = 1;</code>
         *
         * @return Whether the long field is set.
         */
        @java.lang.Override
        public boolean hasLong() {
            return kindCase_ == 1;
        }

        /**
         * <pre>
         * Common
         * </pre>
         * 
         * <code>int64 long = 1;</code>
         *
         * @return The long.
         */
        @java.lang.Override
        public long getLong() {
            if (kindCase_ == 1) {
                return (java.lang.Long) kind_;
            }
            return 0L;
        }

        /**
         * <pre>
         * Common
         * </pre>
         * 
         * <code>int64 long = 1;</code>
         *
         * @param value The long to set.
         */
        private void setLong(long value) {
            kindCase_ = 1;
            kind_ = value;
        }

        /**
         * <pre>
         * Common
         * </pre>
         * 
         * <code>int64 long = 1;</code>
         */
        private void clearLong() {
            if (kindCase_ == 1) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int STRING_FIELD_NUMBER = 2;

        /**
         * <code>string string = 2;</code>
         *
         * @return Whether the string field is set.
         */
        @java.lang.Override
        public boolean hasString() {
            return kindCase_ == 2;
        }

        /**
         * <code>string string = 2;</code>
         *
         * @return The string.
         */
        @java.lang.Override
        public java.lang.String getString() {
            java.lang.String ref = "";
            if (kindCase_ == 2) {
                ref = (java.lang.String) kind_;
            }
            return ref;
        }

        /**
         * <code>string string = 2;</code>
         *
         * @return The bytes for string.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getStringBytes() {
            java.lang.String ref = "";
            if (kindCase_ == 2) {
                ref = (java.lang.String) kind_;
            }
            return com.google.protobuf.ByteString.copyFromUtf8(ref);
        }

        /**
         * <code>string string = 2;</code>
         *
         * @param value The string to set.
         */
        private void setString(java.lang.String value) {
            java.lang.Class<?> valueClass = value.getClass();
            kindCase_ = 2;
            kind_ = value;
        }

        /**
         * <code>string string = 2;</code>
         */
        private void clearString() {
            if (kindCase_ == 2) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        /**
         * <code>string string = 2;</code>
         *
         * @param value The bytes for string to set.
         */
        private void setStringBytes(com.google.protobuf.ByteString value) {
            checkByteStringIsUtf8(value);
            kind_ = value.toStringUtf8();
            kindCase_ = 2;
        }

        public static final int LONGS_WITH_VERSION_FIELD_NUMBER = 3;

        /**
         * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
         */
        @java.lang.Override
        public boolean hasLongsWithVersion() {
            return kindCase_ == 3;
        }

        /**
         * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.common.LongsWithVersion getLongsWithVersion() {
            if (kindCase_ == 3) {
                return (im.turms.client.model.proto.model.common.LongsWithVersion) kind_;
            }
            return im.turms.client.model.proto.model.common.LongsWithVersion.getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
         */
        private void setLongsWithVersion(
                im.turms.client.model.proto.model.common.LongsWithVersion value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 3;
        }

        /**
         * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
         */
        private void mergeLongsWithVersion(
                im.turms.client.model.proto.model.common.LongsWithVersion value) {
            value.getClass();
            if (kindCase_ == 3
                    && kind_ != im.turms.client.model.proto.model.common.LongsWithVersion
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.common.LongsWithVersion
                        .newBuilder(
                                (im.turms.client.model.proto.model.common.LongsWithVersion) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 3;
        }

        /**
         * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
         */
        private void clearLongsWithVersion() {
            if (kindCase_ == 3) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int STRINGS_WITH_VERSION_FIELD_NUMBER = 4;

        /**
         * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
         */
        @java.lang.Override
        public boolean hasStringsWithVersion() {
            return kindCase_ == 4;
        }

        /**
         * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.common.StringsWithVersion getStringsWithVersion() {
            if (kindCase_ == 4) {
                return (im.turms.client.model.proto.model.common.StringsWithVersion) kind_;
            }
            return im.turms.client.model.proto.model.common.StringsWithVersion.getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
         */
        private void setStringsWithVersion(
                im.turms.client.model.proto.model.common.StringsWithVersion value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 4;
        }

        /**
         * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
         */
        private void mergeStringsWithVersion(
                im.turms.client.model.proto.model.common.StringsWithVersion value) {
            value.getClass();
            if (kindCase_ == 4
                    && kind_ != im.turms.client.model.proto.model.common.StringsWithVersion
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.common.StringsWithVersion
                        .newBuilder(
                                (im.turms.client.model.proto.model.common.StringsWithVersion) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 4;
        }

        /**
         * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
         */
        private void clearStringsWithVersion() {
            if (kindCase_ == 4) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int CONVERSATIONS_FIELD_NUMBER = 5;

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.Conversations conversations = 5;</code>
         */
        @java.lang.Override
        public boolean hasConversations() {
            return kindCase_ == 5;
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.Conversations conversations = 5;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.conversation.Conversations getConversations() {
            if (kindCase_ == 5) {
                return (im.turms.client.model.proto.model.conversation.Conversations) kind_;
            }
            return im.turms.client.model.proto.model.conversation.Conversations
                    .getDefaultInstance();
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.Conversations conversations = 5;</code>
         */
        private void setConversations(
                im.turms.client.model.proto.model.conversation.Conversations value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 5;
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.Conversations conversations = 5;</code>
         */
        private void mergeConversations(
                im.turms.client.model.proto.model.conversation.Conversations value) {
            value.getClass();
            if (kindCase_ == 5
                    && kind_ != im.turms.client.model.proto.model.conversation.Conversations
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.conversation.Conversations.newBuilder(
                        (im.turms.client.model.proto.model.conversation.Conversations) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 5;
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.Conversations conversations = 5;</code>
         */
        private void clearConversations() {
            if (kindCase_ == 5) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int MESSAGES_FIELD_NUMBER = 6;

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.Messages messages = 6;</code>
         */
        @java.lang.Override
        public boolean hasMessages() {
            return kindCase_ == 6;
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.Messages messages = 6;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.message.Messages getMessages() {
            if (kindCase_ == 6) {
                return (im.turms.client.model.proto.model.message.Messages) kind_;
            }
            return im.turms.client.model.proto.model.message.Messages.getDefaultInstance();
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.Messages messages = 6;</code>
         */
        private void setMessages(im.turms.client.model.proto.model.message.Messages value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 6;
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.Messages messages = 6;</code>
         */
        private void mergeMessages(im.turms.client.model.proto.model.message.Messages value) {
            value.getClass();
            if (kindCase_ == 6
                    && kind_ != im.turms.client.model.proto.model.message.Messages
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.message.Messages
                        .newBuilder((im.turms.client.model.proto.model.message.Messages) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 6;
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.Messages messages = 6;</code>
         */
        private void clearMessages() {
            if (kindCase_ == 6) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int MESSAGES_WITH_TOTAL_LIST_FIELD_NUMBER = 7;

        /**
         * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
         */
        @java.lang.Override
        public boolean hasMessagesWithTotalList() {
            return kindCase_ == 7;
        }

        /**
         * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.message.MessagesWithTotalList getMessagesWithTotalList() {
            if (kindCase_ == 7) {
                return (im.turms.client.model.proto.model.message.MessagesWithTotalList) kind_;
            }
            return im.turms.client.model.proto.model.message.MessagesWithTotalList
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
         */
        private void setMessagesWithTotalList(
                im.turms.client.model.proto.model.message.MessagesWithTotalList value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 7;
        }

        /**
         * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
         */
        private void mergeMessagesWithTotalList(
                im.turms.client.model.proto.model.message.MessagesWithTotalList value) {
            value.getClass();
            if (kindCase_ == 7
                    && kind_ != im.turms.client.model.proto.model.message.MessagesWithTotalList
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.message.MessagesWithTotalList.newBuilder(
                        (im.turms.client.model.proto.model.message.MessagesWithTotalList) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 7;
        }

        /**
         * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
         */
        private void clearMessagesWithTotalList() {
            if (kindCase_ == 7) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int USER_SESSION_FIELD_NUMBER = 8;

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.UserSession user_session = 8;</code>
         */
        @java.lang.Override
        public boolean hasUserSession() {
            return kindCase_ == 8;
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.UserSession user_session = 8;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.user.UserSession getUserSession() {
            if (kindCase_ == 8) {
                return (im.turms.client.model.proto.model.user.UserSession) kind_;
            }
            return im.turms.client.model.proto.model.user.UserSession.getDefaultInstance();
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.UserSession user_session = 8;</code>
         */
        private void setUserSession(im.turms.client.model.proto.model.user.UserSession value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 8;
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.UserSession user_session = 8;</code>
         */
        private void mergeUserSession(im.turms.client.model.proto.model.user.UserSession value) {
            value.getClass();
            if (kindCase_ == 8
                    && kind_ != im.turms.client.model.proto.model.user.UserSession
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.user.UserSession
                        .newBuilder((im.turms.client.model.proto.model.user.UserSession) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 8;
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.UserSession user_session = 8;</code>
         */
        private void clearUserSession() {
            if (kindCase_ == 8) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int USER_INFOS_WITH_VERSION_FIELD_NUMBER = 9;

        /**
         * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
         */
        @java.lang.Override
        public boolean hasUserInfosWithVersion() {
            return kindCase_ == 9;
        }

        /**
         * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.user.UserInfosWithVersion getUserInfosWithVersion() {
            if (kindCase_ == 9) {
                return (im.turms.client.model.proto.model.user.UserInfosWithVersion) kind_;
            }
            return im.turms.client.model.proto.model.user.UserInfosWithVersion.getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
         */
        private void setUserInfosWithVersion(
                im.turms.client.model.proto.model.user.UserInfosWithVersion value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 9;
        }

        /**
         * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
         */
        private void mergeUserInfosWithVersion(
                im.turms.client.model.proto.model.user.UserInfosWithVersion value) {
            value.getClass();
            if (kindCase_ == 9
                    && kind_ != im.turms.client.model.proto.model.user.UserInfosWithVersion
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.user.UserInfosWithVersion
                        .newBuilder(
                                (im.turms.client.model.proto.model.user.UserInfosWithVersion) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 9;
        }

        /**
         * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
         */
        private void clearUserInfosWithVersion() {
            if (kindCase_ == 9) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int USER_ONLINE_STATUSES_FIELD_NUMBER = 10;

        /**
         * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
         */
        @java.lang.Override
        public boolean hasUserOnlineStatuses() {
            return kindCase_ == 10;
        }

        /**
         * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.user.UserOnlineStatuses getUserOnlineStatuses() {
            if (kindCase_ == 10) {
                return (im.turms.client.model.proto.model.user.UserOnlineStatuses) kind_;
            }
            return im.turms.client.model.proto.model.user.UserOnlineStatuses.getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
         */
        private void setUserOnlineStatuses(
                im.turms.client.model.proto.model.user.UserOnlineStatuses value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 10;
        }

        /**
         * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
         */
        private void mergeUserOnlineStatuses(
                im.turms.client.model.proto.model.user.UserOnlineStatuses value) {
            value.getClass();
            if (kindCase_ == 10
                    && kind_ != im.turms.client.model.proto.model.user.UserOnlineStatuses
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.user.UserOnlineStatuses
                        .newBuilder(
                                (im.turms.client.model.proto.model.user.UserOnlineStatuses) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 10;
        }

        /**
         * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
         */
        private void clearUserOnlineStatuses() {
            if (kindCase_ == 10) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int USER_FRIEND_REQUESTS_WITH_VERSION_FIELD_NUMBER = 11;

        /**
         * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
         */
        @java.lang.Override
        public boolean hasUserFriendRequestsWithVersion() {
            return kindCase_ == 11;
        }

        /**
         * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion getUserFriendRequestsWithVersion() {
            if (kindCase_ == 11) {
                return (im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion) kind_;
            }
            return im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
         */
        private void setUserFriendRequestsWithVersion(
                im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 11;
        }

        /**
         * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
         */
        private void mergeUserFriendRequestsWithVersion(
                im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion value) {
            value.getClass();
            if (kindCase_ == 11
                    && kind_ != im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion
                        .newBuilder(
                                (im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 11;
        }

        /**
         * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
         */
        private void clearUserFriendRequestsWithVersion() {
            if (kindCase_ == 11) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int USER_RELATIONSHIP_GROUPS_WITH_VERSION_FIELD_NUMBER = 12;

        /**
         * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
         */
        @java.lang.Override
        public boolean hasUserRelationshipGroupsWithVersion() {
            return kindCase_ == 12;
        }

        /**
         * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion getUserRelationshipGroupsWithVersion() {
            if (kindCase_ == 12) {
                return (im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion) kind_;
            }
            return im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
         */
        private void setUserRelationshipGroupsWithVersion(
                im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 12;
        }

        /**
         * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
         */
        private void mergeUserRelationshipGroupsWithVersion(
                im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion value) {
            value.getClass();
            if (kindCase_ == 12
                    && kind_ != im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion
                        .newBuilder(
                                (im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 12;
        }

        /**
         * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
         */
        private void clearUserRelationshipGroupsWithVersion() {
            if (kindCase_ == 12) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int USER_RELATIONSHIPS_WITH_VERSION_FIELD_NUMBER = 13;

        /**
         * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
         */
        @java.lang.Override
        public boolean hasUserRelationshipsWithVersion() {
            return kindCase_ == 13;
        }

        /**
         * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.user.UserRelationshipsWithVersion getUserRelationshipsWithVersion() {
            if (kindCase_ == 13) {
                return (im.turms.client.model.proto.model.user.UserRelationshipsWithVersion) kind_;
            }
            return im.turms.client.model.proto.model.user.UserRelationshipsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
         */
        private void setUserRelationshipsWithVersion(
                im.turms.client.model.proto.model.user.UserRelationshipsWithVersion value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 13;
        }

        /**
         * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
         */
        private void mergeUserRelationshipsWithVersion(
                im.turms.client.model.proto.model.user.UserRelationshipsWithVersion value) {
            value.getClass();
            if (kindCase_ == 13
                    && kind_ != im.turms.client.model.proto.model.user.UserRelationshipsWithVersion
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.user.UserRelationshipsWithVersion
                        .newBuilder(
                                (im.turms.client.model.proto.model.user.UserRelationshipsWithVersion) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 13;
        }

        /**
         * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
         */
        private void clearUserRelationshipsWithVersion() {
            if (kindCase_ == 13) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int NEARBY_USERS_FIELD_NUMBER = 14;

        /**
         * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
         */
        @java.lang.Override
        public boolean hasNearbyUsers() {
            return kindCase_ == 14;
        }

        /**
         * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.user.NearbyUsers getNearbyUsers() {
            if (kindCase_ == 14) {
                return (im.turms.client.model.proto.model.user.NearbyUsers) kind_;
            }
            return im.turms.client.model.proto.model.user.NearbyUsers.getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
         */
        private void setNearbyUsers(im.turms.client.model.proto.model.user.NearbyUsers value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 14;
        }

        /**
         * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
         */
        private void mergeNearbyUsers(im.turms.client.model.proto.model.user.NearbyUsers value) {
            value.getClass();
            if (kindCase_ == 14
                    && kind_ != im.turms.client.model.proto.model.user.NearbyUsers
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.user.NearbyUsers
                        .newBuilder((im.turms.client.model.proto.model.user.NearbyUsers) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 14;
        }

        /**
         * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
         */
        private void clearNearbyUsers() {
            if (kindCase_ == 14) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int GROUP_INVITATIONS_WITH_VERSION_FIELD_NUMBER = 15;

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
         */
        @java.lang.Override
        public boolean hasGroupInvitationsWithVersion() {
            return kindCase_ == 15;
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.group.GroupInvitationsWithVersion getGroupInvitationsWithVersion() {
            if (kindCase_ == 15) {
                return (im.turms.client.model.proto.model.group.GroupInvitationsWithVersion) kind_;
            }
            return im.turms.client.model.proto.model.group.GroupInvitationsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
         */
        private void setGroupInvitationsWithVersion(
                im.turms.client.model.proto.model.group.GroupInvitationsWithVersion value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 15;
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
         */
        private void mergeGroupInvitationsWithVersion(
                im.turms.client.model.proto.model.group.GroupInvitationsWithVersion value) {
            value.getClass();
            if (kindCase_ == 15
                    && kind_ != im.turms.client.model.proto.model.group.GroupInvitationsWithVersion
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.group.GroupInvitationsWithVersion
                        .newBuilder(
                                (im.turms.client.model.proto.model.group.GroupInvitationsWithVersion) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 15;
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
         */
        private void clearGroupInvitationsWithVersion() {
            if (kindCase_ == 15) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int GROUP_JOIN_QUESTION_ANSWER_RESULT_FIELD_NUMBER = 16;

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
         */
        @java.lang.Override
        public boolean hasGroupJoinQuestionAnswerResult() {
            return kindCase_ == 16;
        }

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult getGroupJoinQuestionAnswerResult() {
            if (kindCase_ == 16) {
                return (im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult) kind_;
            }
            return im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
         */
        private void setGroupJoinQuestionAnswerResult(
                im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 16;
        }

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
         */
        private void mergeGroupJoinQuestionAnswerResult(
                im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult value) {
            value.getClass();
            if (kindCase_ == 16
                    && kind_ != im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult
                        .newBuilder(
                                (im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 16;
        }

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
         */
        private void clearGroupJoinQuestionAnswerResult() {
            if (kindCase_ == 16) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int GROUP_JOIN_REQUESTS_WITH_VERSION_FIELD_NUMBER = 17;

        /**
         * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
         */
        @java.lang.Override
        public boolean hasGroupJoinRequestsWithVersion() {
            return kindCase_ == 17;
        }

        /**
         * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion getGroupJoinRequestsWithVersion() {
            if (kindCase_ == 17) {
                return (im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion) kind_;
            }
            return im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
         */
        private void setGroupJoinRequestsWithVersion(
                im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 17;
        }

        /**
         * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
         */
        private void mergeGroupJoinRequestsWithVersion(
                im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion value) {
            value.getClass();
            if (kindCase_ == 17
                    && kind_ != im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion
                        .newBuilder(
                                (im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 17;
        }

        /**
         * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
         */
        private void clearGroupJoinRequestsWithVersion() {
            if (kindCase_ == 17) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int GROUP_JOIN_QUESTIONS_WITH_VERSION_FIELD_NUMBER = 18;

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
         */
        @java.lang.Override
        public boolean hasGroupJoinQuestionsWithVersion() {
            return kindCase_ == 18;
        }

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion getGroupJoinQuestionsWithVersion() {
            if (kindCase_ == 18) {
                return (im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion) kind_;
            }
            return im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
         */
        private void setGroupJoinQuestionsWithVersion(
                im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 18;
        }

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
         */
        private void mergeGroupJoinQuestionsWithVersion(
                im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion value) {
            value.getClass();
            if (kindCase_ == 18
                    && kind_ != im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion
                        .newBuilder(
                                (im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 18;
        }

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
         */
        private void clearGroupJoinQuestionsWithVersion() {
            if (kindCase_ == 18) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int GROUP_MEMBERS_WITH_VERSION_FIELD_NUMBER = 19;

        /**
         * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
         */
        @java.lang.Override
        public boolean hasGroupMembersWithVersion() {
            return kindCase_ == 19;
        }

        /**
         * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.group.GroupMembersWithVersion getGroupMembersWithVersion() {
            if (kindCase_ == 19) {
                return (im.turms.client.model.proto.model.group.GroupMembersWithVersion) kind_;
            }
            return im.turms.client.model.proto.model.group.GroupMembersWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
         */
        private void setGroupMembersWithVersion(
                im.turms.client.model.proto.model.group.GroupMembersWithVersion value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 19;
        }

        /**
         * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
         */
        private void mergeGroupMembersWithVersion(
                im.turms.client.model.proto.model.group.GroupMembersWithVersion value) {
            value.getClass();
            if (kindCase_ == 19
                    && kind_ != im.turms.client.model.proto.model.group.GroupMembersWithVersion
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.group.GroupMembersWithVersion.newBuilder(
                        (im.turms.client.model.proto.model.group.GroupMembersWithVersion) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 19;
        }

        /**
         * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
         */
        private void clearGroupMembersWithVersion() {
            if (kindCase_ == 19) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int GROUPS_WITH_VERSION_FIELD_NUMBER = 20;

        /**
         * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
         */
        @java.lang.Override
        public boolean hasGroupsWithVersion() {
            return kindCase_ == 20;
        }

        /**
         * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.group.GroupsWithVersion getGroupsWithVersion() {
            if (kindCase_ == 20) {
                return (im.turms.client.model.proto.model.group.GroupsWithVersion) kind_;
            }
            return im.turms.client.model.proto.model.group.GroupsWithVersion.getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
         */
        private void setGroupsWithVersion(
                im.turms.client.model.proto.model.group.GroupsWithVersion value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 20;
        }

        /**
         * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
         */
        private void mergeGroupsWithVersion(
                im.turms.client.model.proto.model.group.GroupsWithVersion value) {
            value.getClass();
            if (kindCase_ == 20
                    && kind_ != im.turms.client.model.proto.model.group.GroupsWithVersion
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.group.GroupsWithVersion
                        .newBuilder(
                                (im.turms.client.model.proto.model.group.GroupsWithVersion) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 20;
        }

        /**
         * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
         */
        private void clearGroupsWithVersion() {
            if (kindCase_ == 20) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static final int STORAGE_RESOURCE_INFOS_FIELD_NUMBER = 50;

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
         */
        @java.lang.Override
        public boolean hasStorageResourceInfos() {
            return kindCase_ == 50;
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.model.storage.StorageResourceInfos getStorageResourceInfos() {
            if (kindCase_ == 50) {
                return (im.turms.client.model.proto.model.storage.StorageResourceInfos) kind_;
            }
            return im.turms.client.model.proto.model.storage.StorageResourceInfos
                    .getDefaultInstance();
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
         */
        private void setStorageResourceInfos(
                im.turms.client.model.proto.model.storage.StorageResourceInfos value) {
            value.getClass();
            kind_ = value;
            kindCase_ = 50;
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
         */
        private void mergeStorageResourceInfos(
                im.turms.client.model.proto.model.storage.StorageResourceInfos value) {
            value.getClass();
            if (kindCase_ == 50
                    && kind_ != im.turms.client.model.proto.model.storage.StorageResourceInfos
                            .getDefaultInstance()) {
                kind_ = im.turms.client.model.proto.model.storage.StorageResourceInfos.newBuilder(
                        (im.turms.client.model.proto.model.storage.StorageResourceInfos) kind_)
                        .mergeFrom(value)
                        .buildPartial();
            } else {
                kind_ = value;
            }
            kindCase_ = 50;
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
         */
        private void clearStorageResourceInfos() {
            if (kindCase_ == 50) {
                kindCase_ = 0;
                kind_ = null;
            }
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data parseFrom(
                byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data parseFrom(
                java.io.InputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data parseDelimitedFrom(
                java.io.InputStream input) throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data parseFrom(
                com.google.protobuf.CodedInputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite
                    .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(
                im.turms.client.model.proto.notification.TurmsNotification.Data prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /**
         * Protobuf type {@code im.turms.proto.TurmsNotification.Data}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.notification.TurmsNotification.Data, Builder>
                implements
                // @@protoc_insertion_point(builder_implements:im.turms.proto.TurmsNotification.Data)
                im.turms.client.model.proto.notification.TurmsNotification.DataOrBuilder {
            // Construct using
            // im.turms.client.model.proto.notification.TurmsNotification.Data.newBuilder()
            private Builder() {
                super(DEFAULT_INSTANCE);
            }

            @java.lang.Override
            public KindCase getKindCase() {
                return instance.getKindCase();
            }

            public Builder clearKind() {
                copyOnWrite();
                instance.clearKind();
                return this;
            }

            /**
             * <pre>
             * Common
             * </pre>
             * 
             * <code>int64 long = 1;</code>
             *
             * @return Whether the long field is set.
             */
            @java.lang.Override
            public boolean hasLong() {
                return instance.hasLong();
            }

            /**
             * <pre>
             * Common
             * </pre>
             * 
             * <code>int64 long = 1;</code>
             *
             * @return The long.
             */
            @java.lang.Override
            public long getLong() {
                return instance.getLong();
            }

            /**
             * <pre>
             * Common
             * </pre>
             * 
             * <code>int64 long = 1;</code>
             *
             * @param value The long to set.
             * @return This builder for chaining.
             */
            public Builder setLong(long value) {
                copyOnWrite();
                instance.setLong(value);
                return this;
            }

            /**
             * <pre>
             * Common
             * </pre>
             * 
             * <code>int64 long = 1;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearLong() {
                copyOnWrite();
                instance.clearLong();
                return this;
            }

            /**
             * <code>string string = 2;</code>
             *
             * @return Whether the string field is set.
             */
            @java.lang.Override
            public boolean hasString() {
                return instance.hasString();
            }

            /**
             * <code>string string = 2;</code>
             *
             * @return The string.
             */
            @java.lang.Override
            public java.lang.String getString() {
                return instance.getString();
            }

            /**
             * <code>string string = 2;</code>
             *
             * @return The bytes for string.
             */
            @java.lang.Override
            public com.google.protobuf.ByteString getStringBytes() {
                return instance.getStringBytes();
            }

            /**
             * <code>string string = 2;</code>
             *
             * @param value The string to set.
             * @return This builder for chaining.
             */
            public Builder setString(java.lang.String value) {
                copyOnWrite();
                instance.setString(value);
                return this;
            }

            /**
             * <code>string string = 2;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearString() {
                copyOnWrite();
                instance.clearString();
                return this;
            }

            /**
             * <code>string string = 2;</code>
             *
             * @param value The bytes for string to set.
             * @return This builder for chaining.
             */
            public Builder setStringBytes(com.google.protobuf.ByteString value) {
                copyOnWrite();
                instance.setStringBytes(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            @java.lang.Override
            public boolean hasLongsWithVersion() {
                return instance.hasLongsWithVersion();
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.common.LongsWithVersion getLongsWithVersion() {
                return instance.getLongsWithVersion();
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            public Builder setLongsWithVersion(
                    im.turms.client.model.proto.model.common.LongsWithVersion value) {
                copyOnWrite();
                instance.setLongsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            public Builder setLongsWithVersion(
                    im.turms.client.model.proto.model.common.LongsWithVersion.Builder builderForValue) {
                copyOnWrite();
                instance.setLongsWithVersion(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            public Builder mergeLongsWithVersion(
                    im.turms.client.model.proto.model.common.LongsWithVersion value) {
                copyOnWrite();
                instance.mergeLongsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            public Builder clearLongsWithVersion() {
                copyOnWrite();
                instance.clearLongsWithVersion();
                return this;
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            @java.lang.Override
            public boolean hasStringsWithVersion() {
                return instance.hasStringsWithVersion();
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.common.StringsWithVersion getStringsWithVersion() {
                return instance.getStringsWithVersion();
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            public Builder setStringsWithVersion(
                    im.turms.client.model.proto.model.common.StringsWithVersion value) {
                copyOnWrite();
                instance.setStringsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            public Builder setStringsWithVersion(
                    im.turms.client.model.proto.model.common.StringsWithVersion.Builder builderForValue) {
                copyOnWrite();
                instance.setStringsWithVersion(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            public Builder mergeStringsWithVersion(
                    im.turms.client.model.proto.model.common.StringsWithVersion value) {
                copyOnWrite();
                instance.mergeStringsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            public Builder clearStringsWithVersion() {
                copyOnWrite();
                instance.clearStringsWithVersion();
                return this;
            }

            /**
             * <pre>
             * Conversation
             * </pre>
             * 
             * <code>.im.turms.proto.Conversations conversations = 5;</code>
             */
            @java.lang.Override
            public boolean hasConversations() {
                return instance.hasConversations();
            }

            /**
             * <pre>
             * Conversation
             * </pre>
             * 
             * <code>.im.turms.proto.Conversations conversations = 5;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.conversation.Conversations getConversations() {
                return instance.getConversations();
            }

            /**
             * <pre>
             * Conversation
             * </pre>
             * 
             * <code>.im.turms.proto.Conversations conversations = 5;</code>
             */
            public Builder setConversations(
                    im.turms.client.model.proto.model.conversation.Conversations value) {
                copyOnWrite();
                instance.setConversations(value);
                return this;
            }

            /**
             * <pre>
             * Conversation
             * </pre>
             * 
             * <code>.im.turms.proto.Conversations conversations = 5;</code>
             */
            public Builder setConversations(
                    im.turms.client.model.proto.model.conversation.Conversations.Builder builderForValue) {
                copyOnWrite();
                instance.setConversations(builderForValue.build());
                return this;
            }

            /**
             * <pre>
             * Conversation
             * </pre>
             * 
             * <code>.im.turms.proto.Conversations conversations = 5;</code>
             */
            public Builder mergeConversations(
                    im.turms.client.model.proto.model.conversation.Conversations value) {
                copyOnWrite();
                instance.mergeConversations(value);
                return this;
            }

            /**
             * <pre>
             * Conversation
             * </pre>
             * 
             * <code>.im.turms.proto.Conversations conversations = 5;</code>
             */
            public Builder clearConversations() {
                copyOnWrite();
                instance.clearConversations();
                return this;
            }

            /**
             * <pre>
             * Message
             * </pre>
             * 
             * <code>.im.turms.proto.Messages messages = 6;</code>
             */
            @java.lang.Override
            public boolean hasMessages() {
                return instance.hasMessages();
            }

            /**
             * <pre>
             * Message
             * </pre>
             * 
             * <code>.im.turms.proto.Messages messages = 6;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.message.Messages getMessages() {
                return instance.getMessages();
            }

            /**
             * <pre>
             * Message
             * </pre>
             * 
             * <code>.im.turms.proto.Messages messages = 6;</code>
             */
            public Builder setMessages(im.turms.client.model.proto.model.message.Messages value) {
                copyOnWrite();
                instance.setMessages(value);
                return this;
            }

            /**
             * <pre>
             * Message
             * </pre>
             * 
             * <code>.im.turms.proto.Messages messages = 6;</code>
             */
            public Builder setMessages(
                    im.turms.client.model.proto.model.message.Messages.Builder builderForValue) {
                copyOnWrite();
                instance.setMessages(builderForValue.build());
                return this;
            }

            /**
             * <pre>
             * Message
             * </pre>
             * 
             * <code>.im.turms.proto.Messages messages = 6;</code>
             */
            public Builder mergeMessages(im.turms.client.model.proto.model.message.Messages value) {
                copyOnWrite();
                instance.mergeMessages(value);
                return this;
            }

            /**
             * <pre>
             * Message
             * </pre>
             * 
             * <code>.im.turms.proto.Messages messages = 6;</code>
             */
            public Builder clearMessages() {
                copyOnWrite();
                instance.clearMessages();
                return this;
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            @java.lang.Override
            public boolean hasMessagesWithTotalList() {
                return instance.hasMessagesWithTotalList();
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.message.MessagesWithTotalList getMessagesWithTotalList() {
                return instance.getMessagesWithTotalList();
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            public Builder setMessagesWithTotalList(
                    im.turms.client.model.proto.model.message.MessagesWithTotalList value) {
                copyOnWrite();
                instance.setMessagesWithTotalList(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            public Builder setMessagesWithTotalList(
                    im.turms.client.model.proto.model.message.MessagesWithTotalList.Builder builderForValue) {
                copyOnWrite();
                instance.setMessagesWithTotalList(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            public Builder mergeMessagesWithTotalList(
                    im.turms.client.model.proto.model.message.MessagesWithTotalList value) {
                copyOnWrite();
                instance.mergeMessagesWithTotalList(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            public Builder clearMessagesWithTotalList() {
                copyOnWrite();
                instance.clearMessagesWithTotalList();
                return this;
            }

            /**
             * <pre>
             * User
             * </pre>
             * 
             * <code>.im.turms.proto.UserSession user_session = 8;</code>
             */
            @java.lang.Override
            public boolean hasUserSession() {
                return instance.hasUserSession();
            }

            /**
             * <pre>
             * User
             * </pre>
             * 
             * <code>.im.turms.proto.UserSession user_session = 8;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.user.UserSession getUserSession() {
                return instance.getUserSession();
            }

            /**
             * <pre>
             * User
             * </pre>
             * 
             * <code>.im.turms.proto.UserSession user_session = 8;</code>
             */
            public Builder setUserSession(
                    im.turms.client.model.proto.model.user.UserSession value) {
                copyOnWrite();
                instance.setUserSession(value);
                return this;
            }

            /**
             * <pre>
             * User
             * </pre>
             * 
             * <code>.im.turms.proto.UserSession user_session = 8;</code>
             */
            public Builder setUserSession(
                    im.turms.client.model.proto.model.user.UserSession.Builder builderForValue) {
                copyOnWrite();
                instance.setUserSession(builderForValue.build());
                return this;
            }

            /**
             * <pre>
             * User
             * </pre>
             * 
             * <code>.im.turms.proto.UserSession user_session = 8;</code>
             */
            public Builder mergeUserSession(
                    im.turms.client.model.proto.model.user.UserSession value) {
                copyOnWrite();
                instance.mergeUserSession(value);
                return this;
            }

            /**
             * <pre>
             * User
             * </pre>
             * 
             * <code>.im.turms.proto.UserSession user_session = 8;</code>
             */
            public Builder clearUserSession() {
                copyOnWrite();
                instance.clearUserSession();
                return this;
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            @java.lang.Override
            public boolean hasUserInfosWithVersion() {
                return instance.hasUserInfosWithVersion();
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.user.UserInfosWithVersion getUserInfosWithVersion() {
                return instance.getUserInfosWithVersion();
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            public Builder setUserInfosWithVersion(
                    im.turms.client.model.proto.model.user.UserInfosWithVersion value) {
                copyOnWrite();
                instance.setUserInfosWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            public Builder setUserInfosWithVersion(
                    im.turms.client.model.proto.model.user.UserInfosWithVersion.Builder builderForValue) {
                copyOnWrite();
                instance.setUserInfosWithVersion(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            public Builder mergeUserInfosWithVersion(
                    im.turms.client.model.proto.model.user.UserInfosWithVersion value) {
                copyOnWrite();
                instance.mergeUserInfosWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            public Builder clearUserInfosWithVersion() {
                copyOnWrite();
                instance.clearUserInfosWithVersion();
                return this;
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            @java.lang.Override
            public boolean hasUserOnlineStatuses() {
                return instance.hasUserOnlineStatuses();
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.user.UserOnlineStatuses getUserOnlineStatuses() {
                return instance.getUserOnlineStatuses();
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            public Builder setUserOnlineStatuses(
                    im.turms.client.model.proto.model.user.UserOnlineStatuses value) {
                copyOnWrite();
                instance.setUserOnlineStatuses(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            public Builder setUserOnlineStatuses(
                    im.turms.client.model.proto.model.user.UserOnlineStatuses.Builder builderForValue) {
                copyOnWrite();
                instance.setUserOnlineStatuses(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            public Builder mergeUserOnlineStatuses(
                    im.turms.client.model.proto.model.user.UserOnlineStatuses value) {
                copyOnWrite();
                instance.mergeUserOnlineStatuses(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            public Builder clearUserOnlineStatuses() {
                copyOnWrite();
                instance.clearUserOnlineStatuses();
                return this;
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            @java.lang.Override
            public boolean hasUserFriendRequestsWithVersion() {
                return instance.hasUserFriendRequestsWithVersion();
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion getUserFriendRequestsWithVersion() {
                return instance.getUserFriendRequestsWithVersion();
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            public Builder setUserFriendRequestsWithVersion(
                    im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion value) {
                copyOnWrite();
                instance.setUserFriendRequestsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            public Builder setUserFriendRequestsWithVersion(
                    im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion.Builder builderForValue) {
                copyOnWrite();
                instance.setUserFriendRequestsWithVersion(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            public Builder mergeUserFriendRequestsWithVersion(
                    im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion value) {
                copyOnWrite();
                instance.mergeUserFriendRequestsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            public Builder clearUserFriendRequestsWithVersion() {
                copyOnWrite();
                instance.clearUserFriendRequestsWithVersion();
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            @java.lang.Override
            public boolean hasUserRelationshipGroupsWithVersion() {
                return instance.hasUserRelationshipGroupsWithVersion();
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion getUserRelationshipGroupsWithVersion() {
                return instance.getUserRelationshipGroupsWithVersion();
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            public Builder setUserRelationshipGroupsWithVersion(
                    im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion value) {
                copyOnWrite();
                instance.setUserRelationshipGroupsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            public Builder setUserRelationshipGroupsWithVersion(
                    im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion.Builder builderForValue) {
                copyOnWrite();
                instance.setUserRelationshipGroupsWithVersion(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            public Builder mergeUserRelationshipGroupsWithVersion(
                    im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion value) {
                copyOnWrite();
                instance.mergeUserRelationshipGroupsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            public Builder clearUserRelationshipGroupsWithVersion() {
                copyOnWrite();
                instance.clearUserRelationshipGroupsWithVersion();
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            @java.lang.Override
            public boolean hasUserRelationshipsWithVersion() {
                return instance.hasUserRelationshipsWithVersion();
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.user.UserRelationshipsWithVersion getUserRelationshipsWithVersion() {
                return instance.getUserRelationshipsWithVersion();
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            public Builder setUserRelationshipsWithVersion(
                    im.turms.client.model.proto.model.user.UserRelationshipsWithVersion value) {
                copyOnWrite();
                instance.setUserRelationshipsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            public Builder setUserRelationshipsWithVersion(
                    im.turms.client.model.proto.model.user.UserRelationshipsWithVersion.Builder builderForValue) {
                copyOnWrite();
                instance.setUserRelationshipsWithVersion(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            public Builder mergeUserRelationshipsWithVersion(
                    im.turms.client.model.proto.model.user.UserRelationshipsWithVersion value) {
                copyOnWrite();
                instance.mergeUserRelationshipsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            public Builder clearUserRelationshipsWithVersion() {
                copyOnWrite();
                instance.clearUserRelationshipsWithVersion();
                return this;
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            @java.lang.Override
            public boolean hasNearbyUsers() {
                return instance.hasNearbyUsers();
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.user.NearbyUsers getNearbyUsers() {
                return instance.getNearbyUsers();
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            public Builder setNearbyUsers(
                    im.turms.client.model.proto.model.user.NearbyUsers value) {
                copyOnWrite();
                instance.setNearbyUsers(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            public Builder setNearbyUsers(
                    im.turms.client.model.proto.model.user.NearbyUsers.Builder builderForValue) {
                copyOnWrite();
                instance.setNearbyUsers(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            public Builder mergeNearbyUsers(
                    im.turms.client.model.proto.model.user.NearbyUsers value) {
                copyOnWrite();
                instance.mergeNearbyUsers(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            public Builder clearNearbyUsers() {
                copyOnWrite();
                instance.clearNearbyUsers();
                return this;
            }

            /**
             * <pre>
             * Group
             * </pre>
             * 
             * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
             */
            @java.lang.Override
            public boolean hasGroupInvitationsWithVersion() {
                return instance.hasGroupInvitationsWithVersion();
            }

            /**
             * <pre>
             * Group
             * </pre>
             * 
             * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.group.GroupInvitationsWithVersion getGroupInvitationsWithVersion() {
                return instance.getGroupInvitationsWithVersion();
            }

            /**
             * <pre>
             * Group
             * </pre>
             * 
             * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
             */
            public Builder setGroupInvitationsWithVersion(
                    im.turms.client.model.proto.model.group.GroupInvitationsWithVersion value) {
                copyOnWrite();
                instance.setGroupInvitationsWithVersion(value);
                return this;
            }

            /**
             * <pre>
             * Group
             * </pre>
             * 
             * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
             */
            public Builder setGroupInvitationsWithVersion(
                    im.turms.client.model.proto.model.group.GroupInvitationsWithVersion.Builder builderForValue) {
                copyOnWrite();
                instance.setGroupInvitationsWithVersion(builderForValue.build());
                return this;
            }

            /**
             * <pre>
             * Group
             * </pre>
             * 
             * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
             */
            public Builder mergeGroupInvitationsWithVersion(
                    im.turms.client.model.proto.model.group.GroupInvitationsWithVersion value) {
                copyOnWrite();
                instance.mergeGroupInvitationsWithVersion(value);
                return this;
            }

            /**
             * <pre>
             * Group
             * </pre>
             * 
             * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
             */
            public Builder clearGroupInvitationsWithVersion() {
                copyOnWrite();
                instance.clearGroupInvitationsWithVersion();
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            @java.lang.Override
            public boolean hasGroupJoinQuestionAnswerResult() {
                return instance.hasGroupJoinQuestionAnswerResult();
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult getGroupJoinQuestionAnswerResult() {
                return instance.getGroupJoinQuestionAnswerResult();
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            public Builder setGroupJoinQuestionAnswerResult(
                    im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult value) {
                copyOnWrite();
                instance.setGroupJoinQuestionAnswerResult(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            public Builder setGroupJoinQuestionAnswerResult(
                    im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult.Builder builderForValue) {
                copyOnWrite();
                instance.setGroupJoinQuestionAnswerResult(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            public Builder mergeGroupJoinQuestionAnswerResult(
                    im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult value) {
                copyOnWrite();
                instance.mergeGroupJoinQuestionAnswerResult(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            public Builder clearGroupJoinQuestionAnswerResult() {
                copyOnWrite();
                instance.clearGroupJoinQuestionAnswerResult();
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            @java.lang.Override
            public boolean hasGroupJoinRequestsWithVersion() {
                return instance.hasGroupJoinRequestsWithVersion();
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion getGroupJoinRequestsWithVersion() {
                return instance.getGroupJoinRequestsWithVersion();
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            public Builder setGroupJoinRequestsWithVersion(
                    im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion value) {
                copyOnWrite();
                instance.setGroupJoinRequestsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            public Builder setGroupJoinRequestsWithVersion(
                    im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion.Builder builderForValue) {
                copyOnWrite();
                instance.setGroupJoinRequestsWithVersion(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            public Builder mergeGroupJoinRequestsWithVersion(
                    im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion value) {
                copyOnWrite();
                instance.mergeGroupJoinRequestsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            public Builder clearGroupJoinRequestsWithVersion() {
                copyOnWrite();
                instance.clearGroupJoinRequestsWithVersion();
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            @java.lang.Override
            public boolean hasGroupJoinQuestionsWithVersion() {
                return instance.hasGroupJoinQuestionsWithVersion();
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion getGroupJoinQuestionsWithVersion() {
                return instance.getGroupJoinQuestionsWithVersion();
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            public Builder setGroupJoinQuestionsWithVersion(
                    im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion value) {
                copyOnWrite();
                instance.setGroupJoinQuestionsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            public Builder setGroupJoinQuestionsWithVersion(
                    im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion.Builder builderForValue) {
                copyOnWrite();
                instance.setGroupJoinQuestionsWithVersion(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            public Builder mergeGroupJoinQuestionsWithVersion(
                    im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion value) {
                copyOnWrite();
                instance.mergeGroupJoinQuestionsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            public Builder clearGroupJoinQuestionsWithVersion() {
                copyOnWrite();
                instance.clearGroupJoinQuestionsWithVersion();
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            @java.lang.Override
            public boolean hasGroupMembersWithVersion() {
                return instance.hasGroupMembersWithVersion();
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.group.GroupMembersWithVersion getGroupMembersWithVersion() {
                return instance.getGroupMembersWithVersion();
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            public Builder setGroupMembersWithVersion(
                    im.turms.client.model.proto.model.group.GroupMembersWithVersion value) {
                copyOnWrite();
                instance.setGroupMembersWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            public Builder setGroupMembersWithVersion(
                    im.turms.client.model.proto.model.group.GroupMembersWithVersion.Builder builderForValue) {
                copyOnWrite();
                instance.setGroupMembersWithVersion(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            public Builder mergeGroupMembersWithVersion(
                    im.turms.client.model.proto.model.group.GroupMembersWithVersion value) {
                copyOnWrite();
                instance.mergeGroupMembersWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            public Builder clearGroupMembersWithVersion() {
                copyOnWrite();
                instance.clearGroupMembersWithVersion();
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            @java.lang.Override
            public boolean hasGroupsWithVersion() {
                return instance.hasGroupsWithVersion();
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.group.GroupsWithVersion getGroupsWithVersion() {
                return instance.getGroupsWithVersion();
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            public Builder setGroupsWithVersion(
                    im.turms.client.model.proto.model.group.GroupsWithVersion value) {
                copyOnWrite();
                instance.setGroupsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            public Builder setGroupsWithVersion(
                    im.turms.client.model.proto.model.group.GroupsWithVersion.Builder builderForValue) {
                copyOnWrite();
                instance.setGroupsWithVersion(builderForValue.build());
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            public Builder mergeGroupsWithVersion(
                    im.turms.client.model.proto.model.group.GroupsWithVersion value) {
                copyOnWrite();
                instance.mergeGroupsWithVersion(value);
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            public Builder clearGroupsWithVersion() {
                copyOnWrite();
                instance.clearGroupsWithVersion();
                return this;
            }

            /**
             * <pre>
             * Storage
             * </pre>
             * 
             * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
             */
            @java.lang.Override
            public boolean hasStorageResourceInfos() {
                return instance.hasStorageResourceInfos();
            }

            /**
             * <pre>
             * Storage
             * </pre>
             * 
             * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
             */
            @java.lang.Override
            public im.turms.client.model.proto.model.storage.StorageResourceInfos getStorageResourceInfos() {
                return instance.getStorageResourceInfos();
            }

            /**
             * <pre>
             * Storage
             * </pre>
             * 
             * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
             */
            public Builder setStorageResourceInfos(
                    im.turms.client.model.proto.model.storage.StorageResourceInfos value) {
                copyOnWrite();
                instance.setStorageResourceInfos(value);
                return this;
            }

            /**
             * <pre>
             * Storage
             * </pre>
             * 
             * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
             */
            public Builder setStorageResourceInfos(
                    im.turms.client.model.proto.model.storage.StorageResourceInfos.Builder builderForValue) {
                copyOnWrite();
                instance.setStorageResourceInfos(builderForValue.build());
                return this;
            }

            /**
             * <pre>
             * Storage
             * </pre>
             * 
             * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
             */
            public Builder mergeStorageResourceInfos(
                    im.turms.client.model.proto.model.storage.StorageResourceInfos value) {
                copyOnWrite();
                instance.mergeStorageResourceInfos(value);
                return this;
            }

            /**
             * <pre>
             * Storage
             * </pre>
             * 
             * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
             */
            public Builder clearStorageResourceInfos() {
                copyOnWrite();
                instance.clearStorageResourceInfos();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:im.turms.proto.TurmsNotification.Data)
        }

        @java.lang.Override
        @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
        protected final java.lang.Object dynamicMethod(
                com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
                java.lang.Object arg0,
                java.lang.Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE: {
                    return new im.turms.client.model.proto.notification.TurmsNotification.Data();
                }
                case NEW_BUILDER: {
                    return new Builder();
                }
                case BUILD_MESSAGE_INFO: {
                    java.lang.Object[] objects = new java.lang.Object[]{"kind_",
                            "kindCase_",
                            im.turms.client.model.proto.model.common.LongsWithVersion.class,
                            im.turms.client.model.proto.model.common.StringsWithVersion.class,
                            im.turms.client.model.proto.model.conversation.Conversations.class,
                            im.turms.client.model.proto.model.message.Messages.class,
                            im.turms.client.model.proto.model.message.MessagesWithTotalList.class,
                            im.turms.client.model.proto.model.user.UserSession.class,
                            im.turms.client.model.proto.model.user.UserInfosWithVersion.class,
                            im.turms.client.model.proto.model.user.UserOnlineStatuses.class,
                            im.turms.client.model.proto.model.user.UserFriendRequestsWithVersion.class,
                            im.turms.client.model.proto.model.user.UserRelationshipGroupsWithVersion.class,
                            im.turms.client.model.proto.model.user.UserRelationshipsWithVersion.class,
                            im.turms.client.model.proto.model.user.NearbyUsers.class,
                            im.turms.client.model.proto.model.group.GroupInvitationsWithVersion.class,
                            im.turms.client.model.proto.model.group.GroupJoinQuestionsAnswerResult.class,
                            im.turms.client.model.proto.model.group.GroupJoinRequestsWithVersion.class,
                            im.turms.client.model.proto.model.group.GroupJoinQuestionsWithVersion.class,
                            im.turms.client.model.proto.model.group.GroupMembersWithVersion.class,
                            im.turms.client.model.proto.model.group.GroupsWithVersion.class,
                            im.turms.client.model.proto.model.storage.StorageResourceInfos.class,};
                    java.lang.String info =
                            "\u0000\u0015\u0001\u0000\u00012\u0015\u0000\u0000\u0000\u00015\u0000\u0002\u023b"
                                    + "\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000\u0007<\u0000\b<\u0000"
                                    + "\t<\u0000\n<\u0000\u000b<\u0000\f<\u0000\r<\u0000\u000e<\u0000\u000f<\u0000\u0010"
                                    + "<\u0000\u0011<\u0000\u0012<\u0000\u0013<\u0000\u0014<\u00002<\u0000";
                    return newMessageInfo(DEFAULT_INSTANCE, info, objects);
                }
                // fall through
                case GET_DEFAULT_INSTANCE: {
                    return DEFAULT_INSTANCE;
                }
                case GET_PARSER: {
                    com.google.protobuf.Parser<im.turms.client.model.proto.notification.TurmsNotification.Data> parser =
                            PARSER;
                    if (parser == null) {
                        synchronized (im.turms.client.model.proto.notification.TurmsNotification.Data.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                }
                case GET_MEMOIZED_IS_INITIALIZED: {
                    return (byte) 1;
                }
                case SET_MEMOIZED_IS_INITIALIZED: {
                    return null;
                }
            }
            throw new UnsupportedOperationException();
        }

        // @@protoc_insertion_point(class_scope:im.turms.proto.TurmsNotification.Data)
        private static final im.turms.client.model.proto.notification.TurmsNotification.Data DEFAULT_INSTANCE;

        static {
            Data defaultInstance = new Data();
            // New instances are implicitly immutable so no need to make
            // immutable.
            DEFAULT_INSTANCE = defaultInstance;
            com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(Data.class,
                    defaultInstance);
        }

        public static im.turms.client.model.proto.notification.TurmsNotification.Data getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static volatile com.google.protobuf.Parser<Data> PARSER;

        public static com.google.protobuf.Parser<Data> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    private int bitField0_;
    public static final int TIMESTAMP_FIELD_NUMBER = 1;
    private long timestamp_;

    /**
     * <pre>
     * Common =&gt; [1, 3]
     * </pre>
     * 
     * <code>int64 timestamp = 1;</code>
     *
     * @return The timestamp.
     */
    @java.lang.Override
    public long getTimestamp() {
        return timestamp_;
    }

    /**
     * <pre>
     * Common =&gt; [1, 3]
     * </pre>
     * 
     * <code>int64 timestamp = 1;</code>
     *
     * @param value The timestamp to set.
     */
    private void setTimestamp(long value) {

        timestamp_ = value;
    }

    /**
     * <pre>
     * Common =&gt; [1, 3]
     * </pre>
     * 
     * <code>int64 timestamp = 1;</code>
     */
    private void clearTimestamp() {

        timestamp_ = 0L;
    }

    public static final int REQUEST_ID_FIELD_NUMBER = 4;
    private long requestId_;

    /**
     * <pre>
     * Response =&gt; [4, 9]
     * "request_id" is used to tell the client that
     * this notification is a response to the specific request
     * </pre>
     * 
     * <code>optional int64 request_id = 4;</code>
     *
     * @return Whether the requestId field is set.
     */
    @java.lang.Override
    public boolean hasRequestId() {
        return ((bitField0_ & 0x00000001) != 0);
    }

    /**
     * <pre>
     * Response =&gt; [4, 9]
     * "request_id" is used to tell the client that
     * this notification is a response to the specific request
     * </pre>
     * 
     * <code>optional int64 request_id = 4;</code>
     *
     * @return The requestId.
     */
    @java.lang.Override
    public long getRequestId() {
        return requestId_;
    }

    /**
     * <pre>
     * Response =&gt; [4, 9]
     * "request_id" is used to tell the client that
     * this notification is a response to the specific request
     * </pre>
     * 
     * <code>optional int64 request_id = 4;</code>
     *
     * @param value The requestId to set.
     */
    private void setRequestId(long value) {
        bitField0_ |= 0x00000001;
        requestId_ = value;
    }

    /**
     * <pre>
     * Response =&gt; [4, 9]
     * "request_id" is used to tell the client that
     * this notification is a response to the specific request
     * </pre>
     * 
     * <code>optional int64 request_id = 4;</code>
     */
    private void clearRequestId() {
        bitField0_ &= ~0x00000001;
        requestId_ = 0L;
    }

    public static final int CODE_FIELD_NUMBER = 5;
    private int code_;

    /**
     * <code>optional int32 code = 5;</code>
     *
     * @return Whether the code field is set.
     */
    @java.lang.Override
    public boolean hasCode() {
        return ((bitField0_ & 0x00000002) != 0);
    }

    /**
     * <code>optional int32 code = 5;</code>
     *
     * @return The code.
     */
    @java.lang.Override
    public int getCode() {
        return code_;
    }

    /**
     * <code>optional int32 code = 5;</code>
     *
     * @param value The code to set.
     */
    private void setCode(int value) {
        bitField0_ |= 0x00000002;
        code_ = value;
    }

    /**
     * <code>optional int32 code = 5;</code>
     */
    private void clearCode() {
        bitField0_ &= ~0x00000002;
        code_ = 0;
    }

    public static final int REASON_FIELD_NUMBER = 6;
    private java.lang.String reason_;

    /**
     * <code>optional string reason = 6;</code>
     *
     * @return Whether the reason field is set.
     */
    @java.lang.Override
    public boolean hasReason() {
        return ((bitField0_ & 0x00000004) != 0);
    }

    /**
     * <code>optional string reason = 6;</code>
     *
     * @return The reason.
     */
    @java.lang.Override
    public java.lang.String getReason() {
        return reason_;
    }

    /**
     * <code>optional string reason = 6;</code>
     *
     * @return The bytes for reason.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getReasonBytes() {
        return com.google.protobuf.ByteString.copyFromUtf8(reason_);
    }

    /**
     * <code>optional string reason = 6;</code>
     *
     * @param value The reason to set.
     */
    private void setReason(java.lang.String value) {
        java.lang.Class<?> valueClass = value.getClass();
        bitField0_ |= 0x00000004;
        reason_ = value;
    }

    /**
     * <code>optional string reason = 6;</code>
     */
    private void clearReason() {
        bitField0_ &= ~0x00000004;
        reason_ = getDefaultInstance().getReason();
    }

    /**
     * <code>optional string reason = 6;</code>
     *
     * @param value The bytes for reason to set.
     */
    private void setReasonBytes(com.google.protobuf.ByteString value) {
        checkByteStringIsUtf8(value);
        reason_ = value.toStringUtf8();
        bitField0_ |= 0x00000004;
    }

    public static final int DATA_FIELD_NUMBER = 7;
    private im.turms.client.model.proto.notification.TurmsNotification.Data data_;

    /**
     * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
     */
    @java.lang.Override
    public boolean hasData() {
        return data_ != null;
    }

    /**
     * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.notification.TurmsNotification.Data getData() {
        return data_ == null
                ? im.turms.client.model.proto.notification.TurmsNotification.Data
                        .getDefaultInstance()
                : data_;
    }

    /**
     * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
     */
    private void setData(im.turms.client.model.proto.notification.TurmsNotification.Data value) {
        value.getClass();
        data_ = value;

    }

    /**
     * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
     */
    @java.lang.SuppressWarnings({"ReferenceEquality"})
    private void mergeData(im.turms.client.model.proto.notification.TurmsNotification.Data value) {
        value.getClass();
        if (data_ != null
                && data_ != im.turms.client.model.proto.notification.TurmsNotification.Data
                        .getDefaultInstance()) {
            data_ = im.turms.client.model.proto.notification.TurmsNotification.Data
                    .newBuilder(data_)
                    .mergeFrom(value)
                    .buildPartial();
        } else {
            data_ = value;
        }

    }

    /**
     * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
     */
    private void clearData() {
        data_ = null;

    }

    public static final int REQUESTER_ID_FIELD_NUMBER = 10;
    private long requesterId_;

    /**
     * <pre>
     * Notification =&gt; [10, 15]
     * "requester_id" only exists when a requester triggers a notification to its recipients
     * Note: Do not move "requester_id" to TurmsRequest because it requires rebuilding
     * a new TurmsNotification when recipients need "requester_id".
     * </pre>
     * 
     * <code>optional int64 requester_id = 10;</code>
     *
     * @return Whether the requesterId field is set.
     */
    @java.lang.Override
    public boolean hasRequesterId() {
        return ((bitField0_ & 0x00000008) != 0);
    }

    /**
     * <pre>
     * Notification =&gt; [10, 15]
     * "requester_id" only exists when a requester triggers a notification to its recipients
     * Note: Do not move "requester_id" to TurmsRequest because it requires rebuilding
     * a new TurmsNotification when recipients need "requester_id".
     * </pre>
     * 
     * <code>optional int64 requester_id = 10;</code>
     *
     * @return The requesterId.
     */
    @java.lang.Override
    public long getRequesterId() {
        return requesterId_;
    }

    /**
     * <pre>
     * Notification =&gt; [10, 15]
     * "requester_id" only exists when a requester triggers a notification to its recipients
     * Note: Do not move "requester_id" to TurmsRequest because it requires rebuilding
     * a new TurmsNotification when recipients need "requester_id".
     * </pre>
     * 
     * <code>optional int64 requester_id = 10;</code>
     *
     * @param value The requesterId to set.
     */
    private void setRequesterId(long value) {
        bitField0_ |= 0x00000008;
        requesterId_ = value;
    }

    /**
     * <pre>
     * Notification =&gt; [10, 15]
     * "requester_id" only exists when a requester triggers a notification to its recipients
     * Note: Do not move "requester_id" to TurmsRequest because it requires rebuilding
     * a new TurmsNotification when recipients need "requester_id".
     * </pre>
     * 
     * <code>optional int64 requester_id = 10;</code>
     */
    private void clearRequesterId() {
        bitField0_ &= ~0x00000008;
        requesterId_ = 0L;
    }

    public static final int CLOSE_STATUS_FIELD_NUMBER = 11;
    private int closeStatus_;

    /**
     * <code>optional int32 close_status = 11;</code>
     *
     * @return Whether the closeStatus field is set.
     */
    @java.lang.Override
    public boolean hasCloseStatus() {
        return ((bitField0_ & 0x00000010) != 0);
    }

    /**
     * <code>optional int32 close_status = 11;</code>
     *
     * @return The closeStatus.
     */
    @java.lang.Override
    public int getCloseStatus() {
        return closeStatus_;
    }

    /**
     * <code>optional int32 close_status = 11;</code>
     *
     * @param value The closeStatus to set.
     */
    private void setCloseStatus(int value) {
        bitField0_ |= 0x00000010;
        closeStatus_ = value;
    }

    /**
     * <code>optional int32 close_status = 11;</code>
     */
    private void clearCloseStatus() {
        bitField0_ &= ~0x00000010;
        closeStatus_ = 0;
    }

    public static final int RELAYED_REQUEST_FIELD_NUMBER = 12;
    private im.turms.client.model.proto.request.TurmsRequest relayedRequest_;

    /**
     * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
     */
    @java.lang.Override
    public boolean hasRelayedRequest() {
        return relayedRequest_ != null;
    }

    /**
     * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
     */
    @java.lang.Override
    public im.turms.client.model.proto.request.TurmsRequest getRelayedRequest() {
        return relayedRequest_ == null
                ? im.turms.client.model.proto.request.TurmsRequest.getDefaultInstance()
                : relayedRequest_;
    }

    /**
     * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
     */
    private void setRelayedRequest(im.turms.client.model.proto.request.TurmsRequest value) {
        value.getClass();
        relayedRequest_ = value;

    }

    /**
     * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
     */
    @java.lang.SuppressWarnings({"ReferenceEquality"})
    private void mergeRelayedRequest(im.turms.client.model.proto.request.TurmsRequest value) {
        value.getClass();
        if (relayedRequest_ != null
                && relayedRequest_ != im.turms.client.model.proto.request.TurmsRequest
                        .getDefaultInstance()) {
            relayedRequest_ =
                    im.turms.client.model.proto.request.TurmsRequest.newBuilder(relayedRequest_)
                            .mergeFrom(value)
                            .buildPartial();
        } else {
            relayedRequest_ = value;
        }

    }

    /**
     * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
     */
    private void clearRelayedRequest() {
        relayedRequest_ = null;

    }

    public static im.turms.client.model.proto.notification.TurmsNotification parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.notification.TurmsNotification parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.notification.TurmsNotification parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.notification.TurmsNotification parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.notification.TurmsNotification parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static im.turms.client.model.proto.notification.TurmsNotification parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static im.turms.client.model.proto.notification.TurmsNotification parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.notification.TurmsNotification parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.notification.TurmsNotification parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.notification.TurmsNotification parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static im.turms.client.model.proto.notification.TurmsNotification parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static im.turms.client.model.proto.notification.TurmsNotification parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageLite
                .parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(
            im.turms.client.model.proto.notification.TurmsNotification prototype) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /**
     * Protobuf type {@code im.turms.proto.TurmsNotification}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessageLite.Builder<im.turms.client.model.proto.notification.TurmsNotification, Builder>
            implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.TurmsNotification)
            im.turms.client.model.proto.notification.TurmsNotificationOrBuilder {
        // Construct using im.turms.client.model.proto.notification.TurmsNotification.newBuilder()
        private Builder() {
            super(DEFAULT_INSTANCE);
        }

        /**
         * <pre>
         * Common =&gt; [1, 3]
         * </pre>
         * 
         * <code>int64 timestamp = 1;</code>
         *
         * @return The timestamp.
         */
        @java.lang.Override
        public long getTimestamp() {
            return instance.getTimestamp();
        }

        /**
         * <pre>
         * Common =&gt; [1, 3]
         * </pre>
         * 
         * <code>int64 timestamp = 1;</code>
         *
         * @param value The timestamp to set.
         * @return This builder for chaining.
         */
        public Builder setTimestamp(long value) {
            copyOnWrite();
            instance.setTimestamp(value);
            return this;
        }

        /**
         * <pre>
         * Common =&gt; [1, 3]
         * </pre>
         * 
         * <code>int64 timestamp = 1;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearTimestamp() {
            copyOnWrite();
            instance.clearTimestamp();
            return this;
        }

        /**
         * <pre>
         * Response =&gt; [4, 9]
         * "request_id" is used to tell the client that
         * this notification is a response to the specific request
         * </pre>
         * 
         * <code>optional int64 request_id = 4;</code>
         *
         * @return Whether the requestId field is set.
         */
        @java.lang.Override
        public boolean hasRequestId() {
            return instance.hasRequestId();
        }

        /**
         * <pre>
         * Response =&gt; [4, 9]
         * "request_id" is used to tell the client that
         * this notification is a response to the specific request
         * </pre>
         * 
         * <code>optional int64 request_id = 4;</code>
         *
         * @return The requestId.
         */
        @java.lang.Override
        public long getRequestId() {
            return instance.getRequestId();
        }

        /**
         * <pre>
         * Response =&gt; [4, 9]
         * "request_id" is used to tell the client that
         * this notification is a response to the specific request
         * </pre>
         * 
         * <code>optional int64 request_id = 4;</code>
         *
         * @param value The requestId to set.
         * @return This builder for chaining.
         */
        public Builder setRequestId(long value) {
            copyOnWrite();
            instance.setRequestId(value);
            return this;
        }

        /**
         * <pre>
         * Response =&gt; [4, 9]
         * "request_id" is used to tell the client that
         * this notification is a response to the specific request
         * </pre>
         * 
         * <code>optional int64 request_id = 4;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRequestId() {
            copyOnWrite();
            instance.clearRequestId();
            return this;
        }

        /**
         * <code>optional int32 code = 5;</code>
         *
         * @return Whether the code field is set.
         */
        @java.lang.Override
        public boolean hasCode() {
            return instance.hasCode();
        }

        /**
         * <code>optional int32 code = 5;</code>
         *
         * @return The code.
         */
        @java.lang.Override
        public int getCode() {
            return instance.getCode();
        }

        /**
         * <code>optional int32 code = 5;</code>
         *
         * @param value The code to set.
         * @return This builder for chaining.
         */
        public Builder setCode(int value) {
            copyOnWrite();
            instance.setCode(value);
            return this;
        }

        /**
         * <code>optional int32 code = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCode() {
            copyOnWrite();
            instance.clearCode();
            return this;
        }

        /**
         * <code>optional string reason = 6;</code>
         *
         * @return Whether the reason field is set.
         */
        @java.lang.Override
        public boolean hasReason() {
            return instance.hasReason();
        }

        /**
         * <code>optional string reason = 6;</code>
         *
         * @return The reason.
         */
        @java.lang.Override
        public java.lang.String getReason() {
            return instance.getReason();
        }

        /**
         * <code>optional string reason = 6;</code>
         *
         * @return The bytes for reason.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getReasonBytes() {
            return instance.getReasonBytes();
        }

        /**
         * <code>optional string reason = 6;</code>
         *
         * @param value The reason to set.
         * @return This builder for chaining.
         */
        public Builder setReason(java.lang.String value) {
            copyOnWrite();
            instance.setReason(value);
            return this;
        }

        /**
         * <code>optional string reason = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReason() {
            copyOnWrite();
            instance.clearReason();
            return this;
        }

        /**
         * <code>optional string reason = 6;</code>
         *
         * @param value The bytes for reason to set.
         * @return This builder for chaining.
         */
        public Builder setReasonBytes(com.google.protobuf.ByteString value) {
            copyOnWrite();
            instance.setReasonBytes(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        @java.lang.Override
        public boolean hasData() {
            return instance.hasData();
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.notification.TurmsNotification.Data getData() {
            return instance.getData();
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        public Builder setData(
                im.turms.client.model.proto.notification.TurmsNotification.Data value) {
            copyOnWrite();
            instance.setData(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        public Builder setData(
                im.turms.client.model.proto.notification.TurmsNotification.Data.Builder builderForValue) {
            copyOnWrite();
            instance.setData(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        public Builder mergeData(
                im.turms.client.model.proto.notification.TurmsNotification.Data value) {
            copyOnWrite();
            instance.mergeData(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        public Builder clearData() {
            copyOnWrite();
            instance.clearData();
            return this;
        }

        /**
         * <pre>
         * Notification =&gt; [10, 15]
         * "requester_id" only exists when a requester triggers a notification to its recipients
         * Note: Do not move "requester_id" to TurmsRequest because it requires rebuilding
         * a new TurmsNotification when recipients need "requester_id".
         * </pre>
         * 
         * <code>optional int64 requester_id = 10;</code>
         *
         * @return Whether the requesterId field is set.
         */
        @java.lang.Override
        public boolean hasRequesterId() {
            return instance.hasRequesterId();
        }

        /**
         * <pre>
         * Notification =&gt; [10, 15]
         * "requester_id" only exists when a requester triggers a notification to its recipients
         * Note: Do not move "requester_id" to TurmsRequest because it requires rebuilding
         * a new TurmsNotification when recipients need "requester_id".
         * </pre>
         * 
         * <code>optional int64 requester_id = 10;</code>
         *
         * @return The requesterId.
         */
        @java.lang.Override
        public long getRequesterId() {
            return instance.getRequesterId();
        }

        /**
         * <pre>
         * Notification =&gt; [10, 15]
         * "requester_id" only exists when a requester triggers a notification to its recipients
         * Note: Do not move "requester_id" to TurmsRequest because it requires rebuilding
         * a new TurmsNotification when recipients need "requester_id".
         * </pre>
         * 
         * <code>optional int64 requester_id = 10;</code>
         *
         * @param value The requesterId to set.
         * @return This builder for chaining.
         */
        public Builder setRequesterId(long value) {
            copyOnWrite();
            instance.setRequesterId(value);
            return this;
        }

        /**
         * <pre>
         * Notification =&gt; [10, 15]
         * "requester_id" only exists when a requester triggers a notification to its recipients
         * Note: Do not move "requester_id" to TurmsRequest because it requires rebuilding
         * a new TurmsNotification when recipients need "requester_id".
         * </pre>
         * 
         * <code>optional int64 requester_id = 10;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearRequesterId() {
            copyOnWrite();
            instance.clearRequesterId();
            return this;
        }

        /**
         * <code>optional int32 close_status = 11;</code>
         *
         * @return Whether the closeStatus field is set.
         */
        @java.lang.Override
        public boolean hasCloseStatus() {
            return instance.hasCloseStatus();
        }

        /**
         * <code>optional int32 close_status = 11;</code>
         *
         * @return The closeStatus.
         */
        @java.lang.Override
        public int getCloseStatus() {
            return instance.getCloseStatus();
        }

        /**
         * <code>optional int32 close_status = 11;</code>
         *
         * @param value The closeStatus to set.
         * @return This builder for chaining.
         */
        public Builder setCloseStatus(int value) {
            copyOnWrite();
            instance.setCloseStatus(value);
            return this;
        }

        /**
         * <code>optional int32 close_status = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCloseStatus() {
            copyOnWrite();
            instance.clearCloseStatus();
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        @java.lang.Override
        public boolean hasRelayedRequest() {
            return instance.hasRelayedRequest();
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        @java.lang.Override
        public im.turms.client.model.proto.request.TurmsRequest getRelayedRequest() {
            return instance.getRelayedRequest();
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        public Builder setRelayedRequest(im.turms.client.model.proto.request.TurmsRequest value) {
            copyOnWrite();
            instance.setRelayedRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        public Builder setRelayedRequest(
                im.turms.client.model.proto.request.TurmsRequest.Builder builderForValue) {
            copyOnWrite();
            instance.setRelayedRequest(builderForValue.build());
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        public Builder mergeRelayedRequest(im.turms.client.model.proto.request.TurmsRequest value) {
            copyOnWrite();
            instance.mergeRelayedRequest(value);
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        public Builder clearRelayedRequest() {
            copyOnWrite();
            instance.clearRelayedRequest();
            return this;
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.TurmsNotification)
    }

    @java.lang.Override
    @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
    protected final java.lang.Object dynamicMethod(
            com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
            java.lang.Object arg0,
            java.lang.Object arg1) {
        switch (method) {
            case NEW_MUTABLE_INSTANCE: {
                return new im.turms.client.model.proto.notification.TurmsNotification();
            }
            case NEW_BUILDER: {
                return new Builder();
            }
            case BUILD_MESSAGE_INFO: {
                java.lang.Object[] objects = new java.lang.Object[]{"bitField0_",
                        "timestamp_",
                        "requestId_",
                        "code_",
                        "reason_",
                        "data_",
                        "requesterId_",
                        "closeStatus_",
                        "relayedRequest_",};
                java.lang.String info =
                        "\u0000\b\u0000\u0001\u0001\f\b\u0000\u0000\u0000\u0001\u0002\u0004\u1002\u0000\u0005"
                                + "\u1004\u0001\u0006\u1208\u0002\u0007\t\n\u1002\u0003\u000b\u1004\u0004\f\t";
                return newMessageInfo(DEFAULT_INSTANCE, info, objects);
            }
            // fall through
            case GET_DEFAULT_INSTANCE: {
                return DEFAULT_INSTANCE;
            }
            case GET_PARSER: {
                com.google.protobuf.Parser<im.turms.client.model.proto.notification.TurmsNotification> parser =
                        PARSER;
                if (parser == null) {
                    synchronized (im.turms.client.model.proto.notification.TurmsNotification.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            }
            case GET_MEMOIZED_IS_INITIALIZED: {
                return (byte) 1;
            }
            case SET_MEMOIZED_IS_INITIALIZED: {
                return null;
            }
        }
        throw new UnsupportedOperationException();
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.TurmsNotification)
    private static final im.turms.client.model.proto.notification.TurmsNotification DEFAULT_INSTANCE;

    static {
        TurmsNotification defaultInstance = new TurmsNotification();
        // New instances are implicitly immutable so no need to make
        // immutable.
        DEFAULT_INSTANCE = defaultInstance;
        com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(TurmsNotification.class,
                defaultInstance);
    }

    public static im.turms.client.model.proto.notification.TurmsNotification getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<TurmsNotification> PARSER;

    public static com.google.protobuf.Parser<TurmsNotification> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
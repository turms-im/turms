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

package im.turms.server.common.access.client.dto.notification;

/**
 * Protobuf type {@code im.turms.proto.TurmsNotification}
 */
public final class TurmsNotification extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:im.turms.proto.TurmsNotification)
        TurmsNotificationOrBuilder {
    private static final long serialVersionUID = 0L;

    // Use TurmsNotification.newBuilder() to construct.
    private TurmsNotification(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private TurmsNotification() {
        reason_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
        return new TurmsNotification();
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return im.turms.server.common.access.client.dto.notification.TurmsNotificationOuterClass.internal_static_im_turms_proto_TurmsNotification_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return im.turms.server.common.access.client.dto.notification.TurmsNotificationOuterClass.internal_static_im_turms_proto_TurmsNotification_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        im.turms.server.common.access.client.dto.notification.TurmsNotification.class,
                        im.turms.server.common.access.client.dto.notification.TurmsNotification.Builder.class);
    }

    public interface DataOrBuilder extends
            // @@protoc_insertion_point(interface_extends:im.turms.proto.TurmsNotification.Data)
            com.google.protobuf.MessageOrBuilder {

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
        im.turms.server.common.access.client.dto.model.common.LongsWithVersion getLongsWithVersion();

        /**
         * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
         */
        im.turms.server.common.access.client.dto.model.common.LongsWithVersionOrBuilder getLongsWithVersionOrBuilder();

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
        im.turms.server.common.access.client.dto.model.common.StringsWithVersion getStringsWithVersion();

        /**
         * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
         */
        im.turms.server.common.access.client.dto.model.common.StringsWithVersionOrBuilder getStringsWithVersionOrBuilder();

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
        im.turms.server.common.access.client.dto.model.conversation.Conversations getConversations();

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.Conversations conversations = 5;</code>
         */
        im.turms.server.common.access.client.dto.model.conversation.ConversationsOrBuilder getConversationsOrBuilder();

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
        im.turms.server.common.access.client.dto.model.message.Messages getMessages();

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.Messages messages = 6;</code>
         */
        im.turms.server.common.access.client.dto.model.message.MessagesOrBuilder getMessagesOrBuilder();

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
        im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList getMessagesWithTotalList();

        /**
         * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
         */
        im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOrBuilder getMessagesWithTotalListOrBuilder();

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
        im.turms.server.common.access.client.dto.model.user.UserSession getUserSession();

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.UserSession user_session = 8;</code>
         */
        im.turms.server.common.access.client.dto.model.user.UserSessionOrBuilder getUserSessionOrBuilder();

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
        im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion getUserInfosWithVersion();

        /**
         * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
         */
        im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOrBuilder getUserInfosWithVersionOrBuilder();

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
        im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses getUserOnlineStatuses();

        /**
         * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
         */
        im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOrBuilder getUserOnlineStatusesOrBuilder();

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
        im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion getUserFriendRequestsWithVersion();

        /**
         * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
         */
        im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOrBuilder getUserFriendRequestsWithVersionOrBuilder();

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
        im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion getUserRelationshipGroupsWithVersion();

        /**
         * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
         */
        im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOrBuilder getUserRelationshipGroupsWithVersionOrBuilder();

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
        im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion getUserRelationshipsWithVersion();

        /**
         * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
         */
        im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOrBuilder getUserRelationshipsWithVersionOrBuilder();

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
        im.turms.server.common.access.client.dto.model.user.NearbyUsers getNearbyUsers();

        /**
         * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
         */
        im.turms.server.common.access.client.dto.model.user.NearbyUsersOrBuilder getNearbyUsersOrBuilder();

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
        im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion getGroupInvitationsWithVersion();

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
         */
        im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOrBuilder getGroupInvitationsWithVersionOrBuilder();

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
        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult getGroupJoinQuestionAnswerResult();

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
         */
        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOrBuilder getGroupJoinQuestionAnswerResultOrBuilder();

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
        im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion getGroupJoinRequestsWithVersion();

        /**
         * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
         */
        im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOrBuilder getGroupJoinRequestsWithVersionOrBuilder();

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
        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion getGroupJoinQuestionsWithVersion();

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
         */
        im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOrBuilder getGroupJoinQuestionsWithVersionOrBuilder();

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
        im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion getGroupMembersWithVersion();

        /**
         * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
         */
        im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOrBuilder getGroupMembersWithVersionOrBuilder();

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
        im.turms.server.common.access.client.dto.model.group.GroupsWithVersion getGroupsWithVersion();

        /**
         * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
         */
        im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOrBuilder getGroupsWithVersionOrBuilder();

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
        im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos getStorageResourceInfos();

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
         */
        im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOrBuilder getStorageResourceInfosOrBuilder();

        im.turms.server.common.access.client.dto.notification.TurmsNotification.Data.KindCase getKindCase();
    }

    /**
     * Protobuf type {@code im.turms.proto.TurmsNotification.Data}
     */
    public static final class Data extends com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:im.turms.proto.TurmsNotification.Data)
            DataOrBuilder {
        private static final long serialVersionUID = 0L;

        // Use Data.newBuilder() to construct.
        private Data(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }

        private Data() {
        }

        @java.lang.Override
        @SuppressWarnings({"unused"})
        protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
            return new Data();
        }

        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.notification.TurmsNotificationOuterClass.internal_static_im_turms_proto_TurmsNotification_Data_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.notification.TurmsNotificationOuterClass.internal_static_im_turms_proto_TurmsNotification_Data_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.notification.TurmsNotification.Data.class,
                            im.turms.server.common.access.client.dto.notification.TurmsNotification.Data.Builder.class);
        }

        private int kindCase_ = 0;
        @SuppressWarnings("serial")
        private java.lang.Object kind_;

        public enum KindCase implements com.google.protobuf.Internal.EnumLite,
                com.google.protobuf.AbstractMessage.InternalOneOfEnum {
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
             * @param value The number of the enum to look for.
             * @return The enum associated with the given number.
             * @deprecated Use {@link #forNumber(int)} instead.
             */
            @java.lang.Deprecated
            public static KindCase valueOf(int value) {
                return forNumber(value);
            }

            public static KindCase forNumber(int value) {
                return switch (value) {
                    case 1 -> LONG;
                    case 2 -> STRING;
                    case 3 -> LONGS_WITH_VERSION;
                    case 4 -> STRINGS_WITH_VERSION;
                    case 5 -> CONVERSATIONS;
                    case 6 -> MESSAGES;
                    case 7 -> MESSAGES_WITH_TOTAL_LIST;
                    case 8 -> USER_SESSION;
                    case 9 -> USER_INFOS_WITH_VERSION;
                    case 10 -> USER_ONLINE_STATUSES;
                    case 11 -> USER_FRIEND_REQUESTS_WITH_VERSION;
                    case 12 -> USER_RELATIONSHIP_GROUPS_WITH_VERSION;
                    case 13 -> USER_RELATIONSHIPS_WITH_VERSION;
                    case 14 -> NEARBY_USERS;
                    case 15 -> GROUP_INVITATIONS_WITH_VERSION;
                    case 16 -> GROUP_JOIN_QUESTION_ANSWER_RESULT;
                    case 17 -> GROUP_JOIN_REQUESTS_WITH_VERSION;
                    case 18 -> GROUP_JOIN_QUESTIONS_WITH_VERSION;
                    case 19 -> GROUP_MEMBERS_WITH_VERSION;
                    case 20 -> GROUPS_WITH_VERSION;
                    case 50 -> STORAGE_RESOURCE_INFOS;
                    case 0 -> KIND_NOT_SET;
                    default -> null;
                };
            }

            public int getNumber() {
                return this.value;
            }
        }

        public KindCase getKindCase() {
            return KindCase.forNumber(kindCase_);
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

        public static final int STRING_FIELD_NUMBER = 2;

        /**
         * <code>string string = 2;</code>
         *
         * @return Whether the string field is set.
         */
        public boolean hasString() {
            return kindCase_ == 2;
        }

        /**
         * <code>string string = 2;</code>
         *
         * @return The string.
         */
        public java.lang.String getString() {
            java.lang.Object ref = "";
            if (kindCase_ == 2) {
                ref = kind_;
            }
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                if (kindCase_ == 2) {
                    kind_ = s;
                }
                return s;
            }
        }

        /**
         * <code>string string = 2;</code>
         *
         * @return The bytes for string.
         */
        public com.google.protobuf.ByteString getStringBytes() {
            java.lang.Object ref = "";
            if (kindCase_ == 2) {
                ref = kind_;
            }
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                if (kindCase_ == 2) {
                    kind_ = b;
                }
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        public static final int LONGS_WITH_VERSION_FIELD_NUMBER = 3;

        /**
         * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
         *
         * @return Whether the longsWithVersion field is set.
         */
        @java.lang.Override
        public boolean hasLongsWithVersion() {
            return kindCase_ == 3;
        }

        /**
         * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
         *
         * @return The longsWithVersion.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.common.LongsWithVersion getLongsWithVersion() {
            if (kindCase_ == 3) {
                return (im.turms.server.common.access.client.dto.model.common.LongsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.common.LongsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.common.LongsWithVersionOrBuilder getLongsWithVersionOrBuilder() {
            if (kindCase_ == 3) {
                return (im.turms.server.common.access.client.dto.model.common.LongsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.common.LongsWithVersion
                    .getDefaultInstance();
        }

        public static final int STRINGS_WITH_VERSION_FIELD_NUMBER = 4;

        /**
         * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
         *
         * @return Whether the stringsWithVersion field is set.
         */
        @java.lang.Override
        public boolean hasStringsWithVersion() {
            return kindCase_ == 4;
        }

        /**
         * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
         *
         * @return The stringsWithVersion.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.common.StringsWithVersion getStringsWithVersion() {
            if (kindCase_ == 4) {
                return (im.turms.server.common.access.client.dto.model.common.StringsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.common.StringsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.common.StringsWithVersionOrBuilder getStringsWithVersionOrBuilder() {
            if (kindCase_ == 4) {
                return (im.turms.server.common.access.client.dto.model.common.StringsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.common.StringsWithVersion
                    .getDefaultInstance();
        }

        public static final int CONVERSATIONS_FIELD_NUMBER = 5;

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.Conversations conversations = 5;</code>
         *
         * @return Whether the conversations field is set.
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
         *
         * @return The conversations.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.Conversations getConversations() {
            if (kindCase_ == 5) {
                return (im.turms.server.common.access.client.dto.model.conversation.Conversations) kind_;
            }
            return im.turms.server.common.access.client.dto.model.conversation.Conversations
                    .getDefaultInstance();
        }

        /**
         * <pre>
         * Conversation
         * </pre>
         * 
         * <code>.im.turms.proto.Conversations conversations = 5;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.conversation.ConversationsOrBuilder getConversationsOrBuilder() {
            if (kindCase_ == 5) {
                return (im.turms.server.common.access.client.dto.model.conversation.Conversations) kind_;
            }
            return im.turms.server.common.access.client.dto.model.conversation.Conversations
                    .getDefaultInstance();
        }

        public static final int MESSAGES_FIELD_NUMBER = 6;

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.Messages messages = 6;</code>
         *
         * @return Whether the messages field is set.
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
         *
         * @return The messages.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.Messages getMessages() {
            if (kindCase_ == 6) {
                return (im.turms.server.common.access.client.dto.model.message.Messages) kind_;
            }
            return im.turms.server.common.access.client.dto.model.message.Messages
                    .getDefaultInstance();
        }

        /**
         * <pre>
         * Message
         * </pre>
         * 
         * <code>.im.turms.proto.Messages messages = 6;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.MessagesOrBuilder getMessagesOrBuilder() {
            if (kindCase_ == 6) {
                return (im.turms.server.common.access.client.dto.model.message.Messages) kind_;
            }
            return im.turms.server.common.access.client.dto.model.message.Messages
                    .getDefaultInstance();
        }

        public static final int MESSAGES_WITH_TOTAL_LIST_FIELD_NUMBER = 7;

        /**
         * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
         *
         * @return Whether the messagesWithTotalList field is set.
         */
        @java.lang.Override
        public boolean hasMessagesWithTotalList() {
            return kindCase_ == 7;
        }

        /**
         * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
         *
         * @return The messagesWithTotalList.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList getMessagesWithTotalList() {
            if (kindCase_ == 7) {
                return (im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList) kind_;
            }
            return im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOrBuilder getMessagesWithTotalListOrBuilder() {
            if (kindCase_ == 7) {
                return (im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList) kind_;
            }
            return im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList
                    .getDefaultInstance();
        }

        public static final int USER_SESSION_FIELD_NUMBER = 8;

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.UserSession user_session = 8;</code>
         *
         * @return Whether the userSession field is set.
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
         *
         * @return The userSession.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserSession getUserSession() {
            if (kindCase_ == 8) {
                return (im.turms.server.common.access.client.dto.model.user.UserSession) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.UserSession
                    .getDefaultInstance();
        }

        /**
         * <pre>
         * User
         * </pre>
         * 
         * <code>.im.turms.proto.UserSession user_session = 8;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserSessionOrBuilder getUserSessionOrBuilder() {
            if (kindCase_ == 8) {
                return (im.turms.server.common.access.client.dto.model.user.UserSession) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.UserSession
                    .getDefaultInstance();
        }

        public static final int USER_INFOS_WITH_VERSION_FIELD_NUMBER = 9;

        /**
         * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
         *
         * @return Whether the userInfosWithVersion field is set.
         */
        @java.lang.Override
        public boolean hasUserInfosWithVersion() {
            return kindCase_ == 9;
        }

        /**
         * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
         *
         * @return The userInfosWithVersion.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion getUserInfosWithVersion() {
            if (kindCase_ == 9) {
                return (im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOrBuilder getUserInfosWithVersionOrBuilder() {
            if (kindCase_ == 9) {
                return (im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion
                    .getDefaultInstance();
        }

        public static final int USER_ONLINE_STATUSES_FIELD_NUMBER = 10;

        /**
         * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
         *
         * @return Whether the userOnlineStatuses field is set.
         */
        @java.lang.Override
        public boolean hasUserOnlineStatuses() {
            return kindCase_ == 10;
        }

        /**
         * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
         *
         * @return The userOnlineStatuses.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses getUserOnlineStatuses() {
            if (kindCase_ == 10) {
                return (im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOrBuilder getUserOnlineStatusesOrBuilder() {
            if (kindCase_ == 10) {
                return (im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses
                    .getDefaultInstance();
        }

        public static final int USER_FRIEND_REQUESTS_WITH_VERSION_FIELD_NUMBER = 11;

        /**
         * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
         *
         * @return Whether the userFriendRequestsWithVersion field is set.
         */
        @java.lang.Override
        public boolean hasUserFriendRequestsWithVersion() {
            return kindCase_ == 11;
        }

        /**
         * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
         *
         * @return The userFriendRequestsWithVersion.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion getUserFriendRequestsWithVersion() {
            if (kindCase_ == 11) {
                return (im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOrBuilder getUserFriendRequestsWithVersionOrBuilder() {
            if (kindCase_ == 11) {
                return (im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion
                    .getDefaultInstance();
        }

        public static final int USER_RELATIONSHIP_GROUPS_WITH_VERSION_FIELD_NUMBER = 12;

        /**
         * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
         *
         * @return Whether the userRelationshipGroupsWithVersion field is set.
         */
        @java.lang.Override
        public boolean hasUserRelationshipGroupsWithVersion() {
            return kindCase_ == 12;
        }

        /**
         * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
         *
         * @return The userRelationshipGroupsWithVersion.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion getUserRelationshipGroupsWithVersion() {
            if (kindCase_ == 12) {
                return (im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOrBuilder getUserRelationshipGroupsWithVersionOrBuilder() {
            if (kindCase_ == 12) {
                return (im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion
                    .getDefaultInstance();
        }

        public static final int USER_RELATIONSHIPS_WITH_VERSION_FIELD_NUMBER = 13;

        /**
         * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
         *
         * @return Whether the userRelationshipsWithVersion field is set.
         */
        @java.lang.Override
        public boolean hasUserRelationshipsWithVersion() {
            return kindCase_ == 13;
        }

        /**
         * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
         *
         * @return The userRelationshipsWithVersion.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion getUserRelationshipsWithVersion() {
            if (kindCase_ == 13) {
                return (im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOrBuilder getUserRelationshipsWithVersionOrBuilder() {
            if (kindCase_ == 13) {
                return (im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion
                    .getDefaultInstance();
        }

        public static final int NEARBY_USERS_FIELD_NUMBER = 14;

        /**
         * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
         *
         * @return Whether the nearbyUsers field is set.
         */
        @java.lang.Override
        public boolean hasNearbyUsers() {
            return kindCase_ == 14;
        }

        /**
         * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
         *
         * @return The nearbyUsers.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.NearbyUsers getNearbyUsers() {
            if (kindCase_ == 14) {
                return (im.turms.server.common.access.client.dto.model.user.NearbyUsers) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.NearbyUsers
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.user.NearbyUsersOrBuilder getNearbyUsersOrBuilder() {
            if (kindCase_ == 14) {
                return (im.turms.server.common.access.client.dto.model.user.NearbyUsers) kind_;
            }
            return im.turms.server.common.access.client.dto.model.user.NearbyUsers
                    .getDefaultInstance();
        }

        public static final int GROUP_INVITATIONS_WITH_VERSION_FIELD_NUMBER = 15;

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
         *
         * @return Whether the groupInvitationsWithVersion field is set.
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
         *
         * @return The groupInvitationsWithVersion.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion getGroupInvitationsWithVersion() {
            if (kindCase_ == 15) {
                return (im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <pre>
         * Group
         * </pre>
         * 
         * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOrBuilder getGroupInvitationsWithVersionOrBuilder() {
            if (kindCase_ == 15) {
                return (im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion
                    .getDefaultInstance();
        }

        public static final int GROUP_JOIN_QUESTION_ANSWER_RESULT_FIELD_NUMBER = 16;

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
         *
         * @return Whether the groupJoinQuestionAnswerResult field is set.
         */
        @java.lang.Override
        public boolean hasGroupJoinQuestionAnswerResult() {
            return kindCase_ == 16;
        }

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
         *
         * @return The groupJoinQuestionAnswerResult.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult getGroupJoinQuestionAnswerResult() {
            if (kindCase_ == 16) {
                return (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult) kind_;
            }
            return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOrBuilder getGroupJoinQuestionAnswerResultOrBuilder() {
            if (kindCase_ == 16) {
                return (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult) kind_;
            }
            return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult
                    .getDefaultInstance();
        }

        public static final int GROUP_JOIN_REQUESTS_WITH_VERSION_FIELD_NUMBER = 17;

        /**
         * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
         *
         * @return Whether the groupJoinRequestsWithVersion field is set.
         */
        @java.lang.Override
        public boolean hasGroupJoinRequestsWithVersion() {
            return kindCase_ == 17;
        }

        /**
         * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
         *
         * @return The groupJoinRequestsWithVersion.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion getGroupJoinRequestsWithVersion() {
            if (kindCase_ == 17) {
                return (im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOrBuilder getGroupJoinRequestsWithVersionOrBuilder() {
            if (kindCase_ == 17) {
                return (im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion
                    .getDefaultInstance();
        }

        public static final int GROUP_JOIN_QUESTIONS_WITH_VERSION_FIELD_NUMBER = 18;

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
         *
         * @return Whether the groupJoinQuestionsWithVersion field is set.
         */
        @java.lang.Override
        public boolean hasGroupJoinQuestionsWithVersion() {
            return kindCase_ == 18;
        }

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
         *
         * @return The groupJoinQuestionsWithVersion.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion getGroupJoinQuestionsWithVersion() {
            if (kindCase_ == 18) {
                return (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOrBuilder getGroupJoinQuestionsWithVersionOrBuilder() {
            if (kindCase_ == 18) {
                return (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion
                    .getDefaultInstance();
        }

        public static final int GROUP_MEMBERS_WITH_VERSION_FIELD_NUMBER = 19;

        /**
         * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
         *
         * @return Whether the groupMembersWithVersion field is set.
         */
        @java.lang.Override
        public boolean hasGroupMembersWithVersion() {
            return kindCase_ == 19;
        }

        /**
         * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
         *
         * @return The groupMembersWithVersion.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion getGroupMembersWithVersion() {
            if (kindCase_ == 19) {
                return (im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOrBuilder getGroupMembersWithVersionOrBuilder() {
            if (kindCase_ == 19) {
                return (im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion
                    .getDefaultInstance();
        }

        public static final int GROUPS_WITH_VERSION_FIELD_NUMBER = 20;

        /**
         * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
         *
         * @return Whether the groupsWithVersion field is set.
         */
        @java.lang.Override
        public boolean hasGroupsWithVersion() {
            return kindCase_ == 20;
        }

        /**
         * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
         *
         * @return The groupsWithVersion.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupsWithVersion getGroupsWithVersion() {
            if (kindCase_ == 20) {
                return (im.turms.server.common.access.client.dto.model.group.GroupsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.group.GroupsWithVersion
                    .getDefaultInstance();
        }

        /**
         * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOrBuilder getGroupsWithVersionOrBuilder() {
            if (kindCase_ == 20) {
                return (im.turms.server.common.access.client.dto.model.group.GroupsWithVersion) kind_;
            }
            return im.turms.server.common.access.client.dto.model.group.GroupsWithVersion
                    .getDefaultInstance();
        }

        public static final int STORAGE_RESOURCE_INFOS_FIELD_NUMBER = 50;

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
         *
         * @return Whether the storageResourceInfos field is set.
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
         *
         * @return The storageResourceInfos.
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos getStorageResourceInfos() {
            if (kindCase_ == 50) {
                return (im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos) kind_;
            }
            return im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos
                    .getDefaultInstance();
        }

        /**
         * <pre>
         * Storage
         * </pre>
         * 
         * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
         */
        @java.lang.Override
        public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOrBuilder getStorageResourceInfosOrBuilder() {
            if (kindCase_ == 50) {
                return (im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos) kind_;
            }
            return im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos
                    .getDefaultInstance();
        }

        private byte memoizedIsInitialized = -1;

        @java.lang.Override
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }

            memoizedIsInitialized = 1;
            return true;
        }

        @java.lang.Override
        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (kindCase_ == 1) {
                output.writeInt64(1, (long) ((java.lang.Long) kind_));
            }
            if (kindCase_ == 2) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, kind_);
            }
            if (kindCase_ == 3) {
                output.writeMessage(3,
                        (im.turms.server.common.access.client.dto.model.common.LongsWithVersion) kind_);
            }
            if (kindCase_ == 4) {
                output.writeMessage(4,
                        (im.turms.server.common.access.client.dto.model.common.StringsWithVersion) kind_);
            }
            if (kindCase_ == 5) {
                output.writeMessage(5,
                        (im.turms.server.common.access.client.dto.model.conversation.Conversations) kind_);
            }
            if (kindCase_ == 6) {
                output.writeMessage(6,
                        (im.turms.server.common.access.client.dto.model.message.Messages) kind_);
            }
            if (kindCase_ == 7) {
                output.writeMessage(7,
                        (im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList) kind_);
            }
            if (kindCase_ == 8) {
                output.writeMessage(8,
                        (im.turms.server.common.access.client.dto.model.user.UserSession) kind_);
            }
            if (kindCase_ == 9) {
                output.writeMessage(9,
                        (im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion) kind_);
            }
            if (kindCase_ == 10) {
                output.writeMessage(10,
                        (im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses) kind_);
            }
            if (kindCase_ == 11) {
                output.writeMessage(11,
                        (im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion) kind_);
            }
            if (kindCase_ == 12) {
                output.writeMessage(12,
                        (im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion) kind_);
            }
            if (kindCase_ == 13) {
                output.writeMessage(13,
                        (im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion) kind_);
            }
            if (kindCase_ == 14) {
                output.writeMessage(14,
                        (im.turms.server.common.access.client.dto.model.user.NearbyUsers) kind_);
            }
            if (kindCase_ == 15) {
                output.writeMessage(15,
                        (im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion) kind_);
            }
            if (kindCase_ == 16) {
                output.writeMessage(16,
                        (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult) kind_);
            }
            if (kindCase_ == 17) {
                output.writeMessage(17,
                        (im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion) kind_);
            }
            if (kindCase_ == 18) {
                output.writeMessage(18,
                        (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion) kind_);
            }
            if (kindCase_ == 19) {
                output.writeMessage(19,
                        (im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion) kind_);
            }
            if (kindCase_ == 20) {
                output.writeMessage(20,
                        (im.turms.server.common.access.client.dto.model.group.GroupsWithVersion) kind_);
            }
            if (kindCase_ == 50) {
                output.writeMessage(50,
                        (im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos) kind_);
            }
            getUnknownFields().writeTo(output);
        }

        @java.lang.Override
        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) {
                return size;
            }

            size = 0;
            if (kindCase_ == 1) {
                size += com.google.protobuf.CodedOutputStream.computeInt64Size(1,
                        (long) ((java.lang.Long) kind_));
            }
            if (kindCase_ == 2) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, kind_);
            }
            if (kindCase_ == 3) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(3,
                        (im.turms.server.common.access.client.dto.model.common.LongsWithVersion) kind_);
            }
            if (kindCase_ == 4) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(4,
                        (im.turms.server.common.access.client.dto.model.common.StringsWithVersion) kind_);
            }
            if (kindCase_ == 5) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(5,
                        (im.turms.server.common.access.client.dto.model.conversation.Conversations) kind_);
            }
            if (kindCase_ == 6) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(6,
                        (im.turms.server.common.access.client.dto.model.message.Messages) kind_);
            }
            if (kindCase_ == 7) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(7,
                        (im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList) kind_);
            }
            if (kindCase_ == 8) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(8,
                        (im.turms.server.common.access.client.dto.model.user.UserSession) kind_);
            }
            if (kindCase_ == 9) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(9,
                        (im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion) kind_);
            }
            if (kindCase_ == 10) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(10,
                        (im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses) kind_);
            }
            if (kindCase_ == 11) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(11,
                        (im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion) kind_);
            }
            if (kindCase_ == 12) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(12,
                        (im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion) kind_);
            }
            if (kindCase_ == 13) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(13,
                        (im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion) kind_);
            }
            if (kindCase_ == 14) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(14,
                        (im.turms.server.common.access.client.dto.model.user.NearbyUsers) kind_);
            }
            if (kindCase_ == 15) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(15,
                        (im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion) kind_);
            }
            if (kindCase_ == 16) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(16,
                        (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult) kind_);
            }
            if (kindCase_ == 17) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(17,
                        (im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion) kind_);
            }
            if (kindCase_ == 18) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(18,
                        (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion) kind_);
            }
            if (kindCase_ == 19) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(19,
                        (im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion) kind_);
            }
            if (kindCase_ == 20) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(20,
                        (im.turms.server.common.access.client.dto.model.group.GroupsWithVersion) kind_);
            }
            if (kindCase_ == 50) {
                size += com.google.protobuf.CodedOutputStream.computeMessageSize(50,
                        (im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos) kind_);
            }
            size += getUnknownFields().getSerializedSize();
            memoizedSize = size;
            return size;
        }

        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Data other)) {
                return super.equals(obj);
            }

            if (!getKindCase().equals(other.getKindCase())) {
                return false;
            }
            switch (kindCase_) {
                case 1 -> {
                    if (getLong() != other.getLong()) {
                        return false;
                    }
                }
                case 2 -> {
                    if (!getString().equals(other.getString())) {
                        return false;
                    }
                }
                case 3 -> {
                    if (!getLongsWithVersion().equals(other.getLongsWithVersion())) {
                        return false;
                    }
                }
                case 4 -> {
                    if (!getStringsWithVersion().equals(other.getStringsWithVersion())) {
                        return false;
                    }
                }
                case 5 -> {
                    if (!getConversations().equals(other.getConversations())) {
                        return false;
                    }
                }
                case 6 -> {
                    if (!getMessages().equals(other.getMessages())) {
                        return false;
                    }
                }
                case 7 -> {
                    if (!getMessagesWithTotalList().equals(other.getMessagesWithTotalList())) {
                        return false;
                    }
                }
                case 8 -> {
                    if (!getUserSession().equals(other.getUserSession())) {
                        return false;
                    }
                }
                case 9 -> {
                    if (!getUserInfosWithVersion().equals(other.getUserInfosWithVersion())) {
                        return false;
                    }
                }
                case 10 -> {
                    if (!getUserOnlineStatuses().equals(other.getUserOnlineStatuses())) {
                        return false;
                    }
                }
                case 11 -> {
                    if (!getUserFriendRequestsWithVersion()
                            .equals(other.getUserFriendRequestsWithVersion())) {
                        return false;
                    }
                }
                case 12 -> {
                    if (!getUserRelationshipGroupsWithVersion()
                            .equals(other.getUserRelationshipGroupsWithVersion())) {
                        return false;
                    }
                }
                case 13 -> {
                    if (!getUserRelationshipsWithVersion()
                            .equals(other.getUserRelationshipsWithVersion())) {
                        return false;
                    }
                }
                case 14 -> {
                    if (!getNearbyUsers().equals(other.getNearbyUsers())) {
                        return false;
                    }
                }
                case 15 -> {
                    if (!getGroupInvitationsWithVersion()
                            .equals(other.getGroupInvitationsWithVersion())) {
                        return false;
                    }
                }
                case 16 -> {
                    if (!getGroupJoinQuestionAnswerResult()
                            .equals(other.getGroupJoinQuestionAnswerResult())) {
                        return false;
                    }
                }
                case 17 -> {
                    if (!getGroupJoinRequestsWithVersion()
                            .equals(other.getGroupJoinRequestsWithVersion())) {
                        return false;
                    }
                }
                case 18 -> {
                    if (!getGroupJoinQuestionsWithVersion()
                            .equals(other.getGroupJoinQuestionsWithVersion())) {
                        return false;
                    }
                }
                case 19 -> {
                    if (!getGroupMembersWithVersion().equals(other.getGroupMembersWithVersion())) {
                        return false;
                    }
                }
                case 20 -> {
                    if (!getGroupsWithVersion().equals(other.getGroupsWithVersion())) {
                        return false;
                    }
                }
                case 50 -> {
                    if (!getStorageResourceInfos().equals(other.getStorageResourceInfos())) {
                        return false;
                    }
                }
                case 0 -> {
                }
                default -> {
                }
            }
            return getUnknownFields().equals(other.getUnknownFields());
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptor().hashCode();
            switch (kindCase_) {
                case 1 -> {
                    hash = (37 * hash) + LONG_FIELD_NUMBER;
                    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getLong());
                }
                case 2 -> {
                    hash = (37 * hash) + STRING_FIELD_NUMBER;
                    hash = (53 * hash) + getString().hashCode();
                }
                case 3 -> {
                    hash = (37 * hash) + LONGS_WITH_VERSION_FIELD_NUMBER;
                    hash = (53 * hash) + getLongsWithVersion().hashCode();
                }
                case 4 -> {
                    hash = (37 * hash) + STRINGS_WITH_VERSION_FIELD_NUMBER;
                    hash = (53 * hash) + getStringsWithVersion().hashCode();
                }
                case 5 -> {
                    hash = (37 * hash) + CONVERSATIONS_FIELD_NUMBER;
                    hash = (53 * hash) + getConversations().hashCode();
                }
                case 6 -> {
                    hash = (37 * hash) + MESSAGES_FIELD_NUMBER;
                    hash = (53 * hash) + getMessages().hashCode();
                }
                case 7 -> {
                    hash = (37 * hash) + MESSAGES_WITH_TOTAL_LIST_FIELD_NUMBER;
                    hash = (53 * hash) + getMessagesWithTotalList().hashCode();
                }
                case 8 -> {
                    hash = (37 * hash) + USER_SESSION_FIELD_NUMBER;
                    hash = (53 * hash) + getUserSession().hashCode();
                }
                case 9 -> {
                    hash = (37 * hash) + USER_INFOS_WITH_VERSION_FIELD_NUMBER;
                    hash = (53 * hash) + getUserInfosWithVersion().hashCode();
                }
                case 10 -> {
                    hash = (37 * hash) + USER_ONLINE_STATUSES_FIELD_NUMBER;
                    hash = (53 * hash) + getUserOnlineStatuses().hashCode();
                }
                case 11 -> {
                    hash = (37 * hash) + USER_FRIEND_REQUESTS_WITH_VERSION_FIELD_NUMBER;
                    hash = (53 * hash) + getUserFriendRequestsWithVersion().hashCode();
                }
                case 12 -> {
                    hash = (37 * hash) + USER_RELATIONSHIP_GROUPS_WITH_VERSION_FIELD_NUMBER;
                    hash = (53 * hash) + getUserRelationshipGroupsWithVersion().hashCode();
                }
                case 13 -> {
                    hash = (37 * hash) + USER_RELATIONSHIPS_WITH_VERSION_FIELD_NUMBER;
                    hash = (53 * hash) + getUserRelationshipsWithVersion().hashCode();
                }
                case 14 -> {
                    hash = (37 * hash) + NEARBY_USERS_FIELD_NUMBER;
                    hash = (53 * hash) + getNearbyUsers().hashCode();
                }
                case 15 -> {
                    hash = (37 * hash) + GROUP_INVITATIONS_WITH_VERSION_FIELD_NUMBER;
                    hash = (53 * hash) + getGroupInvitationsWithVersion().hashCode();
                }
                case 16 -> {
                    hash = (37 * hash) + GROUP_JOIN_QUESTION_ANSWER_RESULT_FIELD_NUMBER;
                    hash = (53 * hash) + getGroupJoinQuestionAnswerResult().hashCode();
                }
                case 17 -> {
                    hash = (37 * hash) + GROUP_JOIN_REQUESTS_WITH_VERSION_FIELD_NUMBER;
                    hash = (53 * hash) + getGroupJoinRequestsWithVersion().hashCode();
                }
                case 18 -> {
                    hash = (37 * hash) + GROUP_JOIN_QUESTIONS_WITH_VERSION_FIELD_NUMBER;
                    hash = (53 * hash) + getGroupJoinQuestionsWithVersion().hashCode();
                }
                case 19 -> {
                    hash = (37 * hash) + GROUP_MEMBERS_WITH_VERSION_FIELD_NUMBER;
                    hash = (53 * hash) + getGroupMembersWithVersion().hashCode();
                }
                case 20 -> {
                    hash = (37 * hash) + GROUPS_WITH_VERSION_FIELD_NUMBER;
                    hash = (53 * hash) + getGroupsWithVersion().hashCode();
                }
                case 50 -> {
                    hash = (37 * hash) + STORAGE_RESOURCE_INFOS_FIELD_NUMBER;
                    hash = (53 * hash) + getStorageResourceInfos().hashCode();
                }
                case 0 -> {
                }
                default -> {
                }
            }
            hash = (29 * hash) + getUnknownFields().hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data parseFrom(
                byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data parseFrom(
                java.io.InputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data parseDelimitedFrom(
                java.io.InputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER,
                    input);
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data parseFrom(
                com.google.protobuf.CodedInputStream input) throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        @java.lang.Override
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(
                im.turms.server.common.access.client.dto.notification.TurmsNotification.Data prototype) {
            return DEFAULT_INSTANCE.toBuilder()
                    .mergeFrom(prototype);
        }

        @java.lang.Override
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder()
                    : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            return new Builder(parent);
        }

        /**
         * Protobuf type {@code im.turms.proto.TurmsNotification.Data}
         */
        public static final class Builder
                extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:im.turms.proto.TurmsNotification.Data)
                im.turms.server.common.access.client.dto.notification.TurmsNotification.DataOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
                return im.turms.server.common.access.client.dto.notification.TurmsNotificationOuterClass.internal_static_im_turms_proto_TurmsNotification_Data_descriptor;
            }

            @java.lang.Override
            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return im.turms.server.common.access.client.dto.notification.TurmsNotificationOuterClass.internal_static_im_turms_proto_TurmsNotification_Data_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                im.turms.server.common.access.client.dto.notification.TurmsNotification.Data.class,
                                im.turms.server.common.access.client.dto.notification.TurmsNotification.Data.Builder.class);
            }

            // Construct using
            // im.turms.server.common.access.client.dto.notification.TurmsNotification.Data.newBuilder()
            private Builder() {

            }

            private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);

            }

            @java.lang.Override
            public Builder clear() {
                super.clear();
                bitField0_ = 0;
                if (longsWithVersionBuilder_ != null) {
                    longsWithVersionBuilder_.clear();
                }
                if (stringsWithVersionBuilder_ != null) {
                    stringsWithVersionBuilder_.clear();
                }
                if (conversationsBuilder_ != null) {
                    conversationsBuilder_.clear();
                }
                if (messagesBuilder_ != null) {
                    messagesBuilder_.clear();
                }
                if (messagesWithTotalListBuilder_ != null) {
                    messagesWithTotalListBuilder_.clear();
                }
                if (userSessionBuilder_ != null) {
                    userSessionBuilder_.clear();
                }
                if (userInfosWithVersionBuilder_ != null) {
                    userInfosWithVersionBuilder_.clear();
                }
                if (userOnlineStatusesBuilder_ != null) {
                    userOnlineStatusesBuilder_.clear();
                }
                if (userFriendRequestsWithVersionBuilder_ != null) {
                    userFriendRequestsWithVersionBuilder_.clear();
                }
                if (userRelationshipGroupsWithVersionBuilder_ != null) {
                    userRelationshipGroupsWithVersionBuilder_.clear();
                }
                if (userRelationshipsWithVersionBuilder_ != null) {
                    userRelationshipsWithVersionBuilder_.clear();
                }
                if (nearbyUsersBuilder_ != null) {
                    nearbyUsersBuilder_.clear();
                }
                if (groupInvitationsWithVersionBuilder_ != null) {
                    groupInvitationsWithVersionBuilder_.clear();
                }
                if (groupJoinQuestionAnswerResultBuilder_ != null) {
                    groupJoinQuestionAnswerResultBuilder_.clear();
                }
                if (groupJoinRequestsWithVersionBuilder_ != null) {
                    groupJoinRequestsWithVersionBuilder_.clear();
                }
                if (groupJoinQuestionsWithVersionBuilder_ != null) {
                    groupJoinQuestionsWithVersionBuilder_.clear();
                }
                if (groupMembersWithVersionBuilder_ != null) {
                    groupMembersWithVersionBuilder_.clear();
                }
                if (groupsWithVersionBuilder_ != null) {
                    groupsWithVersionBuilder_.clear();
                }
                if (storageResourceInfosBuilder_ != null) {
                    storageResourceInfosBuilder_.clear();
                }
                kindCase_ = 0;
                kind_ = null;
                return this;
            }

            @java.lang.Override
            public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
                return im.turms.server.common.access.client.dto.notification.TurmsNotificationOuterClass.internal_static_im_turms_proto_TurmsNotification_Data_descriptor;
            }

            @java.lang.Override
            public im.turms.server.common.access.client.dto.notification.TurmsNotification.Data getDefaultInstanceForType() {
                return im.turms.server.common.access.client.dto.notification.TurmsNotification.Data
                        .getDefaultInstance();
            }

            @java.lang.Override
            public im.turms.server.common.access.client.dto.notification.TurmsNotification.Data build() {
                im.turms.server.common.access.client.dto.notification.TurmsNotification.Data result =
                        buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            @java.lang.Override
            public im.turms.server.common.access.client.dto.notification.TurmsNotification.Data buildPartial() {
                im.turms.server.common.access.client.dto.notification.TurmsNotification.Data result =
                        new im.turms.server.common.access.client.dto.notification.TurmsNotification.Data(
                                this);
                if (bitField0_ != 0) {
                    buildPartial0(result);
                }
                buildPartialOneofs(result);
                onBuilt();
                return result;
            }

            private void buildPartial0(
                    im.turms.server.common.access.client.dto.notification.TurmsNotification.Data result) {
                int from_bitField0_ = bitField0_;
            }

            private void buildPartialOneofs(
                    im.turms.server.common.access.client.dto.notification.TurmsNotification.Data result) {
                result.kindCase_ = kindCase_;
                result.kind_ = this.kind_;
                if (kindCase_ == 3 && longsWithVersionBuilder_ != null) {
                    result.kind_ = longsWithVersionBuilder_.build();
                }
                if (kindCase_ == 4 && stringsWithVersionBuilder_ != null) {
                    result.kind_ = stringsWithVersionBuilder_.build();
                }
                if (kindCase_ == 5 && conversationsBuilder_ != null) {
                    result.kind_ = conversationsBuilder_.build();
                }
                if (kindCase_ == 6 && messagesBuilder_ != null) {
                    result.kind_ = messagesBuilder_.build();
                }
                if (kindCase_ == 7 && messagesWithTotalListBuilder_ != null) {
                    result.kind_ = messagesWithTotalListBuilder_.build();
                }
                if (kindCase_ == 8 && userSessionBuilder_ != null) {
                    result.kind_ = userSessionBuilder_.build();
                }
                if (kindCase_ == 9 && userInfosWithVersionBuilder_ != null) {
                    result.kind_ = userInfosWithVersionBuilder_.build();
                }
                if (kindCase_ == 10 && userOnlineStatusesBuilder_ != null) {
                    result.kind_ = userOnlineStatusesBuilder_.build();
                }
                if (kindCase_ == 11 && userFriendRequestsWithVersionBuilder_ != null) {
                    result.kind_ = userFriendRequestsWithVersionBuilder_.build();
                }
                if (kindCase_ == 12 && userRelationshipGroupsWithVersionBuilder_ != null) {
                    result.kind_ = userRelationshipGroupsWithVersionBuilder_.build();
                }
                if (kindCase_ == 13 && userRelationshipsWithVersionBuilder_ != null) {
                    result.kind_ = userRelationshipsWithVersionBuilder_.build();
                }
                if (kindCase_ == 14 && nearbyUsersBuilder_ != null) {
                    result.kind_ = nearbyUsersBuilder_.build();
                }
                if (kindCase_ == 15 && groupInvitationsWithVersionBuilder_ != null) {
                    result.kind_ = groupInvitationsWithVersionBuilder_.build();
                }
                if (kindCase_ == 16 && groupJoinQuestionAnswerResultBuilder_ != null) {
                    result.kind_ = groupJoinQuestionAnswerResultBuilder_.build();
                }
                if (kindCase_ == 17 && groupJoinRequestsWithVersionBuilder_ != null) {
                    result.kind_ = groupJoinRequestsWithVersionBuilder_.build();
                }
                if (kindCase_ == 18 && groupJoinQuestionsWithVersionBuilder_ != null) {
                    result.kind_ = groupJoinQuestionsWithVersionBuilder_.build();
                }
                if (kindCase_ == 19 && groupMembersWithVersionBuilder_ != null) {
                    result.kind_ = groupMembersWithVersionBuilder_.build();
                }
                if (kindCase_ == 20 && groupsWithVersionBuilder_ != null) {
                    result.kind_ = groupsWithVersionBuilder_.build();
                }
                if (kindCase_ == 50 && storageResourceInfosBuilder_ != null) {
                    result.kind_ = storageResourceInfosBuilder_.build();
                }
            }

            @java.lang.Override
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof im.turms.server.common.access.client.dto.notification.TurmsNotification.Data) {
                    return mergeFrom(
                            (im.turms.server.common.access.client.dto.notification.TurmsNotification.Data) other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(
                    im.turms.server.common.access.client.dto.notification.TurmsNotification.Data other) {
                if (other == im.turms.server.common.access.client.dto.notification.TurmsNotification.Data
                        .getDefaultInstance()) {
                    return this;
                }
                switch (other.getKindCase()) {
                    case LONG -> {
                        setLong(other.getLong());
                    }
                    case STRING -> {
                        kindCase_ = 2;
                        kind_ = other.kind_;
                        onChanged();
                    }
                    case LONGS_WITH_VERSION -> {
                        mergeLongsWithVersion(other.getLongsWithVersion());
                    }
                    case STRINGS_WITH_VERSION -> {
                        mergeStringsWithVersion(other.getStringsWithVersion());
                    }
                    case CONVERSATIONS -> {
                        mergeConversations(other.getConversations());
                    }
                    case MESSAGES -> {
                        mergeMessages(other.getMessages());
                    }
                    case MESSAGES_WITH_TOTAL_LIST -> {
                        mergeMessagesWithTotalList(other.getMessagesWithTotalList());
                    }
                    case USER_SESSION -> {
                        mergeUserSession(other.getUserSession());
                    }
                    case USER_INFOS_WITH_VERSION -> {
                        mergeUserInfosWithVersion(other.getUserInfosWithVersion());
                    }
                    case USER_ONLINE_STATUSES -> {
                        mergeUserOnlineStatuses(other.getUserOnlineStatuses());
                    }
                    case USER_FRIEND_REQUESTS_WITH_VERSION -> {
                        mergeUserFriendRequestsWithVersion(
                                other.getUserFriendRequestsWithVersion());
                    }
                    case USER_RELATIONSHIP_GROUPS_WITH_VERSION -> {
                        mergeUserRelationshipGroupsWithVersion(
                                other.getUserRelationshipGroupsWithVersion());
                    }
                    case USER_RELATIONSHIPS_WITH_VERSION -> {
                        mergeUserRelationshipsWithVersion(other.getUserRelationshipsWithVersion());
                    }
                    case NEARBY_USERS -> {
                        mergeNearbyUsers(other.getNearbyUsers());
                    }
                    case GROUP_INVITATIONS_WITH_VERSION -> {
                        mergeGroupInvitationsWithVersion(other.getGroupInvitationsWithVersion());
                    }
                    case GROUP_JOIN_QUESTION_ANSWER_RESULT -> {
                        mergeGroupJoinQuestionAnswerResult(
                                other.getGroupJoinQuestionAnswerResult());
                    }
                    case GROUP_JOIN_REQUESTS_WITH_VERSION -> {
                        mergeGroupJoinRequestsWithVersion(other.getGroupJoinRequestsWithVersion());
                    }
                    case GROUP_JOIN_QUESTIONS_WITH_VERSION -> {
                        mergeGroupJoinQuestionsWithVersion(
                                other.getGroupJoinQuestionsWithVersion());
                    }
                    case GROUP_MEMBERS_WITH_VERSION -> {
                        mergeGroupMembersWithVersion(other.getGroupMembersWithVersion());
                    }
                    case GROUPS_WITH_VERSION -> {
                        mergeGroupsWithVersion(other.getGroupsWithVersion());
                    }
                    case STORAGE_RESOURCE_INFOS -> {
                        mergeStorageResourceInfos(other.getStorageResourceInfos());
                    }
                    case KIND_NOT_SET -> {
                    }
                }
                this.mergeUnknownFields(other.getUnknownFields());
                onChanged();
                return this;
            }

            @java.lang.Override
            public final boolean isInitialized() {
                return true;
            }

            @java.lang.Override
            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                if (extensionRegistry == null) {
                    throw new java.lang.NullPointerException();
                }
                try {
                    boolean done = false;
                    while (!done) {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0 -> done = true;
                            case 8 -> {
                                kind_ = input.readInt64();
                                kindCase_ = 1;
                            } // case 8
                            case 18 -> {
                                String s = input.readStringRequireUtf8();
                                kindCase_ = 2;
                                kind_ = s;
                            } // case 18
                            case 26 -> {
                                input.readMessage(getLongsWithVersionFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 3;
                            } // case 26
                            case 34 -> {
                                input.readMessage(getStringsWithVersionFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 4;
                            } // case 34
                            case 42 -> {
                                input.readMessage(getConversationsFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 5;
                            } // case 42
                            case 50 -> {
                                input.readMessage(getMessagesFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 6;
                            } // case 50
                            case 58 -> {
                                input.readMessage(
                                        getMessagesWithTotalListFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 7;
                            } // case 58
                            case 66 -> {
                                input.readMessage(getUserSessionFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 8;
                            } // case 66
                            case 74 -> {
                                input.readMessage(
                                        getUserInfosWithVersionFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 9;
                            } // case 74
                            case 82 -> {
                                input.readMessage(getUserOnlineStatusesFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 10;
                            } // case 82
                            case 90 -> {
                                input.readMessage(
                                        getUserFriendRequestsWithVersionFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 11;
                            } // case 90
                            case 98 -> {
                                input.readMessage(
                                        getUserRelationshipGroupsWithVersionFieldBuilder()
                                                .getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 12;
                            } // case 98
                            case 106 -> {
                                input.readMessage(
                                        getUserRelationshipsWithVersionFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 13;
                            } // case 106
                            case 114 -> {
                                input.readMessage(getNearbyUsersFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 14;
                            } // case 114
                            case 122 -> {
                                input.readMessage(
                                        getGroupInvitationsWithVersionFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 15;
                            } // case 122
                            case 130 -> {
                                input.readMessage(
                                        getGroupJoinQuestionAnswerResultFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 16;
                            } // case 130
                            case 138 -> {
                                input.readMessage(
                                        getGroupJoinRequestsWithVersionFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 17;
                            } // case 138
                            case 146 -> {
                                input.readMessage(
                                        getGroupJoinQuestionsWithVersionFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 18;
                            } // case 146
                            case 154 -> {
                                input.readMessage(
                                        getGroupMembersWithVersionFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 19;
                            } // case 154
                            case 162 -> {
                                input.readMessage(getGroupsWithVersionFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 20;
                            } // case 162
                            case 402 -> {
                                input.readMessage(
                                        getStorageResourceInfosFieldBuilder().getBuilder(),
                                        extensionRegistry);
                                kindCase_ = 50;
                            } // case 402
                            default -> {
                                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                    done = true; // was an endgroup tag
                                }
                            } // default:
                        } // switch (tag)
                    } // while (!done)
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    throw e.unwrapIOException();
                } finally {
                    onChanged();
                } // finally
                return this;
            }

            private int kindCase_ = 0;
            private java.lang.Object kind_;

            public KindCase getKindCase() {
                return KindCase.forNumber(kindCase_);
            }

            public Builder clearKind() {
                kindCase_ = 0;
                kind_ = null;
                onChanged();
                return this;
            }

            private int bitField0_;

            /**
             * <pre>
             * Common
             * </pre>
             * 
             * <code>int64 long = 1;</code>
             *
             * @return Whether the long field is set.
             */
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
             * @return This builder for chaining.
             */
            public Builder setLong(long value) {

                kindCase_ = 1;
                kind_ = value;
                onChanged();
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
                if (kindCase_ == 1) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
                return this;
            }

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
                java.lang.Object ref = "";
                if (kindCase_ == 2) {
                    ref = kind_;
                }
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    if (kindCase_ == 2) {
                        kind_ = s;
                    }
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }

            /**
             * <code>string string = 2;</code>
             *
             * @return The bytes for string.
             */
            @java.lang.Override
            public com.google.protobuf.ByteString getStringBytes() {
                java.lang.Object ref = "";
                if (kindCase_ == 2) {
                    ref = kind_;
                }
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                    if (kindCase_ == 2) {
                        kind_ = b;
                    }
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }

            /**
             * <code>string string = 2;</code>
             *
             * @param value The string to set.
             * @return This builder for chaining.
             */
            public Builder setString(java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                kindCase_ = 2;
                kind_ = value;
                onChanged();
                return this;
            }

            /**
             * <code>string string = 2;</code>
             *
             * @return This builder for chaining.
             */
            public Builder clearString() {
                if (kindCase_ == 2) {
                    kindCase_ = 0;
                    kind_ = null;
                    onChanged();
                }
                return this;
            }

            /**
             * <code>string string = 2;</code>
             *
             * @param value The bytes for string to set.
             * @return This builder for chaining.
             */
            public Builder setStringBytes(com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);
                kindCase_ = 2;
                kind_ = value;
                onChanged();
                return this;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.common.LongsWithVersion, im.turms.server.common.access.client.dto.model.common.LongsWithVersion.Builder, im.turms.server.common.access.client.dto.model.common.LongsWithVersionOrBuilder> longsWithVersionBuilder_;

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             *
             * @return Whether the longsWithVersion field is set.
             */
            @java.lang.Override
            public boolean hasLongsWithVersion() {
                return kindCase_ == 3;
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             *
             * @return The longsWithVersion.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.common.LongsWithVersion getLongsWithVersion() {
                if (longsWithVersionBuilder_ == null) {
                    if (kindCase_ == 3) {
                        return (im.turms.server.common.access.client.dto.model.common.LongsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.common.LongsWithVersion
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 3) {
                        return longsWithVersionBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.common.LongsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            public Builder setLongsWithVersion(
                    im.turms.server.common.access.client.dto.model.common.LongsWithVersion value) {
                if (longsWithVersionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    longsWithVersionBuilder_.setMessage(value);
                }
                kindCase_ = 3;
                return this;
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            public Builder setLongsWithVersion(
                    im.turms.server.common.access.client.dto.model.common.LongsWithVersion.Builder builderForValue) {
                if (longsWithVersionBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    longsWithVersionBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 3;
                return this;
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            public Builder mergeLongsWithVersion(
                    im.turms.server.common.access.client.dto.model.common.LongsWithVersion value) {
                if (longsWithVersionBuilder_ == null) {
                    if (kindCase_ == 3
                            && kind_ != im.turms.server.common.access.client.dto.model.common.LongsWithVersion
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.common.LongsWithVersion
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.common.LongsWithVersion) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 3) {
                        longsWithVersionBuilder_.mergeFrom(value);
                    } else {
                        longsWithVersionBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 3;
                return this;
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            public Builder clearLongsWithVersion() {
                if (longsWithVersionBuilder_ == null) {
                    if (kindCase_ == 3) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 3) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    longsWithVersionBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            public im.turms.server.common.access.client.dto.model.common.LongsWithVersion.Builder getLongsWithVersionBuilder() {
                return getLongsWithVersionFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.common.LongsWithVersionOrBuilder getLongsWithVersionOrBuilder() {
                if ((kindCase_ == 3) && (longsWithVersionBuilder_ != null)) {
                    return longsWithVersionBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 3) {
                        return (im.turms.server.common.access.client.dto.model.common.LongsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.common.LongsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.LongsWithVersion longs_with_version = 3;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.common.LongsWithVersion, im.turms.server.common.access.client.dto.model.common.LongsWithVersion.Builder, im.turms.server.common.access.client.dto.model.common.LongsWithVersionOrBuilder> getLongsWithVersionFieldBuilder() {
                if (longsWithVersionBuilder_ == null) {
                    if (!(kindCase_ == 3)) {
                        kind_ = im.turms.server.common.access.client.dto.model.common.LongsWithVersion
                                .getDefaultInstance();
                    }
                    longsWithVersionBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                            (im.turms.server.common.access.client.dto.model.common.LongsWithVersion) kind_,
                            getParentForChildren(),
                            isClean());
                    kind_ = null;
                }
                kindCase_ = 3;
                onChanged();
                return longsWithVersionBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.common.StringsWithVersion, im.turms.server.common.access.client.dto.model.common.StringsWithVersion.Builder, im.turms.server.common.access.client.dto.model.common.StringsWithVersionOrBuilder> stringsWithVersionBuilder_;

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             *
             * @return Whether the stringsWithVersion field is set.
             */
            @java.lang.Override
            public boolean hasStringsWithVersion() {
                return kindCase_ == 4;
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             *
             * @return The stringsWithVersion.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.common.StringsWithVersion getStringsWithVersion() {
                if (stringsWithVersionBuilder_ == null) {
                    if (kindCase_ == 4) {
                        return (im.turms.server.common.access.client.dto.model.common.StringsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.common.StringsWithVersion
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 4) {
                        return stringsWithVersionBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.common.StringsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            public Builder setStringsWithVersion(
                    im.turms.server.common.access.client.dto.model.common.StringsWithVersion value) {
                if (stringsWithVersionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    stringsWithVersionBuilder_.setMessage(value);
                }
                kindCase_ = 4;
                return this;
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            public Builder setStringsWithVersion(
                    im.turms.server.common.access.client.dto.model.common.StringsWithVersion.Builder builderForValue) {
                if (stringsWithVersionBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    stringsWithVersionBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 4;
                return this;
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            public Builder mergeStringsWithVersion(
                    im.turms.server.common.access.client.dto.model.common.StringsWithVersion value) {
                if (stringsWithVersionBuilder_ == null) {
                    if (kindCase_ == 4
                            && kind_ != im.turms.server.common.access.client.dto.model.common.StringsWithVersion
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.common.StringsWithVersion
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.common.StringsWithVersion) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 4) {
                        stringsWithVersionBuilder_.mergeFrom(value);
                    } else {
                        stringsWithVersionBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 4;
                return this;
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            public Builder clearStringsWithVersion() {
                if (stringsWithVersionBuilder_ == null) {
                    if (kindCase_ == 4) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 4) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    stringsWithVersionBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            public im.turms.server.common.access.client.dto.model.common.StringsWithVersion.Builder getStringsWithVersionBuilder() {
                return getStringsWithVersionFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.common.StringsWithVersionOrBuilder getStringsWithVersionOrBuilder() {
                if ((kindCase_ == 4) && (stringsWithVersionBuilder_ != null)) {
                    return stringsWithVersionBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 4) {
                        return (im.turms.server.common.access.client.dto.model.common.StringsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.common.StringsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.StringsWithVersion strings_with_version = 4;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.common.StringsWithVersion, im.turms.server.common.access.client.dto.model.common.StringsWithVersion.Builder, im.turms.server.common.access.client.dto.model.common.StringsWithVersionOrBuilder> getStringsWithVersionFieldBuilder() {
                if (stringsWithVersionBuilder_ == null) {
                    if (!(kindCase_ == 4)) {
                        kind_ = im.turms.server.common.access.client.dto.model.common.StringsWithVersion
                                .getDefaultInstance();
                    }
                    stringsWithVersionBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                            (im.turms.server.common.access.client.dto.model.common.StringsWithVersion) kind_,
                            getParentForChildren(),
                            isClean());
                    kind_ = null;
                }
                kindCase_ = 4;
                onChanged();
                return stringsWithVersionBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.conversation.Conversations, im.turms.server.common.access.client.dto.model.conversation.Conversations.Builder, im.turms.server.common.access.client.dto.model.conversation.ConversationsOrBuilder> conversationsBuilder_;

            /**
             * <pre>
             * Conversation
             * </pre>
             * 
             * <code>.im.turms.proto.Conversations conversations = 5;</code>
             *
             * @return Whether the conversations field is set.
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
             *
             * @return The conversations.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.conversation.Conversations getConversations() {
                if (conversationsBuilder_ == null) {
                    if (kindCase_ == 5) {
                        return (im.turms.server.common.access.client.dto.model.conversation.Conversations) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.conversation.Conversations
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 5) {
                        return conversationsBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.conversation.Conversations
                            .getDefaultInstance();
                }
            }

            /**
             * <pre>
             * Conversation
             * </pre>
             * 
             * <code>.im.turms.proto.Conversations conversations = 5;</code>
             */
            public Builder setConversations(
                    im.turms.server.common.access.client.dto.model.conversation.Conversations value) {
                if (conversationsBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    conversationsBuilder_.setMessage(value);
                }
                kindCase_ = 5;
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
                    im.turms.server.common.access.client.dto.model.conversation.Conversations.Builder builderForValue) {
                if (conversationsBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    conversationsBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 5;
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
                    im.turms.server.common.access.client.dto.model.conversation.Conversations value) {
                if (conversationsBuilder_ == null) {
                    if (kindCase_ == 5
                            && kind_ != im.turms.server.common.access.client.dto.model.conversation.Conversations
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.conversation.Conversations
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.conversation.Conversations) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 5) {
                        conversationsBuilder_.mergeFrom(value);
                    } else {
                        conversationsBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 5;
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
                if (conversationsBuilder_ == null) {
                    if (kindCase_ == 5) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 5) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    conversationsBuilder_.clear();
                }
                return this;
            }

            /**
             * <pre>
             * Conversation
             * </pre>
             * 
             * <code>.im.turms.proto.Conversations conversations = 5;</code>
             */
            public im.turms.server.common.access.client.dto.model.conversation.Conversations.Builder getConversationsBuilder() {
                return getConversationsFieldBuilder().getBuilder();
            }

            /**
             * <pre>
             * Conversation
             * </pre>
             * 
             * <code>.im.turms.proto.Conversations conversations = 5;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.conversation.ConversationsOrBuilder getConversationsOrBuilder() {
                if ((kindCase_ == 5) && (conversationsBuilder_ != null)) {
                    return conversationsBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 5) {
                        return (im.turms.server.common.access.client.dto.model.conversation.Conversations) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.conversation.Conversations
                            .getDefaultInstance();
                }
            }

            /**
             * <pre>
             * Conversation
             * </pre>
             * 
             * <code>.im.turms.proto.Conversations conversations = 5;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.conversation.Conversations, im.turms.server.common.access.client.dto.model.conversation.Conversations.Builder, im.turms.server.common.access.client.dto.model.conversation.ConversationsOrBuilder> getConversationsFieldBuilder() {
                if (conversationsBuilder_ == null) {
                    if (!(kindCase_ == 5)) {
                        kind_ = im.turms.server.common.access.client.dto.model.conversation.Conversations
                                .getDefaultInstance();
                    }
                    conversationsBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                            (im.turms.server.common.access.client.dto.model.conversation.Conversations) kind_,
                            getParentForChildren(),
                            isClean());
                    kind_ = null;
                }
                kindCase_ = 5;
                onChanged();
                return conversationsBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.message.Messages, im.turms.server.common.access.client.dto.model.message.Messages.Builder, im.turms.server.common.access.client.dto.model.message.MessagesOrBuilder> messagesBuilder_;

            /**
             * <pre>
             * Message
             * </pre>
             * 
             * <code>.im.turms.proto.Messages messages = 6;</code>
             *
             * @return Whether the messages field is set.
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
             *
             * @return The messages.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.message.Messages getMessages() {
                if (messagesBuilder_ == null) {
                    if (kindCase_ == 6) {
                        return (im.turms.server.common.access.client.dto.model.message.Messages) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.message.Messages
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 6) {
                        return messagesBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.message.Messages
                            .getDefaultInstance();
                }
            }

            /**
             * <pre>
             * Message
             * </pre>
             * 
             * <code>.im.turms.proto.Messages messages = 6;</code>
             */
            public Builder setMessages(
                    im.turms.server.common.access.client.dto.model.message.Messages value) {
                if (messagesBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    messagesBuilder_.setMessage(value);
                }
                kindCase_ = 6;
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
                    im.turms.server.common.access.client.dto.model.message.Messages.Builder builderForValue) {
                if (messagesBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    messagesBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 6;
                return this;
            }

            /**
             * <pre>
             * Message
             * </pre>
             * 
             * <code>.im.turms.proto.Messages messages = 6;</code>
             */
            public Builder mergeMessages(
                    im.turms.server.common.access.client.dto.model.message.Messages value) {
                if (messagesBuilder_ == null) {
                    if (kindCase_ == 6
                            && kind_ != im.turms.server.common.access.client.dto.model.message.Messages
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.message.Messages
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.message.Messages) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 6) {
                        messagesBuilder_.mergeFrom(value);
                    } else {
                        messagesBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 6;
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
                if (messagesBuilder_ == null) {
                    if (kindCase_ == 6) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 6) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    messagesBuilder_.clear();
                }
                return this;
            }

            /**
             * <pre>
             * Message
             * </pre>
             * 
             * <code>.im.turms.proto.Messages messages = 6;</code>
             */
            public im.turms.server.common.access.client.dto.model.message.Messages.Builder getMessagesBuilder() {
                return getMessagesFieldBuilder().getBuilder();
            }

            /**
             * <pre>
             * Message
             * </pre>
             * 
             * <code>.im.turms.proto.Messages messages = 6;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.message.MessagesOrBuilder getMessagesOrBuilder() {
                if ((kindCase_ == 6) && (messagesBuilder_ != null)) {
                    return messagesBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 6) {
                        return (im.turms.server.common.access.client.dto.model.message.Messages) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.message.Messages
                            .getDefaultInstance();
                }
            }

            /**
             * <pre>
             * Message
             * </pre>
             * 
             * <code>.im.turms.proto.Messages messages = 6;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.message.Messages, im.turms.server.common.access.client.dto.model.message.Messages.Builder, im.turms.server.common.access.client.dto.model.message.MessagesOrBuilder> getMessagesFieldBuilder() {
                if (messagesBuilder_ == null) {
                    if (!(kindCase_ == 6)) {
                        kind_ = im.turms.server.common.access.client.dto.model.message.Messages
                                .getDefaultInstance();
                    }
                    messagesBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                            (im.turms.server.common.access.client.dto.model.message.Messages) kind_,
                            getParentForChildren(),
                            isClean());
                    kind_ = null;
                }
                kindCase_ = 6;
                onChanged();
                return messagesBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList, im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList.Builder, im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOrBuilder> messagesWithTotalListBuilder_;

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             *
             * @return Whether the messagesWithTotalList field is set.
             */
            @java.lang.Override
            public boolean hasMessagesWithTotalList() {
                return kindCase_ == 7;
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             *
             * @return The messagesWithTotalList.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList getMessagesWithTotalList() {
                if (messagesWithTotalListBuilder_ == null) {
                    if (kindCase_ == 7) {
                        return (im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 7) {
                        return messagesWithTotalListBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            public Builder setMessagesWithTotalList(
                    im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList value) {
                if (messagesWithTotalListBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    messagesWithTotalListBuilder_.setMessage(value);
                }
                kindCase_ = 7;
                return this;
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            public Builder setMessagesWithTotalList(
                    im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList.Builder builderForValue) {
                if (messagesWithTotalListBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    messagesWithTotalListBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 7;
                return this;
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            public Builder mergeMessagesWithTotalList(
                    im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList value) {
                if (messagesWithTotalListBuilder_ == null) {
                    if (kindCase_ == 7
                            && kind_ != im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 7) {
                        messagesWithTotalListBuilder_.mergeFrom(value);
                    } else {
                        messagesWithTotalListBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 7;
                return this;
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            public Builder clearMessagesWithTotalList() {
                if (messagesWithTotalListBuilder_ == null) {
                    if (kindCase_ == 7) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 7) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    messagesWithTotalListBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            public im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList.Builder getMessagesWithTotalListBuilder() {
                return getMessagesWithTotalListFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOrBuilder getMessagesWithTotalListOrBuilder() {
                if ((kindCase_ == 7) && (messagesWithTotalListBuilder_ != null)) {
                    return messagesWithTotalListBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 7) {
                        return (im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.MessagesWithTotalList messages_with_total_list = 7;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList, im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList.Builder, im.turms.server.common.access.client.dto.model.message.MessagesWithTotalListOrBuilder> getMessagesWithTotalListFieldBuilder() {
                if (messagesWithTotalListBuilder_ == null) {
                    if (!(kindCase_ == 7)) {
                        kind_ = im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList
                                .getDefaultInstance();
                    }
                    messagesWithTotalListBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                            (im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList) kind_,
                            getParentForChildren(),
                            isClean());
                    kind_ = null;
                }
                kindCase_ = 7;
                onChanged();
                return messagesWithTotalListBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserSession, im.turms.server.common.access.client.dto.model.user.UserSession.Builder, im.turms.server.common.access.client.dto.model.user.UserSessionOrBuilder> userSessionBuilder_;

            /**
             * <pre>
             * User
             * </pre>
             * 
             * <code>.im.turms.proto.UserSession user_session = 8;</code>
             *
             * @return Whether the userSession field is set.
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
             *
             * @return The userSession.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.UserSession getUserSession() {
                if (userSessionBuilder_ == null) {
                    if (kindCase_ == 8) {
                        return (im.turms.server.common.access.client.dto.model.user.UserSession) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserSession
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 8) {
                        return userSessionBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserSession
                            .getDefaultInstance();
                }
            }

            /**
             * <pre>
             * User
             * </pre>
             * 
             * <code>.im.turms.proto.UserSession user_session = 8;</code>
             */
            public Builder setUserSession(
                    im.turms.server.common.access.client.dto.model.user.UserSession value) {
                if (userSessionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    userSessionBuilder_.setMessage(value);
                }
                kindCase_ = 8;
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
                    im.turms.server.common.access.client.dto.model.user.UserSession.Builder builderForValue) {
                if (userSessionBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    userSessionBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 8;
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
                    im.turms.server.common.access.client.dto.model.user.UserSession value) {
                if (userSessionBuilder_ == null) {
                    if (kindCase_ == 8
                            && kind_ != im.turms.server.common.access.client.dto.model.user.UserSession
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.UserSession
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.user.UserSession) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 8) {
                        userSessionBuilder_.mergeFrom(value);
                    } else {
                        userSessionBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 8;
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
                if (userSessionBuilder_ == null) {
                    if (kindCase_ == 8) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 8) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    userSessionBuilder_.clear();
                }
                return this;
            }

            /**
             * <pre>
             * User
             * </pre>
             * 
             * <code>.im.turms.proto.UserSession user_session = 8;</code>
             */
            public im.turms.server.common.access.client.dto.model.user.UserSession.Builder getUserSessionBuilder() {
                return getUserSessionFieldBuilder().getBuilder();
            }

            /**
             * <pre>
             * User
             * </pre>
             * 
             * <code>.im.turms.proto.UserSession user_session = 8;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.UserSessionOrBuilder getUserSessionOrBuilder() {
                if ((kindCase_ == 8) && (userSessionBuilder_ != null)) {
                    return userSessionBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 8) {
                        return (im.turms.server.common.access.client.dto.model.user.UserSession) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserSession
                            .getDefaultInstance();
                }
            }

            /**
             * <pre>
             * User
             * </pre>
             * 
             * <code>.im.turms.proto.UserSession user_session = 8;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserSession, im.turms.server.common.access.client.dto.model.user.UserSession.Builder, im.turms.server.common.access.client.dto.model.user.UserSessionOrBuilder> getUserSessionFieldBuilder() {
                if (userSessionBuilder_ == null) {
                    if (!(kindCase_ == 8)) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.UserSession
                                .getDefaultInstance();
                    }
                    userSessionBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                            (im.turms.server.common.access.client.dto.model.user.UserSession) kind_,
                            getParentForChildren(),
                            isClean());
                    kind_ = null;
                }
                kindCase_ = 8;
                onChanged();
                return userSessionBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion, im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion.Builder, im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOrBuilder> userInfosWithVersionBuilder_;

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             *
             * @return Whether the userInfosWithVersion field is set.
             */
            @java.lang.Override
            public boolean hasUserInfosWithVersion() {
                return kindCase_ == 9;
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             *
             * @return The userInfosWithVersion.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion getUserInfosWithVersion() {
                if (userInfosWithVersionBuilder_ == null) {
                    if (kindCase_ == 9) {
                        return (im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 9) {
                        return userInfosWithVersionBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            public Builder setUserInfosWithVersion(
                    im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion value) {
                if (userInfosWithVersionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    userInfosWithVersionBuilder_.setMessage(value);
                }
                kindCase_ = 9;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            public Builder setUserInfosWithVersion(
                    im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion.Builder builderForValue) {
                if (userInfosWithVersionBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    userInfosWithVersionBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 9;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            public Builder mergeUserInfosWithVersion(
                    im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion value) {
                if (userInfosWithVersionBuilder_ == null) {
                    if (kindCase_ == 9
                            && kind_ != im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 9) {
                        userInfosWithVersionBuilder_.mergeFrom(value);
                    } else {
                        userInfosWithVersionBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 9;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            public Builder clearUserInfosWithVersion() {
                if (userInfosWithVersionBuilder_ == null) {
                    if (kindCase_ == 9) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 9) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    userInfosWithVersionBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            public im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion.Builder getUserInfosWithVersionBuilder() {
                return getUserInfosWithVersionFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOrBuilder getUserInfosWithVersionOrBuilder() {
                if ((kindCase_ == 9) && (userInfosWithVersionBuilder_ != null)) {
                    return userInfosWithVersionBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 9) {
                        return (im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.UserInfosWithVersion user_infos_with_version = 9;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion, im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion.Builder, im.turms.server.common.access.client.dto.model.user.UserInfosWithVersionOrBuilder> getUserInfosWithVersionFieldBuilder() {
                if (userInfosWithVersionBuilder_ == null) {
                    if (!(kindCase_ == 9)) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion
                                .getDefaultInstance();
                    }
                    userInfosWithVersionBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                            (im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion) kind_,
                            getParentForChildren(),
                            isClean());
                    kind_ = null;
                }
                kindCase_ = 9;
                onChanged();
                return userInfosWithVersionBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses, im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses.Builder, im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOrBuilder> userOnlineStatusesBuilder_;

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             *
             * @return Whether the userOnlineStatuses field is set.
             */
            @java.lang.Override
            public boolean hasUserOnlineStatuses() {
                return kindCase_ == 10;
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             *
             * @return The userOnlineStatuses.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses getUserOnlineStatuses() {
                if (userOnlineStatusesBuilder_ == null) {
                    if (kindCase_ == 10) {
                        return (im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 10) {
                        return userOnlineStatusesBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            public Builder setUserOnlineStatuses(
                    im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses value) {
                if (userOnlineStatusesBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    userOnlineStatusesBuilder_.setMessage(value);
                }
                kindCase_ = 10;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            public Builder setUserOnlineStatuses(
                    im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses.Builder builderForValue) {
                if (userOnlineStatusesBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    userOnlineStatusesBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 10;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            public Builder mergeUserOnlineStatuses(
                    im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses value) {
                if (userOnlineStatusesBuilder_ == null) {
                    if (kindCase_ == 10
                            && kind_ != im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 10) {
                        userOnlineStatusesBuilder_.mergeFrom(value);
                    } else {
                        userOnlineStatusesBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 10;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            public Builder clearUserOnlineStatuses() {
                if (userOnlineStatusesBuilder_ == null) {
                    if (kindCase_ == 10) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 10) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    userOnlineStatusesBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            public im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses.Builder getUserOnlineStatusesBuilder() {
                return getUserOnlineStatusesFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOrBuilder getUserOnlineStatusesOrBuilder() {
                if ((kindCase_ == 10) && (userOnlineStatusesBuilder_ != null)) {
                    return userOnlineStatusesBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 10) {
                        return (im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.UserOnlineStatuses user_online_statuses = 10;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses, im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses.Builder, im.turms.server.common.access.client.dto.model.user.UserOnlineStatusesOrBuilder> getUserOnlineStatusesFieldBuilder() {
                if (userOnlineStatusesBuilder_ == null) {
                    if (!(kindCase_ == 10)) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses
                                .getDefaultInstance();
                    }
                    userOnlineStatusesBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                            (im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses) kind_,
                            getParentForChildren(),
                            isClean());
                    kind_ = null;
                }
                kindCase_ = 10;
                onChanged();
                return userOnlineStatusesBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion, im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion.Builder, im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOrBuilder> userFriendRequestsWithVersionBuilder_;

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             *
             * @return Whether the userFriendRequestsWithVersion field is set.
             */
            @java.lang.Override
            public boolean hasUserFriendRequestsWithVersion() {
                return kindCase_ == 11;
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             *
             * @return The userFriendRequestsWithVersion.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion getUserFriendRequestsWithVersion() {
                if (userFriendRequestsWithVersionBuilder_ == null) {
                    if (kindCase_ == 11) {
                        return (im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 11) {
                        return userFriendRequestsWithVersionBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            public Builder setUserFriendRequestsWithVersion(
                    im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion value) {
                if (userFriendRequestsWithVersionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    userFriendRequestsWithVersionBuilder_.setMessage(value);
                }
                kindCase_ = 11;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            public Builder setUserFriendRequestsWithVersion(
                    im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion.Builder builderForValue) {
                if (userFriendRequestsWithVersionBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    userFriendRequestsWithVersionBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 11;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            public Builder mergeUserFriendRequestsWithVersion(
                    im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion value) {
                if (userFriendRequestsWithVersionBuilder_ == null) {
                    if (kindCase_ == 11
                            && kind_ != im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 11) {
                        userFriendRequestsWithVersionBuilder_.mergeFrom(value);
                    } else {
                        userFriendRequestsWithVersionBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 11;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            public Builder clearUserFriendRequestsWithVersion() {
                if (userFriendRequestsWithVersionBuilder_ == null) {
                    if (kindCase_ == 11) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 11) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    userFriendRequestsWithVersionBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            public im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion.Builder getUserFriendRequestsWithVersionBuilder() {
                return getUserFriendRequestsWithVersionFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOrBuilder getUserFriendRequestsWithVersionOrBuilder() {
                if ((kindCase_ == 11) && (userFriendRequestsWithVersionBuilder_ != null)) {
                    return userFriendRequestsWithVersionBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 11) {
                        return (im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.UserFriendRequestsWithVersion user_friend_requests_with_version = 11;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion, im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion.Builder, im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersionOrBuilder> getUserFriendRequestsWithVersionFieldBuilder() {
                if (userFriendRequestsWithVersionBuilder_ == null) {
                    if (!(kindCase_ == 11)) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion
                                .getDefaultInstance();
                    }
                    userFriendRequestsWithVersionBuilder_ =
                            new com.google.protobuf.SingleFieldBuilderV3<>(
                                    (im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion) kind_,
                                    getParentForChildren(),
                                    isClean());
                    kind_ = null;
                }
                kindCase_ = 11;
                onChanged();
                return userFriendRequestsWithVersionBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion, im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion.Builder, im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOrBuilder> userRelationshipGroupsWithVersionBuilder_;

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             *
             * @return Whether the userRelationshipGroupsWithVersion field is set.
             */
            @java.lang.Override
            public boolean hasUserRelationshipGroupsWithVersion() {
                return kindCase_ == 12;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             *
             * @return The userRelationshipGroupsWithVersion.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion getUserRelationshipGroupsWithVersion() {
                if (userRelationshipGroupsWithVersionBuilder_ == null) {
                    if (kindCase_ == 12) {
                        return (im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 12) {
                        return userRelationshipGroupsWithVersionBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            public Builder setUserRelationshipGroupsWithVersion(
                    im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion value) {
                if (userRelationshipGroupsWithVersionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    userRelationshipGroupsWithVersionBuilder_.setMessage(value);
                }
                kindCase_ = 12;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            public Builder setUserRelationshipGroupsWithVersion(
                    im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion.Builder builderForValue) {
                if (userRelationshipGroupsWithVersionBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    userRelationshipGroupsWithVersionBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 12;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            public Builder mergeUserRelationshipGroupsWithVersion(
                    im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion value) {
                if (userRelationshipGroupsWithVersionBuilder_ == null) {
                    if (kindCase_ == 12
                            && kind_ != im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 12) {
                        userRelationshipGroupsWithVersionBuilder_.mergeFrom(value);
                    } else {
                        userRelationshipGroupsWithVersionBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 12;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            public Builder clearUserRelationshipGroupsWithVersion() {
                if (userRelationshipGroupsWithVersionBuilder_ == null) {
                    if (kindCase_ == 12) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 12) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    userRelationshipGroupsWithVersionBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion.Builder getUserRelationshipGroupsWithVersionBuilder() {
                return getUserRelationshipGroupsWithVersionFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOrBuilder getUserRelationshipGroupsWithVersionOrBuilder() {
                if ((kindCase_ == 12) && (userRelationshipGroupsWithVersionBuilder_ != null)) {
                    return userRelationshipGroupsWithVersionBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 12) {
                        return (im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.UserRelationshipGroupsWithVersion user_relationship_groups_with_version = 12;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion, im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion.Builder, im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersionOrBuilder> getUserRelationshipGroupsWithVersionFieldBuilder() {
                if (userRelationshipGroupsWithVersionBuilder_ == null) {
                    if (!(kindCase_ == 12)) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion
                                .getDefaultInstance();
                    }
                    userRelationshipGroupsWithVersionBuilder_ =
                            new com.google.protobuf.SingleFieldBuilderV3<>(
                                    (im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion) kind_,
                                    getParentForChildren(),
                                    isClean());
                    kind_ = null;
                }
                kindCase_ = 12;
                onChanged();
                return userRelationshipGroupsWithVersionBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion, im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion.Builder, im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOrBuilder> userRelationshipsWithVersionBuilder_;

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             *
             * @return Whether the userRelationshipsWithVersion field is set.
             */
            @java.lang.Override
            public boolean hasUserRelationshipsWithVersion() {
                return kindCase_ == 13;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             *
             * @return The userRelationshipsWithVersion.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion getUserRelationshipsWithVersion() {
                if (userRelationshipsWithVersionBuilder_ == null) {
                    if (kindCase_ == 13) {
                        return (im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 13) {
                        return userRelationshipsWithVersionBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            public Builder setUserRelationshipsWithVersion(
                    im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion value) {
                if (userRelationshipsWithVersionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    userRelationshipsWithVersionBuilder_.setMessage(value);
                }
                kindCase_ = 13;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            public Builder setUserRelationshipsWithVersion(
                    im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion.Builder builderForValue) {
                if (userRelationshipsWithVersionBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    userRelationshipsWithVersionBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 13;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            public Builder mergeUserRelationshipsWithVersion(
                    im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion value) {
                if (userRelationshipsWithVersionBuilder_ == null) {
                    if (kindCase_ == 13
                            && kind_ != im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 13) {
                        userRelationshipsWithVersionBuilder_.mergeFrom(value);
                    } else {
                        userRelationshipsWithVersionBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 13;
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            public Builder clearUserRelationshipsWithVersion() {
                if (userRelationshipsWithVersionBuilder_ == null) {
                    if (kindCase_ == 13) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 13) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    userRelationshipsWithVersionBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            public im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion.Builder getUserRelationshipsWithVersionBuilder() {
                return getUserRelationshipsWithVersionFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOrBuilder getUserRelationshipsWithVersionOrBuilder() {
                if ((kindCase_ == 13) && (userRelationshipsWithVersionBuilder_ != null)) {
                    return userRelationshipsWithVersionBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 13) {
                        return (im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.UserRelationshipsWithVersion user_relationships_with_version = 13;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion, im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion.Builder, im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersionOrBuilder> getUserRelationshipsWithVersionFieldBuilder() {
                if (userRelationshipsWithVersionBuilder_ == null) {
                    if (!(kindCase_ == 13)) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion
                                .getDefaultInstance();
                    }
                    userRelationshipsWithVersionBuilder_ =
                            new com.google.protobuf.SingleFieldBuilderV3<>(
                                    (im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion) kind_,
                                    getParentForChildren(),
                                    isClean());
                    kind_ = null;
                }
                kindCase_ = 13;
                onChanged();
                return userRelationshipsWithVersionBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.NearbyUsers, im.turms.server.common.access.client.dto.model.user.NearbyUsers.Builder, im.turms.server.common.access.client.dto.model.user.NearbyUsersOrBuilder> nearbyUsersBuilder_;

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             *
             * @return Whether the nearbyUsers field is set.
             */
            @java.lang.Override
            public boolean hasNearbyUsers() {
                return kindCase_ == 14;
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             *
             * @return The nearbyUsers.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.NearbyUsers getNearbyUsers() {
                if (nearbyUsersBuilder_ == null) {
                    if (kindCase_ == 14) {
                        return (im.turms.server.common.access.client.dto.model.user.NearbyUsers) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.NearbyUsers
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 14) {
                        return nearbyUsersBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.user.NearbyUsers
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            public Builder setNearbyUsers(
                    im.turms.server.common.access.client.dto.model.user.NearbyUsers value) {
                if (nearbyUsersBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    nearbyUsersBuilder_.setMessage(value);
                }
                kindCase_ = 14;
                return this;
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            public Builder setNearbyUsers(
                    im.turms.server.common.access.client.dto.model.user.NearbyUsers.Builder builderForValue) {
                if (nearbyUsersBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    nearbyUsersBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 14;
                return this;
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            public Builder mergeNearbyUsers(
                    im.turms.server.common.access.client.dto.model.user.NearbyUsers value) {
                if (nearbyUsersBuilder_ == null) {
                    if (kindCase_ == 14
                            && kind_ != im.turms.server.common.access.client.dto.model.user.NearbyUsers
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.NearbyUsers
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.user.NearbyUsers) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 14) {
                        nearbyUsersBuilder_.mergeFrom(value);
                    } else {
                        nearbyUsersBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 14;
                return this;
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            public Builder clearNearbyUsers() {
                if (nearbyUsersBuilder_ == null) {
                    if (kindCase_ == 14) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 14) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    nearbyUsersBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            public im.turms.server.common.access.client.dto.model.user.NearbyUsers.Builder getNearbyUsersBuilder() {
                return getNearbyUsersFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.user.NearbyUsersOrBuilder getNearbyUsersOrBuilder() {
                if ((kindCase_ == 14) && (nearbyUsersBuilder_ != null)) {
                    return nearbyUsersBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 14) {
                        return (im.turms.server.common.access.client.dto.model.user.NearbyUsers) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.user.NearbyUsers
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.NearbyUsers nearby_users = 14;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.user.NearbyUsers, im.turms.server.common.access.client.dto.model.user.NearbyUsers.Builder, im.turms.server.common.access.client.dto.model.user.NearbyUsersOrBuilder> getNearbyUsersFieldBuilder() {
                if (nearbyUsersBuilder_ == null) {
                    if (!(kindCase_ == 14)) {
                        kind_ = im.turms.server.common.access.client.dto.model.user.NearbyUsers
                                .getDefaultInstance();
                    }
                    nearbyUsersBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                            (im.turms.server.common.access.client.dto.model.user.NearbyUsers) kind_,
                            getParentForChildren(),
                            isClean());
                    kind_ = null;
                }
                kindCase_ = 14;
                onChanged();
                return nearbyUsersBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion, im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion.Builder, im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOrBuilder> groupInvitationsWithVersionBuilder_;

            /**
             * <pre>
             * Group
             * </pre>
             * 
             * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
             *
             * @return Whether the groupInvitationsWithVersion field is set.
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
             *
             * @return The groupInvitationsWithVersion.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion getGroupInvitationsWithVersion() {
                if (groupInvitationsWithVersionBuilder_ == null) {
                    if (kindCase_ == 15) {
                        return (im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 15) {
                        return groupInvitationsWithVersionBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <pre>
             * Group
             * </pre>
             * 
             * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
             */
            public Builder setGroupInvitationsWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion value) {
                if (groupInvitationsWithVersionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    groupInvitationsWithVersionBuilder_.setMessage(value);
                }
                kindCase_ = 15;
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
                    im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion.Builder builderForValue) {
                if (groupInvitationsWithVersionBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    groupInvitationsWithVersionBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 15;
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
                    im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion value) {
                if (groupInvitationsWithVersionBuilder_ == null) {
                    if (kindCase_ == 15
                            && kind_ != im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 15) {
                        groupInvitationsWithVersionBuilder_.mergeFrom(value);
                    } else {
                        groupInvitationsWithVersionBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 15;
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
                if (groupInvitationsWithVersionBuilder_ == null) {
                    if (kindCase_ == 15) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 15) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    groupInvitationsWithVersionBuilder_.clear();
                }
                return this;
            }

            /**
             * <pre>
             * Group
             * </pre>
             * 
             * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
             */
            public im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion.Builder getGroupInvitationsWithVersionBuilder() {
                return getGroupInvitationsWithVersionFieldBuilder().getBuilder();
            }

            /**
             * <pre>
             * Group
             * </pre>
             * 
             * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOrBuilder getGroupInvitationsWithVersionOrBuilder() {
                if ((kindCase_ == 15) && (groupInvitationsWithVersionBuilder_ != null)) {
                    return groupInvitationsWithVersionBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 15) {
                        return (im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <pre>
             * Group
             * </pre>
             * 
             * <code>.im.turms.proto.GroupInvitationsWithVersion group_invitations_with_version = 15;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion, im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion.Builder, im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersionOrBuilder> getGroupInvitationsWithVersionFieldBuilder() {
                if (groupInvitationsWithVersionBuilder_ == null) {
                    if (!(kindCase_ == 15)) {
                        kind_ = im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion
                                .getDefaultInstance();
                    }
                    groupInvitationsWithVersionBuilder_ =
                            new com.google.protobuf.SingleFieldBuilderV3<>(
                                    (im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion) kind_,
                                    getParentForChildren(),
                                    isClean());
                    kind_ = null;
                }
                kindCase_ = 15;
                onChanged();
                return groupInvitationsWithVersionBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult.Builder, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOrBuilder> groupJoinQuestionAnswerResultBuilder_;

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             *
             * @return Whether the groupJoinQuestionAnswerResult field is set.
             */
            @java.lang.Override
            public boolean hasGroupJoinQuestionAnswerResult() {
                return kindCase_ == 16;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             *
             * @return The groupJoinQuestionAnswerResult.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult getGroupJoinQuestionAnswerResult() {
                if (groupJoinQuestionAnswerResultBuilder_ == null) {
                    if (kindCase_ == 16) {
                        return (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 16) {
                        return groupJoinQuestionAnswerResultBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            public Builder setGroupJoinQuestionAnswerResult(
                    im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult value) {
                if (groupJoinQuestionAnswerResultBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    groupJoinQuestionAnswerResultBuilder_.setMessage(value);
                }
                kindCase_ = 16;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            public Builder setGroupJoinQuestionAnswerResult(
                    im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult.Builder builderForValue) {
                if (groupJoinQuestionAnswerResultBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    groupJoinQuestionAnswerResultBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 16;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            public Builder mergeGroupJoinQuestionAnswerResult(
                    im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult value) {
                if (groupJoinQuestionAnswerResultBuilder_ == null) {
                    if (kindCase_ == 16
                            && kind_ != im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 16) {
                        groupJoinQuestionAnswerResultBuilder_.mergeFrom(value);
                    } else {
                        groupJoinQuestionAnswerResultBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 16;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            public Builder clearGroupJoinQuestionAnswerResult() {
                if (groupJoinQuestionAnswerResultBuilder_ == null) {
                    if (kindCase_ == 16) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 16) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    groupJoinQuestionAnswerResultBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult.Builder getGroupJoinQuestionAnswerResultBuilder() {
                return getGroupJoinQuestionAnswerResultFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOrBuilder getGroupJoinQuestionAnswerResultOrBuilder() {
                if ((kindCase_ == 16) && (groupJoinQuestionAnswerResultBuilder_ != null)) {
                    return groupJoinQuestionAnswerResultBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 16) {
                        return (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsAnswerResult group_join_question_answer_result = 16;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult.Builder, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResultOrBuilder> getGroupJoinQuestionAnswerResultFieldBuilder() {
                if (groupJoinQuestionAnswerResultBuilder_ == null) {
                    if (!(kindCase_ == 16)) {
                        kind_ = im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult
                                .getDefaultInstance();
                    }
                    groupJoinQuestionAnswerResultBuilder_ =
                            new com.google.protobuf.SingleFieldBuilderV3<>(
                                    (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult) kind_,
                                    getParentForChildren(),
                                    isClean());
                    kind_ = null;
                }
                kindCase_ = 16;
                onChanged();
                return groupJoinQuestionAnswerResultBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion, im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion.Builder, im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOrBuilder> groupJoinRequestsWithVersionBuilder_;

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             *
             * @return Whether the groupJoinRequestsWithVersion field is set.
             */
            @java.lang.Override
            public boolean hasGroupJoinRequestsWithVersion() {
                return kindCase_ == 17;
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             *
             * @return The groupJoinRequestsWithVersion.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion getGroupJoinRequestsWithVersion() {
                if (groupJoinRequestsWithVersionBuilder_ == null) {
                    if (kindCase_ == 17) {
                        return (im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 17) {
                        return groupJoinRequestsWithVersionBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            public Builder setGroupJoinRequestsWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion value) {
                if (groupJoinRequestsWithVersionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    groupJoinRequestsWithVersionBuilder_.setMessage(value);
                }
                kindCase_ = 17;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            public Builder setGroupJoinRequestsWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion.Builder builderForValue) {
                if (groupJoinRequestsWithVersionBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    groupJoinRequestsWithVersionBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 17;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            public Builder mergeGroupJoinRequestsWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion value) {
                if (groupJoinRequestsWithVersionBuilder_ == null) {
                    if (kindCase_ == 17
                            && kind_ != im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 17) {
                        groupJoinRequestsWithVersionBuilder_.mergeFrom(value);
                    } else {
                        groupJoinRequestsWithVersionBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 17;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            public Builder clearGroupJoinRequestsWithVersion() {
                if (groupJoinRequestsWithVersionBuilder_ == null) {
                    if (kindCase_ == 17) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 17) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    groupJoinRequestsWithVersionBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            public im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion.Builder getGroupJoinRequestsWithVersionBuilder() {
                return getGroupJoinRequestsWithVersionFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOrBuilder getGroupJoinRequestsWithVersionOrBuilder() {
                if ((kindCase_ == 17) && (groupJoinRequestsWithVersionBuilder_ != null)) {
                    return groupJoinRequestsWithVersionBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 17) {
                        return (im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.GroupJoinRequestsWithVersion group_join_requests_with_version = 17;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion, im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion.Builder, im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersionOrBuilder> getGroupJoinRequestsWithVersionFieldBuilder() {
                if (groupJoinRequestsWithVersionBuilder_ == null) {
                    if (!(kindCase_ == 17)) {
                        kind_ = im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion
                                .getDefaultInstance();
                    }
                    groupJoinRequestsWithVersionBuilder_ =
                            new com.google.protobuf.SingleFieldBuilderV3<>(
                                    (im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion) kind_,
                                    getParentForChildren(),
                                    isClean());
                    kind_ = null;
                }
                kindCase_ = 17;
                onChanged();
                return groupJoinRequestsWithVersionBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion.Builder, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOrBuilder> groupJoinQuestionsWithVersionBuilder_;

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             *
             * @return Whether the groupJoinQuestionsWithVersion field is set.
             */
            @java.lang.Override
            public boolean hasGroupJoinQuestionsWithVersion() {
                return kindCase_ == 18;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             *
             * @return The groupJoinQuestionsWithVersion.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion getGroupJoinQuestionsWithVersion() {
                if (groupJoinQuestionsWithVersionBuilder_ == null) {
                    if (kindCase_ == 18) {
                        return (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 18) {
                        return groupJoinQuestionsWithVersionBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            public Builder setGroupJoinQuestionsWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion value) {
                if (groupJoinQuestionsWithVersionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    groupJoinQuestionsWithVersionBuilder_.setMessage(value);
                }
                kindCase_ = 18;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            public Builder setGroupJoinQuestionsWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion.Builder builderForValue) {
                if (groupJoinQuestionsWithVersionBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    groupJoinQuestionsWithVersionBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 18;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            public Builder mergeGroupJoinQuestionsWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion value) {
                if (groupJoinQuestionsWithVersionBuilder_ == null) {
                    if (kindCase_ == 18
                            && kind_ != im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 18) {
                        groupJoinQuestionsWithVersionBuilder_.mergeFrom(value);
                    } else {
                        groupJoinQuestionsWithVersionBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 18;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            public Builder clearGroupJoinQuestionsWithVersion() {
                if (groupJoinQuestionsWithVersionBuilder_ == null) {
                    if (kindCase_ == 18) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 18) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    groupJoinQuestionsWithVersionBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion.Builder getGroupJoinQuestionsWithVersionBuilder() {
                return getGroupJoinQuestionsWithVersionFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOrBuilder getGroupJoinQuestionsWithVersionOrBuilder() {
                if ((kindCase_ == 18) && (groupJoinQuestionsWithVersionBuilder_ != null)) {
                    return groupJoinQuestionsWithVersionBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 18) {
                        return (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.GroupJoinQuestionsWithVersion group_join_questions_with_version = 18;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion.Builder, im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersionOrBuilder> getGroupJoinQuestionsWithVersionFieldBuilder() {
                if (groupJoinQuestionsWithVersionBuilder_ == null) {
                    if (!(kindCase_ == 18)) {
                        kind_ = im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion
                                .getDefaultInstance();
                    }
                    groupJoinQuestionsWithVersionBuilder_ =
                            new com.google.protobuf.SingleFieldBuilderV3<>(
                                    (im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion) kind_,
                                    getParentForChildren(),
                                    isClean());
                    kind_ = null;
                }
                kindCase_ = 18;
                onChanged();
                return groupJoinQuestionsWithVersionBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion, im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion.Builder, im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOrBuilder> groupMembersWithVersionBuilder_;

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             *
             * @return Whether the groupMembersWithVersion field is set.
             */
            @java.lang.Override
            public boolean hasGroupMembersWithVersion() {
                return kindCase_ == 19;
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             *
             * @return The groupMembersWithVersion.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion getGroupMembersWithVersion() {
                if (groupMembersWithVersionBuilder_ == null) {
                    if (kindCase_ == 19) {
                        return (im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 19) {
                        return groupMembersWithVersionBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            public Builder setGroupMembersWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion value) {
                if (groupMembersWithVersionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    groupMembersWithVersionBuilder_.setMessage(value);
                }
                kindCase_ = 19;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            public Builder setGroupMembersWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion.Builder builderForValue) {
                if (groupMembersWithVersionBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    groupMembersWithVersionBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 19;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            public Builder mergeGroupMembersWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion value) {
                if (groupMembersWithVersionBuilder_ == null) {
                    if (kindCase_ == 19
                            && kind_ != im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 19) {
                        groupMembersWithVersionBuilder_.mergeFrom(value);
                    } else {
                        groupMembersWithVersionBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 19;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            public Builder clearGroupMembersWithVersion() {
                if (groupMembersWithVersionBuilder_ == null) {
                    if (kindCase_ == 19) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 19) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    groupMembersWithVersionBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            public im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion.Builder getGroupMembersWithVersionBuilder() {
                return getGroupMembersWithVersionFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOrBuilder getGroupMembersWithVersionOrBuilder() {
                if ((kindCase_ == 19) && (groupMembersWithVersionBuilder_ != null)) {
                    return groupMembersWithVersionBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 19) {
                        return (im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.GroupMembersWithVersion group_members_with_version = 19;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion, im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion.Builder, im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersionOrBuilder> getGroupMembersWithVersionFieldBuilder() {
                if (groupMembersWithVersionBuilder_ == null) {
                    if (!(kindCase_ == 19)) {
                        kind_ = im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion
                                .getDefaultInstance();
                    }
                    groupMembersWithVersionBuilder_ =
                            new com.google.protobuf.SingleFieldBuilderV3<>(
                                    (im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion) kind_,
                                    getParentForChildren(),
                                    isClean());
                    kind_ = null;
                }
                kindCase_ = 19;
                onChanged();
                return groupMembersWithVersionBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupsWithVersion, im.turms.server.common.access.client.dto.model.group.GroupsWithVersion.Builder, im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOrBuilder> groupsWithVersionBuilder_;

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             *
             * @return Whether the groupsWithVersion field is set.
             */
            @java.lang.Override
            public boolean hasGroupsWithVersion() {
                return kindCase_ == 20;
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             *
             * @return The groupsWithVersion.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.group.GroupsWithVersion getGroupsWithVersion() {
                if (groupsWithVersionBuilder_ == null) {
                    if (kindCase_ == 20) {
                        return (im.turms.server.common.access.client.dto.model.group.GroupsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupsWithVersion
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 20) {
                        return groupsWithVersionBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            public Builder setGroupsWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupsWithVersion value) {
                if (groupsWithVersionBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    groupsWithVersionBuilder_.setMessage(value);
                }
                kindCase_ = 20;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            public Builder setGroupsWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupsWithVersion.Builder builderForValue) {
                if (groupsWithVersionBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    groupsWithVersionBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 20;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            public Builder mergeGroupsWithVersion(
                    im.turms.server.common.access.client.dto.model.group.GroupsWithVersion value) {
                if (groupsWithVersionBuilder_ == null) {
                    if (kindCase_ == 20
                            && kind_ != im.turms.server.common.access.client.dto.model.group.GroupsWithVersion
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.group.GroupsWithVersion
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.group.GroupsWithVersion) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 20) {
                        groupsWithVersionBuilder_.mergeFrom(value);
                    } else {
                        groupsWithVersionBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 20;
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            public Builder clearGroupsWithVersion() {
                if (groupsWithVersionBuilder_ == null) {
                    if (kindCase_ == 20) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 20) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    groupsWithVersionBuilder_.clear();
                }
                return this;
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            public im.turms.server.common.access.client.dto.model.group.GroupsWithVersion.Builder getGroupsWithVersionBuilder() {
                return getGroupsWithVersionFieldBuilder().getBuilder();
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOrBuilder getGroupsWithVersionOrBuilder() {
                if ((kindCase_ == 20) && (groupsWithVersionBuilder_ != null)) {
                    return groupsWithVersionBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 20) {
                        return (im.turms.server.common.access.client.dto.model.group.GroupsWithVersion) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.group.GroupsWithVersion
                            .getDefaultInstance();
                }
            }

            /**
             * <code>.im.turms.proto.GroupsWithVersion groups_with_version = 20;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.group.GroupsWithVersion, im.turms.server.common.access.client.dto.model.group.GroupsWithVersion.Builder, im.turms.server.common.access.client.dto.model.group.GroupsWithVersionOrBuilder> getGroupsWithVersionFieldBuilder() {
                if (groupsWithVersionBuilder_ == null) {
                    if (!(kindCase_ == 20)) {
                        kind_ = im.turms.server.common.access.client.dto.model.group.GroupsWithVersion
                                .getDefaultInstance();
                    }
                    groupsWithVersionBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                            (im.turms.server.common.access.client.dto.model.group.GroupsWithVersion) kind_,
                            getParentForChildren(),
                            isClean());
                    kind_ = null;
                }
                kindCase_ = 20;
                onChanged();
                return groupsWithVersionBuilder_;
            }

            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos, im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos.Builder, im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOrBuilder> storageResourceInfosBuilder_;

            /**
             * <pre>
             * Storage
             * </pre>
             * 
             * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
             *
             * @return Whether the storageResourceInfos field is set.
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
             *
             * @return The storageResourceInfos.
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos getStorageResourceInfos() {
                if (storageResourceInfosBuilder_ == null) {
                    if (kindCase_ == 50) {
                        return (im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos
                            .getDefaultInstance();
                } else {
                    if (kindCase_ == 50) {
                        return storageResourceInfosBuilder_.getMessage();
                    }
                    return im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos
                            .getDefaultInstance();
                }
            }

            /**
             * <pre>
             * Storage
             * </pre>
             * 
             * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
             */
            public Builder setStorageResourceInfos(
                    im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos value) {
                if (storageResourceInfosBuilder_ == null) {
                    if (value == null) {
                        throw new NullPointerException();
                    }
                    kind_ = value;
                    onChanged();
                } else {
                    storageResourceInfosBuilder_.setMessage(value);
                }
                kindCase_ = 50;
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
                    im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos.Builder builderForValue) {
                if (storageResourceInfosBuilder_ == null) {
                    kind_ = builderForValue.build();
                    onChanged();
                } else {
                    storageResourceInfosBuilder_.setMessage(builderForValue.build());
                }
                kindCase_ = 50;
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
                    im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos value) {
                if (storageResourceInfosBuilder_ == null) {
                    if (kindCase_ == 50
                            && kind_ != im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos
                                    .getDefaultInstance()) {
                        kind_ = im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos
                                .newBuilder(
                                        (im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos) kind_)
                                .mergeFrom(value)
                                .buildPartial();
                    } else {
                        kind_ = value;
                    }
                    onChanged();
                } else {
                    if (kindCase_ == 50) {
                        storageResourceInfosBuilder_.mergeFrom(value);
                    } else {
                        storageResourceInfosBuilder_.setMessage(value);
                    }
                }
                kindCase_ = 50;
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
                if (storageResourceInfosBuilder_ == null) {
                    if (kindCase_ == 50) {
                        kindCase_ = 0;
                        kind_ = null;
                        onChanged();
                    }
                } else {
                    if (kindCase_ == 50) {
                        kindCase_ = 0;
                        kind_ = null;
                    }
                    storageResourceInfosBuilder_.clear();
                }
                return this;
            }

            /**
             * <pre>
             * Storage
             * </pre>
             * 
             * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
             */
            public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos.Builder getStorageResourceInfosBuilder() {
                return getStorageResourceInfosFieldBuilder().getBuilder();
            }

            /**
             * <pre>
             * Storage
             * </pre>
             * 
             * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
             */
            @java.lang.Override
            public im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOrBuilder getStorageResourceInfosOrBuilder() {
                if ((kindCase_ == 50) && (storageResourceInfosBuilder_ != null)) {
                    return storageResourceInfosBuilder_.getMessageOrBuilder();
                } else {
                    if (kindCase_ == 50) {
                        return (im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos) kind_;
                    }
                    return im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos
                            .getDefaultInstance();
                }
            }

            /**
             * <pre>
             * Storage
             * </pre>
             * 
             * <code>.im.turms.proto.StorageResourceInfos storage_resource_infos = 50;</code>
             */
            private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos, im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos.Builder, im.turms.server.common.access.client.dto.model.storage.StorageResourceInfosOrBuilder> getStorageResourceInfosFieldBuilder() {
                if (storageResourceInfosBuilder_ == null) {
                    if (!(kindCase_ == 50)) {
                        kind_ = im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos
                                .getDefaultInstance();
                    }
                    storageResourceInfosBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                            (im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos) kind_,
                            getParentForChildren(),
                            isClean());
                    kind_ = null;
                }
                kindCase_ = 50;
                onChanged();
                return storageResourceInfosBuilder_;
            }

            @java.lang.Override
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.setUnknownFields(unknownFields);
            }

            @java.lang.Override
            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return super.mergeUnknownFields(unknownFields);
            }

            // @@protoc_insertion_point(builder_scope:im.turms.proto.TurmsNotification.Data)
        }

        // @@protoc_insertion_point(class_scope:im.turms.proto.TurmsNotification.Data)
        private static final im.turms.server.common.access.client.dto.notification.TurmsNotification.Data DEFAULT_INSTANCE;

        static {
            DEFAULT_INSTANCE =
                    new im.turms.server.common.access.client.dto.notification.TurmsNotification.Data();
        }

        public static im.turms.server.common.access.client.dto.notification.TurmsNotification.Data getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<Data> PARSER =
                new com.google.protobuf.AbstractParser<>() {
                    @java.lang.Override
                    public Data parsePartialFrom(
                            com.google.protobuf.CodedInputStream input,
                            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                            throws com.google.protobuf.InvalidProtocolBufferException {
                        Builder builder = newBuilder();
                        try {
                            builder.mergeFrom(input, extensionRegistry);
                        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(builder.buildPartial());
                        } catch (com.google.protobuf.UninitializedMessageException e) {
                            throw e.asInvalidProtocolBufferException()
                                    .setUnfinishedMessage(builder.buildPartial());
                        } catch (java.io.IOException e) {
                            throw new com.google.protobuf.InvalidProtocolBufferException(e)
                                    .setUnfinishedMessage(builder.buildPartial());
                        }
                        return builder.buildPartial();
                    }
                };

        public static com.google.protobuf.Parser<Data> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<Data> getParserForType() {
            return PARSER;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.notification.TurmsNotification.Data getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    private int bitField0_;
    public static final int TIMESTAMP_FIELD_NUMBER = 1;
    private long timestamp_ = 0L;

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

    public static final int REQUEST_ID_FIELD_NUMBER = 4;
    private long requestId_ = 0L;

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

    public static final int CODE_FIELD_NUMBER = 5;
    private int code_ = 0;

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

    public static final int REASON_FIELD_NUMBER = 6;
    @SuppressWarnings("serial")
    private volatile java.lang.Object reason_ = "";

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
        java.lang.Object ref = reason_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            reason_ = s;
            return s;
        }
    }

    /**
     * <code>optional string reason = 6;</code>
     *
     * @return The bytes for reason.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getReasonBytes() {
        java.lang.Object ref = reason_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
            reason_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    public static final int DATA_FIELD_NUMBER = 7;
    private im.turms.server.common.access.client.dto.notification.TurmsNotification.Data data_;

    /**
     * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
     *
     * @return Whether the data field is set.
     */
    @java.lang.Override
    public boolean hasData() {
        return data_ != null;
    }

    /**
     * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
     *
     * @return The data.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.notification.TurmsNotification.Data getData() {
        return data_ == null
                ? im.turms.server.common.access.client.dto.notification.TurmsNotification.Data
                        .getDefaultInstance()
                : data_;
    }

    /**
     * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.notification.TurmsNotification.DataOrBuilder getDataOrBuilder() {
        return data_ == null
                ? im.turms.server.common.access.client.dto.notification.TurmsNotification.Data
                        .getDefaultInstance()
                : data_;
    }

    public static final int REQUESTER_ID_FIELD_NUMBER = 10;
    private long requesterId_ = 0L;

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

    public static final int CLOSE_STATUS_FIELD_NUMBER = 11;
    private int closeStatus_ = 0;

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

    public static final int RELAYED_REQUEST_FIELD_NUMBER = 12;
    private im.turms.server.common.access.client.dto.request.TurmsRequest relayedRequest_;

    /**
     * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
     *
     * @return Whether the relayedRequest field is set.
     */
    @java.lang.Override
    public boolean hasRelayedRequest() {
        return relayedRequest_ != null;
    }

    /**
     * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
     *
     * @return The relayedRequest.
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.TurmsRequest getRelayedRequest() {
        return relayedRequest_ == null
                ? im.turms.server.common.access.client.dto.request.TurmsRequest.getDefaultInstance()
                : relayedRequest_;
    }

    /**
     * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
     */
    @java.lang.Override
    public im.turms.server.common.access.client.dto.request.TurmsRequestOrBuilder getRelayedRequestOrBuilder() {
        return relayedRequest_ == null
                ? im.turms.server.common.access.client.dto.request.TurmsRequest.getDefaultInstance()
                : relayedRequest_;
    }

    private byte memoizedIsInitialized = -1;

    @java.lang.Override
    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }

        memoizedIsInitialized = 1;
        return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
        if (timestamp_ != 0L) {
            output.writeInt64(1, timestamp_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            output.writeInt64(4, requestId_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            output.writeInt32(5, code_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 6, reason_);
        }
        if (data_ != null) {
            output.writeMessage(7, getData());
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            output.writeInt64(10, requesterId_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            output.writeInt32(11, closeStatus_);
        }
        if (relayedRequest_ != null) {
            output.writeMessage(12, getRelayedRequest());
        }
        getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
            return size;
        }

        size = 0;
        if (timestamp_ != 0L) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(1, timestamp_);
        }
        if (((bitField0_ & 0x00000001) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(4, requestId_);
        }
        if (((bitField0_ & 0x00000002) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(5, code_);
        }
        if (((bitField0_ & 0x00000004) != 0)) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, reason_);
        }
        if (data_ != null) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(7, getData());
        }
        if (((bitField0_ & 0x00000008) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt64Size(10, requesterId_);
        }
        if (((bitField0_ & 0x00000010) != 0)) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(11, closeStatus_);
        }
        if (relayedRequest_ != null) {
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(12,
                    getRelayedRequest());
        }
        size += getUnknownFields().getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TurmsNotification other)) {
            return super.equals(obj);
        }

        if (getTimestamp() != other.getTimestamp()) {
            return false;
        }
        if (hasRequestId() != other.hasRequestId()) {
            return false;
        }
        if (hasRequestId()) {
            if (getRequestId() != other.getRequestId()) {
                return false;
            }
        }
        if (hasCode() != other.hasCode()) {
            return false;
        }
        if (hasCode()) {
            if (getCode() != other.getCode()) {
                return false;
            }
        }
        if (hasReason() != other.hasReason()) {
            return false;
        }
        if (hasReason()) {
            if (!getReason().equals(other.getReason())) {
                return false;
            }
        }
        if (hasData() != other.hasData()) {
            return false;
        }
        if (hasData()) {
            if (!getData().equals(other.getData())) {
                return false;
            }
        }
        if (hasRequesterId() != other.hasRequesterId()) {
            return false;
        }
        if (hasRequesterId()) {
            if (getRequesterId() != other.getRequesterId()) {
                return false;
            }
        }
        if (hasCloseStatus() != other.hasCloseStatus()) {
            return false;
        }
        if (hasCloseStatus()) {
            if (getCloseStatus() != other.getCloseStatus()) {
                return false;
            }
        }
        if (hasRelayedRequest() != other.hasRelayedRequest()) {
            return false;
        }
        if (hasRelayedRequest()) {
            if (!getRelayedRequest().equals(other.getRelayedRequest())) {
                return false;
            }
        }
        return getUnknownFields().equals(other.getUnknownFields());
    }

    @java.lang.Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getTimestamp());
        if (hasRequestId()) {
            hash = (37 * hash) + REQUEST_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getRequestId());
        }
        if (hasCode()) {
            hash = (37 * hash) + CODE_FIELD_NUMBER;
            hash = (53 * hash) + getCode();
        }
        if (hasReason()) {
            hash = (37 * hash) + REASON_FIELD_NUMBER;
            hash = (53 * hash) + getReason().hashCode();
        }
        if (hasData()) {
            hash = (37 * hash) + DATA_FIELD_NUMBER;
            hash = (53 * hash) + getData().hashCode();
        }
        if (hasRequesterId()) {
            hash = (37 * hash) + REQUESTER_ID_FIELD_NUMBER;
            hash = (53 * hash) + com.google.protobuf.Internal.hashLong(getRequesterId());
        }
        if (hasCloseStatus()) {
            hash = (37 * hash) + CLOSE_STATUS_FIELD_NUMBER;
            hash = (53 * hash) + getCloseStatus();
        }
        if (hasRelayedRequest()) {
            hash = (37 * hash) + RELAYED_REQUEST_FIELD_NUMBER;
            hash = (53 * hash) + getRelayedRequest().hashCode();
        }
        hash = (29 * hash) + getUnknownFields().hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification parseFrom(
            java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification parseFrom(
            java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification parseFrom(
            byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification parseFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification parseDelimitedFrom(
            java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification parseFrom(
            com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3
                .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(
            im.turms.server.common.access.client.dto.notification.TurmsNotification prototype) {
        return DEFAULT_INSTANCE.toBuilder()
                .mergeFrom(prototype);
    }

    @java.lang.Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE
                ? new Builder()
                : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
            com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        return new Builder(parent);
    }

    /**
     * Protobuf type {@code im.turms.proto.TurmsNotification}
     */
    public static final class Builder
            extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:im.turms.proto.TurmsNotification)
            im.turms.server.common.access.client.dto.notification.TurmsNotificationOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return im.turms.server.common.access.client.dto.notification.TurmsNotificationOuterClass.internal_static_im_turms_proto_TurmsNotification_descriptor;
        }

        @java.lang.Override
        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return im.turms.server.common.access.client.dto.notification.TurmsNotificationOuterClass.internal_static_im_turms_proto_TurmsNotification_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            im.turms.server.common.access.client.dto.notification.TurmsNotification.class,
                            im.turms.server.common.access.client.dto.notification.TurmsNotification.Builder.class);
        }

        // Construct using
        // im.turms.server.common.access.client.dto.notification.TurmsNotification.newBuilder()
        private Builder() {

        }

        private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            super(parent);

        }

        @java.lang.Override
        public Builder clear() {
            super.clear();
            bitField0_ = 0;
            timestamp_ = 0L;
            requestId_ = 0L;
            code_ = 0;
            reason_ = "";
            data_ = null;
            if (dataBuilder_ != null) {
                dataBuilder_.dispose();
                dataBuilder_ = null;
            }
            requesterId_ = 0L;
            closeStatus_ = 0;
            relayedRequest_ = null;
            if (relayedRequestBuilder_ != null) {
                relayedRequestBuilder_.dispose();
                relayedRequestBuilder_ = null;
            }
            return this;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return im.turms.server.common.access.client.dto.notification.TurmsNotificationOuterClass.internal_static_im_turms_proto_TurmsNotification_descriptor;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.notification.TurmsNotification getDefaultInstanceForType() {
            return im.turms.server.common.access.client.dto.notification.TurmsNotification
                    .getDefaultInstance();
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.notification.TurmsNotification build() {
            im.turms.server.common.access.client.dto.notification.TurmsNotification result =
                    buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @java.lang.Override
        public im.turms.server.common.access.client.dto.notification.TurmsNotification buildPartial() {
            im.turms.server.common.access.client.dto.notification.TurmsNotification result =
                    new im.turms.server.common.access.client.dto.notification.TurmsNotification(
                            this);
            if (bitField0_ != 0) {
                buildPartial0(result);
            }
            onBuilt();
            return result;
        }

        private void buildPartial0(
                im.turms.server.common.access.client.dto.notification.TurmsNotification result) {
            int from_bitField0_ = bitField0_;
            if (((from_bitField0_ & 0x00000001) != 0)) {
                result.timestamp_ = timestamp_;
            }
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000002) != 0)) {
                result.requestId_ = requestId_;
                to_bitField0_ |= 0x00000001;
            }
            if (((from_bitField0_ & 0x00000004) != 0)) {
                result.code_ = code_;
                to_bitField0_ |= 0x00000002;
            }
            if (((from_bitField0_ & 0x00000008) != 0)) {
                result.reason_ = reason_;
                to_bitField0_ |= 0x00000004;
            }
            if (((from_bitField0_ & 0x00000010) != 0)) {
                result.data_ = dataBuilder_ == null
                        ? data_
                        : dataBuilder_.build();
            }
            if (((from_bitField0_ & 0x00000020) != 0)) {
                result.requesterId_ = requesterId_;
                to_bitField0_ |= 0x00000008;
            }
            if (((from_bitField0_ & 0x00000040) != 0)) {
                result.closeStatus_ = closeStatus_;
                to_bitField0_ |= 0x00000010;
            }
            if (((from_bitField0_ & 0x00000080) != 0)) {
                result.relayedRequest_ = relayedRequestBuilder_ == null
                        ? relayedRequest_
                        : relayedRequestBuilder_.build();
            }
            result.bitField0_ |= to_bitField0_;
        }

        @java.lang.Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof im.turms.server.common.access.client.dto.notification.TurmsNotification) {
                return mergeFrom(
                        (im.turms.server.common.access.client.dto.notification.TurmsNotification) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(
                im.turms.server.common.access.client.dto.notification.TurmsNotification other) {
            if (other == im.turms.server.common.access.client.dto.notification.TurmsNotification
                    .getDefaultInstance()) {
                return this;
            }
            if (other.getTimestamp() != 0L) {
                setTimestamp(other.getTimestamp());
            }
            if (other.hasRequestId()) {
                setRequestId(other.getRequestId());
            }
            if (other.hasCode()) {
                setCode(other.getCode());
            }
            if (other.hasReason()) {
                reason_ = other.reason_;
                bitField0_ |= 0x00000008;
                onChanged();
            }
            if (other.hasData()) {
                mergeData(other.getData());
            }
            if (other.hasRequesterId()) {
                setRequesterId(other.getRequesterId());
            }
            if (other.hasCloseStatus()) {
                setCloseStatus(other.getCloseStatus());
            }
            if (other.hasRelayedRequest()) {
                mergeRelayedRequest(other.getRelayedRequest());
            }
            this.mergeUnknownFields(other.getUnknownFields());
            onChanged();
            return this;
        }

        @java.lang.Override
        public final boolean isInitialized() {
            return true;
        }

        @java.lang.Override
        public Builder mergeFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            if (extensionRegistry == null) {
                throw new java.lang.NullPointerException();
            }
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0 -> done = true;
                        case 8 -> {
                            timestamp_ = input.readInt64();
                            bitField0_ |= 0x00000001;
                        } // case 8
                        case 32 -> {
                            requestId_ = input.readInt64();
                            bitField0_ |= 0x00000002;
                        } // case 32
                        case 40 -> {
                            code_ = input.readInt32();
                            bitField0_ |= 0x00000004;
                        } // case 40
                        case 50 -> {
                            reason_ = input.readStringRequireUtf8();
                            bitField0_ |= 0x00000008;
                        } // case 50
                        case 58 -> {
                            input.readMessage(getDataFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000010;
                        } // case 58
                        case 80 -> {
                            requesterId_ = input.readInt64();
                            bitField0_ |= 0x00000020;
                        } // case 80
                        case 88 -> {
                            closeStatus_ = input.readInt32();
                            bitField0_ |= 0x00000040;
                        } // case 88
                        case 98 -> {
                            input.readMessage(getRelayedRequestFieldBuilder().getBuilder(),
                                    extensionRegistry);
                            bitField0_ |= 0x00000080;
                        } // case 98
                        default -> {
                            if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                                done = true; // was an endgroup tag
                            }
                        } // default:
                    } // switch (tag)
                } // while (!done)
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.unwrapIOException();
            } finally {
                onChanged();
            } // finally
            return this;
        }

        private int bitField0_;

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
         * @return This builder for chaining.
         */
        public Builder setTimestamp(long value) {

            timestamp_ = value;
            bitField0_ |= 0x00000001;
            onChanged();
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
            bitField0_ &= ~0x00000001;
            timestamp_ = 0L;
            onChanged();
            return this;
        }

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
            return ((bitField0_ & 0x00000002) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setRequestId(long value) {

            requestId_ = value;
            bitField0_ |= 0x00000002;
            onChanged();
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
            bitField0_ &= ~0x00000002;
            requestId_ = 0L;
            onChanged();
            return this;
        }

        private int code_;

        /**
         * <code>optional int32 code = 5;</code>
         *
         * @return Whether the code field is set.
         */
        @java.lang.Override
        public boolean hasCode() {
            return ((bitField0_ & 0x00000004) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setCode(int value) {

            code_ = value;
            bitField0_ |= 0x00000004;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 code = 5;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCode() {
            bitField0_ &= ~0x00000004;
            code_ = 0;
            onChanged();
            return this;
        }

        private java.lang.Object reason_ = "";

        /**
         * <code>optional string reason = 6;</code>
         *
         * @return Whether the reason field is set.
         */
        public boolean hasReason() {
            return ((bitField0_ & 0x00000008) != 0);
        }

        /**
         * <code>optional string reason = 6;</code>
         *
         * @return The reason.
         */
        public java.lang.String getReason() {
            java.lang.Object ref = reason_;
            if (!(ref instanceof java.lang.String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                reason_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string reason = 6;</code>
         *
         * @return The bytes for reason.
         */
        public com.google.protobuf.ByteString getReasonBytes() {
            java.lang.Object ref = reason_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
                reason_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string reason = 6;</code>
         *
         * @param value The reason to set.
         * @return This builder for chaining.
         */
        public Builder setReason(java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            reason_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional string reason = 6;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearReason() {
            reason_ = getDefaultInstance().getReason();
            bitField0_ &= ~0x00000008;
            onChanged();
            return this;
        }

        /**
         * <code>optional string reason = 6;</code>
         *
         * @param value The bytes for reason to set.
         * @return This builder for chaining.
         */
        public Builder setReasonBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);
            reason_ = value;
            bitField0_ |= 0x00000008;
            onChanged();
            return this;
        }

        private im.turms.server.common.access.client.dto.notification.TurmsNotification.Data data_;
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.notification.TurmsNotification.Data, im.turms.server.common.access.client.dto.notification.TurmsNotification.Data.Builder, im.turms.server.common.access.client.dto.notification.TurmsNotification.DataOrBuilder> dataBuilder_;

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         *
         * @return Whether the data field is set.
         */
        public boolean hasData() {
            return ((bitField0_ & 0x00000010) != 0);
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         *
         * @return The data.
         */
        public im.turms.server.common.access.client.dto.notification.TurmsNotification.Data getData() {
            if (dataBuilder_ == null) {
                return data_ == null
                        ? im.turms.server.common.access.client.dto.notification.TurmsNotification.Data
                                .getDefaultInstance()
                        : data_;
            } else {
                return dataBuilder_.getMessage();
            }
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        public Builder setData(
                im.turms.server.common.access.client.dto.notification.TurmsNotification.Data value) {
            if (dataBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                data_ = value;
            } else {
                dataBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        public Builder setData(
                im.turms.server.common.access.client.dto.notification.TurmsNotification.Data.Builder builderForValue) {
            if (dataBuilder_ == null) {
                data_ = builderForValue.build();
            } else {
                dataBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        public Builder mergeData(
                im.turms.server.common.access.client.dto.notification.TurmsNotification.Data value) {
            if (dataBuilder_ == null) {
                if (((bitField0_ & 0x00000010) != 0)
                        && data_ != null
                        && data_ != im.turms.server.common.access.client.dto.notification.TurmsNotification.Data
                                .getDefaultInstance()) {
                    getDataBuilder().mergeFrom(value);
                } else {
                    data_ = value;
                }
            } else {
                dataBuilder_.mergeFrom(value);
            }
            bitField0_ |= 0x00000010;
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        public Builder clearData() {
            bitField0_ &= ~0x00000010;
            data_ = null;
            if (dataBuilder_ != null) {
                dataBuilder_.dispose();
                dataBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        public im.turms.server.common.access.client.dto.notification.TurmsNotification.Data.Builder getDataBuilder() {
            bitField0_ |= 0x00000010;
            onChanged();
            return getDataFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        public im.turms.server.common.access.client.dto.notification.TurmsNotification.DataOrBuilder getDataOrBuilder() {
            if (dataBuilder_ != null) {
                return dataBuilder_.getMessageOrBuilder();
            } else {
                return data_ == null
                        ? im.turms.server.common.access.client.dto.notification.TurmsNotification.Data
                                .getDefaultInstance()
                        : data_;
            }
        }

        /**
         * <code>.im.turms.proto.TurmsNotification.Data data = 7;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.notification.TurmsNotification.Data, im.turms.server.common.access.client.dto.notification.TurmsNotification.Data.Builder, im.turms.server.common.access.client.dto.notification.TurmsNotification.DataOrBuilder> getDataFieldBuilder() {
            if (dataBuilder_ == null) {
                dataBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        getData(),
                        getParentForChildren(),
                        isClean());
                data_ = null;
            }
            return dataBuilder_;
        }

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
            return ((bitField0_ & 0x00000020) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setRequesterId(long value) {

            requesterId_ = value;
            bitField0_ |= 0x00000020;
            onChanged();
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
            bitField0_ &= ~0x00000020;
            requesterId_ = 0L;
            onChanged();
            return this;
        }

        private int closeStatus_;

        /**
         * <code>optional int32 close_status = 11;</code>
         *
         * @return Whether the closeStatus field is set.
         */
        @java.lang.Override
        public boolean hasCloseStatus() {
            return ((bitField0_ & 0x00000040) != 0);
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
         * @return This builder for chaining.
         */
        public Builder setCloseStatus(int value) {

            closeStatus_ = value;
            bitField0_ |= 0x00000040;
            onChanged();
            return this;
        }

        /**
         * <code>optional int32 close_status = 11;</code>
         *
         * @return This builder for chaining.
         */
        public Builder clearCloseStatus() {
            bitField0_ &= ~0x00000040;
            closeStatus_ = 0;
            onChanged();
            return this;
        }

        private im.turms.server.common.access.client.dto.request.TurmsRequest relayedRequest_;
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.TurmsRequest, im.turms.server.common.access.client.dto.request.TurmsRequest.Builder, im.turms.server.common.access.client.dto.request.TurmsRequestOrBuilder> relayedRequestBuilder_;

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         *
         * @return Whether the relayedRequest field is set.
         */
        public boolean hasRelayedRequest() {
            return ((bitField0_ & 0x00000080) != 0);
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         *
         * @return The relayedRequest.
         */
        public im.turms.server.common.access.client.dto.request.TurmsRequest getRelayedRequest() {
            if (relayedRequestBuilder_ == null) {
                return relayedRequest_ == null
                        ? im.turms.server.common.access.client.dto.request.TurmsRequest
                                .getDefaultInstance()
                        : relayedRequest_;
            } else {
                return relayedRequestBuilder_.getMessage();
            }
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        public Builder setRelayedRequest(
                im.turms.server.common.access.client.dto.request.TurmsRequest value) {
            if (relayedRequestBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                relayedRequest_ = value;
            } else {
                relayedRequestBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        public Builder setRelayedRequest(
                im.turms.server.common.access.client.dto.request.TurmsRequest.Builder builderForValue) {
            if (relayedRequestBuilder_ == null) {
                relayedRequest_ = builderForValue.build();
            } else {
                relayedRequestBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        public Builder mergeRelayedRequest(
                im.turms.server.common.access.client.dto.request.TurmsRequest value) {
            if (relayedRequestBuilder_ == null) {
                if (((bitField0_ & 0x00000080) != 0)
                        && relayedRequest_ != null
                        && relayedRequest_ != im.turms.server.common.access.client.dto.request.TurmsRequest
                                .getDefaultInstance()) {
                    getRelayedRequestBuilder().mergeFrom(value);
                } else {
                    relayedRequest_ = value;
                }
            } else {
                relayedRequestBuilder_.mergeFrom(value);
            }
            bitField0_ |= 0x00000080;
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        public Builder clearRelayedRequest() {
            bitField0_ &= ~0x00000080;
            relayedRequest_ = null;
            if (relayedRequestBuilder_ != null) {
                relayedRequestBuilder_.dispose();
                relayedRequestBuilder_ = null;
            }
            onChanged();
            return this;
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        public im.turms.server.common.access.client.dto.request.TurmsRequest.Builder getRelayedRequestBuilder() {
            bitField0_ |= 0x00000080;
            onChanged();
            return getRelayedRequestFieldBuilder().getBuilder();
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        public im.turms.server.common.access.client.dto.request.TurmsRequestOrBuilder getRelayedRequestOrBuilder() {
            if (relayedRequestBuilder_ != null) {
                return relayedRequestBuilder_.getMessageOrBuilder();
            } else {
                return relayedRequest_ == null
                        ? im.turms.server.common.access.client.dto.request.TurmsRequest
                                .getDefaultInstance()
                        : relayedRequest_;
            }
        }

        /**
         * <code>.im.turms.proto.TurmsRequest relayed_request = 12;</code>
         */
        private com.google.protobuf.SingleFieldBuilderV3<im.turms.server.common.access.client.dto.request.TurmsRequest, im.turms.server.common.access.client.dto.request.TurmsRequest.Builder, im.turms.server.common.access.client.dto.request.TurmsRequestOrBuilder> getRelayedRequestFieldBuilder() {
            if (relayedRequestBuilder_ == null) {
                relayedRequestBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<>(
                        getRelayedRequest(),
                        getParentForChildren(),
                        isClean());
                relayedRequest_ = null;
            }
            return relayedRequestBuilder_;
        }

        @java.lang.Override
        public final Builder setUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
        }

        @java.lang.Override
        public final Builder mergeUnknownFields(
                final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }

        // @@protoc_insertion_point(builder_scope:im.turms.proto.TurmsNotification)
    }

    // @@protoc_insertion_point(class_scope:im.turms.proto.TurmsNotification)
    private static final im.turms.server.common.access.client.dto.notification.TurmsNotification DEFAULT_INSTANCE;

    static {
        DEFAULT_INSTANCE =
                new im.turms.server.common.access.client.dto.notification.TurmsNotification();
    }

    public static im.turms.server.common.access.client.dto.notification.TurmsNotification getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<TurmsNotification> PARSER =
            new com.google.protobuf.AbstractParser<>() {
                @java.lang.Override
                public TurmsNotification parsePartialFrom(
                        com.google.protobuf.CodedInputStream input,
                        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                        throws com.google.protobuf.InvalidProtocolBufferException {
                    Builder builder = newBuilder();
                    try {
                        builder.mergeFrom(input, extensionRegistry);
                    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(builder.buildPartial());
                    } catch (com.google.protobuf.UninitializedMessageException e) {
                        throw e.asInvalidProtocolBufferException()
                                .setUnfinishedMessage(builder.buildPartial());
                    } catch (java.io.IOException e) {
                        throw new com.google.protobuf.InvalidProtocolBufferException(e)
                                .setUnfinishedMessage(builder.buildPartial());
                    }
                    return builder.buildPartial();
                }
            };

    public static com.google.protobuf.Parser<TurmsNotification> parser() {
        return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<TurmsNotification> getParserForType() {
        return PARSER;
    }

    @java.lang.Override
    public im.turms.server.common.access.client.dto.notification.TurmsNotification getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}
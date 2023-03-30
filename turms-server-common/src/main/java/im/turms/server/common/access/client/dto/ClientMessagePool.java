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

package im.turms.server.common.access.client.dto;

import io.netty.util.concurrent.FastThreadLocal;

import im.turms.server.common.access.client.dto.model.common.LongsWithVersion;
import im.turms.server.common.access.client.dto.model.common.StringsWithVersion;
import im.turms.server.common.access.client.dto.model.conversation.Conversations;
import im.turms.server.common.access.client.dto.model.conversation.GroupConversation;
import im.turms.server.common.access.client.dto.model.conversation.PrivateConversation;
import im.turms.server.common.access.client.dto.model.group.Group;
import im.turms.server.common.access.client.dto.model.group.GroupInvitation;
import im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion;
import im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion;
import im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult;
import im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion;
import im.turms.server.common.access.client.dto.model.group.GroupJoinRequest;
import im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion;
import im.turms.server.common.access.client.dto.model.group.GroupMember;
import im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion;
import im.turms.server.common.access.client.dto.model.group.GroupsWithVersion;
import im.turms.server.common.access.client.dto.model.message.Message;
import im.turms.server.common.access.client.dto.model.message.Messages;
import im.turms.server.common.access.client.dto.model.message.MessagesWithTotal;
import im.turms.server.common.access.client.dto.model.message.MessagesWithTotalList;
import im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo;
import im.turms.server.common.access.client.dto.model.storage.StorageResourceInfos;
import im.turms.server.common.access.client.dto.model.user.NearbyUser;
import im.turms.server.common.access.client.dto.model.user.NearbyUsers;
import im.turms.server.common.access.client.dto.model.user.UserFriendRequest;
import im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion;
import im.turms.server.common.access.client.dto.model.user.UserInfo;
import im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion;
import im.turms.server.common.access.client.dto.model.user.UserLocation;
import im.turms.server.common.access.client.dto.model.user.UserOnlineStatus;
import im.turms.server.common.access.client.dto.model.user.UserOnlineStatuses;
import im.turms.server.common.access.client.dto.model.user.UserRelationship;
import im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup;
import im.turms.server.common.access.client.dto.model.user.UserRelationshipGroupsWithVersion;
import im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest;

/**
 * Using cached builders is a tradeoff between code maintainability and efficiency. For better
 * efficiency, we can write our own code generator to make protobuf models mutable, but it is
 * troublesome, so we just cache these builders for better efficiency while don't need to modify the
 * generated codes.
 *
 * @author James Chen
 */
public class ClientMessagePool {

    private ClientMessagePool() {
    }

    private static final FastThreadLocal<Conversations.Builder> CONVERSATIONS =
            new FastThreadLocal<>() {
                @Override
                protected Conversations.Builder initialValue() {
                    return Conversations.newBuilder();
                }
            };

    private static final FastThreadLocal<CreateMessageRequest.Builder> CREATE_MESSAGE_REQUEST =
            new FastThreadLocal<>() {
                @Override
                protected CreateMessageRequest.Builder initialValue() {
                    return CreateMessageRequest.newBuilder();
                }
            };

    private static final FastThreadLocal<Group.Builder> GROUP = new FastThreadLocal<>() {
        @Override
        protected Group.Builder initialValue() {
            return Group.newBuilder();
        }
    };

    private static final FastThreadLocal<GroupConversation.Builder> GROUP_CONVERSATION =
            new FastThreadLocal<>() {
                @Override
                protected GroupConversation.Builder initialValue() {
                    return GroupConversation.newBuilder();
                }
            };

    private static final FastThreadLocal<GroupInvitation.Builder> GROUP_INVITATION =
            new FastThreadLocal<>() {
                @Override
                protected GroupInvitation.Builder initialValue() {
                    return GroupInvitation.newBuilder();
                }
            };

    private static final FastThreadLocal<GroupInvitationsWithVersion.Builder> GROUP_INVITATIONS_WITH_VERSION =
            new FastThreadLocal<>() {
                @Override
                protected GroupInvitationsWithVersion.Builder initialValue() {
                    return GroupInvitationsWithVersion.newBuilder();
                }
            };

    private static final FastThreadLocal<GroupJoinQuestion.Builder> GROUP_JOIN_QUESTION =
            new FastThreadLocal<>() {
                @Override
                protected GroupJoinQuestion.Builder initialValue() {
                    return GroupJoinQuestion.newBuilder();
                }
            };

    private static final FastThreadLocal<GroupJoinQuestionsAnswerResult.Builder> GROUP_JOIN_QUESTIONS_ANSWER_RESULT =
            new FastThreadLocal<>() {
                @Override
                protected GroupJoinQuestionsAnswerResult.Builder initialValue() {
                    return GroupJoinQuestionsAnswerResult.newBuilder();
                }
            };

    private static final FastThreadLocal<GroupJoinQuestionsWithVersion.Builder> GROUP_JOIN_QUESTIONS_WITH_VERSION =
            new FastThreadLocal<>() {
                @Override
                protected GroupJoinQuestionsWithVersion.Builder initialValue() {
                    return GroupJoinQuestionsWithVersion.newBuilder();
                }
            };

    private static final FastThreadLocal<GroupJoinRequest.Builder> GROUP_JOIN_REQUEST =
            new FastThreadLocal<>() {
                @Override
                protected GroupJoinRequest.Builder initialValue() {
                    return GroupJoinRequest.newBuilder();
                }
            };

    private static final FastThreadLocal<GroupJoinRequestsWithVersion.Builder> GROUP_JOIN_REQUESTS_WITH_VERSION =
            new FastThreadLocal<>() {
                @Override
                protected GroupJoinRequestsWithVersion.Builder initialValue() {
                    return GroupJoinRequestsWithVersion.newBuilder();
                }
            };

    private static final FastThreadLocal<GroupMember.Builder> GROUP_MEMBER =
            new FastThreadLocal<>() {
                @Override
                protected GroupMember.Builder initialValue() {
                    return GroupMember.newBuilder();
                }
            };

    private static final FastThreadLocal<GroupMembersWithVersion.Builder> GROUP_MEMBERS_WITH_VERSION =
            new FastThreadLocal<>() {
                @Override
                protected GroupMembersWithVersion.Builder initialValue() {
                    return GroupMembersWithVersion.newBuilder();
                }
            };

    private static final FastThreadLocal<GroupsWithVersion.Builder> GROUPS_WITH_VERSION =
            new FastThreadLocal<>() {
                @Override
                protected GroupsWithVersion.Builder initialValue() {
                    return GroupsWithVersion.newBuilder();
                }
            };

    private static final FastThreadLocal<LongsWithVersion.Builder> LONGS_WITH_VERSION =
            new FastThreadLocal<>() {
                @Override
                protected LongsWithVersion.Builder initialValue() {
                    return LongsWithVersion.newBuilder();
                }
            };

    private static final FastThreadLocal<Message.Builder> MESSAGE = new FastThreadLocal<>() {
        @Override
        protected Message.Builder initialValue() {
            return Message.newBuilder();
        }
    };

    private static final FastThreadLocal<Messages.Builder> MESSAGES = new FastThreadLocal<>() {
        @Override
        protected Messages.Builder initialValue() {
            return Messages.newBuilder();
        }
    };

    private static final FastThreadLocal<MessagesWithTotal.Builder> MESSAGES_WITH_TOTAL =
            new FastThreadLocal<>() {
                @Override
                protected MessagesWithTotal.Builder initialValue() {
                    return MessagesWithTotal.newBuilder();
                }
            };

    private static final FastThreadLocal<MessagesWithTotalList.Builder> MESSAGES_WITH_TOTAL_LIST =
            new FastThreadLocal<>() {
                @Override
                protected MessagesWithTotalList.Builder initialValue() {
                    return MessagesWithTotalList.newBuilder();
                }
            };

    private static final FastThreadLocal<NearbyUser.Builder> NEARBY_USER = new FastThreadLocal<>() {
        @Override
        protected NearbyUser.Builder initialValue() {
            return NearbyUser.newBuilder();
        }
    };

    private static final FastThreadLocal<NearbyUsers.Builder> NEARBY_USERS =
            new FastThreadLocal<>() {
                @Override
                protected NearbyUsers.Builder initialValue() {
                    return NearbyUsers.newBuilder();
                }
            };

    private static final FastThreadLocal<PrivateConversation.Builder> PRIVATE_CONVERSATION =
            new FastThreadLocal<>() {
                @Override
                protected PrivateConversation.Builder initialValue() {
                    return PrivateConversation.newBuilder();
                }
            };

    private static final FastThreadLocal<StorageResourceInfo.Builder> STORAGE_RESOURCE_INFO =
            new FastThreadLocal<>() {
                @Override
                protected StorageResourceInfo.Builder initialValue() {
                    return StorageResourceInfo.newBuilder();
                }
            };

    private static final FastThreadLocal<StorageResourceInfos.Builder> STORAGE_RESOURCE_INFOS =
            new FastThreadLocal<>() {
                @Override
                protected StorageResourceInfos.Builder initialValue() {
                    return StorageResourceInfos.newBuilder();
                }
            };

    private static final FastThreadLocal<StringsWithVersion.Builder> STRINGS_WITH_VERSION =
            new FastThreadLocal<>() {
                @Override
                protected StringsWithVersion.Builder initialValue() {
                    return StringsWithVersion.newBuilder();
                }
            };

    private static final FastThreadLocal<TurmsNotification.Builder> TURMS_NOTIFICATION =
            new FastThreadLocal<>() {
                @Override
                protected TurmsNotification.Builder initialValue() {
                    return TurmsNotification.newBuilder();
                }
            };

    private static final FastThreadLocal<TurmsNotification.Data.Builder> TURMS_NOTIFICATION_DATA =
            new FastThreadLocal<>() {
                @Override
                protected TurmsNotification.Data.Builder initialValue() {
                    return TurmsNotification.Data.newBuilder();
                }
            };

    private static final FastThreadLocal<TurmsRequest.Builder> TURMS_REQUEST =
            new FastThreadLocal<>() {
                @Override
                protected TurmsRequest.Builder initialValue() {
                    return TurmsRequest.newBuilder();
                }
            };

    private static final FastThreadLocal<UpdateUserOnlineStatusRequest.Builder> UPDATE_USER_ONLINE_STATUS_REQUEST =
            new FastThreadLocal<>() {
                @Override
                protected UpdateUserOnlineStatusRequest.Builder initialValue() {
                    return UpdateUserOnlineStatusRequest.newBuilder();
                }
            };

    private static final FastThreadLocal<UserFriendRequest.Builder> USER_FRIEND_REQUEST =
            new FastThreadLocal<>() {
                @Override
                protected UserFriendRequest.Builder initialValue() {
                    return UserFriendRequest.newBuilder();
                }
            };

    private static final FastThreadLocal<UserFriendRequestsWithVersion.Builder> USER_FRIEND_REQUESTS_WITH_VERSION =
            new FastThreadLocal<>() {
                @Override
                protected UserFriendRequestsWithVersion.Builder initialValue() {
                    return UserFriendRequestsWithVersion.newBuilder();
                }
            };

    private static final FastThreadLocal<UserInfo.Builder> USER_INFO = new FastThreadLocal<>() {
        @Override
        protected UserInfo.Builder initialValue() {
            return UserInfo.newBuilder();
        }
    };

    private static final FastThreadLocal<UserInfosWithVersion.Builder> USER_INFOS_WITH_VERSION =
            new FastThreadLocal<>() {
                @Override
                protected UserInfosWithVersion.Builder initialValue() {
                    return UserInfosWithVersion.newBuilder();
                }
            };

    private static final FastThreadLocal<UserLocation.Builder> USER_LOCATION =
            new FastThreadLocal<>() {
                @Override
                protected UserLocation.Builder initialValue() {
                    return UserLocation.newBuilder();
                }
            };

    private static final FastThreadLocal<UserOnlineStatus.Builder> USER_ONLINE_STATUS =
            new FastThreadLocal<>() {
                @Override
                protected UserOnlineStatus.Builder initialValue() {
                    return UserOnlineStatus.newBuilder();
                }
            };

    private static final FastThreadLocal<UserOnlineStatuses.Builder> USERS_ONLINE_STATUSES =
            new FastThreadLocal<>() {
                @Override
                protected UserOnlineStatuses.Builder initialValue() {
                    return UserOnlineStatuses.newBuilder();
                }
            };

    private static final FastThreadLocal<UserRelationship.Builder> USER_RELATIONSHIP =
            new FastThreadLocal<>() {
                @Override
                protected UserRelationship.Builder initialValue() {
                    return UserRelationship.newBuilder();
                }
            };

    private static final FastThreadLocal<UserRelationshipGroup.Builder> USER_RELATIONSHIP_GROUP =
            new FastThreadLocal<>() {
                @Override
                protected UserRelationshipGroup.Builder initialValue() {
                    return UserRelationshipGroup.newBuilder();
                }
            };

    private static final FastThreadLocal<UserRelationshipGroupsWithVersion.Builder> USER_RELATIONSHIP_GROUPS_WITH_VERSION =
            new FastThreadLocal<>() {
                @Override
                protected UserRelationshipGroupsWithVersion.Builder initialValue() {
                    return UserRelationshipGroupsWithVersion.newBuilder();
                }
            };

    private static final FastThreadLocal<UserRelationshipsWithVersion.Builder> USER_RELATIONSHIPS_WITH_VERSION =
            new FastThreadLocal<>() {
                @Override
                protected UserRelationshipsWithVersion.Builder initialValue() {
                    return UserRelationshipsWithVersion.newBuilder();
                }
            };

    public static Conversations.Builder getConversationsBuilder() {
        return CONVERSATIONS.get()
                .clear();
    }

    public static CreateMessageRequest.Builder getCreateMessageRequestBuilder() {
        return CREATE_MESSAGE_REQUEST.get()
                .clear();
    }

    public static Group.Builder getGroupBuilder() {
        return GROUP.get()
                .clear();
    }

    public static GroupConversation.Builder getGroupConversationBuilder() {
        return GROUP_CONVERSATION.get()
                .clear();
    }

    public static GroupInvitation.Builder getGroupInvitationBuilder() {
        return GROUP_INVITATION.get()
                .clear();
    }

    public static GroupInvitationsWithVersion.Builder getGroupInvitationsWithVersionBuilder() {
        return GROUP_INVITATIONS_WITH_VERSION.get()
                .clear();
    }

    public static GroupJoinQuestion.Builder getGroupJoinQuestionBuilder() {
        return GROUP_JOIN_QUESTION.get()
                .clear();
    }

    public static GroupJoinQuestionsAnswerResult.Builder getGroupJoinQuestionsAnswerResultBuilder() {
        return GROUP_JOIN_QUESTIONS_ANSWER_RESULT.get()
                .clear();
    }

    public static GroupJoinQuestionsWithVersion.Builder getGroupJoinQuestionsWithVersionBuilder() {
        return GROUP_JOIN_QUESTIONS_WITH_VERSION.get()
                .clear();
    }

    public static GroupJoinRequest.Builder getGroupJoinRequestBuilder() {
        return GROUP_JOIN_REQUEST.get()
                .clear();
    }

    public static GroupJoinRequestsWithVersion.Builder getGroupJoinRequestsWithVersionBuilder() {
        return GROUP_JOIN_REQUESTS_WITH_VERSION.get()
                .clear();
    }

    public static GroupMember.Builder getGroupMemberBuilder() {
        return GROUP_MEMBER.get()
                .clear();
    }

    public static GroupMembersWithVersion.Builder getGroupMembersWithVersionBuilder() {
        return GROUP_MEMBERS_WITH_VERSION.get()
                .clear();
    }

    public static GroupsWithVersion.Builder getGroupsWithVersionBuilder() {
        return GROUPS_WITH_VERSION.get()
                .clear();
    }

    public static LongsWithVersion.Builder getLongsWithVersionBuilder() {
        return LONGS_WITH_VERSION.get()
                .clear();
    }

    public static Message.Builder getMessageBuilder() {
        return MESSAGE.get()
                .clear();
    }

    public static Messages.Builder getMessagesBuilder() {
        return MESSAGES.get()
                .clear();
    }

    public static MessagesWithTotal.Builder getMessagesWithTotalBuilder() {
        return MESSAGES_WITH_TOTAL.get()
                .clear();
    }

    public static MessagesWithTotalList.Builder getMessagesWithTotalListBuilder() {
        return MESSAGES_WITH_TOTAL_LIST.get()
                .clear();
    }

    public static NearbyUser.Builder getNearbyUserBuilder() {
        return NEARBY_USER.get()
                .clear();
    }

    public static NearbyUsers.Builder getNearbyUsersBuilder() {
        return NEARBY_USERS.get()
                .clear();
    }

    public static PrivateConversation.Builder getPrivateConversationBuilder() {
        return PRIVATE_CONVERSATION.get()
                .clear();
    }

    public static StorageResourceInfo.Builder getStorageResourceInfoBuilder() {
        return STORAGE_RESOURCE_INFO.get()
                .clear();
    }

    public static StorageResourceInfos.Builder getStorageResourceInfosBuilder() {
        return STORAGE_RESOURCE_INFOS.get()
                .clear();
    }

    public static StringsWithVersion.Builder getStringsWithVersionBuilder() {
        return STRINGS_WITH_VERSION.get()
                .clear();
    }

    public static TurmsNotification.Builder getTurmsNotificationBuilder() {
        return TURMS_NOTIFICATION.get()
                .clear();
    }

    public static TurmsNotification.Data.Builder getTurmsNotificationDataBuilder() {
        return TURMS_NOTIFICATION_DATA.get()
                .clear();
    }

    public static TurmsRequest.Builder getTurmsRequestBuilder() {
        return TURMS_REQUEST.get()
                .clear();
    }

    public static UpdateUserOnlineStatusRequest.Builder getUpdateUserOnlineStatusRequestBuilder() {
        return UPDATE_USER_ONLINE_STATUS_REQUEST.get()
                .clear();
    }

    public static UserFriendRequest.Builder getUserFriendRequestBuilder() {
        return USER_FRIEND_REQUEST.get()
                .clear();
    }

    public static UserFriendRequestsWithVersion.Builder getUserFriendRequestsWithVersionBuilder() {
        return USER_FRIEND_REQUESTS_WITH_VERSION.get()
                .clear();
    }

    public static UserInfo.Builder getUserInfoBuilder() {
        return USER_INFO.get()
                .clear();
    }

    public static UserInfosWithVersion.Builder getUserInfosWithVersionBuilder() {
        return USER_INFOS_WITH_VERSION.get()
                .clear();
    }

    public static UserLocation.Builder getUserLocationBuilder() {
        return USER_LOCATION.get()
                .clear();
    }

    public static UserOnlineStatus.Builder getUserOnlineStatusBuilder() {
        return USER_ONLINE_STATUS.get()
                .clear();
    }

    public static UserOnlineStatuses.Builder getUsersOnlineStatusesBuilder() {
        return USERS_ONLINE_STATUSES.get()
                .clear();
    }

    public static UserRelationship.Builder getUserRelationshipBuilder() {
        return USER_RELATIONSHIP.get()
                .clear();
    }

    public static UserRelationshipGroup.Builder getUserRelationshipGroupBuilder() {
        return USER_RELATIONSHIP_GROUP.get()
                .clear();
    }

    public static UserRelationshipGroupsWithVersion.Builder getUserRelationshipGroupsWithVersionBuilder() {
        return USER_RELATIONSHIP_GROUPS_WITH_VERSION.get()
                .clear();
    }

    public static UserRelationshipsWithVersion.Builder getUserRelationshipsWithVersionBuilder() {
        return USER_RELATIONSHIPS_WITH_VERSION.get()
                .clear();
    }

}
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

package im.turms.service.domain.storage.service;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.web.MediaType;
import im.turms.server.common.access.client.dto.constant.StorageResourceType;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.validation.Validator;
import im.turms.service.domain.storage.bo.StorageResourceInfo;
import im.turms.service.infra.plugin.extension.StorageServiceProvider;

/**
 * @author James Chen
 */
@Service
public class StorageService {

    private static final Mono STORAGE_NOT_IMPLEMENTED =
            Mono.error(ResponseException.get(ResponseStatusCode.STORAGE_NOT_IMPLEMENTED));
    private static final Mono<Void> DELETE_RESOURCE_NOT_IMPLEMENTED = STORAGE_NOT_IMPLEMENTED;
    private static final Mono<Map<String, String>> QUERY_RESOURCE_DOWNLOAD_INFO_NOT_IMPLEMENTED =
            STORAGE_NOT_IMPLEMENTED;
    private static final Mono<Map<String, String>> QUERY_RESOURCE_UPLOAD_INFO_NOT_IMPLEMENTED =
            STORAGE_NOT_IMPLEMENTED;
    private static final Mono<Void> SHARE_MESSAGE_ATTACHMENT_WITH_USER_NOT_IMPLEMENTED =
            STORAGE_NOT_IMPLEMENTED;
    private static final Mono<Void> SHARE_MESSAGE_ATTACHMENT_WITH_GROUP_NOT_IMPLEMENTED =
            STORAGE_NOT_IMPLEMENTED;
    private static final Mono<Void> UNSHARE_MESSAGE_ATTACHMENT_WITH_USER_NOT_IMPLEMENTED =
            STORAGE_NOT_IMPLEMENTED;
    private static final Mono<Void> UNSHARE_MESSAGE_ATTACHMENT_WITH_GROUP_NOT_IMPLEMENTED =
            STORAGE_NOT_IMPLEMENTED;
    private static final Mono<List<StorageResourceInfo>> QUERY_MESSAGE_ATTACHMENT_INFOS_UPLOADED_BY_REQUESTER_NOT_IMPLEMENTED =
            STORAGE_NOT_IMPLEMENTED;
    private static final Mono<List<StorageResourceInfo>> QUERY_MESSAGE_ATTACHMENT_INFOS_IN_PRIVATE_CONVERSATION_NOT_IMPLEMENTED =
            STORAGE_NOT_IMPLEMENTED;
    private static final Mono<List<StorageResourceInfo>> QUERY_MESSAGE_ATTACHMENT_INFOS_IN_GROUP_CONVERSATION_NOT_IMPLEMENTED =
            STORAGE_NOT_IMPLEMENTED;

    // User profile picture
    private static final Method DELETE_USER_PROFILE_PICTURE_METHOD;
    private static final Method QUERY_USER_PROFILE_PICTURE_UPLOAD_INFO_METHOD;
    private static final Method QUERY_USER_PROFILE_PICTURE_DOWNLOAD_INFO_METHOD;

    // Group profile picture
    private static final Method DELETE_GROUP_PROFILE_PICTURE_METHOD;
    private static final Method QUERY_GROUP_PROFILE_PICTURE_UPLOAD_INFO_METHOD;
    private static final Method QUERY_GROUP_PROFILE_PICTURE_DOWNLOAD_INFO_METHOD;

    // Message attachment
    private static final Method DELETE_MESSAGE_ATTACHMENT_METHOD;
    private static final Method SHARE_MESSAGE_ATTACHMENT_WITH_USER_METHOD;
    private static final Method SHARE_MESSAGE_ATTACHMENT_WITH_GROUP_METHOD;
    private static final Method UNSHARE_MESSAGE_ATTACHMENT_WITH_USER_METHOD;
    private static final Method UNSHARE_MESSAGE_ATTACHMENT_WITH_GROUP_METHOD;
    private static final Method QUERY_MESSAGE_ATTACHMENT_UPLOAD_INFO_METHOD;
    private static final Method QUERY_MESSAGE_ATTACHMENT_UPLOAD_INFO_IN_PRIVATE_CONVERSATION_METHOD;
    private static final Method QUERY_MESSAGE_ATTACHMENT_UPLOAD_INFO_IN_GROUP_CONVERSATION_METHOD;
    private static final Method QUERY_MESSAGE_ATTACHMENT_DOWNLOAD_INFO_METHOD;

    private static final Method QUERY_MESSAGE_ATTACHMENT_INFOS_UPLOADED_BY_REQUESTER_METHOD;
    private static final Method QUERY_MESSAGE_ATTACHMENT_INFOS_IN_PRIVATE_CONVERSATIONS_METHOD;
    private static final Method QUERY_MESSAGE_ATTACHMENT_INFOS_IN_GROUP_CONVERSATIONS_METHOD;

    private final PluginManager pluginManager;

    static {
        try {
            DELETE_USER_PROFILE_PICTURE_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("deleteUserProfilePicture", Long.class, Map.class);
            QUERY_USER_PROFILE_PICTURE_UPLOAD_INFO_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("queryUserProfilePictureUploadInfo",
                            Long.class,
                            String.class,
                            MediaType.class,
                            Map.class);
            QUERY_USER_PROFILE_PICTURE_DOWNLOAD_INFO_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("queryUserProfilePictureDownloadInfo",
                            Long.class,
                            Long.class,
                            Map.class);

            DELETE_GROUP_PROFILE_PICTURE_METHOD =
                    StorageServiceProvider.class.getDeclaredMethod("deleteGroupProfilePicture",
                            Long.class,
                            Long.class,
                            Map.class);
            QUERY_GROUP_PROFILE_PICTURE_UPLOAD_INFO_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("queryGroupProfilePictureUploadInfo",
                            Long.class,
                            Long.class,
                            String.class,
                            MediaType.class,
                            Map.class);
            QUERY_GROUP_PROFILE_PICTURE_DOWNLOAD_INFO_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("queryGroupProfilePictureDownloadInfo",
                            Long.class,
                            Long.class,
                            Map.class);

            DELETE_MESSAGE_ATTACHMENT_METHOD =
                    StorageServiceProvider.class.getDeclaredMethod("deleteMessageAttachment",
                            Long.class,
                            Long.class,
                            String.class,
                            Map.class);
            SHARE_MESSAGE_ATTACHMENT_WITH_USER_METHOD =
                    StorageServiceProvider.class.getDeclaredMethod("shareMessageAttachmentWithUser",
                            Long.class,
                            Long.class,
                            String.class,
                            Long.class);
            SHARE_MESSAGE_ATTACHMENT_WITH_GROUP_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("shareMessageAttachmentWithGroup",
                            Long.class,
                            Long.class,
                            String.class,
                            Long.class);
            UNSHARE_MESSAGE_ATTACHMENT_WITH_USER_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("unshareMessageAttachmentWithUser",
                            Long.class,
                            Long.class,
                            String.class,
                            Long.class);
            UNSHARE_MESSAGE_ATTACHMENT_WITH_GROUP_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("unshareMessageAttachmentWithGroup",
                            Long.class,
                            Long.class,
                            String.class,
                            Long.class);
            QUERY_MESSAGE_ATTACHMENT_UPLOAD_INFO_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("queryMessageAttachmentUploadInfo",
                            Long.class,
                            String.class,
                            MediaType.class,
                            Map.class);
            QUERY_MESSAGE_ATTACHMENT_UPLOAD_INFO_IN_PRIVATE_CONVERSATION_METHOD =
                    StorageServiceProvider.class.getDeclaredMethod(
                            "queryMessageAttachmentUploadInfoInPrivateConversation",
                            Long.class,
                            Long.class,
                            String.class,
                            MediaType.class,
                            Map.class);
            QUERY_MESSAGE_ATTACHMENT_UPLOAD_INFO_IN_GROUP_CONVERSATION_METHOD =
                    StorageServiceProvider.class.getDeclaredMethod(
                            "queryMessageAttachmentUploadInfoInGroupConversation",
                            Long.class,
                            Long.class,
                            String.class,
                            MediaType.class,
                            Map.class);
            QUERY_MESSAGE_ATTACHMENT_DOWNLOAD_INFO_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("queryMessageAttachmentDownloadInfo",
                            Long.class,
                            Long.class,
                            String.class,
                            Map.class);

            QUERY_MESSAGE_ATTACHMENT_INFOS_UPLOADED_BY_REQUESTER_METHOD =
                    StorageServiceProvider.class.getDeclaredMethod(
                            "queryMessageAttachmentInfosUploadedByRequester",
                            Long.class,
                            DateRange.class);
            QUERY_MESSAGE_ATTACHMENT_INFOS_IN_PRIVATE_CONVERSATIONS_METHOD =
                    StorageServiceProvider.class.getDeclaredMethod(
                            "queryMessageAttachmentInfosInPrivateConversations",
                            Long.class,
                            Set.class,
                            DateRange.class,
                            Boolean.class);
            QUERY_MESSAGE_ATTACHMENT_INFOS_IN_GROUP_CONVERSATIONS_METHOD =
                    StorageServiceProvider.class.getDeclaredMethod(
                            "queryMessageAttachmentInfosInGroupConversations",
                            Long.class,
                            Set.class,
                            Set.class,
                            DateRange.class);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleInternalChangeException(e);
        }
    }

    public StorageService(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public Mono<Void> deleteResource(
            Long requesterId,
            StorageResourceType resourceType,
            @Nullable Long resourceIdNum,
            @Nullable String resourceIdStr,
            Map<String, String> extra) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(resourceType, "resourceType");
            if (resourceType == StorageResourceType.UNRECOGNIZED) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The resource type must not be UNRECOGNIZED"));
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return switch (resourceType) {
            case USER_PROFILE_PICTURE ->
                pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                        DELETE_USER_PROFILE_PICTURE_METHOD,
                        DELETE_RESOURCE_NOT_IMPLEMENTED,
                        provider -> provider.deleteUserProfilePicture(requesterId, extra));
            case GROUP_PROFILE_PICTURE ->
                pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                        DELETE_GROUP_PROFILE_PICTURE_METHOD,
                        DELETE_RESOURCE_NOT_IMPLEMENTED,
                        provider -> {
                            if (resourceIdNum == null) {
                                return Mono.error(
                                        ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                                "The group ID must not be null"));
                            }
                            return provider
                                    .deleteGroupProfilePicture(requesterId, resourceIdNum, extra);
                        });
            case MESSAGE_ATTACHMENT ->
                pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                        DELETE_MESSAGE_ATTACHMENT_METHOD,
                        DELETE_RESOURCE_NOT_IMPLEMENTED,
                        provider -> provider.deleteMessageAttachment(requesterId,
                                resourceIdNum,
                                resourceIdStr,
                                extra));
            default -> Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Unknown resource type: "
                            + resourceType));
        };
    }

    public Mono<Map<String, String>> queryResourceUploadInfo(
            Long requesterId,
            StorageResourceType resourceType,
            @Nullable Long resourceIdNum,
            @Nullable String resourceName,
            @Nullable String resourceMediaType,
            Map<String, String> extra) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(resourceType, "resourceType");
            Validator.notNull(extra, "extra");
            if (resourceType == StorageResourceType.UNRECOGNIZED) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The resource type must not be UNRECOGNIZED"));
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        MediaType mediaType;
        if (resourceMediaType == null) {
            mediaType = null;
        } else {
            try {
                mediaType = MediaType.create(resourceMediaType);
            } catch (Exception e) {
                return Mono.error(
                        ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, e.getMessage()));
            }
        }
        return switch (resourceType) {
            case USER_PROFILE_PICTURE ->
                pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                        QUERY_USER_PROFILE_PICTURE_UPLOAD_INFO_METHOD,
                        QUERY_RESOURCE_UPLOAD_INFO_NOT_IMPLEMENTED,
                        provider -> provider.queryUserProfilePictureUploadInfo(requesterId,
                                resourceName,
                                mediaType,
                                extra));
            case GROUP_PROFILE_PICTURE ->
                pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                        QUERY_GROUP_PROFILE_PICTURE_UPLOAD_INFO_METHOD,
                        QUERY_RESOURCE_UPLOAD_INFO_NOT_IMPLEMENTED,
                        provider -> {
                            if (resourceIdNum == null) {
                                return Mono.error(
                                        ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                                "The group ID must not be null"));
                            }
                            return provider.queryGroupProfilePictureUploadInfo(requesterId,
                                    resourceIdNum,
                                    resourceName,
                                    mediaType,
                                    extra);
                        });
            case MESSAGE_ATTACHMENT -> {
                if (resourceIdNum == null) {
                    yield pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                            QUERY_MESSAGE_ATTACHMENT_UPLOAD_INFO_METHOD,
                            QUERY_RESOURCE_UPLOAD_INFO_NOT_IMPLEMENTED,
                            provider -> provider.queryMessageAttachmentUploadInfo(requesterId,
                                    resourceName,
                                    mediaType,
                                    extra));
                }
                // Note that the user ID can be 0, but the group ID cannot be 0.
                if (resourceIdNum < 0) {
                    yield pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                            QUERY_MESSAGE_ATTACHMENT_UPLOAD_INFO_IN_GROUP_CONVERSATION_METHOD,
                            QUERY_RESOURCE_UPLOAD_INFO_NOT_IMPLEMENTED,
                            provider -> provider
                                    .queryMessageAttachmentUploadInfoInGroupConversation(
                                            requesterId,
                                            -resourceIdNum,
                                            resourceName,
                                            mediaType,
                                            extra));
                }
                yield pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                        QUERY_MESSAGE_ATTACHMENT_UPLOAD_INFO_IN_PRIVATE_CONVERSATION_METHOD,
                        QUERY_RESOURCE_UPLOAD_INFO_NOT_IMPLEMENTED,
                        provider -> provider.queryMessageAttachmentUploadInfoInPrivateConversation(
                                requesterId,
                                resourceIdNum,
                                resourceName,
                                mediaType,
                                extra));
            }
            default -> Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Unknown resource type: "
                            + resourceType));
        };
    }

    public Mono<Map<String, String>> queryResourceDownloadInfo(
            Long requesterId,
            StorageResourceType resourceType,
            @Nullable Long resourceIdNum,
            @Nullable String resourceIdStr,
            Map<String, String> extra) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(resourceType, "resourceType");
            Validator.notNull(extra, "extra");
            if (resourceType == StorageResourceType.UNRECOGNIZED) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The resource type must not be UNRECOGNIZED"));
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return switch (resourceType) {
            case USER_PROFILE_PICTURE -> {
                if (resourceIdNum == null) {
                    yield Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The user ID must not be null"));
                }
                yield pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                        QUERY_USER_PROFILE_PICTURE_DOWNLOAD_INFO_METHOD,
                        QUERY_RESOURCE_DOWNLOAD_INFO_NOT_IMPLEMENTED,
                        provider -> provider.queryUserProfilePictureDownloadInfo(requesterId,
                                resourceIdNum,
                                extra));
            }
            case GROUP_PROFILE_PICTURE -> {
                if (resourceIdNum == null) {
                    yield Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The group ID must not be null"));
                }
                yield pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                        QUERY_GROUP_PROFILE_PICTURE_DOWNLOAD_INFO_METHOD,
                        QUERY_RESOURCE_DOWNLOAD_INFO_NOT_IMPLEMENTED,
                        provider -> provider.queryGroupProfilePictureDownloadInfo(requesterId,
                                resourceIdNum,
                                extra));
            }
            case MESSAGE_ATTACHMENT ->
                pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                        QUERY_MESSAGE_ATTACHMENT_DOWNLOAD_INFO_METHOD,
                        QUERY_RESOURCE_DOWNLOAD_INFO_NOT_IMPLEMENTED,
                        provider -> provider.queryMessageAttachmentDownloadInfo(requesterId,
                                resourceIdNum,
                                resourceIdStr,
                                extra));
            default -> Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Unknown resource type: "
                            + resourceType));
        };
    }

    // Message-attachment-specific methods

    public Mono<Void> shareMessageAttachmentWithUser(
            Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            Long userIdToShareWith) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(userIdToShareWith, "userIdToShareWith");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                SHARE_MESSAGE_ATTACHMENT_WITH_USER_METHOD,
                SHARE_MESSAGE_ATTACHMENT_WITH_USER_NOT_IMPLEMENTED,
                provider -> provider.shareMessageAttachmentWithUser(requesterId,
                        messageAttachmentIdNum,
                        messageAttachmentIdStr,
                        userIdToShareWith));
    }

    public Mono<Void> shareMessageAttachmentWithGroup(
            Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            Long groupIdToShareWith) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(groupIdToShareWith, "groupIdToShareWith");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                SHARE_MESSAGE_ATTACHMENT_WITH_GROUP_METHOD,
                SHARE_MESSAGE_ATTACHMENT_WITH_GROUP_NOT_IMPLEMENTED,
                provider -> provider.shareMessageAttachmentWithGroup(requesterId,
                        messageAttachmentIdNum,
                        messageAttachmentIdStr,
                        groupIdToShareWith));
    }

    public Mono<Void> unshareMessageAttachmentWithUser(
            Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            Long userIdToUnshareWith) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(userIdToUnshareWith, "userIdToUnshareWith");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                UNSHARE_MESSAGE_ATTACHMENT_WITH_USER_METHOD,
                UNSHARE_MESSAGE_ATTACHMENT_WITH_USER_NOT_IMPLEMENTED,
                provider -> provider.unshareMessageAttachmentWithUser(requesterId,
                        messageAttachmentIdNum,
                        messageAttachmentIdStr,
                        userIdToUnshareWith));
    }

    public Mono<Void> unshareMessageAttachmentWithGroup(
            Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            Long groupIdToUnshareWith) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(groupIdToUnshareWith, "groupIdToUnshareWith");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                UNSHARE_MESSAGE_ATTACHMENT_WITH_GROUP_METHOD,
                UNSHARE_MESSAGE_ATTACHMENT_WITH_GROUP_NOT_IMPLEMENTED,
                provider -> provider.unshareMessageAttachmentWithGroup(requesterId,
                        messageAttachmentIdNum,
                        messageAttachmentIdStr,
                        groupIdToUnshareWith));
    }

    public Mono<List<StorageResourceInfo>> queryMessageAttachmentInfosUploadedByRequester(
            Long requesterId,
            @Nullable DateRange creationDateRange) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                QUERY_MESSAGE_ATTACHMENT_INFOS_UPLOADED_BY_REQUESTER_METHOD,
                QUERY_MESSAGE_ATTACHMENT_INFOS_UPLOADED_BY_REQUESTER_NOT_IMPLEMENTED,
                provider -> provider.queryMessageAttachmentInfosUploadedByRequester(requesterId,
                        creationDateRange));
    }

    public Mono<List<StorageResourceInfo>> queryMessageAttachmentInfosInPrivateConversations(
            Long requesterId,
            @Nullable Set<Long> userIds,
            @Nullable DateRange creationDateRange,
            @Nullable Boolean areSharedByRequester) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                QUERY_MESSAGE_ATTACHMENT_INFOS_IN_PRIVATE_CONVERSATIONS_METHOD,
                QUERY_MESSAGE_ATTACHMENT_INFOS_IN_PRIVATE_CONVERSATION_NOT_IMPLEMENTED,
                provider -> provider.queryMessageAttachmentInfosInPrivateConversations(requesterId,
                        userIds,
                        creationDateRange,
                        areSharedByRequester));
    }

    public Mono<List<StorageResourceInfo>> queryMessageAttachmentInfosInGroupConversations(
            Long requesterId,
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange creationDateRange) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                QUERY_MESSAGE_ATTACHMENT_INFOS_IN_GROUP_CONVERSATIONS_METHOD,
                QUERY_MESSAGE_ATTACHMENT_INFOS_IN_GROUP_CONVERSATION_NOT_IMPLEMENTED,
                provider -> provider.queryMessageAttachmentInfosInGroupConversations(requesterId,
                        groupIds,
                        userIds,
                        creationDateRange));
    }

}
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

import im.turms.server.common.access.client.dto.constant.StorageResourceType;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.validation.Validator;
import im.turms.service.infra.plugin.extension.StorageServiceProvider;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.util.Map;
import jakarta.annotation.Nullable;

/**
 * @author James Chen
 */
@Service
public class StorageService {

    private static final Mono STORAGE_NOT_IMPLEMENTED = Mono.error(ResponseException.get(ResponseStatusCode.STORAGE_NOT_IMPLEMENTED));
    private static final Mono<Void> DELETE_RESOURCE_NOT_IMPLEMENTED = STORAGE_NOT_IMPLEMENTED;
    private static final Mono<Map<String, String>> QUERY_RESOURCE_DOWNLOAD_INFO_NOT_IMPLEMENTED = STORAGE_NOT_IMPLEMENTED;
    private static final Mono<Map<String, String>> QUERY_RESOURCE_UPLOAD_INFO_NOT_IMPLEMENTED = STORAGE_NOT_IMPLEMENTED;

    private static final Method DELETE_USER_PROFILE_PICTURE_METHOD;
    private static final Method QUERY_USER_PROFILE_PICTURE_UPLOAD_INFO_METHOD;
    private static final Method QUERY_USER_PROFILE_PICTURE_DOWNLOAD_INFO_METHOD;

    private static final Method DELETE_GROUP_PROFILE_PICTURE_METHOD;
    private static final Method QUERY_GROUP_PROFILE_PICTURE_UPLOAD_INFO_METHOD;
    private static final Method QUERY_GROUP_PROFILE_PICTURE_DOWNLOAD_INFO_METHOD;

    private static final Method DELETE_MESSAGE_ATTACHMENT_METHOD;
    private static final Method QUERY_MESSAGE_ATTACHMENT_UPLOAD_INFO_METHOD;
    private static final Method QUERY_MESSAGE_ATTACHMENT_DOWNLOAD_INFO_METHOD;

    private final PluginManager pluginManager;

    static {
        try {
            DELETE_USER_PROFILE_PICTURE_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("deleteUserProfilePicture", Long.class, String.class, Long.class);
            QUERY_USER_PROFILE_PICTURE_UPLOAD_INFO_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("queryUserProfilePictureUploadInfo", Long.class, String.class, Long.class);
            QUERY_USER_PROFILE_PICTURE_DOWNLOAD_INFO_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("queryUserProfilePictureDownloadInfo", Long.class, String.class, Long.class);

            DELETE_GROUP_PROFILE_PICTURE_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("deleteGroupProfilePicture", Long.class, String.class, Long.class);
            QUERY_GROUP_PROFILE_PICTURE_UPLOAD_INFO_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("queryGroupProfilePictureUploadInfo", Long.class, String.class, Long.class);
            QUERY_GROUP_PROFILE_PICTURE_DOWNLOAD_INFO_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("queryGroupProfilePictureDownloadInfo", Long.class, String.class, Long.class);

            DELETE_MESSAGE_ATTACHMENT_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("deleteMessageAttachment", Long.class, String.class, Long.class);
            QUERY_MESSAGE_ATTACHMENT_UPLOAD_INFO_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("queryMessageAttachmentUploadInfo", Long.class, String.class, Long.class);
            QUERY_MESSAGE_ATTACHMENT_DOWNLOAD_INFO_METHOD = StorageServiceProvider.class
                    .getDeclaredMethod("queryMessageAttachmentDownloadInfo", Long.class, String.class, Long.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public StorageService(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public Mono<Void> deleteResource(Long requesterId,
                                     StorageResourceType resourceType,
                                     @Nullable String resourceKeyStr,
                                     @Nullable Long resourceKeyNum) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(resourceType, "resourceType");
            if (resourceType == StorageResourceType.UNRECOGNIZED) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The resource type must not be UNRECOGNIZED"));
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return switch (resourceType) {
            case USER_PROFILE_PICTURE -> pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                    DELETE_USER_PROFILE_PICTURE_METHOD,
                    DELETE_RESOURCE_NOT_IMPLEMENTED,
                    provider -> provider.deleteUserProfilePicture(requesterId, resourceKeyStr, resourceKeyNum));
            case GROUP_PROFILE_PICTURE -> pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                    DELETE_GROUP_PROFILE_PICTURE_METHOD,
                    DELETE_RESOURCE_NOT_IMPLEMENTED,
                    provider -> provider.deleteGroupProfilePicture(requesterId, resourceKeyStr, resourceKeyNum));
            case MESSAGE_ATTACHMENT -> pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                    DELETE_MESSAGE_ATTACHMENT_METHOD,
                    DELETE_RESOURCE_NOT_IMPLEMENTED,
                    provider -> provider.deleteMessageAttachment(requesterId, resourceKeyStr, resourceKeyNum));
            default -> Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "Unknown resource type: " + resourceType));
        };
    }

    public Mono<Map<String, String>> queryResourceUploadInfo(Long requesterId,
                                                             StorageResourceType resourceType,
                                                             @Nullable String resourceKeyStr,
                                                             @Nullable Long resourceKeyNum) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(resourceType, "resourceType");
            if (resourceType == StorageResourceType.UNRECOGNIZED) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The resource type must not be UNRECOGNIZED"));
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return switch (resourceType) {
            case USER_PROFILE_PICTURE -> pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                    QUERY_USER_PROFILE_PICTURE_UPLOAD_INFO_METHOD,
                    QUERY_RESOURCE_UPLOAD_INFO_NOT_IMPLEMENTED,
                    provider -> provider.queryUserProfilePictureUploadInfo(requesterId, resourceKeyStr, resourceKeyNum));
            case GROUP_PROFILE_PICTURE -> pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                    QUERY_GROUP_PROFILE_PICTURE_UPLOAD_INFO_METHOD,
                    QUERY_RESOURCE_UPLOAD_INFO_NOT_IMPLEMENTED,
                    provider -> provider.queryGroupProfilePictureUploadInfo(requesterId, resourceKeyStr, resourceKeyNum));
            case MESSAGE_ATTACHMENT -> pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                    QUERY_MESSAGE_ATTACHMENT_UPLOAD_INFO_METHOD,
                    QUERY_RESOURCE_UPLOAD_INFO_NOT_IMPLEMENTED,
                    provider -> provider.queryMessageAttachmentUploadInfo(requesterId, resourceKeyStr, resourceKeyNum));
            default -> Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "Unknown resource type: " + resourceType));
        };
    }

    public Mono<Map<String, String>> queryResourceDownloadInfo(Long requesterId,
                                                               StorageResourceType resourceType,
                                                               @Nullable String resourceKeyStr,
                                                               @Nullable Long resourceKeyNum) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(resourceType, "resourceType");
            if (resourceType == StorageResourceType.UNRECOGNIZED) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The resource type must not be UNRECOGNIZED"));
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return switch (resourceType) {
            case USER_PROFILE_PICTURE -> pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                    QUERY_USER_PROFILE_PICTURE_DOWNLOAD_INFO_METHOD,
                    QUERY_RESOURCE_DOWNLOAD_INFO_NOT_IMPLEMENTED,
                    provider -> provider.queryUserProfilePictureDownloadInfo(requesterId, resourceKeyStr, resourceKeyNum));
            case GROUP_PROFILE_PICTURE -> pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                    QUERY_GROUP_PROFILE_PICTURE_DOWNLOAD_INFO_METHOD,
                    QUERY_RESOURCE_DOWNLOAD_INFO_NOT_IMPLEMENTED,
                    provider -> provider.queryGroupProfilePictureDownloadInfo(requesterId, resourceKeyStr, resourceKeyNum));
            case MESSAGE_ATTACHMENT -> pluginManager.invokeFirstExtensionPoint(StorageServiceProvider.class,
                    QUERY_MESSAGE_ATTACHMENT_DOWNLOAD_INFO_METHOD,
                    QUERY_RESOURCE_DOWNLOAD_INFO_NOT_IMPLEMENTED,
                    provider -> provider.queryMessageAttachmentDownloadInfo(requesterId, resourceKeyStr, resourceKeyNum));
            default -> Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "Unknown resource type: " + resourceType));
        };
    }

}
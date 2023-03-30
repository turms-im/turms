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

package im.turms.service.infra.plugin.extension;

import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.web.MediaType;
import im.turms.server.common.infra.exception.NotImplementedException;
import im.turms.server.common.infra.plugin.ExtensionPoint;
import im.turms.server.common.infra.time.DateRange;
import im.turms.service.domain.storage.bo.StorageResourceInfo;

/**
 * @author James Chen
 */
public interface StorageServiceProvider extends ExtensionPoint {

    // User profile picture

    default Mono<Void> deleteUserProfilePicture(
            @NotNull Long requesterId,
            @NotNull Map<String, String> extra) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Map<String, String>> queryUserProfilePictureUploadInfo(
            @NotNull Long requesterId,
            @Nullable String resourceName,
            @Nullable MediaType resourceMediaType,
            @NotNull Map<String, String> extra) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Map<String, String>> queryUserProfilePictureDownloadInfo(
            @NotNull Long requesterId,
            @NotNull Long userId,
            @NotNull Map<String, String> extra) {
        return Mono.error(new NotImplementedException());
    }

    // Group profile picture

    default Mono<Void> deleteGroupProfilePicture(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Map<String, String> extra) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Map<String, String>> queryGroupProfilePictureUploadInfo(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @Nullable String resourceName,
            @Nullable MediaType resourceMediaType,
            @NotNull Map<String, String> extra) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Map<String, String>> queryGroupProfilePictureDownloadInfo(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Map<String, String> extra) {
        return Mono.error(new NotImplementedException());
    }

    // Message attachment

    default Mono<Void> deleteMessageAttachment(
            @NotNull Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            @NotNull Map<String, String> extra) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Void> shareMessageAttachmentWithUser(
            @NotNull Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            @NotNull Long userId) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Void> shareMessageAttachmentWithGroup(
            @NotNull Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            @NotNull Long groupId) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Void> unshareMessageAttachmentWithUser(
            @NotNull Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            @NotNull Long userId) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Void> unshareMessageAttachmentWithGroup(
            @NotNull Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            @NotNull Long groupId) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Map<String, String>> queryMessageAttachmentUploadInfo(
            @NotNull Long requesterId,
            @Nullable String resourceName,
            @Nullable MediaType resourceMediaType,
            @NotNull Map<String, String> extra) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Map<String, String>> queryMessageAttachmentUploadInfoInPrivateConversation(
            @NotNull Long requesterId,
            @NotNull Long userId,
            @Nullable String resourceName,
            @Nullable MediaType resourceMediaType,
            @NotNull Map<String, String> extra) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Map<String, String>> queryMessageAttachmentUploadInfoInGroupConversation(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @Nullable String resourceName,
            @Nullable MediaType resourceMediaType,
            @NotNull Map<String, String> extra) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<Map<String, String>> queryMessageAttachmentDownloadInfo(
            @NotNull Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            @NotNull Map<String, String> extra) {
        return Mono.error(new NotImplementedException());
    }

    // Message attachment - Info

    default Mono<List<StorageResourceInfo>> queryMessageAttachmentInfosUploadedByRequester(
            @NotNull Long requesterId,
            @Nullable DateRange creationDateRange) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<List<StorageResourceInfo>> queryMessageAttachmentInfosInPrivateConversations(
            @NotNull Long requesterId,
            @Nullable Set<Long> userIds,
            @Nullable DateRange creationDateRange,
            @Nullable Boolean areSharedByRequester) {
        return Mono.error(new NotImplementedException());
    }

    default Mono<List<StorageResourceInfo>> queryMessageAttachmentInfosInGroupConversations(
            @NotNull Long requesterId,
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange creationDateRange) {
        return Mono.error(new NotImplementedException());
    }

}
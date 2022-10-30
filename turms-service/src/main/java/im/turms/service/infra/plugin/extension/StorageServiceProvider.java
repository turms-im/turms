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

import im.turms.server.common.infra.plugin.ExtensionPoint;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author James Chen
 */
public interface StorageServiceProvider extends ExtensionPoint {

    // User profile picture

    Mono<Void> deleteUserProfilePicture(
            @NotNull Long requesterId,
            @Nullable String resourceKeyStr,
            @Nullable Long resourceKeyNum);

    Mono<Map<String, String>> queryUserProfilePictureUploadInfo(
            @NotNull Long requesterId,
            @Nullable String resourceKeyStr,
            @Nullable Long resourceKeyNum);

    Mono<Map<String, String>> queryUserProfilePictureDownloadInfo(
            @NotNull Long requesterId,
            @Nullable String resourceKeyStr,
            @Nullable Long resourceKeyNum);

    // Group profile picture

    Mono<Void> deleteGroupProfilePicture(
            @NotNull Long requesterId,
            @Nullable String resourceKeyStr,
            @Nullable Long resourceKeyNum);

    Mono<Map<String, String>> queryGroupProfilePictureUploadInfo(
            @NotNull Long requesterId,
            @Nullable String resourceKeyStr,
            @Nullable Long resourceKeyNum);

    Mono<Map<String, String>> queryGroupProfilePictureDownloadInfo(
            @NotNull Long requesterId,
            @Nullable String resourceKeyStr,
            @Nullable Long resourceKeyNum);

    // Message attachment

    Mono<Void> deleteMessageAttachment(
            @NotNull Long requesterId,
            @Nullable String resourceKeyStr,
            @Nullable Long resourceKeyNum);

    Mono<Map<String, String>> queryMessageAttachmentUploadInfo(
            @NotNull Long requesterId,
            @Nullable String resourceKeyStr,
            @Nullable Long resourceKeyNum);

    Mono<Map<String, String>> queryMessageAttachmentDownloadInfo(
            @NotNull Long requesterId,
            @Nullable String resourceKeyStr,
            @Nullable Long resourceKeyNum);

}
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

package im.turms.server.common.infra.property.env.service.business.storage;

import java.util.Collections;
import java.util.List;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.access.admin.web.MediaTypeConst;
import im.turms.server.common.infra.property.metadata.Description;

import static im.turms.server.common.infra.unit.ByteSizeUnit.MB;

/**
 * There properties are "recommended" and optional for plugin providers to implement
 *
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class StorageItemProperties {

    @Description("Delete the resource the specific days after creation. "
            + "0 means no expiration")
    @Min(0)
    private int expireAfterDays;

    @Description("Restrict access to the resource to only allow the specific referrers (e.g. \"https://github.com/turms-im/turms/*\")")
    private List<String> allowedReferrers = Collections.emptyList();

    @Description("The allowed \"Content-Type\" of the resource that the client can upload")
    private String allowedContentType = MediaTypeConst.ALL_VALUE;

    @Description("The minimum size of the resource that the client can upload. "
            + "0 means no limit")
    @Min(0)
    private int minSizeBytes;

    @Description("The maximum size of the resource that the client can upload. "
            + "0 means no limit")
    @Min(0)
    private int maxSizeBytes = MB;

    @Description("The presigned URLs are valid only for the specified duration. "
            + "0 means no expiration")
    @Max(7 * 24 * 60 * 60)
    @Min(0)
    private int downloadUrlExpireAfterSeconds = 5 * 60;

    @Description("The presigned URLs are valid only for the specified duration. "
            + "0 means no expiration")
    @Max(7 * 24 * 60 * 60)
    @Min(0)
    private int uploadUrlExpireAfterSeconds = 5 * 60;

}
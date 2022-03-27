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

package im.turms.server.common.infra.property.env.service.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.MimeTypeUtils;

import javax.validation.constraints.Min;
import java.time.Duration;

/**
 * There properties are "recommended" and optional for plugin providers to implement
 *
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class StorageProperties {

    @Min(0)
    private int profileExpiration = 30;

    @Min(0)
    private int groupProfileExpiration = 30;

    //    @Min(0)
    //    private int storageExpiration = 30;
    @Min(0)
    private int attachmentExpiration = 30;

    private String profileContentType = MimeTypeUtils.ALL_VALUE;

    private String groupProfileContentType = MimeTypeUtils.ALL_VALUE;

    //    private String storageContentType = MimeTypeUtils.ALL_VALUE;

    private String attachmentContentType = MimeTypeUtils.ALL_VALUE;

    private int profileSizeLimit = 1 * 1024 * 1024;

    private int groupProfileSizeLimit = 1 * 1024 * 1024;

    //    private int storageSizeLimit = 10 * 1024 * 1024;

    private int attachmentSizeLimit = 10 * 1024 * 1024;

    private Duration signatureDurationForGet = Duration.ofMinutes(5);

    private Duration signatureDurationForPut = Duration.ofMinutes(5);

}
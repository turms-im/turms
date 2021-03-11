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

package im.turms.server.common.exception;

import im.turms.server.common.constant.TurmsStatusCode;
import lombok.Data;

@Data
public final class ThrowableInfo {

    private final TurmsStatusCode code;
    private final String reason;

    private ThrowableInfo(Throwable throwable) {
        if (throwable instanceof TurmsBusinessException) {
            TurmsBusinessException exception = (TurmsBusinessException) throwable;
            code = exception.getCode();
            reason = exception.getReason();
        } else {
            code = TurmsStatusCode.SERVER_INTERNAL_ERROR;
            reason = throwable.getMessage();
        }
    }

    public static ThrowableInfo get(Throwable throwable) {
        return new ThrowableInfo(throwable);
    }

}
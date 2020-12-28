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

package im.turms.turms.workflow.service.util;

import im.turms.common.constant.RequestStatus;
import im.turms.turms.workflow.dao.domain.group.GroupInvitation;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * @author James Chen
 */
public class RequestStatusUtil {

    private RequestStatusUtil() {
    }

    private static boolean isProcessedByResponder(@Nullable RequestStatus status) {
        return status == RequestStatus.ACCEPTED
                || status == RequestStatus.DECLINED
                || status == RequestStatus.IGNORED;
    }

    public static Date getResponseDateBasedOnStatus(
            @Nullable RequestStatus status,
            @Nullable Date responseDate,
            @Nullable Date now) {
        if (RequestStatusUtil.isProcessedByResponder(status)) {
            if (responseDate == null) {
                responseDate = Objects.requireNonNullElseGet(now, Date::new);
            }
            return responseDate;
        } else {
            return null;
        }
    }

    public static Update updateResponseDateBasedOnStatus(@NotNull Update update, @Nullable RequestStatus status, @Nullable Date responseDate) {
        if (status != null) {
            if (RequestStatusUtil.isProcessedByResponder(status)) {
                if (responseDate == null) {
                    responseDate = new Date();
                }
                update.set(GroupInvitation.Fields.RESPONSE_DATE, responseDate);
            } else {
                update.unset(GroupInvitation.Fields.RESPONSE_DATE);
            }
        }
        return update;
    }

}
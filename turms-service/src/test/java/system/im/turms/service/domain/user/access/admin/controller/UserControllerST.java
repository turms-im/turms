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

package system.im.turms.service.domain.user.access.admin.controller;

import java.util.Collection;

import helper.SpringAwareIntegrationTest;
import org.junit.jupiter.api.Test;

import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.domain.user.po.User;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerST extends SpringAwareIntegrationTest {

    @Test
    void queryUsers_shouldReturnNotEmptyData() {
        ResponseDTO<Collection<User>> response =
                getResponse("/users?registrationDateStart=1970-01-01T00:00:00Z");
        assertThat(response.data()).isNotEmpty();
    }

}

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

package unit.im.turms.gateway.access.client.common.accesscontrol.policy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import im.turms.gateway.access.client.common.authorization.policy.Policy;
import im.turms.gateway.access.client.common.authorization.policy.PolicyManager;
import im.turms.gateway.access.client.common.authorization.policy.PolicyStatement;
import im.turms.gateway.access.client.common.authorization.policy.PolicyStatementAction;
import im.turms.gateway.access.client.common.authorization.policy.PolicyStatementEffect;
import im.turms.gateway.access.client.common.authorization.policy.PolicyStatementResource;
import im.turms.server.common.access.client.dto.request.TurmsRequest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class PolicyManagerTests {

    @Test
    void test() {
        PolicyManager manager = new PolicyManager();

        Set<TurmsRequest.KindCase> allowedRequestTypes = manager.findAllowedRequestTypes(new Policy(
                List.of(new PolicyStatement(
                        PolicyStatementEffect.ALLOW,
                        Set.of(PolicyStatementAction.ALL),
                        Set.of(PolicyStatementResource.values())))));
        Set<TurmsRequest.KindCase> types = new HashSet<>(Set.of(TurmsRequest.KindCase.values()));
        types.removeAll(Set.of(TurmsRequest.KindCase.KIND_NOT_SET,
                TurmsRequest.KindCase.CREATE_SESSION_REQUEST,
                TurmsRequest.KindCase.DELETE_SESSION_REQUEST));
        assertThat(allowedRequestTypes).containsExactlyInAnyOrderElementsOf(types);

        allowedRequestTypes = manager.findAllowedRequestTypes(new Policy(
                List.of(new PolicyStatement(
                        PolicyStatementEffect.ALLOW,
                        Set.of(PolicyStatementAction.ALL),
                        Set.of(PolicyStatementResource.USER)))));
        assertThat(allowedRequestTypes)
                .containsExactlyInAnyOrder(TurmsRequest.KindCase.UPDATE_USER_REQUEST);
    }

}

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

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import im.turms.gateway.access.client.common.authorization.policy.IllegalPolicyException;
import im.turms.gateway.access.client.common.authorization.policy.Policy;
import im.turms.gateway.access.client.common.authorization.policy.PolicyDeserializer;
import im.turms.gateway.access.client.common.authorization.policy.PolicyStatement;
import im.turms.gateway.access.client.common.authorization.policy.PolicyStatementAction;
import im.turms.gateway.access.client.common.authorization.policy.PolicyStatementEffect;
import im.turms.gateway.access.client.common.authorization.policy.PolicyStatementResource;
import im.turms.server.common.infra.json.JsonUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author James Chen
 */
class PolicyDeserializerTests {

    @Test
    void test() {
        // language=JSON
        String json = """
                {
                    "authenticated": true,
                    "statements": [{
                        "effect": "ALLOW",
                        "actions": "*",
                        "resources": ["USER", "GROUP_BLOCKED_USER"]
                    }]
                }
                """;
        Map<String, Object> map = JsonUtil.readStringObjectMapValue(json.getBytes());
        Policy policy = PolicyDeserializer.parse(map);
        assertThat(policy.statements()).hasSize(1);
        assertThat(policy.statements()
                .get(0))
                .isEqualTo(new PolicyStatement(
                        PolicyStatementEffect.ALLOW,
                        Set.of(PolicyStatementAction.ALL),
                        Set.of(PolicyStatementResource.USER,
                                PolicyStatementResource.GROUP_BLOCKED_USER)));

        json = """
                {
                    "authenticated": true,
                    "statements": [{
                        "effect": "ALLOW",
                        "actions": "*",
                        "resources": ["USER", "a non-existing resource"]
                    }]
                }
                """;
        map = JsonUtil.readStringObjectMapValue(json.getBytes());
        Map<String, Object> finalMap = map;
        assertThatThrownBy(() -> PolicyDeserializer.parse(finalMap))
                .isInstanceOf(IllegalPolicyException.class);
    }
}
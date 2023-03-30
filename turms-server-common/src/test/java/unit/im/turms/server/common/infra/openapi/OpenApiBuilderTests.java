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

package unit.im.turms.server.common.infra.openapi;

import java.lang.reflect.Method;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpMethod;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.web.ApiEndpoint;
import im.turms.server.common.access.admin.web.ApiEndpointKey;
import im.turms.server.common.access.admin.web.MethodParameterInfo;
import im.turms.server.common.infra.openapi.OpenApiBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class OpenApiBuilderTests {

    @SneakyThrows
    @Test
    void test() {
        String version = "0.0.0";
        String nodeType = "my-node-type";
        String serverUrl = "my-url";
        String path = "/my-path";
        ApiEndpointKey endpointKey = new ApiEndpointKey(path, HttpMethod.GET);
        MethodParameterInfo parameterInfo = new MethodParameterInfo(
                "method-name",
                ResponseDTO.class,
                null,
                null,
                null,
                true,
                false,
                true,
                false,
                null,
                "json",
                true);
        Method method = mock(Method.class);
        when(method.getGenericReturnType()).thenReturn(ResponseDTO.class);
        ApiEndpoint endpoint = new ApiEndpoint(
                new Object(),
                method,
                HttpMethod.GET,
                new MethodParameterInfo[]{parameterInfo},
                "media",
                null,
                null);
        byte[] bytes =
                OpenApiBuilder.build(version, nodeType, serverUrl, Map.of(endpointKey, endpoint));
        JsonNode node = new ObjectMapper().readTree(bytes);

        assertThat(node.get("info")
                .get("title")
                .textValue()).contains(nodeType);
        assertThat(node.get("info")
                .get("version")
                .textValue()).hasToString(version);
        assertThat(node.get("servers")
                .get(0)
                .get("url")
                .textValue()).hasToString(serverUrl);
        assertThat(node.get("paths")
                .get(path)
                .get("get")).isNotNull();
    }

}

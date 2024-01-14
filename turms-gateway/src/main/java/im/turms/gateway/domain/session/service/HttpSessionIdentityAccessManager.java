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

package im.turms.gateway.domain.session.service;

import java.time.Duration;
import java.util.Map;
import java.util.Set;

import io.netty.handler.codec.http.HttpMethod;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import im.turms.gateway.access.client.common.authorization.policy.Policy;
import im.turms.gateway.access.client.common.authorization.policy.PolicyDeserializer;
import im.turms.gateway.access.client.common.authorization.policy.PolicyManager;
import im.turms.gateway.domain.session.bo.UserLoginInfo;
import im.turms.gateway.domain.session.bo.UserPermissionInfo;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.json.JsonUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.http.HttpAuthenticationResponseExpectationProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.http.HttpIdentityAccessManagementProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.http.HttpIdentityAccessManagementRequestProperties;
import im.turms.server.common.infra.validation.Validator;

/**
 * @author James Chen
 */
public class HttpSessionIdentityAccessManager implements SessionIdentityAccessManagementSupport {

    private final HttpClient httpIdentityAccessManagementClient;
    private final HttpMethod httpIdentityAccessManagementHttpMethod;
    private final Set<String> httpAuthenticationExpectedStatusCodes;
    private final Map<String, String> httpAuthenticationExpectedHeaders;
    private final Map<String, Object> httpAuthenticationExpectedBodyFields;

    private final PolicyManager policyManager;

    public HttpSessionIdentityAccessManager(
            HttpIdentityAccessManagementProperties httpProperties,
            PolicyManager policyManager) {
        HttpIdentityAccessManagementRequestProperties requestProperties =
                httpProperties.getRequest();
        HttpAuthenticationResponseExpectationProperties responseExpectationProperties =
                httpProperties.getAuthentication()
                        .getResponseExpectation();
        String url = requestProperties.getUrl();
        Exception exception = Validator.url(url);
        if (exception != null) {
            throw new IllegalArgumentException(
                    "Illegal HTTP URL: "
                            + url,
                    exception);
        }
        this.httpIdentityAccessManagementClient = HttpClient.create()
                .baseUrl(url)
                .headers(entries -> {
                    for (Map.Entry<String, String> entry : requestProperties.getHeaders()
                            .entrySet()) {
                        entries.add(entry.getKey(), entry.getValue());
                    }
                })
                .responseTimeout(Duration.ofMillis(requestProperties.getTimeoutMillis()));
        this.httpIdentityAccessManagementHttpMethod =
                HttpMethod.valueOf(requestProperties.getHttpMethod()
                        .name());
        this.httpAuthenticationExpectedStatusCodes = responseExpectationProperties.getStatusCodes();
        this.httpAuthenticationExpectedHeaders = responseExpectationProperties.getHeaders();
        this.httpAuthenticationExpectedBodyFields = responseExpectationProperties.getBodyFields();

        this.policyManager = policyManager;
    }

    @Override
    public Mono<UserPermissionInfo> verifyAndGrant(UserLoginInfo userLoginInfo) {
        return httpIdentityAccessManagementClient.request(httpIdentityAccessManagementHttpMethod)
                .send(Mono.fromCallable(() -> JsonUtil.write(userLoginInfo)))
                .responseSingle((response, bodyBufferMono) -> {
                    if (!StringUtil.matchLatin1(response.status()
                            .toString(), httpAuthenticationExpectedStatusCodes)) {
                        return UserPermissionInfo.LOGIN_AUTHENTICATION_FAILED_MONO;
                    }
                    for (Map.Entry<String, String> entry : httpAuthenticationExpectedHeaders
                            .entrySet()) {
                        if (!entry.getValue()
                                .equals(response.responseHeaders()
                                        .get(entry.getKey()))) {
                            return UserPermissionInfo.LOGIN_AUTHENTICATION_FAILED_MONO;
                        }
                    }
                    return bodyBufferMono.asInputStream()
                            .map(inputStream -> {
                                Map<String, Object> map;
                                Policy policy;
                                try {
                                    map = JsonUtil.readStringObjectMapValue(inputStream);
                                    policy = PolicyDeserializer.parse(map);
                                } catch (Exception e) {
                                    throw new IllegalArgumentException("Illegal request body", e);
                                }
                                if (!CollectionUtil.containsAllLooseComparison(map,
                                        httpAuthenticationExpectedBodyFields)) {
                                    return UserPermissionInfo.LOGIN_AUTHENTICATION_FAILED;
                                }
                                return new UserPermissionInfo(
                                        ResponseStatusCode.OK,
                                        policyManager.findAllowedRequestTypes(policy));
                            });
                });
    }
}
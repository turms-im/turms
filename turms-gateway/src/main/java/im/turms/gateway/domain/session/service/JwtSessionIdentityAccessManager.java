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

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import reactor.core.publisher.Mono;

import im.turms.gateway.access.client.common.authorization.policy.IllegalPolicyException;
import im.turms.gateway.access.client.common.authorization.policy.Policy;
import im.turms.gateway.access.client.common.authorization.policy.PolicyDeserializer;
import im.turms.gateway.access.client.common.authorization.policy.PolicyManager;
import im.turms.gateway.domain.session.bo.UserLoginInfo;
import im.turms.gateway.domain.session.bo.UserPermissionInfo;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtAlgorithmProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtIdentityAccessManagementProperties;
import im.turms.server.common.infra.security.jwt.Jwt;
import im.turms.server.common.infra.security.jwt.JwtManager;
import im.turms.server.common.infra.security.jwt.JwtPayload;
import im.turms.server.common.infra.security.jwt.exception.InvalidJwtException;
import im.turms.server.common.infra.security.jwt.exception.JwtSignatureVerificationException;

import static im.turms.gateway.domain.session.bo.UserPermissionInfo.*;

/**
 * @author James Chen
 */
public class JwtSessionIdentityAccessManager implements SessionIdentityAccessManagementSupport {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(JwtSessionIdentityAccessManager.class);

    private final JwtManager jwtManager;
    private final PolicyManager policyManager;
    private final Map<String, Object> jwtAuthenticationExpectedCustomPayloadClaims;

    public JwtSessionIdentityAccessManager(
            JwtIdentityAccessManagementProperties jwtProperties,
            PolicyManager policyManager) {
        jwtAuthenticationExpectedCustomPayloadClaims = jwtProperties.getAuthentication()
                .getExpectation()
                .getCustomPayloadClaims();
        JwtAlgorithmProperties jwtAlgorithmProperties = jwtProperties.getAlgorithm();
        jwtManager = new JwtManager(
                jwtProperties.getVerification(),
                jwtAlgorithmProperties.getRsa256(),
                jwtAlgorithmProperties.getRsa384(),
                jwtAlgorithmProperties.getRsa512(),
                jwtAlgorithmProperties.getPs256(),
                jwtAlgorithmProperties.getPs384(),
                jwtAlgorithmProperties.getPs512(),
                jwtAlgorithmProperties.getEcdsa256(),
                jwtAlgorithmProperties.getEcdsa384(),
                jwtAlgorithmProperties.getEcdsa512(),
                jwtAlgorithmProperties.getHmac256(),
                jwtAlgorithmProperties.getHmac384(),
                jwtAlgorithmProperties.getHmac512());
        LOGGER.info("Supported algorithms for JWT: {}", jwtManager.getSupportedAlgorithmNames());

        this.policyManager = policyManager;
    }

    @Override
    public Mono<UserPermissionInfo> verifyAndGrant(UserLoginInfo userLoginInfo) {
        Long userId = userLoginInfo.userId();
        String jwtToken = userLoginInfo.password();
        if (StringUtil.isBlank(jwtToken)) {
            return Mono.error(
                    new IllegalArgumentException("Invalid JWT token: JWT must not be blank"));
        }
        Jwt jwt;
        try {
            jwt = jwtManager.decode(jwtToken);
        } catch (InvalidJwtException | NoSuchAlgorithmException
                | JwtSignatureVerificationException e) {
            return Mono.error(new IllegalArgumentException("Invalid JWT token", e));
        }
        JwtPayload payload = jwt.payload();
        String subject = payload.subject();
        if (subject == null) {
            return Mono.error(new IllegalArgumentException(
                    "Invalid JWT token: the sub claim in the payload must exist"));
        }
        if (!subject.equals(userId.toString())) {
            return LOGIN_AUTHENTICATION_FAILED_MONO;
        }
        Date expiresAt = payload.expiresAt();
        Date notBefore = payload.notBefore();
        boolean hasExpiresAt = expiresAt != null;
        boolean hasNotBefore = notBefore != null;
        if (hasExpiresAt || hasNotBefore) {
            long now = System.currentTimeMillis();
            if ((hasExpiresAt && expiresAt.getTime() <= now)
                    || (hasNotBefore && notBefore.getTime() > now)) {
                return LOGIN_AUTHENTICATION_FAILED_MONO;
            }
        }
        Map<String, Object> customClaims = payload.customClaims();
        if (!CollectionUtil.containsAllLooseComparison(customClaims,
                jwtAuthenticationExpectedCustomPayloadClaims)) {
            return LOGIN_AUTHENTICATION_FAILED_MONO;
        }
        Policy policy;
        try {
            policy = PolicyDeserializer.parse(customClaims);
        } catch (IllegalPolicyException e) {
            return Mono.error(new IllegalArgumentException("Invalid JWT token", e));
        }
        return Mono.just(new UserPermissionInfo(
                ResponseStatusCode.OK,
                policyManager.findAllowedRequestTypes(policy)));
    }
}
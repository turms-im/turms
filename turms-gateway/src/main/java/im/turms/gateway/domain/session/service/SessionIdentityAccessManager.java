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

import im.turms.gateway.access.client.common.authorization.policy.IllegalPolicyException;
import im.turms.gateway.access.client.common.authorization.policy.Policy;
import im.turms.gateway.access.client.common.authorization.policy.PolicyDeserializer;
import im.turms.gateway.access.client.common.authorization.policy.PolicyManager;
import im.turms.gateway.domain.session.bo.UserLoginInfo;
import im.turms.gateway.domain.session.bo.UserPermissionInfo;
import im.turms.gateway.infra.plugin.extension.UserAuthenticator;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.client.dto.request.TurmsRequestTypePool;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.admin.constant.AdminConst;
import im.turms.server.common.domain.location.bo.Location;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.json.JsonUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.plugin.invoker.SequentialExtensionPointInvoker;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.constant.IdentityAccessManagementType;
import im.turms.server.common.infra.property.env.gateway.SessionProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.IdentityAccessManagementProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.http.HttpAuthenticationResponseExpectationProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.http.HttpIdentityAccessManagementProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.http.HttpIdentityAccessManagementRequestProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtAlgorithmProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtIdentityAccessManagementProperties;
import im.turms.server.common.infra.security.jwt.Jwt;
import im.turms.server.common.infra.security.jwt.JwtManager;
import im.turms.server.common.infra.security.jwt.JwtPayload;
import im.turms.server.common.infra.security.jwt.exception.CorruptedJwtException;
import io.netty.handler.codec.http.HttpMethod;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
public class SessionIdentityAccessManager implements SessionIdentityAccessManagementSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionIdentityAccessManager.class);

    private static final Method AUTHENTICATE_METHOD;

    private static final UserPermissionInfo GRANTED_WITH_ALL_PERMISSIONS =
            new UserPermissionInfo(ResponseStatusCode.OK, TurmsRequestTypePool.ALL);
    private static final Mono<UserPermissionInfo> GRANTED_WITH_ALL_PERMISSIONS_MONO = Mono.just(GRANTED_WITH_ALL_PERMISSIONS);
    private static final UserPermissionInfo LOGIN_AUTHENTICATION_FAILED = new UserPermissionInfo(ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED);
    private static final Mono<UserPermissionInfo> LOGIN_AUTHENTICATION_FAILED_MONO = Mono.just(LOGIN_AUTHENTICATION_FAILED);
    private static final Mono<UserPermissionInfo> LOGGING_IN_USER_NOT_ACTIVE_MONO =
            Mono.just(new UserPermissionInfo(ResponseStatusCode.LOGGING_IN_USER_NOT_ACTIVE));

    static {
        try {
            AUTHENTICATE_METHOD = UserAuthenticator.class
                    .getDeclaredMethod("authenticate", UserLoginInfo.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private final JwtManager jwtManager;
    private final PluginManager pluginManager;
    private final PolicyManager policyManager;

    private final UserService userService;

    private final HttpClient httpIdentityAccessManagementClient;
    private final HttpMethod httpIdentityAccessManagementHttpMethod;
    private final Set<String> httpAuthenticationExpectedStatusCodes;
    private final Map<String, String> httpAuthenticationExpectedHeaders;
    private final Map<String, Object> httpAuthenticationExpectedBodyFields;

    private final Map<String, Object> jwtAuthenticationExpectedCustomPayloadClaims;

    private boolean enableIdentityAccessManagement;
    private final IdentityAccessManagementType identityAccessManagementType;

    public SessionIdentityAccessManager(TurmsPropertiesManager propertiesManager,
                                        PluginManager pluginManager,
                                        UserService userService) {
        this.pluginManager = pluginManager;
        this.userService = userService;
        this.policyManager = new PolicyManager();
        updateGlobalProperties(propertiesManager.getGlobalProperties());
        IdentityAccessManagementProperties identityAccessManagementProperties = propertiesManager
                .getLocalProperties().getGateway().getSession().getIdentityAccessManagement();
        identityAccessManagementType = identityAccessManagementProperties.getType();
        JwtManager jwtManager = null;
        Map<String, Object> jwtAuthenticationExpectedCustomPayloadClaims = null;
        HttpClient httpIdentityAccessManagementClient = null;
        HttpMethod httpIdentityAccessManagementHttpMethod = null;
        Set<String> httpAuthenticationExpectedStatusCodes = null;
        Map<String, String> httpAuthenticationExpectedHeaders = null;
        Map<String, Object> httpAuthenticationExpectedBodyFields = null;
        if (identityAccessManagementType == IdentityAccessManagementType.JWT) {
            JwtIdentityAccessManagementProperties jwtProperties = identityAccessManagementProperties.getJwt();
            jwtAuthenticationExpectedCustomPayloadClaims = jwtProperties.getAuthentication().getExpectation().getCustomPayloadClaims();
            JwtAlgorithmProperties jwtAlgorithmProperties = jwtProperties.getAlgorithm();
            jwtManager = new JwtManager(jwtProperties.getVerification(),
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
            LOGGER.info("The supported algorithms for JWT identity and access management are: " + jwtManager.getSupportedAlgorithmNames());
        } else if (identityAccessManagementType == IdentityAccessManagementType.HTTP) {
            HttpIdentityAccessManagementProperties httpProperties = identityAccessManagementProperties.getHttp();
            HttpIdentityAccessManagementRequestProperties requestProperties = httpProperties.getRequest();
            HttpAuthenticationResponseExpectationProperties responseExpectationProperties = httpProperties.getAuthentication().getResponseExpectation();
            String url = requestProperties.getUrl();
            try {
                new URL(url).toURI();
            } catch (MalformedURLException | URISyntaxException e) {
                throw new IllegalArgumentException("The HTTP URL for identity and access management is illegal", e);
            }
            httpIdentityAccessManagementClient = HttpClient.create()
                    .baseUrl(url)
                    .headers(entries -> {
                        for (Map.Entry<String, String> entry : requestProperties.getHeaders().entrySet()) {
                            entries.add(entry.getKey(), entry.getValue());
                        }
                    })
                    .responseTimeout(Duration.ofMillis(requestProperties.getTimeoutMillis()));
            httpIdentityAccessManagementHttpMethod = HttpMethod.valueOf(requestProperties.getHttpMethod().name());
            httpAuthenticationExpectedStatusCodes = responseExpectationProperties.getStatusCodes();
            httpAuthenticationExpectedHeaders = responseExpectationProperties.getHeaders();
            httpAuthenticationExpectedBodyFields = responseExpectationProperties.getBodyFields();
        }
        this.jwtManager = jwtManager;
        this.jwtAuthenticationExpectedCustomPayloadClaims = jwtAuthenticationExpectedCustomPayloadClaims;
        this.httpIdentityAccessManagementClient = httpIdentityAccessManagementClient;
        this.httpIdentityAccessManagementHttpMethod = httpIdentityAccessManagementHttpMethod;
        this.httpAuthenticationExpectedStatusCodes = httpAuthenticationExpectedStatusCodes;
        this.httpAuthenticationExpectedHeaders = httpAuthenticationExpectedHeaders;
        this.httpAuthenticationExpectedBodyFields = httpAuthenticationExpectedBodyFields;
        propertiesManager.addGlobalPropertiesChangeListener(this::updateGlobalProperties);
    }

    private void updateGlobalProperties(TurmsProperties properties) {
        SessionProperties sessionProperties = properties.getGateway().getSession();
        enableIdentityAccessManagement = sessionProperties.getIdentityAccessManagement().isEnabled();
    }

    @Override
    public Mono<UserPermissionInfo> verifyAndGrant(int version,
                                                   Long userId,
                                                   @Nullable String password,
                                                   DeviceType deviceType,
                                                   @Nullable Map<String, String> deviceDetails,
                                                   @Nullable UserStatus userStatus,
                                                   @Nullable Location location,
                                                   @Nullable String ip) {
        if (userId.equals(AdminConst.ADMIN_REQUESTER_ID)) {
            return LOGIN_AUTHENTICATION_FAILED_MONO;
        }
        if (!enableIdentityAccessManagement) {
            return GRANTED_WITH_ALL_PERMISSIONS_MONO;
        }
        UserLoginInfo userLoginInfo = new UserLoginInfo(
                version,
                userId,
                password,
                deviceType,
                deviceDetails,
                userStatus,
                location,
                ip);
        // TODO: Support authorization for plugins
        if (pluginManager.hasRunningExtensions(UserAuthenticator.class)) {
            Mono<UserPermissionInfo> authenticate = pluginManager.invokeExtensionPointsSequentially(
                            UserAuthenticator.class,
                            AUTHENTICATE_METHOD,
                            (SequentialExtensionPointInvoker<UserAuthenticator, Boolean>)
                                    (authenticator, pre) -> pre.switchIfEmpty(Mono.defer(() -> authenticator.authenticate(userLoginInfo))))
                    .map(authenticated -> authenticated ? GRANTED_WITH_ALL_PERMISSIONS : LOGIN_AUTHENTICATION_FAILED);
            return authenticate
                    .switchIfEmpty(Mono.defer(() -> verifyAndGrant0(userLoginInfo)));
        }
        return Mono.defer(() -> verifyAndGrant0(userLoginInfo));
    }

    private Mono<UserPermissionInfo> verifyAndGrant0(UserLoginInfo userLoginInfo) {
        Long userId = userLoginInfo.userId();
        String password = userLoginInfo.password();
        return switch (identityAccessManagementType) {
            case NOOP -> GRANTED_WITH_ALL_PERMISSIONS_MONO;
            case HTTP -> verifyAndGrantUsingHttp(userLoginInfo);
            case JWT -> verifyAndGrantUsingJwt(userId, password);
            case PASSWORD -> verifyAndGrantUsingPassword(userId, password);
        };
    }

    private Mono<UserPermissionInfo> verifyAndGrantUsingHttp(UserLoginInfo userLoginInfo) {
        return httpIdentityAccessManagementClient.request(httpIdentityAccessManagementHttpMethod)
                .send(Mono.fromCallable(() -> JsonUtil.write(userLoginInfo)))
                .responseSingle((response, bodyBufferMono) -> {
                    if (!StringUtil.matchLatin1(response.status().toString(), httpAuthenticationExpectedStatusCodes)) {
                        return LOGIN_AUTHENTICATION_FAILED_MONO;
                    }
                    for (Map.Entry<String, String> entry : httpAuthenticationExpectedHeaders.entrySet()) {
                        if (!entry.getValue().equals(response.responseHeaders().get(entry.getKey()))) {
                            return LOGIN_AUTHENTICATION_FAILED_MONO;
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
                                if (!CollectionUtil.containsAllLooseComparison(map, httpAuthenticationExpectedBodyFields)) {
                                    return LOGIN_AUTHENTICATION_FAILED;
                                }
                                return new UserPermissionInfo(ResponseStatusCode.OK, policyManager.findAllowedRequestTypes(policy));
                            });
                });
    }

    private Mono<UserPermissionInfo> verifyAndGrantUsingJwt(Long userId, String jwtToken) {
        if (StringUtil.isBlank(jwtToken)) {
            return Mono.error(new IllegalArgumentException("Corrupt JWT token: JWT must not be blank"));
        }
        Jwt jwt;
        try {
            jwt = jwtManager.decode(jwtToken);
        } catch (CorruptedJwtException e) {
            return Mono.error(new IllegalArgumentException("Corrupt JWT token", e));
        } catch (UnsupportedOperationException e) {
            return Mono.error(new IllegalArgumentException(e));
        }
        long now = System.currentTimeMillis();
        JwtPayload payload = jwt.payload();
        String subject = payload.subject();
        if (subject == null) {
            return Mono.error(new IllegalArgumentException("Corrupt JWT token: the sub claim in the payload must exist"));
        }
        if (!subject.equals(userId.toString())) {
            return LOGIN_AUTHENTICATION_FAILED_MONO;
        }
        Date expiresAt = payload.expiresAt();
        if (expiresAt != null && expiresAt.getTime() <= now) {
            return LOGIN_AUTHENTICATION_FAILED_MONO;
        }
        Date notBefore = payload.notBefore();
        if (notBefore != null && notBefore.getTime() > now) {
            return LOGIN_AUTHENTICATION_FAILED_MONO;
        }
        Map<String, Object> customClaims = payload.customClaims();
        if (!CollectionUtil.containsAllLooseComparison(customClaims, jwtAuthenticationExpectedCustomPayloadClaims)) {
            return LOGIN_AUTHENTICATION_FAILED_MONO;
        }
        Policy policy;
        try {
            policy = PolicyDeserializer.parse(customClaims);
        } catch (IllegalPolicyException e) {
            return Mono.error(new IllegalArgumentException("Corrupt JWT token", e));
        }
        return Mono.just(new UserPermissionInfo(ResponseStatusCode.OK,
                policyManager.findAllowedRequestTypes(policy)));
    }

    private Mono<UserPermissionInfo> verifyAndGrantUsingPassword(Long userId, String password) {
        return userService.isActiveAndNotDeleted(userId)
                .flatMap(isActiveAndNotDeleted -> isActiveAndNotDeleted
                        ? userService.authenticate(userId, password)
                        .map(authenticated -> authenticated ? GRANTED_WITH_ALL_PERMISSIONS : LOGIN_AUTHENTICATION_FAILED)
                        : LOGGING_IN_USER_NOT_ACTIVE_MONO);
    }

}
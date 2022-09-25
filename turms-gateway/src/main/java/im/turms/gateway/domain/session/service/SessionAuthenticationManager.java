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

import im.turms.gateway.domain.session.bo.UserLoginInfo;
import im.turms.gateway.infra.plugin.extension.UserAuthenticator;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
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
import im.turms.server.common.infra.property.constant.AuthenticationType;
import im.turms.server.common.infra.property.env.gateway.SessionProperties;
import im.turms.server.common.infra.property.env.gateway.authentication.AuthenticationProperties;
import im.turms.server.common.infra.property.env.gateway.authentication.HttpAuthenticationProperties;
import im.turms.server.common.infra.property.env.gateway.authentication.HttpAuthenticationRequestProperties;
import im.turms.server.common.infra.property.env.gateway.authentication.HttpAuthenticationResponseExpectationProperties;
import im.turms.server.common.infra.property.env.gateway.authentication.JwtAlgorithmProperties;
import im.turms.server.common.infra.property.env.gateway.authentication.JwtAuthenticationProperties;
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
public class SessionAuthenticationManager implements SessionAuthenticationSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionAuthenticationManager.class);

    private static final Method AUTHENTICATE_METHOD;

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

    private final UserService userService;

    private final HttpClient httpAuthenticationClient;
    private final HttpMethod httpAuthenticationHttpMethod;
    private final Set<String> httpAuthenticationExpectedStatusCodes;
    private final Map<String, String> httpAuthenticationExpectedHeaders;
    private final Map<String, String> httpAuthenticationExpectedBodyFields;

    private final Map<String, String> expectedCustomPayloadClaims;

    private boolean enableAuthentication;
    private final AuthenticationType authenticationType;

    public SessionAuthenticationManager(TurmsPropertiesManager propertiesManager,
                                        PluginManager pluginManager,
                                        UserService userService) {
        this.pluginManager = pluginManager;
        this.userService = userService;
        updateGlobalProperties(propertiesManager.getGlobalProperties());
        AuthenticationProperties authenticationProperties = propertiesManager.getLocalProperties().getGateway().getSession().getAuthentication();
        authenticationType = authenticationProperties.getType();
        JwtManager jwtManager = null;
        Map<String, String> expectedCustomPayloadClaims = null;
        HttpClient httpAuthenticationClient = null;
        HttpMethod httpAuthenticationHttpMethod = null;
        Set<String> httpAuthenticationExpectedStatusCodes = null;
        Map<String, String> httpAuthenticationExpectedHeaders = null;
        Map<String, String> httpAuthenticationExpectedBodyFields = null;
        if (authenticationType == AuthenticationType.JWT) {
            JwtAuthenticationProperties jwtProperties = authenticationProperties.getJwt();
            expectedCustomPayloadClaims = jwtProperties.getExpectation().getCustomPayloadClaims();
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
                    jwtAlgorithmProperties.getHmac256SecretFilePath(),
                    jwtAlgorithmProperties.getHmac384SecretFilePath(),
                    jwtAlgorithmProperties.getHmac512SecretFilePath());
            LOGGER.info("The supported algorithms for JWT authentication are: " + jwtManager.getSupportedAlgorithmNames());
        } else if (authenticationType == AuthenticationType.HTTP) {
            HttpAuthenticationProperties httpProperties = authenticationProperties.getHttp();
            HttpAuthenticationRequestProperties requestProperties = httpProperties.getRequest();
            HttpAuthenticationResponseExpectationProperties responseExpectationProperties = httpProperties.getResponseExpectation();
            String url = requestProperties.getUrl();
            try {
                new URL(url).toURI();
            } catch (MalformedURLException | URISyntaxException e) {
                throw new IllegalArgumentException("The HTTP URL for authentication is illegal", e);
            }
            httpAuthenticationClient = HttpClient.create()
                    .baseUrl(url)
                    .headers(entries -> {
                        for (Map.Entry<String, String> entry : requestProperties.getHeaders().entrySet()) {
                            entries.add(entry.getKey(), entry.getValue());
                        }
                    })
                    .responseTimeout(Duration.ofMillis(requestProperties.getTimeoutMillis()));
            httpAuthenticationHttpMethod = HttpMethod.valueOf(requestProperties.getHttpMethod().name());
            httpAuthenticationExpectedStatusCodes = responseExpectationProperties.getStatusCodes();
            httpAuthenticationExpectedHeaders = responseExpectationProperties.getHeaders();
            httpAuthenticationExpectedBodyFields = responseExpectationProperties.getBodyFields();
        }
        this.jwtManager = jwtManager;
        this.expectedCustomPayloadClaims = expectedCustomPayloadClaims;
        this.httpAuthenticationClient = httpAuthenticationClient;
        this.httpAuthenticationHttpMethod = httpAuthenticationHttpMethod;
        this.httpAuthenticationExpectedStatusCodes = httpAuthenticationExpectedStatusCodes;
        this.httpAuthenticationExpectedHeaders = httpAuthenticationExpectedHeaders;
        this.httpAuthenticationExpectedBodyFields = httpAuthenticationExpectedBodyFields;
        propertiesManager.addGlobalPropertiesChangeListener(this::updateGlobalProperties);
    }

    private void updateGlobalProperties(TurmsProperties properties) {
        SessionProperties sessionProperties = properties.getGateway().getSession();
        enableAuthentication = sessionProperties.getAuthentication().isEnabled();
    }

    @Override
    public Mono<ResponseStatusCode> authenticate(int version,
                                                 Long userId,
                                                 @Nullable String password,
                                                 DeviceType deviceType,
                                                 @Nullable Map<String, String> deviceDetails,
                                                 @Nullable UserStatus userStatus,
                                                 @Nullable Location location,
                                                 @Nullable String ip) {
        if (userId.equals(AdminConst.ADMIN_REQUESTER_ID)) {
            return Mono.just(ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED);
        }
        if (!enableAuthentication) {
            return Mono.just(ResponseStatusCode.OK);
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
        if (pluginManager.hasRunningExtensions(UserAuthenticator.class)) {
            Mono<ResponseStatusCode> authenticate = pluginManager.invokeExtensionPointsSequentially(
                            UserAuthenticator.class,
                            AUTHENTICATE_METHOD,
                            (SequentialExtensionPointInvoker<UserAuthenticator, Boolean>)
                                    (authenticator, pre) -> pre.switchIfEmpty(Mono.defer(() -> authenticator.authenticate(userLoginInfo))))
                    .map(authenticated -> authenticated ? ResponseStatusCode.OK : ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED);
            return authenticate
                    .switchIfEmpty(Mono.defer(() -> authenticate0(userLoginInfo)));
        }
        return Mono.defer(() -> authenticate0(userLoginInfo));
    }

    private Mono<ResponseStatusCode> authenticate0(UserLoginInfo userLoginInfo) {
        Long userId = userLoginInfo.userId();
        String password = userLoginInfo.password();
        return switch (authenticationType) {
            case NOOP -> Mono.just(ResponseStatusCode.OK);
            case HTTP -> authenticateUsingHttp(userLoginInfo);
            case JWT -> authenticateUsingJwt(password);
            case PASSWORD -> authenticateUsingPassword(userId, password);
        };
    }

    private Mono<ResponseStatusCode> authenticateUsingHttp(UserLoginInfo userLoginInfo) {
        return httpAuthenticationClient.request(httpAuthenticationHttpMethod)
                .send(Mono.fromCallable(() -> JsonUtil.write(userLoginInfo)))
                .responseSingle((response, bodyBufferMono) -> {
                    if (!StringUtil.match(response.status().toString(), httpAuthenticationExpectedStatusCodes)) {
                        return Mono.just(ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED);
                    }
                    for (Map.Entry<String, String> entry : httpAuthenticationExpectedHeaders.entrySet()) {
                        if (!entry.getValue().equals(response.responseHeaders().get(entry.getKey()))) {
                            return Mono.just(ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED);
                        }
                    }
                    if (httpAuthenticationExpectedBodyFields.isEmpty()) {
                        return Mono.just(ResponseStatusCode.OK);
                    }
                    return bodyBufferMono.asInputStream()
                            .map(inputStream -> {
                                Map<String, String> map;
                                try {
                                    map = JsonUtil.readStringStringMapValue(inputStream);
                                } catch (Exception e) {
                                    throw new IllegalArgumentException("Illegal request body", e);
                                }
                                return CollectionUtil.containsAllLooseComparison(map, httpAuthenticationExpectedBodyFields)
                                        ? ResponseStatusCode.OK
                                        : ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED;
                            });
                });
    }

    private Mono<ResponseStatusCode> authenticateUsingJwt(String jwtToken) {
        if (StringUtil.isBlank(jwtToken)) {
            return Mono.error(new IllegalArgumentException("The JWT token must not be blank"));
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
        Date expiresAt = payload.expiresAt();
        if (expiresAt != null && expiresAt.getTime() <= now) {
            return Mono.just(ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED);
        }
        Date notBefore = payload.notBefore();
        if (notBefore != null && notBefore.getTime() > now) {
            return Mono.just(ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED);
        }
        return CollectionUtil.containsAllLooseComparison(payload.customClaims(), expectedCustomPayloadClaims)
                ? Mono.just(ResponseStatusCode.OK)
                : Mono.just(ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED);
    }

    private Mono<ResponseStatusCode> authenticateUsingPassword(Long userId, String password) {
        return userService.isActiveAndNotDeleted(userId)
                .flatMap(isActiveAndNotDeleted -> isActiveAndNotDeleted
                        ? userService.authenticate(userId, password)
                        .map(authenticated -> authenticated ? ResponseStatusCode.OK : ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED)
                        : Mono.just(ResponseStatusCode.LOGGING_IN_USER_NOT_ACTIVE));
    }

}
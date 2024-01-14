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

import java.util.List;

import reactor.core.publisher.Mono;

import im.turms.gateway.domain.session.bo.UserLoginInfo;
import im.turms.gateway.domain.session.bo.UserPermissionInfo;
import im.turms.gateway.infra.ldap.LdapClient;
import im.turms.gateway.infra.ldap.element.operation.search.DerefAliases;
import im.turms.gateway.infra.ldap.element.operation.search.Scope;
import im.turms.gateway.infra.ldap.element.operation.search.SearchRequest;
import im.turms.gateway.infra.ldap.element.operation.search.SearchResultEntry;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.ldap.LdapIdentityAccessManagementAdminProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.ldap.LdapIdentityAccessManagementProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.ldap.LdapIdentityAccessManagementUserProperties;
import im.turms.server.common.infra.reactor.TaskScheduler;
import im.turms.server.common.infra.time.DurationConst;

import static im.turms.gateway.domain.session.bo.UserPermissionInfo.GRANTED_WITH_ALL_PERMISSIONS_MONO;
import static im.turms.gateway.domain.session.bo.UserPermissionInfo.LOGGING_IN_USER_NOT_ACTIVE_MONO;
import static im.turms.gateway.domain.session.bo.UserPermissionInfo.LOGIN_AUTHENTICATION_FAILED_MONO;

/**
 * @author James Chen
 */
public class LdapSessionIdentityAccessManager implements SessionIdentityAccessManagementSupport {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(LdapSessionIdentityAccessManager.class);

    private final String baseDn;
    private final String userSearchFilter;

    private final LdapClient adminLdapClient;
    private final LdapClient userLdapClient;

    private final TaskScheduler clientBindTaskScheduler;

    public LdapSessionIdentityAccessManager(LdapIdentityAccessManagementProperties properties) {
        baseDn = properties.getBaseDn();

        LdapIdentityAccessManagementAdminProperties adminProperties = properties.getAdmin();
        adminLdapClient = new LdapClient(
                adminProperties.getHost(),
                adminProperties.getPort(),
                adminProperties.getSsl());

        LdapIdentityAccessManagementUserProperties userProperties = properties.getUser();
        userSearchFilter = userProperties.getSearchFilter();
        if (!userSearchFilter.contains(
                LdapIdentityAccessManagementUserProperties.SEARCH_FILTER_PLACEHOLDER_USER_ID)) {
            throw new IllegalArgumentException(
                    "The user search filter must contain the placeholder for user ID");
        }
        userLdapClient = new LdapClient(
                userProperties.getHost(),
                userProperties.getPort(),
                userProperties.getSsl());

        String adminUsername = adminProperties.getUsername();
        LOGGER.info("Checking the LDAP server for the admin: "
                + adminUsername);
        LOGGER.info("Checking the LDAP server for users");
        Mono<?> checkAdminLdapServer =
                adminLdapClient.bind(false, adminUsername, adminProperties.getPassword())
                        .flatMap(authenticated -> {
                            if (!authenticated) {
                                return Mono.error(new RuntimeException(
                                        "Failed to bind to the LDAP server for the admin \""
                                                + adminUsername
                                                + "\" because of invalid credentials"));
                            }
                            return Mono.empty();
                        })
                        .then(Mono.defer(() -> adminLdapClient
                                .search(baseDn,
                                        Scope.SINGLE_LEVEL,
                                        DerefAliases.ALWAYS,
                                        1,
                                        0,
                                        false,
                                        SearchRequest.NO_ATTRIBUTES,
                                        "objectClass=*")
                                .flatMap(searchResult -> searchResult.isSuccess()
                                        ? Mono.empty()
                                        : Mono.error(new RuntimeException(
                                                "Failed to search on the LDAP server for the admin \""
                                                        + adminUsername
                                                        + "\" with the result code: "
                                                        + searchResult.getResultCode())))
                                .then()))
                        .doOnSuccess(
                                result -> LOGGER.info("Checked the LDAP server for the admin: \""
                                        + adminUsername
                                        + "\""))
                        .onErrorMap(throwable -> new RuntimeException(
                                "Failed to check the LDAP server for the admin: \""
                                        + adminUsername
                                        + "\"",
                                throwable));

        Mono<?> checkedUserLdapServer = userLdapClient.bind(true, "", "")
                // We don't need to check its response because we just need to
                // ensure it can connect, and the communication works fine.
                .doOnSuccess(authenticated -> LOGGER.info("Checked the LDAP server for users"))
                .onErrorMap(throwable -> new RuntimeException(
                        "Failed to check the LDAP server for users",
                        throwable));

        Mono.when(checkAdminLdapServer, checkedUserLdapServer)
                .block(DurationConst.ONE_MINUTE);

        clientBindTaskScheduler = new TaskScheduler();
    }

    @Override
    public Mono<UserPermissionInfo> verifyAndGrant(UserLoginInfo userLoginInfo) {
        String password = userLoginInfo.password();
        if (StringUtil.isBlank(password)) {
            return LOGIN_AUTHENTICATION_FAILED_MONO;
        }
        Long userId = userLoginInfo.userId();
        String filter = userSearchFilter.replace(
                LdapIdentityAccessManagementUserProperties.SEARCH_FILTER_PLACEHOLDER_USER_ID,
                userId.toString());
        return adminLdapClient.search(baseDn,
                Scope.WHOLE_SUBTREE,
                DerefAliases.ALWAYS,
                // Use 2 so that we can refuse to authenticate user
                // if there are more than 1 entry.
                2,
                0,
                false,
                SearchRequest.NO_ATTRIBUTES,
                filter)
                .flatMap(searchResult -> {
                    List<SearchResultEntry> entries = searchResult.getEntries();
                    int size = entries.size();
                    if (size == 0) {
                        return LOGGING_IN_USER_NOT_ACTIVE_MONO;
                    }
                    if (size > 1) {
                        return Mono.error(
                                ResponseException.get(ResponseStatusCode.SERVER_INTERNAL_ERROR,
                                        "More than 1 entry found for the user ("
                                                + userId
                                                + "), "
                                                + "which means that the filter \""
                                                + userSearchFilter
                                                + "\" is wrong"));
                    }
                    String objectName = entries.getFirst()
                            .getObjectName();
                    return authenticateUser(objectName, password)
                            .flatMap(authenticated -> authenticated
                                    ? GRANTED_WITH_ALL_PERMISSIONS_MONO
                                    : LOGIN_AUTHENTICATION_FAILED_MONO);
                });
    }

    private Mono<Boolean> authenticateUser(String dn, String password) {
        // RFC 4511: 4.2.1. Processing of the Bind Request
        // After sending a BindRequest, clients MUST NOT send further LDAP PDUs
        // until receiving the BindResponse. Similarly, servers SHOULD NOT
        // process or respond to requests received while processing a
        // BindRequest.
        return clientBindTaskScheduler
                .schedule(Mono.defer(() -> userLdapClient.bind(true, dn, password)));
    }

}
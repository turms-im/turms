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

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import im.turms.gateway.domain.session.repository.UserRepository;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.security.password.PasswordManager;
import im.turms.server.common.infra.validation.Validator;

/**
 * @author James Chen
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordManager passwordManager;

    public UserService(UserRepository userRepository, PasswordManager passwordManager) {
        this.userRepository = userRepository;
        this.passwordManager = passwordManager;
    }

    public Mono<Boolean> authenticate(@NotNull Long userId, @Nullable String rawPassword) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userRepository.findPassword(userId)
                .map(user -> passwordManager.matchesUserPassword(rawPassword, user.getPassword()))
                // empty if the user doesn't exist
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> isActiveAndNotDeleted(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userRepository.isActiveAndNotDeleted(userId);
    }

}

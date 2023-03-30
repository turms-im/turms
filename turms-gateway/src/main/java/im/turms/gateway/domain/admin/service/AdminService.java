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

package im.turms.gateway.domain.admin.service;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import im.turms.gateway.domain.admin.repository.AdminRepository;
import im.turms.server.common.domain.admin.service.BaseAdminService;
import im.turms.server.common.infra.security.password.PasswordManager;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class AdminService extends BaseAdminService {

    public AdminService(
            PasswordManager passwordManager,
            AdminRepository adminRepository,
            AdminRoleService adminRoleService) {
        super(passwordManager, adminRepository, adminRoleService);
        listenAndLoadAdmins();
    }

}
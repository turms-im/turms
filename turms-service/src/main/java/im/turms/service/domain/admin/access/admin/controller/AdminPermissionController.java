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

package im.turms.service.domain.admin.access.admin.controller;

import java.util.Collection;
import java.util.List;

import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.admin.access.admin.dto.response.PermissionDTO;
import im.turms.service.domain.common.access.admin.controller.BaseController;

/**
 * @author James Chen
 */
@RestController("admins/permissions")
public class AdminPermissionController extends BaseController {

    private static final List<PermissionDTO> ALL_PERMISSIONS;

    static {
        AdminPermission[] permissions = ClassUtil.getSharedEnumConstants(AdminPermission.class);
        ALL_PERMISSIONS = CollectionUtil.transformAsList(permissions,
                permission -> new PermissionDTO(permission.getGroup(), permission));
    }

    public AdminPermissionController(TurmsPropertiesManager propertiesManager) {
        super(propertiesManager);
    }

    @GetMapping
    @RequiredPermission(AdminPermission.ADMIN_PERMISSION_QUERY)
    public ResponseDTO<Collection<PermissionDTO>> queryAdminPermissions() {
        return new ResponseDTO<>(ResponseStatusCode.OK, ALL_PERMISSIONS);
    }

}
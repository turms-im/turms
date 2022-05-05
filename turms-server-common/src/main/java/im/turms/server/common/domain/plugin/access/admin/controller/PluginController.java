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

package im.turms.server.common.domain.plugin.access.admin.controller;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.dto.response.UpdateResultDTO;
import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.PutMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.domain.plugin.access.admin.dto.request.AddJsPluginDTO;
import im.turms.server.common.domain.plugin.access.admin.dto.request.UpdatePluginDTO;
import im.turms.server.common.domain.plugin.access.admin.dto.response.ExtensionDTO;
import im.turms.server.common.domain.plugin.access.admin.dto.response.PluginDTO;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.plugin.ExtensionPoint;
import im.turms.server.common.infra.plugin.Plugin;
import im.turms.server.common.infra.plugin.PluginDescriptor;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.plugin.TurmsExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author James Chen
 */
@RestController("plugins")
public class PluginController {

    private final PluginManager pluginManager;

    public PluginController(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @GetMapping
    @RequiredPermission(AdminPermission.PLUGIN_QUERY)
    public HttpHandlerResult<ResponseDTO<Collection<PluginDTO>>> getPlugins(@QueryParam(required = false) Set<String> ids) {
        Collection<Plugin> plugins = CollectionUtil.isEmpty(ids)
                ? pluginManager.getPlugins()
                : pluginManager.getPlugins(ids);
        List<PluginDTO> pluginInfoList = new ArrayList<>(plugins.size());
        for (Plugin plugin : plugins) {
            List<TurmsExtension> extensions = plugin.extensions();
            List<ExtensionDTO> extensionDTOs = new ArrayList<>(extensions.size());
            for (TurmsExtension extension : extensions) {
                List<Class<? extends ExtensionPoint>> classes = pluginManager.getExtensionPoints(extension);
                List<String> classNames = new ArrayList<>(classes.size());
                for (Class<? extends ExtensionPoint> clazz : classes) {
                    classNames.add(clazz.getName());
                }
                extensionDTOs.add(new ExtensionDTO(extension.getClass().getName(),
                        extension.isStarted(),
                        extension.isRunning(),
                        classNames));
            }
            PluginDescriptor descriptor = plugin.descriptor();
            pluginInfoList.add(new PluginDTO(
                    descriptor.getId(),
                    descriptor.getVersion(),
                    descriptor.getProvider(),
                    descriptor.getLicense(),
                    descriptor.getDescription(),
                    extensionDTOs));
        }
        return HttpHandlerResult.okIfTruthy(pluginInfoList);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.PLUGIN_UPDATE)
    public HttpHandlerResult<ResponseDTO<UpdateResultDTO>> updatePlugins(
            Set<String> ids,
            @RequestBody UpdatePluginDTO updatePluginDTO) {
        UpdatePluginDTO.PluginStatus status = updatePluginDTO.status();
        if (status == null) {
            return HttpHandlerResult.okIfTruthy(UpdateResultDTO.NONE);
        }
        long count = switch (status) {
            case STARTED -> pluginManager.startPlugins(ids);
            case STOPPED -> pluginManager.stopPlugins(ids);
            case RESUMED -> pluginManager.resumePlugins(ids);
            case PAUSED -> pluginManager.pausePlugins(ids);
        };
        return HttpHandlerResult.okIfTruthy(new UpdateResultDTO(count, count));
    }

    @PostMapping("js")
    @RequiredPermission(AdminPermission.PLUGIN_CREATE)
    public HttpHandlerResult<ResponseDTO<Void>> createJsPlugins(
            boolean save,
            @RequestBody AddJsPluginDTO addJsPluginDTO) {
        Set<String> scripts = addJsPluginDTO.scripts();
        pluginManager.loadJsPlugins(scripts, save);
        return HttpHandlerResult.RESPONSE_OK;
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.PLUGIN_DELETE)
    public HttpHandlerResult<ResponseDTO<Void>> deletePlugins(
            Set<String> ids,
            boolean deleteLocalFiles) {
        pluginManager.deletePlugins(ids, deleteLocalFiles);
        return HttpHandlerResult.RESPONSE_OK;
    }

}
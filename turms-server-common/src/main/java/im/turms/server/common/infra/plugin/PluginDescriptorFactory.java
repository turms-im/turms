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

package im.turms.server.common.infra.plugin;

import java.util.List;
import java.util.Map;
import jakarta.annotation.Nullable;

import org.springframework.util.StringUtils;

import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.lang.StringUtil;

/**
 * @author James Chen
 */
public abstract class PluginDescriptorFactory {

    static final String PLUGIN_ID = "id";
    static final String PLUGIN_VERSION = "version";
    static final String PLUGIN_PROVIDER = "provider";
    static final String PLUGIN_LICENSE = "license";
    static final String PLUGIN_DESCRIPTION = "description";
    static final String PLUGIN_COMPATIBLE_SERVER = "compatible-server";
    static final String PLUGIN_COMPATIBLE_SERVER_TYPE = "type";
    private static final NodeType[] NODE_TYPES = ClassUtil.getSharedEnumConstants(NodeType.class);
    static final String SERVER_TYPES = StringUtil.joinLatin1(", ",
            CollectionUtil.transformAsList(NODE_TYPES, NodeType::getId));
    static final Map<NodeType, PluginDescriptor.ServerInfo> ALL_SERVERS =
            CollectionUtil.newMap(NODE_TYPES, nodeType -> PluginDescriptor.ServerInfo.DEFAULT);

    public static PluginDescriptor createPluginDescriptor(Map<String, Object> properties) {
        String id = readPropertyAsString(properties, PLUGIN_ID, true);
        Map<NodeType, PluginDescriptor.ServerInfo> compatibleServers =
                readCompatibleServersProperty(properties);
        String version = readPropertyAsString(properties, PLUGIN_VERSION, false);
        String provider = readPropertyAsString(properties, PLUGIN_PROVIDER, false);
        String license = readPropertyAsString(properties, PLUGIN_LICENSE, false);
        String description = readPropertyAsString(properties, PLUGIN_DESCRIPTION, false);
        return new PluginDescriptor(
                id,
                compatibleServers,
                version,
                provider,
                license,
                description,
                null);
    }

    /**
     * @implNote If the plugin does not specify the compatible servers, we consider it supports all
     *           server types by default
     */
    private static Map<NodeType, PluginDescriptor.ServerInfo> readCompatibleServersProperty(
            Map<String, Object> properties) {
        List<Object> compatibleServers =
                readPropertyAsList(properties, PLUGIN_COMPATIBLE_SERVER, false);
        if (compatibleServers == null) {
            return ALL_SERVERS;
        }
        Map<NodeType, PluginDescriptor.ServerInfo> servers =
                CollectionUtil.newMapWithExpectedSize(compatibleServers.size());
        for (Object compatibleServer : compatibleServers) {
            NodeType serverType;
            if (!(compatibleServer instanceof Map<?, ?> map)) {
                throw new IllegalArgumentException(
                        "The value of the field \""
                                + PLUGIN_COMPATIBLE_SERVER
                                + "\" must be key-value pairs");
            }
            Object type = map.get(PLUGIN_COMPATIBLE_SERVER_TYPE);
            if (type == null) {
                continue;
            }
            if (!(type instanceof String typeStr)) {
                throw new IllegalArgumentException(
                        "The value of the field \""
                                + PLUGIN_COMPATIBLE_SERVER
                                + "."
                                + PLUGIN_COMPATIBLE_SERVER_TYPE
                                + "\""
                                + " must be one of the types: "
                                + SERVER_TYPES);
            }
            try {
                serverType = NodeType.valueOf(typeStr);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        "The value of the field \""
                                + PLUGIN_COMPATIBLE_SERVER
                                + "."
                                + PLUGIN_COMPATIBLE_SERVER_TYPE
                                + "\""
                                + " must be one of the types: "
                                + SERVER_TYPES);
            }
            if (servers.put(serverType, PluginDescriptor.ServerInfo.DEFAULT) != null) {
                throw new IllegalArgumentException(
                        "The compatible server type can only be specified once at most, but the server type ("
                                + serverType
                                + ") is specified more than once");
            }
        }
        return servers;
    }

    @Nullable
    public static <T> List<T> readPropertyAsList(
            Map<String, Object> properties,
            String key,
            boolean required) {
        List<T> value;
        try {
            value = (List<T>) properties.get(key);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "The value of the field \""
                            + key
                            + "\" must be key-value pairs",
                    e);
        }
        if (value == null && required) {
            throw new IllegalArgumentException(
                    "The value of the field \""
                            + key
                            + "\" must be key-value pairs");
        }
        return value;
    }

    @Nullable
    public static <T> Map<String, T> readPropertyAsMap(
            Map<String, Object> properties,
            String key,
            boolean required) {
        Map<String, T> value;
        try {
            value = (Map<String, T>) properties.get(key);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "The value of the field \""
                            + key
                            + "\" must be key-value pairs",
                    e);
        }
        if (value == null && required) {
            throw new IllegalArgumentException(
                    "The value of the field \""
                            + key
                            + "\" must be key-value pairs");
        }
        return value;
    }

    @Nullable
    public static String readPropertyAsString(
            Map<String, Object> properties,
            String key,
            boolean required) {
        return readPropertyAsString(properties, null, key, required);
    }

    @Nullable
    public static String readPropertyAsString(
            Map<String, Object> properties,
            @Nullable String parent,
            String key,
            boolean required) {
        String value;
        try {
            value = (String) properties.get(key);
        } catch (Exception e) {
            String message = parent == null
                    ? "The value of the field \""
                            + key
                            + "\" must be a string"
                    : "The value of the field \""
                            + parent
                            + "."
                            + key
                            + "\" must be a string";
            throw new IllegalArgumentException(message, e);
        }
        if (StringUtils.hasText(value)) {
            return value;
        }
        if (required) {
            String message = parent == null
                    ? "The value of the field \""
                            + key
                            + "\" must not be blank"
                    : "The value of the field \""
                            + parent
                            + "."
                            + key
                            + "\" must not be blank";
            throw new IllegalArgumentException(message);
        }
        return null;
    }
}
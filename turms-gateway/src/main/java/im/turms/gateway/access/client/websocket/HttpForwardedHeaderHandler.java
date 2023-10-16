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

package im.turms.gateway.access.client.websocket;

import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import reactor.netty.http.server.ConnectionInfo;
import reactor.netty.transport.AddressUtils;

import im.turms.server.common.infra.lang.IntUtil;

import static reactor.netty.http.server.ConnectionInfo.getDefaultHostPort;

/**
 * @author James Chen
 * @see reactor.netty.http.server.DefaultHttpForwardedHeaderHandler
 */
public class HttpForwardedHeaderHandler
        implements BiFunction<ConnectionInfo, HttpRequest, ConnectionInfo> {

    private static final String FORWARDED_HEADER = "Forwarded";
    private static final String X_FORWARDED_IP_HEADER = "X-Forwarded-For";
    private static final String X_FORWARDED_HOST_HEADER = "X-Forwarded-Host";
    private static final String X_FORWARDED_PORT_HEADER = "X-Forwarded-Port";
    private static final String X_FORWARDED_PROTO_HEADER = "X-Forwarded-Proto";

    private static final String NO_FORWARDED_IP_ERROR_MESSAGE =
            "The \"for\" directive must be specified in the \"Forwarded\" header, or the \""
                    + X_FORWARDED_IP_HEADER
                    + "\" header must be specified";

    private static final Pattern FORWARDED_HOST_PATTERN = Pattern.compile("host=\"?([^;,\"]+)\"?");
    private static final Pattern FORWARDED_PROTO_PATTERN =
            Pattern.compile("proto=\"?([^;,\"]+)\"?");
    private static final Pattern FORWARDED_FOR_PATTERN = Pattern.compile("for=\"?([^;,\"]+)\"?");

    private final boolean isForwardedIpRequired;

    public HttpForwardedHeaderHandler(boolean isForwardedIpRequired) {
        this.isForwardedIpRequired = isForwardedIpRequired;
    }

    @Override
    public ConnectionInfo apply(ConnectionInfo connectionInfo, HttpRequest request) {
        String forwardedHeader = request.headers()
                .get(FORWARDED_HEADER);
        if (forwardedHeader != null) {
            return parseForwardedInfo(connectionInfo, forwardedHeader);
        }
        return parseXForwardedInfo(connectionInfo, request);
    }

    private ConnectionInfo parseForwardedInfo(
            ConnectionInfo connectionInfo,
            String forwardedHeader) {
        String forwarded = forwardedHeader.split(",", 2)[0];
        Matcher forMatcher = FORWARDED_FOR_PATTERN.matcher(forwarded);
        if (forMatcher.find()) {
            connectionInfo = connectionInfo.withRemoteAddress(AddressUtils.parseAddress(
                    forMatcher.group(1)
                            .trim(),
                    connectionInfo.getRemoteAddress()
                            .getPort(),
                    true));
        } else if (isForwardedIpRequired) {
            throw new IllegalArgumentException(
                    "The \"for\" directive must be specified in the \"Forwarded\" header");
        }
        Matcher protoMatcher = FORWARDED_PROTO_PATTERN.matcher(forwarded);
        if (protoMatcher.find()) {
            connectionInfo = connectionInfo.withScheme(protoMatcher.group(1)
                    .trim());
        }
        Matcher hostMatcher = FORWARDED_HOST_PATTERN.matcher(forwarded);
        if (hostMatcher.find()) {
            connectionInfo =
                    connectionInfo.withHostAddress(AddressUtils.parseAddress(hostMatcher.group(1),
                            getDefaultHostPort(connectionInfo.getScheme()),
                            true));
        }
        return connectionInfo;
    }

    private ConnectionInfo parseXForwardedInfo(ConnectionInfo connectionInfo, HttpRequest request) {
        HttpHeaders headers = request.headers();
        String ipHeader = headers.get(X_FORWARDED_IP_HEADER);
        if (ipHeader != null) {
            connectionInfo = connectionInfo
                    .withRemoteAddress(AddressUtils.parseAddress(ipHeader.split(",", 2)[0],
                            connectionInfo.getRemoteAddress()
                                    .getPort()));
        } else if (isForwardedIpRequired) {
            throw new IllegalArgumentException(NO_FORWARDED_IP_ERROR_MESSAGE);
        }
        String protoHeader = headers.get(X_FORWARDED_PROTO_HEADER);
        if (protoHeader != null) {
            connectionInfo = connectionInfo.withScheme(protoHeader.split(",", 2)[0].trim());
        }
        String hostHeader = headers.get(X_FORWARDED_HOST_HEADER);
        if (hostHeader != null) {
            connectionInfo = connectionInfo
                    .withHostAddress(AddressUtils.parseAddress(hostHeader.split(",", 2)[0].trim(),
                            getDefaultHostPort(connectionInfo.getScheme()),
                            true));
        }

        String portHeader = headers.get(X_FORWARDED_PORT_HEADER);
        if (portHeader != null && !portHeader.isEmpty()) {
            String portStr = portHeader.split(",", 2)[0].trim();
            Integer port = IntUtil.tryParse(portStr);
            if (port == null) {
                throw new IllegalArgumentException(NO_FORWARDED_IP_ERROR_MESSAGE);
            }
            connectionInfo =
                    connectionInfo
                            .withHostAddress(
                                    AddressUtils.createUnresolved(connectionInfo.getHostAddress()
                                            .getHostString(), port),
                                    connectionInfo.getHostName(),
                                    port);
        }
        return connectionInfo;
    }
}
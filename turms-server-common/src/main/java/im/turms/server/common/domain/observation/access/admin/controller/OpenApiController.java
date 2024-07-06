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

package im.turms.server.common.domain.observation.access.admin.controller;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import org.springframework.context.ApplicationContext;

import im.turms.server.common.access.admin.web.ApiEndpoint;
import im.turms.server.common.access.admin.web.ApiEndpointKey;
import im.turms.server.common.access.admin.web.HttpRequestDispatcher;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.infra.address.BaseServiceAddressManager;
import im.turms.server.common.infra.application.TurmsApplicationContext;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.netty.ByteBufUtil;
import im.turms.server.common.infra.openapi.OpenApiBuilder;

import static im.turms.server.common.access.admin.web.ContentEncodingConst.GZIP;
import static im.turms.server.common.access.admin.web.MediaTypeConst.APPLICATION_JAVASCRIPT;
import static im.turms.server.common.access.admin.web.MediaTypeConst.APPLICATION_JSON;
import static im.turms.server.common.access.admin.web.MediaTypeConst.IMAGE_PNG;
import static im.turms.server.common.access.admin.web.MediaTypeConst.TEXT_CSS;
import static im.turms.server.common.access.admin.web.MediaTypeConst.TEXT_HTML;
import static im.turms.server.common.infra.openapi.OpenApiResourceConst.FAVICON_32x32;
import static im.turms.server.common.infra.openapi.OpenApiResourceConst.INDEX_CSS;
import static im.turms.server.common.infra.openapi.OpenApiResourceConst.SWAGGER_UI_BUNDLE;
import static im.turms.server.common.infra.openapi.OpenApiResourceConst.SWAGGER_UI_CSS;
import static im.turms.server.common.infra.openapi.OpenApiResourceConst.SWAGGER_UI_STANDALONE_PRESET;

/**
 * @author James Chen
 */
@RestController("openapi")
public class OpenApiController {

    private final Node node;
    private final ApplicationContext context;

    private volatile ByteBuf apiBuffer;
    private final ByteBuf indexHtml;
    private final ByteBuf swaggerInitializer;

    public OpenApiController(
            Node node,
            ApplicationContext context,
            BaseServiceAddressManager serviceAddressManager) {
        this.node = node;
        this.context = context;
        // language=HTML
        String indexHtmlStr =
                """
                        <!DOCTYPE html>
                        <html lang="en">
                          <head>
                            <meta charset="UTF-8">
                            <title>{} OpenAPI</title>
                            <link rel="stylesheet" type="text/css" href="/openapi/ui/swagger-ui.css" />
                            <link rel="stylesheet" type="text/css" href="/openapi/ui/index.css" />
                            <link rel="icon" type="image/png" href="/openapi/ui/favicon-32x32.png" sizes="32x32" />
                          </head>
                          <body>
                            <div id="swagger-ui"></div>
                            <script src="/openapi/ui/swagger-ui-bundle.js" charset="UTF-8"> </script>
                            <script src="/openapi/ui/swagger-ui-standalone-preset.js" charset="UTF-8"> </script>
                            <script src="/openapi/ui/swagger-initializer.js" charset="UTF-8"> </script>
                          </body>
                        </html>
                        """;
        indexHtml = ByteBufUtil.getUnreleasableDirectBuffer(StringUtil
                .substitute(indexHtmlStr,
                        node.getNodeType()
                                .getDisplayName())
                .getBytes(StandardCharsets.UTF_8));
        // language=JavaScript
        String swaggerInitializerStr = """
                window.onload = function() {
                  window.ui = SwaggerUIBundle({
                    url: "{}/openapi/docs",
                    dom_id: '#swagger-ui',
                    deepLinking: true,
                    presets: [
                      SwaggerUIBundle.presets.apis,
                      SwaggerUIStandalonePreset
                    ],
                    plugins: [
                      SwaggerUIBundle.plugins.DownloadUrl
                    ],
                    layout: "StandaloneLayout"
                  });
                };
                """;
        swaggerInitializer = ByteBufUtil.getUnreleasableDirectBuffer(StringUtil
                .substitute(swaggerInitializerStr, serviceAddressManager.getAdminApiAddress()));
    }

    @GetMapping(value = "docs", produces = APPLICATION_JSON)
    public ByteBuf getApiDocs() {
        if (apiBuffer == null) {
            synchronized (this) {
                if (apiBuffer == null) {
                    updateApiBuffer(null);
                }
            }
        }
        return apiBuffer;
    }

    @GetMapping(value = "ui", produces = TEXT_HTML)
    public ByteBuf getIndexHtml() {
        return indexHtml;
    }

    @GetMapping(value = "ui/favicon-32x32.png", produces = IMAGE_PNG)
    public ByteBuf getFavicon3232() {
        return FAVICON_32x32;
    }

    @GetMapping(value = "ui/index.css", produces = TEXT_CSS)
    public ByteBuf getIndexCss() {
        return INDEX_CSS;
    }

    @GetMapping(value = "ui/swagger-ui.css", produces = TEXT_CSS)
    public ByteBuf getSwaggerUiCss() {
        return SWAGGER_UI_CSS;
    }

    @GetMapping(
            value = "ui/swagger-ui-bundle.js",
            produces = APPLICATION_JAVASCRIPT,
            encoding = GZIP)
    public ByteBuf getSwaggerUiBundle() {
        return SWAGGER_UI_BUNDLE;
    }

    @GetMapping(
            value = "ui/swagger-ui-standalone-preset.js",
            produces = APPLICATION_JAVASCRIPT,
            encoding = GZIP)
    public ByteBuf getSwaggerUiStandalonePreset() {
        return SWAGGER_UI_STANDALONE_PRESET;
    }

    @GetMapping(value = "ui/swagger-initializer.js", produces = APPLICATION_JAVASCRIPT)
    public ByteBuf getSwaggerInitializer() {
        return swaggerInitializer;
    }

    private synchronized void updateApiBuffer(
            @Nullable Map<ApiEndpointKey, ApiEndpoint> keyToEndpoint) {
        HttpRequestDispatcher dispatcher = context.getBean(HttpRequestDispatcher.class);
        if (apiBuffer == null) {
            dispatcher.addEndpointChangeListener(this::updateApiBuffer);
        } else {
            apiBuffer.unwrap()
                    .release();
        }
        if (keyToEndpoint == null) {
            keyToEndpoint = dispatcher.getKeyToEndpoint();
        }
        byte[] bytes = OpenApiBuilder.build(context.getBean(TurmsApplicationContext.class)
                .getBuildProperties()
                .version(),
                node.getNodeType()
                        .getDisplayName(),
                context.getBean(BaseServiceAddressManager.class)
                        .getAdminApiAddress(),
                keyToEndpoint);
        apiBuffer = ByteBufUtil.getUnreleasableDirectBuffer(bytes);
    }

}
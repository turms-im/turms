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

package im.turms.turms.openapi;

import im.turms.server.common.property.TurmsPropertiesManager;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author James Chen
 * @see <a href="https://springdoc.org">SpringDoc Reference Documentation</a>
 * @see <a href="https://github.com/OAI/OpenAPI-Specification/blob/3.0.1/versions/3.0.1.md">OpenAPI Specification</a>
 * @see org.springdoc.webflux.ui.SwaggerConfig
 */
@Configuration
public class OpenApiConfig {

    private static final String PROJECT_NAME = "Turms";

    @Bean
    public OpenApiCustomiser schemaCustomizer(TurmsPropertiesManager turmsPropertiesManager) {
        return openApi -> {
            Info info = new Info().title(PROJECT_NAME)
                    .version(turmsPropertiesManager.getLocalProperties().getCluster().getNode().getVersion());
            SecurityScheme securityScheme = new SecurityScheme()
                    .name("basicAuth")
                    .scheme("basic")
                    .type(SecurityScheme.Type.HTTP);
            openApi.info(info)
                    .security(List.of(new SecurityRequirement().addList(securityScheme.getName())))
                    .getComponents().addSecuritySchemes(securityScheme.getName(), securityScheme);
        };
    }

}
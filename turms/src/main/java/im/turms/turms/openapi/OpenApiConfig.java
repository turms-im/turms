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

import com.fasterxml.classmate.TypeResolver;
import im.turms.server.common.property.TurmsPropertiesManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ParameterType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

/**
 * @author James Chen
 * @see <a href="https://springfox.github.io/springfox/docs/current">Springfox Reference Documentation</a>
 * @see springfox.boot.starter.autoconfigure.OpenApiAutoConfiguration
 */
@Configuration
public class OpenApiConfig {

    private final TypeResolver typeResolver;

    private static final String PROJECT_NAME = "Turms";
    private static final String HEADER_FIELD_NAME_ACCOUNT = "account";
    private static final String HEADER_FIELD_NAME_PASSWORD = "password";
    private static final String DEFAULT_ACCOUNT = "turms";
    private static final String DEFAULT_PASSWORD = DEFAULT_ACCOUNT;

    public OpenApiConfig(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Bean
    public Docket docket(TurmsPropertiesManager turmsPropertiesManager) {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title(PROJECT_NAME)
                        .version(turmsPropertiesManager.getLocalProperties().getCluster().getNode().getVersion())
                        .build())
                .alternateTypeRules(newRule(typeResolver.resolve(Mono.class,
                        typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                        typeResolver.resolve(WildcardType.class)))
                .ignoredParameterTypes()
                // TODO: Exclude the APIs that don't need these headers
                //  Springfox doesn't support this for now: https://github.com/springfox/springfox/issues/1910
                .globalRequestParameters(List.of(
                        new RequestParameterBuilder()
                                .name(HEADER_FIELD_NAME_ACCOUNT)
                                .in(ParameterType.HEADER)
                                .query(builder -> builder.defaultValue(DEFAULT_ACCOUNT)
                                        .model(model -> model.scalarModel(ScalarType.STRING)))
                                .required(true)
                                .build(),
                        new RequestParameterBuilder()
                                .name(HEADER_FIELD_NAME_PASSWORD)
                                .in(ParameterType.HEADER)
                                .query(builder -> builder.defaultValue(DEFAULT_PASSWORD)
                                        .model(model -> model.scalarModel(ScalarType.STRING)))
                                .required(true)
                                .build()));
    }

}

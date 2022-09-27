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

package im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class JwtAlgorithmProperties {

    @NestedConfigurationProperty
    private JwtKeyAlgorithmProperties rsa256 = new JwtKeyAlgorithmProperties();

    @NestedConfigurationProperty
    private JwtKeyAlgorithmProperties rsa384 = new JwtKeyAlgorithmProperties();

    @NestedConfigurationProperty
    private JwtKeyAlgorithmProperties rsa512 = new JwtKeyAlgorithmProperties();

    @NestedConfigurationProperty
    private JwtKeyAlgorithmProperties ps256 = new JwtKeyAlgorithmProperties();

    @NestedConfigurationProperty
    private JwtKeyAlgorithmProperties ps384 = new JwtKeyAlgorithmProperties();

    @NestedConfigurationProperty
    private JwtKeyAlgorithmProperties ps512 = new JwtKeyAlgorithmProperties();

    @NestedConfigurationProperty
    private JwtKeyAlgorithmProperties ecdsa256 = new JwtKeyAlgorithmProperties();

    @NestedConfigurationProperty
    private JwtKeyAlgorithmProperties ecdsa384 = new JwtKeyAlgorithmProperties();

    @NestedConfigurationProperty
    private JwtKeyAlgorithmProperties ecdsa512 = new JwtKeyAlgorithmProperties();

    @NestedConfigurationProperty
    private JwtSecretKeyAlgorithmProperties hmac256 = new JwtSecretKeyAlgorithmProperties();

    @NestedConfigurationProperty
    private JwtSecretKeyAlgorithmProperties hmac384 = new JwtSecretKeyAlgorithmProperties();

    @NestedConfigurationProperty
    private JwtSecretKeyAlgorithmProperties hmac512 = new JwtSecretKeyAlgorithmProperties();

}

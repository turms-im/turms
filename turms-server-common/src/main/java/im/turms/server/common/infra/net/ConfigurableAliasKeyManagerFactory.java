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

package im.turms.server.common.infra.net;

import java.security.NoSuchAlgorithmException;
import javax.net.ssl.KeyManagerFactory;

/**
 * @author James Chen
 */
public class ConfigurableAliasKeyManagerFactory extends KeyManagerFactory {

    ConfigurableAliasKeyManagerFactory(String alias, String algorithm)
            throws NoSuchAlgorithmException {
        this(KeyManagerFactory.getInstance(algorithm), alias, algorithm);
    }

    private ConfigurableAliasKeyManagerFactory(
            KeyManagerFactory delegate,
            String alias,
            String algorithm) {
        super(new ConfigurableAliasKeyManagerFactorySpi(delegate, alias),
                delegate.getProvider(),
                algorithm);
    }

}
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

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Arrays;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.X509ExtendedKeyManager;

/**
 * @author James Chen
 */
public class ConfigurableAliasKeyManagerFactorySpi extends KeyManagerFactorySpi {

    private final KeyManagerFactory delegate;
    private final String alias;

    ConfigurableAliasKeyManagerFactorySpi(KeyManagerFactory delegate, String alias) {
        this.delegate = delegate;
        this.alias = alias;
    }

    @Override
    protected void engineInit(KeyStore keyStore, char[] chars)
            throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        delegate.init(keyStore, chars);
    }

    @Override
    protected void engineInit(ManagerFactoryParameters managerFactoryParameters)
            throws InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("Unsupported ManagerFactoryParameters");
    }

    @Override
    protected KeyManager[] engineGetKeyManagers() {
        return Arrays.stream(delegate.getKeyManagers())
                .filter(X509ExtendedKeyManager.class::isInstance)
                .map(X509ExtendedKeyManager.class::cast)
                .map(this::wrap)
                .toArray(KeyManager[]::new);
    }

    private ConfigurableAliasKeyManager wrap(X509ExtendedKeyManager keyManager) {
        return new ConfigurableAliasKeyManager(keyManager, alias);
    }

}
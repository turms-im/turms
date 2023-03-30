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

import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.util.Arrays;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import jakarta.annotation.Nullable;

import lombok.SneakyThrows;
import org.springframework.boot.web.server.SslConfigurationValidator;
import org.springframework.boot.web.server.SslStoreProvider;
import org.springframework.util.ResourceUtils;
import reactor.netty.http.Http11SslContextSpec;
import reactor.netty.http.server.HttpServer;
import reactor.netty.tcp.AbstractProtocolSslContextSpec;
import reactor.netty.tcp.DefaultSslContextSpec;
import reactor.netty.tcp.SslProvider;

import im.turms.server.common.infra.property.env.common.SslProperties;

/**
 * @author James Chen
 */
public final class SslUtil {

    private SslUtil() {
    }

    public static void configureSslContextSpec(
            SslProvider.SslContextSpec sslContextSpec,
            SslProperties ssl,
            boolean forServer) {
        if (ssl.isEnabled()) {
            SslProvider.ProtocolSslContextSpec spec = forServer
                    ? createSslContextSpec(ssl, null)
                    : getClientContextBuilder(ssl);
            sslContextSpec.sslContext(spec);
        }
    }

    public static HttpServer apply(
            HttpServer server,
            SslProperties ssl,
            @Nullable SslStoreProvider sslStoreProvider) {
        if (ssl.isEnabled()) {
            return server
                    .secure(spec -> spec.sslContext(createSslContextSpec(ssl, sslStoreProvider)));
        }
        return server;
    }

    private static SslProvider.ProtocolSslContextSpec getClientContextBuilder(SslProperties ssl) {
        return DefaultSslContextSpec.forClient()
                .configure(builder -> {
                    builder.keyManager(getKeyManagerFactory(ssl, null))
                            .trustManager(getTrustManagerFactory(ssl, null));
                    if (ssl.getEnabledProtocols() != null) {
                        builder.protocols(ssl.getEnabledProtocols());
                    }
                    if (ssl.getCiphers() != null) {
                        builder.ciphers(Arrays.asList(ssl.getCiphers()));
                    }
                });
    }

    private static AbstractProtocolSslContextSpec<?> createSslContextSpec(
            SslProperties ssl,
            SslStoreProvider sslStoreProvider) {
        return Http11SslContextSpec.forServer(getKeyManagerFactory(ssl, sslStoreProvider))
                .configure(builder -> {
                    builder.trustManager(getTrustManagerFactory(ssl, sslStoreProvider));
                    if (ssl.getEnabledProtocols() != null) {
                        builder.protocols(ssl.getEnabledProtocols());
                    }
                    if (ssl.getCiphers() != null) {
                        builder.ciphers(Arrays.asList(ssl.getCiphers()));
                    }
                    if (ssl.getClientAuth() != null) {
                        builder.clientAuth(ssl.getClientAuth());
                    }
                });
    }

    private static KeyManagerFactory getKeyManagerFactory(
            SslProperties ssl,
            @Nullable SslStoreProvider sslStoreProvider) {
        try {
            KeyStore keyStore = getKeyStore(ssl, sslStoreProvider);
            SslConfigurationValidator.validateKeyAlias(keyStore, ssl.getKeyAlias());
            KeyManagerFactory keyManagerFactory = ssl.getKeyAlias() == null
                    ? KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
                    : new ConfigurableAliasKeyManagerFactory(
                            ssl.getKeyAlias(),
                            KeyManagerFactory.getDefaultAlgorithm());
            char[] keyPassword = ssl.getKeyPassword() == null
                    ? null
                    : ssl.getKeyPassword()
                            .toCharArray();
            if (keyPassword == null && ssl.getKeyStorePassword() != null) {
                keyPassword = ssl.getKeyStorePassword()
                        .toCharArray();
            }
            keyManagerFactory.init(keyStore, keyPassword);
            return keyManagerFactory;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get the key manager factory", e);
        }
    }

    private static KeyStore getKeyStore(
            SslProperties ssl,
            @Nullable SslStoreProvider sslStoreProvider) throws Exception {
        if (sslStoreProvider != null) {
            return sslStoreProvider.getKeyStore();
        }
        return loadStore(ssl.getKeyStoreType(),
                ssl.getKeyStoreProvider(),
                ssl.getKeyStore(),
                ssl.getKeyStorePassword());
    }

    private static TrustManagerFactory getTrustManagerFactory(
            SslProperties ssl,
            SslStoreProvider sslStoreProvider) {
        try {
            KeyStore store = getTrustStore(ssl, sslStoreProvider);
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(store);
            return trustManagerFactory;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static KeyStore getTrustStore(
            SslProperties ssl,
            @Nullable SslStoreProvider sslStoreProvider) {
        if (sslStoreProvider != null) {
            try {
                return sslStoreProvider.getTrustStore();
            } catch (Exception e) {
                throw new RuntimeException("Failed to get the instance of the trust store", e);
            }
        }
        String trustStore = ssl.getTrustStore();
        if (trustStore == null) {
            return null;
        }
        return loadStore(ssl.getTrustStoreType(),
                ssl.getTrustStoreProvider(),
                trustStore,
                ssl.getTrustStorePassword());
    }

    @SneakyThrows
    private static KeyStore loadStore(
            String type,
            @Nullable String provider,
            String resource,
            @Nullable String password) {
        KeyStore store = provider == null
                ? KeyStore.getInstance(type)
                : KeyStore.getInstance(type, provider);
        try {
            URL url = ResourceUtils.getURL(resource);
            try (InputStream stream = url.openStream()) {
                store.load(stream,
                        password == null
                                ? null
                                : password.toCharArray());
            }
            return store;
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Failed to load key store from the resource: "
                            + resource,
                    ex);
        }
    }

}

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

import im.turms.server.common.infra.property.env.common.SslProperties;
import lombok.SneakyThrows;
import org.springframework.boot.web.server.SslConfigurationValidator;
import org.springframework.boot.web.server.SslStoreProvider;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.util.ResourceUtils;
import reactor.netty.http.Http11SslContextSpec;
import reactor.netty.http.server.HttpServer;
import reactor.netty.tcp.AbstractProtocolSslContextSpec;
import reactor.netty.tcp.DefaultSslContextSpec;
import reactor.netty.tcp.SslProvider;

import javax.annotation.Nullable;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.util.Arrays;

/**
 * @author James Chen
 */
public final class SslUtil {

    private SslUtil() {
    }

    public static void configureSslContextSpec(SslProvider.SslContextSpec sslContextSpec,
                                               SslProperties ssl,
                                               boolean forServer) {
        if (ssl.isEnabled()) {
            SslProvider.ProtocolSslContextSpec spec = forServer
                    ? createSslContextSpec(ssl, null)
                    : getClientContextBuilder(ssl);
            sslContextSpec.sslContext(spec);
        }
    }

    public static HttpServer apply(HttpServer server,
                                   SslProperties ssl,
                                   @Nullable SslStoreProvider sslStoreProvider) {
        if (ssl.isEnabled()) {
            return server.secure(spec -> spec.sslContext(createSslContextSpec(ssl, sslStoreProvider)));
        }
        return server;
    }

    private static SslProvider.ProtocolSslContextSpec getClientContextBuilder(SslProperties ssl) {
        return DefaultSslContextSpec
                .forClient()
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

    private static AbstractProtocolSslContextSpec<?> createSslContextSpec(SslProperties ssl, SslStoreProvider sslStoreProvider) {
        return Http11SslContextSpec
                .forServer(getKeyManagerFactory(ssl, sslStoreProvider))
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

    private static KeyManagerFactory getKeyManagerFactory(SslProperties ssl, @Nullable SslStoreProvider sslStoreProvider) {
        try {
            KeyStore keyStore = getKeyStore(ssl, sslStoreProvider);
            SslConfigurationValidator.validateKeyAlias(keyStore, ssl.getKeyAlias());
            KeyManagerFactory keyManagerFactory = ssl.getKeyAlias() == null
                    ? KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
                    : new ConfigurableAliasKeyManagerFactory(ssl.getKeyAlias(),
                    KeyManagerFactory.getDefaultAlgorithm());
            char[] keyPassword = ssl.getKeyPassword() == null ? null : ssl.getKeyPassword().toCharArray();
            if (keyPassword == null && ssl.getKeyStorePassword() != null) {
                keyPassword = ssl.getKeyStorePassword().toCharArray();
            }
            keyManagerFactory.init(keyStore, keyPassword);
            return keyManagerFactory;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    private static KeyStore getKeyStore(SslProperties ssl,
                                        @Nullable SslStoreProvider sslStoreProvider) throws Exception {
        if (sslStoreProvider != null) {
            return sslStoreProvider.getKeyStore();
        }
        return loadKeyStore(ssl.getKeyStoreType(), ssl.getKeyStoreProvider(), ssl.getKeyStore(),
                ssl.getKeyStorePassword());
    }

    private static TrustManagerFactory getTrustManagerFactory(SslProperties ssl, SslStoreProvider sslStoreProvider) {
        try {
            KeyStore store = getTrustStore(ssl, sslStoreProvider);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(store);
            return trustManagerFactory;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    @SneakyThrows
    private static KeyStore getTrustStore(SslProperties ssl, @Nullable SslStoreProvider sslStoreProvider) {
        if (sslStoreProvider != null) {
            return sslStoreProvider.getTrustStore();
        }
        return loadTrustStore(ssl.getTrustStoreType(), ssl.getTrustStoreProvider(), ssl.getTrustStore(),
                ssl.getTrustStorePassword());
    }

    private static KeyStore loadKeyStore(String type, String provider, String resource, String password) {

        return loadStore(type, provider, resource, password);
    }

    private static KeyStore loadTrustStore(String type, String provider, String resource, String password) {
        if (resource == null) {
            return null;
        }
        return loadStore(type, provider, resource, password);
    }

    @SneakyThrows
    private static KeyStore loadStore(String type, String provider, String resource, String password) {
        type = type == null ? "JKS" : type;
        KeyStore store = provider == null
                ? KeyStore.getInstance(type)
                : KeyStore.getInstance(type, provider);
        try {
            URL url = ResourceUtils.getURL(resource);
            try (InputStream stream = url.openStream()) {
                store.load(stream, password == null ? null : password.toCharArray());
            }
            return store;
        } catch (Exception ex) {
            throw new WebServerException("Could not load key store '" + resource + "'", ex);
        }
    }

}

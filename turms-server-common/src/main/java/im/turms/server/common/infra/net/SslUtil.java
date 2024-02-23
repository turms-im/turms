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

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.security.KeyStore;
import java.util.Arrays;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import jakarta.annotation.Nullable;

import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import reactor.netty.http.Http11SslContextSpec;
import reactor.netty.http.Http2SslContextSpec;
import reactor.netty.http.server.HttpServer;
import reactor.netty.tcp.AbstractProtocolSslContextSpec;
import reactor.netty.tcp.SslProvider;
import reactor.netty.tcp.TcpSslContextSpec;

import im.turms.server.common.infra.io.ProcessUtil;
import im.turms.server.common.infra.property.env.common.SslProperties;

/**
 * @author James Chen
 */
public final class SslUtil {

    private static final String KEYTOOL = Path.of(System.getProperty("java.home")
            + File.separator
            + "bin"
            + File.separator
            + "keytool")
            .toString();

    private SslUtil() {
    }

    public static Mono<Void> generateKeyStore(
            String keystorePath,
            String keyAlgorithm,
            String alias,
            String storePassword,
            int keySize,
            int validity,
            String organizationName,
            String extension) {
        return ProcessUtil.run(KEYTOOL,
                "-genkeypair",
                "-dname",
                "o="
                        + organizationName,
                "-keyalg",
                keyAlgorithm,
                "-keysize",
                String.valueOf(keySize),
                "-alias",
                alias,
                "-validity",
                String.valueOf(validity),
                "-keystore",
                keystorePath,
                "-storepass",
                storePassword,
                "-ext",
                extension);
    }

    public static Mono<Void> generateTrustStore(
            String truststorePath,
            String certificatePath,
            String keyPassword,
            String storePassword) {
        return ProcessUtil.run(KEYTOOL,
                "-import",
                "-trustcacerts",
                "-file",
                certificatePath,
                "-keypass",
                keyPassword,
                "-storepass",
                storePassword,
                "-keystore",
                truststorePath,
                "-noprompt");
    }

    public static Mono<Void> generateCertificate(
            String certificatePath,
            String keystorePath,
            String alias,
            String storePassword) {
        return ProcessUtil.run(KEYTOOL,
                "-exportcert",
                "-keystore",
                keystorePath,
                "-alias",
                alias,
                "-storepass",
                storePassword,
                "-rfc",
                "-file",
                certificatePath);
    }

    public static void configureSslContextSpec(
            SslProvider.SslContextSpec sslContextSpec,
            SslContextSpecType sslContextSpecType,
            SslProperties ssl,
            boolean forServer) {
        if (!ssl.isEnabled()) {
            return;
        }
        SslProvider.ProtocolSslContextSpec spec = forServer
                ? createSslContextSpec(ssl, sslContextSpecType)
                : getClientContextBuilder(ssl, sslContextSpecType);
        sslContextSpec.sslContext(spec);
    }

    public static HttpServer apply(HttpServer server, SslProperties ssl, boolean isHttp2) {
        if (ssl.isEnabled()) {
            return server.secure(spec -> spec.sslContext(createSslContextSpec(ssl,
                    isHttp2
                            ? SslContextSpecType.HTTP2
                            : SslContextSpecType.HTTP11)));
        }
        return server;
    }

    private static SslProvider.ProtocolSslContextSpec getClientContextBuilder(
            SslProperties ssl,
            SslContextSpecType sslContextSpecType) {
        AbstractProtocolSslContextSpec<?> sslContextSpec = switch (sslContextSpecType) {
            case TCP -> TcpSslContextSpec.forClient();
            case HTTP11 -> Http11SslContextSpec.forClient();
            case HTTP2 -> Http2SslContextSpec.forClient();
        };
        return sslContextSpec.configure(builder -> {
            String keyStore = ssl.getKeyStore();
            if (keyStore != null) {
                builder.keyManager(getKeyManagerFactory(ssl));
            }
            String trustStore = ssl.getTrustStore();
            if (trustStore != null) {
                builder.trustManager(getTrustManagerFactory(ssl));
            }
            String[] enabledProtocols = ssl.getEnabledProtocols();
            if (enabledProtocols != null) {
                builder.protocols(enabledProtocols);
            }
            String[] ciphers = ssl.getCiphers();
            if (ciphers != null) {
                builder.ciphers(Arrays.asList(ciphers));
            }
        });
    }

    private static AbstractProtocolSslContextSpec<?> createSslContextSpec(
            SslProperties ssl,
            SslContextSpecType sslContextSpecType) {
        AbstractProtocolSslContextSpec<?> sslContextSpec = switch (sslContextSpecType) {
            case TCP -> TcpSslContextSpec.forServer(getKeyManagerFactory(ssl));
            case HTTP11 -> Http11SslContextSpec.forServer(getKeyManagerFactory(ssl));
            case HTTP2 -> Http2SslContextSpec.forServer(getKeyManagerFactory(ssl));
        };
        return sslContextSpec.configure(builder -> {
            builder.trustManager(getTrustManagerFactory(ssl));
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

    private static KeyManagerFactory getKeyManagerFactory(SslProperties ssl) {
        String password = ssl.getKeyPassword();
        String keyStorePassword = ssl.getKeyStorePassword();
        char[] keyPassword;
        if (password == null) {
            if (keyStorePassword == null) {
                throw new IllegalArgumentException("The key password is required");
            }
            keyPassword = keyStorePassword.toCharArray();
        } else {
            keyPassword = password.toCharArray();
        }
        try {
            KeyStore keyStore = getKeyStore(ssl);
            String alias = ssl.getKeyAlias();
            KeyManagerFactory keyManagerFactory;
            if (StringUtils.hasLength(alias)) {
                if (!keyStore.containsAlias(alias)) {
                    throw new RuntimeException(
                            "The keystore does not contain the alias: \""
                                    + alias
                                    + "\"");
                }
                keyManagerFactory = new ConfigurableAliasKeyManagerFactory(
                        alias,
                        KeyManagerFactory.getDefaultAlgorithm());
            } else {
                keyManagerFactory =
                        KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            }
            keyManagerFactory.init(keyStore, keyPassword);
            return keyManagerFactory;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get the key manager factory", e);
        }
    }

    private static KeyStore getKeyStore(SslProperties ssl) {
        return loadStore(ssl.getKeyStoreType(),
                ssl.getKeyStoreProvider(),
                ssl.getKeyStore(),
                ssl.getKeyStorePassword());
    }

    private static TrustManagerFactory getTrustManagerFactory(SslProperties ssl) {
        try {
            KeyStore store = getTrustStore(ssl);
            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(store);
            return trustManagerFactory;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get the trust manager factory", e);
        }
    }

    @Nullable
    private static KeyStore getTrustStore(SslProperties ssl) {
        String trustStore = ssl.getTrustStore();
        if (trustStore == null) {
            return null;
        }
        return loadStore(ssl.getTrustStoreType(),
                ssl.getTrustStoreProvider(),
                trustStore,
                ssl.getTrustStorePassword());
    }

    private static KeyStore loadStore(
            @Nullable String type,
            @Nullable String provider,
            String resource,
            @Nullable String password) {
        KeyStore store;
        try {
            if (type == null) {
                type = resource.endsWith(".jks")
                        ? "JKS"
                        : "PKCS12";
            }
            store = provider == null
                    ? KeyStore.getInstance(type)
                    : KeyStore.getInstance(type, provider);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load key store", e);
        }
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
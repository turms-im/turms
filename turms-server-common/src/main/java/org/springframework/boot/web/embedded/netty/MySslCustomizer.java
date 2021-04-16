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

package org.springframework.boot.web.embedded.netty;

import org.springframework.boot.web.server.Ssl;
import reactor.netty.tcp.DefaultSslContextSpec;

import java.util.Arrays;

import static reactor.netty.tcp.SslProvider.ProtocolSslContextSpec;

/**
 * @author James Chen
 */
@SuppressWarnings("deprecation")
public class MySslCustomizer extends SslServerCustomizer {

    private final boolean forServer;
    private final Ssl ssl;

    public MySslCustomizer(Ssl ssl, boolean forServer) {
        super(ssl, null, null);
        this.ssl = ssl;
        this.forServer = forServer;
    }

    public ProtocolSslContextSpec getSpec() {
        return forServer
                ? super.createSslContextSpec()
                : getClientContextBuilder();
    }

    private ProtocolSslContextSpec getClientContextBuilder() {
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

}
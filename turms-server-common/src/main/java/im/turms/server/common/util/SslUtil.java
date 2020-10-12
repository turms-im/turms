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

package im.turms.server.common.util;

import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.boot.web.embedded.netty.SslServerCustomizer;
import org.springframework.boot.web.server.Ssl;
import reactor.netty.tcp.SslProvider;

import javax.annotation.Nullable;

/**
 * @author James Chen
 */
public class SslUtil {

    private SslUtil() {
    }

    @Nullable
    public static SslContextBuilder getSslContextBuilder(Ssl ssl) {
        if (ssl.isEnabled()) {
            MySslServerCustomizer sslServerCustomizer = new MySslServerCustomizer(ssl);
            return sslServerCustomizer.getContextBuilder();
        }
        return null;
    }

    public static void configureSslContextSpec(SslProvider.SslContextSpec sslContextSpec, Ssl ssl) {
        SslContextBuilder builder = getSslContextBuilder(ssl);
        if (builder != null) {
            sslContextSpec.sslContext(builder);
        }
    }

    private static class MySslServerCustomizer extends SslServerCustomizer {

        public MySslServerCustomizer(Ssl ssl) {
            super(ssl, null, null);
        }

        @Override
        public SslContextBuilder getContextBuilder() {
            return super.getContextBuilder();
        }

    }

}

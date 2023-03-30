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

package im.turms.server.common.infra.metrics;

import java.net.SocketAddress;
import java.time.Duration;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Timer;
import reactor.netty.channel.ChannelMetricsRecorder;

import static reactor.netty.Metrics.ADDRESS_RESOLVER;
import static reactor.netty.Metrics.CONNECT_TIME;
import static reactor.netty.Metrics.DATA_RECEIVED;
import static reactor.netty.Metrics.DATA_SENT;
import static reactor.netty.Metrics.ERRORS;
import static reactor.netty.Metrics.REGISTRY;
import static reactor.netty.Metrics.TLS_HANDSHAKE_TIME;
import static reactor.netty.Metrics.URI;

/**
 * @author James Chen
 * @see reactor.netty.channel.MicrometerChannelMetricsRecorder
 */
public class TurmsMicrometerChannelMetricsRecorder implements ChannelMetricsRecorder {

    private final DistributionSummary dataReceived;
    private final DistributionSummary dataSent;

    private final Counter errorCount;

    private final Timer connectTime;
    private final Timer tlsHandshakeTime;
    private final Timer addressResolverTime;

    public TurmsMicrometerChannelMetricsRecorder(String name, String protocol) {
        dataReceived = DistributionSummary.builder(name + DATA_RECEIVED)
                .baseUnit("bytes")
                .description("Amount of the data received, in bytes")
                .tag(URI, protocol)
                .register(REGISTRY);

        dataSent = DistributionSummary.builder(name + DATA_SENT)
                .baseUnit("bytes")
                .description("Amount of the data sent, in bytes")
                .tag(URI, protocol)
                .register(REGISTRY);

        errorCount = Counter.builder(name + ERRORS)
                .description("Number of errors that occurred")
                .tag(URI, protocol)
                .register(REGISTRY);

        connectTime = Timer.builder(name + CONNECT_TIME)
                .description("Time spent for connecting to the remote address")
                .register(REGISTRY);

        tlsHandshakeTime = Timer.builder(name + TLS_HANDSHAKE_TIME)
                .description("Time spent for TLS handshake")
                .register(REGISTRY);

        addressResolverTime = Timer.builder(name + ADDRESS_RESOLVER)
                .description("Time spent for resolving the address")
                .register(REGISTRY);
    }

    @Override
    public void recordDataReceived(SocketAddress remoteAddress, long bytes) {
        dataReceived.record(bytes);
    }

    @Override
    public void recordDataSent(SocketAddress remoteAddress, long bytes) {
        dataSent.record(bytes);
    }

    @Override
    public void incrementErrorsCount(SocketAddress remoteAddress) {
        errorCount.increment();
    }

    @Override
    public void recordTlsHandshakeTime(SocketAddress remoteAddress, Duration time, String status) {
        tlsHandshakeTime.record(time);
    }

    @Override
    public void recordConnectTime(SocketAddress remoteAddress, Duration time, String status) {
        connectTime.record(time);
    }

    @Override
    public void recordResolveAddressTime(
            SocketAddress remoteAddress,
            Duration time,
            String status) {
        addressResolverTime.record(time);
    }

}
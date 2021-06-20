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

package im.turms.server.common.client;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.user.CreateSessionRequest;
import im.turms.common.model.dto.request.user.DeleteSessionRequest;
import im.turms.common.util.RandomUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.resources.LoopResources;
import reactor.netty.tcp.TcpClient;

import javax.annotation.Nullable;
import java.util.List;

import static im.turms.server.common.util.ProtoUtil.getDirectByteBuffer;

/**
 * @author James Chen
 */
public class TurmsTcpClient extends TurmsClient {

    private Connection connection;

    @Override
    public Mono<Void> connect(String host, int port, LoopResources loopResources) {
        return TcpClient.newConnection()
                .host(host)
                .port(port)
                .runOn(loopResources)
                .connect()
                .doOnNext(conn -> {
                    // Inbound
                    conn.addHandlerLast("protobufFrameDecoder", new ProtobufVarint32FrameDecoder());
                    conn.addHandlerLast("turmsProtobufDecoder", new TurmsProtobufDecoder());
                    // Outbound
                    conn.addHandlerFirst("turmsProtobufEncoder", new TurmsProtobufEncoder());
                    conn.addHandlerFirst("protobufFrameEncoder", new ProtobufVarint32LengthFieldPrepender());

                    conn.inbound()
                            .receiveObject()
                            .cast(TurmsNotification.class)
                            .doOnNext(this::handleResponse)
                            .subscribe();

                    connection = conn;
                })
                .then();
    }

    @Override
    public Mono<TurmsNotification> login(long userId, DeviceType deviceType, @Nullable String password) {
        return sendRequest(TurmsRequest.newBuilder()
                .setCreateSessionRequest(CreateSessionRequest.newBuilder()
                        .setUserId(userId)
                        .setDeviceType(deviceType)
                        .setPassword(password)
                        .build()));
    }

    @Override
    public Mono<Void> logout() {
        if (connection == null) {
            return Mono.empty();
        }
        if (connection.isDisposed()) {
            return Mono.empty();
        }
        return sendRequest(TurmsRequest.newBuilder()
                .setDeleteSessionRequest(DeleteSessionRequest.newBuilder()
                        .build()))
                .then();
    }

    @Override
    public Mono<TurmsNotification> sendRequest(TurmsRequest.Builder requestBuilder) {
        if (connection == null) {
            return Mono.error(new IllegalStateException("Connection is null"));
        }
        if (connection.isDisposed()) {
            return Mono.error(new IllegalStateException("Connection is closed"));
        }
        if (!requestBuilder.hasRequestId()) {
            requestBuilder.setRequestId(RandomUtil.nextPositiveLong());
        }
        TurmsRequest request = requestBuilder.build();
        return connection.outbound()
                .sendObject(request)
                .then()
                .then(Mono.defer(() -> waitForResponse(request)));
    }

    private static class TurmsProtobufDecoder extends ByteToMessageDecoder {
        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            TurmsNotification notification = TurmsNotification.parseFrom(in.nioBuffer());
            in.skipBytes(in.readableBytes());
            out.add(notification);
        }
    }

    @ChannelHandler.Sharable
    private static class TurmsProtobufEncoder extends MessageToMessageEncoder<MessageLiteOrBuilder> {
        @Override
        protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder messageLiteOrBuilder, List<Object> out) {
            MessageLite message = messageLiteOrBuilder instanceof MessageLite.Builder builder
                    ? builder.build()
                    : (MessageLite) messageLiteOrBuilder;
            out.add(getDirectByteBuffer(message));
        }
    }

}

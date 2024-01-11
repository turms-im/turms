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

package im.turms.gateway.infra.ldap.handler;

import java.util.List;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.jctools.maps.NonBlockingHashMapLong;
import reactor.core.publisher.Sinks;

import im.turms.gateway.infra.ldap.PendingLdapRequestContext;
import im.turms.gateway.infra.ldap.asn1.BerBuffer;
import im.turms.gateway.infra.ldap.element.common.LdapMessage;
import im.turms.gateway.infra.ldap.element.common.control.Control;
import im.turms.gateway.infra.ldap.element.operation.ProtocolOperation;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

/**
 * @author James Chen
 */
public class LdapMessageDecoder extends ByteToMessageDecoder {

    public static final Logger LOGGER = LoggerFactory.getLogger(LdapMessageDecoder.class);

    private final NonBlockingHashMapLong<PendingLdapRequestContext<? extends ProtocolOperation<?>>> messageIdToRequestContext;

    public LdapMessageDecoder(
            NonBlockingHashMapLong<PendingLdapRequestContext<? extends ProtocolOperation<?>>> messageIdToRequestContext) {
        this.messageIdToRequestContext = messageIdToRequestContext;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        BerBuffer buffer = new BerBuffer(in);
        LdapMessage<?> message;
        do {
            in.markReaderIndex();
            DecodeResult result = decodeMessage(buffer);
            message = result.message;
            if (message != null) {
                out.add(message);
            }
            if (result.noEnoughData) {
                in.resetReaderIndex();
                return;
            }
        } while (true);
    }

    private DecodeResult decodeMessage(BerBuffer buffer) {
        if (!buffer.isReadable()) {
            return DecodeResult.NO_ENOUGH_DATA;
        }
        buffer.skipTag();

        int length = buffer.tryReadLengthIfReadable();
        if (length == -1 || !buffer.isReadable(length)) {
            return DecodeResult.NO_ENOUGH_DATA;
        }

        int valueStartIndex = buffer.readerIndex();

        int messageId = buffer.readInteger();

        PendingLdapRequestContext<? extends ProtocolOperation<?>> requestContext =
                messageIdToRequestContext.get(messageId);
        if (requestContext == null) {
            LOGGER.warn("No request found for the message ID: {}", messageId);
            buffer.skipBytes(length - (buffer.readerIndex() - valueStartIndex));
            return DecodeResult.HAS_DATA;
        }
        ProtocolOperation operation = requestContext.getResponseDecoder()
                .decode(buffer);
        List<Control> controls = Control.decode(buffer);

        if (operation.isComplete()) {
            Sinks.One sink = requestContext.getSink();
            sink.tryEmitValue(operation);
            return new DecodeResult(new LdapMessage<>(messageId, operation, controls), false);
        } else {
            requestContext.setResponseDecoder(operation);
            return DecodeResult.HAS_DATA;
        }
    }

    private record DecodeResult(
            @Nullable LdapMessage<?> message,
            boolean noEnoughData
    ) {
        static final DecodeResult NO_ENOUGH_DATA = new DecodeResult(null, true);
        static final DecodeResult HAS_DATA = new DecodeResult(null, false);
    }
}
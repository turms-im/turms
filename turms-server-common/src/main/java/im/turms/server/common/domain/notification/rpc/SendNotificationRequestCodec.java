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

package im.turms.server.common.domain.notification.rpc;

import im.turms.server.common.infra.cluster.service.codec.codec.CodecId;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecUtil;
import im.turms.server.common.infra.cluster.service.rpc.codec.RpcRequestCodec;
import im.turms.server.common.infra.collection.MapUtil;
import io.netty.buffer.ByteBuf;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import java.util.Set;

/**
 * @author James Chen
 */
public class SendNotificationRequestCodec extends RpcRequestCodec<SendNotificationRequest> {

    @Override
    public CodecId getCodecId() {
        return CodecId.RPC_SEND_NOTIFICATION;
    }

    @Override
    public void writeRequestData(ByteBuf out, SendNotificationRequest data) {
        int recipientCount = data.getRecipientIds().size();
        if (recipientCount == 0) {
            throw new IllegalArgumentException("The number of recipients must be greater than 0");
        }
        CodecUtil.writeVarint32(out, recipientCount);
        for (Long id : data.getRecipientIds()) {
            out.writeLong(id);
        }
    }

    @Override
    public SendNotificationRequest readRequestData(ByteBuf in) {
        int recipientCount = CodecUtil.readVarint32(in);
        Set<Long> recipientIds = UnifiedSet.newSet(MapUtil.getCapability(recipientCount));
        for (int i = 0; i < recipientCount; i++) {
            recipientIds.add(in.readLong());
        }
        ByteBuf notificationBuffer = in.readRetainedSlice(in.readableBytes());
        return new SendNotificationRequest(notificationBuffer, recipientIds);
    }

    @Override
    public int initialCapacityForRequest(SendNotificationRequest data) {
        int size = data.getRecipientIds().size();
        return CodecUtil.computeVarint32Size(size) + size * Long.BYTES;
    }

    @Override
    public ByteBuf byteBufToComposite(SendNotificationRequest data) {
        return data.getNotificationBuffer();
    }

}

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

package im.turms.server.common.rpc.serializer.request;

import im.turms.server.common.cluster.service.serialization.serializer.SerializerId;
import im.turms.server.common.rpc.request.SendNotificationRequest;
import im.turms.server.common.util.MapUtil;
import io.netty.buffer.ByteBuf;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import java.util.Set;

/**
 * @author James Chen
 */
public class SendNotificationRequestSerializer extends RpcCallableSerializer<SendNotificationRequest> {

    @Override
    public SerializerId getSerializerId() {
        return SerializerId.RPC_SEND_NOTIFICATION;
    }

    @Override
    public void writeRequestData(ByteBuf output, SendNotificationRequest data) {
        short recipientsNumber = (short) data.getRecipientIds().size();
        if (recipientsNumber == 0) {
            throw new IllegalArgumentException("The number of recipients must be greater than 0");
        }
        output.writeShort(recipientsNumber);
        for (Long id : data.getRecipientIds()) {
            output.writeLong(id);
        }
    }

    @Override
    public SendNotificationRequest readRequestData(ByteBuf input) {
        int recipientsNumber = input.readShort();
        Set<Long> recipientIds = UnifiedSet.newSet(MapUtil.getCapability(recipientsNumber));
        for (int i = 0; i < recipientsNumber; i++) {
            recipientIds.add(input.readLong());
        }
        ByteBuf notificationBuffer = input.slice();
        return new SendNotificationRequest(notificationBuffer, recipientIds);
    }

    @Override
    public int initialCapacityForRequest(SendNotificationRequest data) {
        int size = data.getRecipientIds().size();
        return Short.BYTES + size * Long.BYTES;
    }

    @Override
    public ByteBuf byteBufToComposite(SendNotificationRequest data) {
        return data.getNotificationBuffer();
    }

}

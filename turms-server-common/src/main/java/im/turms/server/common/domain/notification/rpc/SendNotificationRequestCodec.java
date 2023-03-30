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

import java.util.Collections;
import java.util.Set;

import io.netty.buffer.ByteBuf;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.domain.common.util.DeviceTypeUtil;
import im.turms.server.common.domain.session.bo.UserSessionId;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecId;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamInput;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamOutput;
import im.turms.server.common.infra.cluster.service.rpc.codec.RpcRequestCodec;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.io.Stream;

/**
 * @author James Chen
 */
public class SendNotificationRequestCodec extends RpcRequestCodec<SendNotificationRequest> {

    @Override
    public CodecId getCodecId() {
        return CodecId.RPC_SEND_NOTIFICATION;
    }

    @Override
    public void writeRequestData(CodecStreamOutput out, SendNotificationRequest data) {
        // write "recipientIds"
        Set<Long> recipientIds = data.getRecipientIds();
        if (recipientIds.isEmpty()) {
            throw new IllegalArgumentException("The number of recipients must be greater than 0");
        }
        out.writeLongs(recipientIds);
        // write "excludedUserSessionIds"
        Set<UserSessionId> excludedUserSessionIds = data.getExcludedUserSessionIds();
        out.writeVarint32(excludedUserSessionIds.size());
        for (UserSessionId id : excludedUserSessionIds) {
            out.writeLong(id.userId());
            out.writeByte(DeviceTypeUtil.deviceType2Byte(id.deviceType()));
        }
        // write "excludedDeviceType"
        DeviceType excludedDeviceType = data.getExcludedDeviceType();
        if (excludedDeviceType == null) {
            out.writeByte(-1);
        } else {
            out.writeByte(DeviceTypeUtil.deviceType2Byte(excludedDeviceType));
        }
    }

    @Override
    public SendNotificationRequest readRequestData(CodecStreamInput in) {
        // read "recipientIds"
        Set<Long> recipientIds = in.readLongSet();
        // read "excludedUserSessionIds"
        int excludedUserSessionIdCount = in.readVarint32();
        Set<UserSessionId> excludedUserSessionIds;
        if (excludedUserSessionIdCount > 0) {
            excludedUserSessionIds =
                    CollectionUtil.newSetWithExpectedSize(excludedUserSessionIdCount);
            for (int i = 0; i < excludedUserSessionIdCount; i++) {
                long userId = in.readLong();
                DeviceType deviceType = DeviceTypeUtil.byte2DeviceType(in.readByte());
                excludedUserSessionIds.add(new UserSessionId(userId, deviceType));
            }
        } else {
            excludedUserSessionIds = Collections.emptySet();
        }
        // read "excludedDeviceType"
        byte excludedDeviceTypeByte = in.readByte();
        DeviceType excludedDeviceType = excludedDeviceTypeByte == -1
                ? null
                : DeviceTypeUtil.byte2DeviceType(excludedDeviceTypeByte);

        // read "notificationBuffer"
        ByteBuf notificationBuffer = in.readRetainedSlice(in.readableBytes());
        return new SendNotificationRequest(
                notificationBuffer,
                recipientIds,
                excludedUserSessionIds,
                excludedDeviceType);
    }

    @Override
    public int initialCapacityForRequest(SendNotificationRequest data) {
        int recipientCount = data.getRecipientIds()
                .size();
        int excludedUserSessionIdCount = data.getExcludedUserSessionIds()
                .size();
        return Stream.computeVarint32Size(recipientCount) + recipientCount * Long.BYTES
                + Stream.computeVarint32Size(excludedUserSessionIdCount)
                + excludedUserSessionIdCount * (Long.BYTES + Byte.SIZE) + Byte.BYTES;
    }

    @Override
    public ByteBuf byteBufToComposite(SendNotificationRequest data) {
        return data.getNotificationBuffer();
    }

}

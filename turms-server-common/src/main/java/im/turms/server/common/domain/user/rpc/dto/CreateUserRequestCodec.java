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

package im.turms.server.common.domain.user.rpc.dto;

import java.util.Date;

import im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecId;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamInput;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamOutput;
import im.turms.server.common.infra.cluster.service.rpc.codec.RpcRequestCodec;

/**
 * @author James Chen
 */
public class CreateUserRequestCodec extends RpcRequestCodec<CreateUserRequest> {

    @Override
    public CodecId getCodecId() {
        return CodecId.RPC_CREATE_USER;
    }

    @Override
    protected int initialCapacityForRequest(CreateUserRequest data) {
        return 64;
    }

    @Override
    protected void writeRequestData(CodecStreamOutput output, CreateUserRequest data) {
        Long id = data.getId();
        String rawPassword = data.getRawPassword();
        String name = data.getName();
        String intro = data.getIntro();
        String profilePicture = data.getProfilePicture();
        ProfileAccessStrategy profileAccessStrategy = data.getProfileAccessStrategy();
        Long permissionGroupId = data.getPermissionGroupId();
        Date registrationDate = data.getRegistrationDate();
        Boolean isActive = data.getIsActive();
        if (id != null) {
            output.writeInt(0)
                    .writeLong(id);
        }
        if (rawPassword != null) {
            output.writeInt(1)
                    .writeString(rawPassword);
        }
        if (name != null) {
            output.writeInt(2)
                    .writeString(name);
        }
        if (intro != null) {
            output.writeInt(3)
                    .writeString(intro);
        }
        if (profilePicture != null) {
            output.writeInt(4)
                    .writeString(profilePicture);
        }
        if (profileAccessStrategy != null) {
            output.writeInt(5)
                    .writeInt(profileAccessStrategy.ordinal());
        }
        if (permissionGroupId != null) {
            output.writeInt(6)
                    .writeLong(permissionGroupId);
        }
        if (registrationDate != null) {
            output.writeInt(7)
                    .writeLong(registrationDate.getTime());
        }
        if (isActive != null) {
            output.writeInt(8)
                    .writeBoolean(isActive);
        }
    }

    @Override
    public CreateUserRequest readRequestData(CodecStreamInput input) {
        Long id = null;
        String rawPassword = null;
        String name = null;
        String intro = null;
        String profilePicture = null;
        ProfileAccessStrategy profileAccessStrategy = null;
        Long permissionGroupId = null;
        Date registrationDate = null;
        Boolean isActive = null;
        int tag;
        while (input.isReadable()) {
            tag = input.readInt();
            switch (tag) {
                case 0 -> id = input.readLong();
                case 1 -> rawPassword = input.readString();
                case 2 -> name = input.readString();
                case 3 -> intro = input.readString();
                case 4 -> profilePicture = input.readString();
                case 5 -> profileAccessStrategy = ProfileAccessStrategy.values()[input.readInt()];
                case 6 -> permissionGroupId = input.readLong();
                case 7 -> registrationDate = new Date(input.readLong());
                case 8 -> isActive = input.readBoolean();
                default -> throw new IllegalArgumentException(
                        "Unknown tag: "
                                + tag);
            }
        }
        return new CreateUserRequest(
                id,
                rawPassword,
                name,
                intro,
                profilePicture,
                profileAccessStrategy,
                permissionGroupId,
                registrationDate,
                isActive);
    }

}
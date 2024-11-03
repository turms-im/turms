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

    private static final int TAG_ID = 0;
    private static final int TAG_RAW_PASSWORD = 1;
    private static final int TAG_NAME = 2;
    private static final int TAG_INTRO = 3;
    private static final int TAG_PROFILE_PICTURE = 4;
    private static final int TAG_PROFILE_ACCESS_STRATEGY = 5;
    private static final int TAG_ROLE_ID = 6;
    private static final int TAG_REGISTRATION_DATE = 7;
    private static final int TAG_IS_ACTIVE = 8;

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
        Long roleId = data.getRoleId();
        Date registrationDate = data.getRegistrationDate();
        Boolean isActive = data.getIsActive();
        if (id != null) {
            output.writeByte(TAG_ID)
                    .writeLong(id);
        }
        if (rawPassword != null) {
            output.writeByte(TAG_RAW_PASSWORD)
                    .writeString(rawPassword);
        }
        if (name != null) {
            output.writeByte(TAG_NAME)
                    .writeString(name);
        }
        if (intro != null) {
            output.writeByte(TAG_INTRO)
                    .writeString(intro);
        }
        if (profilePicture != null) {
            output.writeByte(TAG_PROFILE_PICTURE)
                    .writeString(profilePicture);
        }
        if (profileAccessStrategy != null) {
            output.writeByte(TAG_PROFILE_ACCESS_STRATEGY)
                    .writeByte(profileAccessStrategy.getNumber());
        }
        if (roleId != null) {
            output.writeByte(TAG_ROLE_ID)
                    .writeLong(roleId);
        }
        if (registrationDate != null) {
            output.writeByte(TAG_REGISTRATION_DATE)
                    .writeLong(registrationDate.getTime());
        }
        if (isActive != null) {
            output.writeByte(TAG_IS_ACTIVE)
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
        Long roleId = null;
        Date registrationDate = null;
        Boolean isActive = null;
        byte tag;
        while (input.isReadable()) {
            tag = input.readByte();
            switch (tag) {
                case TAG_ID -> id = input.readLong();
                case TAG_RAW_PASSWORD -> rawPassword = input.readString();
                case TAG_NAME -> name = input.readString();
                case TAG_INTRO -> intro = input.readString();
                case TAG_PROFILE_PICTURE -> profilePicture = input.readString();
                case TAG_PROFILE_ACCESS_STRATEGY ->
                    profileAccessStrategy = ProfileAccessStrategy.forNumber(input.readByte());
                case TAG_ROLE_ID -> roleId = input.readLong();
                case TAG_REGISTRATION_DATE -> registrationDate = new Date(input.readLong());
                case TAG_IS_ACTIVE -> isActive = input.readBoolean();
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
                roleId,
                registrationDate,
                isActive);
    }

}
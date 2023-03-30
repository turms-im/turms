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

package im.turms.server.common.infra.fake;

import java.util.Random;
import java.util.Set;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.Message;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.user.UpdateUserOnlineStatusRequest;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.random.RandomUtil;

import static com.google.protobuf.Descriptors.FieldDescriptor;
import static com.google.protobuf.Descriptors.OneofDescriptor;

/**
 * @author James Chen
 */
public final class RandomRequestFactory {

    public static final String CREATE_SESSION_REQUEST_FILED_NAME = TurmsRequest.getDescriptor()
            .findFieldByNumber(TurmsRequest.CREATE_SESSION_REQUEST_FIELD_NUMBER)
            .getName();
    public static final String DELETE_SESSION_REQUEST_FILED_NAME = TurmsRequest.getDescriptor()
            .findFieldByNumber(TurmsRequest.DELETE_SESSION_REQUEST_FIELD_NUMBER)
            .getName();

    private static final Random RANDOM = new Random();
    private static final String REQUEST_TYPE_FILED_NAME = "kind";
    private static final OneofDescriptor REQUEST_TYPE_DESC = TurmsRequest.getDefaultInstance()
            .getDescriptorForType()
            .getRealOneofs()
            .stream()
            .filter(desc -> REQUEST_TYPE_FILED_NAME.equals(desc.getName()))
            .findFirst()
            .get();
    private static final Pair<FieldDescriptor, RandomProtobufGenerator<AbstractMessage>>[] REQUEST_GENERATORS;

    static {
        int count = REQUEST_TYPE_DESC.getFieldCount();
        REQUEST_GENERATORS = new Pair[count];
        TurmsRequest.Builder builder = TurmsRequest.newBuilder();
        for (int i = 0; i < count; i++) {
            FieldDescriptor descriptor = REQUEST_TYPE_DESC.getField(i);
            Message defaultRequestType = builder.newBuilderForField(descriptor)
                    .getDefaultInstanceForType();
            RandomProtobufGenerator<AbstractMessage> generator =
                    new RandomProtobufGenerator<>(RANDOM, defaultRequestType);
            REQUEST_GENERATORS[i] = Pair.of(descriptor, generator);
        }
    }

    private RandomRequestFactory() {
    }

    public static TurmsRequest create(
            Set<String> excludedRequestNames,
            RandomProtobufGenerator.GeneratorOptions options) {
        Pair<FieldDescriptor, RandomProtobufGenerator<AbstractMessage>> entry =
                pickRandomRequestGenerator(excludedRequestNames);
        TurmsRequest.Builder builder = ClientMessagePool.getTurmsRequestBuilder()
                .setRequestId(RandomUtil.nextPositiveLong())
                .setField(entry.first(),
                        entry.second()
                                .generate(options));
        if (builder.hasUpdateUserOnlineStatusRequest()) {
            UpdateUserOnlineStatusRequest updateStatusRequest =
                    builder.getUpdateUserOnlineStatusRequest();
            if (updateStatusRequest.getUserStatus() == UserStatus.OFFLINE) {
                builder.setUpdateUserOnlineStatusRequest(updateStatusRequest.toBuilder()
                        .setUserStatus(UserStatus.INVISIBLE));
            }
        }
        return builder.build();
    }

    private static Pair<FieldDescriptor, RandomProtobufGenerator<AbstractMessage>> pickRandomRequestGenerator(
            Set<String> excludedRequestNames) {
        while (true) {
            Pair<FieldDescriptor, RandomProtobufGenerator<AbstractMessage>> pair =
                    REQUEST_GENERATORS[RANDOM.nextInt(REQUEST_TYPE_DESC.getFieldCount())];
            if (excludedRequestNames == null
                    || excludedRequestNames.isEmpty()
                    || !excludedRequestNames.contains(pair.first()
                            .getName())) {
                return pair;
            }
        }
    }

}

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

package im.turms.server.common.infra.cluster.service.connection.codec;

import im.turms.server.common.infra.cluster.service.codec.codec.CodecId;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamInput;
import im.turms.server.common.infra.cluster.service.connection.request.KeepaliveRequest;
import im.turms.server.common.infra.cluster.service.rpc.codec.RpcRequestCodec;

/**
 * @author James Chen
 */
public class KeepaliveRequestCodec extends RpcRequestCodec<KeepaliveRequest> {

    @Override
    public CodecId getCodecId() {
        return CodecId.RPC_KEEPALIVE;
    }

    @Override
    protected int initialCapacityForRequest(KeepaliveRequest data) {
        return 0;
    }

    @Override
    public KeepaliveRequest readRequestData(CodecStreamInput input) {
        return new KeepaliveRequest();
    }

}

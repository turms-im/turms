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

package im.turms.server.common.domain.session.rpc;

import im.turms.server.common.infra.cluster.service.codec.codec.CodecId;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecUtil;
import im.turms.server.common.infra.cluster.service.rpc.codec.RpcRequestCodec;
import io.netty.buffer.ByteBuf;

/**
 * @author James Chen
 */
public class QueryUserSessionsRequestCodec extends RpcRequestCodec<QueryUserSessionsRequest> {

    @Override
    public CodecId getCodecId() {
        return CodecId.RPC_QUERY_USER_SESSIONS;
    }

    @Override
    public void writeRequestData(ByteBuf output, QueryUserSessionsRequest data) {
        CodecUtil.writeLongs(output, data.getUserIds());
    }

    @Override
    public QueryUserSessionsRequest readRequestData(ByteBuf input) {
        return new QueryUserSessionsRequest(CodecUtil.readLongSet(input));
    }

    @Override
    public int initialCapacityForRequest(QueryUserSessionsRequest data) {
        int size = data.getUserIds().size();
        return CodecUtil.computeVarint32Size(size) + size * Long.BYTES;
    }

}

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

package com.google.protobuf;

import java.nio.ByteBuffer;

/**
 * @author James Chen
 */
public final class CodedInputStreamUtil {

    private CodedInputStreamUtil() {
    }

    public static CodedInputStream newInstance(ByteBuffer buffer) {
        CodedInputStream stream = CodedInputStream.newInstance(buffer, true);
        // we don't need to set a size limit because
        // it should be rejected earlier if it is too large
        stream.discardUnknownFields();
        stream.enableAliasing(true);
        return stream;
    }

    public static CodedInputStream newInstance(Iterable<ByteBuffer> buffers) {
        CodedInputStream stream = CodedInputStream.newInstance(buffers, true);
        // we don't need to set a size limit because
        // it should be rejected earlier if it is too large
        stream.discardUnknownFields();
        stream.enableAliasing(true);
        return stream;
    }

}

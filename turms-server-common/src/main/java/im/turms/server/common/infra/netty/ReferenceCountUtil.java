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

package im.turms.server.common.infra.netty;

import io.netty.util.ReferenceCounted;

/**
 * @author James Chen
 */
public final class ReferenceCountUtil {

    private ReferenceCountUtil() {
    }

    public static void ensureReleased(ReferenceCounted value) {
        int refCnt = value.refCnt();
        if (refCnt > 0) {
            value.release(refCnt);
        }
    }

    public static void ensureReleased(ReferenceCounted[] values) {
        for (ReferenceCounted value : values) {
            ensureReleased(value);
        }
    }

    public static void ensureReleased(ReferenceCounted[] values, int start, int end) {
        for (int i = start; i < end; i++) {
            ensureReleased(values[i]);
        }
    }

    public static void safeEnsureReleased(ReferenceCounted value) {
        try {
            int refCnt = value.refCnt();
            if (refCnt > 0) {
                value.release(refCnt);
            }
        } catch (Exception ignored) {
        }
    }

}
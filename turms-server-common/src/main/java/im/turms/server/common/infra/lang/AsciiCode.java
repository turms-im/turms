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

package im.turms.server.common.infra.lang;

/**
 * <a href="https://www.ascii-code.com">ASCII Code</a>
 *
 * @author James Chen
 */
public final class AsciiCode {

    private AsciiCode() {
    }

    /**
     * 10
     */
    public static final byte NEW_LINE = (byte) '\n';

    /**
     * 32
     */
    public static final byte SPACE = (byte) ' ';

    /**
     * 46
     */
    public static final byte PERIOD = (byte) '.';

    /**
     * 58
     */
    public static final byte COLON = (byte) ':';

}

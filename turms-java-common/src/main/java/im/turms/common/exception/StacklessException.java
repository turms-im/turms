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

package im.turms.common.exception;

/**
 * @author James Chen
 */
public class StacklessException extends RuntimeException {

    public StacklessException() {
        super();
    }

    public StacklessException(String message) {
        super(message);
    }

    public StacklessException(Throwable cause) {
        super(cause);
    }

    public StacklessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Don't call the following parent constructor to avoid filling in stack trace
     * because android sdk 21 doesn't have the constructor.
     *
     * @see java.lang.RuntimeException#RuntimeException(java.lang.String, java.lang.Throwable, boolean, boolean)
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
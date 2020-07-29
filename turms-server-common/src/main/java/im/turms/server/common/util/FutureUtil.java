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

package im.turms.server.common.util;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author James Chen
 */
public class FutureUtil {

    private FutureUtil() {
    }

    public static <T> CompletableFuture<T> race(List<CompletableFuture<T>> futures) {
        CompletableFuture<T> resultFuture = new CompletableFuture<>();
        for (CompletableFuture<T> future : futures) {
            future.whenComplete((value, throwable) -> {
                if (value != null) {
                    resultFuture.complete(value);
                    for (CompletableFuture<T> futureToCancel : futures) {
                        futureToCancel.cancel(false);
                    }
                }
            });
        }
        return resultFuture;
    }

}

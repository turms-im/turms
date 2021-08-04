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

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author James Chen
 */
public final class ReactorUtil {

    private ReactorUtil() {
    }

    public static Mono<Boolean> areAllTrue(Mono<Boolean>... monos) {
        if (monos.length == 0) {
            return Mono.just(false);
        }
        return Flux.merge(monos)
                .collect(CollectorUtil.toList(monos.length))
                .map(results -> {
                    for (boolean result : results) {
                        if (!result) {
                            return false;
                        }
                    }
                    return true;
                });
    }

    public static Mono<Boolean> areAllTrue(@NotNull List<Mono<Boolean>> monos) {
        if (monos.isEmpty()) {
            return Mono.just(false);
        }
        return Flux.merge(monos)
                .collect(CollectorUtil.toList(monos.size()))
                .map(results -> {
                    for (boolean result : results) {
                        if (!result) {
                            return false;
                        }
                    }
                    return true;
                });
    }

    public static Mono<Boolean> atLeastOneTrue(@NotNull List<Mono<Boolean>> monos) {
        if (monos.isEmpty()) {
            return Mono.just(false);
        }
        return Flux.merge(monos)
                .collect(CollectorUtil.toList(monos.size()))
                .map(results -> {
                    for (boolean result : results) {
                        if (result) {
                            return true;
                        }
                    }
                    return false;
                });
    }

}

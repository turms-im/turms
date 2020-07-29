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

import java.util.List;

/**
 * @author James Chen
 */
public class ReactorUtil {

    private ReactorUtil() {
    }

    public static Mono<Boolean> areAllTrue(List<Mono<Boolean>> monos) {
        return Flux.merge(monos)
                .collectList()
                .map(results -> {
                    for (Object result : results) {
                        if (!(boolean) result) {
                            return false;
                        }
                    }
                    return true;
                });
    }

    public static Mono<Boolean> atLeastOneTrue(List<Mono<Boolean>> monos) {
        return Flux.merge(monos)
                .collectList()
                .map(booleans -> {
                    for (Boolean result : booleans) {
                        if (result) {
                            return true;
                        }
                    }
                    return false;
                });
    }

}

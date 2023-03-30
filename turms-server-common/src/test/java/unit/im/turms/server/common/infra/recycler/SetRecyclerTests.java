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

package unit.im.turms.server.common.infra.recycler;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.recycler.SetRecycler;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class SetRecyclerTests {

    @Test
    void test() {
        Recyclable<Set<Integer>> recyclableSet1 = SetRecycler.obtain();
        Set<Integer> set = recyclableSet1.getValue();
        set.addAll(List.of(1, 2, 3));

        recyclableSet1.recycle();
        assertThat(set).isEmpty();

        Recyclable<Set<Integer>> recyclableSet2 = SetRecycler.obtain();
        assertThat(recyclableSet2).isSameAs(recyclableSet1);
        assertThat(recyclableSet2.getValue()).isEmpty();

        Recyclable<Set<Integer>> recyclableSet3 = SetRecycler.obtain();
        assertThat(recyclableSet3).isNotSameAs(recyclableSet2);
        assertThat(recyclableSet3.getValue()).isEmpty();
    }

}

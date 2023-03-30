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

package unit.im.turms.server.common.infra.collection;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.collection.ChunkedArrayList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class ChunkedArrayListTests {

    @Test
    void add() {
        int elementSizePerChunk = 32;
        ChunkedArrayList<Integer> list = new ChunkedArrayList<>(elementSizePerChunk, 1);
        ArrayList<Integer> expectedList = new ArrayList<>(elementSizePerChunk);
        for (int i = 0; i < elementSizePerChunk - 1; i++) {
            expectedList.add(i);
            list.add(i);
        }
        assertThat(list).hasSize(expectedList.size())
                .containsExactlyElementsOf(expectedList);

        expectedList.add(0);
        list.add(0);
        assertThat(list).hasSize(expectedList.size())
                .containsExactlyElementsOf(expectedList);

        for (int i = 0; i < elementSizePerChunk - 1; i++) {
            list.add(i);
            expectedList.add(i);
        }
        assertThat(list).hasSize(expectedList.size())
                .containsExactlyElementsOf(expectedList);

        expectedList.add(0);
        list.add(0);
        assertThat(list).hasSize(expectedList.size())
                .containsExactlyElementsOf(expectedList);
    }

    @Test
    void removeFromEnd() {
        int elementSizePerChunk = 32;
        ChunkedArrayList<Integer> list = new ChunkedArrayList<>(elementSizePerChunk, 1);
        ArrayList<Integer> expectedList = new ArrayList<>(elementSizePerChunk);
        for (int i = 0; i < elementSizePerChunk * 3; i++) {
            expectedList.add(i);
            list.add(i);
        }
        for (int i = elementSizePerChunk * 3 - 2; i >= 0; i--) {
            expectedList.remove(i);
            list.remove(i);
        }
        assertThat(list).hasSize(expectedList.size())
                .containsExactlyElementsOf(expectedList);

        expectedList.remove(0);
        list.remove(0);
        assertThat(list).hasSize(expectedList.size())
                .containsExactlyElementsOf(expectedList);
    }

    @Test
    void removeFromStart() {
        int elementSizePerChunk = 32;
        ChunkedArrayList<Integer> list = new ChunkedArrayList<>(elementSizePerChunk, 1);
        ArrayList<Integer> expectedList = new ArrayList<>(elementSizePerChunk);
        for (int i = 0; i < elementSizePerChunk * 3; i++) {
            expectedList.add(i);
            list.add(i);
        }
        for (int i = 0; i < elementSizePerChunk * 3 - 1; i++) {
            expectedList.remove(0);
            list.remove(0);
        }
        assertThat(list).hasSize(expectedList.size())
                .containsExactlyElementsOf(expectedList);

        expectedList.remove(0);
        list.remove(0);
        assertThat(list).hasSize(expectedList.size())
                .containsExactlyElementsOf(expectedList);
    }

    @Test
    void iterator() {
        int elementSizePerChunk = 32;
        ChunkedArrayList<Object> list = new ChunkedArrayList<>(elementSizePerChunk, 1);
        ArrayList<Integer> expectedList = new ArrayList<>(elementSizePerChunk);
        for (int i = 0; i < elementSizePerChunk * 2 + 1; i++) {
            expectedList.add(i);
            list.add(i);
        }
        Iterator<Object> iterator = list.iterator();
        assertThat(iterator).toIterable()
                .containsExactlyElementsOf(expectedList);
    }
}

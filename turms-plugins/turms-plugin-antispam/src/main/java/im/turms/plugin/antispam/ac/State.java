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

package im.turms.plugin.antispam.ac;

import java.util.Collection;
import jakarta.annotation.Nullable;

import lombok.ToString;
import org.eclipse.collections.api.iterator.MutableCharIterator;
import org.eclipse.collections.api.iterator.MutableIntIterator;
import org.eclipse.collections.api.map.primitive.MutableCharObjectMap;
import org.eclipse.collections.impl.factory.primitive.CharObjectMaps;
import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;

/**
 * @author James Chen
 */
@ToString
public class State {

    final int depth;
    final MutableCharObjectMap<State> success;
    /**
     * points to the state that its prefix has the longest common subsequence with the suffix of the
     * current state
     */
    State failure;
    /**
     * points to the index of {@link AhoCorasickDoubleArrayTrie#words}
     */
    IntHashSet emits;
    int largestTermIndex;
    int indexInDat;

    State(int depth) {
        this.depth = depth;
        success = CharObjectMaps.mutable.empty();
    }

    void addEmit(int termIndex) {
        if (emits == null) {
            emits = new IntHashSet(4);
        }
        emits.add(termIndex);
        if (largestTermIndex < termIndex) {
            largestTermIndex = termIndex;
        }
    }

    int getLargestTermIndex() {
        if (emits == null || emits.isEmpty()) {
            return -1;
        }
        return largestTermIndex;
    }

    void addEmits(IntHashSet emits) {
        MutableIntIterator iterator = emits.intIterator();
        while (iterator.hasNext()) {
            addEmit(iterator.next());
        }
    }

    boolean isTermination() {
        return depth > 0 && emits != null;
    }

    @Nullable
    State findNextState(char code) {
        State nextState = success.get(code);
        if (nextState == null && depth == 0) {
            return this;
        }
        return nextState;
    }

    State addState(char code) {
        State nextState = success.get(code);
        if (nextState == null) {
            nextState = new State(depth + 1);
            success.put(code, nextState);
        }
        return nextState;
    }

    Collection<State> getStates() {
        return success.values();
    }

    MutableCharIterator getTransitions() {
        return success.keySet()
                .charIterator();
    }

}

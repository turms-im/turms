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

package im.turms.plugin.spam.ac;

import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * @author James Chen
 */
public class AhoCorasickDoubleArrayTrie {

    private final int[] fail;
    private final int[][] output;
    private final int[] termLengths;
    private final DoubleArrayTrie dat;

    public AhoCorasickDoubleArrayTrie(List<String> terms) {
        termLengths = new int[terms.size()];
        Trie trie = new Trie();
        int i = 0;
        for (String term : terms) {
            termLengths[i] = term.length();
            trie.addTerm(term, i++);
        }
        dat = new DoubleArrayTrie(trie, terms.size());
        fail = new int[dat.currentMaxCodePoint + 1];
        output = new int[dat.currentMaxCodePoint + 1][];
        constructOutputAndFailure(trie);
    }

    public void findOccurrences(char[] text, HitHandler handler) {
        int position = 1;
        int currentState = 0;
        for (char codePoint : text) {
            currentState = findNextState(currentState, codePoint);
            int[] emits = output[currentState];
            if (emits != null) {
                for (int emit : emits) {
                    handler.hit(position - termLengths[emit], position);
                }
            }
            ++position;
        }
    }

    public boolean matches(char[] text) {
        int currentState = 0;
        for (char codePoint : text) {
            currentState = findNextState(currentState, codePoint);
            int[] emits = output[currentState];
            if (emits != null) {
                return true;
            }
        }
        return false;
    }

    private int findNextState(int currentState, char codePoint) {
        int nextState = transitionWithRoot(currentState, codePoint);
        while (nextState == -1) {
            currentState = fail[currentState];
            nextState = transitionWithRoot(currentState, codePoint);
        }
        return nextState;
    }

    private int transitionWithRoot(int indexInDat, char codePoint) {
        int offset = dat.base[indexInDat];
        int nextState = offset + codePoint + 1;
        if (offset == dat.check[nextState]) {
            return nextState;
        }
        return indexInDat == 0 ? 0 : -1;
    }

    private void constructOutputAndFailure(Trie trie) {
        Queue<State> queue = new ArrayDeque<>(16);

        // Point the failure of states of the depth 1 to the root state
        for (State depthOneState : trie.rootState.getStates()) {
            State rootState = trie.rootState;
            depthOneState.failure = rootState;
            int indexInDat = rootState.indexInDat;
            fail[indexInDat] = indexInDat;
            queue.add(depthOneState);
            constructOutput(depthOneState);
        }

        // Use BFS for states of the depth >1 to set failure to
        // the same code point in the direct children of the failure of the parent state.
        // If not found, refers to the root state.
        State currentState;
        while ((currentState = queue.poll()) != null) {
            for (char transition : currentState.getTransitions()) {
                State targetState = currentState.findNextState(transition);
                queue.add(targetState);
                State traceFailureState = currentState.failure;
                while (traceFailureState.findNextState(transition) == null) {
                    traceFailureState = traceFailureState.failure;
                }
                State newFailureState = traceFailureState.findNextState(transition);
                targetState.failure = newFailureState;
                int indexInDat = newFailureState.indexInDat;
                fail[indexInDat] = indexInDat;
                IntHashSet emits = newFailureState.emits;
                if (emits != null) {
                    targetState.addEmits(emits);
                }
                constructOutput(targetState);
            }
        }
    }

    private void constructOutput(State targetState) {
        IntHashSet emits = targetState.emits;
        if (emits == null || emits.isEmpty()) {
            return;
        }
        output[targetState.indexInDat] = emits.toArray();
    }

}
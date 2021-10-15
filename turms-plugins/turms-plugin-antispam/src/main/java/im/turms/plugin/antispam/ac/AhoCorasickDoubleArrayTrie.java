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

import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author James Chen
 */
public class AhoCorasickDoubleArrayTrie {

    final int[] fail;
    final int[][] output;
    final int[] termLengths;
    final DoubleArrayTrie dat;

    public AhoCorasickDoubleArrayTrie(List<char[]> terms) {
        termLengths = new int[terms.size()];
        Trie trie = new Trie();
        int i = 0;
        for (char[] term : terms) {
            termLengths[i] = term.length;
            trie.addTerm(term, i++);
        }
        dat = new DoubleArrayTrie(trie);
        fail = new int[dat.capacity];
        output = new int[dat.capacity][];
        constructOutputAndFailure(trie);
    }

    public AhoCorasickDoubleArrayTrie(int[] fail, int[][] output, int[] termLengths, DoubleArrayTrie dat) {
        this.fail = fail;
        this.output = output;
        this.termLengths = termLengths;
        this.dat = dat;
    }

    public boolean findOccurrences(char[] text, HitHandler handler) {
        int position = 1;
        int currentState = 0;
        boolean found = false;
        for (char code : text) {
            currentState = findNextState(currentState, code);
            int[] emits = output[currentState];
            if (emits != null) {
                for (int emit : emits) {
                    found = true;
                    handler.hit(position - termLengths[emit], position);
                }
            }
            ++position;
        }
        return found;
    }

    public boolean matches(char[] text) {
        int currentState = 0;
        for (char code : text) {
            currentState = findNextState(currentState, code);
            int[] emits = output[currentState];
            if (emits != null) {
                return true;
            }
        }
        return false;
    }

    private int findNextState(int currentState, char code) {
        int nextState = transitionWithRoot(currentState, code);
        while (nextState == -1) {
            currentState = fail[currentState];
            nextState = transitionWithRoot(currentState, code);
        }
        return nextState;
    }

    private int transitionWithRoot(int indexInDat, char code) {
        int offset = dat.base[indexInDat];
        int nextState = offset + code + 1;
        if (nextState < dat.capacity && offset == dat.check[nextState]) {
            return nextState;
        }
        return indexInDat == 0 ? 0 : -1;
    }

    private void constructOutputAndFailure(Trie trie) {
        Queue<State> queue = new LinkedList<>();

        // Point the failure of states of the depth 1 to the root state
        for (State depthOneState : trie.rootState.getStates()) {
            State rootState = trie.rootState;
            depthOneState.failure = rootState;
            fail[depthOneState.failure.indexInDat] = rootState.indexInDat;
            queue.add(depthOneState);
            constructOutput(depthOneState);
        }

        // Use BFS to set the failure of the states of depth >1 to
        // the same code in the direct children of the failure of the parent state.
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
                fail[targetState.indexInDat] = newFailureState.indexInDat;
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
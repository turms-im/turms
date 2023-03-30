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

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import org.eclipse.collections.api.iterator.MutableCharIterator;
import org.eclipse.collections.impl.set.mutable.primitive.IntHashSet;

import im.turms.plugin.antispam.dictionary.Word;

/**
 * @author James Chen
 */
public class AhoCorasickDoubleArrayTrie {

    public static final int ROOT_STATUS = 0;
    public static final int STATUS_NOT_FOUND = -1;

    public final int[] fail;
    public final int[][] output;
    public final DoubleArrayTrie dat;

    // TODO: we store the words and will support querying for admin API later
    public final Word[] words;

    public AhoCorasickDoubleArrayTrie(List<Word> words) {
        Trie trie = new Trie();
        int i = 0;
        this.words = new Word[words.size()];
        for (Word word : words) {
            this.words[i] = word;
            trie.addWord(word.getWord(), i++);
        }
        dat = new DoubleArrayTrie(trie);
        fail = new int[dat.capacity];
        output = new int[dat.capacity][];
        constructOutputAndFailure(trie);
    }

    public AhoCorasickDoubleArrayTrie(
            int[] fail,
            int[][] output,
            DoubleArrayTrie dat,
            Word[] words) {
        this.fail = fail;
        this.output = output;
        this.dat = dat;
        this.words = words;
    }

    public boolean matches(char[] text) {
        int currentState = ROOT_STATUS;
        for (char code : text) {
            currentState = findNextState(currentState, code);
            int[] emits = output[currentState];
            if (emits != null) {
                return true;
            }
        }
        return false;
    }

    protected int findNextState(int currentState, char code) {
        int nextState = transition(currentState, code);
        while (nextState == STATUS_NOT_FOUND) {
            currentState = fail[currentState];
            nextState = transition(currentState, code);
        }
        return nextState;
    }

    protected int transition(int indexInDat, char code) {
        int offset = dat.base[indexInDat];
        int nextState = offset + code + 1;
        if (nextState < dat.capacity && offset == dat.check[nextState]) {
            return nextState;
        }
        return indexInDat == ROOT_STATUS
                ? ROOT_STATUS
                : STATUS_NOT_FOUND;
    }

    protected void constructOutputAndFailure(Trie trie) {
        Queue<State> queue = new ArrayDeque<>(64);

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
            MutableCharIterator transitions = currentState.getTransitions();
            while (transitions.hasNext()) {
                char transition = transitions.next();
                State targetState = currentState.findNextState(transition);
                queue.add(targetState);
                State tempFailureState = currentState.failure;
                State targetFailureState;
                while ((targetFailureState = tempFailureState.findNextState(transition)) == null) {
                    tempFailureState = tempFailureState.failure;
                }
                targetState.failure = targetFailureState;
                fail[targetState.indexInDat] = targetFailureState.indexInDat;
                IntHashSet emits = targetFailureState.emits;
                if (emits != null && !emits.isEmpty()) {
                    targetState.addEmits(emits);
                }
                constructOutput(targetState);
            }
        }
    }

    protected void constructOutput(State targetState) {
        IntHashSet emits = targetState.emits;
        if (emits != null && !emits.isEmpty()) {
            output[targetState.indexInDat] = emits.toArray();
        }
    }

}
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

import javax.annotation.Nullable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author James Chen
 */
public class DoubleArrayTrie {

    private static final int MAX_CAPACITY = 10_000_000;
    private static final float MIN_GROW_FACTOR = 1.1f;

    /**
     * nextState = base[currentState] + code
     * value: 0 if unused; positive for termination; negative for leaf
     */
    int[] base;
    /**
     * check[nextState] == currentState
     * value: 0 if unused; positive if used
     */
    int[] check;
    @Nullable
    private boolean[] used;
    /**
     * a parameter controls the memory growth speed of the arrays
     */
    private int progress;
    /**
     * the next position to check unused memory
     */
    private int nextCheckPos;

    int currentMaxCodePoint;

    private int capacity;
    private final int termCount;

    public DoubleArrayTrie(Trie trie, int termCount) {
        this.termCount = termCount;
        State rootNode = trie.rootState;
        Set<Map.Entry<Character, State>> siblingEntries = rootNode.success.entrySet();
        int size = siblingEntries.size();
        if (size == 0) {
            return;
        }
        resize(1 << 16);
        // For the root state
        base[0] = 1;
        List<NodeEntry> siblings = new ArrayList<>(size);
        for (Map.Entry<Character, State> entry : siblingEntries) {
            siblings.add(new NodeEntry(entry.getKey() + 1, entry.getValue()));
        }
        Queue<SiblingGroup> siblingGroupQueue = new ArrayDeque<>(16);
        SiblingGroup siblingGroup = new SiblingGroup(-1, siblings);
        do {
            insert(siblingGroupQueue, siblingGroup);
        } while ((siblingGroup = siblingGroupQueue.poll()) != null);
    }

    private void resize(int newSize) {
        int[] newBase = new int[newSize];
        int[] newCheck = new int[newSize];
        boolean[] newUsed = new boolean[newSize];
        if (capacity > 0) {
            System.arraycopy(base, 0, newBase, 0, capacity);
            System.arraycopy(check, 0, newCheck, 0, capacity);
            System.arraycopy(used, 0, newUsed, 0, capacity);
        }
        base = newBase;
        check = newCheck;
        used = newUsed;
        capacity = newSize;
    }

    private void insert(Queue<SiblingGroup> siblingGroupQueue, SiblingGroup siblingGroup) {
        List<NodeEntry> siblings = siblingGroup.siblings;

        int siblingCount = siblings.size();
        int firstSiblingPos = siblings.get(0).pos;
        int begin;
        int pos = Math.max(firstSiblingPos, nextCheckPos - 1);
        int usedSize = 0;
        boolean first = false;

        if (capacity <= pos) {
            resize(pos + 1);
        }

        // Find the "pos" and "begin" to ensure there is
        // an available continuous space for siblings that satisfy
        // base[begin + siblings1...siblingsn]
        findBeginLoop:
        while (true) {
            pos++;
            if (capacity <= pos) {
                resize(pos + 1);
            }
            if (check[pos] == 0) {
                if (!first) {
                    nextCheckPos = pos;
                    first = true;
                }
            } else {
                usedSize++;
                continue;
            }
            begin = pos - firstSiblingPos;
            if (capacity <= (begin + siblings.get(siblingCount - 1).pos)) {
                int newSize = (int) (Math.max(MIN_GROW_FACTOR, ((float) termCount) / (progress + 1)) * capacity);
                if (newSize >= MAX_CAPACITY) {
                    throw new RuntimeException("The capacity of double array trie cannot be greater than %d. Requested: %d"
                            .formatted(MAX_CAPACITY, newSize));
                }
                resize(Math.min(newSize, MAX_CAPACITY));
            }
            if (used[begin]) {
                continue;
            }
            for (int i = 1; i < siblingCount; i++) {
                if (check[begin + siblings.get(i).pos] != 0) {
                    continue findBeginLoop;
                }
            }
            break;
        }

        used[begin] = true;
        currentMaxCodePoint = Math.max(currentMaxCodePoint, begin + siblings.get(siblingCount - 1).pos + 1);
        // if 95% space of check between the "nextCheckPos" and "pos"
        // is taken, set "nextCheckPos" to "pos" to find the next position when inserting the next node
        if (((float) usedSize) / (pos - nextCheckPos + 1) >= 0.95f) {
            nextCheckPos = pos;
        }
        for (NodeEntry sibling : siblings) {
            int targetPos = begin + sibling.pos;
            check[targetPos] = begin;
            State parent = sibling.state;
            Set<Map.Entry<Character, State>> siblingEntries = parent.success.entrySet();
            List<NodeEntry> newSiblings = new ArrayList<>(siblingEntries.size() + 1);
            if (parent.isMiddleState()) {
                State child = new State(-parent.depth - 1);
                child.addEmit(parent.getLargestTermIndex());
                newSiblings.add(new NodeEntry(0, child));
            }
            for (Map.Entry<Character, State> entry : siblingEntries) {
                newSiblings.add(new NodeEntry(entry.getKey() + 1, entry.getValue()));
            }
            if (newSiblings.isEmpty()) {
                base[targetPos] = -parent.getLargestTermIndex() - 1;
                progress++;
            } else {
                siblingGroupQueue.add(new SiblingGroup(targetPos, newSiblings));
            }
            parent.indexInDat = targetPos;
        }

        // insert siblings
        int parentBaseIndex = siblingGroup.parentBaseIndex;
        if (parentBaseIndex != -1) {
            base[parentBaseIndex] = begin;
        }
    }

    /**
     * @param pos = code point + 1
     */
    private record NodeEntry(int pos, State state) {
    }

    private record SiblingGroup(int parentBaseIndex, List<NodeEntry> siblings) {
    }
}

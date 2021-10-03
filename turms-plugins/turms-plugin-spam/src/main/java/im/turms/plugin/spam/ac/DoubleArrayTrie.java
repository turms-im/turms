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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author James Chen
 */
public class DoubleArrayTrie {

    private static final int MAX_CAPACITY = 10_000_000;
    private static final float GROW_FACTOR = 1.5f;

    /**
     * nextState = base[currentState] + code
     * value: 0 if unused; positive for termination; negative for leaf
     * range: [0, currentMaxPos]
     */
    int[] base;
    /**
     * check[nextState] = currentState
     * value: 0 if unused; positive if used
     * range: [0, currentMaxPos]
     */
    int[] check;
    int capacity;
    /**
     * the next position to check unused memory
     */
    int checkPosForNextRun;
    /**
     * currentMaxPos = max code + 1. Add 1 to avoid occupying the position of root
     */
    int currentMaxPos;

    public DoubleArrayTrie(Trie trie) {
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
        Queue<SiblingGroup> siblingGroupQueue = new LinkedList<>();
        SiblingGroup siblingGroup = new SiblingGroup(-1, siblings);
        do {
            insert(siblingGroupQueue, siblingGroup);
        } while ((siblingGroup = siblingGroupQueue.poll()) != null);
        compact();
    }

    public DoubleArrayTrie(int[] base, int[] check, int capacity) {
        this.base = base;
        this.check = check;
        this.capacity = capacity;
    }

    private void ensureSize(int minSize) {
        if (capacity >= minSize) {
            return;
        }
        if (minSize >= MAX_CAPACITY) {
            throw new RuntimeException("The capacity of double array trie cannot be greater than %d. Requested: %d"
                    .formatted(MAX_CAPACITY, minSize));
        }
        int newSize = capacity == 0 ? 65536 : (int) (capacity * GROW_FACTOR);
        newSize = Math.max(minSize, newSize);
        int[] newBase = new int[newSize];
        int[] newCheck = new int[newSize];
        if (capacity > 0) {
            System.arraycopy(base, 0, newBase, 0, capacity);
            System.arraycopy(check, 0, newCheck, 0, capacity);
        }
        base = newBase;
        check = newCheck;
        capacity = newSize;
    }

    private void compact() {
        int newCapacity = currentMaxPos + 1;
        if (capacity <= newCapacity) {
            return;
        }
        int[] newBase = new int[newCapacity];
        int[] newCheck = new int[newCapacity];
        System.arraycopy(base, 0, newBase, 0, newCapacity);
        System.arraycopy(check, 0, newCheck, 0, newCapacity);
        base = newBase;
        check = newCheck;
        capacity = newCapacity;
    }

    private void insert(Queue<SiblingGroup> siblingGroupQueue, SiblingGroup siblingGroup) {
        List<NodeEntry> siblings = siblingGroup.siblings;
        int siblingCount = siblings.size();
        int firstSiblingPos = siblings.get(0).pos;
        int lastSiblingPos = siblingCount > 1
                ? siblings.get(siblingCount - 1).pos
                : firstSiblingPos;
        // begin is always greater than 0 because base[0] is for the root state
        int begin;
        int checkPos = Math.max(firstSiblingPos, checkPosForNextRun);
        int usedSize = 0;
        boolean isFirstFreeCheckPosFound = false;

        // Find the "checkPos" and "begin" to ensure there are
        // consecutive free positions for siblings that satisfy
        // base[begin + siblings[0].pos...siblings[n].pos]
        findBeginLoop:
        while (true) {
            checkPos++;
            ensureSize(checkPos + 1);
            if (check[checkPos] != 0) {
                usedSize++;
                continue;
            }
            if (!isFirstFreeCheckPosFound) {
                checkPosForNextRun = checkPos;
                isFirstFreeCheckPosFound = true;
            }
            begin = checkPos - firstSiblingPos;
            ensureSize(begin + lastSiblingPos + 1);
            for (int i = 0; i < siblingCount; i++) {
                if (check[begin + siblings.get(i).pos] != 0) {
                    continue findBeginLoop;
                }
            }
            if (checkPosForNextRun == checkPos) {
                checkPosForNextRun += siblingCount;
            }
            break;
        }

        currentMaxPos = Math.max(currentMaxPos, begin + lastSiblingPos);
        // if 95% positions of check between "nextCheckPos" and "pos"
        // is taken, set "checkPosForNextRun" to "pos" to find the next check position next time
        if (((float) usedSize) / (checkPos - checkPosForNextRun + 1) >= 0.95f) {
            checkPosForNextRun = checkPos;
        }
        for (NodeEntry sibling : siblings) {
            int targetPos = begin + sibling.pos;
            check[targetPos] = begin;
            State parent = sibling.state;
            Set<Map.Entry<Character, State>> siblingEntries = parent.success.entrySet();
            List<NodeEntry> newSiblings = new ArrayList<>(siblingEntries.size() + 1);
            if (parent.isTermination()) {
                State child = new State(-parent.depth - 1);
                child.addEmit(parent.getLargestTermIndex());
                newSiblings.add(new NodeEntry(0, child));
            }
            for (Map.Entry<Character, State> entry : siblingEntries) {
                newSiblings.add(new NodeEntry(entry.getKey() + 1, entry.getValue()));
            }
            if (newSiblings.isEmpty()) {
                base[targetPos] = -parent.getLargestTermIndex() - 1;
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
     * @param pos = code + 1. Add 1 to avoid occupying the position of root
     */
    private record NodeEntry(int pos, State state) {
    }

    private record SiblingGroup(int parentBaseIndex, List<NodeEntry> siblings) {
    }
}

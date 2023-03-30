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
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.tuple.primitive.CharObjectPair;

/**
 * @author James Chen
 */
public class DoubleArrayTrie {

    private static final int MAX_CAPACITY = 10_000_000;
    private static final float GROW_FACTOR = 1.5f;

    /**
     * nextState = base[currentState] + code value: 0 if unused; positive for termination; negative
     * for leaf range: [0, currentMaxPos]
     */
    int[] base;
    /**
     * check[nextState] = currentState value: 0 if unused; positive if used range: [0,
     * currentMaxPos]
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
        RichIterable<CharObjectPair<State>> siblingEntries = rootNode.success.keyValuesView();
        int size = siblingEntries.size();
        if (size == 0) {
            return;
        }
        ensureSize(65536);
        // The index of the root state is 0,
        // and its value is 1 to point to the first child
        base[0] = 1;
        List<NodeEntry> siblings = new ArrayList<>(size);
        for (CharObjectPair<State> entry : siblingEntries) {
            siblings.add(new NodeEntry(entry.getOne() + 1, entry.getTwo()));
        }
        Queue<SiblingGroup> siblingGroupQueue = new ArrayDeque<>(64);
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
        if (minSize > MAX_CAPACITY) {
            throw new IllegalArgumentException(
                    "The requested minimum size ("
                            + minSize
                            + ") must not exceed the maximum capacity "
                            + MAX_CAPACITY);
        }
        int newSize = Math.max(minSize, Math.min((int) (capacity * GROW_FACTOR), MAX_CAPACITY));
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
        int usedPositionCount = 0;
        boolean isFirstFreeCheckPosFound = false;

        // Find the "checkPos" and "begin" to ensure there are
        // consecutive free positions for siblings that satisfy
        // base[begin + siblings[0].pos...siblings[n].pos]
        findBeginLoop: while (true) {
            checkPos++;
            ensureSize(checkPos + 1);
            if (check[checkPos] != 0) {
                usedPositionCount++;
                continue;
            }
            if (!isFirstFreeCheckPosFound) {
                checkPosForNextRun = checkPos;
                isFirstFreeCheckPosFound = true;
            }
            begin = checkPos - firstSiblingPos;
            ensureSize(begin + lastSiblingPos + 1);
            for (NodeEntry sibling : siblings) {
                if (check[begin + sibling.pos] != 0) {
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
        if (((float) usedPositionCount) / (checkPos - checkPosForNextRun + 1) >= 0.95F) {
            checkPosForNextRun = checkPos;
        }
        for (NodeEntry sibling : siblings) {
            int targetPos = begin + sibling.pos;
            check[targetPos] = begin;
            State parent = sibling.state;
            RichIterable<CharObjectPair<State>> siblingEntries = parent.success.keyValuesView();
            List<NodeEntry> newSiblings = new ArrayList<>(siblingEntries.size() + 1);
            if (parent.isTermination()) {
                State child = new State(-parent.depth - 1);
                child.addEmit(parent.getLargestTermIndex());
                newSiblings.add(new NodeEntry(0, child));
            }
            for (CharObjectPair<State> entry : siblingEntries) {
                newSiblings.add(new NodeEntry(entry.getOne() + 1, entry.getTwo()));
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
    private record NodeEntry(
            int pos,
            State state
    ) {
    }

    private record SiblingGroup(
            int parentBaseIndex,
            List<NodeEntry> siblings
    ) {
    }
}

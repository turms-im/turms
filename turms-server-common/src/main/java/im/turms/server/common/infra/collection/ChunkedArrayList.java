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

package im.turms.server.common.infra.collection;

import org.jctools.util.Pow2;

import javax.annotation.concurrent.NotThreadSafe;
import javax.validation.constraints.NotNull;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

/**
 * @author James Chen
 */
@NotThreadSafe
public class ChunkedArrayList<E> extends AbstractList<E> implements RandomAccess {

    private static final int DEFAULT_CHUNK_CAPACITY = 32;
    private static final int DEFAULT_INITIAL_CHUNKS = 8;

    private final int elementSizePerChunk;
    private final int elementSizePerChunkMask;
    private final List<List<E>> chunks;

    private int itemCount;

    public ChunkedArrayList(int elementSizePerChunk, int initialChunks) {
        this.elementSizePerChunk = Pow2.roundToPowerOfTwo(elementSizePerChunk);
        elementSizePerChunkMask = this.elementSizePerChunk - 1;
        chunks = new ArrayList<>(initialChunks);
    }

    public ChunkedArrayList(int elementSizePerChunk) {
        this(elementSizePerChunk, DEFAULT_INITIAL_CHUNKS);
    }

    public ChunkedArrayList() {
        this(DEFAULT_CHUNK_CAPACITY, DEFAULT_INITIAL_CHUNKS);
    }

    @Override
    public int size() {
        return itemCount;
    }

    @Override
    public boolean contains(Object element) {
        for (List<E> chunk : chunks) {
            if (chunk.contains(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(@NotNull E element) {
        int chunkCount = chunks.size();
        List<E> chunk;
        if (chunkCount == 0 || (chunk = chunks.get(chunkCount - 1)).size() == elementSizePerChunk) {
            chunk = new ArrayList<>(elementSizePerChunk);
            chunks.add(chunk);
        }
        chunk.add(element);
        itemCount++;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        List<E> chunk;
        List<E> nextChunk;
        for (int i = 0, chunkCount = chunks.size(); i < chunkCount; i++) {
            chunk = chunks.get(i);
            if (chunk.remove(element)) {
                int elementCount = chunk.size();
                if (elementCount == 0) {
                    chunks.remove(i);
                } else if (elementCount == elementSizePerChunkMask) {
                    while (++i < chunkCount) {
                        nextChunk = chunks.get(i);
                        chunk.add(nextChunk.remove(0));
                        if (nextChunk.isEmpty()) {
                            chunks.remove(i);
                            break;
                        }
                        chunk = nextChunk;
                    }
                }
                itemCount--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (List<E> chunk : chunks) {
            chunk.clear();
        }
        chunks.clear();
        itemCount = 0;
    }

    @Override
    public E get(int index) {
        return chunks.get(index / elementSizePerChunk).get(index & elementSizePerChunkMask);
    }

    @Override
    public E set(int index, @NotNull E element) {
        List<E> chunk = chunks.get(index / elementSizePerChunk);
        int elementIndex = index & elementSizePerChunkMask;
        E existingElement = chunk.get(elementIndex);
        if (existingElement != null) {
            itemCount++;
        }
        return chunk.set(elementIndex, element);
    }

    @Override
    public void add(int index, @NotNull E element) {
        int chunkIndex = index / elementSizePerChunk;
        int elementIndex = index & elementSizePerChunkMask;
        List<E> chunk;
        int chunkCount = chunks.size();
        while (chunkIndex < chunkCount) {
            chunk = chunks.get(chunkIndex);
            if (chunk.size() < elementSizePerChunk) {
                chunk.add(elementIndex, element);
                itemCount++;
                return;
            }
            E removedElement = chunk.remove(elementSizePerChunkMask);
            chunk.add(elementIndex, element);
            element = removedElement;
            chunkIndex++;
            elementIndex = 0;
        }
        chunk = new ArrayList<>(elementSizePerChunk);
        chunks.add(chunk);
        chunk.add(element);
        itemCount++;
    }

    @Override
    public E remove(int index) {
        int chunkIndex = index / elementSizePerChunk;
        int elementIndex = index & elementSizePerChunkMask;
        List<E> chunk = chunks.get(chunkIndex);
        E removedElement = chunk.remove(elementIndex);
        if (removedElement == null) {
            return null;
        }
        itemCount--;
        List<E> nextChunk;
        int elementCount = chunk.size();
        if (elementCount == 0) {
            chunks.remove(chunkIndex);
        } else if (elementCount == elementSizePerChunkMask) {
            int chunkCount = chunks.size();
            while (++chunkIndex < chunkCount) {
                nextChunk = chunks.get(chunkIndex);
                chunk.add(nextChunk.remove(0));
                if (nextChunk.isEmpty()) {
                    chunks.remove(chunkIndex);
                    break;
                }
                chunk = nextChunk;
            }
        }
        return removedElement;
    }

    @Override
    public int indexOf(Object element) {
        List<E> chunk;
        int result;
        for (int i = 0, chunkCount = chunks.size(); i < chunkCount; i++) {
            chunk = chunks.get(i);
            result = chunk.indexOf(element);
            if (result >= 0) {
                return i * elementSizePerChunk + result;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        List<E> chunk;
        int result;
        for (int i = chunks.size() - 1; i >= 0; i--) {
            chunk = chunks.get(i);
            result = chunk.lastIndexOf(element);
            if (result >= 0) {
                return i * elementSizePerChunk + result;
            }
        }
        return -1;
    }

}
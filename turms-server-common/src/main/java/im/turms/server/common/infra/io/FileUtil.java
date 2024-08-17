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

package im.turms.server.common.infra.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.buffer.ByteBuf;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

/**
 * @author James Chen
 */
public final class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    private static Path tempDir;
    private static WatchService watchService;
    private static ConcurrentHashMap<Path, Sinks.Many<FileChangeEvent>> watchedAbsPathToEvents;

    private FileUtil() {
    }

    public static void startWatchService() {
        if (watchService == null) {
            synchronized (FileUtil.class) {
                if (watchService == null) {
                    watchedAbsPathToEvents = new ConcurrentHashMap<>();
                    startWatchService0();
                }
            }
        }
    }

    private static void startWatchService0() {
        try {
            watchService = FileSystems.getDefault()
                    .newWatchService();
        } catch (IOException e) {
            throw new InputOutputException("Failed to create the watch service", e);
        }
        LOGGER.info("Started the watch service");
        Thread.ofVirtual()
                .start(() -> {
                    boolean continueRunning;
                    do {
                        try {
                            continueRunning = waitAndHandleWatchKey();
                        } catch (Exception e) {
                            LOGGER.error("Caught an error while handling a watch key", e);
                            continueRunning = true;
                        }
                    } while (continueRunning);
                });
    }

    private static boolean waitAndHandleWatchKey() {
        WatchKey key;
        try {
            key = watchService.take();
        } catch (InterruptedException e) {
            try {
                watchService.close();
            } catch (Exception ignored) {
            }
            LOGGER.info("Stopped the watch service due to an interrupt");
            return false;
        } catch (ClosedWatchServiceException e) {
            LOGGER.info("Stopped the watch service due to the watch service being closed");
            return false;
        } catch (Exception e) {
            LOGGER.error("Failed to take a watch key", e);
            return true;
        }
        if (key == null) {
            return true;
        }
        try {
            Path watchedPath = (Path) key.watchable();
            Sinks.Many<FileChangeEvent> sink = watchedAbsPathToEvents.get(watchedPath);
            if (sink == null) {
                return true;
            }
            for (WatchEvent<?> watchEvent : key.pollEvents()) {
                Path path = (Path) watchEvent.context();
                sink.tryEmitNext(new FileChangeEvent(
                        watchedPath.resolve(path),
                        (WatchEvent.Kind<Path>) watchEvent.kind()));
            }
        } finally {
            key.reset();
        }
        return true;
    }

    public static Flux<FileChangeEvent> watchDir(Path pathToWatch) {
        startWatchService();
        Sinks.Many<FileChangeEvent> eventSink =
                watchedAbsPathToEvents.computeIfAbsent(pathToWatch.toAbsolutePath()
                        .normalize(), key -> {
                            try {
                                key.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                            } catch (IOException e) {
                                throw new InputOutputException(
                                        "Failed to watch the directory: "
                                                + key,
                                        e);
                            }
                            return Sinks.many()
                                    .multicast()
                                    .directAllOrNothing();
                        });
        return eventSink.asFlux();
    }

    public static void setTempDir(Path tempDir) {
        FileUtil.tempDir = tempDir;
        try {
            Files.createDirectories(tempDir);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to create the temp directory: "
                            + tempDir,
                    e);
        }
    }

    public static Path createTempFile(Path dir, String fileName) {
        dir = tempDir.resolve(dir);
        try {
            // Ensure the directory exists because it may be deleted by users unexpectedly
            Files.createDirectories(dir);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to create the directory: "
                            + dir,
                    e);
        }
        Path filePath = dir.resolve(fileName);
        try {
            return Files.createFile(filePath);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to create the file: "
                            + filePath,
                    e);
        }
    }

    public static long size(Path path) {
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to get the size of the file: "
                            + path,
                    e);
        }
    }

    public static void write(File file, ByteBuf buffer) {
        try (FileOutputStream outputStream = new FileOutputStream(file);
                FileChannel channel = outputStream.getChannel()) {
            if (buffer.nioBufferCount() == 1) {
                channel.write(buffer.nioBuffer());
            } else {
                for (ByteBuffer buf : buffer.nioBuffers()) {
                    channel.write(buf);
                }
            }
        } catch (IOException e) {
            throw new InputOutputException("Caught an error while closing the file channel", e);
        }
    }

    public static ByteBuffer read(FileChannel channel) {
        long size;
        try {
            size = channel.size();
        } catch (IOException e) {
            throw new InputOutputException("Failed to get the size of the file", e);
        }
        try {
            return channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
        } catch (IOException e) {
            throw new InputOutputException("Failed to map the file", e);
        }
    }

}
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

package im.turms.server.common.domain.observation.service;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicLong;
import jakarta.annotation.Nullable;

import com.sun.management.HotSpotDiagnosticMXBean;
import org.springframework.stereotype.Service;

import im.turms.server.common.access.admin.web.HttpResponseException;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.common.service.BaseService;
import im.turms.server.common.infra.application.TurmsApplicationContext;
import im.turms.server.common.infra.io.FileResource;
import im.turms.server.common.infra.io.FileUtil;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

/**
 * @author James Chen
 */
@Service
public class HeapDumpService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeapDumpService.class);
    private static final AtomicLong TEMP_HEAP_DUMP_FILE_ID = new AtomicLong(0);

    private final HotSpotDiagnosticMXBean bean;
    private final Path dir;
    @Nullable
    private FileResource exportingFile;

    public HeapDumpService(TurmsApplicationContext context) {
        dir = context.getHome()
                .resolve("hprof")
                .normalize();
        bean = ManagementFactory.getPlatformMXBean(HotSpotDiagnosticMXBean.class);
    }

    @GetMapping
    public FileResource heapDump(boolean live) {
        FileResource heap;
        synchronized (this) {
            if (exportingFile != null) {
                // We limit the concurrent exporting heapdump file to 1
                throw new HttpResponseException(
                        ResponseStatusCode.CLIENT_REQUESTS_TOO_FREQUENT,
                        "A heap dump file is being exported");
            }
            heap = dumpHeap(live);
            exportingFile = heap;
        }
        return heap;
    }

    private FileResource dumpHeap(boolean live) {
        Path tempFile = FileUtil.createTempFile(dir,
                TEMP_HEAP_DUMP_FILE_ID.getAndIncrement()
                        + ".hprof")
                .toAbsolutePath();
        String filePathStr = tempFile.toString();
        try {
            bean.dumpHeap(filePathStr, live);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to dump the heap from the HotSpotDiagnosticMXBean bean",
                    e);
        }
        return new FileResource(
                System.currentTimeMillis()
                        + ".hprof",
                tempFile,
                throwable -> {
                    try {
                        Files.deleteIfExists(tempFile);
                    } catch (IOException e) {
                        LOGGER.warn("Failed to delete the temporary heap dump file: "
                                + filePathStr, e);
                    }
                    exportingFile = null;
                });
    }

}
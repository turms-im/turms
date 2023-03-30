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

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;

import com.sun.management.HotSpotDiagnosticMXBean;
import org.springframework.stereotype.Service;

import im.turms.server.common.access.admin.web.HttpResponseException;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.io.FileResource;
import im.turms.server.common.infra.io.FileUtil;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.random.RandomUtil;

/**
 * @author James Chen
 */
@Service
public class HeapDumpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeapDumpService.class);

    private final HotSpotDiagnosticMXBean bean;
    private final Path dir;
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
        File tempFile = FileUtil.createTempFile("temp-"
                + RandomUtil.nextPositiveInt(), ".hprof", dir.toFile());
        tempFile.delete();
        String filePathStr = tempFile.getAbsolutePath();
        Path filePath = tempFile.toPath();
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
                filePath,
                throwable -> {
                    try {
                        Files.deleteIfExists(filePath);
                    } catch (IOException e) {
                        LOGGER.warn("Failed to delete the temporary heap dump file: "
                                + filePathStr, e);
                    }
                    exportingFile = null;
                });
    }

}

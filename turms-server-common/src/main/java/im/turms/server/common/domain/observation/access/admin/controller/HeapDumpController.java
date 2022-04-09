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

package im.turms.server.common.domain.observation.access.admin.controller;

import com.sun.management.HotSpotDiagnosticMXBean;
import im.turms.server.common.domain.common.dto.response.ResponseFactory;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.io.FileResource;
import im.turms.server.common.infra.io.FileUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.random.RandomUtil;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * spring-framework:v5.3.18 doesn't support triggering a callback
 * after a heap file is transferred in zero-copy mode.
 * <p>
 * So we use this class to support triggering a callback
 * after a heap file is transferred in zero-copy mode
 *
 * @author James Chen
 * @see org.springframework.boot.actuate.management.HeapDumpWebEndpoint.TemporaryFileSystemResource#isFile()
 */
@RestController
@RequestMapping("/heapdump")
public class HeapDumpController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeapDumpController.class);

    private final HotSpotDiagnosticMXBean bean;
    private final Path dir;

    private volatile Resource exportingFile;

    public HeapDumpController(TurmsApplicationContext context) {
        dir = context.getHome().resolve("hprof").normalize();
        bean = ManagementFactory.getPlatformMXBean(HotSpotDiagnosticMXBean.class);
    }

    @GetMapping
    public ResponseEntity<Resource> heapDump(@RequestParam(required = false, defaultValue = "true") boolean live) {
        Resource heap;
        synchronized (this) {
            if (exportingFile != null) {
                // We limit the concurrent exporting heapdump file to 1
                throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS, "A heap dump file is being exported");
            }
            heap = dumpHeap(live);
            exportingFile = heap;
        }

        return ResponseFactory.raw(heap);
    }

    @SneakyThrows
    private Resource dumpHeap(boolean live) {
        File tempFile = FileUtil.createTempFile("temp-" + RandomUtil.nextPositiveInt(), ".hprof", dir.toFile());
        tempFile.delete();
        bean.dumpHeap(tempFile.getAbsolutePath(), live);
        return new FileResource(tempFile, throwable -> {
            try {
                Files.deleteIfExists(tempFile.toPath());
            } catch (IOException e) {
                LOGGER.warn("Failed to delete temporary heap dump file \"" + tempFile.getAbsolutePath() + "\"", e);
            }
            exportingFile = null;
        });
    }

}

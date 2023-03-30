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

package im.turms.server.common.infra.thread;

import java.lang.management.LockInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

import im.turms.server.common.infra.lang.NumberFormatter;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.time.DateUtil;

/**
 * @author James Chen
 */
public class ThreadDumpFormatter {

    private static final byte[] AT = StringUtil.getBytes("\tat ");
    private static final byte[] FULL_THREAD_DUMP;
    private static final byte[] LOCK_INFO_BEFORE = StringUtil.getBytes("> (a ");
    private static final byte[] LOCKED = StringUtil.getBytes("\t- Locked ");
    private static final byte[] LOCKED_FOR_MONITOR = StringUtil.getBytes("\t- locked ");
    private static final byte[] LOCKED_OWNABLE_SYNCHRONIZERS =
            StringUtil.getBytes("   Locked ownable synchronizers:\n");
    private static final byte[] NONE = StringUtil.getBytes("\t- None\n");
    private static final byte[] OWNED_BY = StringUtil.getBytes(" owned by \"");
    private static final byte[] PARKING_TO_WAIT_FOR =
            StringUtil.getBytes("\t- parking to wait for %s\n");
    private static final byte[] THREAD = StringUtil.getBytes(" - Thread t@");
    private static final byte[] THREAD_STATE_CLASS_NAME =
            StringUtil.getBytes(Thread.State.class.getCanonicalName());
    private static final byte[] THREE_SPACES = StringUtil.getBytes("   ");
    private static final byte[] THREAD_T = StringUtil.getBytes("\" t@");
    private static final byte[] WAITING_ON = StringUtil.getBytes("\t- waiting on ");
    private static final byte[] WAITING_TO_LOCK = StringUtil.getBytes("\t- waiting to lock ");

    static {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        FULL_THREAD_DUMP = StringUtil.getBytes("Full thread dump "
                + runtime.getVmName()
                + " ("
                + runtime.getVmVersion()
                + " "
                + System.getProperty("java.vm.info")
                + "):\n");
    }

    private ThreadDumpFormatter() {
    }

    public static ByteBuf format(ThreadInfo[] threads) {
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(threads.length * 512);
        buffer.writeBytes(DateUtil.toBytes(System.currentTimeMillis()))
                .writeByte('\n')
                .writeBytes(FULL_THREAD_DUMP)
                .writeByte('\n');
        for (ThreadInfo info : threads) {
            writeThread(buffer, info);
        }
        return buffer;
    }

    private static List<MonitorInfo> getLockedMonitorsForDepth(
            MonitorInfo[] lockedMonitors,
            int depth) {
        List<MonitorInfo> monitors = new ArrayList<>(lockedMonitors.length);
        for (MonitorInfo monitor : lockedMonitors) {
            if (monitor.getLockedStackDepth() == depth) {
                monitors.add(monitor);
            }
        }
        return monitors;
    }

    private static void writeThread(ByteBuf buffer, ThreadInfo info) {
        buffer.writeByte('\"')
                .writeBytes(StringUtil.getBytes(info.getThreadName()))
                .writeByte('\"')
                .writeBytes(THREAD)
                .writeBytes(NumberFormatter.toCharBytes(info.getThreadId()))
                .writeByte('\n')
                .writeBytes(THREE_SPACES)
                .writeBytes(THREAD_STATE_CLASS_NAME)
                .writeByte(':')
                .writeByte(' ')
                .writeBytes(StringUtil.getBytes(info.getThreadState()
                        .toString()));
        writeStackTrace(buffer, info, info.getLockedMonitors());
        buffer.writeByte('\n');
        writeLockedOwnableSynchronizers(buffer, info);
        buffer.writeByte('\n');
    }

    private static void writeLockInfo(ByteBuf buffer, LockInfo lockInfo) {
        buffer.writeByte('<')
                .writeBytes(
                        StringUtil.getBytes(Integer.toHexString(lockInfo.getIdentityHashCode())))
                .writeBytes(LOCK_INFO_BEFORE)
                .writeBytes(StringUtil.getBytes(lockInfo.getClassName()))
                .writeByte(')');
    }

    private static void writeStackTrace(
            ByteBuf buffer,
            ThreadInfo info,
            MonitorInfo[] lockedMonitors) {
        int depth = 0;
        for (StackTraceElement element : info.getStackTrace()) {
            writeStackTraceElement(buffer,
                    element,
                    info,
                    getLockedMonitorsForDepth(lockedMonitors, depth),
                    depth == 0);
            depth++;
        }
    }

    private static void writeStackTraceElement(
            ByteBuf buffer,
            StackTraceElement element,
            ThreadInfo info,
            List<MonitorInfo> lockedMonitors,
            boolean firstElement) {
        buffer.writeBytes(AT)
                .writeBytes(StringUtil.getBytes(element.toString()))
                .writeByte('\n');
        LockInfo lockInfo = info.getLockInfo();
        if (firstElement && lockInfo != null) {
            if (element.getClassName()
                    .equals(Object.class.getName())
                    && element.getMethodName()
                            .equals("wait")) {
                buffer.writeBytes(WAITING_ON);
                writeLockInfo(buffer, lockInfo);
                buffer.writeByte('\n');
            } else {
                String lockOwner = info.getLockOwnerName();
                if (lockOwner == null) {
                    buffer.writeBytes(PARKING_TO_WAIT_FOR);
                    writeLockInfo(buffer, lockInfo);
                } else {
                    buffer.writeBytes(WAITING_TO_LOCK);
                    writeLockInfo(buffer, lockInfo);
                    buffer.writeBytes(OWNED_BY)
                            .writeBytes(StringUtil.getBytes(lockOwner))
                            .writeBytes(THREAD_T)
                            .writeBytes(NumberFormatter.toCharBytes(info.getLockOwnerId()))
                            .writeByte('\n');
                }
            }
        }
        writeMonitors(buffer, lockedMonitors);
    }

    private static void writeMonitors(
            ByteBuf buffer,
            List<MonitorInfo> lockedMonitorsAtCurrentDepth) {
        for (MonitorInfo lockedMonitor : lockedMonitorsAtCurrentDepth) {
            buffer.writeBytes(LOCKED_FOR_MONITOR);
            writeLockInfo(buffer, lockedMonitor);
            buffer.writeByte('\n');
        }
    }

    private static void writeLockedOwnableSynchronizers(ByteBuf buffer, ThreadInfo info) {
        buffer.writeBytes(LOCKED_OWNABLE_SYNCHRONIZERS);
        LockInfo[] lockedSynchronizers = info.getLockedSynchronizers();
        if (lockedSynchronizers == null || lockedSynchronizers.length == 0) {
            buffer.writeBytes(NONE);
        } else {
            for (LockInfo lockedSynchronizer : lockedSynchronizers) {
                buffer.writeBytes(LOCKED);
                writeLockInfo(buffer, lockedSynchronizer);
                buffer.writeByte('\n');
            }
        }
    }

}

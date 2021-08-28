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

package im.turms.server.common.cluster.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.primitives.Ints;
import lombok.Getter;
import org.springframework.data.annotation.Transient;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author James Chen
 */
@Getter
public class NodeVersion implements Comparable<NodeVersion> {

    private static final Pattern VERSION_PATTERN =
            Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)(-SNAPSHOT)?$");
    private static final String WRONG_VERSION_FORMAT_ERROR_MESSAGE =
            "The version string must follow the formats: \"major.minor.patch\" or \"major.minor.patch-SNAPSHOT\"";

    private final byte major;
    private final byte minor;
    private final byte patch;
    private final byte qualifier;

    @JsonIgnore
    @Transient
    private final int version;

    public NodeVersion(byte major, byte minor, byte patch, byte qualifier) {
        if (major < 0 || minor < 0 || patch < 0 || qualifier < 0) {
            throw new IllegalArgumentException(WRONG_VERSION_FORMAT_ERROR_MESSAGE);
        }
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.qualifier = qualifier;

        version = Ints.fromBytes(major, minor, patch, qualifier);
    }

    @Override
    public int compareTo(NodeVersion that) {
        if (version > that.version) {
            return 1;
        } else {
            return version == that.version ? 0 : -1;
        }
    }

    @Override
    public String toString() {
        return qualifier == 0
                ? "%d.%d.%d".formatted(major, minor, patch)
                : "%d.%d.%d-SNAPSHOT".formatted(major, minor, patch);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NodeVersion that = (NodeVersion) o;
        return version == that.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(version);
    }

    public static NodeVersion parse(String version) {
        Matcher matcher = VERSION_PATTERN.matcher(version);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(WRONG_VERSION_FORMAT_ERROR_MESSAGE);
        }
        byte major = -1;
        byte minor = -1;
        byte patch = -1;
        byte qualifier = Qualifier.RELEASE.getId();
        int groupCount = matcher.groupCount();
        for (int i = 0; i < groupCount; i++) {
            String token = matcher.group(i + 1);
            if (token == null) {
                break;
            }
            switch (i) {
                case 0 -> major = Byte.parseByte(token);
                case 1 -> minor = Byte.parseByte(token);
                case 2 -> patch = Byte.parseByte(token);
                case 3 -> {
                    for (Qualifier qualifierValue : Qualifier.values()) {
                        if (token.endsWith(qualifierValue.name())) {
                            qualifier = qualifierValue.getId();
                            break;
                        }
                    }
                }
                default -> throw new IllegalArgumentException(WRONG_VERSION_FORMAT_ERROR_MESSAGE);
            }
        }
        return new NodeVersion(major, minor, patch, qualifier);
    }

    @Getter
    private enum Qualifier {

        RELEASE((byte) 0),
        SNAPSHOT((byte) 1);

        byte id;

        Qualifier(byte id) {
            this.id = id;
        }

    }
}
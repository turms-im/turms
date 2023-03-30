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

package unit.im.turms.server.common.infra.property;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.property.InvalidPropertyException;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesConvertor;
import im.turms.server.common.testing.JsonUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static im.turms.server.common.infra.property.TurmsPropertiesConvertor.mergeProperties;
import static im.turms.server.common.infra.property.TurmsPropertiesConvertor.validatePropertiesForUpdating;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.METADATA;

/**
 * @author James Chen
 */
class TurmsPropertiesConvertorTests {

    @Test
    void validatePropertiesForUpdating_shouldNotReturnException_whenUpdatingMutableProperties() {
        InvalidPropertyException exception = validatePropertiesForUpdating(new TurmsProperties(),
                Map.of("gateway",
                        Map.of("adminApi", Map.of("rateLimiting", Map.of("capacity", "9513")))));
        assertThat(exception).isNull();
    }

    @Test
    void validatePropertiesForUpdating_shouldReturnException_forNonExistingProperties() {
        InvalidPropertyException exception = validatePropertiesForUpdating(new TurmsProperties(),
                Map.of("gateway", Map.of("rateLimiting", Map.of("capacity", "9513"))));
        assertThat(exception).isNotNull();
    }

    @Test
    void validatePropertiesForUpdating_shouldReturnException_forImmutableProperties() {
        InvalidPropertyException exception = validatePropertiesForUpdating(new TurmsProperties(),
                Map.of("userStatus", Map.of("userSessionsStatusCacheMaxSize", "95175328")));
        assertThat(exception).isNotNull();
    }

    @Test
    void mergeProperties_shouldReturnNewProperties_whenMergingEmptyProperties() {
        TurmsProperties properties = new TurmsProperties();
        TurmsProperties newProperties = mergeProperties(properties, Map.of());
        assertThat(newProperties).isNotSameAs(properties);
        assertThat(newProperties.getIp()).isNotSameAs(properties.getIp());
        assertThat(newProperties.getIp()
                .getPublicIpDetectorAddresses()).isNotSameAs(properties.getIp()
                        .getPublicIpDetectorAddresses());
    }

    @Test
    void mergeProperties_shouldOnlyUpdateNewProperties_whenMeringValidProperties() {
        TurmsProperties properties = new TurmsProperties();
        List<String> originalIpAddresses = new ArrayList<>(
                properties.getIp()
                        .getPublicIpDetectorAddresses());
        int expectedUserSessionsStatusCacheMaxSize = 654132;
        TurmsProperties newProperties = mergeProperties(properties,
                Map.of("ip",
                        Map.of("publicIpDetectorAddresses", List.of("123")),
                        "userStatus",
                        Map.of("cacheUserSessionsStatus",
                                false,
                                "userSessionsStatusCacheMaxSize",
                                expectedUserSessionsStatusCacheMaxSize)));

        assertThat(properties.getIp()
                .getPublicIpDetectorAddresses()).containsExactlyElementsOf(originalIpAddresses);

        assertThat(newProperties).isNotSameAs(properties);
        assertThat(newProperties.getIp()
                .getPublicIpDetectorAddresses()).containsExactly("123");
        assertThat(newProperties.getUserStatus()
                .isCacheUserSessionsStatus()).isFalse();
        assertThat(newProperties.getUserStatus()
                .getUserSessionsStatusCacheMaxSize())
                .isEqualTo(expectedUserSessionsStatusCacheMaxSize);
    }

    @Test
    void mergeProperties_shouldUpdate_whenMeringValidEmptyPropertyValues() {
        TurmsProperties properties = new TurmsProperties();
        List<String> originalIpAddresses = new ArrayList<>(
                properties.getIp()
                        .getPublicIpDetectorAddresses());
        TurmsProperties newProperties = mergeProperties(properties,
                Map.of("ip", Map.of("publicIpDetectorAddresses", List.of())));

        assertThat(properties.getIp()
                .getPublicIpDetectorAddresses()).containsExactlyElementsOf(originalIpAddresses);
        assertThat(newProperties).isNotSameAs(properties);
        assertThat(newProperties.getIp()
                .getPublicIpDetectorAddresses()).isEmpty();
    }

    @Test
    void mergeProperties_shouldNotUpdate_whenMeringValidEmptyProperties() {
        TurmsProperties properties = new TurmsProperties();
        TurmsProperties newProperties = mergeProperties(properties, Map.of("userStatus", Map.of()));

        assertThat(newProperties).isNotSameAs(properties);
        assertThat(newProperties.getUserStatus()
                .isCacheUserSessionsStatus()).isEqualTo(properties.getUserStatus()
                        .isCacheUserSessionsStatus());
        assertThat(newProperties.getUserStatus()
                .getUserSessionsStatusCacheMaxSize()).isEqualTo(properties.getUserStatus()
                        .getUserSessionsStatusCacheMaxSize());
    }

    @Test
    void mergeProperties_shouldThrow_whenMergingPropertiesWithWrongType() {
        assertThatThrownBy(() -> mergeProperties(new TurmsProperties(),
                Map.of("ip", Map.of("publicIpDetectorAddresses", Map.of(123, 321)))))
                .isInstanceOf(InvalidPropertyException.class);

        assertThatThrownBy(() -> mergeProperties(new TurmsProperties(),
                Map.of("userStatus", Map.of("cacheUserSessionsStatus", "Wrong Value Type"))))
                .isInstanceOf(InvalidPropertyException.class);

        assertThatThrownBy(() -> mergeProperties(new TurmsProperties(),
                Map.of("userStatus", Map.of("userSessionsStatusCacheMaxSize", "Wrong Value Type"))))
                .isInstanceOf(InvalidPropertyException.class);
    }

    @Test
    void mergeProperties_shouldThrow_whenMergingNonExistingProperties() {
        assertThatThrownBy(() -> mergeProperties(new TurmsProperties(),
                Map.of("<>?:L>", Map.of("publicIpDetectorAddresses", "Wrong Value Type"))))
                .isInstanceOf(InvalidPropertyException.class);
    }

    @Test
    void mergeMetadataWithPropertyValue() {
        Map<String, Object> actual = TurmsPropertiesConvertor
                .mergeMetadataWithPropertyValue(METADATA, new TurmsProperties());
        InputStream expected = getClass().getClassLoader()
                .getResourceAsStream("turms-properties-metadata-with-property-value.json");
        JsonUtil.assertEqual(actual, expected);
    }

}

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

package im.turms.plugin.push.core.sender;

import java.util.Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import im.turms.plugin.push.core.PushNotification;
import im.turms.plugin.push.core.PushNotificationServiceProvider;
import im.turms.plugin.push.core.PushNotificationType;
import im.turms.plugin.push.core.sender.apns.ApnsSender;
import im.turms.plugin.push.property.ApnsProperties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author James Chen
 */
class ApnsSenderTests {

    @Test
    void test() throws JsonProcessingException {
        String signingKey = """
                -----BEGIN PRIVATE KEY-----
                MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQg5+Rz0Q2t1CiC7VW3
                yLjX9uJtlrf5zPpWwE8vRqhVrMagCgYIKoZIzj0DAQehRANCAASNeBnC7fQpp1l/
                vFTrS6U+pN6Dk2eT/64dXO4s4Ox/Vx4N/iKd4+mjrNcBuprmYZmL34yFhIfTx60X
                lRK0bZhH
                -----END PRIVATE KEY-----""";
        ApnsSender sender = new ApnsSender(
                Collections.emptyMap(),
                new ApnsProperties().toBuilder()
                        .signingKey(signingKey)
                        .build());
        ObjectMapper objectMapper =
                new ObjectMapper().enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);

        String token = "549ede2dea554e116520da773e1f6074c2688a325a47acad5f57caa8fbbd083a";
        PushNotification notification = new PushNotification(
                PushNotificationType.SEND_MESSAGE,
                PushNotificationServiceProvider.APN,
                token,
                "Nujabes",
                "Reflection Eternal",
                99);
        String payload = sender.buildPayload(notification, "en", Collections::emptyMap);
        String expected =
                """
                        {"aps":{"mutable_content":1,"alert":{"title":"Nujabes","body":"Reflection Eternal"},"badge":99}}""";
        assertThat(objectMapper.readTree(payload)).isEqualTo(objectMapper.readTree(expected));

        notification = new PushNotification(
                PushNotificationType.SEND_MESSAGE,
                PushNotificationServiceProvider.APN,
                token,
                "「幻华」（城之内ミサ）",
                payload,
                null);
        payload = sender.buildPayload(notification, "en", Collections::emptyMap);
        expected =
                """
                        {"aps":{"mutable_content":1,"alert":{"title":"「幻华」（城之内ミサ）","body":"{\\"aps\\":{\\"mutable_content\\":1,\\"alert\\":{\\"title\\":\\"Nujabes\\",\\"body\\":\\"Reflection Eternal\\"},\\"badge\\":99}}"}}}""";
        assertThat(objectMapper.readTree(payload)).isEqualTo(objectMapper.readTree(expected));
    }

}
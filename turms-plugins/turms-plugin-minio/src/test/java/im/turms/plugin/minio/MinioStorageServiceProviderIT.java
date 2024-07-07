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

package im.turms.plugin.minio;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.test.StepVerifier;

import im.turms.plugin.minio.properties.MinioStorageProperties;
import im.turms.server.common.access.admin.web.MediaType;
import im.turms.server.common.access.admin.web.MediaTypeConst;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.mongo.MongoProperties;
import im.turms.server.common.infra.property.env.service.ServiceProperties;
import im.turms.server.common.infra.property.env.service.business.storage.StorageProperties;
import im.turms.server.common.testing.BaseIntegrationTest;
import im.turms.server.common.testing.environment.ServiceTestEnvironmentType;
import im.turms.server.common.testing.properties.MinioTestEnvironmentProperties;
import im.turms.server.common.testing.properties.TestProperties;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.user.service.UserRelationshipService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MinioStorageServiceProviderIT extends BaseIntegrationTest {

    private static final long USER_ID = 1L;
    private static final byte[] FILE_FOR_UPLOADING = new byte[]{0, 1, 2, 3};
    private static final String FILE_MEDIA_TYPE = MediaTypeConst.IMAGE_PNG;

    private static final Duration TIMEOUT_DURATION = Duration.ofMinutes(1);

    private static MinioStorageServiceProvider serviceProvider;

    @BeforeAll
    static void setup() {
        setupTestEnvironment(new TestProperties().toBuilder()
                .minio(new MinioTestEnvironmentProperties().toBuilder()
                        .type(ServiceTestEnvironmentType.CONTAINER)
                        .build())
                .build());
    }

    @Order(0)
    @Test
    void test_start() {
        TurmsPropertiesManager turmsPropertiesManager = mock(TurmsPropertiesManager.class);
        when(turmsPropertiesManager.getLocalProperties())
                .thenReturn(new TurmsProperties().toBuilder()
                        .service(new ServiceProperties().toBuilder()
                                .storage(new StorageProperties().toBuilder()
                                        .build())
                                .build())
                        .build());

        ApplicationContext applicationContext = mock(ApplicationContext.class);
        when(applicationContext.getBean(Node.class)).thenReturn(mock(Node.class));
        when(applicationContext.getBean(GroupMemberService.class))
                .thenReturn(mock(GroupMemberService.class));
        when(applicationContext.getBean(UserRelationshipService.class))
                .thenReturn(mock(UserRelationshipService.class));
        when(applicationContext.getBean(TurmsPropertiesManager.class))
                .thenReturn(turmsPropertiesManager);

        MinioStorageServiceProvider provider = spy(new MinioStorageServiceProvider());
        doReturn(applicationContext).when(provider)
                .getContext();
        doReturn(new MinioStorageProperties().toBuilder()
                .endpoint(testEnvironmentManager.getMinioUri())
                .mongo(new MongoProperties(testEnvironmentManager.getMongoUri()))
                .build()).when(provider)
                .loadProperties(MinioStorageProperties.class);

        StepVerifier.create(provider.start()
                .timeout(TIMEOUT_DURATION))
                .verifyComplete();

        serviceProvider = provider;
    }

    @Order(100)
    @Test
    void test_queryUserProfilePictureUploadInfo() {
        Mono<Map<String, String>> queryUploadInfo = serviceProvider
                .queryUserProfilePictureUploadInfo(USER_ID,
                        null,
                        MediaType.create(MediaTypeConst.IMAGE_PNG),
                        Collections.emptyList())
                .timeout(TIMEOUT_DURATION);
        StepVerifier.create(queryUploadInfo)
                .expectNextMatches(uploadInfo -> {
                    String resourceId = uploadInfo.remove(MinioStorageServiceProvider.RESOURCE_ID);
                    String resourceUploadUrl =
                            uploadInfo.remove(MinioStorageServiceProvider.RESOURCE_URL);
                    assertThat(resourceId).as("The resource ID should not be null")
                            .isNotNull();
                    assertThat(resourceUploadUrl).as("The resource upload URL should not be null")
                            .isNotNull();

                    StepVerifier.create(HttpClient.create()
                            .post()
                            .uri(resourceUploadUrl)
                            .sendForm((request, form) -> {
                                request.requestHeaders()
                                        .remove(HttpHeaderNames.TRANSFER_ENCODING);
                                form.multipart(true);
                                for (Map.Entry<String, String> entry : uploadInfo.entrySet()) {
                                    form.attr(entry.getKey(), entry.getValue());
                                }
                                form.attr("Content-Type", MediaTypeConst.IMAGE_PNG)
                                        .attr("key", resourceId)
                                        .file("file",
                                                resourceId,
                                                new ByteArrayInputStream(FILE_FOR_UPLOADING),
                                                FILE_MEDIA_TYPE);
                            })
                            .responseSingle((response, responseBodyMono) -> {
                                HttpResponseStatus status = response.status();
                                if (status.equals(HttpResponseStatus.NO_CONTENT)) {
                                    return Mono.empty();
                                }
                                return responseBodyMono.asString()
                                        .switchIfEmpty(
                                                Mono.defer(() -> Mono.error(new RuntimeException(
                                                        "The response status code should be 200, but got: "
                                                                + status.code()))))
                                        .flatMap(body -> Mono.error(new RuntimeException(
                                                "The response status code should be 200, but got: "
                                                        + status.code()
                                                        + ". Response body: \""
                                                        + body
                                                        + "\"")));
                            })
                            .timeout(TIMEOUT_DURATION))
                            .verifyComplete();
                    return true;
                })
                .verifyComplete();
    }

    @Order(101)
    @Test
    void test_queryUserProfilePictureDownloadInfo() {
        Mono<Map<String, String>> queryDownloadInfo = serviceProvider
                .queryUserProfilePictureDownloadInfo(USER_ID, USER_ID, Collections.emptyList())
                .timeout(TIMEOUT_DURATION);
        StepVerifier.create(queryDownloadInfo)
                .expectNextMatches(downloadInfo -> {
                    String resourceDownloadUrl =
                            downloadInfo.get(MinioStorageServiceProvider.RESOURCE_URL);
                    assertThat(resourceDownloadUrl)
                            .as("The resource download URL should not be null")
                            .isNotNull();

                    StepVerifier.create(HttpClient.create()
                            .get()
                            .uri(resourceDownloadUrl)
                            .responseSingle((response, responseBodyMono) -> {
                                HttpResponseStatus status = response.status();
                                if (status.equals(HttpResponseStatus.OK)) {
                                    return responseBodyMono.asByteArray()
                                            .switchIfEmpty(Mono
                                                    .defer(() -> Mono.error(new RuntimeException(
                                                            "The downloaded file should be the same with the upload file"))))
                                            .flatMap(bytes -> {
                                                if (Arrays.equals(bytes, FILE_FOR_UPLOADING)) {
                                                    return Mono.empty();
                                                }
                                                return Mono.error(new RuntimeException(
                                                        "The downloaded file should be the same with the upload file"));
                                            });
                                }
                                return responseBodyMono.asString()
                                        .switchIfEmpty(
                                                Mono.defer(() -> Mono.error(new RuntimeException(
                                                        "The response status code should be 200, but got: "
                                                                + status.code()))))
                                        .flatMap(body -> Mono.error(new RuntimeException(
                                                "The response status code should be 200, but got: "
                                                        + status.code()
                                                        + ". Response body: \""
                                                        + body
                                                        + "\"")));
                            })
                            .timeout(TIMEOUT_DURATION))
                            .verifyComplete();
                    return true;
                })
                .verifyComplete();
    }

    @Order(102)
    @Test
    void test_deleteUserProfilePicture() {
        Mono<Void> deleteUserProfilePicture =
                serviceProvider.deleteUserProfilePicture(USER_ID, Collections.emptyList())
                        .timeout(TIMEOUT_DURATION);
        StepVerifier.create(deleteUserProfilePicture)
                .verifyComplete();

        Mono<Map<String, String>> queryDownloadInfo = serviceProvider
                .queryUserProfilePictureDownloadInfo(USER_ID, USER_ID, Collections.emptyList())
                .timeout(TIMEOUT_DURATION);
        StepVerifier.create(queryDownloadInfo)
                .expectNextMatches(downloadInfo -> {
                    String resourceDownloadUrl =
                            downloadInfo.get(MinioStorageServiceProvider.RESOURCE_URL);
                    assertThat(resourceDownloadUrl)
                            .as("The resource download URL should not be null")
                            .isNotNull();

                    StepVerifier.create(HttpClient.create()
                            .get()
                            .uri(resourceDownloadUrl)
                            .response()
                            .timeout(TIMEOUT_DURATION))
                            .expectNextMatches(response -> {
                                assertThat(response.status())
                                        .isEqualTo(HttpResponseStatus.NOT_FOUND);
                                return true;
                            })
                            .as("The deleted file should not be found")
                            .verifyComplete();
                    return true;
                })
                .verifyComplete();
    }

}
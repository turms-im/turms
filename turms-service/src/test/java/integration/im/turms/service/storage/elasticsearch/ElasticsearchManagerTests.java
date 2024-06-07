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

package integration.im.turms.service.storage.elasticsearch;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import helper.SpringAwareIntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.ServiceProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchClientProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchGroupUseCaseProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchIndexProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchMongoProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchUseCasesProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchUserUseCaseProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.LanguageCode;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.TurmsElasticsearchProperties;
import im.turms.server.common.testing.environment.ServiceTestEnvironmentType;
import im.turms.server.common.testing.properties.ElasticsearchTestEnvironmentProperties;
import im.turms.server.common.testing.properties.TestProperties;
import im.turms.service.domain.group.po.Group;
import im.turms.service.domain.group.repository.GroupRepository;
import im.turms.service.domain.user.repository.UserRepository;
import im.turms.service.storage.elasticsearch.ElasticsearchManager;
import im.turms.service.storage.elasticsearch.model.Hit;
import im.turms.service.storage.elasticsearch.model.SearchResponse;
import im.turms.service.storage.elasticsearch.model.doc.UserDoc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class ElasticsearchManagerTests extends SpringAwareIntegrationTest {

    private static final List<String> NAMES = List.of("hello world", "你好，世界", "こんにちは、世界");
    private static final List<TestCase> TEST_CASES = List.of(new TestCase(
            "world",
            List.of(new ExpectedHit("hello world", formatExpectedHighlight("hello {0}world{1}")))),
            new TestCase(
                    "世界",
                    List.of(new ExpectedHit("你好，世界", formatExpectedHighlight("你好，{0}世界{1}")),
                            new ExpectedHit(
                                    "こんにちは、世界",
                                    formatExpectedHighlight("こんにちは、{0}世界{1}")))),
            new TestCase(
                    "こんにちは",
                    List.of(new ExpectedHit(
                            "こんにちは、世界",
                            // TODO: The expected result should be:
                            // "{0}こんにちは{1}、世界"
                            formatExpectedHighlight("{0}こ{1}{0}ん{1}{0}に{1}{0}ち{1}{0}は{1}、世界")))));

    @BeforeAll
    static void setup() {
        setupTestEnvironment(new TestProperties().toBuilder()
                .elasticsearch(new ElasticsearchTestEnvironmentProperties().toBuilder()
                        .type(ServiceTestEnvironmentType.CONTAINER)
                        .build())
                .build());
    }

    @Test
    void test() throws InterruptedException {
        ElasticsearchManager elasticsearchManager = newElasticsearchManager();

        waitForElasticsearch();

        for (TestCase testCase : TEST_CASES) {
            Mono<SearchResponse<UserDoc>> searchedUserDocs = elasticsearchManager
                    .searchUserDocs(null, null, testCase.searchText, null, true, null, null);
            StepVerifier.create(searchedUserDocs)
                    .expectNextMatches(response -> {
                        List<Hit<UserDoc>> hits = response.hits()
                                .hits();
                        List<ExpectedHit> expectedHits = testCase.expectedHits;
                        assertThat(hits).hasSize(expectedHits.size());
                        for (int i = 0,
                                expectedHitCount = expectedHits.size(); i < expectedHitCount; i++) {
                            ExpectedHit expectedHit = expectedHits.get(i);
                            Hit<UserDoc> hit = hits.get(i);
                            assertThat(hit.source()
                                    .getName()).isEqualTo(expectedHit.name);
                            assertThat(hit.highlight()
                                    .values()
                                    .iterator()
                                    .next()
                                    .getFirst()).isEqualTo(expectedHit.highlight);
                        }
                        return true;
                    })
                    .verifyComplete();
        }

        long userId = 2000L;
        String name = "test";
        Mono<Void> putUserDoc = elasticsearchManager.putUserDoc(userId, name);
        StepVerifier.create(putUserDoc)
                .verifyComplete();

        waitForElasticsearch();

        Mono<SearchResponse<UserDoc>> searchedUserDocs =
                elasticsearchManager.searchUserDocs(null, null, name, null, true, null, null);
        StepVerifier.create(searchedUserDocs)
                .expectNextMatches(response -> {
                    List<Hit<UserDoc>> hits = response.hits()
                            .hits();
                    assertThat(hits).hasSize(1);
                    Hit<UserDoc> hit = hits.getFirst();
                    assertThat(hit.id()).isEqualTo(Long.toString(userId));
                    assertThat(hit.source()
                            .getName()).isEqualTo(name);
                    assertThat(hit.highlight()).isNotEmpty();
                    return true;
                })
                .verifyComplete();
    }

    private static ElasticsearchManager newElasticsearchManager() {
        Node node = mock(Node.class);
        when(node.isLocalNodeLeader()).thenReturn(true);
        var idRef = new Object() {
            long id = 1L;
        };
        when(node.getLocalMemberId()).thenReturn("fake-node-id");
        when(node.nextIncreasingId(any())).then(invocation -> idRef.id++);

        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        ElasticsearchClientProperties elasticsearchClientProperties =
                ElasticsearchClientProperties.builder()
                        .uri(testEnvironmentManager.getElasticsearchUri())
                        .build();
        ElasticsearchMongoProperties mongoProperties =
                new ElasticsearchMongoProperties().toBuilder()
                        .uri(testEnvironmentManager.getMongoUri())
                        .build();
        when(propertiesManager.getLocalProperties()).thenReturn(new TurmsProperties().toBuilder()
                .service(new ServiceProperties().toBuilder()
                        .elasticsearch(new TurmsElasticsearchProperties().toBuilder()
                                .enabled(true)
                                .useCase(new ElasticsearchUseCasesProperties().toBuilder()
                                        .user(new ElasticsearchUserUseCaseProperties().toBuilder()
                                                .mongo(mongoProperties)
                                                .client(elasticsearchClientProperties)
                                                .indexes(List.of(
                                                        new ElasticsearchIndexProperties()
                                                                .toBuilder()
                                                                .code(LanguageCode.NONE)
                                                                .build(),
                                                        new ElasticsearchIndexProperties()
                                                                .toBuilder()
                                                                .code(LanguageCode.JA)
                                                                .build(),
                                                        new ElasticsearchIndexProperties()
                                                                .toBuilder()
                                                                .code(LanguageCode.ZH)
                                                                .build()))
                                                .build())
                                        .group(new ElasticsearchGroupUseCaseProperties().toBuilder()
                                                .mongo(mongoProperties)
                                                .client(elasticsearchClientProperties)
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build());

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findAllNames())
                .thenReturn(Flux.fromIterable(IntStream.range(0, NAMES.size())
                        .mapToObj(i -> buildUser(i, NAMES.get(i)))
                        .collect(Collectors.toList())));

        GroupRepository groupRepository = mock(GroupRepository.class);
        when(groupRepository.findAllNames())
                .thenReturn(Flux.fromIterable(IntStream.range(0, NAMES.size())
                        .mapToObj(i -> buildGroup(i, NAMES.get(i)))
                        .collect(Collectors.toList())));

        return new ElasticsearchManager(node, propertiesManager, userRepository, groupRepository);
    }

    private static User buildUser(long id, String name) {
        return new User(id, null, name, null, null, null, null, null, null, null, null);
    }

    private static Group buildGroup(long id, String name) {
        return new Group(
                id,
                null,
                null,
                null,
                name,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    private static String formatExpectedHighlight(String pattern) {
        return MessageFormat
                .format(pattern, ElasticsearchManager.PRE_TAG, ElasticsearchManager.POST_TAG);
    }

    private static void waitForElasticsearch() throws InterruptedException {
        Thread.sleep(1000);
    }

    private record TestCase(
            String searchText,
            List<ExpectedHit> expectedHits
    ) {
    }

    private record ExpectedHit(
            String name,
            String highlight
    ) {
    }
}
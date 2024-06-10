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

package im.turms.service.storage.elasticsearch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectReader;
import io.netty.handler.codec.http.HttpHeaderNames;
import lombok.Getter;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.codec.Base64Util;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.json.JsonUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchClientProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchGroupUseCaseProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchIndexProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchIndexPropertiesFieldProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchIndexTextFieldProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchUseCasesProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.ElasticsearchUserUseCaseProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.HttpHeaderProperties;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.LanguageCode;
import im.turms.server.common.infra.property.env.service.env.elasticsearch.TurmsElasticsearchProperties;
import im.turms.server.common.infra.time.DurationConst;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.exception.DuplicateKeyException;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.group.po.Group;
import im.turms.service.domain.group.repository.GroupRepository;
import im.turms.service.domain.user.repository.UserRepository;
import im.turms.service.storage.elasticsearch.model.BulkRequest;
import im.turms.service.storage.elasticsearch.model.CreateIndexRequest;
import im.turms.service.storage.elasticsearch.model.DeleteByQueryRequest;
import im.turms.service.storage.elasticsearch.model.DeleteByQueryResponse;
import im.turms.service.storage.elasticsearch.model.DeleteResponse;
import im.turms.service.storage.elasticsearch.model.DynamicMapping;
import im.turms.service.storage.elasticsearch.model.ErrorResponse;
import im.turms.service.storage.elasticsearch.model.FieldCollapse;
import im.turms.service.storage.elasticsearch.model.HealthStatus;
import im.turms.service.storage.elasticsearch.model.Highlight;
import im.turms.service.storage.elasticsearch.model.IndexSettings;
import im.turms.service.storage.elasticsearch.model.IndexSettingsAnalysis;
import im.turms.service.storage.elasticsearch.model.OperationType;
import im.turms.service.storage.elasticsearch.model.PointInTimeReference;
import im.turms.service.storage.elasticsearch.model.Property;
import im.turms.service.storage.elasticsearch.model.Script;
import im.turms.service.storage.elasticsearch.model.SearchRequest;
import im.turms.service.storage.elasticsearch.model.SearchResponse;
import im.turms.service.storage.elasticsearch.model.TypeMapping;
import im.turms.service.storage.elasticsearch.model.UpdateByQueryRequest;
import im.turms.service.storage.elasticsearch.model.UpdateByQueryResponse;
import im.turms.service.storage.elasticsearch.model.doc.BaseDoc;
import im.turms.service.storage.elasticsearch.model.doc.GroupDoc;
import im.turms.service.storage.elasticsearch.model.doc.UserDoc;
import im.turms.service.storage.elasticsearch.mongo.SyncLog;
import im.turms.service.storage.elasticsearch.mongo.SyncStatus;

/**
 * @author James Chen
 */
@Component
public class ElasticsearchManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchManager.class);

    private static final String USER_INDEX = "turms_user";
    private static final String GROUP_INDEX = "turms_group";

    private static final int SYNC_BATCH_SIZE = 1000;

    public static final String PRE_TAG = "\u0002";
    public static final String POST_TAG = "\u0003";

    private static final Highlight HIGHLIGHT_NAME = new Highlight(
            Map.of(BaseDoc.Fields.NAME
                    + "*", Collections.emptyMap()),
            List.of(PRE_TAG),
            List.of(POST_TAG),
            "plain",
            // Use 0 so that Elasticsearch won't trim our tags.
            0);

    private static final ObjectReader READER_USER_DOC =
            ElasticsearchClient.READER.forType(new TypeReference<SearchResponse<UserDoc>>() {
            });
    private static final ObjectReader READER_GROUP_DOC =
            ElasticsearchClient.READER.forType(new TypeReference<SearchResponse<GroupDoc>>() {
            });
    public static final FieldCollapse COLLAPSE_ID = new FieldCollapse(BaseDoc.Fields.ID);

    private final ElasticsearchClient elasticsearchClientForUserDocs;
    private final ElasticsearchClient elasticsearchClientForGroupDocs;
    private final boolean shareClient;

    @Getter
    private final boolean isUserUseCaseEnabled;
    @Getter
    private final boolean isGroupUseCaseEnabled;
    @Getter
    private final boolean isTransactionWithMongoEnabledForUser;
    @Getter
    private final boolean isTransactionWithMongoEnabledForGroup;
    private List<String> allIndexesForUserDocs = Collections.emptyList();
    private List<String> allIndexesForGroupDocs = Collections.emptyList();
    private boolean onlyOneIndexForUserDocs;
    private boolean onlyOneIndexForGroupDocs;

    private final Node node;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public ElasticsearchManager(
            Node node,
            TurmsPropertiesManager propertiesManager,
            UserRepository userRepository,
            GroupRepository groupRepository) {
        this.node = node;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;

        TurmsElasticsearchProperties properties = propertiesManager.getLocalProperties()
                .getService()
                .getElasticsearch();

        ElasticsearchUseCasesProperties useCasesProperties = properties.getUseCase();
        ElasticsearchUserUseCaseProperties userUseCaseProperties = useCasesProperties.getUser();
        ElasticsearchGroupUseCaseProperties groupUseCaseProperties = useCasesProperties.getGroup();
        boolean enabled = properties.isEnabled();
        isUserUseCaseEnabled = enabled && userUseCaseProperties.isEnabled();
        isGroupUseCaseEnabled = enabled && groupUseCaseProperties.isEnabled();
        isTransactionWithMongoEnabledForUser = isUserUseCaseEnabled
                && userUseCaseProperties.getMongo()
                        .isEnableTransaction();
        isTransactionWithMongoEnabledForGroup = isGroupUseCaseEnabled
                && groupUseCaseProperties.getMongo()
                        .isEnableTransaction();

        if (!isUserUseCaseEnabled && !isGroupUseCaseEnabled) {
            elasticsearchClientForUserDocs = null;
            elasticsearchClientForGroupDocs = null;
            shareClient = false;
            return;
        }

        ElasticsearchClientProperties elasticsearchClientForGroupProperties =
                groupUseCaseProperties.getClient();
        if (isUserUseCaseEnabled) {
            ElasticsearchClientProperties elasticsearchClientForUserProperties =
                    userUseCaseProperties.getClient();
            elasticsearchClientForUserDocs =
                    initElasticsearchClient(elasticsearchClientForUserProperties);
            if (isGroupUseCaseEnabled) {
                if (elasticsearchClientForUserProperties
                        .equals(elasticsearchClientForGroupProperties)) {
                    elasticsearchClientForGroupDocs = elasticsearchClientForUserDocs;
                    shareClient = true;
                } else {
                    elasticsearchClientForGroupDocs =
                            initElasticsearchClient(elasticsearchClientForGroupProperties);
                    shareClient = false;
                }
            } else {
                elasticsearchClientForGroupDocs = null;
                shareClient = false;
            }
        } else if (isGroupUseCaseEnabled) {
            elasticsearchClientForUserDocs = null;
            elasticsearchClientForGroupDocs =
                    initElasticsearchClient(elasticsearchClientForGroupProperties);
            shareClient = false;
        } else {
            elasticsearchClientForUserDocs = null;
            elasticsearchClientForGroupDocs = null;
            shareClient = false;
        }

        init(userUseCaseProperties, groupUseCaseProperties).blockLast(DurationConst.FIVE_MINUTES);
    }

    private ElasticsearchClient initElasticsearchClient(ElasticsearchClientProperties properties) {
        String uri = properties.getUri();
        List<HttpHeaderProperties> requestHeaders = properties.getRequestHeaders();
        HttpClient httpClient = HttpClient.create()
                .baseUrl(uri)
                .headers(entries -> entries.add(HttpHeaderNames.CONTENT_TYPE, "application/json"));
        String username = properties.getUsername();
        String password = properties.getPassword();
        boolean hasUsername = StringUtil.isNotBlank(username);
        boolean hasPassword = StringUtil.isNotBlank(password);
        boolean hasRequestHeaders = CollectionUtil.isNotEmpty(requestHeaders);
        if (hasUsername || hasPassword || hasRequestHeaders) {
            httpClient = httpClient.headers(headers -> {
                if (hasUsername || hasPassword) {
                    headers.add("Authorization",
                            "Basic "
                                    + Base64Util.encodeToString((hasUsername
                                            ? username
                                            : "")
                                            + ":"
                                            + (hasPassword
                                                    ? password
                                                    : "")));
                }
                if (hasRequestHeaders) {
                    for (HttpHeaderProperties requestHeader : requestHeaders) {
                        headers.add(requestHeader.getName(), requestHeader.getValue());
                    }
                }
            });
        }
        return new ElasticsearchClient(httpClient);
    }

    private Flux<Void> init(
            ElasticsearchUserUseCaseProperties userUseCaseProperties,
            ElasticsearchGroupUseCaseProperties groupUseCaseProperties) {
        List<Mono<Void>> jobs = List.of(ensureHealthy(),
                createIndexes(userUseCaseProperties, groupUseCaseProperties),
                fullSyncIfEnabled(userUseCaseProperties, groupUseCaseProperties));
        return Flux.concat(jobs);
    }

    private Mono<Void> fullSyncIfEnabled(
            ElasticsearchUserUseCaseProperties userUseCaseProperties,
            ElasticsearchGroupUseCaseProperties groupUseCaseProperties) {
        boolean isUserDocsFullSyncEnabled = isUserUseCaseEnabled
                && userUseCaseProperties.getSync()
                        .isPerformFullSyncAtStartup();
        boolean isGroupDocsFullSyncEnabled = isGroupUseCaseEnabled
                && groupUseCaseProperties.getSync()
                        .isPerformFullSyncAtStartup();
        if (!isUserDocsFullSyncEnabled && !isGroupDocsFullSyncEnabled) {
            return Mono.empty();
        }
        if (!node.isLocalNodeLeader()) {
            LOGGER.info("Skip the full sync because this node is not the cluster leader");
            return Mono.empty();
        }
        if (shareClient) {
            return TurmsMongoClient.of(userUseCaseProperties.getMongo(), "elasticsearch")
                    .flatMap(mongoClient -> {
                        mongoClient.registerEntitiesByClasses(SyncLog.class);
                        return mongoClient.createCollectionIfNotExists(SyncLog.class)
                                .then(mongoClient.findMany(SyncLog.class,
                                        Filter.newBuilder(1)
                                                .inIfNotNullForEnumStrings(SyncLog.Fields.status,
                                                        List.of(SyncStatus.IN_PROGRESS,
                                                                SyncStatus.COMPLETED)))
                                        .collect(CollectorUtil.toChunkedList())
                                        .flatMap(syncLogs -> performFullSyncs(mongoClient,
                                                syncLogs,
                                                isUserDocsFullSyncEnabled,
                                                isGroupDocsFullSyncEnabled)));
                    });
        }

        Mono<Void> performFullSyncForUserDocs = Mono.defer(() -> TurmsMongoClient
                .of(userUseCaseProperties.getMongo(), "elasticsearch-for-user-docs")
                .flatMap(mongoClient -> {
                    mongoClient.registerEntitiesByClasses(SyncLog.class);
                    return mongoClient.createCollectionIfNotExists(SyncLog.class)
                            .then(mongoClient.findMany(SyncLog.class,
                                    Filter.newBuilder(1)
                                            .inIfNotNullForEnumStrings(SyncLog.Fields.status,
                                                    List.of(SyncStatus.IN_PROGRESS,
                                                            SyncStatus.COMPLETED)))
                                    .collect(CollectorUtil.toChunkedList())
                                    .flatMap(syncLogs -> performFullSyncs(mongoClient,
                                            syncLogs,
                                            true,
                                            false)));
                }));
        Mono<Void> performFullSyncForGroupDocs = Mono.defer(() -> TurmsMongoClient
                .of(groupUseCaseProperties.getMongo(), "elasticsearch-for-group-docs")
                .flatMap(mongoClient -> {
                    mongoClient.registerEntitiesByClasses(SyncLog.class);
                    return mongoClient.createCollectionIfNotExists(SyncLog.class)
                            .then(mongoClient.findMany(SyncLog.class,
                                    Filter.newBuilder(1)
                                            .inIfNotNullForEnumStrings(SyncLog.Fields.status,
                                                    List.of(SyncStatus.IN_PROGRESS,
                                                            SyncStatus.COMPLETED)))
                                    .collect(CollectorUtil.toChunkedList())
                                    .flatMap(syncLogs -> performFullSyncs(mongoClient,
                                            syncLogs,
                                            false,
                                            true)));
                }));
        if (isUserDocsFullSyncEnabled && isGroupDocsFullSyncEnabled) {
            return performFullSyncForUserDocs.then(performFullSyncForGroupDocs);
        } else if (isUserDocsFullSyncEnabled) {
            return performFullSyncForUserDocs;
        } else {
            return performFullSyncForGroupDocs;
        }
    }

    @NotNull
    private Mono<Void> performFullSyncs(
            TurmsMongoClient mongoClient,
            List<SyncLog> syncLogs,
            boolean isUserFullSyncEnabled,
            boolean isGroupFullSyncEnabled) {
        Map<String, Set<String>> syncedOrSyncingCollectionToIndexes;
        if (syncLogs.isEmpty()) {
            syncedOrSyncingCollectionToIndexes = Collections.emptyMap();
        } else {
            syncedOrSyncingCollectionToIndexes =
                    CollectionUtil.newMapWithExpectedSize(syncLogs.size());
            for (SyncLog syncLog : syncLogs) {
                syncedOrSyncingCollectionToIndexes
                        .computeIfAbsent(syncLog.getMongoCollection(), k -> new UnifiedSet<>(8))
                        .add(syncLog.getEsIndex());
            }
        }
        List<Mono<Void>> syncJobs = new ArrayList<>(2);
        Date now = new Date();
        if (isUserFullSyncEnabled) {
            syncJobs.add(performFullSyncForUserDocs(mongoClient,
                    syncedOrSyncingCollectionToIndexes,
                    now));
        }
        if (isGroupFullSyncEnabled) {
            syncJobs.add(performFullSyncForGroupDocs(mongoClient,
                    syncedOrSyncingCollectionToIndexes,
                    now));
        }
        return Flux.concat(syncJobs)
                .then();
    }

    private Mono<Void> performFullSyncForUserDocs(
            TurmsMongoClient mongoClient,
            Map<String, Set<String>> syncedOrSyncingCollectionToIndexes,
            Date now) {
        List<String> indexesForSync = new ArrayList<>(allIndexesForUserDocs);
        indexesForSync.removeAll(syncedOrSyncingCollectionToIndexes
                .getOrDefault(User.COLLECTION_NAME, Collections.emptySet()));
        if (indexesForSync.isEmpty()) {
            // TODO: check if the initiator node is still alive because it may have crashed
            // and leaved the dirty syncing status.
            LOGGER.info("Skip syncing user docs because they are already synced or syncing");
            return Mono.empty();
        }
        LOGGER.info("Start syncing user docs to the indexes: "
                + indexesForSync);
        // TODO: fetch as batches
        return userRepository.findAllNames()
                .collect(CollectorUtil.toChunkedList())
                .flatMap(users -> {
                    List<Mono<Void>> syncs = new ArrayList<>(indexesForSync.size());
                    for (String index : indexesForSync) {
                        syncs.add(performFullSync(mongoClient,
                                now,
                                User.COLLECTION_NAME,
                                index,
                                () -> fullSync(users, batch -> {
                                    List<Object> operations = new ArrayList<>(batch.size() << 1);
                                    for (User user : batch) {
                                        Long id = user.getId();
                                        operations.add(Map.of(OperationType.INDEX,
                                                Map.of("_index", index, "_id", id)));
                                        operations.add(new UserDoc(id, user.getName()));
                                    }
                                    return elasticsearchClientForUserDocs
                                            .bulk(new BulkRequest(operations))
                                            .flatMap(bulkResponse -> {
                                                if (bulkResponse.errors()) {
                                                    return Mono.error(new RuntimeException(
                                                            "Failed to index user docs: "
                                                                    + bulkResponse));
                                                }
                                                LOGGER.info("Indexed user docs: {}", bulkResponse);
                                                return Mono.empty();
                                            })
                                            .then();
                                })));
                    }
                    return Flux.concat(syncs)
                            .then();
                });
    }

    private Mono<Void> performFullSyncForGroupDocs(
            TurmsMongoClient mongoClient,
            Map<String, Set<String>> syncedOrSyncingCollectionToIndexes,
            Date now) {
        List<String> indexesForSync = new ArrayList<>(allIndexesForGroupDocs);
        indexesForSync.removeAll(syncedOrSyncingCollectionToIndexes
                .getOrDefault(Group.COLLECTION_NAME, Collections.emptySet()));
        if (indexesForSync.isEmpty()) {
            LOGGER.info("Skip syncing group docs because they are already synced or syncing");
            return Mono.empty();
        }
        LOGGER.info("Start syncing group docs to the indexes: "
                + indexesForSync);
        return groupRepository.findAllNames()
                .collect(CollectorUtil.toChunkedList())
                .flatMap(groups -> {
                    List<Mono<Void>> syncs = new ArrayList<>(indexesForSync.size());
                    for (String index : indexesForSync) {
                        syncs.add(performFullSync(mongoClient,
                                now,
                                Group.COLLECTION_NAME,
                                index,
                                () -> fullSync(groups, batch -> {
                                    List<Object> operations = new ArrayList<>(batch.size() << 1);
                                    for (Group group : batch) {
                                        Long id = group.getId();
                                        operations.add(Map.of(OperationType.INDEX,
                                                Map.of("_index", index, "_id", id)));
                                        operations.add(new GroupDoc(id, group.getName()));
                                    }
                                    return elasticsearchClientForGroupDocs
                                            .bulk(new BulkRequest(operations))
                                            .flatMap(bulkResponse -> {
                                                if (bulkResponse.errors()) {
                                                    return Mono.error(new RuntimeException(
                                                            "Failed to index group docs: "
                                                                    + bulkResponse));
                                                }
                                                LOGGER.info("Indexed group docs: {}", bulkResponse);
                                                return Mono.empty();
                                            })
                                            .then();
                                })));
                    }
                    return Flux.concat(syncs)
                            .then();
                });
    }

    private Mono<Void> performFullSync(
            TurmsMongoClient mongoClient,
            Date creationDate,
            String mongoCollection,
            String esIndex,
            Supplier<Mono<Void>> sync) {
        return insertSyncLog(mongoClient, creationDate, mongoCollection, esIndex)
                .onErrorMap(t -> new RuntimeException("Failed to perform user full sync", t))
                .flatMap(logId -> sync.get()
                        .onErrorResume(t -> mongoClient
                                .updateOne(SyncLog.class,
                                        Filter.newBuilder(1)
                                                .eq(DomainFieldName.ID, logId),
                                        Update.newBuilder(2)
                                                .setEnumString(SyncLog.Fields.status,
                                                        SyncStatus.FAILED)
                                                .set(SyncLog.Fields.lastUpdatedDate, new Date()))
                                .materialize()
                                .flatMap(signal -> {
                                    Throwable updateSyncLogThrowable = signal.getThrowable();
                                    if (updateSyncLogThrowable != null) {
                                        t.addSuppressed(new RuntimeException(
                                                "Failed to update the sync log: "
                                                        + logId,
                                                updateSyncLogThrowable));
                                    }
                                    return Mono.error(new RuntimeException(
                                            "Failed to perform user full sync",
                                            t));
                                }))
                        .then(Mono.defer(() -> mongoClient
                                .updateOne(SyncLog.class,
                                        Filter.newBuilder(1)
                                                .eq(DomainFieldName.ID, logId),
                                        Update.newBuilder(2)
                                                .setEnumString(SyncLog.Fields.status,
                                                        SyncStatus.COMPLETED)
                                                .set(SyncLog.Fields.lastUpdatedDate, new Date()))
                                .onErrorMap(t -> new RuntimeException(
                                        "Failed to update the sync log: "
                                                + logId,
                                        t))
                                .then())));
    }

    private <T> Mono<Void> fullSync(List<T> records, Function<List<T>, Mono<Void>> batchHandler) {
        if (records.isEmpty()) {
            return Mono.empty();
        }
        int size = records.size();
        int startIndex = 0;
        int count = size / SYNC_BATCH_SIZE + (size % SYNC_BATCH_SIZE == 0
                ? 0
                : 1);
        List<Mono<Void>> inserts = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            List<T> batch =
                    records.subList(startIndex, Math.min(size, startIndex + SYNC_BATCH_SIZE));
            inserts.add(batchHandler.apply(batch));
            startIndex += SYNC_BATCH_SIZE;
        }
        return Flux.concat(inserts)
                .then();
    }

    private Mono<Long> insertSyncLog(
            TurmsMongoClient mongoClient,
            Date creationDate,
            String mongoCollection,
            String esIndex) {
        long logId = node.nextIncreasingId(ServiceType.ELASTICSEARCH_SYNC_LOG);
        return mongoClient
                .insert(new SyncLog(
                        logId,
                        node.getLocalMemberId(),
                        mongoCollection,
                        esIndex,
                        SyncStatus.IN_PROGRESS,
                        creationDate,
                        creationDate,
                        0))
                .thenReturn(logId)
                .onErrorResume(DuplicateKeyException.class,
                        e -> insertSyncLog(mongoClient, creationDate, mongoCollection, esIndex));
    }

    private Mono<Void> ensureHealthy() {
        if (shareClient) {
            return elasticsearchClientForUserDocs.healthcheck()
                    .flatMap(healthResponse -> {
                        HealthStatus status = healthResponse.status();
                        if (HealthStatus.GREEN != status && HealthStatus.YELLOW != status) {
                            return Mono.error(new IllegalStateException(
                                    "Health check failed. Expected: GREEN or YELLOW. Actual: "
                                            + status));
                        }
                        return Mono.empty();
                    });
        }
        Mono<Object> healthcheckForUserDocs = isUserUseCaseEnabled
                ? elasticsearchClientForUserDocs.healthcheck()
                        .flatMap(healthResponse -> {
                            HealthStatus status = healthResponse.status();
                            if (HealthStatus.GREEN != status && HealthStatus.YELLOW != status) {
                                return Mono.error(new IllegalStateException(
                                        "Health check failed for user docs. Expected: GREEN or YELLOW. Actual: "
                                                + status));
                            }
                            return Mono.empty();
                        })
                : Mono.empty();
        Mono<Object> healthcheckForGroupDocs = isGroupUseCaseEnabled
                ? elasticsearchClientForGroupDocs.healthcheck()
                        .flatMap(healthResponse -> {
                            HealthStatus status = healthResponse.status();
                            if (HealthStatus.GREEN != status && HealthStatus.YELLOW != status) {
                                return Mono.error(new IllegalStateException(
                                        "Health check failed for group docs. Expected: GREEN or YELLOW. Actual: "
                                                + status));
                            }
                            return Mono.empty();
                        })
                : Mono.empty();
        return Mono.when(healthcheckForUserDocs, healthcheckForGroupDocs);
    }

    private Mono<Void> createIndexes(
            ElasticsearchUserUseCaseProperties userUseCaseProperties,
            ElasticsearchGroupUseCaseProperties groupUseCaseProperties) {
        return Mono.when(isUserUseCaseEnabled
                ? createIndexes(elasticsearchClientForUserDocs,
                        userUseCaseProperties.getIndexes(),
                        USER_INDEX).doOnSuccess(indexes -> {
                            allIndexesForUserDocs = indexes;
                            onlyOneIndexForUserDocs = indexes.size() == 1;
                        })
                : Mono.empty(),
                isGroupUseCaseEnabled
                        ? createIndexes(elasticsearchClientForGroupDocs,
                                groupUseCaseProperties.getIndexes(),
                                GROUP_INDEX).doOnSuccess(indexes -> {
                                    allIndexesForGroupDocs = indexes;
                                    onlyOneIndexForGroupDocs = indexes.size() == 1;
                                })
                        : Mono.empty());
    }

    private Mono<List<String>> createIndexes(
            ElasticsearchClient elasticsearchClient,
            List<ElasticsearchIndexProperties> indexPropertiesList,
            String indexPrefix) {
        int indexCount = indexPropertiesList.size();
        List<Mono<String>> createIndexes = new ArrayList<>(indexCount);
        Set<LanguageCode> codes = CollectionUtil.newSetWithExpectedSize(indexCount);
        for (ElasticsearchIndexProperties indexProperties : indexPropertiesList) {
            LanguageCode code = indexProperties.getCode();
            if (!codes.add(code)) {
                return Mono.error(new IllegalArgumentException(
                        "Duplicate index properties for the language code: "
                                + code));
            }
        }
        for (ElasticsearchIndexProperties indexProperties : indexPropertiesList) {
            createIndexes.add(createIndex(elasticsearchClient, indexPrefix, indexProperties));
        }
        return Flux.merge(createIndexes)
                .collect(CollectorUtil.toList(indexCount));
    }

    private static Mono<String> createIndex(
            ElasticsearchClient elasticsearchClient,
            String indexPrefix,
            ElasticsearchIndexProperties indexProperties) {
        LanguageCode code = indexProperties.getCode();
        int tempNumberOfShards = indexProperties.getNumberOfShards();
        int tempNumberOfReplicas = indexProperties.getNumberOfReplicas();
        Integer numberOfShards = tempNumberOfShards >= 0
                ? tempNumberOfShards
                : null;
        Integer numberOfReplicas = tempNumberOfReplicas >= 0
                ? tempNumberOfReplicas
                : null;
        ElasticsearchIndexPropertiesFieldProperties nameFieldProperties =
                indexProperties.getProperties()
                        .getName();
        List<ElasticsearchIndexTextFieldProperties> nameTextFieldsPropertiesList =
                nameFieldProperties.getTextFields();

        IndexSettingsAnalysis indexSettingsAnalysis = null;

        String index = indexPrefix;
        Map<String, Property> fieldToProperty =
                CollectionUtil.newMapWithExpectedSize(nameTextFieldsPropertiesList.size());

        for (ElasticsearchIndexTextFieldProperties textFieldProperties : nameTextFieldsPropertiesList) {
            String fieldName = textFieldProperties.getFieldName();
            String analyzer = textFieldProperties.getAnalyzer();
            String searchAnalyzer = textFieldProperties.getSearchAnalyzer();
            if (code != LanguageCode.NONE) {
                index = indexPrefix
                        + "-"
                        + code.getCanonicalCode();
            }
            if (StringUtil.isBlank(analyzer)) {
                if (StringUtil.isNotBlank(searchAnalyzer)) {
                    throw new IllegalArgumentException(
                            "The search analyzer must be blank if the analyzer is blank. "
                                    + "The language code: "
                                    + code);
                }
                IndexTextFieldSetting indexTextFieldSetting =
                        DefaultLanguageSettings.getSetting(code);
                if (indexTextFieldSetting == null) {
                    indexTextFieldSetting = DefaultLanguageSettings.DEFAULT;
                }
                IndexSettingsAnalysis analysis = indexTextFieldSetting.analysis();
                if (analysis != null) {
                    indexSettingsAnalysis = indexSettingsAnalysis == null
                            ? analysis
                            : indexSettingsAnalysis.merge(analysis);
                }
                fieldToProperty.putAll(indexTextFieldSetting.fieldToProperty());
            } else {
                Property property =
                        new Property(Property.Type.TEXT, analyzer, searchAnalyzer, null);
                fieldToProperty.put(fieldName, property);
            }
        }

        CreateIndexRequest request = new CreateIndexRequest(
                new TypeMapping(
                        DynamicMapping.STRICT,
                        Map.of(BaseDoc.Fields.ID,
                                new Property(Property.Type.KEYWORD, null, null, null),
                                BaseDoc.Fields.NAME,
                                new Property(Property.Type.KEYWORD, null, null, fieldToProperty))),
                new IndexSettings(
                        new IndexSettings(
                                null,
                                numberOfShards,
                                numberOfReplicas,
                                null,
                                indexSettingsAnalysis),
                        null,
                        null,
                        null,
                        null));
        String finalIndex = index;
        return elasticsearchClient.putIndex(index, request)
                .doOnSuccess(unused -> LOGGER.info("Created an index: \""
                        + finalIndex
                        + "\""))
                .onErrorResume(ErrorResponseException.class, e -> {
                    ErrorResponse errorResponse = e.getErrorResponse();
                    if ("resource_already_exists_exception".equals(errorResponse.error()
                            .type())) {
                        LOGGER.info("The index already exists: \""
                                + finalIndex
                                + "\"");
                        return Mono.empty();
                    }
                    return Mono.error(e);
                })
                .thenReturn(finalIndex);
    }

    public Mono<Void> putUserDoc(Long userId, String name) {
        if (onlyOneIndexForUserDocs) {
            return elasticsearchClientForUserDocs.putDoc(allIndexesForUserDocs.getFirst(),
                    userId.toString(),
                    () -> JsonUtil.write(64, new UserDoc(userId, name)));
        }
        List<String> indexes = allIndexesForUserDocs;
        List<Object> operations = new ArrayList<>(indexes.size() << 1);
        UserDoc userDoc = new UserDoc(userId, name);
        for (String index : indexes) {
            operations.add(Map.of(OperationType.INDEX, Map.of("_index", index, "_id", userId)));
            operations.add(userDoc);
        }
        BulkRequest request = new BulkRequest(operations);
        return elasticsearchClientForUserDocs.bulk(request)
                .flatMap(bulkResponse -> bulkResponse.errors()
                        ? Mono.error(new IllegalStateException(
                                "Bulk response contains errors: "
                                        + bulkResponse))
                        : Mono.empty());
    }

    public Mono<UpdateByQueryResponse> putUserDocs(Collection<Long> userIds, String name) {
        return elasticsearchClientForUserDocs.updateByQuery(USER_INDEX
                + "*",
                new UpdateByQueryRequest(
                        Map.of("ids", Map.of("values", userIds)),
                        new Script(
                                "ctx._source."
                                        + BaseDoc.Fields.NAME
                                        + "="
                                        + JsonUtil.writeAsString(name))));
    }

    public Mono<DeleteResponse> deleteUserDoc(Long userId) {
        return elasticsearchClientForUserDocs.deleteDoc(USER_INDEX
                + "*", userId.toString());
    }

    public Mono<DeleteByQueryResponse> deleteUserDocs(Collection<Long> userIds) {
        return elasticsearchClientForUserDocs.deleteByQuery(USER_INDEX
                + "*", new DeleteByQueryRequest(Map.of("terms", Map.of("_ids", userIds))));
    }

    public Mono<SearchResponse<UserDoc>> searchUserDocs(
            @Nullable Integer from,
            @Nullable Integer size,
            String name,
            @Nullable Collection<Long> ids,
            boolean highlight,
            @Nullable String scrollId,
            @Nullable String keepAlive) {
        return search(READER_USER_DOC,
                elasticsearchClientForUserDocs,
                USER_INDEX
                        + "*",
                from,
                size,
                name,
                ids,
                highlight,
                scrollId,
                keepAlive);
    }

    public Mono<Void> putGroupDoc(Long groupId, String name) {
        if (onlyOneIndexForGroupDocs) {
            return elasticsearchClientForGroupDocs.putDoc(allIndexesForGroupDocs.getFirst(),
                    groupId.toString(),
                    () -> JsonUtil.write(64, new GroupDoc(groupId, name)));
        }
        List<String> indexes = allIndexesForGroupDocs;
        List<Object> operations = new ArrayList<>(indexes.size() << 1);
        GroupDoc groupDoc = new GroupDoc(groupId, name);
        for (String index : indexes) {
            operations.add(Map.of(OperationType.INDEX, Map.of("_index", index, "_id", groupId)));
            operations.add(groupDoc);
        }
        BulkRequest request = new BulkRequest(operations);
        return elasticsearchClientForGroupDocs.bulk(request)
                .flatMap(bulkResponse -> bulkResponse.errors()
                        ? Mono.error(new IllegalStateException(
                                "Bulk response contains errors: "
                                        + bulkResponse))
                        : Mono.empty());
    }

    public Mono<UpdateByQueryResponse> putGroupDocs(Collection<Long> groupIds, String name) {
        return elasticsearchClientForGroupDocs.updateByQuery(GROUP_INDEX
                + "*",
                new UpdateByQueryRequest(
                        Map.of("ids", Map.of("values", groupIds)),
                        new Script(
                                "ctx._source."
                                        + BaseDoc.Fields.NAME
                                        + "="
                                        + JsonUtil.writeAsString(name))));
    }

    public Mono<DeleteByQueryResponse> deleteGroupDocs(Collection<Long> groupIds) {
        return elasticsearchClientForGroupDocs.deleteByQuery(GROUP_INDEX
                + "*", new DeleteByQueryRequest(Map.of("terms", Map.of("_ids", groupIds))));
    }

    public Mono<DeleteByQueryResponse> deleteAllGroupDocs() {
        return elasticsearchClientForGroupDocs.deleteByQuery(GROUP_INDEX
                + "*", new DeleteByQueryRequest(Map.of("match_all", Collections.emptyMap())));
    }

    public Mono<SearchResponse<GroupDoc>> searchGroupDocs(
            @Nullable Integer from,
            @Nullable Integer size,
            String name,
            @Nullable Collection<Long> ids,
            boolean highlight,
            @Nullable String scrollId,
            @Nullable String keepAlive) {
        return search(READER_GROUP_DOC,
                elasticsearchClientForGroupDocs,
                GROUP_INDEX
                        + "*",
                from,
                size,
                name,
                ids,
                highlight,
                scrollId,
                keepAlive);
    }

    private <T> Mono<SearchResponse<T>> search(
            ObjectReader docReader,
            ElasticsearchClient elasticsearchClient,
            String index,
            @Nullable Integer from,
            @Nullable Integer size,
            String name,
            @Nullable Collection<Long> ids,
            boolean highlight,
            @Nullable String scrollId,
            @Nullable String keepAlive) {
        Map<String, Object> multiMatch = Map.of("multi_match",
                Map.of("fields",
                        List.of(BaseDoc.Fields.NAME
                                + "*",
                                BaseDoc.Fields.NAME
                                        + "*.standard^0.5",
                                BaseDoc.Fields.NAME
                                        + "*.ngram^0.25"),
                        "query",
                        name));
        Map<String, Object> query = CollectionUtil.isEmpty(ids)
                ? multiMatch
                : Map.of("bool",
                        Map.of("must", List.of(Map.of("ids", Map.of("values", ids)), multiMatch)));

        SearchRequest request = new SearchRequest(
                from,
                size,
                COLLAPSE_ID,
                highlight
                        ? HIGHLIGHT_NAME
                        : null,
                scrollId == null
                        ? null
                        : new PointInTimeReference(scrollId, keepAlive),
                query);
        return elasticsearchClient.search(index, request, docReader);
    }

    public Mono<Void> deletePitForUserDocs(String scrollId) {
        return elasticsearchClientForUserDocs.deletePit(scrollId);
    }
}
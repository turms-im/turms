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

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.spec.SecretKeySpec;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import io.minio.BucketExistsArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioAsyncClient;
import io.minio.PostPolicy;
import io.minio.RemoveObjectArgs;
import io.minio.SetBucketLifecycleArgs;
import io.minio.SetBucketPolicyArgs;
import io.minio.http.Method;
import io.minio.messages.Expiration;
import io.minio.messages.LifecycleConfiguration;
import io.minio.messages.LifecycleRule;
import io.minio.messages.RuleFilter;
import io.minio.messages.Status;
import org.springframework.context.ApplicationContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import im.turms.plugin.minio.core.BucketPolicy;
import im.turms.plugin.minio.core.BucketPolicyAction;
import im.turms.plugin.minio.core.BucketPolicyConditionCriteria;
import im.turms.plugin.minio.core.BucketPolicyConditionKey;
import im.turms.plugin.minio.core.BucketPolicyConditionOperator;
import im.turms.plugin.minio.core.BucketPolicyEffect;
import im.turms.plugin.minio.core.BucketPolicyStatement;
import im.turms.plugin.minio.core.TurmsMinioAsyncClient;
import im.turms.plugin.minio.po.MessageAttachment;
import im.turms.plugin.minio.properties.MinioStorageProperties;
import im.turms.plugin.minio.repository.MessageAttachmentRepository;
import im.turms.server.common.access.admin.web.MediaType;
import im.turms.server.common.access.client.dto.constant.StorageResourceType;
import im.turms.server.common.access.client.dto.model.common.Value;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.codec.Base62;
import im.turms.server.common.infra.collection.ArrayUtil;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.json.JsonUtil;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.lang.LongUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.ExtensionPointMethod;
import im.turms.server.common.infra.plugin.TurmsExtension;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.business.storage.StorageItemProperties;
import im.turms.server.common.infra.property.env.service.business.storage.StorageProperties;
import im.turms.server.common.infra.reactor.PublisherUtil;
import im.turms.server.common.infra.security.MacUtil;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.storage.bo.StorageResourceInfo;
import im.turms.service.domain.user.service.UserRelationshipService;
import im.turms.service.infra.plugin.extension.StorageServiceProvider;

import static im.turms.server.common.access.client.dto.constant.StorageResourceType.GROUP_PROFILE_PICTURE;
import static im.turms.server.common.access.client.dto.constant.StorageResourceType.MESSAGE_ATTACHMENT;
import static im.turms.server.common.access.client.dto.constant.StorageResourceType.USER_PROFILE_PICTURE;

/**
 * @author James Chen
 */
public class MinioStorageServiceProvider extends TurmsExtension implements StorageServiceProvider {

    public static final String RESOURCE_ID = "id";
    public static final String RESOURCE_URL = "url";

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioStorageServiceProvider.class);

    private static final int INIT_BUCKETS_TIMEOUT_SECONDS = 60;
    private static final Map<StorageResourceType, String> RESOURCE_TYPE_TO_BUCKET_NAME;

    private static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    private static final Mono<Map<String, String>> ERROR_NOT_UPLOADER_OR_SHARED_WITH_USER_TO_DOWNLOAD_MESSAGE_ATTACHMENT =
            Mono.error(ResponseException.get(
                    ResponseStatusCode.NOT_UPLOADER_OR_SHARED_WITH_USER_TO_DOWNLOAD_MESSAGE_ATTACHMENT));

    private TurmsMinioAsyncClient client;
    private String baseUrl;

    private boolean isBase62Enabled;
    @Nullable
    private Base62 base62;
    /**
     * @implNote 1. We use HMAC(key, message) instead of a HASH(key + message) to avoid the length
     *           extension attack. To put simply, if a hacker knows the signature of the resource
     *           "1", and he can also know the signature of resource "12", "13", "123", and so on
     *           without knowledge of the key.
     *           <p>
     *           2. Use MD5 because its output size (128 bits) is small, and it is a 22-character
     *           Base62-encoded string.
     */
    private boolean isMacEnabled;
    @Nullable
    private SecretKeySpec macKey;

    private StorageProperties storageProperties;

    private Node node;
    private GroupMemberService groupMemberService;
    private UserRelationshipService userRelationshipService;

    private MessageAttachmentRepository messageAttachmentRepository;

    private boolean isServing;

    static {
        StorageResourceType[] resourceTypes =
                ClassUtil.getSharedEnumConstants(StorageResourceType.class);
        Map.Entry<StorageResourceType, String>[] resourceTypeToBucketName =
                new Map.Entry[resourceTypes.length - 1];
        int writerIndex = 0;
        for (StorageResourceType resourceType : resourceTypes) {
            if (resourceType != StorageResourceType.UNRECOGNIZED) {
                String name = StringUtil.replaceLatin1(resourceType.name()
                        .toLowerCase(), (byte) '_', (byte) '-');
                resourceTypeToBucketName[writerIndex++] = Map.entry(resourceType, name);
            }
        }
        RESOURCE_TYPE_TO_BUCKET_NAME = Map.ofEntries(resourceTypeToBucketName);
    }

    @Override
    public Mono<Void> start() {
        return setUp();
    }

    private Mono<Void> setUp() {
        MinioStorageProperties properties = loadProperties(MinioStorageProperties.class);
        if (!properties.isEnabled()) {
            return Mono.empty();
        }
        String endpoint = properties.getEndpoint();
        URI uri;
        try {
            uri = new URI(endpoint);
        } catch (URISyntaxException e) {
            return Mono.error(new IllegalArgumentException(
                    "Illegal endpoint URL: "
                            + endpoint,
                    e));
        }
        if (!uri.isAbsolute()) {
            return Mono.error(new IllegalArgumentException(
                    "The endpoint URL ("
                            + endpoint
                            + ") must be absolute"));
        }
        ApplicationContext context = getContext();
        node = context.getBean(Node.class);
        groupMemberService = context.getBean(GroupMemberService.class);
        userRelationshipService = context.getBean(UserRelationshipService.class);
        TurmsPropertiesManager propertiesManager = context.getBean(TurmsPropertiesManager.class);
        storageProperties = propertiesManager.getLocalProperties()
                .getService()
                .getStorage();
        Mono<TurmsMongoClient> clientMono =
                TurmsMongoClient.of(properties.getMongo(), "message-attachment");
        return clientMono.flatMap(
                mongoClient -> initClientAndBuckets(mongoClient, properties, uri, endpoint));
    }

    private Mono<Void> initClientAndBuckets(
            TurmsMongoClient mongoClient,
            MinioStorageProperties properties,
            URI uri,
            String endpoint) {
        messageAttachmentRepository = new MessageAttachmentRepository(mongoClient);
        baseUrl = uri.getScheme()
                + "://"
                + uri.getAuthority();
        MinioStorageProperties.ResourceIdBase62 base62Properties = properties.getResourceId()
                .getBase62();
        MinioStorageProperties.ResourceIdMac macProperties = properties.getResourceId()
                .getMac();
        isMacEnabled = macProperties.isEnabled();
        isBase62Enabled = isMacEnabled || base62Properties.isEnabled();
        if (isBase62Enabled) {
            String charset = base62Properties.getCharset();
            base62 = StringUtil.isEmpty(charset)
                    ? Base62.getDefaultInstance()
                    : new Base62(charset);
        } else {
            base62 = null;
        }
        if (isMacEnabled) {
            byte[] key;
            String base64Key = macProperties.getBase64Key();
            try {
                key = Base64.getDecoder()
                        .decode(base64Key);
            } catch (Exception e) {
                return Mono.error(new IllegalArgumentException(
                        "The HMAC key must be Base64-encoded, but got: "
                                + base64Key,
                        e));
            }
            if (key.length < 16) {
                return Mono.error(new IllegalArgumentException(
                        "The length of HMAC key must be greater than or equal to 16, but got: "
                                + key.length));
            }
            macKey = new SecretKeySpec(key, "HmacMD5");
        } else {
            macKey = null;
        }
        String region = properties.getRegion();
        String accessKey = properties.getAccessKey();
        String secretKey = properties.getSecretKey();
        MinioAsyncClient.Builder builder = MinioAsyncClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey);
        if (StringUtil.isNotBlank(region)) {
            builder.region(region);
        }
        client = new TurmsMinioAsyncClient(builder.build());

        Mono<Void> initBuckets =
                initBuckets().timeout(Duration.ofSeconds(INIT_BUCKETS_TIMEOUT_SECONDS))
                        .doOnSuccess(unused -> isServing = true);

        MinioStorageProperties.Retry retry = properties.getRetry();
        int maxAttempts = retry.getMaxAttempts();
        if (!retry.isEnabled() || maxAttempts <= 0) {
            return initBuckets.onErrorResume(t -> Mono
                    .error(new RuntimeException("Failed to initialize the MinIO client", t)));
        }
        return initBuckets.retryWhen(Retry.max(maxAttempts)
                .doBeforeRetryAsync(retrySignal -> Mono.deferContextual(contextView -> {
                    long totalRetries = retrySignal.totalRetries();
                    try (TracingCloseableContext ignored =
                            TracingContext.getCloseableContext(contextView)) {
                        LOGGER.error("Failed to initialize the MinIO client. Retry times: "
                                + totalRetries, retrySignal.failure());
                    }
                    if (0 == totalRetries) {
                        return Mono.delay(Duration.ofMillis(retry.getInitialIntervalMillis()))
                                .then();
                    }
                    return Mono.delay(Duration.ofMillis(retry.getIntervalMillis()))
                            .then();
                })));
    }

    // region bucket
    private Mono<Void> initBuckets() {
        StorageResourceType[] resourceTypes =
                ClassUtil.getSharedEnumConstants(StorageResourceType.class);
        List<Mono<Void>> initBuckets = new ArrayList<>(resourceTypes.length - 1);
        for (StorageResourceType resourceType : resourceTypes) {
            if (resourceType == StorageResourceType.UNRECOGNIZED) {
                continue;
            }
            String bucket = getBucketName(resourceType);
            Mono<Void> initBucket = bucketExists(bucket).flatMap(exists -> {
                if (exists) {
                    LOGGER.info("The bucket \"{}\" has already existed", bucket);
                    return Mono.empty();
                }
                StorageItemProperties itemProperties = switch (resourceType) {
                    case USER_PROFILE_PICTURE -> storageProperties.getUserProfilePicture();
                    case GROUP_PROFILE_PICTURE -> storageProperties.getGroupProfilePicture();
                    case MESSAGE_ATTACHMENT -> storageProperties.getMessageAttachment();
                    default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "Unknown storage resource type: "
                                    + resourceType);
                };
                Mono<Void> createBucket = createBucket(resourceType).then(Mono.defer(
                        () -> setBucketLifecycle(bucket, itemProperties.getExpireAfterDays())));
                if (resourceType == StorageResourceType.USER_PROFILE_PICTURE
                        || resourceType == GROUP_PROFILE_PICTURE) {
                    createBucket = createBucket.then(Mono.defer(
                            () -> setBucketPolicy(bucket, itemProperties.getAllowedReferrers())));
                }
                return createBucket.doOnSuccess(
                        unused -> LOGGER.info("The bucket \"{}\" has been created", bucket));
            })
                    .onErrorMap(
                            t -> new RuntimeException("Caught an error while creating buckets", t));
            initBuckets.add(initBucket);
        }
        return Mono.whenDelayError(initBuckets);
    }

    private Mono<Void> createBucket(StorageResourceType resourceType) {
        String bucketName = getBucketName(resourceType);
        return PublisherUtil.fromFuture(() -> client.makeBucket(MakeBucketArgs.builder()
                .bucket(bucketName)
                .build()))
                .onErrorMap(t -> new RuntimeException(
                        "Failed to create the bucket: \""
                                + bucketName
                                + "\"",
                        t));
    }

    private Mono<Void> setBucketPolicy(String bucket, List<String> allowedReferrers) {
        BucketPolicyStatement.BucketPolicyStatementBuilder statementBuilder =
                BucketPolicyStatement.builder()
                        .sid("PublicRead")
                        .effect(BucketPolicyEffect.ALLOW)
                        .principal("*")
                        .action(List.of(BucketPolicyAction.GET_OBJECT))
                        .resource(List.of("arn:aws:s3:::"
                                + bucket
                                + "/*"));
        if (CollectionUtil.isNotEmpty(allowedReferrers)) {
            statementBuilder.conditions(Map.of(BucketPolicyConditionOperator.STRING_LIKE,
                    new BucketPolicyConditionCriteria()
                            .withCondition(BucketPolicyConditionKey.REFERER, allowedReferrers)));
        }
        BucketPolicy policy = BucketPolicy.builder()
                .version("2012-10-17")
                .statement(List.of(statementBuilder.build()))
                .build();
        String config = JsonUtil.writeAsString(policy);
        return PublisherUtil.fromFuture(() -> client.setBucketPolicy(SetBucketPolicyArgs.builder()
                .bucket(bucket)
                .config(config)
                .build()))
                .onErrorMap(t -> new RuntimeException(
                        "Failed to set the bucket policy ("
                                + StringUtil.sanitizeLatin1(config)
                                + ") to the bucket: \""
                                + bucket
                                + "\"",
                        t))
                .then();
    }

    private Mono<Void> setBucketLifecycle(String bucket, int expireAfterDays) {
        if (expireAfterDays <= 0) {
            return Mono.empty();
        }
        return PublisherUtil
                .fromFuture(() -> client.setBucketLifecycle(SetBucketLifecycleArgs.builder()
                        .bucket(bucket)
                        .config(new LifecycleConfiguration(
                                List.of(new LifecycleRule(
                                        Status.ENABLED,
                                        null,
                                        new Expiration((ZonedDateTime) null, expireAfterDays, null),
                                        new RuleFilter(""),
                                        null,
                                        null,
                                        null,
                                        null))))
                        .build()))
                .onErrorMap(t -> new RuntimeException(
                        "Failed to set a lifecycle configuration to the bucket: \""
                                + bucket
                                + "\"",
                        t))
                .then();
    }

    private Mono<Boolean> bucketExists(String bucket) {
        return PublisherUtil.fromFuture(() -> client.bucketExists(BucketExistsArgs.builder()
                .bucket(bucket)
                .build()))
                .onErrorMap(t -> new RuntimeException(
                        "Failed to check if the bucket \""
                                + bucket
                                + "\" exists",
                        t));
    }

    private String getBucketName(StorageResourceType resourceType) {
        return RESOURCE_TYPE_TO_BUCKET_NAME.get(resourceType);
    }
    // endregion

    // region presign
    private Mono<String> getPresignedDownloadUrl(
            @NotNull String bucket,
            @NotNull String key,
            int expireAfterSeconds) {
        GetPresignedObjectUrlArgs.Builder builder = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucket)
                .object(key);
        if (expireAfterSeconds > 0) {
            builder.expiry(expireAfterSeconds);
        }
        return PublisherUtil.fromFuture(() -> client.getPresignedObjectUrlAsync(builder.build()))
                .onErrorMap(t -> new RuntimeException(
                        "Failed to get the presigned URL to download the resource object \""
                                + key
                                + "\" in the bucket: \""
                                + bucket
                                + "\"",
                        t));
    }

    private Map<String, String> getResourceUploadInfo(
            @NotNull String bucket,
            @NotNull String objectKey,
            @NotNull String allowedContentType,
            int minSizeBytes,
            int maxSizeBytes,
            int expireAfterSeconds) {
        PostPolicy policy = new PostPolicy(
                bucket,
                ZonedDateTime.now()
                        .plusSeconds(expireAfterSeconds));
        policy.addEqualsCondition("key", objectKey);
        MediaType mediaType = MediaType.create(allowedContentType);
        if (mediaType.isWildcardType()) {
            policy.addStartsWithCondition(HTTP_HEADER_CONTENT_TYPE, "");
        } else {
            if (mediaType.isWildcardSubtype()) {
                policy.addStartsWithCondition(HTTP_HEADER_CONTENT_TYPE,
                        mediaType.type()
                                + "/");
            } else {
                policy.addEqualsCondition(HTTP_HEADER_CONTENT_TYPE, mediaType.toString());
            }
        }
        boolean hasMaxSizeLimit = maxSizeBytes > 0;
        if (minSizeBytes > 0 || hasMaxSizeLimit) {
            if (hasMaxSizeLimit && minSizeBytes > maxSizeBytes) {
                throw new IllegalArgumentException(
                        "The minimum size cannot be greater than the maximum size");
            }
            policy.addContentLengthRangeCondition(minSizeBytes, maxSizeBytes);
        }
        try {
            Map<String, String> map = client.getPresignedPostFormData(policy);
            map = CollectionUtil.add(map, RESOURCE_ID, objectKey);
            map.put(RESOURCE_URL,
                    baseUrl
                            + "/"
                            + bucket);
            return map;
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to get the presigned post form data for the resource object \""
                            + objectKey
                            + "\" in the bucket \""
                            + bucket
                            + "\"",
                    e);
        }
    }
    // endregion

    // region object key
    private String getObjectKey(long key) {
        if (isMacEnabled) {
            byte[] keyBytes = LongUtil.toBytes(key);
            byte[] md5 = MacUtil.signMd5(keyBytes, macKey);
            byte[] base62Key = base62.encode(keyBytes);
            byte[] base62Md5 = base62.encode(md5);
            return StringUtil.newLatin1String(ArrayUtil.concat(base62Key, base62Md5));
        }
        if (isBase62Enabled) {
            byte[] base62Key = base62.encode(key);
            return StringUtil.newLatin1String(base62Key);
        }
        return Long.toString(key);
    }

    private String getMessageAttachmentObjectKey(long key) {
        return Long.toString(key);
    }
    // endregion

    // region transformation
    private Mono<List<StorageResourceInfo>> simpleAttachmentFlux2infos(
            Flux<MessageAttachment> attachmentFlux) {
        return attachmentFlux
                .map(attachment -> new StorageResourceInfo(
                        attachment.getId(),
                        null,
                        attachment.getName(),
                        attachment.getMediaType(),
                        attachment.getUploaderId(),
                        attachment.getCreationDate()))
                .collect(CollectorUtil.toChunkedList());
    }
    // endregion

    @ExtensionPointMethod
    @Override
    public Mono<Void> deleteUserProfilePicture(
            Long requesterId,
            @NotNull List<Value> customAttributes) {
        if (!isServing) {
            return ResponseExceptionPublisherPool.serverUnavailable();
        }
        String objectKey = getObjectKey(requesterId);
        String bucketName = getBucketName(StorageResourceType.USER_PROFILE_PICTURE);
        return PublisherUtil.fromFuture(() -> client.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectKey)
                .build()))
                .onErrorMap(t -> new RuntimeException(
                        "Failed to remove the user profile picture with the object key \""
                                + objectKey
                                + "\" in the bucket \""
                                + bucketName
                                + "\"",
                        t));
    }

    @ExtensionPointMethod
    @Override
    public Mono<Map<String, String>> queryUserProfilePictureUploadInfo(
            Long requesterId,
            @Nullable String resourceName,
            @Nullable MediaType resourceMediaType,
            @NotNull List<Value> customAttributes) {
        if (!isServing) {
            return ResponseExceptionPublisherPool.serverUnavailable();
        }
        String objectKey = getObjectKey(requesterId);
        StorageItemProperties itemProperties = storageProperties.getUserProfilePicture();
        Map<String, String> uploadInfo = getResourceUploadInfo(getBucketName(USER_PROFILE_PICTURE),
                objectKey,
                itemProperties.getAllowedContentType(),
                itemProperties.getMinSizeBytes(),
                itemProperties.getMaxSizeBytes(),
                itemProperties.getUploadUrlExpireAfterSeconds());
        return Mono.just(uploadInfo);
    }

    @ExtensionPointMethod
    @Override
    public Mono<Map<String, String>> queryUserProfilePictureDownloadInfo(
            Long requesterId,
            Long userId,
            @NotNull List<Value> customAttributes) {
        if (!isServing) {
            return ResponseExceptionPublisherPool.serverUnavailable();
        }
        String url = baseUrl
                + "/"
                + getBucketName(StorageResourceType.USER_PROFILE_PICTURE)
                + "/"
                + getObjectKey(userId);
        return Mono.just(Map.of(RESOURCE_URL, url));
    }

    @ExtensionPointMethod
    @Override
    public Mono<Void> deleteGroupProfilePicture(
            Long requesterId,
            Long groupId,
            @NotNull List<Value> customAttributes) {
        if (!isServing) {
            return ResponseExceptionPublisherPool.serverUnavailable();
        }
        return groupMemberService.isOwnerOrManager(requesterId, groupId, false)
                .flatMap(hasPermission -> {
                    if (!hasPermission) {
                        return ResponseExceptionPublisherPool.unauthorized();
                    }
                    String objectKey = getObjectKey(groupId);
                    String bucketName = getBucketName(GROUP_PROFILE_PICTURE);
                    return PublisherUtil
                            .fromFuture(() -> client.removeObject(RemoveObjectArgs.builder()
                                    .bucket(bucketName)
                                    .object(objectKey)
                                    .build()))
                            .onErrorMap(t -> new RuntimeException(
                                    "Failed to remove the group profile picture with the object key \""
                                            + objectKey
                                            + "\" in the bucket \""
                                            + bucketName
                                            + "\"",
                                    t));
                });
    }

    @ExtensionPointMethod
    @Override
    public Mono<Map<String, String>> queryGroupProfilePictureUploadInfo(
            Long requesterId,
            Long groupId,
            @Nullable String resourceName,
            @Nullable MediaType resourceMediaType,
            @NotNull List<Value> customAttributes) {
        if (!isServing) {
            return ResponseExceptionPublisherPool.serverUnavailable();
        }
        return groupMemberService.isOwnerOrManager(requesterId, groupId, false)
                .flatMap(hasPermission -> {
                    if (!hasPermission) {
                        return ResponseExceptionPublisherPool.unauthorized();
                    }
                    StorageItemProperties itemProperties =
                            storageProperties.getGroupProfilePicture();
                    String objectKey = getObjectKey(groupId);
                    Map<String, String> uploadInfo =
                            getResourceUploadInfo(getBucketName(GROUP_PROFILE_PICTURE),
                                    objectKey,
                                    itemProperties.getAllowedContentType(),
                                    itemProperties.getMinSizeBytes(),
                                    itemProperties.getMaxSizeBytes(),
                                    itemProperties.getUploadUrlExpireAfterSeconds());
                    return Mono.just(uploadInfo);
                });
    }

    @ExtensionPointMethod
    @Override
    public Mono<Map<String, String>> queryGroupProfilePictureDownloadInfo(
            Long requesterId,
            Long groupId,
            @NotNull List<Value> customAttributes) {
        if (!isServing) {
            return ResponseExceptionPublisherPool.serverUnavailable();
        }
        String url = baseUrl
                + "/"
                + getBucketName(GROUP_PROFILE_PICTURE)
                + "/"
                + getObjectKey(groupId);
        return Mono.just(Map.of(RESOURCE_URL, url));
    }

    @ExtensionPointMethod
    @Override
    public Mono<Void> deleteMessageAttachment(
            Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            @NotNull List<Value> customAttributes) {
        return StorageServiceProvider.super.deleteMessageAttachment(requesterId,
                messageAttachmentIdNum,
                messageAttachmentIdStr,
                customAttributes);
    }

    @ExtensionPointMethod
    @Override
    public Mono<Void> shareMessageAttachmentWithUser(
            Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            Long userId) {
        if (messageAttachmentIdNum == null) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The message ID must not be null"));
        }
        return messageAttachmentRepository.isUploader(messageAttachmentIdNum, requesterId)
                .flatMap(isUploader -> {
                    if (!isUploader) {
                        return Mono.error(ResponseException
                                .get(ResponseStatusCode.NOT_UPLOADER_TO_SHARE_MESSAGE_ATTACHMENT));
                    }
                    return messageAttachmentRepository
                            .addSharedWithUser(messageAttachmentIdNum, userId)
                            .then();
                });
    }

    @ExtensionPointMethod
    @Override
    public Mono<Void> shareMessageAttachmentWithGroup(
            Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            Long groupId) {
        if (messageAttachmentIdNum == null) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The message ID must not be null"));
        }
        return messageAttachmentRepository.isUploader(messageAttachmentIdNum, requesterId)
                .flatMap(isUploader -> {
                    if (!isUploader) {
                        return Mono.error(ResponseException
                                .get(ResponseStatusCode.NOT_UPLOADER_TO_SHARE_MESSAGE_ATTACHMENT));
                    }
                    return messageAttachmentRepository
                            .addSharedWithGroup(messageAttachmentIdNum, groupId)
                            .then();
                });
    }

    @ExtensionPointMethod
    @Override
    public Mono<Void> unshareMessageAttachmentWithUser(
            Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            Long userId) {
        return StorageServiceProvider.super.unshareMessageAttachmentWithUser(requesterId,
                messageAttachmentIdNum,
                messageAttachmentIdStr,
                userId);
    }

    @ExtensionPointMethod
    @Override
    public Mono<Void> unshareMessageAttachmentWithGroup(
            Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            Long groupId) {
        return StorageServiceProvider.super.unshareMessageAttachmentWithGroup(requesterId,
                messageAttachmentIdNum,
                messageAttachmentIdStr,
                groupId);
    }

    @ExtensionPointMethod
    @Override
    public Mono<Map<String, String>> queryMessageAttachmentUploadInfo(
            Long requesterId,
            @Nullable String resourceName,
            @Nullable MediaType resourceMediaType,
            @NotNull List<Value> customAttributes) {
        return StorageServiceProvider.super.queryMessageAttachmentUploadInfo(requesterId,
                resourceName,
                resourceMediaType,
                customAttributes);
    }

    @ExtensionPointMethod
    @Override
    public Mono<Map<String, String>> queryMessageAttachmentUploadInfoInPrivateConversation(
            Long requesterId,
            Long userId,
            @Nullable String resourceName,
            @Nullable MediaType resourceMediaType,
            @NotNull List<Value> customAttributes) {
        return queryMessageAttachmentUploadInfo(requesterId,
                userId,
                null,
                resourceName,
                resourceMediaType);
    }

    @ExtensionPointMethod
    @Override
    public Mono<Map<String, String>> queryMessageAttachmentUploadInfoInGroupConversation(
            Long requesterId,
            Long groupId,
            @Nullable String resourceName,
            @Nullable MediaType resourceMediaType,
            @NotNull List<Value> customAttributes) {
        return queryMessageAttachmentUploadInfo(requesterId,
                null,
                groupId,
                resourceName,
                resourceMediaType);
    }

    private Mono<Map<String, String>> queryMessageAttachmentUploadInfo(
            Long requesterId,
            @Nullable Long userId,
            @Nullable Long groupId,
            @Nullable String name,
            @Nullable MediaType mediaType) {
        if (!isServing) {
            return ResponseExceptionPublisherPool.serverUnavailable();
        }
        if (StringUtil.isBlank(name)) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The resource name must not be null or blank"));
        }
        if (mediaType == null || !mediaType.isConcrete()) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The resource media type must not be null and must be a concrete media type"));
        }
        long id = node.nextLargeGapId(ServiceType.STORAGE_MESSAGE_ATTACHMENT);
        StorageItemProperties itemProperties = storageProperties.getMessageAttachment();
        List<Long> sharedWithUserIds;
        List<Long> associatedGroupIds;
        Mono<Void> authorize;
        if (groupId == null) {
            if (userId == null) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The user ID or group ID to share with must not both be null"));
            }
            sharedWithUserIds = List.of(userId);
            associatedGroupIds = null;
            authorize = userRelationshipService
                    .hasRelationshipAndNotBlocked(userId, requesterId, false)
                    .flatMap(hasRelationshipAndNotBlocked -> hasRelationshipAndNotBlocked
                            ? Mono.empty()
                            : Mono.error(ResponseException.get(
                                    ResponseStatusCode.NOT_FRIEND_TO_UPLOAD_MESSAGE_ATTACHMENT_IN_PRIVATE_CONVERSATION)));
        } else {
            sharedWithUserIds = null;
            associatedGroupIds = List.of(groupId);
            // TODO: check mute status
            authorize = groupMemberService.isGroupMember(groupId, requesterId, false)
                    .flatMap(isGroupMember -> isGroupMember
                            ? Mono.empty()
                            : Mono.error(ResponseException.get(
                                    ResponseStatusCode.NOT_GROUP_MEMBER_TO_UPLOAD_MESSAGE_ATTACHMENT_IN_GROUP_CONVERSATION)));
        }
        return authorize.then(Mono.defer(() -> {
            Map<String, String> uploadInfo =
                    getResourceUploadInfo(getBucketName(MESSAGE_ATTACHMENT),
                            Long.toString(id),
                            itemProperties.getAllowedContentType(),
                            itemProperties.getMinSizeBytes(),
                            itemProperties.getMaxSizeBytes(),
                            itemProperties.getUploadUrlExpireAfterSeconds());
            MessageAttachment attachment = new MessageAttachment(
                    id,
                    name,
                    mediaType.toString(),
                    requesterId,
                    new Date(),
                    sharedWithUserIds,
                    associatedGroupIds);
            return messageAttachmentRepository.insert(attachment)
                    .thenReturn(uploadInfo);
        }));
    }

    @ExtensionPointMethod
    @Override
    public Mono<Map<String, String>> queryMessageAttachmentDownloadInfo(
            Long requesterId,
            @Nullable Long messageAttachmentIdNum,
            @Nullable String messageAttachmentIdStr,
            @NotNull List<Value> customAttributes) {
        if (!isServing) {
            return ResponseExceptionPublisherPool.serverUnavailable();
        }
        if (messageAttachmentIdNum == null) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The message attachment ID must not be null"));
        }
        return messageAttachmentRepository
                .findUploaderIdAndSharedWithUserIdsAndGroupIds(messageAttachmentIdNum)
                .flatMap(attachment -> {
                    if (!attachment.getUploaderId()
                            .equals(requesterId)
                            && !CollectionUtil.contains(attachment.getSharedWithUserIds(),
                                    requesterId)) {
                        List<Long> sharedWithGroupIds = attachment.getSharedWithGroupIds();
                        if (sharedWithGroupIds == null || sharedWithGroupIds.isEmpty()) {
                            return ERROR_NOT_UPLOADER_OR_SHARED_WITH_USER_TO_DOWNLOAD_MESSAGE_ATTACHMENT;
                        }
                        return groupMemberService
                                .isGroupMember(CollectionUtil.toSet(sharedWithGroupIds),
                                        requesterId)
                                .flatMap(isGroupMember -> {
                                    if (!isGroupMember) {
                                        return ERROR_NOT_UPLOADER_OR_SHARED_WITH_USER_TO_DOWNLOAD_MESSAGE_ATTACHMENT;
                                    }
                                    return getPresignedDownloadUrl(
                                            getBucketName(MESSAGE_ATTACHMENT),
                                            getMessageAttachmentObjectKey(messageAttachmentIdNum),
                                            storageProperties.getMessageAttachment()
                                                    .getDownloadUrlExpireAfterSeconds())
                                            .map(url -> Map.of(RESOURCE_URL, url));
                                });
                    }
                    return getPresignedDownloadUrl(getBucketName(MESSAGE_ATTACHMENT),
                            getMessageAttachmentObjectKey(messageAttachmentIdNum),
                            storageProperties.getMessageAttachment()
                                    .getDownloadUrlExpireAfterSeconds())
                            .map(url -> Map.of(RESOURCE_URL, url));
                })
                .switchIfEmpty(
                        ERROR_NOT_UPLOADER_OR_SHARED_WITH_USER_TO_DOWNLOAD_MESSAGE_ATTACHMENT);
    }

    @ExtensionPointMethod
    @Override
    public Mono<List<StorageResourceInfo>> queryMessageAttachmentInfosUploadedByRequester(
            Long requesterId,
            @Nullable DateRange creationDateRange) {
        Flux<MessageAttachment> attachmentFlux = messageAttachmentRepository
                .findSimpleAttachmentsByUploaderId(requesterId, creationDateRange);
        return simpleAttachmentFlux2infos(attachmentFlux);
    }

    @ExtensionPointMethod
    @Override
    public Mono<List<StorageResourceInfo>> queryMessageAttachmentInfosInPrivateConversations(
            Long requesterId,
            @Nullable Set<Long> userIds,
            @Nullable DateRange creationDateRange,
            @Nullable Boolean areSharedByRequester) {
        if (CollectionUtil.getSize(userIds) != 1) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The size of user IDs must be 1"));
        }
        Long userId = userIds.iterator()
                .next();
        Flux<MessageAttachment> attachmentFlux;
        if (requesterId.equals(userId)) {
            attachmentFlux = messageAttachmentRepository
                    .findSimpleAttachmentsInPrivateConversation(requesterId, creationDateRange);
        } else {
            attachmentFlux =
                    userRelationshipService.hasRelationshipAndNotBlocked(userId, requesterId, false)
                            .flatMapMany(hasRelationshipAndNotBlocked -> {
                                if (!hasRelationshipAndNotBlocked) {
                                    return Mono.error(ResponseException.get(
                                            ResponseStatusCode.NOT_FRIEND_TO_QUERY_MESSAGE_ATTACHMENT_INFO_IN_PRIVATE_CONVERSATION));
                                }
                                return messageAttachmentRepository
                                        .findSimpleAttachmentsInPrivateConversation(requesterId,
                                                userId,
                                                creationDateRange,
                                                areSharedByRequester);
                            });
        }
        return simpleAttachmentFlux2infos(attachmentFlux);
    }

    @ExtensionPointMethod
    @Override
    public Mono<List<StorageResourceInfo>> queryMessageAttachmentInfosInGroupConversations(
            Long requesterId,
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange creationDateRange) {
        if (CollectionUtil.getSize(groupIds) != 1) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The size of group IDs must be 1"));
        }
        Long groupId = groupIds.iterator()
                .next();
        return groupMemberService.isGroupMember(groupId, requesterId, false)
                .flatMap(isGroupMember -> {
                    if (!isGroupMember) {
                        return Mono.error(ResponseException.get(
                                ResponseStatusCode.NOT_GROUP_MEMBER_TO_QUERY_MESSAGE_ATTACHMENT_INFO_IN_GROUP_CONVERSATION));
                    }
                    return simpleAttachmentFlux2infos(messageAttachmentRepository
                            .findSimpleAttachmentsBySharedWithGroupId(groupId,
                                    userIds,
                                    creationDateRange));
                });
    }

}
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

package im.turms.plugin.impl;

import im.turms.server.common.access.client.dto.constant.ContentType;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.TurmsExtension;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.env.service.business.StorageProperties;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.message.service.MessageService;
import im.turms.service.infra.plugin.extension.StorageServiceProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.BucketLifecycleConfiguration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ExpirationStatus;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketResponse;
import software.amazon.awssdk.services.s3.model.LifecycleExpiration;
import software.amazon.awssdk.services.s3.model.LifecycleRule;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;
import software.amazon.awssdk.services.s3.model.PutBucketLifecycleConfigurationRequest;
import software.amazon.awssdk.services.s3.model.PutBucketPolicyRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author James Chen
 */
public class MinioStorageServiceProvider extends TurmsExtension implements StorageServiceProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioStorageServiceProvider.class);

    private static final int TIMEOUT_SECONDS = 10;

    private S3AsyncClient client;
    private S3Presigner presigner;
    private StorageProperties storageProperties;
    private MessageService messageService;
    private MinioProperties minioProperties;
    private GroupMemberService groupMemberService;

    private boolean isServing;

    @Override
    public void onStarted() {
        setUp();
    }

    @Override
    public Mono<Void> deleteResource(@NotNull Long requesterId,
                                     @NotNull ContentType contentType,
                                     String keyStr,
                                     @Nullable Long keyNum) {
        if (!isServing) {
            return ResponseExceptionPublisherPool.serverUnavailable();
        }
        return hasPermissionToDelete(requesterId, contentType, keyNum)
                .flatMap(hasPermission -> {
                    if (!hasPermission) {
                        return ResponseExceptionPublisherPool.unauthorized();
                    }
                    String key;
                    switch (contentType) {
                        case PROFILE -> key = requesterId.toString();
                        case GROUP_PROFILE -> {
                            if (keyNum == null) {
                                return Mono.error(ResponseException
                                        .get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The group ID must not be null"));
                            }
                            key = keyNum.toString();
                        }
                        case ATTACHMENT -> {
                            return Mono.error(ResponseException
                                    .get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The attachments cannot be deleted"));
                        }
                        default -> {
                            return Mono.error(new IllegalStateException("Unexpected value: " + contentType));
                        }
                    }
                    DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                            .bucket(getBucketName(contentType))
                            .key(key)
                            .build();
                    return Mono.fromFuture(client.deleteObject(deleteObjectRequest)).then();
                });
    }

    @Override
    public Mono<String> queryPresignedGetUrl(@NotNull Long requesterId,
                                             @NotNull ContentType contentType,
                                             String keyStr,
                                             @Nullable Long keyNum) {
        if (!isServing) {
            return ResponseExceptionPublisherPool.serverUnavailable();
        }
        return hasPermissionToGet(requesterId, contentType, keyNum)
                .flatMap(hasPermission -> {
                    if (!hasPermission) {
                        return ResponseExceptionPublisherPool.unauthorized();
                    }
                    return switch (contentType) {
                        case PROFILE, GROUP_PROFILE -> Mono
                                .error(ResponseException.get(ResponseStatusCode.REDUNDANT_REQUEST_FOR_PRESIGNED_PROFILE_URL));
                        case ATTACHMENT -> {
                            if (keyNum == null) {
                                yield Mono.error(ResponseException
                                        .get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The message ID must not be null"));
                            }
                            String key = keyStr == null
                                    ? keyNum.toString()
                                    : "%d/%s".formatted(keyNum, keyStr);
                            String url = presignedUrlForGet(getBucketName(contentType), key);
                            yield Mono.just(url);
                        }
                        default -> Mono.error(new IllegalStateException("Unexpected value: " + contentType));
                    };
                });
    }

    @Override
    public Mono<String> queryPresignedPutUrl(@NotNull Long requesterId,
                                             @NotNull ContentType contentType,
                                             @Nullable String keyStr,
                                             @Nullable Long keyNum,
                                             long contentLength) {
        if (!isServing) {
            return ResponseExceptionPublisherPool.serverUnavailable();
        }
        int sizeLimit = switch (contentType) {
            case PROFILE -> storageProperties.getProfileSizeLimit();
            case GROUP_PROFILE -> storageProperties.getGroupProfileSizeLimit();
            case ATTACHMENT -> storageProperties.getAttachmentSizeLimit();
            default -> throw new IllegalStateException("Unexpected value: " + contentType);
        };
        if (sizeLimit != 0 && contentLength > sizeLimit) {
            throw ResponseException.get(ResponseStatusCode.FILE_TOO_LARGE);
        }
        return hasPermissionToPut(requesterId, contentType, keyNum)
                .flatMap(hasPermission -> {
                    if (!hasPermission) {
                        return ResponseExceptionPublisherPool.unauthorized();
                    }
                    String type;
                    String objectKey;
                    switch (contentType) {
                        case PROFILE -> {
                            type = storageProperties.getProfileContentType();
                            objectKey = requesterId.toString();
                        }
                        case GROUP_PROFILE -> {
                            type = storageProperties.getGroupProfileContentType();
                            if (keyNum == null) {
                                return Mono.error(ResponseException
                                        .get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The group ID must not be null"));
                            }
                            objectKey = keyNum.toString();
                        }
                        case ATTACHMENT -> {
                            type = storageProperties.getAttachmentContentType();
                            if (keyNum == null) {
                                return Mono.error(ResponseException
                                        .get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The message ID must not be null"));
                            }
                            objectKey = keyStr == null
                                    ? keyNum.toString()
                                    : "%d/%s".formatted(keyNum, keyStr);
                        }
                        default -> {
                            return Mono.error(new IllegalStateException("Unexpected value: " + contentType));
                        }
                    }
                    String url = presignedPutUrl(getBucketName(contentType), objectKey, type);
                    return Mono.just(url);
                });
    }

    private void setUp() {
        minioProperties = loadProperties(MinioProperties.class);
        if (!minioProperties.isEnabled()) {
            return;
        }
        ApplicationContext context = getContext();
        TurmsProperties turmsProperties = context.getBean(TurmsProperties.class);
        storageProperties = turmsProperties.getService().getStorage();
        messageService = context.getBean(MessageService.class);
        groupMemberService = context.getBean(GroupMemberService.class);

        initClient(minioProperties.getEndpoint(), minioProperties.getRegion(),
                minioProperties.getAccessKey(), minioProperties.getSecretKey());
        try {
            initBuckets();
            isServing = true;
        } catch (Exception e) {
            if (!minioProperties.getRetry().isEnabled()) {
                LOGGER.warn("The MinIO client failed to initialize");
                return;
            }
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(new Runnable() {
                int currentRetryTimes;

                @Override
                public void run() {
                    currentRetryTimes++;
                    int maxAttempts = minioProperties.getRetry().getMaxAttempts();
                    if (maxAttempts > 0 && currentRetryTimes > maxAttempts) {
                        LOGGER.warn("The MinIO client failed to initialize");
                        executor.shutdown();
                        return;
                    }
                    try {
                        initBuckets();
                        isServing = true;
                    } catch (Exception ignored) {
                    }
                }
            }, minioProperties.getRetry().getInitialInterval(), minioProperties.getRetry().getInterval(), TimeUnit.SECONDS);
        }
    }

    private void initClient(String endpointStr, String regionStr, String accessKey, String secretKey) {
        URI endpoint = URI.create(endpointStr);
        Region region = Region.of(regionStr);
        AwsCredentialsProvider credentialsProvider = () -> AwsBasicCredentials.create(accessKey, secretKey);
        SdkAsyncHttpClient httpClient = NettyNioAsyncHttpClient.builder()
                .writeTimeout(Duration.ZERO)
                .maxConcurrency(64)
                .build();
        S3Configuration configuration = S3Configuration.builder()
                .checksumValidationEnabled(false)
                .chunkedEncodingEnabled(true)
                .build();
        client = S3AsyncClient.builder()
                .httpClient(httpClient)
                .endpointOverride(endpoint)
                .region(region)
                .credentialsProvider(credentialsProvider)
                .serviceConfiguration(configuration)
                .build();
        presigner = S3Presigner.builder()
                .endpointOverride(endpoint)
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();
        LOGGER.info("The MinIO client is connecting to: {}", endpoint);
    }

    private void initBuckets() throws InterruptedException, ExecutionException, TimeoutException {
        for (ContentType type : ContentType.values()) {
            if (type != ContentType.UNRECOGNIZED) {
                boolean exists = bucketExists(type);
                String bucket = getBucketName(type);
                if (!exists) {
                    LOGGER.info("Bucket {} is being created", bucket);
                    createBucket(type);
                    putBucketPolicy(type);
                    putBucketLifecycleConfig(type);
                    LOGGER.info("Bucket {} is created", bucket);
                } else {
                    LOGGER.info("Bucket {} has already existed", bucket);
                }
            }
        }
    }

    private void createBucket(ContentType contentType) throws InterruptedException, ExecutionException, TimeoutException {
        CreateBucketRequest request = CreateBucketRequest.builder()
                .bucket(getBucketName(contentType))
                .build();
        client.createBucket(request).get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    private void putBucketPolicy(ContentType contentType) throws InterruptedException, ExecutionException, TimeoutException {
        if (contentType == ContentType.PROFILE || contentType == ContentType.GROUP_PROFILE) {
            String bucket = getBucketName(contentType);
            String policy = """
                    {
                      "Version": "2012-10-17",
                      "Statement": [
                        {
                          "Action": [
                            "s3:GetObject"
                          ],
                          "Effect": "Allow",
                          "Resource": "arn:aws:s3:::%s/*",
                          "Principal": "*"
                        }
                      ]
                    }""".formatted(bucket);
            PutBucketPolicyRequest policyRequest = PutBucketPolicyRequest.builder()
                    .bucket(bucket)
                    .policy(policy)
                    .build();
            client.putBucketPolicy(policyRequest).get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        }
    }

    private void putBucketLifecycleConfig(ContentType contentType) throws InterruptedException, ExecutionException, TimeoutException {
        if (contentType != ContentType.UNRECOGNIZED) {
            int days = switch (contentType) {
                case PROFILE -> storageProperties.getProfileExpiration();
                case GROUP_PROFILE -> storageProperties.getGroupProfileExpiration();
                case ATTACHMENT -> storageProperties.getAttachmentExpiration();
                default -> throw new IllegalStateException("Unexpected value: " + contentType);
            };
            if (days > 0) {
                LifecycleExpiration expiration = LifecycleExpiration.builder().days(days).build();
                LifecycleRule rule = LifecycleRule.builder()
                        .expiration(expiration)
                        .status(ExpirationStatus.ENABLED)
                        .build();
                BucketLifecycleConfiguration configuration = BucketLifecycleConfiguration.builder()
                        .rules(rule)
                        .build();
                PutBucketLifecycleConfigurationRequest request = PutBucketLifecycleConfigurationRequest.builder()
                        .bucket(getBucketName(contentType))
                        .lifecycleConfiguration(configuration)
                        .build();
                client.putBucketLifecycleConfiguration(request).get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
            }
        }
    }

    private boolean bucketExists(ContentType contentType) throws InterruptedException, ExecutionException, TimeoutException {
        try {
            HeadBucketRequest request = HeadBucketRequest.builder()
                    .bucket(getBucketName(contentType))
                    .build();
            HeadBucketResponse response = client.headBucket(request).get(TIMEOUT_SECONDS, TimeUnit.SECONDS);
            return 200 == response.sdkHttpResponse().statusCode();
        } catch (Exception e) {
            if (e.getCause() instanceof NoSuchBucketException) {
                return false;
            }
            throw e;
        }
    }

    private String presignedUrlForGet(@NotNull String bucket, @NotNull String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();
        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(getObjectRequest)
                .signatureDuration(storageProperties.getSignatureDurationForGet())
                .build();
        PresignedGetObjectRequest request = presigner.presignGetObject(presignRequest);
        return request.url().toString();
    }

    private String presignedPutUrl(@NotNull String bucket, @NotNull String key, @Nullable String contentType) {
        PutObjectRequest.Builder builder = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key);
        if (contentType != null && !contentType.equals(MimeTypeUtils.ALL_VALUE)) {
            builder.contentType(contentType);
        }
        PutObjectRequest putObjectRequest = builder.build();
        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .putObjectRequest(putObjectRequest)
                .signatureDuration(storageProperties.getSignatureDurationForPut())
                .build();
        PresignedPutObjectRequest request = presigner.presignPutObject(presignRequest);
        return request.url().toString();
    }

    private String getBucketName(ContentType contentType) {
        return contentType.name().toLowerCase().replace("_", "-");
    }

    // Permission

    private Mono<Boolean> hasPermissionToGet(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable Long keyNum) {
        return switch (contentType) {
            case PROFILE, GROUP_PROFILE -> PublisherPool.TRUE;
            case ATTACHMENT -> {
                if (keyNum == null) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The message ID must not be null");
                }
                yield messageService.isMessageRecipientOrSender(keyNum, requesterId);
            }
            default -> throw new IllegalStateException("Unexpected value: " + contentType);
        };
    }

    private Mono<Boolean> hasPermissionToPut(@NotNull Long requesterId,
                                             @NotNull ContentType contentType,
                                             @Nullable Long keyNum) {
        return switch (contentType) {
            case PROFILE -> PublisherPool.TRUE;
            case GROUP_PROFILE -> {
                if (keyNum == null) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The group ID must not be null");
                }
                yield groupMemberService.isOwnerOrManager(requesterId, keyNum);
            }
            case ATTACHMENT -> {
                if (keyNum == null) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The message ID must not be null");
                }
                yield messageService.isMessageRecipientOrSender(keyNum, requesterId);
            }
            default -> throw new IllegalStateException("Unexpected value: " + contentType);
        };
    }

    private Mono<Boolean> hasPermissionToDelete(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable Long keyNum) {
        return switch (contentType) {
            case PROFILE -> PublisherPool.TRUE;
            case GROUP_PROFILE -> {
                if (keyNum == null) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, "The group ID must not be null");
                }
                yield groupMemberService.isOwnerOrManager(requesterId, keyNum);
            }
            case ATTACHMENT -> PublisherPool.FALSE;
            default -> throw new IllegalStateException("Unexpected value: " + contentType);
        };
    }

}
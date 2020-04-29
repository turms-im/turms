package im.turms.plugin.impl;

import im.turms.common.TurmsStatusCode;
import im.turms.common.constant.ContentType;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.turms.plugin.StorageServiceProvider;
import im.turms.turms.plugin.TurmsPlugin;
import im.turms.turms.property.TurmsProperties;
import im.turms.turms.service.group.GroupMemberService;
import im.turms.turms.service.message.MessageService;
import org.pf4j.Extension;
import org.pf4j.PluginWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.Duration;
import java.util.concurrent.*;

public class TurmsMinioPlugin extends TurmsPlugin {
    public TurmsMinioPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class MinioStorageServiceProvider extends StorageServiceProvider {
        private static final Logger log = LoggerFactory.getLogger(MinioStorageServiceProvider.class);

        private static final int TIMEOUT = 10;
        private static final int DEFAULT_RETRY_INTERVAL = 30;
        private static final int DEFAULT_RETRY_MAX_ATTEMPTS = 3;

        private S3AsyncClient client;
        private S3Presigner presigner;
        private TurmsProperties turmsProperties;
        private MessageService messageService;
        private MinioProperties minioProperties;
        private GroupMemberService groupMemberService;

        @Override
        public void setContext(ApplicationContext context) throws Exception {
            super.setContext(context);
            setUp();
        }

        @Override
        public Mono<Void> deleteResource(@NotNull Long requesterId, @NotNull ContentType contentType, String keyStr, @Nullable Long keyNum) {
            return hasPermissionToDelete(requesterId, contentType, keyStr, keyNum)
                    .flatMap(hasPermission -> {
                        if (hasPermission) {
                            String key;
                            switch (contentType) {
                                case PROFILE:
                                    key = requesterId.toString();
                                    break;
                                case GROUP_PROFILE:
                                    if (keyNum != null) {
                                        key = keyNum.toString();
                                    } else {
                                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The group ID must not be null"));
                                    }
                                    break;
                                case ATTACHMENT:
                                    return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The attachments cannot be deleted"));
                                default:
                                    return Mono.error(new IllegalStateException("Unexpected value: " + contentType));
                            }
                            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                                    .bucket(getBucketName(contentType))
                                    .key(key)
                                    .build();
                            return Mono.fromFuture(client.deleteObject(deleteObjectRequest)).then();
                        } else {
                            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                        }
                    });
        }

        @Override
        public Mono<String> queryPresignedGetUrl(@NotNull Long requesterId, @NotNull ContentType contentType, String keyStr, @Nullable Long keyNum) {
            return hasPermissionToGet(requesterId, contentType, keyStr, keyNum)
                    .flatMap(hasPermission -> {
                        if (hasPermission) {
                            switch (contentType) {
                                case PROFILE:
                                case GROUP_PROFILE:
                                    return Mono.error(TurmsBusinessException.get(TurmsStatusCode.REDUNDANT_REQUEST));
                                case ATTACHMENT:
                                    if (keyNum != null) {
                                        String key;
                                        if (keyStr != null) {
                                            key = String.format("%d/%s", keyNum, keyStr);
                                        } else {
                                            key = keyNum.toString();
                                        }
                                        String url = presignedUrlForGet(getBucketName(contentType), key);
                                        return Mono.just(url);
                                    } else {
                                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The message ID must not be null"));
                                    }
                                default:
                                    return Mono.error(new IllegalStateException("Unexpected value: " + contentType));
                            }
                        } else {
                            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                        }
                    });
        }

        @Override
        public Mono<String> queryPresignedPutUrl(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable String keyStr, @Nullable Long keyNum, long contentLength) {
            int sizeLimit;
            switch (contentType) {
                case PROFILE:
                    sizeLimit = turmsProperties.getStorage().getProfileSizeLimit();
                    break;
                case GROUP_PROFILE:
                    sizeLimit = turmsProperties.getStorage().getGroupProfileSizeLimit();
                    break;
                case ATTACHMENT:
                    sizeLimit = turmsProperties.getStorage().getAttachmentSizeLimit();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + contentType);
            }
            if (sizeLimit == 0 || contentLength <= sizeLimit) {
                return hasPermissionToPut(requesterId, contentType, keyStr, keyNum)
                        .flatMap(hasPermission -> {
                            if (hasPermission) {
                                String type;
                                String objectKey;
                                switch (contentType) {
                                    case PROFILE:
                                        type = turmsProperties.getStorage().getProfileContentType();
                                        objectKey = requesterId.toString();
                                        break;
                                    case GROUP_PROFILE:
                                        type = turmsProperties.getStorage().getGroupProfileContentType();
                                        if (keyNum != null) {
                                            objectKey = keyNum.toString();
                                        } else {
                                            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The group ID must not be null"));
                                        }
                                        break;
                                    case ATTACHMENT:
                                        type = turmsProperties.getStorage().getAttachmentContentType();
                                        if (keyNum != null) {
                                            if (keyStr != null) {
                                                objectKey = String.format("%d/%s", keyNum, keyStr);
                                            } else {
                                                objectKey = keyNum.toString();
                                            }
                                        } else {
                                            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The message ID must not be null"));
                                        }
                                        break;
                                    default:
                                        return Mono.error(new IllegalStateException("Unexpected value: " + contentType));
                                }
                                String url = presignedPutUrl(getBucketName(contentType), objectKey, type);
                                return Mono.just(url);
                            } else {
                                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                            }
                        });
            } else {
                throw TurmsBusinessException.get(TurmsStatusCode.FILE_TOO_LARGE);
            }
        }

        private void setUp() {
            ApplicationContext context = getContext();
            minioProperties = new MinioProperties(context.getEnvironment());
            if (minioProperties.isEnabled()) {
                turmsProperties = context.getBean(TurmsProperties.class);
                messageService = context.getBean(MessageService.class);
                groupMemberService = context.getBean(GroupMemberService.class);

                initClient(minioProperties.getEndpoint(), minioProperties.getRegion(),
                        minioProperties.getAccessKey(), minioProperties.getSecretKey());
                try {
                    initBuckets();
                    setServing(true);
                } catch (Exception e) {
                    if (minioProperties.getRetry().isEnabled()) {
                        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
                        executor.scheduleAtFixedRate(new Runnable() {
                            int currentRetryTimes;

                            @Override
                            public void run() {
                                currentRetryTimes++;
                                int maxAttempts = minioProperties.getRetry().getMaxAttempts();
                                if (maxAttempts > 0 && currentRetryTimes > maxAttempts) {
                                    log.warn("The MinIO client failed to initialize");
                                    executor.shutdown();
                                    throw new RuntimeException("The MinIO client failed to initialize");
                                }
                                try {
                                    initBuckets();
                                    setServing(true);
                                } catch (Exception ignored) {
                                }
                            }
                        }, minioProperties.getRetry().getInitialInterval(), minioProperties.getRetry().getInterval(), TimeUnit.SECONDS);
                    } else {
                        log.warn("The MinIO client failed to initialize");
                    }
                }
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
            log.info("The MinIO client is connecting to: {}", endpoint);
        }

        private void initBuckets() throws InterruptedException, ExecutionException, TimeoutException {
            for (ContentType type : ContentType.values()) {
                if (type != ContentType.UNRECOGNIZED) {
                    boolean exists = bucketExists(type);
                    String bucket = getBucketName(type);
                    if (!exists) {
                        log.info("Bucket {} is being created", bucket);
                        createBucket(type);
                        putBucketPolicy(type);
                        putBucketLifecycleConfig(type);
                        log.info("Bucket {} is created", bucket);
                    } else {
                        log.info("Bucket {} has already existed", bucket);
                    }
                }
            }
        }

        private void createBucket(ContentType contentType) throws InterruptedException, ExecutionException, TimeoutException {
            CreateBucketRequest request = CreateBucketRequest.builder()
                    .bucket(getBucketName(contentType))
                    .build();
            client.createBucket(request).get(TIMEOUT, TimeUnit.SECONDS);
        }

        private void putBucketPolicy(ContentType contentType) throws InterruptedException, ExecutionException, TimeoutException {
            if (contentType == ContentType.PROFILE || contentType == ContentType.GROUP_PROFILE) {
                String bucket = getBucketName(contentType);
                String policy = "{\n" +
                        "  \"Version\": \"2012-10-17\",\n" +
                        "  \"Statement\": [\n" +
                        "    {\n" +
                        "      \"Action\": [\n" +
                        "        \"s3:GetObject\"\n" +
                        "      ],\n" +
                        "      \"Effect\": \"Allow\",\n" +
                        "      \"Resource\": \"arn:aws:s3:::" + bucket + "/*\",\n" +
                        "      \"Principal\": \"*\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}";
                PutBucketPolicyRequest policyRequest = PutBucketPolicyRequest.builder()
                        .bucket(bucket)
                        .policy(policy)
                        .build();
                client.putBucketPolicy(policyRequest).get(TIMEOUT, TimeUnit.SECONDS);
            }
        }

        private void putBucketLifecycleConfig(ContentType contentType) throws InterruptedException, ExecutionException, TimeoutException {
            if (contentType != ContentType.UNRECOGNIZED) {
                int days;
                switch (contentType) {
                    case PROFILE:
                        days = turmsProperties.getStorage().getProfileExpiration();
                        break;
                    case GROUP_PROFILE:
                        days = turmsProperties.getStorage().getGroupProfileExpiration();
                        break;
                    case ATTACHMENT:
                        days = turmsProperties.getStorage().getAttachmentExpiration();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + contentType);
                }
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
                    client.putBucketLifecycleConfiguration(request).get(TIMEOUT, TimeUnit.SECONDS);
                }
            }
        }

        private boolean bucketExists(ContentType contentType) throws InterruptedException, ExecutionException, TimeoutException {
            try {
                HeadBucketRequest request = HeadBucketRequest.builder()
                        .bucket(getBucketName(contentType))
                        .build();
                HeadBucketResponse response = client.headBucket(request).get(TIMEOUT, TimeUnit.SECONDS);
                return 200 == response.sdkHttpResponse().statusCode();
            } catch (Exception e) {
                if (e.getCause() instanceof NoSuchBucketException) {
                    return false;
                } else {
                    throw e;
                }
            }
        }

        private String presignedUrlForGet(@NotNull String bucket, @NotNull String key) {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();
            GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                    .getObjectRequest(getObjectRequest)
                    .signatureDuration(turmsProperties.getStorage().getSignatureDurationForGet())
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
                    .signatureDuration(turmsProperties.getStorage().getSignatureDurationForPut())
                    .build();
            PresignedPutObjectRequest request = presigner.presignPutObject(presignRequest);
            return request.url().toString();
        }

        private String getBucketName(ContentType contentType) {
            return contentType.name().toLowerCase().replace("_", "-");
        }

        // Permission

        private Mono<Boolean> hasPermissionToGet(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable String keyStr, @Nullable Long keyNum) {
            switch (contentType) {
                case PROFILE:
                case GROUP_PROFILE:
                    return Mono.just(true);
                case ATTACHMENT:
                    if (keyNum != null) {
                        return messageService.isMessageSentToUserOrByUser(keyNum, requesterId);
                    } else {
                        throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The message ID must not be null");
                    }
                default:
                    throw new IllegalStateException("Unexpected value: " + contentType);
            }
        }

        private Mono<Boolean> hasPermissionToPut(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable String keyStr, @Nullable Long keyNum) {
            switch (contentType) {
                case PROFILE:
                    return Mono.just(true);
                case GROUP_PROFILE:
                    if (keyNum != null) {
                        return groupMemberService.isOwnerOrManager(requesterId, keyNum);
                    } else {
                        throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The group ID must not be null");
                    }
                case ATTACHMENT:
                    if (keyNum != null) {
                        return messageService.isMessageSentToUserOrByUser(keyNum, requesterId);
                    } else {
                        throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The message ID must not be null");
                    }
                default:
                    throw new IllegalStateException("Unexpected value: " + contentType);
            }
        }

        private Mono<Boolean> hasPermissionToDelete(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable String keyStr, @Nullable Long keyNum) {
            switch (contentType) {
                case PROFILE:
                    return Mono.just(true);
                case GROUP_PROFILE:
                    if (keyNum != null) {
                        return groupMemberService.isOwnerOrManager(requesterId, keyNum);
                    } else {
                        throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The group ID must not be null");
                    }
                case ATTACHMENT:
                    return Mono.just(false);
                default:
                    throw new IllegalStateException("Unexpected value: " + contentType);
            }
        }
    }
}

package im.turms.plugin.impl;

import im.turms.common.TurmsStatusCode;
import im.turms.common.constant.ContentType;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.turms.plugin.StorageServiceProvider;
import im.turms.turms.plugin.TurmsPlugin;
import im.turms.turms.property.TurmsProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.pf4j.Extension;
import org.pf4j.PluginWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TurmsMinioPlugin extends TurmsPlugin {
    public TurmsMinioPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Extension
    public static class MinioStorageServiceProvider extends StorageServiceProvider {
        S3AsyncClient client;
        S3Presigner presigner;
        TurmsProperties turmsProperties;

        @Override
        public void setContext(ApplicationContext context) throws Exception {
            super.setContext(context);
            setUp();
        }

        @Override
        public Mono<Boolean> deleteResource(@NotNull Long requesterId, @NotNull ContentType contentType, String keyStr, @Nullable Long keyNum) {
            String key;
            switch (contentType) {
                case PROFILE:
                    key = requesterId.toString();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + contentType);
            }
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(contentType.name().toLowerCase())
                    .key(key)
                    .build();
            return Mono.fromFuture(client.deleteObject(deleteObjectRequest))
                    .thenReturn(true);
        }

        @Override
        public Mono<String> queryPresignedGetUrl(@NotNull Long requesterId, @NotNull ContentType contentType, String keyStr, @Nullable Long keyNum) {
            switch (contentType) {
                case PROFILE:
                    throw TurmsBusinessException.get(TurmsStatusCode.REDUNDANT_REQUEST);
                case ATTACHMENT:
                    if (keyStr != null && keyNum != null) {
                        String key = String.format("%d/%s", keyNum, keyStr);
                        String url = presignedUrlForGet(contentType.name().toLowerCase(), key);
                        return Mono.just(url);
                    } else {
                        throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS);
                    }
                default:
                    throw new IllegalStateException("Unexpected value: " + contentType);
            }
        }

        @Override
        public Mono<String> queryPresignedPutUrl(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable String keyStr, @Nullable Long keyNum, long contentLength) {
            String type;
            String objectKey;
            switch (contentType) {
                case PROFILE:
                    type = turmsProperties.getStorage().getProfileContentType();
                    objectKey = requesterId.toString();
                    break;
                case ATTACHMENT:
                    type = turmsProperties.getStorage().getAttachmentContentType();
                    if (keyNum != null) {
                        objectKey = String.format("%d/%s", keyNum, RandomStringUtils.random(8));
                    } else {
                        throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + contentType);
            }
            String url = presignedPutUrl(contentType.name().toLowerCase(), objectKey, type);
            return Mono.just(url);
        }

        private void setUp() throws InterruptedException, ExecutionException, TimeoutException {
            turmsProperties = getContext().getBean(TurmsProperties.class);
            Environment env = getContext().getEnvironment();
            String endpointStr = env.getProperty("turms.storage.minio.endpoint", "http://localhost:9000");
            String accessKey = env.getProperty("turms.storage.minio.accessKey", "minioadmin");
            String secretKey = env.getProperty("turms.storage.minio.secretKey", "minioadmin");
            String regionStr = env.getProperty("turms.storage.minio.region", Region.AWS_GLOBAL.toString());
            Region region = Region.of(regionStr);
            URI endpoint = URI.create(endpointStr);

            SdkAsyncHttpClient httpClient = NettyNioAsyncHttpClient.builder()
                    .writeTimeout(Duration.ZERO)
                    .maxConcurrency(64)
                    .build();
            S3Configuration configuration = S3Configuration.builder()
                    .checksumValidationEnabled(false)
                    .chunkedEncodingEnabled(true)
                    .build();
            AwsCredentialsProvider credentialsProvider = () -> AwsBasicCredentials.create(accessKey, secretKey);
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
            initMinio();
        }

        private void initMinio() throws InterruptedException, ExecutionException, TimeoutException {
            for (ContentType type : ContentType.values()) {
                if (type != ContentType.UNRECOGNIZED) {
                    boolean exists = bucketExists(type);
                    if (!exists) {
                        createBucket(type);
                        putBucketLifecycleConfig(type);
                    }
                }
            }
        }

        private void createBucket(ContentType contentType) throws InterruptedException, ExecutionException, TimeoutException {
            BucketCannedACL acl = null;
            switch (contentType) {
                case PROFILE:
                    acl = BucketCannedACL.PUBLIC_READ;
                    break;
                case ATTACHMENT:
                    acl = BucketCannedACL.PRIVATE;
                    break;
            }
            CreateBucketRequest request = CreateBucketRequest.builder()
                    .bucket(contentType.name().toLowerCase())
                    .acl(acl)
                    .build();
            client.createBucket(request).get(30, TimeUnit.SECONDS);
        }

        private void putBucketLifecycleConfig(ContentType contentType) throws InterruptedException, ExecutionException, TimeoutException {
            if (contentType != ContentType.UNRECOGNIZED) {
                int days;
                switch (contentType) {
                    case PROFILE:
                        days = turmsProperties.getStorage().getProfileExpiration();
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
                            .build();
                    BucketLifecycleConfiguration configuration = BucketLifecycleConfiguration.builder()
                            .rules(rule)
                            .build();
                    PutBucketLifecycleConfigurationRequest request = PutBucketLifecycleConfigurationRequest.builder()
                            .bucket(contentType.name().toLowerCase())
                            .lifecycleConfiguration(configuration)
                            .build();
                    client.putBucketLifecycleConfiguration(request).get(30, TimeUnit.SECONDS);
                }
            }
        }

        private boolean bucketExists(ContentType contentType) throws InterruptedException, ExecutionException, TimeoutException {
            try {
                HeadBucketRequest request = HeadBucketRequest.builder()
                        .bucket(contentType.name().toLowerCase())
                        .build();
                HeadBucketResponse response = client.headBucket(request).get(30, TimeUnit.SECONDS);
                return 200 == response.sdkHttpResponse().statusCode();
            } catch (NoSuchBucketException e) {
                return false;
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
    }
}

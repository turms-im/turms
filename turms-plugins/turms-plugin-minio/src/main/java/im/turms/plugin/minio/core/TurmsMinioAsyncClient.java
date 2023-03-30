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

package im.turms.plugin.minio.core;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.google.common.collect.Multimap;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioAsyncClient;
import io.minio.Signer;
import io.minio.credentials.Credentials;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.XmlParserException;
import io.minio.http.HttpUtils;
import io.minio.http.Method;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * @author James Chen
 */
public class TurmsMinioAsyncClient extends MinioAsyncClient {

    public TurmsMinioAsyncClient(MinioAsyncClient client) {
        super(client);
    }

    /**
     * @see MinioAsyncClient#getPresignedObjectUrl(GetPresignedObjectUrlArgs)
     */
    public CompletableFuture<String> getPresignedObjectUrlAsync(GetPresignedObjectUrlArgs args)
            throws InsufficientDataException, InternalException, InvalidKeyException, IOException,
            NoSuchAlgorithmException, XmlParserException {
        checkArgs(args);
        byte[] body = (args.method() == Method.PUT || args.method() == Method.POST)
                ? HttpUtils.EMPTY_BODY
                : null;
        Multimap<String, String> queryParams = newMultimap(args.extraQueryParams());
        String versionId = args.versionId();
        if (versionId != null) {
            queryParams.put("versionId", versionId);
        }
        return getRegionAsync(args.bucket(), args.region()).thenApply(region -> {
            try {
                if (provider == null) {
                    HttpUrl url = buildUrl(args
                            .method(), args.bucket(), args.object(), region, queryParams);
                    return url.toString();
                }
                Credentials credentials = provider.fetch();
                if (credentials.sessionToken() != null) {
                    queryParams.put("X-Amz-Security-Token", credentials.sessionToken());
                }
                HttpUrl url =
                        buildUrl(args.method(), args.bucket(), args.object(), region, queryParams);
                Request request = createRequest(url,
                        args.method(),
                        args.extraHeaders() == null
                                ? null
                                : httpHeaders(args.extraHeaders()),
                        body,
                        0,
                        credentials);
                url = Signer.presignV4(request,
                        region,
                        credentials.accessKey(),
                        credentials.secretKey(),
                        args.expiry());
                return url.toString();
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        });
    }

}
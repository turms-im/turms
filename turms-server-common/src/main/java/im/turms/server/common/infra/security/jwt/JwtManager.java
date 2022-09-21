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

package im.turms.server.common.infra.security.jwt;

import com.fasterxml.jackson.databind.ObjectReader;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.json.JsonCodecPool;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.property.env.gateway.authentication.JwtAuthenticationVerificationProperties;
import im.turms.server.common.infra.property.env.gateway.authentication.JwtKeyAlgorithmProperties;
import im.turms.server.common.infra.property.env.gateway.authentication.JwtP12KeyStoreProperties;
import im.turms.server.common.infra.security.CertificateUtil;
import im.turms.server.common.infra.security.jwt.algorithm.EcdsaAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.HmacAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.JwtAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.RsaAlgorithm;
import im.turms.server.common.infra.security.jwt.exception.CorruptedJwtException;
import im.turms.server.common.infra.security.jwt.exception.SignatureVerificationException;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * @author James Chen
 */
public class JwtManager {

    private static final Base64.Decoder URL_DECODER = Base64.getUrlDecoder();
    private static final ObjectReader PAYLOAD_READER;
    private static final ObjectReader HEADER_READER;

    private final Map<String, JwtAlgorithm> nameToAlgorithm;
    private final List<Predicate<JwtPayload>> verifications = new ArrayList<>(4);

    static {
        PAYLOAD_READER = JsonCodecPool.MAPPER.readerFor(JwtPayload.class);
        HEADER_READER = JsonCodecPool.MAPPER.readerFor(JwtHeader.class);
    }

    @SneakyThrows
    public JwtManager(JwtAuthenticationVerificationProperties verificationProperties,
                      JwtKeyAlgorithmProperties ecdsa256,
                      JwtKeyAlgorithmProperties ecdsa384,
                      JwtKeyAlgorithmProperties ecdsa512,
                      JwtKeyAlgorithmProperties rsa256,
                      JwtKeyAlgorithmProperties rsa384,
                      JwtKeyAlgorithmProperties rsa512,
                      String hmac256SecretFilePath,
                      String hmac384SecretFilePath,
                      String hmac512SecretFilePath) {
        String issuer = verificationProperties.getIssuer();
        if (!StringUtil.isBlank(issuer)) {
            verifications.add(payload -> issuer.equals(payload.issuer()));
        }
        String subject = verificationProperties.getSubject();
        if (!StringUtil.isBlank(subject)) {
            verifications.add(payload -> subject.equals(payload.subject()));
        }
        String audience = verificationProperties.getAudience();
        if (!StringUtil.isBlank(audience)) {
            verifications.add(payload -> CollectionUtil.isNotEmpty(payload.audiences())
                    && payload.audiences().contains(audience));
        }
        Map<String, String> customPayloadClaims = verificationProperties.getCustomPayloadClaims();
        if (CollectionUtil.isNotEmpty(customPayloadClaims)) {
            verifications.add(payload -> CollectionUtil
                    .containsAllLooseComparison(payload.customClaims(), customPayloadClaims));
        }

        List<Map.Entry<String, JwtAlgorithm>> algorithms = new ArrayList<>(9);
        PublicKey publicKey;
        if ((publicKey = getPublicKey(CertificateUtil.ALGORITHM_RSA, rsa256)) != null) {
            algorithms.add(Map.entry("RS256", new RsaAlgorithm("RS256", "SHA256withRSA", (RSAPublicKey) publicKey)));
        }
        if ((publicKey = getPublicKey(CertificateUtil.ALGORITHM_RSA, rsa384)) != null) {
            algorithms.add(Map.entry("RS384", new RsaAlgorithm("RS384", "SHA384withRSA", (RSAPublicKey) publicKey)));
        }
        if ((publicKey = getPublicKey(CertificateUtil.ALGORITHM_RSA, rsa512)) != null) {
            algorithms.add(Map.entry("RS512", new RsaAlgorithm("RS512", "SHA512withRSA", (RSAPublicKey) publicKey)));
        }
        if ((publicKey = getPublicKey(CertificateUtil.ALGORITHM_EC, ecdsa256)) != null) {
            algorithms.add(Map.entry("ES256", new EcdsaAlgorithm("ES256", "SHA256withECDSA", 32, (ECPublicKey) publicKey)));
        }
        if ((publicKey = getPublicKey(CertificateUtil.ALGORITHM_EC, ecdsa384)) != null) {
            algorithms.add(Map.entry("ES384", new EcdsaAlgorithm("ES384", "SHA384withECDSA", 48, (ECPublicKey) publicKey)));
        }
        if ((publicKey = getPublicKey(CertificateUtil.ALGORITHM_EC, ecdsa512)) != null) {
            algorithms.add(Map.entry("ES512", new EcdsaAlgorithm("ES512", "SHA512withECDSA", 66, (ECPublicKey) publicKey)));
        }
        if (!StringUtil.isBlank(hmac256SecretFilePath)) {
            algorithms.add(Map.entry("HS256", new HmacAlgorithm("HS256", "HmacSHA256", Files.readAllBytes(Path.of(hmac256SecretFilePath)))));
        }
        if (!StringUtil.isBlank(hmac384SecretFilePath)) {
            algorithms.add(Map.entry("HS384", new HmacAlgorithm("HS384", "HmacSHA384", Files.readAllBytes(Path.of(hmac384SecretFilePath)))));
        }
        if (!StringUtil.isBlank(hmac512SecretFilePath)) {
            algorithms.add(Map.entry("HS512", new HmacAlgorithm("HS512", "HmacSHA512", Files.readAllBytes(Path.of(hmac512SecretFilePath)))));
        }
        nameToAlgorithm = Map.ofEntries(algorithms.toArray(new Map.Entry[0]));
    }

    private static <T extends PublicKey> T getPublicKey(String algorithm, JwtKeyAlgorithmProperties properties) {
        String pemFilePath = properties.getPemFilePath();
        if (!StringUtil.isBlank(pemFilePath)) {
            return (T) CertificateUtil.getPublicKeyFromPem(new File(pemFilePath), algorithm);
        }
        JwtP12KeyStoreProperties p12 = properties.getP12();
        String filePath = p12.getFilePath();
        if (!StringUtil.isBlank(filePath)) {
            return (T) CertificateUtil.getPublicKeyFromP12(new File(filePath), p12.getPassword(), p12.getAlias());
        }
        return null;
    }

    public Set<String> getSupportedAlgorithmNames() {
        return nameToAlgorithm.keySet();
    }

    public Jwt decode(String jwt) {
        List<String> parts = StringUtil.splitMultipleLatin1(jwt, (byte) '.');
        if (parts == null || parts.size() != 3) {
            throw new CorruptedJwtException("The JWT must have three parts");
        }
        byte[] encodedHeaderBytes = StringUtil.getBytes(parts.get(0));
        byte[] decodedHeaderBytes;
        try {
            decodedHeaderBytes = URL_DECODER.decode(encodedHeaderBytes);
        } catch (Exception e) {
            throw new CorruptedJwtException("The JWT header isn't a valid Base64-encoded string", e);
        }
        byte[] encodedPayloadBytes = StringUtil.getUTF8Bytes(parts.get(1));
        byte[] decodedPayloadBytes;
        try {
            decodedPayloadBytes = URL_DECODER.decode(encodedPayloadBytes);
        } catch (Exception e) {
            throw new CorruptedJwtException("The JWT payload isn't a valid Base64-encoded string", e);
        }
        JwtHeader header;
        try {
            header = HEADER_READER.readValue(decodedHeaderBytes);
        } catch (Exception e) {
            throw new CorruptedJwtException("Illegal JWT header format", e);
        }
        JwtPayload payload;
        try {
            payload = PAYLOAD_READER.readValue(decodedPayloadBytes);
        } catch (Exception e) {
            throw new CorruptedJwtException("Illegal JWT payload format", e);
        }
        String algorithmName = header.algorithm().toUpperCase();
        JwtAlgorithm algorithm = nameToAlgorithm.get(algorithmName);
        if (algorithm == null) {
            throw new UnsupportedOperationException("The " + algorithmName + " algorithm isn't supported");
        }
        Jwt jwtEntity = new Jwt(header,
                payload,
                encodedHeaderBytes,
                encodedPayloadBytes,
                URL_DECODER.decode(StringUtil.getBytes(parts.get(2))));
        for (Predicate<JwtPayload> verification : verifications) {
            if (!verification.test(jwtEntity.payload())) {
                throw new SignatureVerificationException();
            }
        }
        if (algorithm.verify(jwtEntity)) {
            return jwtEntity;
        }
        throw new SignatureVerificationException();
    }

}

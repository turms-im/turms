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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import jakarta.annotation.Nullable;

import com.fasterxml.jackson.databind.ObjectReader;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.json.JsonCodecPool;
import im.turms.server.common.infra.lang.AsciiCode;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtIdentityAccessManagementVerificationProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtKeyAlgorithmProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtP12KeyStoreProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.jwt.JwtSecretKeyAlgorithmProperties;
import im.turms.server.common.infra.security.CertificateUtil;
import im.turms.server.common.infra.security.jwt.algorithm.EcdsaAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.HmacAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.JwtAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.JwtAlgorithmDefinition;
import im.turms.server.common.infra.security.jwt.algorithm.RsaAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.RsaPssAlgorithm;
import im.turms.server.common.infra.security.jwt.exception.InvalidJwtException;
import im.turms.server.common.infra.security.jwt.exception.JwtSignatureVerificationException;

/**
 * @author James Chen
 */
public class JwtManager {

    private static final Base64.Decoder URL_DECODER = Base64.getUrlDecoder();
    private static final ObjectReader PAYLOAD_READER;
    private static final ObjectReader HEADER_READER;

    private final Map<String, JwtAlgorithm> nameToAlgorithm;
    private final List<Predicate<JwtPayload>> verifications = new ArrayList<>(3);

    static {
        PAYLOAD_READER = JsonCodecPool.MAPPER.readerFor(JwtPayload.class);
        HEADER_READER = JsonCodecPool.MAPPER.readerFor(JwtHeader.class);
    }

    public JwtManager(
            JwtIdentityAccessManagementVerificationProperties verificationProperties,
            JwtKeyAlgorithmProperties rsa256,
            JwtKeyAlgorithmProperties rsa384,
            JwtKeyAlgorithmProperties rsa512,
            JwtKeyAlgorithmProperties ps256,
            JwtKeyAlgorithmProperties ps384,
            JwtKeyAlgorithmProperties ps512,
            JwtKeyAlgorithmProperties ecdsa256,
            JwtKeyAlgorithmProperties ecdsa384,
            JwtKeyAlgorithmProperties ecdsa512,
            JwtSecretKeyAlgorithmProperties hmac256,
            JwtSecretKeyAlgorithmProperties hmac384,
            JwtSecretKeyAlgorithmProperties hmac512) {
        String issuer = verificationProperties.getIssuer();
        if (StringUtil.isNotBlank(issuer)) {
            verifications.add(payload -> issuer.equals(payload.issuer()));
        }
        String audience = verificationProperties.getAudience();
        if (StringUtil.isNotBlank(audience)) {
            verifications.add(payload -> CollectionUtil.isNotEmpty(payload.audiences())
                    && payload.audiences()
                            .contains(audience));
        }
        Map<String, String> customPayloadClaims = verificationProperties.getCustomPayloadClaims();
        if (CollectionUtil.isNotEmpty(customPayloadClaims)) {
            verifications.add(payload -> CollectionUtil
                    .containsAllLooseComparison(payload.customClaims(), customPayloadClaims));
        }

        List<Map.Entry<String, JwtAlgorithm>> algorithms = new ArrayList<>(12);
        PublicKey publicKey;
        byte[] secretKey;
        JwtAlgorithmDefinition definition;
        if ((publicKey = getRsaPublicKey(rsa256)) != null) {
            definition = JwtAlgorithmDefinition.RS256;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new RsaAlgorithm(definition, (RSAPublicKey) publicKey)));
        }
        if ((publicKey = getRsaPublicKey(rsa384)) != null) {
            definition = JwtAlgorithmDefinition.RS384;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new RsaAlgorithm(definition, (RSAPublicKey) publicKey)));
        }
        if ((publicKey = getRsaPublicKey(rsa512)) != null) {
            definition = JwtAlgorithmDefinition.RS512;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new RsaAlgorithm(definition, (RSAPublicKey) publicKey)));
        }
        if ((publicKey = getRsaPublicKey(ps256)) != null) {
            definition = JwtAlgorithmDefinition.PS256;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new RsaPssAlgorithm(
                            definition,
                            (RSAPublicKey) publicKey,
                            new PSSParameterSpec(
                                    MGF1ParameterSpec.SHA256.getDigestAlgorithm(),
                                    "MGF1",
                                    MGF1ParameterSpec.SHA256,
                                    32,
                                    1))));
        }
        if ((publicKey = getRsaPublicKey(ps384)) != null) {
            definition = JwtAlgorithmDefinition.PS384;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new RsaPssAlgorithm(
                            definition,
                            (RSAPublicKey) publicKey,
                            new PSSParameterSpec(
                                    MGF1ParameterSpec.SHA384.getDigestAlgorithm(),
                                    "MGF1",
                                    MGF1ParameterSpec.SHA384,
                                    48,
                                    1))));
        }
        if ((publicKey = getRsaPublicKey(ps512)) != null) {
            definition = JwtAlgorithmDefinition.PS512;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new RsaPssAlgorithm(
                            definition,
                            (RSAPublicKey) publicKey,
                            new PSSParameterSpec(
                                    MGF1ParameterSpec.SHA512.getDigestAlgorithm(),
                                    "MGF1",
                                    MGF1ParameterSpec.SHA512,
                                    64,
                                    1))));
        }
        if ((publicKey = getEcPublicKey(ecdsa256)) != null) {
            definition = JwtAlgorithmDefinition.ES256;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new EcdsaAlgorithm(definition, 32, (ECPublicKey) publicKey)));
        }
        if ((publicKey = getEcPublicKey(ecdsa384)) != null) {
            definition = JwtAlgorithmDefinition.ES384;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new EcdsaAlgorithm(definition, 48, (ECPublicKey) publicKey)));
        }
        if ((publicKey = getEcPublicKey(ecdsa512)) != null) {
            definition = JwtAlgorithmDefinition.ES512;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new EcdsaAlgorithm(definition, 66, (ECPublicKey) publicKey)));
        }
        definition = JwtAlgorithmDefinition.HS256;
        if ((secretKey = getSecretKey(definition.getJwtAlgorithmName(), hmac256)) != null) {
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new HmacAlgorithm(definition, secretKey)));
        }
        definition = JwtAlgorithmDefinition.HS384;
        if ((secretKey = getSecretKey(definition.getJwtAlgorithmName(), hmac384)) != null) {
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new HmacAlgorithm(definition, secretKey)));
        }
        definition = JwtAlgorithmDefinition.HS512;
        if ((secretKey = getSecretKey(definition.getJwtAlgorithmName(), hmac512)) != null) {
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new HmacAlgorithm(definition, secretKey)));
        }
        nameToAlgorithm = CollectionUtil.newImmutableMap(algorithms);
    }

    @Nullable
    private RSAPublicKey getRsaPublicKey(JwtKeyAlgorithmProperties properties) {
        return getPublicKey(CertificateUtil.ALGORITHM_RSA, properties);
    }

    @Nullable
    private ECPublicKey getEcPublicKey(JwtKeyAlgorithmProperties properties) {
        return getPublicKey(CertificateUtil.ALGORITHM_EC, properties);
    }

    @Nullable
    private byte[] getSecretKey(String algorithmName, JwtSecretKeyAlgorithmProperties properties) {
        if (StringUtil.isNotBlank(properties.getFilePath())) {
            try {
                return Files.readAllBytes(Path.of(properties.getFilePath()));
            } catch (IOException e) {
                throw new RuntimeException(
                        "Failed to read the secret key from the file path ("
                                + properties.getFilePath()
                                + ") for the algorithm: "
                                + algorithmName,
                        e);
            }
        }
        JwtP12KeyStoreProperties p12 = properties.getP12();
        String filePath = p12.getFilePath();
        if (StringUtil.isNotBlank(filePath)) {
            return CertificateUtil.getSecretKeyFromP12File(new File(filePath),
                    p12.getPassword(),
                    p12.getKeyAlias());
        }
        return null;
    }

    @Nullable
    private <T extends PublicKey> T getPublicKey(
            String algorithm,
            JwtKeyAlgorithmProperties properties) {
        String pemFilePath = properties.getPemFilePath();
        if (StringUtil.isNotBlank(pemFilePath)) {
            return (T) CertificateUtil.getPublicKeyFromPem(new File(pemFilePath), algorithm);
        }
        JwtP12KeyStoreProperties p12 = properties.getP12();
        String filePath = p12.getFilePath();
        if (StringUtil.isNotBlank(filePath)) {
            return (T) CertificateUtil.getPublicKeyFromP12File(new File(filePath),
                    p12.getPassword(),
                    p12.getKeyAlias());
        }
        return null;
    }

    public Set<String> getSupportedAlgorithmNames() {
        return nameToAlgorithm.keySet();
    }

    public Jwt decode(String jwt) throws InvalidJwtException, NoSuchAlgorithmException,
            JwtSignatureVerificationException {
        List<String> parts = StringUtil.splitMultipleLatin1(jwt, AsciiCode.PERIOD);
        if (parts == null || parts.size() != 3) {
            throw new InvalidJwtException("The input JWT must have three parts");
        }
        byte[] encodedHeaderBytes = StringUtil.getBytes(parts.get(0));
        byte[] encodedPayloadBytes = StringUtil.getBytes(parts.get(1));
        JwtHeader header = decodeHeader(encodedHeaderBytes);
        JwtPayload payload = decodePayload(encodedPayloadBytes);
        String algorithmName = header.algorithm();
        JwtAlgorithm algorithm = nameToAlgorithm.get(algorithmName);
        if (algorithm == null) {
            throw new NoSuchAlgorithmException(
                    "Unknown algorithm: \""
                            + algorithmName
                            + "\"");
        }
        for (Predicate<JwtPayload> verification : verifications) {
            if (!verification.test(payload)) {
                throw new JwtSignatureVerificationException("The input JWT signature is invalid");
            }
        }
        Jwt jwtEntity = new Jwt(
                header,
                payload,
                encodedHeaderBytes,
                encodedPayloadBytes,
                URL_DECODER.decode(StringUtil.getBytes(parts.get(2))));
        if (algorithm.verify(jwtEntity)) {
            return jwtEntity;
        }
        throw new JwtSignatureVerificationException("The input JWT signature is invalid");
    }

    private JwtHeader decodeHeader(byte[] encodedHeaderBytes) {
        byte[] decodedHeaderBytes;
        try {
            decodedHeaderBytes = URL_DECODER.decode(encodedHeaderBytes);
        } catch (Exception e) {
            throw new InvalidJwtException(
                    "The input JWT header is not a valid Base64-encoded string",
                    e);
        }
        try {
            return HEADER_READER.readValue(decodedHeaderBytes);
        } catch (Exception e) {
            throw new InvalidJwtException("Illegal JWT header format", e);
        }
    }

    private JwtPayload decodePayload(byte[] encodedPayloadBytes) {
        byte[] decodedPayloadBytes;
        try {
            decodedPayloadBytes = URL_DECODER.decode(encodedPayloadBytes);
        } catch (Exception e) {
            throw new InvalidJwtException(
                    "The input JWT payload is not a valid Base64-encoded string",
                    e);
        }
        try {
            return PAYLOAD_READER.readValue(decodedPayloadBytes);
        } catch (Exception e) {
            throw new InvalidJwtException("Illegal JWT payload format", e);
        }
    }

}

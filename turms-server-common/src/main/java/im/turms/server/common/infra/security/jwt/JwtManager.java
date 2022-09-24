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
import im.turms.server.common.infra.lang.AsciiCode;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.property.env.gateway.authentication.JwtAuthenticationVerificationProperties;
import im.turms.server.common.infra.property.env.gateway.authentication.JwtKeyAlgorithmProperties;
import im.turms.server.common.infra.property.env.gateway.authentication.JwtP12KeyStoreProperties;
import im.turms.server.common.infra.security.CertificateUtil;
import im.turms.server.common.infra.security.jwt.algorithm.EcdsaAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.HmacAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.JwtAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.JwtAlgorithmDefinition;
import im.turms.server.common.infra.security.jwt.algorithm.RsaAlgorithm;
import im.turms.server.common.infra.security.jwt.algorithm.RsaPssAlgorithm;
import im.turms.server.common.infra.security.jwt.exception.CorruptedJwtException;
import im.turms.server.common.infra.security.jwt.exception.SignatureVerificationException;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
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
                      JwtKeyAlgorithmProperties rsa256,
                      JwtKeyAlgorithmProperties rsa384,
                      JwtKeyAlgorithmProperties rsa512,
                      JwtKeyAlgorithmProperties ps256,
                      JwtKeyAlgorithmProperties ps384,
                      JwtKeyAlgorithmProperties ps512,
                      JwtKeyAlgorithmProperties ecdsa256,
                      JwtKeyAlgorithmProperties ecdsa384,
                      JwtKeyAlgorithmProperties ecdsa512,
                      String hmac256SecretFilePath,
                      String hmac384SecretFilePath,
                      String hmac512SecretFilePath) {
        String issuer = verificationProperties.getIssuer();
        if (StringUtil.isNotBlank(issuer)) {
            verifications.add(payload -> issuer.equals(payload.issuer()));
        }
        String subject = verificationProperties.getSubject();
        if (StringUtil.isNotBlank(subject)) {
            verifications.add(payload -> subject.equals(payload.subject()));
        }
        String audience = verificationProperties.getAudience();
        if (StringUtil.isNotBlank(audience)) {
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
                    new RsaPssAlgorithm(definition, (RSAPublicKey) publicKey,
                            new PSSParameterSpec(
                                    MGF1ParameterSpec.SHA256.getDigestAlgorithm(),
                                    "MGF1",
                                    MGF1ParameterSpec.SHA256,
                                    32, 1
                            ))));
        }
        if ((publicKey = getRsaPublicKey(ps384)) != null) {
            definition = JwtAlgorithmDefinition.PS384;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new RsaPssAlgorithm(definition, (RSAPublicKey) publicKey,
                            new PSSParameterSpec(
                                    MGF1ParameterSpec.SHA384.getDigestAlgorithm(),
                                    "MGF1",
                                    MGF1ParameterSpec.SHA384,
                                    48, 1
                            ))));
        }
        if ((publicKey = getRsaPublicKey(ps512)) != null) {
            definition = JwtAlgorithmDefinition.PS512;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new RsaPssAlgorithm(definition, (RSAPublicKey) publicKey,
                            new PSSParameterSpec(
                                    MGF1ParameterSpec.SHA512.getDigestAlgorithm(),
                                    "MGF1",
                                    MGF1ParameterSpec.SHA512,
                                    64, 1
                            ))));
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
        if (StringUtil.isNotBlank(hmac256SecretFilePath)) {
            definition = JwtAlgorithmDefinition.HS256;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new HmacAlgorithm(definition, Files.readAllBytes(Path.of(hmac256SecretFilePath)))));
        }
        if (StringUtil.isNotBlank(hmac384SecretFilePath)) {
            definition = JwtAlgorithmDefinition.HS384;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new HmacAlgorithm(definition, Files.readAllBytes(Path.of(hmac384SecretFilePath)))));
        }
        if (StringUtil.isNotBlank(hmac512SecretFilePath)) {
            definition = JwtAlgorithmDefinition.HS512;
            algorithms.add(Map.entry(definition.getJwtAlgorithmName(),
                    new HmacAlgorithm(definition, Files.readAllBytes(Path.of(hmac512SecretFilePath)))));
        }
        nameToAlgorithm = Map.ofEntries(algorithms.toArray(new Map.Entry[0]));
    }

    private RSAPublicKey getRsaPublicKey(JwtKeyAlgorithmProperties properties) {
        return getPublicKey(CertificateUtil.ALGORITHM_RSA, properties);
    }

    private ECPublicKey getEcPublicKey(JwtKeyAlgorithmProperties properties) {
        return getPublicKey(CertificateUtil.ALGORITHM_EC, properties);
    }

    private <T extends PublicKey> T getPublicKey(String algorithm, JwtKeyAlgorithmProperties properties) {
        String pemFilePath = properties.getPemFilePath();
        if (StringUtil.isNotBlank(pemFilePath)) {
            return (T) CertificateUtil.getPublicKeyFromPem(new File(pemFilePath), algorithm);
        }
        JwtP12KeyStoreProperties p12 = properties.getP12();
        String filePath = p12.getFilePath();
        if (StringUtil.isNotBlank(filePath)) {
            return (T) CertificateUtil.getPublicKeyFromP12(new File(filePath), p12.getPassword(), p12.getAlias());
        }
        return null;
    }

    public Set<String> getSupportedAlgorithmNames() {
        return nameToAlgorithm.keySet();
    }

    public Jwt decode(String jwt) {
        List<String> parts = StringUtil.splitMultipleLatin1(jwt, AsciiCode.PERIOD);
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
        for (Predicate<JwtPayload> verification : verifications) {
            if (!verification.test(payload)) {
                throw new SignatureVerificationException();
            }
        }
        Jwt jwtEntity = new Jwt(header,
                payload,
                encodedHeaderBytes,
                encodedPayloadBytes,
                URL_DECODER.decode(StringUtil.getBytes(parts.get(2))));
        if (algorithm.verify(jwtEntity)) {
            return jwtEntity;
        }
        throw new SignatureVerificationException();
    }

}

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

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import im.turms.server.common.infra.json.JsonCodecPool;
import im.turms.server.common.infra.lang.AsciiCode;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.security.jwt.algorithm.JwtAlgorithm;
import im.turms.server.common.infra.security.jwt.exception.InvalidJwtException;
import im.turms.server.common.infra.security.jwt.exception.JwtSignatureVerificationException;

/**
 * @author James Chen
 */
public final class JwtUtil {

    private static final Base64.Encoder BASE64_ENCODER = Base64.getUrlEncoder()
            .withoutPadding();
    private static final Base64.Decoder BASE64_DECODER = Base64.getUrlDecoder();

    private static final ObjectWriter PAYLOAD_WRITER =
            JsonCodecPool.MAPPER.writerFor(JwtPayload.class);
    private static final ObjectReader PAYLOAD_READER =
            JsonCodecPool.MAPPER.readerFor(JwtPayload.class);

    private static final ObjectWriter HEADER_WRITER =
            JsonCodecPool.MAPPER.writerFor(JwtHeader.class);
    private static final ObjectReader HEADER_READER =
            JsonCodecPool.MAPPER.readerFor(JwtHeader.class);

    private JwtUtil() {
    }

    public static byte[] encode(JwtAlgorithm algorithm, JwtHeader header, JwtPayload payload) {
        byte[] encodedHeader;
        try {
            encodedHeader = BASE64_ENCODER.encode(HEADER_WRITER.writeValueAsBytes(header));
        } catch (JsonProcessingException e) {
            throw new InvalidJwtException("Illegal JWT header format", e);
        }
        byte[] encodedPayload;
        try {
            encodedPayload = BASE64_ENCODER.encode(PAYLOAD_WRITER.writeValueAsBytes(payload));
        } catch (JsonProcessingException e) {
            throw new InvalidJwtException("Illegal JWT payload format", e);
        }
        byte[] signature = BASE64_ENCODER.encode(algorithm.sign(encodedHeader, encodedPayload));

        int headerLength = encodedHeader.length;
        int payloadLength = encodedPayload.length;
        int signatureLength = signature.length;

        byte[] encoded = new byte[headerLength + payloadLength + signatureLength + 2];
        System.arraycopy(encodedHeader, 0, encoded, 0, headerLength);
        encoded[headerLength] = AsciiCode.PERIOD;
        System.arraycopy(encodedPayload, 0, encoded, headerLength + 1, payloadLength);
        encoded[headerLength + payloadLength + 1] = AsciiCode.PERIOD;
        System.arraycopy(signature, 0, encoded, headerLength + payloadLength + 2, signatureLength);
        return encoded;
    }

    public static Jwt decode(
            Map<String, JwtAlgorithm> nameToAlgorithm,
            List<Predicate<JwtPayload>> verifications,
            String jwt) throws InvalidJwtException, NoSuchAlgorithmException,
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
                BASE64_DECODER.decode(StringUtil.getBytes(parts.get(2))));
        if (algorithm.verify(jwtEntity)) {
            return jwtEntity;
        }
        throw new JwtSignatureVerificationException("The input JWT signature is invalid");
    }

    private static JwtHeader decodeHeader(byte[] encodedHeaderBytes) {
        byte[] decodedHeaderBytes;
        try {
            decodedHeaderBytes = BASE64_DECODER.decode(encodedHeaderBytes);
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

    private static JwtPayload decodePayload(byte[] encodedPayloadBytes) {
        byte[] decodedPayloadBytes;
        try {
            decodedPayloadBytes = BASE64_DECODER.decode(encodedPayloadBytes);
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
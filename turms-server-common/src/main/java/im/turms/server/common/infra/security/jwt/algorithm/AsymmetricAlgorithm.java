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

package im.turms.server.common.infra.security.jwt.algorithm;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;

import im.turms.server.common.infra.security.SignaturePool;

/**
 * @author James Chen
 */
public abstract non-sealed class AsymmetricAlgorithm extends JwtAlgorithm {

    protected AsymmetricAlgorithm(JwtAlgorithmDefinition definition) {
        super(definition);
        SignaturePool.ensureAvailability(definition.getJavaAlgorithmName());
    }

    public boolean verifySignature(
            String algorithm,
            PublicKey publicKey,
            byte[] headerBytes,
            byte[] payloadBytes,
            byte[] signatureBytes) {
        Signature signature = SignaturePool.get(algorithm);
        try {
            signature.initVerify(publicKey);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(
                    "Invalid public key: "
                            + publicKey,
                    e);
        }
        try {
            signature.update(headerBytes);
        } catch (SignatureException e) {
            throw new IllegalStateException("Failed to update the header bytes to be verified", e);
        }
        try {
            signature.update(JWT_PART_SEPARATOR);
        } catch (SignatureException e) {
            throw new IllegalStateException(
                    "Failed to update the JWT part separator to be verified",
                    e);
        }
        try {
            signature.update(payloadBytes);
        } catch (SignatureException e) {
            throw new IllegalStateException("Failed to update the payload bytes to be verified", e);
        }
        try {
            return signature.verify(signatureBytes);
        } catch (SignatureException e) {
            throw new IllegalStateException("Failed to verify", e);
        }
    }

    public boolean verifySignature(
            String algorithm,
            AlgorithmParameterSpec parameterSpec,
            PublicKey publicKey,
            byte[] headerBytes,
            byte[] payloadBytes,
            byte[] signatureBytes) {
        Signature signature = SignaturePool.get(algorithm);
        try {
            signature.setParameter(parameterSpec);
        } catch (InvalidAlgorithmParameterException e) {
            throw new IllegalArgumentException(
                    "Invalid algorithm parameter: "
                            + parameterSpec,
                    e);
        }
        try {
            signature.initVerify(publicKey);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(
                    "Invalid public key: "
                            + publicKey,
                    e);
        }
        try {
            signature.update(headerBytes);
        } catch (SignatureException e) {
            throw new IllegalStateException("Failed to update the header bytes to be verified", e);
        }
        try {
            signature.update(JWT_PART_SEPARATOR);
        } catch (SignatureException e) {
            throw new IllegalStateException(
                    "Failed to update the JWT part separator to be verified",
                    e);
        }
        try {
            signature.update(payloadBytes);
        } catch (SignatureException e) {
            throw new IllegalStateException("Failed to update the payload bytes to be verified", e);
        }
        try {
            return signature.verify(signatureBytes);
        } catch (SignatureException e) {
            throw new IllegalStateException("Failed to verify", e);
        }
    }

}
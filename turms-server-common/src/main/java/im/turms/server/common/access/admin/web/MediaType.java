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

package im.turms.server.common.access.admin.web;

import im.turms.server.common.infra.lang.StringUtil;

/**
 * @author James Chen
 * @implNote The class drop the media type parameters just because we don't need it currently
 * @see <a href="https://www.iana.org/assignments/media-types/media-types.xhtml">Media Types</a>
 */
public record MediaType(
        String type,
        String subtype
) {

    private static final String WILDCARD_TYPE = "*";

    public static MediaType create(String mediaType) {
        if (StringUtil.isBlank(mediaType)) {
            throw new IllegalArgumentException("The media type must not be blank");
        }
        int index = mediaType.indexOf(';');
        String fullType = (index >= 0
                ? mediaType.substring(0, index)
                : mediaType).trim();
        if (fullType.isBlank()) {
            throw new IllegalArgumentException("The media type must not be blank");
        }
        if (WILDCARD_TYPE.equals(fullType)) {
            fullType = "*/*";
        }
        int subIndex = fullType.indexOf('/');
        if (subIndex == -1) {
            throw new IllegalArgumentException(
                    "The media type \""
                            + mediaType
                            + "\" must not contain \"/\"");
        }
        if (subIndex == fullType.length() - 1) {
            throw new IllegalArgumentException(
                    "The media type \""
                            + mediaType
                            + "\" must not contain subtype after \"/\"");
        }
        String type = fullType.substring(0, subIndex);
        String subtype = fullType.substring(subIndex + 1);
        if (WILDCARD_TYPE.equals(type) && !WILDCARD_TYPE.equals(subtype)) {
            throw new IllegalArgumentException(
                    "The wildcard type is legal only in \"*/*\", but got: \""
                            + mediaType
                            + "\"");
        }
        return new MediaType(type, subtype);
    }

    @Override
    public String toString() {
        return type
                + "/"
                + subtype;
    }

    public boolean isWildcardType() {
        return WILDCARD_TYPE.equals(type);
    }

    public boolean isWildcardSubtype() {
        return WILDCARD_TYPE.equals(subtype) || subtype.startsWith("*+");
    }

    public boolean isConcrete() {
        return !isWildcardType() && !isWildcardSubtype();
    }

}

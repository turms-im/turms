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

package im.turms.plugin.antispam.core.dictionary;

import java.util.Date;
import jakarta.annotation.Nullable;

import lombok.Data;
import lombok.Getter;

/**
 * @author James Chen
 */
@Data
public class ExtendedWord extends Word {

    @Nullable
    private final String id;

    @Nullable
    private final Integer level;

    @Nullable
    private final String category;

    @Nullable
    private final String source;

    @Nullable
    private final Date createDate;

    @Nullable
    private final Date disableDate;

    @Nullable
    private final Date enableDate;

    @Nullable
    private final Date updateDate;

    @Nullable
    private final String comment;

    public static ExtendedWord.Builder builder() {
        return new Builder();
    }

    public ExtendedWord(
            char[] word,
            @Nullable String id,
            @Nullable Integer level,
            @Nullable String category,
            @Nullable String source,
            @Nullable Date createDate,
            @Nullable Date disableDate,
            @Nullable Date enableDate,
            @Nullable Date updateDate,
            @Nullable String comment) {
        super(word);
        this.id = id;
        this.level = level;
        this.category = category;
        this.source = source;
        this.createDate = createDate;
        this.disableDate = disableDate;
        this.enableDate = enableDate;
        this.updateDate = updateDate;
        this.comment = comment;
    }

    @Getter
    public static class Builder {

        private char[] word;
        private String id;
        private Integer level;
        private String category;
        private String source;
        private Date createDate;
        private Date disableDate;
        private Date enableDate;
        private Date updateDate;
        private String comment;

        public void reset() {
            word = null;
            id = null;
            level = null;
            category = null;
            source = null;
            createDate = null;
            disableDate = null;
            enableDate = null;
            updateDate = null;
            comment = null;
        }

        public Builder setWord(char[] word) {
            this.word = word;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setLevel(Integer level) {
            this.level = level;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setSource(String source) {
            this.source = source;
            return this;
        }

        public Builder setCreateDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }

        public Builder setDisableDate(Date disableDate) {
            this.disableDate = disableDate;
            return this;
        }

        public Builder setEnableDate(Date enableDate) {
            this.enableDate = enableDate;
            return this;
        }

        public Builder setUpdateDate(Date updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public ExtendedWord build() {
            return new ExtendedWord(
                    word,
                    id,
                    level,
                    category,
                    source,
                    createDate,
                    disableDate,
                    enableDate,
                    updateDate,
                    comment);
        }
    }

}
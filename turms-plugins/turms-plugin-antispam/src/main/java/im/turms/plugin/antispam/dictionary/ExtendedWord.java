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

package im.turms.plugin.antispam.dictionary;

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
    private final Date createTime;

    @Nullable
    private final Date disableTime;

    @Nullable
    private final Date enableTime;

    @Nullable
    private final Date updateTime;

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
            @Nullable Date createTime,
            @Nullable Date disableTime,
            @Nullable Date enableTime,
            @Nullable Date updateTime,
            @Nullable String comment) {
        super(word);
        this.id = id;
        this.level = level;
        this.category = category;
        this.source = source;
        this.createTime = createTime;
        this.disableTime = disableTime;
        this.enableTime = enableTime;
        this.updateTime = updateTime;
        this.comment = comment;
    }

    @Getter
    public static class Builder {

        private char[] word;
        private String id;
        private Integer level;
        private String category;
        private String source;
        private Date createTime;
        private Date disableTime;
        private Date enableTime;
        private Date updateTime;
        private String comment;

        public void reset() {
            word = null;
            id = null;
            level = null;
            category = null;
            source = null;
            createTime = null;
            disableTime = null;
            enableTime = null;
            updateTime = null;
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

        public Builder setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder setDisableTime(Date disableTime) {
            this.disableTime = disableTime;
            return this;
        }

        public Builder setEnableTime(Date enableTime) {
            this.enableTime = enableTime;
            return this;
        }

        public Builder setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
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
                    createTime,
                    disableTime,
                    enableTime,
                    updateTime,
                    comment);
        }
    }

}

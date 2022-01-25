<template>
    <a-modal
        :confirm-loading="loading"
        :mask-closable="false"
        :keyboard="false"
        :visible="visible"
        :title="title"
        class="content-modal-form"
        @cancel="handleCancel"
        @ok="handleSubmit"
    >
        <div
            v-if="showNoFieldForUpdateError"
            class="content-modal-form__error"
        >
            {{ $t('noFieldForUpdateError') }}
        </div>
        <a-form
            ref="form"
            :model="formState"
        >
            <template
                v-for="(field, index) in formItems"
            >
                <a-form-item
                    v-if="field.type !== 'DYNAMIC-INPUT'"
                    :key="field.id"
                    :name="field.id"
                    :label-col="labelCol"
                    :wrapper-col="wrapperCol"
                    :label="$t(field.label || field.id)"
                    :rules="$validator.parseRules(field.rules)"
                >
                    <a-input
                        v-if="field.type === 'INPUT'"
                        v-model:value="formState[field.id]"
                        class="content-modal-form__item"
                        :placeholder="(field.placeholder && $t(field.placeholder)) || ''"
                        :date-id="field.id"
                    />
                    <a-input-number
                        v-else-if="field.type === 'INPUT-NUMBER'"
                        v-model:value="formState[field.id]"
                        class="content-modal-form__item"
                        :max="field.max"
                        :min="field.min"
                    />
                    <a-date-picker
                        v-else-if="field.type === 'DATE'"
                        v-model:value="formState[field.id]"
                        class="content-modal-form__item content-modal-form__date-picker"
                        type="date"
                        :show-time="true"
                        :disabled-date="(cur) => isDateDisabled(cur, field.allowFuture, field.allowPast)"
                        :disabled-time="(cur) => getDisabledTime(cur, field.allowFuture, field.allowPast)"
                        :date-id="field.id"
                    />
                    <a-switch
                        v-else-if="field.type === 'SWITCH'"
                        v-model:checked="formState[field.id]"
                        class="content-modal-form__item"
                        :default-checked="field.defaultChecked"
                        :date-id="field.id"
                    />
                    <a-textarea
                        v-else-if="field.type === 'TEXTAREA'"
                        v-model:value="formState[field.id]"
                        class="content-modal-form__item"
                        :rows="field.rows"
                        :placeholder="(field.placeholder && $t(field.placeholder)) || $t(`${field.id}`)"
                        :date-id="field.id"
                    />
                    <a-select
                        v-else-if="field.type === 'SELECT'"
                        v-model:value="formState[field.id]"
                        class="content-modal-form__item"
                        :allow-clear="true"
                        :date-id="field.id"
                    >
                        <template
                            v-if="!field.options?.values"
                        >
                            <a-select-option
                                value="ALL"
                            >
                                {{ $t('all') }}
                            </a-select-option>
                        </template>
                        <a-select-option
                            v-for="value in field.options.values"
                            v-else
                            :key="value.id.toString()"
                            :value="value.id.toString()"
                        >
                            {{ $t(value.label) }}
                        </a-select-option>
                    </a-select>
                    <a-tree
                        v-else-if="field.type === 'TREE'"
                        v-model:checkedKeys="formState[field.id]"
                        :date-id="field.id"
                        class="content-modal-form__item"
                        :auto-expand-parent="false"
                        :tree-data="field.data"
                        :checkable="true"
                    />
                </a-form-item>
                <template
                    v-if="field.type === 'DYNAMIC-INPUT'"
                >
                    <a-form-item
                        v-for="(val, subIndex) in formState[field.id]"
                        :key="val.key"
                        :name="[field.id, subIndex, 'value']"
                        :label="subIndex ? '' : $t(field.label)"
                        :required="false"
                        :rules="$validator.parseRules(field.rules)"
                        v-bind="subIndex ? $layout.formItemLayoutWithoutLabel : $layout.formItemLayout"
                    >
                        <a-input
                            v-model:value="val.value"
                            class="content-modal-form__item dynamic-input"
                            :placeholder="field.placeholder ? $t(field.placeholder) : ''"
                            :date-id="`${field.id}-${val.key}`"
                        />
                        <minus-circle-outlined
                            v-if="formState[field.id].length > 1 || subIndex"
                            class="dynamic-delete-button"
                            @click="deleteInput(field, subIndex)"
                        />
                    </a-form-item>
                    <a-form-item
                        :key="'add' + index"
                        v-bind="$layout.formItemLayoutWithoutLabel"
                    >
                        <a-button
                            type="dashed"
                            class="dynamic-input__add"
                            @click="addNewInput(field)"
                        >
                            <plus-outlined />
                            {{ $t(field.addButtonLabel) }}
                        </a-button>
                    </a-form-item>
                </template>
            </template>
        </a-form>
    </a-modal>
</template>

<script>
import JSONBig from 'json-bigint';
import {MinusCircleOutlined, PlusOutlined} from '@ant-design/icons-vue';

export default {
    name: 'content-modal-form',
    components: {
        MinusCircleOutlined,
        PlusOutlined
    },
    props: {
        type: {
            type: String,
            default: 'CREATE'
        },
        submitUrl: {
            type: String,
            required: true
        },
        size: {
            type: String,
            default: 'M'
        },
        disabled: {
            type: Boolean,
            default: false
        },
        visible: {
            type: Boolean,
            default: false
        },
        title: {
            type: String,
            default: ''
        },
        formItems: {
            type: Array,
            required: true
        },
        queryKey: {
            type: String,
            default: 'ids'
        },
        targetRecordKeys: {
            type: Array,
            default: () => []
        }
    },
    emits: ['onRecordCreated', 'onRecordsUpdated', 'hide'],
    data() {
        const formState = {};
        for (const item of this.formItems) {
            formState[item.id] = item.value;
            if (item.type === 'DYNAMIC-INPUT') {
                formState[item.id] = [{
                    value: '',
                    key: Date.now()
                }];
            } else if (item.type === 'SWITCH') {
                formState[item.id] = false;
            }
        }
        return {
            loading: false,
            formState,
            showNoFieldForUpdateError: false,
            formItemLayout: {
                labelCol: {
                    xs: {span: 24},
                    sm: {span: 4}
                },
                wrapperCol: {
                    xs: {span: 24},
                    sm: {span: 20}
                }
            },
            formItemLayoutWithOutLabel: {
                wrapperCol: {
                    xs: {span: 24, offset: 0},
                    sm: {span: 20, offset: 4}
                }
            },
            dynamicInputId: 1
        };
    },
    computed: {
        labelCol() {
            switch (this.size) {
                case 'L':
                    return this.$layout.formItemLayoutWithWideLabel.labelCol;
                case 'XL':
                    return this.$layout.formItemLayoutWithWiderLabel.labelCol;
                default:
                    return this.$layout.formItemLayout.labelCol;
            }
        },
        wrapperCol() {
            switch (this.size) {
                case 'L':
                    return this.$layout.formItemLayoutWithWideLabel.wrapperCol;
                case 'XL':
                    return this.$layout.formItemLayoutWithWiderLabel.wrapperCol;
                default:
                    return this.$layout.formItemLayout.wrapperCol;
            }
        }
    },
    methods: {
        addNewInput(field) {
            this.formState[field.id].push({
                value: '',
                key: Date.now()
            });
        },
        deleteInput(field, index) {
            this.formState[field.id].splice(index, 1);
        },
        getDisabledTime(current, allowFuture, allowPast = true) {
            if (allowFuture && allowPast) {
                return;
            }
            const present = this.$date();
            const currentStartOfDate = this.$date(current).startOf('day').toDate().getTime();
            const presentStartOfDate = this.$date().startOf('day').toDate().getTime();
            if (currentStartOfDate !== presentStartOfDate) {
                return;
            }
            if (allowFuture) {
                return {
                    disabledHours: () => this.$util.range(0, present.hour() - 1)
                };
            } else {
                return {
                    disabledHours: () => this.$util.range(present.hour() + 1, 24)
                };
            }
        },
        hide() {
            this.$emit('hide');
        },
        handleCancel() {
            this.hide();
        },
        async handleSubmit() {
            try {
                await this.$refs.form.validate();
            } catch (e) {
                // Suppress the error to avoid some browsers logging something like "Uncaught (in promise)"
                return;
            }
            const record = this.parseRecord(this.formState);
            if (this.type === 'CREATE') {
                this.requestCreateNewRecord(record);
            } else {
                this.requestUpdateRecord(record);
            }
        },
        isDateDisabled(current, allowFuture, allowPast = true) {
            if (!current) {
                return true;
            }
            current = this.$date(current).startOf('day').toDate().getTime();
            const present = this.$date().startOf('day').toDate().getTime();
            if (current === present) {
                return false;
            }
            if (allowFuture && allowPast) {
                return false;
            } else if (allowFuture) {
                return current <= present;
            } else {
                return current >= present;
            }
        },
        parseRecord(formState) {
            const record = this.$util.copy(formState);
            const dynamicInputFieldIds = [];
            for (const item of this.formItems) {
                const itemType = item.type;
                const itemId = item.id;
                if (itemType === 'TREE') {
                    record[itemId] = item.transform(formState[itemId]);
                } else if (itemType === 'DYNAMIC-INPUT') {
                    dynamicInputFieldIds.push(itemId);
                }
            }
            Object.entries(record).forEach(([key, value]) => {
                if (dynamicInputFieldIds.includes(key)) {
                    const values = value.filter(i => i)
                        .map(i => i.value);
                    if (values.length) {
                        record[key] = values;
                    } else {
                        delete record[key];
                    }
                } else if (this.$date.isDayjs(value)) {
                    record[key] = value.toISOString();
                }
            });
            this.$util.removeEmpty(record);
            return record;
        },
        requestCreateNewRecord(record) {
            this.loading = true;
            this.$http.post(this.submitUrl, this.getRequestBodyData(record))
                .then(() => {
                    this.$message.success(this.$t('createdSuccessfully'));
                    this.$emit('onRecordCreated', record);
                    this.hide();
                })
                .catch(err => {
                    this.$error(this.$t('createFailed'), err);
                })
                .finally(() => {
                    this.loading = false;
                });
        },
        requestUpdateRecord(updatedFields) {
            if (!Object.keys(updatedFields).length) {
                this.showNoFieldForUpdateError = true;
                return;
            }
            this.showNoFieldForUpdateError = false;
            this.loading = true;
            const params = this.$rq.getQueryParams(this.queryKey, this.targetRecordKeys);
            this.$http.put(`${this.submitUrl}${params}`, this.getRequestBodyData(updatedFields))
                .then(() => {
                    this.$emit('onRecordsUpdated', this.targetRecordKeys, updatedFields);
                    this.hide();
                    this.$message.success(this.$t('updatedSuccessfully'));
                })
                .catch(err => {
                    this.$error(this.$t('updateFailed'), err);
                })
                .finally(() => {
                    this.loading = false;
                });
        },
        getRequestBodyData(record) {
            if (!record) {
                return {};
            }
            const postData = {};
            for (const [key, value] of Object.entries(record)) {
                try {
                    postData[key] = JSONBig.parse(value);
                } catch {
                    postData[key] = value;
                }
            }
            return postData;
        }
    }
};
</script>
<style lang="scss" scoped>
.content-modal-form {
    &__date-picker {
        width: 100%;
    }

    &__error {
        margin-bottom: 16px;
        margin-left: 8px;
        color: red;
    }

    .dynamic-input {
        width: 90%;
        margin-right: 8px;
    }

    .dynamic-input__add {
        width: 60%;
    }
}
</style>
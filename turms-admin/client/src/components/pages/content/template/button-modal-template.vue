<template>
    <div>
        <a-button
            type="primary"
            :disabled="disabled"
            @click="showModal"
        >
            {{ buttonLabel }}
        </a-button>
        <a-modal
            :confirm-loading="loading"
            :mask-closable="false"
            :keyboard="false"
            :visible="visible"
            :title="title"
            class="button-modal-template"
            @cancel="handleCancel"
            @ok="handleSubmit"
        >
            <div
                v-if="showNoFieldForUpdateError"
                class="button-modal-template__error"
            >
                {{ $t('noFieldForUpdateError') }}
            </div>
            <a-form
                ref="form"
                :model="formState"
            >
                <template
                    v-for="(field, index) in fields"
                >
                    <a-form-item
                        v-if="field.type !== 'DYNAMIC-INPUT'"
                        :key="field.id"
                        :name="field.id"
                        :label-col="labelCol"
                        :wrapper-col="wrapperCol"
                        :label="(field.label && $t(field.label)) || $t(`${field.id}`)"
                        :rules="$validator.parseRules(field.rules)"
                    >
                        <a-input
                            v-if="field.type.toUpperCase() === 'INPUT'"
                            v-model:value="formState[field.id]"
                            :placeholder="(field.placeholder && $t(field.placeholder)) || ''"
                        />
                        <a-date-picker
                            v-if="field.type.toUpperCase() === 'DATE'"
                            v-model:value="formState[field.id]"
                            :show-time="field.showTime == null ? true : field.showTime"
                            :disabled-date="disabledDate"
                            class="button-modal-template__date-picker"
                        />
                        <a-switch
                            v-if="field.type.toUpperCase() === 'SWITCH'"
                            v-model:value="formState[field.id]"
                            :default-checked="field.defaultChecked"
                        />
                        <a-textarea
                            v-if="field.type.toUpperCase() === 'TEXTAREA'"
                            :key="index"
                            v-model:value="formState[field.id]"
                            :rows="field.rows"
                            :placeholder="(field.placeholder && $t(field.placeholder)) || $t(`${field.id}`)"
                        />
                        <a-select
                            v-if="field.type.toUpperCase() === 'SELECT'"
                            v-model:value="formState[field.id]"
                            :allow-clear="true"
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
                            v-if="field.type.toUpperCase() === 'TREE'"
                            v-model:checked-keys="field.checkedKeys"
                            :auto-expand-parent="false"
                            :tree-data="field.data"
                            :checkable="true"
                        />
                    </a-form-item>
                    <template
                        v-if="field.type === 'DYNAMIC-INPUT'"
                    >
                        <a-form-item
                            v-for="(val, subIndex) in formState[field.id].items"
                            :key="subIndex"
                            :name="`${field.id}.${subIndex}`"
                            :label="subIndex ? '' : $t(field.label)"
                            :required="false"
                            :rules="$validator.parseRules(field.rules)"
                            v-bind="subIndex ? $layout.formItemLayoutWithoutLabel : $layout.formItemLayout"
                        >
                            <a-input
                                v-model:value="formState[field.id].items[subIndex]"
                                :placeholder="$t(field.placeholder)"
                                class="dynamic-input"
                            />
                            <minus-circle-outlined
                                v-if="subIndex"
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
    </div>
</template>

<script>
import JSONBig from 'json-bigint';
import {MinusCircleOutlined, PlusOutlined} from '@ant-design/icons-vue';

export default {
    name: 'button-modal-template',
    components: {
        MinusCircleOutlined,
        PlusOutlined
    },
    props: {
        type: {
            type: String,
            default: 'CREATE'
        },
        url: {
            type: String,
            default: ''
        },
        buttonLabel: {
            type: String,
            default: ''
        },
        size: {
            type: String,
            default: 'M'
        },
        disabled: {
            type: Boolean,
            default: false
        },
        title: {
            type: String,
            default: ''
        },
        fields: {
            type: Array,
            default: () => []
        },
        queryKey: {
            type: String,
            default: 'ids'
        },
        keys: {
            type: Array,
            default: () => []
        }
    },
    emits: ['onDataUpdated'],
    data() {
        const formState = {};
        for (const field of this.fields) {
            if (field.type === 'DYNAMIC-INPUT') {
                formState[field.id] = {
                    items: ['']
                };
            }
        }
        return {
            loading: false,
            formState,
            showNoFieldForUpdateError: false,
            visible: false,
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
            }
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
            this.formState[field.id].items.push('');
        },
        deleteInput(field, index) {
            if (index) {
                this.formState[field.id].items.splice(index, 1);
            }
        },
        disabledDate(current) {
            // Cannot select the days before today and today
            return current && current > this.$moment().endOf('day');
        },
        showModal() {
            const type = this.type.toUpperCase();
            if (type === 'CREATE' || (type === 'UPDATE' && this.keys.length)) {
                this.visible = true;
            } else {
                this.$message.error(this.$t('noRecordsSelected'));
            }
        },
        handleCancel() {
            this.visible = false;
        },
        handleSubmit() {
            this.$refs.form.validate()
                .then(() => {
                    const values = JSON.parse(JSON.stringify(this.formState));
                    const dynamicInputFieldIds = [];
                    for (const field of this.fields) {
                        const fieldType = field.type.toUpperCase();
                        const fieldId = field.id;
                        if (fieldType === 'TREE') {
                            if (field.rules?.some(r => r.required) && !field.checkedKeys?.length) {
                                // TODO: Use error field in next version
                                this.$message.error(`${fieldId}${this.$t('fieldIsRequired')}`);
                                return;
                            } else {
                                values[fieldId] = field.transform(field.checkedKeys);
                            }
                        } else if (fieldType === 'DYNAMIC-INPUT') {
                            dynamicInputFieldIds.push(fieldId);
                        }
                    }
                    Object.entries(values).forEach(([key, value]) => {
                        if (dynamicInputFieldIds.includes(key)) {
                            Object.entries(value).forEach(([subKey, subValue]) => {
                                if (subValue == null) {
                                    delete value[subKey];
                                }
                            });
                        } else if (this.$moment.isMoment(value)) {
                            values[key] = value.toISOString();
                        }
                    });
                    if (this.type === 'CREATE') {
                        this.createModel(values);
                    } else {
                        this.tryUpdateModel(values);
                    }
                });
        },
        createModel(values) {
            this.loading = true;
            this.$http.post(this.url, this.filterParams(values))
                .then(response => {
                    if (response.status === 204) {
                        this.$message.error(this.$t('createFailed'));
                    } else {
                        this.$message.success(this.$t('createdSuccessfully'));
                        this.visible = false;
                        this.$parent.search();
                    }
                })
                .catch(err => {
                    this.$error(this.$t('createFailed'), err);
                })
                .finally(() => {
                    this.loading = false;
                });
        },
        tryUpdateModel(values) {
            if (!Object.keys(values).length) {
                this.showNoFieldForUpdateError = true;
                return;
            } else {
                this.showNoFieldForUpdateError = false;
            }
            this.loading = true;
            const params = this.$rq.getQueryParams(this.queryKey, this.keys);
            this.$http.put(`${this.url}${params}`, this.filterParams(values))
                .then(() => {
                    this.$emit('onDataUpdated', this.keys, values);
                    this.visible = false;
                    this.$message.success(this.$t('updatedSuccessfully'));
                })
                .catch(err => {
                    this.$error(this.$t('updateFailed'), err);
                })
                .finally(() => {
                    this.loading = false;
                });
        },
        filterParams(params) {
            if (params) {
                for (const [key, value] of Object.entries(params)) {
                    try {
                        params[key] = JSONBig.parse(value);
                        // eslint-disable-next-line no-empty
                    } catch {
                    }
                }
            }
            return params;
        }
    }
};
</script>
<style lang="scss" scoped>
.button-modal-template {
    .button-modal-template__date-picker {
        width: 100% !important;
    }

    .button-modal-template__error {
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
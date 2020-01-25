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
                v-if="showUpdateAllError"
                class="button-modal-template__error"
            >
                {{ $t('noFieldForUpdateError') }}
            </div>
            <a-form
                :form="form"
                @submit="handleSubmit"
            >
                <template
                    v-for="(field, index) in fields"
                >
                    <a-form-item
                        v-if="field.type !== 'DYNAMIC-INPUT'"
                        :key="'dynamic-input' + field.decorator[0] + index"
                        :label-col="labelCol"
                        :wrapper-col="wrapperCol"
                        :label="field.label || $t(`${field.decorator[0]}`)"
                    >
                        <a-input
                            v-if="field.type.toUpperCase() === 'INPUT'"
                            v-decorator="field.decorator"
                            :placeholder="field.placeholder || ''"
                        />
                        <a-date-picker
                            v-if="field.type.toUpperCase() === 'DATE'"
                            v-model="field.model"
                            v-decorator="field.decorator"
                            :show-time="typeof field.showTime === 'undefined' ? true : field.showTime"
                            :disabled-date="disabledDate"
                            class="button-modal-template__date-picker"
                        />
                        <a-switch
                            v-if="field.type.toUpperCase() === 'SWITCH'"
                            v-decorator="field.decorator"
                            :default-checked="field.defaultChecked || false"
                        />
                        <a-textarea
                            v-if="field.type.toUpperCase() === 'TEXTAREA'"
                            :key="index"
                            v-decorator="field.decorator"
                            :rows="field.rows"
                            :placeholder="field.placeholder || $t(`${field.decorator[0]}`)"
                        />
                        <a-select
                            v-if="field.type.toUpperCase() === 'SELECT'"
                            v-decorator="field.decorator"
                            :allow-clear="true"
                        >
                            <template
                                v-if="!field.options || !field.options.values"
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
                                {{ value.label }}
                            </a-select-option>
                        </a-select>
                        <a-tree
                            v-if="field.type.toUpperCase() === 'TREE'"
                            v-model="field.checkedKeys"
                            :auto-expand-parent="false"
                            :tree-data="field.data"
                            checkable
                        />
                    </a-form-item>
                    <template
                        v-if="field.type === 'DYNAMIC-INPUT'"
                    >
                        <a-form-item
                            v-for="(key, subIndex) in form.getFieldValue(`${field.decorator[0]}Keys`)"
                            :key="key + index"
                            :label="subIndex === 0 ? field.label : ''"
                            :required="false"
                            v-bind="subIndex === 0 ? $layout.formItemLayout : $layout.formItemLayoutWithoutLabel"
                        >
                            <a-input
                                v-decorator="getDynamicInputDecorator(field, key)"
                                :placeholder="field.placeholder"
                                class="dynamic-input"
                            />
                            <a-icon
                                v-if="form.getFieldValue(`${field.decorator[0]}Keys`).length > 1"
                                :disabled="form.getFieldValue(`${field.decorator[0]}Keys`).length === 1"
                                class="dynamic-delete-button"
                                type="minus-circle-o"
                                @click="deleteInput(field, key)"
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
                                <a-icon type="plus" />
                                {{ field.addButtonLabel }}
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
let id = 1;
export default {
    name: 'button-modal-template',
    props: {
        type: {
            type: String,
            default: () => {
                return 'CREATE';
            }
        },
        url: {
            type: String,
            default: () => {
                return '';
            }
        },
        buttonLabel: {
            type: String,
            default: () => {
                return '';
            }
        },
        size: {
            type: String,
            default: () => {
                return 'M';
            }
        },
        disabled: {
            type: Boolean,
            default: false
        },
        title: {
            type: String,
            default: () => {
                return '';
            }
        },
        fields: {
            type: Array,
            default: () => {
                return [];
            }
        },
        queryKey: {
            type: String,
            default: () => {
                return 'ids';
            }
        },
        recordKey: {
            type: String,
            default: () => {
                return 'id';
            }
        },
        keys: {
            type: Array,
            default: () => {
                return [];
            }
        }
    },
    data() {
        return {
            loading: false,
            form: this.$form.createForm(this),
            showUpdateAllError: false,
            visible: false,
            formItemLayout: {
                labelCol: {
                    xs: { span: 24 },
                    sm: { span: 4 }
                },
                wrapperCol: {
                    xs: { span: 24 },
                    sm: { span: 20 }
                }
            },
            formItemLayoutWithOutLabel: {
                wrapperCol: {
                    xs: { span: 24, offset: 0 },
                    sm: { span: 20, offset: 4 }
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
    watch: {
        fields: {
            handler() {
                for (const field of this.fields) {
                    if (field.type === 'DYNAMIC-INPUT') {
                        const name = field.decorator[0];
                        this.form.getFieldDecorator(`${name}Keys`, {
                            initialValue: [0],
                            preserve: true
                        });
                    }
                }
            },
            deep: true,
            immediate: true
        }
    },
    methods: {
        deleteInput(field, deleteKey) {
            const name = `${field.decorator[0]}Keys`;
            const keys = this.form.getFieldValue(name);
            if (keys.length === 1) {
                return;
            }
            const value = {};
            value[name] = keys.filter(key => key !== deleteKey);
            this.form.setFieldsValue(value);
        },
        addNewInput(field) {
            const name = `${field.decorator[0]}Keys`;
            const keys = this.form.getFieldValue(name);
            const value = {};
            value[name] = keys.concat(id++);
            this.form.setFieldsValue(value);
        },
        getDynamicInputDecorator(field, key) {
            const name = field.decorator[0];
            return [`${name}[${key}]`, field.decorator[1]];
        },
        disabledDate(current) {
            // Cannot select the days before today and today
            return current && current > this.$moment().endOf('day');
        },
        showModal() {
            const type = this.type.toUpperCase();
            if (type === 'CREATE' || (type === 'UPDATE' && this.keys.length > 0)) {
                this.visible = true;
            } else {
                this.$message.error(this.$t('noRecordsSelected'));
            }
        },
        handleCancel() {
            this.visible = false;
        },
        handleSubmit() {
            this.form.validateFields((error, values) => {
                if (!error) {
                    const dynamicInputFieldNames = [];
                    const dynamicInputFieldKeys = [];
                    for (const field of this.fields) {
                        const fieldType = field.type.toUpperCase();
                        const fieldName = field.decorator[0];
                        if (fieldType === 'TREE') {
                            if (field.decorator[1]
                                && field.decorator[1].rules[0].required === true
                                && (!field.checkedKeys || !field.checkedKeys.length)) {
                                //TODO: Use error field in next version
                                this.$message.error(`${fieldName}${this.$t('fieldIsRequired')}`);
                                return;
                            } else {
                                values[fieldName] = field.transform(field.checkedKeys);
                            }
                        } else if (fieldType === 'DYNAMIC-INPUT') {
                            dynamicInputFieldNames.push(fieldName);
                            dynamicInputFieldKeys.push(`${fieldName}Keys`);
                        }
                    }
                    Object.entries(values).forEach(entry => {
                        const key = entry[0];
                        if (dynamicInputFieldKeys.includes(key) || typeof entry[1] === 'undefined') {
                            delete values[key];
                        } else if (dynamicInputFieldNames.includes(key)) {
                            Object.entries(values[key]).forEach(subEntry => {
                                const subValue = subEntry[1];
                                if (typeof subValue === 'undefined' || subValue === null) {
                                    delete values[key][subEntry[0]];
                                }
                            });
                        }
                    });
                    if (this.type === 'CREATE') {
                        this.loading = true;
                        this.$client.post(this.url, this.filterParams(values))
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
                    } else {
                        if (Object.keys(values).length === 0) {
                            this.showUpdateAllError = true;
                            return;
                        } else {
                            this.showUpdateAllError = false;
                        }
                        this.loading = true;
                        const params = this.$rq.getQueryParams(this.queryKey, this.keys);
                        this.$client.put(`${this.url}${params}`, this.filterParams(values))
                            .then(() => {
                                this.$parent.$data.records.forEach(record => {
                                    let currentKey = record[this.recordKey];
                                    currentKey = this.recordKey === 'key' ? JSON.stringify(currentKey) : currentKey;
                                    if (currentKey._isBigNumber) {
                                        currentKey = currentKey.toFixed();
                                    }
                                    if (this.keys.includes(currentKey)) {
                                        Object.assign(record, values);
                                    }
                                });
                                this.$emit('afterDataUpdated', this.keys, values);
                                this.visible = false;
                                this.$message.success(this.$t('updatedSuccessfully'));
                            })
                            .catch(err => {
                                this.$error(this.$t('updateFailed'), err);
                            })
                            .finally(() => {
                                this.loading = false;
                            });
                    }
                }
            });
        },
        filterParams(params) {
            if (params) {
                for (const entry of Object.entries(params)) {
                    try {
                        params[entry[0]] = JSONBig.parse(entry[1]);
                        // eslint-disable-next-line no-empty
                    } catch {}
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
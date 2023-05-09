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
            :model="parsedFormState"
        >
            <template
                v-for="field in extendedFormItems"
            >
                <template
                    v-for="subField in (field.fields || [])"
                >
                    <modal-form-dynamic-input
                        v-if="subField.type === 'DYNAMIC-INPUT'"
                        :key="`${field.id}:${subField.id}`"
                        :field="subField"
                        :parent-field-name="field.id"
                        :form-state="parsedFormState[field.id][subField.id]"
                    />
                    <modal-form-item
                        v-else
                        :key="`${field.id}:${subField.id}`"
                        :wrapper-col="wrapperCol"
                        :label-col="labelCol"
                        :field="subField"
                        :parent-field-name="field.id"
                        :form-state="parsedFormState[field.id]"
                    />
                </template>
                <modal-form-dynamic-input
                    v-if="field.type === 'DYNAMIC-INPUT'"
                    :key="field.id"
                    :field="field"
                    :form-state="parsedFormState[field.id]"
                />
                <modal-form-item
                    v-else-if="!field.fields"
                    :key="field.id"
                    :wrapper-col="wrapperCol"
                    :label-col="labelCol"
                    :field="field"
                    :form-state="parsedFormState"
                />
            </template>
        </a-form>
    </a-modal>
</template>

<script>
import JSONBig from 'json-bigint';
import ModalFormDynamicInput from './modal-form-dynamic-input.vue';
import ModalFormItem from './modal-form-item.vue';

const BUILTIN_NODE_ID_SELECT = '__nodeId__';

export default {
    name: 'content-modal-form',
    components: {
        ModalFormDynamicInput,
        ModalFormItem
    },
    props: {
        members: {
            type: Array,
            default: () => []
        },
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
        formState: {
            type: Object,
            default: null
        },
        queryKey: {
            type: String,
            default: 'ids'
        },
        targetRecords: {
            type: Array,
            default: () => []
        },
        targetRecordKeys: {
            type: Array,
            default: () => []
        }
    },
    emits: ['onRecordCreated', 'onRecordsUpdated', 'hide'],
    data() {
        return {
            loading: false,
            parsedFormState: {},
            showNoFieldForUpdateError: false,
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
            },
            dynamicInputId: 1
        };
    },
    computed: {
        clusterMode() {
            return this.members?.length;
        },
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
        },
        extendedFormItems() {
            if (!this.clusterMode || this.type !== 'CREATE') {
                return this.formItems;
            }
            const memberSelect = {
                id: BUILTIN_NODE_ID_SELECT,
                type: 'SELECT',
                label: 'targetNodeId',
                value: this.members[0].nodeId,
                rules: this.$validator.create({required: true}),
                options: {
                    values: this.members.map(member => ({
                        id: member.nodeId,
                        rawLabel: member.nodeId
                    }))
                }
            };
            return [memberSelect, ...this.formItems];
        }
    },
    watch: {
        extendedFormItems: {
            handler() {
                this.parsedFormState = this.formState || this.initFormState(this.extendedFormItems, {});
            },
            immediate: true
        }
    },
    methods: {
        initFormState(items, state) {
            for (const item of items) {
                const itemId = item.id;
                if (item.fields) {
                    const nestedState = this.initFormState(item.fields, {});
                    // state[itemId] = item.dynamicFieldGroups ? [nestedState] : nestedState;
                    state[itemId] = nestedState;
                    continue;
                }
                state[itemId] = item.value;
                if (item.type === 'DYNAMIC-INPUT') {
                    state[itemId] = [{
                        value: '',
                        key: Date.now()
                    }];
                } else if (item.type === 'SWITCH') {
                    state[itemId] = false;
                } else if (item.type === 'UPLOAD') {
                    state[itemId] = [];
                }
            }
            return state;
        },
        hide() {
            this.$emit('hide', this.parsedFormState);
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
            const { form, record } = this.parseFormItems(this.formItems, this.parsedFormState, {});
            if (this.type === 'CREATE') {
                this.requestCreateNewRecord(form, record);
            } else {
                this.requestUpdateRecord(record);
            }
        },
        parseFormItems(items, state, record) {
            let form;
            for (const item of items) {
                const itemType = item.type;
                const itemId = item.id;
                const value = state[itemId];
                if (itemType === 'TREE') {
                    record[itemId] = item.transform(value);
                } else if (itemType === 'DYNAMIC-INPUT') {
                    const values = value.filter(Boolean)
                        .map(i => i.value);
                    if (values.length) {
                        record[itemId] = values;
                    }
                } else if (itemType === 'UPLOAD') {
                    form = {};
                    for (const file of value) {
                        form[file.name] = file.originFileObj;
                    }
                } else if (item.fields) {
                    if (item.fields.length) {
                        const { record: nestedRecord } = this.parseFormItems(item.fields, value, {});
                        record[itemId] = item.dynamicFieldGroups ? [nestedRecord] : nestedRecord;
                    }
                } else {
                    record[itemId] = this.$date.isDayjs(value) ? value.toISOString() : value;
                }
            }
            return {
                form,
                record
            };
        },
        requestCreateNewRecord(form, record) {
            this.loading = true;
            const selectedNodeId = this.parsedFormState[BUILTIN_NODE_ID_SELECT];
            const member = this.members.find(m => m.nodeId === selectedNodeId);
            const baseUrl = member?.adminApiAddress || '';
            const request = form
                ? this.$http.postForm(`${baseUrl}${this.submitUrl}?${this.$qs.encode(record)}`, form)
                : this.$http.post(`${baseUrl}${this.submitUrl}`, this.getRequestBodyData(record));
            request
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
            let request;
            if (this.clusterMode) {
                const addressToKeys = {};
                for (const record of this.targetRecords) {
                    const adminApiAddress = this.members.find(m => m.nodeId === record.nodeId).adminApiAddress;
                    const keys = addressToKeys[adminApiAddress] || [];
                    keys.push(record.rowKey);
                    addressToKeys[adminApiAddress] = keys;
                }
                const requests = Object.entries(addressToKeys).map(([address, keys]) => {
                    const params = this.$rq.getQueryParams(this.queryKey, keys);
                    return this.$http.put(`${address}${this.submitUrl}${params}`, this.getRequestBodyData(updatedFields));
                });
                request = Promise.all(requests);
            } else {
                const params = this.$rq.getQueryParams(this.queryKey, this.targetRecordKeys);
                request = this.$http.put(`${this.submitUrl}${params}`, this.getRequestBodyData(updatedFields));
            }
            request.then(() => {
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
<style lang="scss">
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
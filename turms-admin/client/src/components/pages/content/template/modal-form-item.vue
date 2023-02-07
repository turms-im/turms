<template>
    <a-form-item
        :name="parentFieldName ? [parentFieldName, field.id] : field.id"
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
                {{ value.rawLabel || $t(value.label) }}
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
        <a-upload-dragger
            v-else-if="field.type === 'UPLOAD'"
            v-model:fileList="formState[field.id]"
            name="file"
            :accept="field.supportedFileTypes?.join(',')"
            :before-upload="() => false"
            :multiple="true"
        >
            <p class="ant-upload-drag-icon">
                <icon type="inbox" />
            </p>
            <p class="ant-upload-text">
                {{ $t('uploadAreaPrompt') }}
            </p>
        </a-upload-dragger>
    </a-form-item>
</template>

<script>
import Icon from '../../../common/icon.vue';

export default {
    name: 'modal-form-item',
    components: {
        Icon
    },
    props: {
        formState: {
            type: Object,
            required: true
        },
        field: {
            type: Object,
            required: true
        },
        labelCol: {
            type: Object,
            required: true
        },
        wrapperCol: {
            type: Object,
            required: true
        },
        parentFieldName: {
            type: String,
            default: ''
        }
    },
    methods: {
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
            return {
                disabledHours: () => allowFuture
                    ? this.$util.range(0, present.hour() - 1)
                    : this.$util.range(present.hour() + 1, 24)
            };
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
        }
    }
};
</script>
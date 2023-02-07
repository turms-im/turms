<template>
    <a-form-item
        v-for="(val, index) in formState"
        :key="val.key"
        :name="parentFieldName? [parentFieldName, field.id, index, 'value'] : [field.id, index, 'value']"
        :label="index ? '' : $t(field.label)"
        :required="false"
        :rules="$validator.parseRules(field.rules)"
        v-bind="index ? $layout.formItemLayoutWithoutLabel : $layout.formItemLayout"
    >
        <a-input
            v-model:value="val.value"
            class="content-modal-form__item dynamic-input"
            :placeholder="field.placeholder ? $t(field.placeholder) : ''"
            :date-id="`${field.id}-${val.key}`"
        />
        <minus-circle-outlined
            v-if="formState.length > 1 || index"
            class="dynamic-delete-button"
            @click="deleteInput(field, index)"
        />
    </a-form-item>
    <a-form-item
        :key="'add-button:' + field.id"
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

<script>
import { MinusCircleOutlined, PlusOutlined } from '@ant-design/icons-vue';

export default {
    name: 'modal-form-dynamic-input',
    components: {
        MinusCircleOutlined,
        PlusOutlined
    },
    props: {
        formState: {
            type: Array,
            required: true
        },
        parentFieldName: {
            type: String,
            default: ''
        },
        field: {
            type: Object,
            required: true
        }
    },
    methods: {
        addNewInput() {
            this.formState.push({
                value: '',
                key: Date.now()
            });
        },
        deleteInput(index) {
            this.formState.splice(index, 1);
        }
    }
};
</script>
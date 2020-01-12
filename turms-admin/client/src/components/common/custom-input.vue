<template>
    <a-textarea
        v-if="multiline"
        :placeholder="placeholder"
        :rows="rows"
        :value="computedValue"
        @change="onChange"
    />
    <a-input
        v-else
        v-decorator="decorator"
        :placeholder="placeholder"
        :value="computedValue"
        @change="onChange"
    />
</template>

<script>
const nonNumberRegx = /[^0-9]/g;
const onlyNumberAndComma = /[^0-9,]/g;
const nonSpecialCharsRegx = /[^0-9]/g;
const nonSpaceRegx = /\s/g;

export default {
    name: 'custom-input',
    props: {
        placeholder: {
            type: String,
            default: ''
        },
        onlyNumber: {
            type: Boolean,
            default: false
        },
        onlyNumberAndComma: {
            type: Boolean,
            default: false
        },
        nonSpecialChars: {
            type: Boolean,
            default: false
        },
        nonSpace: {
            type: Boolean,
            default: false
        },
        maxLength: {
            type: Number,
            default: null
        },
        multiline: {
            type: Boolean,
            default: false
        },
        rows: {
            type: Number,
            default: 1
        },
        decorator: {
            type: Array,
            default: () => []
        },
        labelCol: {
            type: Object,
            default: null
        },
        wrapperCol: {
            type: Object,
            default: null
        },
        label: {
            type: String,
            default: ''
        },
        value: {
            type: String,
            default: ''
        }
    },
    computed: {
        computedValue() {
            return this.value ? this.value.toString() : null;
        }
    },
    methods: {
        onChange(e) {
            let { value } = e.target;
            if (this.onlyNumber) {
                value = value.replace(nonNumberRegx, '');
                value = value.substring(0, 19);
            }
            if (this.onlyNumberAndComma) {
                value = value.replace(onlyNumberAndComma, '');
            }
            if (this.nonSpecialChars) {
                value = value.replace(nonSpecialCharsRegx, '');
            }
            if (this.nonSpace) {
                value = value.replace(nonSpaceRegx, '');
            }
            if (this.maxLength) {
                value = value.substring(0, this.maxLength);
            }
            if (this.computedValue !== value) {
                this.$emit('input', value);
            }
        }
    }
};
</script>
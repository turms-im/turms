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
        :rules="$validator.parseRules(rules)"
        :placeholder="placeholder"
        :value="computedValue"
        @change="onChange"
    />
</template>

<script>
const NON_NUMBER = /[^0-9]/g;
const ONLY_NUMBER_AND_COMMA = /[^0-9,]/g;
const NON_SPECIAL_CHARS = /[^0-9]/g;
const NON_SPACE = /\s/g;

const RULE_MAP = {
    onlyNumber: (value) => {
        return value
            .replace(NON_NUMBER, '')
            .substring(0, 19);
    },
    onlyNumberAndComma: (value) => {
        return value.replace(ONLY_NUMBER_AND_COMMA, '');
    },
    nonSpecialChars: (value) => {
        return value.replace(NON_SPECIAL_CHARS, '');
    },
    nonSpace: (value) => {
        return value.replace(NON_SPACE, '');
    },
    maxLength: (value, maxLength) => {
        return value.substring(0, maxLength);
    }
};

const RULE_NAMES = Object.keys(RULE_MAP);

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
        rules: {
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
            return this.value?.toString();
        }
    },
    methods: {
        onChange(e) {
            let {value} = e.target;
            for (const name of RULE_NAMES) {
                if (this[name]) {
                    const transformer = RULE_MAP[name];
                    if (transformer) {
                        value = transformer(value, this.maxLength);
                    }
                }
            }
            if (this.computedValue !== value) {
                this.$emit('update:value', value);
            }
        }
    }
};
</script>
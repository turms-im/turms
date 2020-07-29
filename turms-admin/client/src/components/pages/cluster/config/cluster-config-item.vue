<template>
    <div class="cluster-config-item">
        <a-tooltip
            v-if="pair[1].desc"
            placement="right"
            :title="pair[1].desc"
        >
            <a-icon
                type="question-circle"
                class="cluster-config-item__info"
            />
        </a-tooltip>
        <div :class="[{'cluster-config-item__name--deprecated': pair[1].deprecated}, 'cluster-config-item__name']">
            <a-icon
                v-if="!pair[1].mutable"
                type="close"
                class="cluster-config-item__immutable-icon"
            />
            {{ pair[0] }}
        </div>
        <div v-if="pair[1].type === 'string'">
            <a-input
                v-model="pair[1].value"
                :disabled="!pair[1].mutable"
                class="cluster-config-item__input"
            />
        </div>
        <div v-if="isNumberType(pair[1].type)">
            <a-input-number
                v-model="pair[1].value"
                :disabled="!pair[1].mutable"
                :min="0"
                class="cluster-config-item__input-number"
            />
        </div>
        <div v-if="pair[1].type === 'boolean'">
            <a-switch
                v-model="pair[1].value"
                :disabled="!pair[1].mutable"
            />
        </div>
        <div v-if="pair[1].type === 'enum'">
            <a-select
                v-model="pair[1].value"
                class="cluster-config-item__select"
            >
                <a-select-option
                    v-for="(option, index) in pair[1].options"
                    :key="index"
                    :disabled="!pair[1].mutable"
                    :value="option"
                >
                    {{ option }}
                </a-select-option>
            </a-select>
        </div>
        <div
            v-if="!isNumberType(pair[1].type)
                && pair[1].type !== 'string'
                && pair[1].type !== 'boolean'
                && pair[1].type !== 'enum'"
        >
            {{ pair[1].type }}
        </div>
        <div
            class="cluster-config-item__rollback"
            @click="onRollbackClicked(pair[1])"
        >
            <a-icon
                v-if="isValueChanged(pair[1])"
                type="rollback"
            />
        </div>
    </div>
</template>

<script>
export default {
    name: 'cluster-config-item',
    props: {
        pair: {
            type: Array,
            required: true
        }
    },
    data() {
        return {
            initialized: false,
            loading: false
        };
    },
    methods: {
        isValueChanged(item) {
            return typeof item.defaultValue !== 'undefined' && item.defaultValue !== item.value;
        },
        onRollbackClicked(item) {
            item.value = JSON.parse(JSON.stringify(item.defaultValue));
        },
        isNumberType(type) {
            return type === 'int'
                || type === 'long'
                || type === 'short'
                || type === 'float'
                || type === 'double';
        }
    }
};
</script>

<style lang="scss" scoped>
.cluster-config-item {
    display: flex;

    &:not(:last-child) {
        margin-bottom: 20px;
    }

    &__info {
        line-height: 2;
        margin-right: 12px;
    }

    &__name {
        margin-right: 18px;

        &--deprecated {
            text-decoration: line-through;
        }
    }

    &__immutable-icon {
        color: #cf1322;
    }

    &__input {
        min-width: 200px;
        max-width: 550px;
    }

    &__input-number {
        width: 150px;
    }

    &__item-select {
        width: 100%;
    }

    &__rollback {
        margin-left: 16px;
        cursor: pointer;
        color: #314659;
    }
}
</style>

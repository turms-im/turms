<template>
    <div class="cluster-config-item">
        <a-tooltip
            v-if="property.description"
            placement="right"
            :title="property.description"
        >
            <span class="cluster-config-item__info">
                <icon type="question-circle" />
            </span>
        </a-tooltip>
        <div :class="[{'cluster-config-item__name--deprecated': property.deprecated}, 'cluster-config-item__name']">
            <span
                v-if="!property.mutable"
                class="cluster-config-item__immutable-icon"
            >
                <icon type="close" />
            </span>
            {{ name }}
        </div>
        <div v-if="property.type === 'string'">
            <a-input
                v-model:value="property.value"
                :disabled="!property.mutable"
                class="cluster-config-item__input"
            />
        </div>
        <div v-if="isFlatListType(property)">
            <a-input
                v-model:value="property.value"
                :disabled="!property.mutable"
                :min="0"
                class="cluster-config-item__input"
            />
        </div>
        <div v-if="isNumberType(property.type)">
            <a-input-number
                v-model:value="property.value"
                :disabled="!property.mutable"
                :min="0"
                class="cluster-config-item__input-number"
            />
        </div>
        <div v-if="property.type === 'boolean'">
            <a-switch
                v-model:checked="property.value"
                :disabled="!property.mutable"
            />
        </div>
        <div v-if="property.type === 'enum'">
            <a-select
                v-model:value="property.value"
                class="cluster-config-item__select"
                :disabled="!property.mutable"
            >
                <a-select-option
                    v-for="(option, index) in property.options"
                    :key="index"
                    :value="option"
                >
                    {{ option }}
                </a-select-option>
            </a-select>
        </div>
        <div
            v-if="!isFlatListType(property) && !isFlatType(property.type)"
        >
            {{ property.type }}
        </div>
        <span
            v-if="isValueChanged(property)"
            class="cluster-config-item__rollback"
            @click="onRollbackClicked(property)"
        >
            <icon type="rollback" />
        </span>
    </div>
</template>

<script>
import Icon from '../../../common/icon.vue';

export default {
    name: 'cluster-config-item',
    components: {
        Icon
    },
    props: {
        name: {
            type: String,
            required: true
        },
        property: {
            type: Object,
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
            return item.defaultValue != null && item.defaultValue !== item.value;
        },
        onRollbackClicked(item) {
            item.value = this.$util.copy(item.defaultValue);
        },
        isFlatListType({type, elementType}) {
            const isList = type === 'java.util.List'
                || type === 'java.util.LinkedHashSet'
                || type === 'java.util.Set';
            const isFlatElement = this.isFlatType(elementType);
            return isList && isFlatElement;
        },
        isFlatType(type) {
            return this.isNumberType(type)
                || type === 'string'
                || type === 'boolean'
                || type === 'enum';
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

<style lang="scss">
.cluster-config-item {
    display: flex;

    &:not(:last-child) {
        margin-bottom: 20px;
    }

    &__info {
        margin-right: 12px;
        line-height: 2;
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
        color: #314659;
        cursor: pointer;
    }
}
</style>

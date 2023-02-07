<template>
    <div class="content-filter-group">
        <template v-for="filter in filters">
            <custom-input
                v-if="filter.type.toUpperCase() === 'INPUT'"
                :key="filter.id"
                v-model:value="filter.model"
                :placeholder="$t(filter.placeholder)"
                :only-number-and-comma="filter.rules == null || filter.rules.onlyNumberAndComma"
                :non-space="filter.rules?.nonSpace"
                class="search-filter search-filter__input"
                :data-id="filter.id"
            />
            <a-select
                v-if="filter.type.toUpperCase() === 'SELECT'"
                :key="filter.id"
                v-model:value="filter.model"
                class="search-filter search-filter__select"
                :style="`width: ${getSelectWidth(filter)}px`"
                :data-id="filter.id"
            >
                <a-select-option
                    v-for="option in (filter.options.base || []).concat(filter.options.values || [])"
                    :key="option.id.toString()"
                    :value="option.id.toString()"
                >
                    {{ option.rawLabel || $t(option.label) }}
                </a-select-option>
            </a-select>
            <date-range-picker
                v-if="filter.type.toUpperCase() === 'DATE-RANGE'"
                :key="filter.id"
                v-model:value="filter.model"
                :include-today="true"
                :show-time="true"
                :placeholder="getDatePickerPlaceholder(filter)"
                class="search-filter search-filter__date-picker"
                :data-id="filter.id"
            />
            <a-date-picker
                v-if="filter.type.toUpperCase() === 'DATE'"
                :key="filter.id"
                v-model:value="filter.model"
                :show-time="filter.showTime ?? true"
                :data-id="filter.id"
            />
        </template>
        <a-button
            class="search-filter search-filter__search"
            type="primary"
            :loading="loading"
            @click="onSearchClicked"
        >
            {{ filters.length ? $t('search') : $t('refresh') }}
        </a-button>
        <a-button
            v-if="filters.length"
            class="search-filter search-filter__clear-filters"
            type="primary"
            danger
            :loading="loading"
            @click="clearFilters"
        >
            {{ $t('clearFilters') }}
        </a-button>
    </div>
</template>

<script>
import CustomInput from '../../../common/custom-input.vue';
import DateRangePicker from '../../../common/date-range-picker.vue';

export default {
    name: 'content-filter-group',
    components: {
        CustomInput,
        DateRangePicker
    },
    props: {
        loading: {
            type: Boolean,
            default: false
        },
        filters: {
            type: Array,
            default: () => []
        }
    },
    emits: ['onSearchClicked'],
    watch: {
        filters: {
            handler() {
                for (const filter of this.filters) {
                    filter.id = filter.id || filter.name;
                    if (filter.model == null) {
                        this.resetFilterData(filter);
                    }
                }
            },
            immediate: true
        }
    },
    methods: {
        clearFilters() {
            for (const filter of this.filters) {
                this.resetFilterData(filter);
            }
        },
        resetFilterData(filter) {
            switch (filter.type) {
                case 'SELECT': {
                    let model = null;
                    const base = filter.options.base || [];
                    for (const value of base) {
                        if (value.id === 'ALL') {
                            model = 'ALL';
                            break;
                        }
                    }
                    filter.model = model;
                }
                    break;
                case 'DATE-RANGE':
                    filter.model = [];
                    break;
                case 'INPUT':
                    filter.model = '';
                    break;
                default:
                    filter.model = null;
            }
        },
        getDatePickerPlaceholder(filter) {
            if (filter.placeholder) {
                return this.$t(filter.placeholder);
            }
            return [this.$t(`${filter.name}Range.start`), this.$t(`${filter.name}Range.end`)];
        },
        getSelectWidth(filter) {
            const longestLabel = (filter.options.base || [])
                .concat(filter.options.values || [])
                .map(val => val.rawLabel || this.$t(val.label))
                .reduce((max, val) => max.length > val.length ? max : val, '');
            return longestLabel.length * 11;
        },
        onSearchClicked() {
            this.$emit('onSearchClicked');
        }
    }
};
</script>

<style>
.search-filter {
    margin-right: 18px !important;
    margin-bottom: 18px !important;
}

.search-filter__input {
    width: 200px !important;
}

.search-filter__select {
    min-width: 140px;
    max-width: 260px;
}

.search-filter__date-picker {
    width: 250px;
}

.content-filter-group {
    display: flex;
    flex-wrap: wrap;
}

</style>
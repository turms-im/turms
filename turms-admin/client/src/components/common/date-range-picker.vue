<template>
    <a-range-picker
        :value="range"
        :allow-clear="allowClear"
        :disabled="disabled"
        :disabled-date="disabledEndDate"
        :format="isMonthMode ? 'YYYY/MM' : showTime ? 'YYYY/MM/DD HH:mm:ss' : 'YYYY/MM/DD'"
        :placeholder="computedPlaceholder"
        :mode="isMonthMode ? ['month', 'month'] : ['date', 'date']"
        :ranges="presets"
        :show-time="showTime"
        @change="onRangeChange"
        @panelChange="onPanelChange"
    />
</template>

<script>
export default {
    name: 'date-range-picker',
    props: {
        disabled: {
            type: Boolean,
            default: false
        },
        allowClear: {
            type: Boolean,
            default: true
        },
        placeholder: {
            type: Array,
            default: () => []
        },
        isMonthMode: {
            type: Boolean,
            default: false
        },
        showTime: {
            type: Boolean,
            default: false
        },
        initialStartDate: {
            type: Object,
            default: null
        },
        initialEndDate: {
            type: Object,
            default: null
        },
        useDefaultDate: {
            type: Boolean,
            default: false
        },
        value: {
            type: Array,
            default: () => []
        },
        includeToday: {
            type: Boolean,
            default: false
        }
    },
    emits: ['update:value'],
    data() {
        let dateRange = [];
        let monthRange = [];
        if (this.initialStartDate && this.initialEndDate) {
            if (this.isMonthMode) {
                monthRange = [this.initialStartDate, this.initialEndDate];
            } else {
                dateRange = [this.initialStartDate, this.initialEndDate];
            }
        } else if (this.useDefaultDate) {
            monthRange = this.getLastMonthRange(3);
            dateRange = this.getLastMonthToYesterdayRange();
        }
        this.$emit('update:value', this.isMonthMode ? monthRange : dateRange);
        return {
            dateRange: dateRange,
            monthRange: monthRange
        };
    },
    computed: {
        computedPlaceholder() {
            return this.placeholder.length === 2
                ? this.placeholder
                : [this.$t('startDate'), this.$t('endDate')];
        },
        range() {
            return [...this.value];
        },
        presets() {
            const presets = {};
            if (this.isMonthMode) {
                presets[this.$t('lastThreeMonths')] = this.getLastMonthRange(3);
                presets[this.$t('lastSixMonths')] = this.getLastMonthRange(6);
                presets[this.$t('januaryToLastMonth')] = this.getJanuaryToLastMonthRange();
            } else {
                if (this.includeToday) {
                    presets[this.$t('lastMonthToToday')] = this.getLastMonthToTodayRange();
                    presets[this.$t('firstToToday')] = this.getFirstToToday();
                } else {
                    presets[this.$t('lastMonthToYesterday')] = this.getLastMonthToYesterdayRange();
                    presets[this.$t('firstToYesterday')] = this.getFirstToYesterday();
                }
            }
            return presets;
        }
    },
    watch: {
        isMonthMode: function (value) {
            this.$emit('update:value', value ? this.monthRange : this.dateRange);
        }
    },
    methods: {
        onRangeChange(range) {
            if (this.isMonthMode) {
                this.monthRange = range;
            } else {
                this.dateRange = range;
            }
            this.$emit('update:value', range);
        },
        onPanelChange(range) {
            if (this.isMonthMode) {
                this.monthRange = range;
            } else {
                this.dateRange = range;
            }
            this.$emit('update:value', range);
        },
        disabledEndDate(currentDate) {
            if (this.isMonthMode) {
                //TODO: check this bug
                return currentDate.month() >= this.$moment().month();
            } else {
                if (this.includeToday) {
                    return this.$moment().isBefore(currentDate, 'days');
                } else {
                    return this.$moment().isSameOrBefore(currentDate, 'days');
                }
            }
        },
        getLastMonthRange(number) {
            const startMonth = this.$moment().startOf('day').subtract(number, 'months');
            const endMonth = this.$moment().startOf('day').subtract(1, 'months');
            return [startMonth, endMonth];
        },
        getJanuaryToLastMonthRange() {
            const january = this.$moment().startOf('year');
            if (this.$moment().month() === 0) {
                return [january, january];
            } else {
                return [january, this.$moment().startOf('day').subtract(1, 'months')];
            }
        },
        getLastMonthToTodayRange() {
            return [
                this.$moment().startOf('day').subtract(1, 'months'),
                this.$moment().startOf('day')
            ];
        },
        getFirstToToday() {
            const first = this.$moment().startOf('month');
            const today = this.$moment().startOf('day');
            if (1 === today.daysInMonth()) {
                return [first, first];
            } else {
                return [first, today];
            }
        },
        getLastMonthToYesterdayRange() {
            return [
                this.$moment().startOf('day').subtract(1, 'days').subtract(1, 'months'),
                this.$moment().startOf('day').subtract(1, 'days')
            ];
        },
        getFirstToYesterday() {
            const first = this.$moment().startOf('month');
            if (1 === this.$moment().daysInMonth()) {
                return [first, first];
            } else {
                return [first, this.$moment().startOf('day').subtract(1, 'days')];
            }
        }
    }
};
</script>
<template>
    <a-date-picker
        :allow-clear="false"
        :disabled-date="disabledDate"
        :show-time="{ defaultValue: $moment('00:00:00', 'HH:mm:ss') }"
        format="YYYY-MM-DD HH:mm:ss"
    />
</template>

<script>
export default {
    name: 'date-picker',
    data() {
        return {
            disabledDate(current) {
                return current && current > this.$moment().endOf('day');
            }
        };
    }
};
</script>
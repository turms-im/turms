<template>
    <a-locale-provider :locale="locale">
        <Layout />
    </a-locale-provider>
</template>

<script>
import zhCN from 'ant-design-vue/lib/locale-provider/zh_CN';
import Layout from './components/layout/index';

export default {
    name: 'app',
    components: {
        Layout
    },
    data() {
        return {
            locale: zhCN
        };
    }
};
</script>

<style lang="scss">
    .search-filter-group {
        display: flex;
        flex-wrap: wrap;
    }

    .search-filter {
        margin-right: 18px !important;
        margin-bottom: 18px !important;
    }

    .search-filter__input {
        width: 200px !important;
    }

    .search-filter__select {
        min-width: 140px;
        max-width: 200px;
    }

    .search-filter__date-picker {
        width: 250px;
    }

    .search-filter-id {
        max-width: 200px;
    }

    .action-button {
        margin-right: 18px !important;
        margin-bottom: 18px !important;
    }

    td {
        word-break: break-all;
    }
</style>

<template>
    <a-config-provider :locale="locale">
        <Layout />
    </a-config-provider>
</template>

<script>
import Layout from './components/layout/index';

export default {
    name: 'app',
    components: {
        Layout
    },
    computed: {
        locale() {
            const locales = Object.values(this.$locales);
            for (const locale of locales) {
                if (this.$store.getters.locale === locale.locale) {
                    return locale;
                }
            }
            return locales.enUS;
        }
    },
    watch: {
        locale(locale) {
            this.$i18n.locale = locale.locale;
        }
    },
    created() {
        const locale = localStorage.getItem(this.$rs.storage.locale);
        if (locale) {
            this.$store.setLocale(locale);
        }
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
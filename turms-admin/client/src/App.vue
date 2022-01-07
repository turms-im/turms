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
@import "styles/main";

.ant-message-notice-content {
    max-width: 500px;
}

td {
    word-break: break-all;
}
</style>
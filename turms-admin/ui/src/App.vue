<template>
    <a-config-provider :locale="locale">
        <Layout />
    </a-config-provider>
</template>

<script>
import dayjs from 'dayjs';
import Layout from './components/layout/index.vue';
import 'dayjs/locale/es-us';
import 'dayjs/locale/ja';
import 'dayjs/locale/zh-cn';

export default {
    name: 'app',
    components: {
        Layout
    },
    computed: {
        locale() {
            const currentLocale = Object.values(this.$locales)
                .find(locale => this.$store.getters.locale === locale.locale)
                || this.$locales.enUS;
            dayjs.locale(currentLocale.locale);
            return currentLocale;
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
</style>
import {reactive, readonly} from 'vue';

export default {
    install: app => {
        const state = reactive({
            admin: null,
            locale: 'en',
            tab: null,
            url: ''
        });
        app.config.globalProperties.$store = {
            getters: readonly(state),
            setAdmin(admin) {
                state.admin = admin;
            },
            setLocale(locale) {
                state.locale = locale;
            },
            setTab(tab) {
                state.tab = tab;
            },
            setUrl(url) {
                state.url = url;
            },
            clearAdmin() {
                state.admin = null;
            }
        };
    }
};
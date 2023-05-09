import { reactive, readonly, type App } from 'vue';

const state = reactive({
    admin: null,
    locale: 'en',
    tab: null,
    url: ''
});

const $store = {
    getters: readonly(state),
    setAdmin(admin?: any) {
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

declare module '@vue/runtime-core' {
    interface ComponentCustomProperties {
        $store: typeof $store
    }
}

export default {
    install: (app: App): void => {
        app.config.globalProperties.$store = $store;
    }
};
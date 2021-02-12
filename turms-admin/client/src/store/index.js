import {reactive, readonly} from 'vue';

export default {
    install: app => {
        const state = reactive({
            tab: null,
            admin: null,
            url: ''
        });
        app.config.globalProperties.$store = {
            getters: readonly(state),
            setTab(tab) {
                state.tab = tab;
            },
            setAdmin(admin) {
                state.admin = admin;
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
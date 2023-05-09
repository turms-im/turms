import JSONbig from 'json-bigint';
import axios from 'axios';
import {createApp} from 'vue';
import dayjs from 'dayjs';
import enUS from 'ant-design-vue/es/locale/en_US';
import jaJP from 'ant-design-vue/es/locale/ja_JP';
import zhCN from 'ant-design-vue/es/locale/zh_CN';
import RelativeTime from 'dayjs/plugin/relativeTime';
import createApis from './apis';
import App from './App.vue';
import antd from './antd';
import i18n from './i18n';
import resources from './configs/resources.json';
import router from './router';
import store from './store';
import utils from './utils';

dayjs.extend(RelativeTime);

const $http = axios.create({
    timeout: 60 * 1000,
    transformResponse: [data => {
        try {
            return data && JSONbig.parse(data);
        } catch (e) {
            return data;
        }
    }]
});

const $locales = {
    enUS,
    jaJP,
    zhCN
};

const $sleep = (millis) => new Promise(resolve => setTimeout(resolve, millis));

declare module '@vue/runtime-core' {
    interface ComponentCustomProperties {
        $http: typeof $http
        $locales: typeof $locales
        $date: typeof dayjs
        $rs: typeof resources
        $sleep: typeof $sleep
    }
}

createApp(App)
    .use(i18n)
    .use(router)
    .use(antd)
    .use(utils)
    .use(store)
    .use({
        install: app => {
            const globalProperties = app.config.globalProperties;
            globalProperties.$http = $http;
            globalProperties.$locales = $locales;
            globalProperties.$date = dayjs;
            globalProperties.$rs = resources;
            globalProperties.$sleep = $sleep;
            globalProperties.$apis = createApis(globalProperties);
        }
    })
    .mount('#app');
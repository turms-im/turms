import JSONbig from 'json-bigint';
import * as _ from 'lodash-es';
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

const $_ = {
    get: _.get,
    set: _.set,
    isEqual: _.isEqual,
    uniq: _.uniq
};

declare module '@vue/runtime-core' {
    interface ComponentCustomProperties {
        $http: typeof $http
        $locales: typeof $locales
        $date: typeof dayjs
        $rs: typeof resources
        $_: typeof $_
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
            // Don't use "_" because of https://github.com/vuejs/vue-next/issues/2546
            // TODO: merge into $util
            globalProperties.$_ = $_;
            globalProperties.$sleep = $sleep;
            globalProperties.$apis = createApis(globalProperties);
        }
    })
    .mount('#app');
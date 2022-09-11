import JSONbig from 'json-bigint';
import _ from 'lodash';
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
import resources from './configs/resources';
import router from './router';
import store from './store';
import utils from './utils';

const isProd = process.env.NODE_ENV === 'production';
if (isProd) {
    if (window.console) {
        ['log', 'debug', 'warn', 'info'].forEach(method => {
            console[method] = () => {
            };
        });
    } else {
        window.console = {};
    }
}

dayjs.extend(RelativeTime);

createApp(App)
    .use(i18n)
    .use(router)
    .use(antd)
    .use(utils)
    .use(store)
    .use({
        install: app => {
            const globalProperties = app.config.globalProperties;
            globalProperties.$http = axios.create({
                timeout: 60 * 1000,
                transformResponse: [data => {
                    try {
                        return data && JSONbig.parse(data);
                    } catch (e) {
                        return data;
                    }
                }]
            });
            globalProperties.$locales = {
                enUS,
                jaJP,
                zhCN
            };
            globalProperties.$date = dayjs;
            globalProperties.$rs = resources;
            // Don't use "_" because of https://github.com/vuejs/vue-next/issues/2546
            globalProperties.$_ = {
                get: _.get,
                set: _.set,
                uniq: _.uniq
            };
            globalProperties.$sleep = (millis) => new Promise(resolve => setTimeout(resolve, millis));
            globalProperties.$apis = createApis(globalProperties);
        }
    })
    .mount('#app');
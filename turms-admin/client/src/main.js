import {createApp} from 'vue';
import enUS from 'ant-design-vue/es/locale/en_US';
import zhCN from 'ant-design-vue/es/locale/zh_CN';
import axios from 'axios';
import moment from 'moment';
import _ from 'lodash';
import resources from './configs/resources';
import App from './App.vue';

// Plugins
import router from './router';
import i18n from './i18n';
import antd from './antd';
import utils from './utils';
import store from './store';
import JSONbig from 'json-bigint';

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
                transformResponse: [function (data) {
                    return data ? JSONbig.parse(data) : data;
                }]
            });
            globalProperties.$locales = {
                enUS,
                zhCN
            };
            globalProperties.$moment = moment;
            globalProperties.$rs = resources;
            // Don't use "_" because of https://github.com/vuejs/vue-next/issues/2546
            globalProperties.$_ = {
                get: _.get,
                set: _.set
            };
            globalProperties.$sleep = async (millis) => await new Promise(resolve => setTimeout(resolve, millis));
        }
    })
    .mount('#app');
import {createApp} from 'vue';
import axios from 'axios';
import moment from 'moment';
import qs from 'querystring';
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
// Vue.config.productionTip = false;
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
            app.config.globalProperties.$http = axios.create({
                timeout: 15 * 1000,
                transformResponse: [function (data) {
                    return data ? JSONbig.parse(data) : data;
                }]
            });
            app.config.globalProperties.$moment = moment;
            app.config.globalProperties.$qs = qs;
            app.config.globalProperties.$rs = resources;
        }
    })
    .mount('#app');
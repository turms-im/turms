import Vue from 'vue';
import moment from 'moment';
import qs from 'querystring';

import App from './App.vue';
import router from './router';
import store from './store';
import resources from './configs/resources';
import i18n from './i18n';

const NODE_ENV = process.env.NODE_ENV;
const isProd = NODE_ENV === 'production';

require('./antd');
require('./utils');

Vue.config.productionTip = false;
if (isProd) {
    if (!window.console) {
        window.console = {};
    }
    const methods = ['log', 'debug', 'warn', 'info'];
    for (let i = 0; i < methods.length; i++) {
        console[methods[i]] = function () {};
    }
}
Vue.prototype.$moment = moment;
Vue.prototype.$qs = qs;
Vue.prototype.$rs = resources;

new Vue({
    created: function () {
        Vue.prototype.$validator.vm = this;
        Vue.prototype.$es = this;
    },
    render: (h) => h(App),
    i18n,
    store,
    router
}).$mount('#app');
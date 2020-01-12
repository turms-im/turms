import Vue from 'vue';
import Calculate from './calculate';
import CommonUtil from './common-util';
import LayoutUtil from './layout-util';
import RequestUtil from './request-util';
import UiUtil from './ui-util';
import Validator from './validator';

Vue.prototype.$cal = Calculate;
Vue.prototype.$util = CommonUtil;
Vue.prototype.$layout = LayoutUtil;
Vue.prototype.$rq = RequestUtil;
Vue.prototype.$ui = UiUtil;
Vue.prototype.$validator = Validator;
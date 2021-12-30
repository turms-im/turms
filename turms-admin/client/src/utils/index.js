import Calculate from './calculate';
import CommonUtil from './common-util';
import LayoutUtil from './layout-util';
import Querystring from './querystring';
import RequestUtil from './request-util';
import UiUtil from './ui-util';
import Validator from './validator';

export default {
    install: (app) => {
        app.config.globalProperties.$cal = Calculate;
        app.config.globalProperties.$util = CommonUtil;
        app.config.globalProperties.$layout = LayoutUtil;
        app.config.globalProperties.$qs = Querystring;
        app.config.globalProperties.$rq = RequestUtil;
        app.config.globalProperties.$ui = UiUtil;
        app.config.globalProperties.$validator = Validator;
        const t = app._context.provides[app.__VUE_I18N_SYMBOL__]?.global?.t;
        if (t) {
            Validator.getMessage = t;
        } else {
            throw new Error('"app.__VUE_I18N__.global.t" doesn\'t exist');
        }
    }
};
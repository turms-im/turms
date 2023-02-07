import type { App } from 'vue';
import Calculate from './calculate';
import CommonUtil from './common-util';
import FileSaver from './file-saver';
import LayoutUtil from './layout-util';
import Querystring from './querystring';
import RequestUtil from './request-util';
import Storage from './storage';
import UiUtil from './ui-util';
import Validator from './validator';

interface Addition {
    $cal: typeof Calculate;
    $util: typeof CommonUtil;
    $fs: typeof FileSaver;
    $layout: typeof LayoutUtil;
    $qs: typeof Querystring;
    $rq: typeof RequestUtil;
    $storage: typeof Storage;
    $ui: typeof UiUtil;
    $validator: typeof Validator;
}

const addition: Addition = {
    $cal: Calculate,
    $util: CommonUtil,
    $fs: FileSaver,
    $layout: LayoutUtil,
    $qs: Querystring,
    $rq: RequestUtil,
    $storage: Storage,
    $ui: UiUtil,
    $validator: Validator
};

declare module '@vue/runtime-core' {
    interface ComponentCustomProperties extends Addition {}
}

export default {
    install: (app: App): void => {
        Object.assign(app.config.globalProperties, addition);
        // @ts-ignore
        const t = app._context.provides[app.__VUE_I18N_SYMBOL__]?.global?.t;
        if (t) {
            Validator.getMessage = t;
        } else {
            throw new Error('"app.__VUE_I18N__.global.t" doesn\'t exist');
        }
    }
};
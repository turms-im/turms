import type { App } from 'vue';
import {
    Breadcrumb,
    Button,
    Card,
    Checkbox,
    Col,
    Collapse,
    ConfigProvider,
    DatePicker,
    Dropdown,
    Empty,
    Form,
    Input,
    InputNumber,
    Layout,
    Menu,
    Modal,
    Pagination,
    Popconfirm,
    Popover,
    Row,
    Select,
    Skeleton,
    Spin,
    Switch,
    Table,
    Tabs,
    Tooltip,
    Tree,
    TreeSelect,
    Upload,
    message
} from 'ant-design-vue';
import 'ant-design-vue/es/message/style/css';
import type {ComponentCustomProperties} from '@vue/runtime-core';

const $error = function (msg, error) {
    let desc = error.message;
    if (error.response?.data?.code) {
        desc = `(${error.response.status}, ${error.response.data.code}) ${error.response.data.reason}`;
    }
    message.error({
        content: `${msg}: ${desc}`,
        duration: 6
    });
    console.error(msg, error);
};

const $loading = function (this: ComponentCustomProperties, {promise, loading, success, error, successCb, finallyCb}: {
    promise: Promise<any>,
    loading: string,
    success?: string,
    error:string,
    successCb: (data: any) => void,
    finallyCb?: () => void
})  {
    if (!promise) {
        return;
    }
    const hide = message.loading(this.$t(loading), 0);
    promise
        .then(data => {
            if (success) {
                message.success(this.$t(success));
            }
            successCb(data);
        })
        .catch(e => {
            if (e) {
                $error(this.$t(error), e);
            } else {
                message.error(this.$t(error));
            }
        })
        .finally(() => {
            setTimeout(hide);
            finallyCb?.();
        });
};


declare module '@vue/runtime-core' {
    interface ComponentCustomProperties {
        $error: typeof $error
        $loading: typeof $loading
        $message: typeof message
    }
}

function loadAntd(app: App) {
    app.config.globalProperties.$message = message;
    app.config.globalProperties.$error = $error;
    app.config.globalProperties.$loading = $loading.bind(app.config.globalProperties);
    app.use(Breadcrumb);
    app.use(Button);
    app.use(Checkbox);
    app.use(Card);
    app.use(Col);
    app.use(Collapse);
    app.use(DatePicker);
    app.use(Dropdown);
    app.use(Empty);
    app.use(Form);
    app.use(Input);
    app.use(InputNumber);
    app.use(Layout);
    app.use(Menu);
    app.use(Modal);
    app.use(Pagination);
    app.use(Popconfirm);
    app.use(Popover);
    app.use(Row);
    app.use(Select);
    app.use(Skeleton);
    app.use(Spin);
    app.use(Switch);
    app.use(Table);
    app.use(Tabs);
    app.use(Tooltip);
    app.use(Tree);
    app.use(TreeSelect);
    app.use(Upload);

    app.use(ConfigProvider);
}

export default {
    install: (app: App): void => {
        loadAntd(app);
    }
};
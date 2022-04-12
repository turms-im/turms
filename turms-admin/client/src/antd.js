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
    // eslint-disable-next-line import/named
    Icon,
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
    message
} from 'ant-design-vue';

function loadAntd(app) {
    app.config.globalProperties.$message = message;
    app.config.globalProperties.$error = function (msg, error) {
        let desc = error.message;
        if (error.response?.data?.code) {
            desc = `(${error.response.status}, ${error.response.data.code}) ${error.response.data.reason}`;
        }
        this.$message.error({
            content: `${msg}: ${desc}`,
            duration: 6
        });
    };
    app.config.globalProperties.$loading = function ({promise, loading, success, error, successCb, finallyCb}) {
        if (!promise) {
            return;
        }
        const hide = this.$message.loading(this.$t(loading), 0);
        promise
            .then(data => {
                if (success) {
                    this.$message.success(this.$t(success));
                }
                successCb(data);
            })
            .catch(e => {
                if (e) {
                    this.$error(this.$t(error), e);
                } else {
                    this.$message.error(this.$t(error));
                }
            })
            .finally(() => {
                setTimeout(hide);
                finallyCb?.();
            });
    };
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
    app.use(Icon);
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

    app.use(ConfigProvider);
}

export default {
    install: (app) => {
        loadAntd(app);
    }
};
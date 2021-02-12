import './ant-style.css';
import {
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
    Icon,
    Input,
    InputNumber,
    Layout,
    Menu,
    message,
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
    TreeSelect
} from 'ant-design-vue';

function loadAntd(app) {
    app.config.globalProperties.$message = message;
    app.config.globalProperties.$error = function (msg, error) {
        let desc = error.message;
        if (error.response?.data?.code) {
            desc = `(${error.response.status}, ${error.response.data.code}) ${error.response.data.reason}`;
        }
        this.$message.error(`${msg}: ${desc}`);
    };
    app.use(Button);
    app.use(Icon);
    app.use(Row);
    app.use(Col);
    app.use(Layout);
    app.use(Dropdown);
    app.use(Menu);
    app.use(Pagination);
    app.use(Checkbox);
    app.use(DatePicker);
    app.use(Form);
    app.use(Input);
    app.use(InputNumber);
    app.use(Select);
    app.use(Switch);
    app.use(TreeSelect);
    app.use(Card);
    app.use(Collapse);
    app.use(Empty);
    app.use(Popover);
    app.use(Table);
    app.use(Tabs);
    app.use(Tooltip);
    app.use(Tree);
    app.use(Modal);
    app.use(Popconfirm);
    app.use(Skeleton);
    app.use(Spin);
    app.use(ConfigProvider);
}

export default {
    install: (app) => {
        loadAntd(app);
    }
};
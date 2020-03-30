import Vue from 'vue';
import './ant-style.css';
import {Button, Icon, Row, Col, Layout, Dropdown, Menu, Pagination, Checkbox, DatePicker,
    Form, Input, InputNumber, Select, Switch, TreeSelect, Card, Collapse, Empty, Popover,
    Table, Tabs, Tooltip, Tree, message, Modal, Popconfirm, Skeleton, Spin, ConfigProvider
} from 'ant-design-vue';

import 'ant-design-vue/lib/button';
import 'ant-design-vue/lib/icon/style/css';
import 'ant-design-vue/lib/row/style/css';
import 'ant-design-vue/lib/col/style/css';
import 'ant-design-vue/lib/layout/style/css';
import 'ant-design-vue/lib/dropdown/style/css';
import 'ant-design-vue/lib/menu/style/css';
import 'ant-design-vue/lib/pagination/style/css';
import 'ant-design-vue/lib/checkbox/style/css';
import 'ant-design-vue/lib/date-picker/style/css';
import 'ant-design-vue/lib/form/style/css';
import 'ant-design-vue/lib/input/style/css';
import 'ant-design-vue/lib/input-number/style/css';
import 'ant-design-vue/lib/select/style/css';
import 'ant-design-vue/lib/switch/style/css';
import 'ant-design-vue/lib/tree-select/style/css';
import 'ant-design-vue/lib/card/style/css';
import 'ant-design-vue/lib/collapse/style/css';
import 'ant-design-vue/lib/empty/style/css';
import 'ant-design-vue/lib/popover/style/css';
import 'ant-design-vue/lib/table/style/css';
import 'ant-design-vue/lib/tabs/style/css';
import 'ant-design-vue/lib/tooltip/style/css';
import 'ant-design-vue/lib/tree/style/css';
import 'ant-design-vue/lib/modal/style/css';
import 'ant-design-vue/lib/popconfirm/style/css';
import 'ant-design-vue/lib/skeleton/style/css';
import 'ant-design-vue/lib/spin/style/css';
import 'ant-design-vue/lib/message/style/css';

Vue.prototype.$message = message;
Vue.prototype.$error = function (msg, error) {
    let desc = error.message;
    if (error.response && error.response.data && error.response.data.code) {
        desc = `(${error.response.status}, ${error.response.data.code}) ${error.response.data.reason}`;
    }
    this.$message.error(`${msg}: ${desc}`);
};
Vue.use(Button);
Vue.use(Icon);
Vue.use(Row);
Vue.use(Col);
Vue.use(Layout);
Vue.use(Dropdown);
Vue.use(Menu);
Vue.use(Pagination);
Vue.use(Checkbox);
Vue.use(DatePicker);
Vue.use(Form);
Vue.use(Input);
Vue.use(InputNumber);
Vue.use(Select);
Vue.use(Switch);
Vue.use(TreeSelect);
Vue.use(Card);
Vue.use(Collapse);
Vue.use(Empty);
Vue.use(Popover);
Vue.use(Table);
Vue.use(Tabs);
Vue.use(Tooltip);
Vue.use(Tree);
Vue.use(Modal);
Vue.use(Popconfirm);
Vue.use(Skeleton);
Vue.use(Spin);
Vue.use(ConfigProvider);
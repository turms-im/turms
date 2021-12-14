<template>
    <component :is="type + '-outlined'" />
</template>

<script>
import {
    AreaChartOutlined,
    BarChartOutlined,
    BarsOutlined,
    CheckOutlined,
    CloseOutlined,
    ClusterOutlined,
    CodeOutlined,
    DatabaseOutlined,
    InfoCircleOutlined,
    LinkOutlined,
    LockOutlined,
    LogoutOutlined,
    MessageOutlined,
    ProfileOutlined,
    QuestionCircleOutlined,
    RollbackOutlined,
    SafetyOutlined,
    SettingOutlined,
    StopOutlined,
    TeamOutlined,
    UserOutlined
} from '@ant-design/icons-vue';

export default {
    name: 'icon',
    components: {
        AreaChartOutlined,
        BarChartOutlined,
        BarsOutlined,
        CheckOutlined,
        CloseOutlined,
        ClusterOutlined,
        CodeOutlined,
        DatabaseOutlined,
        InfoCircleOutlined,
        LinkOutlined,
        LockOutlined,
        LogoutOutlined,
        MessageOutlined,
        ProfileOutlined,
        QuestionCircleOutlined,
        RollbackOutlined,
        SafetyOutlined,
        SettingOutlined,
        StopOutlined,
        TeamOutlined,
        UserOutlined
    },
    props: {
        type: {
            type: String,
            required: true
        }
    }
};
</script>
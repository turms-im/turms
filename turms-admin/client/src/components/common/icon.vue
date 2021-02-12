<template>
    <area-chart-outlined v-if="type === 'area-chart'" />
    <bar-chart-outlined v-if="type === 'bar-chart'" />
    <bars-outlined v-if="type === 'bars'" />
    <check-outlined v-if="type === 'check'" />
    <close-outlined v-if="type === 'close'" />
    <cluster-outlined v-if="type === 'cluster'" />
    <database-outlined v-if="type === 'database'" />
    <info-circle-outlined v-if="type === 'info-circle'" />
    <link-outlined v-if="type === 'link'" />
    <lock-outlined v-if="type === 'lock'" />
    <logout-outlined v-if="type === 'logout'" />
    <message-outlined v-if="type === 'message'" />
    <profile-outlined v-if="type === 'profile'" />
    <question-circle-outlined v-if="type === 'question-circle'" />
    <rollback-outlined v-if="type === 'rollback'" />
    <safety-outlined v-if="type === 'safety'" />
    <setting-outlined v-if="type === 'setting'" />
    <team-outlined v-if="type === 'team'" />
    <user-outlined v-if="type === 'user'" />
</template>

<script>
import {
    AreaChartOutlined,
    BarChartOutlined,
    BarsOutlined,
    CheckOutlined,
    CloseOutlined,
    ClusterOutlined,
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
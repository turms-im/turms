<template>
    <a-tabs
        default-active-key="1"
        :animated="true"
    >
        <a-tab-pane
            key="1"
            :tab="$t('adminInfo')"
        >
            <access-control-admin-pane />
        </a-tab-pane>
        <a-tab-pane
            key="2"
            :tab="$t('adminRole')"
        >
            <access-control-role-pane />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import AccessControlAdminPane from './access-control-admin-pane.vue';
import AccessControlRolePane from './access-control-admin-role-pane.vue';

export default {
    name: 'access-control-tabs',
    components: {
        AccessControlAdminPane,
        AccessControlRolePane
    }
};
</script>
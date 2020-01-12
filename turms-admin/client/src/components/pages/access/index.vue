<template>
    <a-tabs default-active-key="1">
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
        <a-tab-pane
            key="3"
            :tab="$t('adminActionLog')"
        >
            <access-control-admin-action-log-pane />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import AccessControlAdminPane from './access-control-admin-pane';
import AccessControlRolePane from './access-control-admin-role-pane';
import AccessControlAdminActionLogPane from './access-control-admin-action-log-pane';

export default {
    name: 'access-control-tabs',
    components: {
        AccessControlAdminPane,
        AccessControlRolePane,
        AccessControlAdminActionLogPane
    }
};
</script>
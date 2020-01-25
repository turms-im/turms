<template>
    <a-tabs default-active-key="1">
        <a-tab-pane
            key="1"
            :tab="$t('userInfo')"
        >
            <content-user-info-pane />
        </a-tab-pane>
        <a-tab-pane
            key="2"
            :tab="$t('onlineUserStatus')"
        >
            <content-online-user-pane />
        </a-tab-pane>
        <a-tab-pane
            key="3"
            :tab="$t('userRelationship')"
        >
            <content-user-relationship-pane />
        </a-tab-pane>
        <a-tab-pane
            key="4"
            :tab="$t('friendRequest')"
        >
            <content-user-friend-request-pane />
        </a-tab-pane>
        <a-tab-pane
            key="5"
            :tab="$t('permissionGroup')"
        >
            <content-user-permission-group-pane />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import ContentUserInfoPane from './content-user-info-pane';
import ContentOnlineUserPane from './content-online-user-info-pane';
import ContentUserRelationshipPane from './relationship/index';
import ContentUserFriendRequestPane from './content-user-friend-request-pane';
import ContentUserPermissionGroupPane from './content-user-permission-group-pane';

export default {
    name: 'content-user-tabs',
    components: {
        ContentUserInfoPane,
        ContentOnlineUserPane,
        ContentUserRelationshipPane,
        ContentUserFriendRequestPane,
        ContentUserPermissionGroupPane
    }
};
</script>
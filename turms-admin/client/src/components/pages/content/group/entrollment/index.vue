<template>
    <a-tabs
        default-active-key="1"
        :animated="true"
    >
        <a-tab-pane
            key="1"
            :tab="$t('groupInvitation')"
        >
            <content-group-invitation-pane />
        </a-tab-pane>
        <a-tab-pane
            key="2"
            :tab="$t('joinRequest')"
        >
            <content-group-join-request-pane />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import ContentGroupInvitationPane from './content-group-invitation-pane.vue';
import ContentGroupJoinRequestPane from './content-group-join-request-pane.vue';

export default {
    name: 'content-group-enrollment-tabs',
    components: {
        ContentGroupInvitationPane,
        ContentGroupJoinRequestPane
    }
};
</script>
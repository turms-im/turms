<template>
    <a-tabs default-active-key="1">
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
import ContentGroupInvitationPane from './content-group-invitation-pane';
import ContentGroupJoinRequestPane from './content-group-join-request-pane';

export default {
    name: 'content-group-enrollment-tabs',
    components: {
        ContentGroupInvitationPane,
        ContentGroupJoinRequestPane
    }
};
</script>
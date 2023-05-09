<template>
    <a-tabs
        default-active-key="1"
        :animated="true"
    >
        <a-tab-pane
            key="1"
            :tab="$t('groupInfo')"
        >
            <content-group-info-pane />
        </a-tab-pane>
        <a-tab-pane
            key="2"
            :tab="$t('groupMemberInfo')"
        >
            <content-group-member-pane />
        </a-tab-pane>
        <a-tab-pane
            key="3"
            :tab="$t('groupBlocklist')"
        >
            <content-group-blocklist-pane />
        </a-tab-pane>
        <a-tab-pane
            key="4"
            :tab="$t('groupEnrollment')"
        >
            <content-group-enrollment-pane />
        </a-tab-pane>
        <a-tab-pane
            key="5"
            :tab="$t('groupType')"
        >
            <content-group-type-pane />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import ContentGroupInfoPane from './info/index.vue';
import ContentGroupMemberPane from './content-group-member-pane.vue';
import ContentGroupBlocklistPane from './content-group-blocklist-pane.vue';
import ContentGroupEnrollmentPane from './entrollment/index.vue';
import ContentGroupTypePane from './content-group-type-pane.vue';

export default {
    name: 'content-group-tabs',
    components: {
        ContentGroupInfoPane,
        ContentGroupMemberPane,
        ContentGroupBlocklistPane,
        ContentGroupEnrollmentPane,
        ContentGroupTypePane
    }
};
</script>
<template>
    <a-tabs default-active-key="1">
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
import ContentGroupInfoPane from './info/index';
import ContentGroupMemberPane from './content-group-member-pane';
import ContentGroupBlocklistPane from './content-group-blocklist-pane';
import ContentGroupEnrollmentPane from './entrollment/index';
import ContentGroupTypePane from './content-group-type-pane';

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
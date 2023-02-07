<template>
    <a-tabs
        default-active-key="1"
        :animated="true"
    >
        <a-tab-pane
            key="1"
            :tab="$t('userRelationship')"
        >
            <content-user-relationship-pane />
        </a-tab-pane>
        <a-tab-pane
            key="2"
            :tab="$t('userRelationshipGroup')"
        >
            <content-user-relationship-group-pane />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import ContentUserRelationshipPane from './content-user-relationship-pane.vue';
import ContentUserRelationshipGroupPane from './content-user-relationship-group-pane.vue';

export default {
    name: 'content-user-relationship-tabs',
    components: {
        ContentUserRelationshipPane,
        ContentUserRelationshipGroupPane
    }
};
</script>
<template>
    <a-tabs
        default-active-key="1"
        :animated="true"
    >
        <a-tab-pane
            key="1"
            :tab="$t('basicInfo')"
        >
            <content-group-base-info-pane />
        </a-tab-pane>
        <a-tab-pane
            key="2"
            :tab="$t('groupQuestion')"
        >
            <content-group-question-pane />
        </a-tab-pane>
    </a-tabs>
</template>

<script>
import ContentGroupBaseInfoPane from './content-group-info-pane.vue';
import ContentGroupQuestionPane from './content-group-question-pane.vue';

export default {
    name: 'content-group-info-tabs',
    components: {
        ContentGroupBaseInfoPane,
        ContentGroupQuestionPane
    }
};
</script>
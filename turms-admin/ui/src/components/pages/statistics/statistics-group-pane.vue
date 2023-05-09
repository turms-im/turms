<template>
    <statistics-template
        :name="name"
        :name-label="nameLabel"
        :path="path"
        :url="url"
        :resources="resources"
        :cards="cards"
    />
</template>

<script>
import StatisticsTemplate from './common/statistics-template.vue';

export default {
    name: 'statistics-group-pane',
    components: {StatisticsTemplate},
    data() {
        return {
            name: 'group',
            nameLabel: this.$t('group'),
            path: '/statistics/group',
            url: `${this.$rs.apis.group}/count`,
            resources: {
                all: ['created', 'deleted', 'sentMessage']
            },
            cards: [
                {
                    id: 'createdGroups',
                    tooltip: '昨日新增的群组数'
                },
                {
                    id: 'deletedGroups',
                    tooltip: '昨日解散的群组数'
                },
                {
                    id: 'groupsThatSentMessages',
                    tooltip: '存在上行消息的群组数'
                }
            ]
        };
    }
};
</script>
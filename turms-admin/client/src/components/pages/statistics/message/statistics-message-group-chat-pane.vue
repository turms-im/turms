<template>
    <statistics-template
        :name="name"
        :name-label="nameLabel"
        :path="path"
        :url="url"
        :params="params"
        :resources="resources"
        :cards="cards"
    />
</template>

<script>
import StatisticsTemplate from '../common/statistics-template.vue';

export default {
    name: 'statistics-message-group-chat-pane',
    components: {StatisticsTemplate},
    data() {
        return {
            name: 'message-group-chat',
            nameLabel: this.$t('groupMessage'),
            path: '/statistics/message/private-chat',
            url: `${this.$rs.apis.message}/count`,
            params: {
                areGroupMessages: true
            },
            resources: {
                all: ['sent', 'sentOnAverage', 'acknowledged', 'acknowledgedOnAverage']
            },
            cards: [
                {
                    id: 'sentMessages',
                    title: '群聊上行消息数',
                    tooltip: '用户在群组内发送的消息数'
                },
                {
                    id: 'sentMessagesOnAverage',
                    title: '群聊平均上行消息数',
                    tooltip: '平均每个群组内用户发送的消息数'
                },
                {
                    id: 'acknowledgedMessages',
                    title: '群聊下行消息数',
                    tooltip: '用户在线时接收到的群聊消息数与上线后接收到的离线群聊消息数之和'
                },
                {
                    id: 'acknowledgedMessagesOnAverage',
                    title: '群聊人均下行消息数',
                    tooltip: '平均每个用户接收到的消息数'
                }
            ]
        };
    }
};
</script>
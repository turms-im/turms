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
    name: 'statistics-message-private-chat-pane',
    components: {StatisticsTemplate},
    data() {
        return {
            name: 'message-private-chat',
            nameLabel: this.$t('privateMessage'),
            path: '/statistics/message/group-chat',
            url: `${this.$rs.apis.message}/count`,
            params: {
                areGroupMessages: false
            },
            resources: {
                all: ['sent', 'sentOnAverage', 'acknowledged', 'acknowledgedOnAverage']
            },
            cards: [
                {
                    id: 'sentMessages',
                    title: '单聊上行消息数',
                    tooltip: '用户发送给单个用户的消息数'
                },
                {
                    id: 'sentMessagesOnAverage',
                    title: '单聊人均上行消息数',
                    tooltip: '平均每个用户发送给单个用户的消息数'
                },
                {
                    id: 'acknowledgedMessages',
                    title: '单聊下行消息数',
                    tooltip: '用户在线时接收到的单聊消息数与上线后接收到的离线单聊消息数之和'
                },
                {
                    id: 'acknowledgedMessagesOnAverage',
                    title: '单聊人均下行消息数',
                    tooltip: '每个用户平均接收到的单聊消息数'
                }
            ]
        };
    }
};
</script>
<template>
    <statistics-template
        :name="name"
        :name-label="nameLabel"
        :path="path"
        :url="url"
        :resources="resources"
        :key-map="keyMap"
        :cards="cards"
    />
</template>

<script>
import StatisticsTemplate from './common/statistics-template.vue';

export default {
    name: 'statistics-user-pane',
    components: {StatisticsTemplate},
    data() {
        return {
            name: 'user',
            nameLabel: this.$t('user'),
            path: '/statistics/user',
            url: `${this.$rs.apis.user}/count`,
            resources: {
                all: ['registered', 'deleted', 'sentMessage', 'loggedIn', 'maxOnlineUsers'],
                beforeDay: ['registered']
            },
            keyMap: {
                beforeDay: {
                    registeredUsers: 'registeredUsersBeforeToday'
                }
            },
            cards: [
                {
                    id: 'registeredUsers',
                    tooltip: '昨日新注册的用户数'
                },
                {
                    id: 'deletedUsers',
                    tooltip: '昨日注销的用户数'
                },
                {
                    id: 'loggedInUsers',
                    tooltip: '昨日通过账号连接Turms服务器的用户数。当同一用户通过多台设备连接时，仍记为一次活跃'
                },
                {
                    id: 'usersWhoSentMessages',
                    tooltip: '昨日有进行发送消息操作的用户总数'
                },
                {
                    id: 'maxOnlineUsers',
                    tooltip: '昨日最大用户同时连接数'
                },
                {
                    id: 'registeredUsersBeforeToday',
                    tooltip: '截止到昨日所有注册用户总数',
                    excluded: true
                }
            ]
        };
    }
};
</script>
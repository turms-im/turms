<template>
    <a-layout-sider :trigger="null">
        <logo />
        <a-menu
            v-model="selectedKeys"
            theme="dark"
            mode="inline"
            :open-keys="openKeys"
            @openChange="openChange"
            @select="onMenuItemSelected"
        >
            <template v-for="item in menuItems">
                <a-sub-menu
                    v-if="item.children"
                    :key="item.key"
                >
                    <span slot="title">
                        <a-icon :type="item.iconType" />
                        <span>
                            {{ item.title }}
                        </span>
                    </span>
                    <a-menu-item
                        v-for="childItem in item.children"
                        :key="childItem.key"
                        :disabled="childItem.disabled"
                    >
                        <a-icon :type="childItem.iconType" />
                        {{ childItem.title }}
                    </a-menu-item>
                </a-sub-menu>
                <a-menu-item
                    v-else
                    :key="item.key"
                    :disabled="item.disabled"
                >
                    <a-icon :type="item.iconType" />
                    {{ item.title }}
                </a-menu-item>
            </template>
        </a-menu>
    </a-layout-sider>
</template>

<script>
import Logo from '../common/logo';

export default {
    name: 'layout-sider',
    components: {
        Logo
    },
    data() {
        const menuItems = [
            // TODO: We hide the statistics pages because the server side isn't ready for it.
            //  And we will integrate Flink to do the job later.
            // {
            //     key: 'statistics',
            //     iconType: 'bar-chart',
            //     title: this.$t('statistics'),
            //     children: [
            //         {
            //             key: '/statistics/user',
            //             iconType: 'user',
            //             title: this.$t('userClass')
            //         },
            //         {
            //             key: '/statistics/group',
            //             iconType: 'team',
            //             title: this.$t('groupClass')
            //         },
            //         {
            //             key: '/statistics/message',
            //             iconType: 'message',
            //             title: this.$t('messageClass')
            //         }
            //     ]
            // },
            {
                key: 'content',
                iconType: 'profile',
                title: this.$t('contentManagement'),
                children: [
                    {
                        key: '/content/user',
                        iconType: 'user',
                        title: this.$t('userClass')
                    },
                    {
                        key: '/content/group',
                        iconType: 'team',
                        title: this.$t('groupClass')
                    },
                    {
                        key: '/content/conversation',
                        iconType: 'message',
                        title: this.$t('conversationClass')
                    },
                    {
                        key: '/content/message',
                        iconType: 'message',
                        title: this.$t('messageClass')
                    }
                ]
            },
            {
                key: '/access',
                iconType: 'safety',
                title: this.$t('accessControl')
            },
            {
                key: '/cluster',
                iconType: 'cluster',
                title: this.$t('clusterManagement')
            },
            {
                key: '/about',
                iconType: 'info-circle',
                title: this.$t('about')
            }
        ];
        return {
            menuItems: menuItems,
            openKeys: ['statistics'],
            selectedKeys: ['statistics-user']
        };
    },
    mounted() {
        if (this.$route.fullPath) {
            const paths = this.$route.fullPath.split('/');
            if (paths.length === 3) {
                this.openKeys = [paths[1]];
            }
            if (paths.length >= 2) {
                this.selectedKeys = [this.$route.fullPath];
            }
        }
    },
    methods: {
        openChange(subMenu) {
            if (subMenu.length > 0) {
                this.openKeys = [subMenu[subMenu.length - 1]];
            } else {
                this.openKeys = [];
            }
        },
        onMenuItemSelected(item) {
            this.$router.push(item.key);
            this.$es.$emit('tabChanged', item.key);
        }
    }
};
</script>
<template>
    <a-layout-sider :trigger="null">
        <logo />
        <a-menu
            v-model:selected-keys="selectedKeys"
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
                    <template #title>
                        <icon :type="item.iconType" />
                        <span>
                            {{ item.title }}
                        </span>
                    </template>
                    <a-menu-item
                        v-for="childItem in item.children"
                        :key="childItem.key"
                        :disabled="childItem.disabled"
                    >
                        <icon :type="childItem.iconType" />
                        {{ childItem.title }}
                    </a-menu-item>
                </a-sub-menu>
                <a-menu-item
                    v-else
                    :key="item.key"
                    :disabled="item.disabled"
                >
                    <icon :type="item.iconType" />
                    {{ item.title }}
                </a-menu-item>
            </template>
        </a-menu>
    </a-layout-sider>
</template>

<script>
import Logo from '../common/logo';
import Icon from '../common/icon';

export default {
    name: 'layout-sider',
    components: {
        Logo,
        Icon
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
                key: '/content',
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
            menuItems,
            openKeys: ['/content'],
            selectedKeys: ['/content/user']
        };
    },
    mounted() {
        const fullPath = window.location.pathname || '';
        const paths = fullPath.split('/')
            .filter(path => path);
        if (paths.length >= 2) {
            this.openKeys = ['/' + paths[0]];
        }
        if (paths.length) {
            this.selectedKeys = [fullPath];
        }
    },
    methods: {
        openChange(subMenu) {
            this.openKeys = subMenu.length
                ? [subMenu[subMenu.length - 1]]
                : [];
        },
        onMenuItemSelected(item) {
            this.$router.push(item.key);
            this.$store.setTab(item.key);
        }
    }
};
</script>
<template>
    <a-layout-sider :trigger="null">
        <logo />
        <a-menu
            v-model:selected-keys="selectedKeys"
            theme="dark"
            mode="inline"
            :open-keys="openKeys"
            @openChange="openChange"
            @select="item => onMenuItemSelected(item.key)"
        >
            <template v-for="item in menuItems">
                <a-sub-menu
                    v-if="item.children"
                    :key="item.key"
                >
                    <template #title>
                        <icon :type="item.iconType" />
                        <span>
                            {{ $t(item.title) }}
                        </span>
                    </template>
                    <a-menu-item
                        v-for="childItem in item.children"
                        :key="childItem.key"
                        :disabled="childItem.disabled"
                    >
                        <icon :type="childItem.iconType" />
                        {{ $t(childItem.title) }}
                    </a-menu-item>
                </a-sub-menu>
                <a-menu-item
                    v-else
                    :key="item.key"
                    :disabled="item.disabled"
                >
                    <icon :type="item.iconType" />
                    {{ $t(item.title) }}
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
                title: 'contentManagement',
                children: [
                    {
                        key: '/content/user',
                        iconType: 'user',
                        title: 'userClass'
                    },
                    {
                        key: '/content/group',
                        iconType: 'team',
                        title: 'groupClass'
                    },
                    {
                        key: '/content/conversation',
                        iconType: 'message',
                        title: 'conversationClass'
                    },
                    {
                        key: '/content/message',
                        iconType: 'message',
                        title: 'messageClass'
                    }
                ]
            },
            {
                key: '/access',
                iconType: 'safety',
                title: 'accessControl'
            },
            {
                key: '/cluster',
                iconType: 'cluster',
                title: 'clusterManagement'
            },
            {
                key: '/terminal',
                iconType: 'code',
                title: 'clientTerminal'
            },
            {
                key: '/about',
                iconType: 'info-circle',
                title: 'about'
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
            this.onMenuItemSelected(fullPath);
        }
    },
    methods: {
        openChange(subMenu) {
            this.openKeys = subMenu.length
                ? [subMenu[subMenu.length - 1]]
                : [];
        },
        onMenuItemSelected(tab) {
            this.$router.push(tab);
            this.$store.setTab(tab);
        }
    }
};
</script>
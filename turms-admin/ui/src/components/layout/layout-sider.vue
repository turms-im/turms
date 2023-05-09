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
import Logo from '../common/logo.vue';
import Icon from '../common/icon.vue';

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
                key: '/cluster',
                iconType: 'cluster',
                title: 'clusterManagement',
                children: [
                    {
                        key: '/cluster/dashboard',
                        iconType: 'dashboard',
                        title: 'clusterDashboard'
                    },
                    {
                        key: '/cluster/config',
                        iconType: 'setting',
                        title: 'clusterConfig'
                    },
                    {
                        key: '/cluster/flight-recorder',
                        iconType: 'monitor',
                        title: 'flightRecorder'
                    },
                    {
                        key: '/cluster/plugin',
                        iconType: 'api',
                        title: 'clusterPlugin'
                    }
                ]
            },
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
                key: '/blocklist',
                iconType: 'stop',
                title: 'blocklist'
            },
            {
                key: '/access',
                iconType: 'safety',
                title: 'accessControl'
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
        let openKey;
        let selectedKey;
        const fullPath = window.location.pathname || '';
        const paths = fullPath.split('/')
            .filter(Boolean);
        if (paths.length) {
            const fullPathExists = menuItems
                .find(item => item.key === fullPath || item.children?.find(sub => sub.key === fullPath));
            if (fullPathExists) {
                selectedKey = fullPath;
                if (paths.length >= 2) {
                    openKey = `/${paths[0]}`;
                }
            }
        }
        if (!selectedKey) {
            openKey = menuItems[0].key;
            selectedKey = menuItems[0].children?.[0]?.key || openKey;
        }
        this.onMenuItemSelected(selectedKey);
        return {
            menuItems,
            openKeys: openKey ? [openKey] : [],
            selectedKeys: [selectedKey]
        };
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
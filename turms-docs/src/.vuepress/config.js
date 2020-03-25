module.exports = {
    title: 'Turms Documentation',
    base: '/turms/',
    dest: '../docs',
    themeConfig: {
        repo: 'https://github.com/turms-im/turms',
        lastUpdated: '上次更新',
        smoothScroll: false,
        sidebar: [
            {
                title: '介绍',
                collapsable: false,
                children: [
                    '/intro/',
                    '/intro/features',
                    '/intro/redevelopment',
                ]
            },
            {
                title: '业务功能',
                collapsable: false,
                children: [
                    '/features/',
                    '/features/simultaneous-login',
                    '/features/user',
                    '/features/group',
                    '/features/message'
                ]
            },
            {
                title: '开发者文档',
                collapsable: false,
                children: [
                    '/for-developers/quick-start',
                    '/for-developers/architecture',
                    '/for-developers/config',
                    '/for-developers/admin-api',
                    '/for-developers/client-api',
                    '/for-developers/status-code',
                    '/for-developers/custom-plugin'
                ]
            }
        ]
    }
};
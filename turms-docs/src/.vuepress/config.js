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
                    '/features/user-info',
                    '/features/user-relationship',
                    '/features/group',
                    '/features/message',
                    '/features/others',
                ]
            },
            {
                title: '开发者文档',
                collapsable: false,
                children: [
                    '/for-developers/quick-start',
                    '/for-developers/config',
                    '/for-developers/api',
                    '/for-developers/custom-plugin'
                ]
            }
        ]
    }
}
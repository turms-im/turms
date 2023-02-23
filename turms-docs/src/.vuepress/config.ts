import { defineUserConfig } from 'vuepress';
import { mdEnhancePlugin } from 'vuepress-plugin-md-enhance';
import { searchPlugin } from '@vuepress/plugin-search';
import { defaultTheme } from '@vuepress/theme-default';

export default defineUserConfig({
    title: 'Turms Documentation',
    base: '/docs/',
    plugins: [
        searchPlugin({
            locales: {
                '/': {
                    placeholder: '搜索',
                }
            },
            maxSuggestions: 10
        }),
        mdEnhancePlugin({
            codetabs: true,
            mermaid: true
        })
    ],
    theme: defaultTheme({
        repo: 'https://github.com/turms-im/turms',
        contributors: false,
        lastUpdatedText: '上次更新',
        logo: 'https://raw.githubusercontent.com/turms-im/assets/master/logo/pegion.svg',
        sidebar: [
            {
                text: '介绍',
                link: '/README.md',
            },
            {
                text: '社区常见问题',
                link: '/community/README.md',
            },
            {
                text: '业务功能',
                collapsible: true,
                children: [
                    '/features/',
                    '/features/simultaneous-login.md',
                    '/features/user.md',
                    '/features/group.md',
                    '/features/message.md'
                ]
            },
            {
                text: '服务端指南',
                collapsible: true,
                children: [
                    {
                        text: '部署',
                        children: [
                            '/for-developers/getting-started.md',
                            '/for-developers/distribution.md',
                            '/for-developers/config.md'
                        ]
                    },
                    {
                        text: '设计',
                        children: [
                            '/for-developers/architecture.md',
                            '/for-developers/schema.md',
                            '/for-developers/status-aware.md'
                        ]
                    },
                    {
                        text: '开发',
                        children: [
                            '/for-developers/redevelopment.md',
                            '/for-developers/rules.md',
                            '/for-developers/plugin.md',
                            '/for-developers/code.md',
                            '/for-developers/testing.md'
                            // TODO: Config Development
                        ]
                    },
                    {
                        text: '主要模块',
                        children: [
                            '/for-developers/cluster.md',
                            '/for-developers/system-resource-management.md',
                            '/for-developers/observability.md',
                            '/for-developers/security.md',
                            '/for-developers/storage.md',
                            '/for-developers/chatbot.md',
                            '/for-developers/anti-spam.md',
                            '/for-developers/data-analytics.md'
                        ]
                    }
                ]
            },
            {
                text: '客户端指南',
                collapsible: true,
                children: [
                    '/for-developers/client-quick-start.md',
                    '/for-developers/client-requirements.md',
                    '/for-developers/client-api.md',
                    '/for-developers/client-session.md',
                    '/for-developers/client-metrics.md',
                    '/for-developers/client-identity-access-management.md',
                    '/for-developers/client-server-protocol.md',
                    '/for-developers/client-turms-client-js.md'
                ]
            },
            '/for-developers/turms-admin.md',
            {
                text: '手册',
                collapsible: true,
                children: [
                    '/for-developers/admin-api.md',
                    '/for-developers/status-code.md'
                ]
            }
        ]
    })
});
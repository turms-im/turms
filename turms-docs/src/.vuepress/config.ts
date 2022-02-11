import { defineUserConfig } from 'vuepress';
import type { DefaultThemeOptions } from 'vuepress';
const { path } = require('@vuepress/utils');

export default defineUserConfig<DefaultThemeOptions>({
    title: 'Turms Documentation',
    base: '/docs/',
    plugins: [
        [
            '@vuepress/plugin-search',
            {
                locales: {
                    '/': {
                        placeholder: '搜索',
                    }
                },
                maxSuggestions: 10
            }
        ],
        [
            path.resolve(__dirname, '../plugins/mermaid'),
        ]
    ],
    themeConfig: {
        repo: 'https://github.com/turms-im/turms',
        contributors: false,
        lastUpdatedText: '上次更新',
        logo: 'https://raw.githubusercontent.com/turms-im/assets/master/logo/pegion.svg',
        smoothScroll: false,
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
                text: '服务端知识',
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
                            '/for-developers/code.md'
                            // TODO: 1. Config Development; 2. Testing
                        ]
                    },
                    {
                        text: '主要模块',
                        children: [
                            '/for-developers/cluster.md',
                            '/for-developers/system-resource-management.md',
                            '/for-developers/observability.md',
                            '/for-developers/security.md',
                            '/for-developers/anti-spam.md',
                            '/for-developers/data-analytics.md'
                        ]
                    }
                ]
            },
            '/for-developers/client-api.md',
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
    }
});
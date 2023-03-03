import { defineUserConfig } from 'vuepress';
import { mdEnhancePlugin } from 'vuepress-plugin-md-enhance';
import { docsearchPlugin } from '@vuepress/plugin-docsearch';
import { defaultTheme } from '@vuepress/theme-default';

export default defineUserConfig({
    title: 'Turms Documentation',
    base: '/docs/',
    plugins: [
        docsearchPlugin({
            appId: 'WV3XO0QV2E',
            apiKey: 'f7a7c652875d1f2cc0f0ec52d8f8f660',
            indexName: 'turms-imio',
            locales: {
                '/': {
                    placeholder: '搜索文档',
                    translations: {
                        button: {
                            buttonText: '搜索文档',
                            buttonAriaLabel: '搜索文档'
                        },
                        modal: {
                            searchBox: {
                                resetButtonTitle: '清除查询条件',
                                resetButtonAriaLabel: '清除查询条件',
                                cancelButtonText: '取消',
                                cancelButtonAriaLabel: '取消'
                            },
                            startScreen: {
                                recentSearchesTitle: '搜索历史',
                                noRecentSearchesText: '没有搜索历史',
                                saveRecentSearchButtonTitle: '保存至搜索历史',
                                removeRecentSearchButtonTitle: '从搜索历史中移除',
                                favoriteSearchesTitle: '收藏',
                                removeFavoriteSearchButtonTitle: '从收藏中移除'
                            },
                            errorScreen: {
                                titleText: '无法获取结果',
                                helpText: '你可能需要检查你的网络连接'
                            },
                            footer: {
                                selectText: '选择',
                                navigateText: '切换',
                                closeText: '关闭',
                                searchByText: '搜索提供者'
                            },
                            noResultsScreen: {
                                noResultsText: '无法找到相关结果',
                                suggestedQueryText: '你可以尝试查询',
                                reportMissingResultsLinkText: '点击反馈',
                                reportMissingResultsText: '你认为该查询应该有结果？'
                            }
                        }
                    }
                }
            }
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
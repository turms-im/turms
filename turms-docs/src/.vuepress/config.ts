import {defineUserConfig} from 'vuepress';
import {mdEnhancePlugin} from 'vuepress-plugin-md-enhance';
import {docsearchPlugin} from '@vuepress/plugin-docsearch';
import {defaultTheme} from '@vuepress/theme-default';

export default defineUserConfig({
    title: 'Turms',
    base: '/docs/',
    locales: {
        '/': {
            lang: 'en-US',
        },
        '/zh-CN/': {
            lang: 'zh-CN',
        },
    },
    plugins: [
        docsearchPlugin({
            appId: 'WV3XO0QV2E',
            apiKey: 'f7a7c652875d1f2cc0f0ec52d8f8f660',
            indexName: 'turms-imio',
            locales: {
                '/zh-CN/': {
                    placeholder: '搜索',
                    translations: {
                        button: {
                            buttonText: '搜索',
                            buttonAriaLabel: '搜索'
                        },
                        modal: {
                            searchBox: {
                                resetButtonTitle: '重置查询',
                                resetButtonAriaLabel: '重置查询',
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
                                titleText: '无法获取搜索结果',
                                helpText: '请检查网络连接'
                            },
                            footer: {
                                selectText: '选择',
                                navigateText: '切换',
                                closeText: '关闭',
                                searchByText: '搜索提供者'
                            },
                            noResultsScreen: {
                                noResultsText: '没有找到相关结果',
                                suggestedQueryText: '您可能想搜索',
                                reportMissingResultsLinkText: '点击反馈',
                                reportMissingResultsText: '没有找到结果？请报告缺失'
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
        logo: 'https://raw.githubusercontent.com/turms-im/assets/master/logo/pegion.svg',
        locales: {
            '/zh-CN/': {
                lastUpdatedText: '上次更新',
                sidebar: [
                    {
                        text: '介绍',
                        link: '/zh-CN/README.md',
                    },
                    {
                        text: '社区常见问题',
                        link: '/zh-CN/community/README.md',
                    },
                    {
                        text: '业务功能',
                        collapsible: true,
                        children: [
                            '/zh-CN/feature/README.md',
                            '/zh-CN/feature/simultaneous-login.md',
                            '/zh-CN/feature/user.md',
                            '/zh-CN/feature/group.md',
                            '/zh-CN/feature/message.md'
                        ]
                    },
                    {
                        text: '设计',
                        children: [
                            '/zh-CN/design/architecture.md',
                            '/zh-CN/design/schema.md',
                            '/zh-CN/design/status-aware.md'
                        ]
                    },
                    {
                        text: '服务端指南',
                        collapsible: true,
                        children: [
                            {
                                text: '部署',
                                children: [
                                    '/zh-CN/server/deployment/getting-started.md',
                                    '/zh-CN/server/deployment/distribution.md',
                                    '/zh-CN/server/deployment/config.md'
                                ]
                            },
                            {
                                text: '开发',
                                children: [
                                    '/zh-CN/server/development/redevelopment.md',
                                    '/zh-CN/server/development/rules.md',
                                    '/zh-CN/server/development/plugin.md',
                                    '/zh-CN/server/development/code.md',
                                    '/zh-CN/server/development/testing.md'
                                    // TODO: Config Development
                                ]
                            },
                            {
                                text: '主要模块',
                                children: [
                                    // Though some topics can be independent,
                                    // but we still put them under "module" because we don't
                                    // have enough content for an independent topic currently.
                                    '/zh-CN/server/module/identity-access-management.md',
                                    '/zh-CN/server/module/cluster.md',
                                    '/zh-CN/server/module/system-resource-management.md',
                                    '/zh-CN/server/module/observability.md',
                                    '/zh-CN/server/module/security.md',
                                    '/zh-CN/server/module/storage.md',
                                    '/zh-CN/server/module/chatbot.md',
                                    '/zh-CN/server/module/anti-spam.md',
                                    '/zh-CN/server/module/data-analytics.md'
                                ]
                            }
                        ]
                    },
                    {
                        text: '客户端指南',
                        collapsible: true,
                        children: [
                            '/zh-CN/client/quick-start.md',
                            '/zh-CN/client/requirements.md',
                            '/zh-CN/client/api.md',
                            '/zh-CN/client/session.md',
                            '/zh-CN/client/metrics.md',
                            '/zh-CN/client/communication-protocol.md',
                            '/zh-CN/client/turms-client-js.md'
                        ]
                    },
                    '/zh-CN/turms-admin.md',
                    {
                        text: '手册',
                        collapsible: true,
                        children: [
                            '/zh-CN/reference/admin-api.md',
                            '/zh-CN/reference/status-code.md'
                        ]
                    }
                ],
            },
            '/': {
                sidebar: {
                    '/README.md': [
                        {
                            text: 'Intro',
                            link: 'README.md',
                        },
                    ],
                    '/community/': [
                        {
                            text: 'Community FAQ',
                            link: '/community/README.md',
                        }
                    ],
                    '/feature/': [
                        {
                            text: 'Business Features',
                            collapsible: true,
                            children: [
                                '/feature/README.md',
                                '/feature/simultaneous-login.md',
                                '/feature/user.md',
                                '/feature/group.md',
                                '/feature/message.md'
                            ]
                        },
                    ],
                    '/design/': [
                        {
                            text: 'Design',
                            children: [
                                '/design/architecture.md',
                                '/design/schema.md',
                                '/design/status-aware.md'
                            ]
                        }
                    ],
                    '/server/': [
                        {
                            text: 'Server Guide',
                            collapsible: true,
                            children: [
                                {
                                    text: 'Deployment',
                                    children: [
                                        '/server/deployment/getting-started.md',
                                        '/server/deployment/distribution.md',
                                        '/server/deployment/config.md'
                                    ]
                                },
                                {
                                    text: 'Development',
                                    children: [
                                        '/server/development/redevelopment.md',
                                        '/server/development/rules.md',
                                        '/server/development/plugin.md',
                                        '/server/development/code.md',
                                        '/server/development/testing.md'
                                        // TODO: Config Development
                                    ]
                                },
                                {
                                    text: 'Main Modules',
                                    children: [
                                        // Though some topics can be independent,
                                        // but we still put them under "module" because we don't
                                        // have enough content for an independent topic currently.
                                        '/server/module/identity-access-management.md',
                                        '/server/module/cluster.md',
                                        '/server/module/system-resource-management.md',
                                        '/server/module/observability.md',
                                        '/server/module/security.md',
                                        '/server/module/storage.md',
                                        '/server/module/chatbot.md',
                                        '/server/module/anti-spam.md',
                                        '/server/module/data-analytics.md'
                                    ]
                                }
                            ]
                        }
                    ],
                    '/client/': [
                        {
                            text: 'Client Guide',
                            collapsible: true,
                            children: [
                                '/client/quick-start.md',
                                '/client/requirements.md',
                                '/client/api.md',
                                '/client/session.md',
                                '/client/metrics.md',
                                '/client/communication-protocol.md',
                                '/client/turms-client-js.md'
                            ]
                        }
                    ],
                    '/turms-admin': [
                        {
                            text: 'turms-admin',
                            link: '/turms-admin.md',
                        },
                    ],
                    '/reference/': [
                        {
                            text: 'Reference',
                            collapsible: true,
                            children: [
                                '/reference/admin-api.md',
                                '/reference/status-code.md'
                            ]
                        }
                    ]
                }
            },
        },
    })
});
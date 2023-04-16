import {defineUserConfig} from 'vuepress';
import {mdEnhancePlugin} from 'vuepress-plugin-md-enhance';
import {docsearchPlugin} from '@vuepress/plugin-docsearch';
import {defaultTheme} from '@vuepress/theme-default';

export default defineUserConfig({
    title: 'Turms Documentation',
    base: '/docs/',
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
        lastUpdatedText: '上次更新',
        logo: 'https://raw.githubusercontent.com/turms-im/assets/master/logo/pegion.svg',
        locales: {
            '/': {
                sidebar: [
                    {
                        text: 'Intro',
                        link: '/README.md',
                    },
                    {
                        text: 'Community FAQ',
                        link: '/community/README.md',
                    },
                    {
                        text: 'Business Features',
                        collapsible: true,
                        children: [
                            '/feature/',
                            '/feature/simultaneous-login.md',
                            '/feature/user.md',
                            '/feature/group.md',
                            '/feature/message.md'
                        ]
                    },
                    {
                        text: 'Design',
                        children: [
                            '/design/architecture.md',
                            '/design/schema.md',
                            '/design/status-aware.md'
                        ]
                    },
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
                    },
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
                    },
                    '/turms-admin.md',
                    {
                        text: 'Reference',
                        collapsible: true,
                        children: [
                            '/reference/admin-api.md',
                            '/reference/status-code.md'
                        ]
                    }
                ]
            },
            '/zh-CN': {
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
                            '/feature/',
                            '/feature/simultaneous-login.md',
                            '/feature/user.md',
                            '/feature/group.md',
                            '/feature/message.md'
                        ]
                    },
                    {
                        text: '设计',
                        children: [
                            '/design/architecture.md',
                            '/design/schema.md',
                            '/design/status-aware.md'
                        ]
                    },
                    {
                        text: '服务端指南',
                        collapsible: true,
                        children: [
                            {
                                text: '部署',
                                children: [
                                    '/server/deployment/getting-started.md',
                                    '/server/deployment/distribution.md',
                                    '/server/deployment/config.md'
                                ]
                            },
                            {
                                text: '开发',
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
                                text: '主要模块',
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
                    },
                    {
                        text: '客户端指南',
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
                    },
                    '/turms-admin.md',
                    {
                        text: '手册',
                        collapsible: true,
                        children: [
                            '/reference/admin-api.md',
                            '/reference/status-code.md'
                        ]
                    }
                ]
            }
        }
    })
});
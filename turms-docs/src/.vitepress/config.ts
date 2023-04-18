import { defineConfig } from 'vitepress';
import mermaid from './plugins/mermaid/mermaid-plugin';

export default defineConfig({
    title: 'Turms Documentation',
    base: '/docs/',
    markdown: {
        config: (md) => {
            md.use(mermaid);
        }
    },
    themeConfig: {
        logo: 'https://raw.githubusercontent.com/turms-im/assets/master/logo/pegion.svg',
        lastUpdatedText: '上次更新',
        outline: {
            label: '当前页'
        },
        docFooter: {
            prev: '上一页',
            next: '下一页'
        },
        socialLinks: [
            {
                icon: 'github',
                link: 'https://github.com/turms-im/turms'
            }
        ],
        search: {
            provider: 'algolia',
            options: {
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
            }
        },

        sidebar: [
            {
                text: '介绍',
                link: '/index.md',
            },
            {
                text: '社区常见问题',
                link: '/community/index.md',
            },
            {
                text: '业务功能',
                collapsed: true,
                items: [
                    {
                        text: '业务功能介绍',
                        link: '/features/index.md'
                    },
                    {
                        text: '多端登录',
                        link: '/features/simultaneous-login.md'
                    },
                    {
                        text: '用户相关功能',
                        link: '/features/user.md'
                    },
                    {
                        text: '群组相关功能',
                        link: '/features/group.md'
                    },
                    {
                        text: '消息相关功能',
                        link: '/features/message.md'
                    }
                ]
            },
            {
                text: '服务端指南',
                collapsed: true,
                items: [
                    {
                        text: '部署',
                        items: [
                            {
                                text: '搭建与启动',
                                link: '/for-developers/getting-started.md'
                            },
                            {
                                text: '发布',
                                link: '/for-developers/distribution.md'
                            },
                            {
                                text: '配置参数',
                                link: '/for-developers/config.md'
                            }
                        ]
                    },
                    {
                        text: '设计',
                        items: [
                            {
                                text: '架构设计',
                                link: '/for-developers/architecture.md'
                            },
                            {
                                text: '集合结构设计',
                                link: '/for-developers/schema.md'
                            },
                            {
                                text: '状态感知',
                                link: '/for-developers/status-aware.md'
                            }
                        ]
                    },
                    {
                        text: '开发',
                        items: [
                            {
                                text: '关于二次开发',
                                link: '/for-developers/redevelopment.md'
                            },
                            {
                                text: '开发基本规约',
                                link: '/for-developers/rules.md'
                            },
                            {
                                text: '自定义插件',
                                link: '/for-developers/plugin.md'
                            },
                            {
                                text: '源码',
                                link: '/for-developers/code.md'
                            },
                            {
                                text: '测试',
                                link: '/for-developers/testing.md'
                            }
                            // TODO: Config Development
                        ]
                    },
                    {
                        text: '主要模块',
                        items: [
                            {
                                text: '集群的设计与实现',
                                link: '/for-developers/cluster.md'
                            },
                            {
                                text: '系统资源管理',
                                link: '/for-developers/system-resource-management.md'
                            },
                            {
                                text: '可观测性体系',
                                link: '/for-developers/observability.md'
                            },
                            {
                                text: '安全',
                                link: '/for-developers/security.md'
                            },
                            {
                                text: '存储服务',
                                link: '/for-developers/storage.md'
                            },
                            {
                                text: '聊天机器人',
                                link: '/for-developers/chatbot.md'
                            },
                            {
                                text: '敏感词过滤',
                                link: '/for-developers/anti-spam.md'
                            },
                            {
                                text: '数据分析',
                                link: '/for-developers/data-analytics.md'
                            }
                        ]
                    }
                ]
            },
            {
                text: '客户端指南',
                collapsed: true,
                items: [
                    {
                        text: 'Quick Start',
                        link: '/for-developers/client-quick-start.md'
                    },
                    {
                        text: '版本要求',
                        link: '/for-developers/client-requirements.md'
                    },
                    {
                        text: '接口',
                        link: '/for-developers/client-api.md'
                    },
                    {
                        text: '会话的生命周期',
                        link: '/for-developers/client-session.md'
                    },
                    {
                        text: '度量数据',
                        link: '/for-developers/client-metrics.md'
                    },
                    {
                        text: '身份与访问管理',
                        link: '/for-developers/client-identity-access-management.md'
                    },
                    {
                        text: '与服务端通信时使用的数据格式',
                        link: '/for-developers/client-server-protocol.md'
                    },
                    {
                        text: 'turms-client-js共享上下文',
                        link: '/for-developers/client-turms-client-js.md'
                    }
                ]
            },
            {
                text: 'turms-admin',
                link: '/for-developers/turms-admin.md'
            },
            {
                text: '手册',
                collapsed: true,
                items: [
                    {
                        text: '管理员API接口',
                        link: '/for-developers/admin-api.md'
                    },
                    {
                        text: '状态码',
                        link: '/for-developers/status-code.md'
                    }
                ]
            }
        ]
    }
});
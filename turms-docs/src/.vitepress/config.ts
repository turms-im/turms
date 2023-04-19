import { defineConfig } from 'vitepress';
import mermaidPlugin from './plugins/mermaid/mermaid-plugin';

export default defineConfig({
    title: 'Turms Documentation',
    base: '/docs/',
    markdown: {
        config: (md) => {
            md.use(mermaidPlugin);
        }
    },
    ignoreDeadLinks: [
        /^https?:\/\/localhost/,
    ],
    locales: {
        root: {
            label: 'English',
            lang: 'en',
            themeConfig: {
                sidebar: [
                    {
                        text: 'Introduction',
                        link: '/index.md',
                    },
                    {
                        text: 'Community FAQ',
                        link: '/community/index.md',
                    },
                    {
                        text: 'Business Features',
                        collapsed: true,
                        items: [
                            {
                                text: 'Business Features',
                                link: '/feature/index.md'
                            },
                            {
                                text: 'Simultaneous Login',
                                link: '/feature/simultaneous-login.md'
                            },
                            {
                                text: 'User-related Features',
                                link: '/feature/user.md'
                            },
                            {
                                text: 'Group-related Features',
                                link: '/feature/group.md'
                            },
                            {
                                text: 'Message-related Features',
                                link: '/feature/message.md'
                            }
                        ]
                    },
                    {
                        text: 'Design',
                        collapsed: true,
                        items: [
                            {
                                text: 'Architecture Design',
                                link: '/design/architecture.md'
                            },
                            {
                                text: 'Collection Schema Design',
                                link: '/design/schema.md'
                            },
                            {
                                text: 'Status Awareness',
                                link: '/design/status-aware.md'
                            }
                        ]
                    },
                    {
                        text: 'Server Guide',
                        collapsed: true,
                        items: [
                            {
                                text: 'Deployment',
                                items: [
                                    {
                                        text: 'Build and Start',
                                        link: '/server/deployment/getting-started.md'
                                    },
                                    {
                                        text: 'Distribution',
                                        link: '/server/deployment/distribution.md'
                                    },
                                    {
                                        text: 'Configuration',
                                        link: '/server/deployment/config.md'
                                    }
                                ]
                            },
                            {
                                text: 'Development',
                                items: [
                                    {
                                        text: 'About Secondary Development',
                                        link: '/server/development/redevelopment.md'
                                    },
                                    {
                                        text: 'Basic Development Rules',
                                        link: '/server/development/rules.md'
                                    },
                                    {
                                        text: 'Custom Plugins',
                                        link: '/server/development/plugin.md'
                                    },
                                    {
                                        text: 'Source Code',
                                        link: '/server/development/code.md'
                                    },
                                    {
                                        text: 'Testing',
                                        link: '/server/development/testing.md'
                                    }
                                    // TODO: Config Development
                                ]
                            },
                            {
                                text: 'Main Modules',
                                items: [
                                    {
                                        text: 'Identity and Access Management',
                                        link: '/server/module/identity-access-management.md'
                                    },
                                    {
                                        text: 'Cluster Design and Implementation',
                                        link: '/server/module/cluster.md'
                                    },
                                    {
                                        text: 'System Resource Management',
                                        link: '/server/module/system-resource-management.md'
                                    },
                                    {
                                        text: 'Observability',
                                        link: '/server/module/observability.md'
                                    },
                                    {
                                        text: 'Security',
                                        link: '/server/module/security.md'
                                    },
                                    {
                                        text: 'Storage Service',
                                        link: '/server/module/storage.md'
                                    },
                                    {
                                        text: 'Chatbot',
                                        link: '/server/module/chatbot.md'
                                    },
                                    {
                                        text: 'Content Moderation',
                                        link: '/server/module/anti-spam.md'
                                    },
                                    {
                                        text: 'Data Analysis',
                                        link: '/server/module/data-analytics.md'
                                    }
                                ]
                            }
                        ]
                    },
                    {
                        text: 'Client Guide',
                        collapsed: true,
                        items: [
                            {
                                text: 'Quick Start',
                                link: '/client/quick-start.md'
                            },
                            {
                                text: 'Version Requirements',
                                link: '/client/requirements.md'
                            },
                            {
                                text: 'API',
                                link: '/client/api.md'
                            },
                            {
                                text: 'Session Lifecycle',
                                link: '/client/session.md'
                            },
                            {
                                text: 'Metrics',
                                link: '/client/metrics.md'
                            },
                            {
                                text: 'Communication Protocol Used Between Client and Server',
                                link: '/client/communication-protocol.md'
                            },
                            {
                                text: 'turms-client-js Shared Context',
                                link: '/client/turms-client-js.md'
                            }
                        ]
                    },
                    {
                        text: 'turms-admin',
                        link: '/turms-admin.md'
                    },
                    {
                        text: 'Reference',
                        collapsed: true,
                        items: [
                            {
                                text: 'Admin API',
                                link: '/reference/admin-api.md'
                            },
                            {
                                text: 'Status Code',
                                link: '/reference/status-code.md'
                            }
                        ]
                    }
                ]
            }
        },
        'zh-CN': {
            label: '简体中文',
            lang: 'zh-CN',
            themeConfig: {
                lastUpdatedText: '上次更新',
                outline: {
                    label: '当前页'
                },
                docFooter: {
                    prev: '上一页',
                    next: '下一页'
                },
                sidebar: [
                    {
                        text: '介绍',
                        link: '/zh-CN/index.md',
                    },
                    {
                        text: '社区常见问题',
                        link: '/zh-CN/community/index.md',
                    },
                    {
                        text: '业务功能',
                        collapsed: true,
                        items: [
                            {
                                text: '业务功能介绍',
                                link: '/zh-CN/feature/index.md'
                            },
                            {
                                text: '多端登录',
                                link: '/zh-CN/feature/simultaneous-login.md'
                            },
                            {
                                text: '用户相关功能',
                                link: '/zh-CN/feature/user.md'
                            },
                            {
                                text: '群组相关功能',
                                link: '/zh-CN/feature/group.md'
                            },
                            {
                                text: '消息相关功能',
                                link: '/zh-CN/feature/message.md'
                            }
                        ]
                    },
                    {
                        text: '设计',
                        collapsed: true,
                        items: [
                            {
                                text: '架构设计',
                                link: '/zh-CN/design/architecture.md'
                            },
                            {
                                text: '集合结构设计',
                                link: '/zh-CN/design/schema.md'
                            },
                            {
                                text: '状态感知',
                                link: '/zh-CN/design/status-aware.md'
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
                                        link: '/zh-CN/server/deployment/getting-started.md'
                                    },
                                    {
                                        text: '发布',
                                        link: '/zh-CN/server/deployment/distribution.md'
                                    },
                                    {
                                        text: '配置参数',
                                        link: '/zh-CN/server/deployment/config.md'
                                    }
                                ]
                            },
                            {
                                text: '开发',
                                items: [
                                    {
                                        text: '关于二次开发',
                                        link: '/zh-CN/server/development/redevelopment.md'
                                    },
                                    {
                                        text: '基本开发规约',
                                        link: '/zh-CN/server/development/rules.md'
                                    },
                                    {
                                        text: '自定义插件',
                                        link: '/zh-CN/server/development/plugin.md'
                                    },
                                    {
                                        text: '源码',
                                        link: '/zh-CN/server/development/code.md'
                                    },
                                    {
                                        text: '测试',
                                        link: '/zh-CN/server/development/testing.md'
                                    }
                                    // TODO: Config Development
                                ]
                            },
                            {
                                text: '主要模块',
                                items: [
                                    {
                                        text: '身份与访问管理',
                                        link: '/zh-CN/server/module/identity-access-management.md'
                                    },
                                    {
                                        text: '集群的设计与实现',
                                        link: '/zh-CN/server/module/cluster.md'
                                    },
                                    {
                                        text: '系统资源管理',
                                        link: '/zh-CN/server/module/system-resource-management.md'
                                    },
                                    {
                                        text: '可观测性体系',
                                        link: '/zh-CN/server/module/observability.md'
                                    },
                                    {
                                        text: '安全',
                                        link: '/zh-CN/server/module/security.md'
                                    },
                                    {
                                        text: '存储服务',
                                        link: '/zh-CN/server/module/storage.md'
                                    },
                                    {
                                        text: '聊天机器人',
                                        link: '/zh-CN/server/module/chatbot.md'
                                    },
                                    {
                                        text: '敏感词过滤',
                                        link: '/zh-CN/server/module/anti-spam.md'
                                    },
                                    {
                                        text: '数据分析',
                                        link: '/zh-CN/server/module/data-analytics.md'
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
                                link: '/zh-CN/client/quick-start.md'
                            },
                            {
                                text: '版本要求',
                                link: '/zh-CN/client/requirements.md'
                            },
                            {
                                text: '接口',
                                link: '/zh-CN/client/api.md'
                            },
                            {
                                text: '会话的生命周期',
                                link: '/zh-CN/client/session.md'
                            },
                            {
                                text: '度量数据',
                                link: '/zh-CN/client/metrics.md'
                            },
                            {
                                text: '与服务端通信时使用的数据格式',
                                link: '/zh-CN/client/communication-protocol.md'
                            },
                            {
                                text: 'turms-client-js共享上下文',
                                link: '/zh-CN/client/turms-client-js.md'
                            }
                        ]
                    },
                    {
                        text: 'turms-admin',
                        link: '/zh-CN/turms-admin.md'
                    },
                    {
                        text: '手册',
                        collapsed: true,
                        items: [
                            {
                                text: '管理员API接口',
                                link: '/zh-CN/reference/admin-api.md'
                            },
                            {
                                text: '状态码',
                                link: '/zh-CN/reference/status-code.md'
                            }
                        ]
                    }
                ]
            }
        }
    },
    themeConfig: {
        logo: 'https://raw.githubusercontent.com/turms-im/assets/master/logo/pegion.svg',
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
                    'zh-CN': {
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
            }
        }
    }
});
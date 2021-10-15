import { defineUserConfig } from 'vuepress'
import type { DefaultThemeOptions } from 'vuepress'

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
                children: [
                    {
                        text: '介绍',
                        link: '/intro/'
                    },
                    '/intro/features.md',
                    '/intro/redevelopment.md'
                ]
            },
            {
                text: '业务功能',
                children: [
                    '/features/',
                    '/features/simultaneous-login.md',
                    '/features/user.md',
                    '/features/group.md',
                    '/features/message.md'
                ]
            },
            {
                text: '开发者文档',
                children: [
                    // Practical knowledge
                    '/for-developers/getting-started.md',
                    '/for-developers/distribution.md',
                    '/for-developers/config.md',
                    // Manual
                    '/for-developers/admin-api.md',
                    '/for-developers/client-api.md',
                    '/for-developers/status-code.md',
                    // Redevelopment
                    '/for-developers/custom-plugin.md',
                    // Theory
                    '/for-developers/architecture.md',
                    '/for-developers/schema.md',
                    '/for-developers/observability.md',
                    '/for-developers/security.md',
                    '/for-developers/anti-spam.md',
                    '/for-developers/status-aware.md',
                    '/for-developers/data-analytics.md'
                ]
            }
        ]
    }
});
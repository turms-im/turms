import { defineUserConfig } from 'vuepress'
import type { DefaultThemeOptions } from 'vuepress'

export default defineUserConfig<DefaultThemeOptions>({
    title: 'Turms Documentation',
    base: '/docs/',
    themeConfig: {
        repo: 'https://github.com/turms-im/docs',
        contributors: false,
        lastUpdatedText: '上次更新',
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
                    '/for-developers/getting-started.md',
                    '/for-developers/distribution.md',
                    '/for-developers/architecture.md',
                    '/for-developers/config.md',
                    '/for-developers/admin-api.md',
                    '/for-developers/client-api.md',
                    '/for-developers/status-code.md',
                    '/for-developers/status-aware.md',
                    '/for-developers/custom-plugin.md',
                    '/for-developers/schema.md',
                    '/for-developers/data-analytics.md'
                ]
            }
        ]
    }
});
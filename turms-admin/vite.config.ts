import { fileURLToPath, URL } from 'node:url';
import autoprefixer from 'autoprefixer';
import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import Markdown from 'vite-plugin-vue-markdown';
import Components from 'unplugin-vue-components/vite';
import { AntDesignVueResolver } from 'unplugin-vue-components/resolvers';
import { visualizer } from 'rollup-plugin-visualizer';

const isReportMode = process.env.REPORT === 'true';

export default defineConfig(({mode }) => ({
    root: './client',
    build: {
        outDir: '../dist',
        rollupOptions: {
            input: {
                app: fileURLToPath(new URL('./client/index.html', import.meta.url))
            }
        }
    },
    css: {
        postcss: {
            plugins: [
                autoprefixer
            ]
        }
    },
    esbuild: {
        drop: mode === 'production' ? ['console', 'debugger'] : []
    },
    define: {
        'process.env': {
            NODE_ENV: process.env.NODE_ENV
        }
    },
    plugins: [
        vue({
            include: [/\.vue$/, /\.md$/]
        }),
        Markdown({
            markdownItOptions: {
                html: true,
                typographer: true
            }
        }),
        Components({
            resolvers: [AntDesignVueResolver()]
        }),
        isReportMode
            ? visualizer({
                filename: './build/.cache/stats.html',
                open: true
            })
            : null
    ]
}));
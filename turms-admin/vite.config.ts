import { fileURLToPath, URL } from 'node:url';
import autoprefixer from 'autoprefixer';
import { defineConfig, splitVendorChunkPlugin } from 'vite';
import vue from '@vitejs/plugin-vue';
import Components from 'unplugin-vue-components/vite';
import { AntDesignVueResolver } from 'unplugin-vue-components/resolvers';
import Markdown from 'unplugin-vue-markdown/vite';
import { visualizer } from 'rollup-plugin-visualizer';

const isReportMode = process.env.REPORT === 'true';

export default defineConfig(({mode }) => ({
    root: './ui',
    build: {
        outDir: '../dist',
        rollupOptions: {
            input: {
                app: fileURLToPath(new URL('./ui/index.html', import.meta.url))
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
            resolvers: [AntDesignVueResolver({
                importStyle: false
            })]
        }),
        // We don't split vendor into more chunks because
        // it will cause chunks to run in wrong order.
        splitVendorChunkPlugin(),
        isReportMode
            ? visualizer({
                filename: './build/.cache/stats.html',
                open: true
            })
            : null
    ]
}));
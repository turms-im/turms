import fs from 'fs';
import path from 'path';
import { createRequire } from 'module';
import { defineConfig, OutputOptions, RollupOptions } from 'rollup';
import babel from '@rollup/plugin-babel';
import commonjs from '@rollup/plugin-commonjs';
import filesize from 'rollup-plugin-filesize';
import json from '@rollup/plugin-json';
import { nodeResolve as resolve } from '@rollup/plugin-node-resolve';
import terser from '@rollup/plugin-terser';
import typescript from '@rollup/plugin-typescript';
import { visualizer } from 'rollup-plugin-visualizer';
import dts from 'rollup-plugin-dts';

// @ts-ignore
const require = createRequire(import.meta.url);
const pkg = require('./package.json');
// --experimental-json-modules
// import pkg from './package.json' assert { type: 'json' };

const input = 'src/turms-client.ts';

function getConfig({
    output,
    useBabel,
    useTerser
}: {
    output: OutputOptions,
    useBabel?: boolean,
    useTerser?: boolean
}): RollupOptions {
    const ts = typescript({
        sourceMap: false
    });
    const babelPlugin = useBabel
        ? babel({
            exclude: 'node_modules/**',
            babelHelpers: 'bundled',
            presets: [
                [
                    '@babel/preset-env',
                    {
                        'modules': false,
                        'useBuiltIns': 'usage',
                        'corejs': 3
                    }
                ],
                '@babel/preset-typescript'
            ],
            extensions: ['js', 'ts']
        })
        : null;
    const terserPlugin = useTerser ? terser() : null;
    return {
        input,
        output: [output],
        plugins: [
            json(),
            resolve({ extensions: ['.js', '.ts'] }),
            commonjs(),
            ts,
            babelPlugin,
            terserPlugin,
            filesize(),
            visualizer({
                gzipSize: true,
                filename: `stats/${path.basename(output.file)}.size-analysis.html`
            })
        ]
    };
}

const config: RollupOptions[] = [
    // CommonJS for Node
    getConfig({
        output: {
            file: pkg.main,
            format: 'cjs',
            sourcemap: true
        }
    }),
    // ES module for bundlers
    getConfig({
        output: {
            file: pkg.module,
            format: 'esm',
            sourcemap: true
        }
    }),
    // IIFE for browsers
    getConfig({
        output: {
            file: 'dist/turms-client.iife.js',
            format: 'iife',
            name: 'TurmsClient',
            sourcemap: true
        },
        useBabel: true
    }),
    getConfig({
        output: {
            file: 'dist/turms-client.iife.min.js',
            format: 'iife',
            name: 'TurmsClient',
            sourcemap: true
        },
        useBabel: true,
        useTerser: true
    }),
    // Bundle definition files
    {
        input,
        output: {
            file: pkg.types,
            format: 'esm'
        },
        plugins: [dts()]
    }
];

if (fs.existsSync('dist')) {
    fs.rmSync('dist', {
        recursive: true
    });
}

export default defineConfig(config);
const fs = require('fs');
const path = require('path');

const commonjs = require('@rollup/plugin-commonjs');
const filesize = require('rollup-plugin-filesize');
const json = require('@rollup/plugin-json');
const resolve = require('@rollup/plugin-node-resolve').nodeResolve;
const terser = require('rollup-plugin-terser').terser;
const ts = require('rollup-plugin-ts');
const visualizer = require('rollup-plugin-visualizer').visualizer;

const pkg = require('./package.json');

function getConfig({output, useBabel, withDeclaration}) {
    const transpile = useBabel ?
        ts({
            transpiler: 'babel'
        })
        : ts({
            // disable browserslist, or the Typescript target will be determined
            // by Browserslist instead of the target property of tsconfig.json
            browserslist: false,
            tsconfig: config => ({ ...config, declaration: withDeclaration, declarationMap: withDeclaration }),
            hook: {
                outputPath: ((p, kind) => {
                    if (kind === 'declaration') {
                        return 'dist/turms-client.d.ts';
                    } else if (kind === 'declarationMap') {
                        return 'dist/turms-client.d.ts.map';
                    }
                    return p;
                })
            }
        });
    const fileName = path.basename(output.file);
    return {
        input: 'src/turms-client.ts',
        output: [output],
        plugins: [
            json(),
            resolve({extensions: ['.js', '.ts']}),
            commonjs(),
            transpile,
            filesize(),
            visualizer({
                gzipSize: true,
                filename: `stats/${fileName}.size-analysis.html`
            })
        ]
    };
}

if (fs.existsSync('dist')) {
    fs.rmSync('dist', {recursive: true});
}

module.exports = [
    // CommonJS for Node
    {
        output: {
            file: pkg.main,
            format: 'cjs'
        },
        withDeclaration: true
    },
    // ES module for bundlers
    {
        output: {
            file: pkg.module,
            format: 'esm'
        }
    },
    // IIFE for browsers
    {
        output: {
            file: 'dist/turms-client.iife.js',
            format: 'iife',
            name: 'TurmsClient'
        },
        useBabel: true
    },
    {
        output: {
            file: 'dist/turms-client.iife.min.js',
            format: 'iife',
            name: 'TurmsClient',
            plugins: [terser()]
        },
        useBabel: true
    }
].map(getConfig);
const path = require('path');
const webpack = require('webpack');

const NODE_ENV = process.env.NODE_ENV;
const CLIENT_DIR = path.resolve(__dirname, 'client');
const IS_PROD = NODE_ENV === 'production';

module.exports = {
    css: {
        loaderOptions: {
            less: {
                lessOptions: {
                    javascriptEnabled: true
                }
            }
        }
    },
    configureWebpack: config => {
        config.entry = {
            app: [`${CLIENT_DIR}/src/main.js`]
        };
        const processProvidePlugin = new webpack.ProvidePlugin({
            Buffer: ['buffer', 'Buffer'],
            process: 'process/browser'
        });
        config.plugins.push(processProvidePlugin);
        if (IS_PROD) {
            const contextReplacementPlugin = new webpack.ContextReplacementPlugin(
                /moment[/\\]locale$/,
                /en|zh-cn/
            );
            config.plugins.push(contextReplacementPlugin);
        } else {
            config.devtool = 'source-map';
        }
        config.module.rules.push({
            test: /\.md$/i,
            use: [
                {
                    loader: 'vue-loader'
                },
                {
                    loader: require.resolve(`${CLIENT_DIR}/src/loaders/markdown-loader`)
                }
            ]
        });
        config.resolve = {
            ...config.resolve,
            alias: {
                process: 'process/browser'
            },
            fallback: {
                crypto: false,
                fs: false,
                stream: require.resolve('stream-browserify'),
                util: false
            }
        };
    },
    chainWebpack: config => {
        config
            .plugin('html')
            .tap(args => {
                args[0].template = `${CLIENT_DIR}/public/index.html`;
                args[0].favicon = `${CLIENT_DIR}/public/favicon.svg`;
                return args;
            });
    }
};

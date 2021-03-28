const path = require('path');
const NODE_ENV = process.env.NODE_ENV;
const webpack = require('webpack');
const CLIENT_DIR = path.resolve(__dirname, 'client');
const isProd = NODE_ENV === 'production';

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
        // config.node = false; // To avoid importing node environment by third party dependencies
        config.entry = {
            app: [`${CLIENT_DIR}/src/main.js`]
        };
        if (isProd) {
            config.plugins.push(
                new webpack.ContextReplacementPlugin(
                    /moment[/\\]locale$/,
                    /en|zh-cn/
                ));
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
    },
    chainWebpack: config => {
        config
            .plugin('html')
            .tap(args => {
                args[0].template = `${CLIENT_DIR}/public/index.html`;
                args[0].favicon = `${CLIENT_DIR}/public/favicon.ico`;
                return args;
            });
    }
};

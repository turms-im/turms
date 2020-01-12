const path = require('path');
const TerserPlugin = require('terser-webpack-plugin');
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;

module.exports = (env, argv) => {
    const base = {
        entry: {
            'turms-client': './src/turms-client.ts',
            'turms-client-min': './src/turms-client.ts'
        },
        devtool: 'inline-source-map',
        module: {
            rules: [
                {
                    test: /\.ts$/,
                    // Do not remove 'ts-loader' because some syntax are not supported by babel-loader
                    use: ['babel-loader', 'ts-loader'],
                    exclude: /node_modules/
                }
            ]
        },
        optimization: {
            minimize: true,
            minimizer: [
                new TerserPlugin({
                    terserOptions: {
                        output: {
                            comments: false,
                        }
                    },
                    test: /min\.js$/,
                    sourceMap: false,
                    extractComments: false
                }),
            ],
        },
        resolve: {
            extensions: ['.ts', '.js']
        },
        output: {
            path: path.resolve(__dirname, 'dist'),
            filename: "[name].js"
        }
    };
    base.plugins = [];
    if (argv.report) {
        base.plugins.push(new BundleAnalyzerPlugin());
    }
    return base;
};

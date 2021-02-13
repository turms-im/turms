module.exports = {
    presets: [
        '@vue/app'
    ],
    plugins: [
        'lodash',
        [
            'import', {
                libraryName: 'ant-design-vue',
                libraryDirectory: 'es',
                style: true
            }
        ]
    ]
};
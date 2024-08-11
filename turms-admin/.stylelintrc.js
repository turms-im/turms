export default {
    extends: [
        'stylelint-config-standard',
        'stylelint-config-recess-order',
        'stylelint-config-standard-scss',
        'stylelint-config-recommended-vue'
    ],
    plugins: [
        "@stylistic/stylelint-plugin"
    ],
    rules: {
        // BEM
        'selector-class-pattern': '^([a-z][a-z0-9]*)|(--|-|__|\.[a-z0-9]+).*$',
        '@stylistic/indentation': 4
    }
};
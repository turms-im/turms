module.exports = {
    extends: [
        'stylelint-config-standard',
        'stylelint-config-recess-order',
        'stylelint-config-standard-scss',
        'stylelint-config-recommended-vue'
    ],
    rules: {
        indentation: [4],
        // BEM
        'selector-class-pattern': '^([a-z][a-z0-9]*)|(--|-|__|\.[a-z0-9]+).*$'
    }
};
// eslint-disable-next-line no-undef
module.exports = {
    root: true,
    env: {
        browser: true,
        node: true
    },
    parserOptions: {
        ecmaVersion: 2020
    },
    extends: [
        'eslint:recommended',
        'plugin:cypress/recommended',
        'plugin:import/recommended',
        'plugin:vue/recommended',
        '@vue/eslint-config-typescript'
    ],
    overrides: [
        {
            files: [
                'cypress/e2e/**/*.{cy,spec}.{js,ts,jsx,tsx}'
            ],
            extends: [
                'plugin:cypress/recommended'
            ]
        }
    ],
    rules: {
        'array-element-newline': ['error', 'consistent'],
        'comma-dangle': ['error', 'never'],
        'import/order': 'error',
        'import/no-unresolved': 'off',
        'indent': ['error', 4, {'SwitchCase': 1}],
        'no-var': ['error'],
        'prefer-const': ['error'],
        'quotes': ['error', 'single', { 'avoidEscape': true }],
        'semi': ['error', 'always'],
        'vue/custom-event-name-casing': 0,
        'vue/component-definition-name-casing': ['error', 'kebab-case'],
        'vue/html-indent': ['error', 4, {
            'attribute': 1,
            'baseIndent': 1,
            'closeBracket': 0,
            'alignAttributesVertically': false,
            'ignores': []
        }],
        'vue/max-attributes-per-line': ['error', {
            'singleline': 1,
            'multiline': 1
        }],
        'vue/html-closing-bracket-spacing': ['error'],
        'vue/multi-word-component-names': 0,
        'vue/no-static-inline-styles': ['error'],
        'vue/no-v-model-argument': 0,
        'vue/no-mutating-props': 0,
        'vue/no-multiple-template-root': 0
    }
};

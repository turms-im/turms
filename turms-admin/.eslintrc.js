module.exports = {
    root: true,
    env: {
        browser: true,
        node: true
    },
    parserOptions: {
        parser: '@babel/eslint-parser'
    },
    extends: [
        'eslint:recommended',
        'plugin:cypress/recommended',
        'plugin:vue/recommended'
    ],
    rules: {
        'quotes': ['error', 'single', { 'avoidEscape': true }],
        'semi': ['error', 'always'],
        'indent': ['error', 4, {'SwitchCase': 1}],
        'comma-dangle': ['error', 'never'],
        'prefer-const': ['error'],
        'no-var': ['error'],
        'array-element-newline': ['error', 'consistent'],
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
        'vue/name-property-casing': ['error', 'kebab-case'],
        'vue/html-closing-bracket-spacing': ['error'],
        'vue/multi-word-component-names': 0,
        'vue/no-static-inline-styles': ['error'],
        'vue/no-v-model-argument': 0,
        'vue/no-mutating-props': 0,
        'vue/no-multiple-template-root': 0
    }
};

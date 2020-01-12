module.exports = {
    "root": true,
    "env": {
        "jest": true,
    },
    "parser": "@typescript-eslint/parser",
    "parserOptions": {
        "ecmaVersion": 2019,
        "sourceType": "module",
        "ecmaFeatures": {
            "jsx": false
        },
        "project": "tsconfig.json",
    },
    "extends": [
        "eslint:recommended",
        "plugin:@typescript-eslint/eslint-recommended",
        "plugin:@typescript-eslint/recommended"
    ],
    "rules": {
        "@typescript-eslint/ban-ts-ignore": "off",
        "no-trailing-spaces": "error"
    }
};

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
        "project": "tsconfig.json"
    },
    "extends": [
        "eslint:recommended",
        "plugin:@typescript-eslint/eslint-recommended",
        "plugin:@typescript-eslint/recommended"
    ],
    "rules": {
        "@typescript-eslint/ban-ts-ignore": "off",
        "@typescript-eslint/no-namespace": "off",
        "@typescript-eslint/explicit-function-return-type": "error",
        "no-trailing-spaces": "error"
    }
};

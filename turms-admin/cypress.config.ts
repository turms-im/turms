// @ts-ignore
const { defineConfig } = require('cypress');

module.exports = defineConfig({
    env: {
        baseUrl: 'http://localhost:8510',
        turms: require('./client/src/configs/resources.json')
    },
    e2e: {
        specPattern: 'cypress/e2e/**/*.{cy,spec}.{js,ts}',
        baseUrl: 'http://localhost:6510'
    }
});
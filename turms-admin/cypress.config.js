const { defineConfig } = require('cypress');

module.exports = defineConfig({
    env: {
        baseUrl: 'http://localhost:8510',
        turms: require('./client/src/configs/resources.json')
    },
    e2e: {
        baseUrl: 'http://localhost:6510'
    }
});
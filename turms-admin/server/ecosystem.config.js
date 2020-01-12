// https://pm2.keymetrics.io/docs/usage/application-declaration/
module.exports = {
    apps : [{
        name: 'turms-admin',
        script: 'server/src/app.js',
        instances: 1,
        autorestart: true,
        watch: false,
        max_memory_restart: '1G',
        env: {
            NODE_ENV: 'development'
        },
        env_production: {
            NODE_ENV: 'production'
        }
    }]
};

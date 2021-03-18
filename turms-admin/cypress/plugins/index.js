/**
 * @type {Cypress.PluginConfig}
 */
module.exports = (on, config) => {
    Object.assign(config.env, {
        turms: require('../../client/src/configs/resources.json')
    });
    return config;
};
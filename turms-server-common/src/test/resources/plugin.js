class MyBaseExtension extends TurmsExtension {
    testBaseMethod() {
    }
}

class MyExtension extends MyBaseExtension {
    getExtensionPoints() {
        return ['unit.im.turms.server.common.infra.plugin.MyExtensionPointForJs'];
    }

    @extensionPointMethod
    testBool() {
        return true;
    }

    @extensionPointMethod
    async testNotification(builders) {
        const builder = builders.get(0);
        const notification = builder.setCode(123)
            .setReason('reason')
            .build();
        const List = Java.type('java.util.List');
        return List.of(notification);
    }

    @extensionPointMethod
    async testFetch() {
        const response = await fetch('https://api.github.com/repos/turms-im/turms');
        return JSON.parse(response.data)['full_name'];
    }

    @extensionPointMethod
    testLog() {
        turms.log.info('A log from plugin.js');
    }

    @extensionPointMethod
    testError() {
        throw new Error('An error from plugin.js');
    }

    // testNotImplemented()
}

class MyPlugin extends TurmsPlugin {
    getDescriptor() {
        return {
            id: 'com.mydomain.myplugin',
            version: '0.0.1',
            provider: 'com.mydomain',
            license: 'MIT',
            description: ''
        };
    }

    getExtensions() {
        return [MyExtension];
    }
}
export default MyPlugin;
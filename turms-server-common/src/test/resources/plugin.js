function getPluginDescriptor() {
    return {
        id: 'com.mydomain.MyClientRequestHandler',
        version: '0.0.1',
        provider: 'com.mydomain',
        license: 'MIT',
        description: ''
    };
}

class MyStorageServiceProvider extends TurmsExtension {
    getExtensionPoints() {
        return ['im.turms.plugin.MyExtensionPoint'];
    }

    testBool() {
        return true;
    }

    async testNotification(builders) {
        const builder = builders.get(0);
        const notification = builder.setCode(123)
            .setReason('reason')
            .build();
        const List = Java.type('java.util.List');
        return List.of(notification);
    }
}
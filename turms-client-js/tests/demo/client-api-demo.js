// import TurmsClient from "../../dist/turms-client";
const TurmsClient = require("../../dist/turms-client");

// Initialize client
const client = new TurmsClient(); // new TurmsClient('ws://any-turms-server.com');

// Listen to the close event
client.userService.addOnOfflineListener(sessionCloseInfo => {
    console.info(`onOffline: ${JSON.stringify(sessionCloseInfo)}`);
});

// Listen to inbound notifications
client.notificationService.addNotificationListener(notification => {
    console.info(`Notification: Receive a notification from other users or server: ${JSON.stringify(notification)}`);
});

// Listen to inbound messages
client.messageService.addMessageListener(message => {
    console.info(`Message: Receive a message from other users or server: ${JSON.stringify(message)}`);
});

client.userService.login({
    userId: '1',
    password: '123'
})
    .then(() => {
        client.userService.queryNearbyUsers({
            latitude: 139.667651,
            longitude: 35.792657,
            maxCount: 10,
            maxDistance: 1000
        })
            .then(response => {
                console.log(`user ids: ${response.data}`);
            });
        client.messageService.sendMessage({
            isGroupMessage: false,
            targetId: '1',
            deliveryDate: new Date(),
            text: 'Hello Turms',
            burnAfter: 30
        })
            .then(response => {
                console.log(`message ${response.data} has been sent`);
            });
        client.groupService.createGroup({
            name: 'Turms Developers Group',
            intro: 'This is a group for the developers who are interested in Turms',
            announcement: 'nope'
        })
            .then(response => {
                console.log(`group ${response.data} has been created`);
            });
    })
    .catch(reason => {
        console.error(reason);
    });
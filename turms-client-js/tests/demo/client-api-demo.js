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

client.userService.login('1', '123')
    .then(() => {
        client.userService.queryNearbyUsers(
            139.667651,
            35.792657,
            100,
            10)
            .then(response => {
                console.log(`user ids: ${response.data}`);
            });
        client.messageService.sendMessage(
            false,
            '1',
            new Date(),
            'Hello Turms',
            null,
            30)
            .then(response => {
                console.log(`message ${response.data} has been sent`);
            });
        client.groupService.createGroup(
            'Turms Developers Group',
            'This is a group for the developers who are interested in Turms',
            'nope')
            .then(response => {
                console.log(`group ${response.data} has been created`);
            });
    })
    .catch(reason => {
        console.error(reason);
    });
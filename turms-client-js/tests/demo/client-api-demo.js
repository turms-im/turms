// import TurmsClient from "../../dist/turms-client";
const TurmsClient = require("../../dist/turms-client");

// Initialize client
const client = new TurmsClient(); // new TurmsClient('ws://any-turms-server.com');

// Listen to the close event
client.driver.onSessionDisconnected = disconnectInfo => {
    console.info(`onSessionDisconnected: ${JSON.stringify(disconnectInfo)}`);
};

// Listen to inbound notifications
client.notificationService.onNotification = (notification) => {
    console.info(`onNotification: Receive a notification from other users or server: ${JSON.stringify(notification)}`);
};

// Listen to inbound messages
client.messageService.onMessage = (message) => {
    console.info(`onMessage: Receive a message from other users or server: ${JSON.stringify(message)}`);
};

client.userService.login('1', '123')
    .then(() => {
        client.userService.queryUserIdsNearby(
            139.667651,
            35.792657,
            100,
            10)
            .then(ids => {
                console.log(`user ids: ${ids}`);
            });
        client.messageService.sendMessage(
            false,
            '1',
            new Date(),
            'Hello Turms',
            null,
            30)
            .then(id => {
                console.log(`message ${id} has been sent`);
            });
        client.groupService.createGroup(
            'Turms Developers Group',
            'This is a group for the developers who are interested in Turms',
            'nope')
            .then(id => {
                console.log(`group ${id} has been created`);
            });
    })
    .catch(reason => {
        console.error(reason);
    });
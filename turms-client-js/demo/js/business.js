const userOneNotificationContainer = document
    .querySelector("#user-one-notification-container");
const userTwoNotificationContainer = document
    .querySelector("#user-two-notification-container");
const ERROR_PREFIX = "error: ";

function appendContainer(element, text, isError) {
    element.value = element.value
        + (element.value ? "\n" : "")
        + (isError ? "✖ " : "✔ ")
        + new Date().toLocaleTimeString() + "\n"
        + (isError ? ERROR_PREFIX : '')
        + text;
    element.scrollTop = element.scrollHeight;
}

function beautify(object) {
    if (object instanceof Error) {
        object = {
            message: object.message
        }
    }
    return JSON.stringify(object, null, "\t");
}

function setupClient(container, client, userId, password, targetId) {
    client.userService.addOnOfflineListener(sessionCloseInfo => {
        appendContainer(container, `onOffline: ${beautify(sessionCloseInfo)}`);
    });
    client.notificationService.addNotificationListener(notification => {
        appendContainer(container, `Notification: Receive a notification from other users or server: ${beautify(notification)}`);
    });
    client.messageService.addMessageListener(message => {
        appendContainer(container, `Message: Receive a message from other users or server: ${beautify(message)}`);
    });
    client.userService.login({
        userId,
        password
    })
        .then(() => {
            appendContainer(container, `login: User ${userId} has logged in`);
            client.messageService.queryMessagesWithTotal({
                ids: ['1']
            })
                .then(res => appendContainer(container, `Offline messages: ${beautify(res.data)}`))
                .catch(error => appendContainer(container, `failed to query offline messages ${beautify(error)}`, true));
            const intervalId = setInterval(() => {
                if (client.driver.isConnected) {
                    client.messageService.sendMessage({
                        isGroupMessage: false,
                        targetId,
                        deliveryDate: new Date(),
                        text: "Hello Turms, My userId is " + userId,
                        burnAfter: 30
                    })
                        .then(res => appendContainer(container, `message ${res.data} has been sent`))
                        .catch(error => appendContainer(container, `failed to send message: ${beautify(error)}`, true));
                } else {
                    clearInterval(intervalId);
                }
            }, 2000);
            client.groupService.createGroup({
                name: 'Turms Developers Group',
                intro: 'This is a group for the developers who are interested in Turms',
                announcement: 'nope'
            })
                .then(res => appendContainer(container, `group ${res.data} has been created`))
                .catch(error => appendContainer(container, `failed to create group: ${beautify(error)}`, true));
        })
        .catch(reason => appendContainer(container, `failed to log in ${beautify(reason)}`, true));
}

function start() {
    const clientUserOne = new TurmsClient('ws://localhost:10510', 30 * 1000);
    const clientUserTwo = new TurmsClient('ws://localhost:10510', 30 * 1000);
    const USER_ONE_ID = '1';
    const USER_TWO_ID = '2';
    setupClient(userOneNotificationContainer, clientUserOne, USER_ONE_ID, '123', USER_TWO_ID);
    setupClient(userTwoNotificationContainer, clientUserTwo, USER_TWO_ID, '123', USER_ONE_ID);
}
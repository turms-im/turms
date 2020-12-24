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
    client.driver.onSessionDisconnected = disconnectInfo => {
        appendContainer(container, `onSessionDisconnected: ${beautify(disconnectInfo)}`);
    };
    client.notificationService.onNotification = notification => {
        appendContainer(container, `onNotification: Receive a notification from other users or server: ${beautify(notification)}`);
    };
    client.messageService.onMessage = message => {
        appendContainer(container, `onMessage: Receive a message from other users or server: ${beautify(message)}`);
    };
    client.userService.login(userId, password)
        .then(() => {
            appendContainer(container, 'login: User one has logged in');
            client.messageService.queryPendingMessagesWithTotal(1)
                .then(messages => appendContainer(container, `Offline messages: ${beautify(messages)}`))
                .catch(error => {
                    if (error && error.code === 2001) {
                        appendContainer(container, 'No offline message');
                    }
                });
            const intervalId = setInterval(() => {
                if (client.driver.isConnected()) {
                    client.messageService.sendMessage(
                        false,
                        targetId,
                        new Date(),
                        "Hello Turms, My userId is " + userId,
                        null,
                        30)
                        .then(id => appendContainer(container, `message ${id} has been sent`))
                        .catch(error => appendContainer(container, `failed to send message: ${beautify(error)}`, true));
                } else {
                    clearInterval(intervalId);
                }
            }, 2000);
            client.groupService.createGroup(
                'Turms Developers Group',
                'This is a group for the developers who are interested in Turms',
                'nope')
                .then(id => appendContainer(container, `group ${id} has been created`))
                .catch(error => appendContainer(container, `failed to create group: ${beautify(error)}`, true));
        })
        .catch(reason => appendContainer(container, `failed to log in ${beautify(reason)}`, true));
}

function start() {
    const clientUserOne = new TurmsClient('ws://localhost:9510', 30 * 1000);
    const clientUserTwo = new TurmsClient('ws://localhost:9510', 30 * 1000);
    const USER_ONE_ID = '1';
    const USER_TWO_ID = '2';
    setupClient(userOneNotificationContainer, clientUserOne, USER_ONE_ID, '123', USER_TWO_ID);
    setupClient(userTwoNotificationContainer, clientUserTwo, USER_TWO_ID, '123', USER_ONE_ID);
}
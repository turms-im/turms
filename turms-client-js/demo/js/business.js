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

function appendUserOneContainer(text, isError) {
    appendContainer(userOneNotificationContainer, text, isError);
}

function appendUserTwoContainer(text, isError) {
    appendContainer(userTwoNotificationContainer, text, isError);
}

function beautify(object) {
    if (object instanceof Error) {
        object = {
            message: object.message
        }
    }
    return JSON.stringify(object, null, "\t");
}

function start() {
    const clientUserOne = new TurmsClient('ws://localhost:9510', 30 * 1000);
    const clientUserTwo = new TurmsClient('ws://localhost:9510', 30 * 1000);
    const USER_ONE_ID = '1';
    const USER_TWO_ID = '2';
    clientUserOne.driver.onSessionDisconnected = disconnectInfo => {
        appendUserOneContainer(`onSessionDisconnected: ${beautify(disconnectInfo)}`);
    };
    clientUserOne.notificationService.onNotification = notification => {
        appendUserOneContainer('onNotification: Receive a notification from other users or server: ' + beautify(notification));
    };
    clientUserOne.messageService.onMessage = message => {
        appendUserOneContainer('onMessage: Receive a message from other users or server: ' + beautify(message));
    };
    clientUserTwo.driver.onClose = closeInfo => {
        appendUserTwoContainer(`onClose: ${beautify(closeInfo)}`);
    };
    clientUserTwo.notificationService.onNotification = (notification) => {
        appendUserTwoContainer('onNotification: Receive a notification from other users or server: ' + beautify(notification));
    };
    clientUserTwo.messageService.onMessage = message => {
        appendUserTwoContainer('onMessage: Receive a message from other users or server: ' + beautify(message));
    };
    return Promise.all([
        clientUserOne.userService.login(USER_ONE_ID, '123')
            .then(() => {
                appendUserOneContainer('login: User one has logged in');
                return Promise.resolve();
            })
            .catch(reason => appendUserOneContainer('Failed to log in ' + beautify(reason), true)),
        clientUserTwo.userService.login(USER_TWO_ID, '123')
            .then(() => {
                appendUserTwoContainer('login: User two has logged in');
                return Promise.resolve();
            })
            .catch(reason => appendUserTwoContainer('Failed to log in ' + beautify(reason), true))
    ])
        .then(() => {
                clientUserOne.messageService.queryPendingMessagesWithTotal(1)
                    .then(messages => {
                        appendUserOneContainer('Offline messages: ' + beautify(messages));
                    })
                    .catch(error => {
                        if (error && error.code === 2001) {
                            appendUserOneContainer('No offline message');
                        }
                    });
                const intervalOne = setInterval(() => {
                    if (clientUserOne.driver.isConnected()) {
                        clientUserOne.messageService.sendMessage(
                            false,
                            USER_TWO_ID,
                            new Date(),
                            "Hello Turms, My userId is " + USER_ONE_ID,
                            null,
                            30)
                            .then(id => {
                                appendUserOneContainer('message ' + id + ' has been sent');
                            })
                            .catch(error => {
                                appendUserOneContainer('failed to send message: ' + beautify(error), true);
                            });
                    } else {
                        clearInterval(intervalOne);
                    }
                }, 2000);
                clientUserOne.groupService.createGroup(
                    'Turms Developers Group',
                    'This is a group for the developers who are interested in Turms',
                    'nope')
                    .then(id => {
                        appendUserOneContainer('group ' + id + ' has been created');
                    })
                    .catch(error => {
                        appendUserOneContainer('failed to create group: ' + beautify(error), true);
                    });
                clientUserTwo.messageService.queryPendingMessagesWithTotal(1)
                    .then(messages => {
                        appendUserTwoContainer('Offline messages: ' + beautify(messages));
                    })
                    .catch(error => {
                        if (error && error.code === 2001) {
                            appendUserTwoContainer('No offline message');
                        }
                    });
                const intervalTwo = setInterval(() => {
                    if (clientUserOne.driver.isConnected()) {
                        clientUserTwo.messageService.sendMessage(
                            false,
                            USER_ONE_ID,
                            new Date(),
                            "Hello Community, My userId is " + USER_TWO_ID,
                            null,
                            30)
                            .then(id => {
                                appendUserTwoContainer('message ' + id + ' has been sent');
                            })
                            .catch(error => {
                                appendUserTwoContainer('failed to send message: ' + beautify(error), true);
                            });
                    } else {
                        clearInterval(intervalTwo);
                    }
                }, 2000);
            }
        )
        .catch(reason => {
            appendUserOneContainer('Failed to log in ' + beautify(reason), true);
            appendUserTwoContainer('Failed to log in ' + beautify(reason), true);
        });
}
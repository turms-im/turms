<template>
    <div class="client-terminal-container">
        <div
            ref="inputTerminal"
            class="client-terminal-container__input"
        />
        <div
            ref="notificationTerminal"
            class="client-terminal-container__notification"
        />
    </div>
</template>

<script>
import TurmsClient from 'turms-client-js';
import Terminal from './terminal';

const ONBOARD_MESSAGES = [
    `Current version of turms-client-js: ${TurmsClient.version}`,
    'Input commands like "user.login(\'1\', \'123\')"',
    '"help" for details'
];
const MESSAGE_FOR_VOID_FUNCTION = '(Done)';
const HELP = `* Builtin Objects:
    * Turms Client: client
    * Services:
        * conversation (convo)
        * group,
        * message (msg)
        * notification (notif)
        * storage (stge)
        * user
* Command Examples:
    * user.login('1', '123')
    * msg.sendMessage(false, '1', null, 'This is my message')
`;

export default {
    name: 'client-terminal',
    props: {
        options: {
            type: Object,
            required: false,
            default: () => ({})
        }
    },
    mounted() {
        this.notificationTerm = this.initNotificationTerm(this.$refs.notificationTerminal);
        this.inputTerm = this.initInputTerm(this.options, this.$refs.inputTerminal);
        this.client = this.initClient(this.notificationTerm);
    },
    beforeUnmount() {
        this.inputTerm.dispose();
        this.notificationTerm.dispose();
    },
    methods: {
        async executeCmd(cmd) {
            try {
                if (cmd === 'help') {
                    return {
                        type: 'info',
                        msg: HELP.replace(/\n/g, '\r\n')
                    };
                }
                const context = `
                    const client = this;
                    const conversation = convo = client.conversationService;
                    const group = client.groupService;
                    const message = msg = client.messageService;
                    const notification = notif = client.notificationService;
                    const storage = stge = client.storageService;
                    const user = client.userService;
                    return `;
                const func = new Function(context + cmd);
                let result = func.call(this.client);
                let isFunction;
                if (result instanceof Promise) {
                    isFunction = true;
                    result = await result;
                } else if (cmd.endsWith(')')) {
                    isFunction = true;
                }
                if (result == null && isFunction) {
                    result = MESSAGE_FOR_VOID_FUNCTION;
                } else {
                    result = this.stringify(result);
                }
                return {
                    msg: result,
                    newLine: true
                };
            } catch (e) {
                return {
                    type: 'error',
                    msg: this.stringify(e),
                    newLine: true
                };
            }
        },

        initClient(term) {
            const client = new TurmsClient({
                // TODO: make configurable
                wsUrl: `ws://${window.location.hostname}:10510`
            });
            client.userService.addOnOnlineListener(() => {
                term.writeMsg({
                    msg: 'Go online'
                });
            });
            client.userService.addOnOfflineListener(() => {
                term.writeMsg({
                    msg: 'Go offline'
                });
            });
            client.messageService.addMessageListener(m => {
                term.writeMsg({
                    msg: 'Received message: ' + this.stringify(m)
                });
            });
            client.notificationService.addNotificationListener(n => {
                term.writeMsg({
                    msg: 'Received notification: ' + this.stringify(n)
                });
            });
            return client;
        },

        initInputTerm(options, container) {
            const term = new Terminal(container, options);
            term.onLine = async (cmd) => await this.executeCmd(cmd);
            for (let i = 0; i < ONBOARD_MESSAGES.length; i++) {
                term.writeMsg({
                    type: 'info',
                    msg: ONBOARD_MESSAGES[i],
                    newLine: i === ONBOARD_MESSAGES.length - 1
                });
            }
            term.startNewLine();
            term.focus();
            return term;
        },

        initNotificationTerm(container) {
            return new Terminal(container, {
                disableStdin: true
            });
        },

        stringify(obj) {
            let msg;
            try {
                if (typeof obj === 'function') {
                    return '[Function]';
                }
                msg = obj.message || JSON.stringify(obj, null, '  ');
                return msg.replace(/\n/g, '\r\n');
            } catch (e) {
                // Suppress "TypeError: Converting circular structure to JSON"
                return obj;
            }
        }
    }
};
</script>

<style lang="scss">
.client-terminal-container {
    display: flex;
    width: 100%;
    height: 100%;

    &__input,
    &__notification {
        flex-grow: 1;
        max-width: 50%;
    }

    &__notification {
        margin-left: 8px;
    }

    .xterm-screen {
        canvas {
            padding: 8px;
        }
    }
}
</style>
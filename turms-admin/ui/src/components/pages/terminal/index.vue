<template>
    <div class="client-terminal-pane">
        <client-terminal
            ref="cliTerminal"
            class="client-terminal-pane__cli-terminal"
            :title="$t('cliConsole')"
            :options="cliTerminalOptions"
            :show-setting="true"
            @settingClick="openSettingModal"
        />
        <client-terminal
            ref="notificationTerminal"
            class="client-terminal-pane__notification-terminal"
            :title="$t('notifications')"
            :options="notificationTerminalOptions"
        />
        <a-modal
            v-model:visible="modalVisible"
            :title="$t('settings')"
            @ok="handleOk"
        >
            <a-form
                ref="form"
                :model="formState"
            >
                <a-form-item
                    label="WebSocket URL"
                    name="url"
                    :rules="$validator.create({required: true, isWsUrl: true})"
                >
                    <a-input v-model:value="formState.url" />
                </a-form-item>
                <a-form-item
                    :label="$t('useSharedContext')"
                    name="useSharedContext"
                >
                    <a-switch v-model:checked="formState.useSharedContext" />
                </a-form-item>
                <a-form-item
                    :label="$t('commandsHistorySize')"
                    name="commandsHistorySize"
                >
                    <a-input-number
                        v-model:value="formState.commandsHistorySize"
                        :min="0"
                        :max="1000"
                    />
                </a-form-item>
            </a-form>
        </a-modal>
    </div>
</template>

<script>
import TurmsClient from 'turms-client-js';
import Ast from '../../../assets/turms-client-ast.json';
import ClientTerminal from './client-terminal.vue';

const ONBOARD_MESSAGES = [
    `Current version of turms-client-js: ${TurmsClient.version}`,
    'Input commands, e.g. "user.login({userId: 1, password: \'123\'})"',
    '"help" for details'
];

const HELP = `* Builtin Objects:
    * Turms Client: client
    * Services:
        * conversation
        * group
        * message
        * notification
        * storage
        * user
* Builtin Commands:
    * help: print this help message
    * clear: clear screen
* Command Examples:
    * user.login({userId: 1, password: '123'})
    * message.sendMessage({isGroupMessage: false, targetId: 1, text: 'This is my message'})
`.replace(/\n/g, '\r\n');

const CONTEXT = `
const client = this;
const conversation = client.conversationService;
const group = client.groupService;
const message = client.messageService;
const notification = client.notificationService;
const storage = client.storageService;
const user = client.userService;
return `;

const AST_ROOT = [
    {
        name: 'help'
    },
    {
        name: 'clear'
    },
    {
        name: 'this',
        type: 'TurmsClient'
    },
    {
        name: 'client',
        type: 'TurmsClient'
    },
    {
        name: 'conversation',
        type: 'ConversationService'
    },
    {
        name: 'group',
        type: 'GroupService'
    },
    {
        name: 'message',
        type: 'MessageService'
    },
    {
        name: 'notification',
        type: 'NotificationService'
    },
    {
        name: 'storage',
        type: 'StorageService'
    },
    {
        name: 'user',
        type: 'UserService'
    }
].map(val => ({syntax: 'variable', ...val}));

const MESSAGE_FOR_VOID_FUNCTION = '(Done)';

export default {
    name: 'client-terminal-pane',
    components: {
        ClientTerminal
    },
    data() {
        const settings = {
            url: `ws://${window.location.hostname}:10510`,
            useSharedContext: false,
            commandsHistorySize: 0,
            ...this.$storage.get(this.$rs.storage.terminalSettings)
        };
        return {
            modalVisible: false,
            settings,
            formState: this.$util.copy(settings),
            cliTerminalOptions: {
                history: this.$storage.getArray(this.$rs.storage.terminalCommandHistory),
                ast: Ast,
                astRoot: AST_ROOT
            },
            notificationTerminalOptions: {
                disableStdin: true
            }
        };
    },
    mounted() {
        this.initCliTerminal();
        this.notificationTerminal = this.$refs.notificationTerminal.getTerminal();
        this.client = this.initClient();
    },
    methods: {
        openSettingModal() {
            this.formState = this.$util.copy(this.settings);
            this.modalVisible = true;
        },
        closeSettingModal() {
            this.modalVisible = false;
        },
        async handleOk() {
            let newSettings;
            try {
                newSettings = await this.$refs.form.validateFields();
            } catch (errorInfo) {
                return;
            }
            const isClientSettingsChanged = !this.$util.isEqual(this.settings, newSettings);
            const mergedSettings = {
                ...this.settings,
                ...newSettings
            };
            this.settings = mergedSettings;
            if (isClientSettingsChanged) {
                this.$storage.set(this.$rs.storage.terminalSettings, mergedSettings);
                await this.client.close();
                this.client = this.initClient();
                this.notificationTerminal.writeMsg({
                    msg: 'The previous client has been closed, and a new client with the new settings have been created'
                });
            }
            this.closeSettingModal();
        },
        async executeCmd(cmd) {
            if (cmd === 'help') {
                return {
                    message: {
                        type: 'info',
                        msg: HELP
                    }
                };
            } else if (cmd === 'clear') {
                return {
                    clear: true
                };
            }
            try {
                const func = new Function(CONTEXT + cmd);
                let result = func.call(this.client);
                const isFunction = cmd.endsWith(')');
                if (result instanceof Promise) {
                    result = await result;
                }
                result = result == null && isFunction
                    ? MESSAGE_FOR_VOID_FUNCTION
                    : this.stringify(result.isTurmsResponse ? result.data : result);
                return {
                    message: {
                        type: isFunction ? 'success' : 'info',
                        msg: result
                    }
                };
            } catch (e) {
                return {
                    message: {
                        type: 'error',
                        msg: this.stringify(e)
                    }
                };
            }
        },
        initClient() {
            const terminal = this.notificationTerminal;
            const client = new TurmsClient({
                wsUrl: this.settings.url,
                useSharedContext: this.settings.useSharedContext
            });
            client.userService.addOnOnlineListener(() => {
                terminal.writeMsg({
                    msg: 'Go online'
                });
            });
            client.userService.addOnOfflineListener(() => {
                terminal.writeMsg({
                    msg: 'Go offline'
                });
            });
            client.messageService.addMessageListener(m => {
                terminal.writeMsg({
                    msg: `Received message: ${this.stringify(m)}`
                });
            });
            client.notificationService.addNotificationListener(n => {
                terminal.writeMsg({
                    msg: `Received notification: ${this.stringify(n)}`
                });
            });
            return client;
        },

        initCliTerminal() {
            const cliTerminal = this.$refs.cliTerminal.getTerminal();
            cliTerminal.onLine = cmd => {
                this.$storage.push(this.$rs.storage.terminalCommandHistory,
                    cmd,
                    this.settings.commandsHistorySize);
                return this.executeCmd(cmd);
            };
            for (const element of ONBOARD_MESSAGES) {
                cliTerminal.writeMsg({
                    type: 'info',
                    msg: element
                });
            }
            cliTerminal.startNewLine();
            cliTerminal.focus();
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
.client-terminal-pane {
    display: flex;
    width: 100%;
    height: 100%;

    &__cli-terminal,
    &__notification-terminal {
        flex-grow: 1;
        max-width: 50%;
    }

    &__notification-terminal {
        margin-left: 8px;
    }
}
</style>
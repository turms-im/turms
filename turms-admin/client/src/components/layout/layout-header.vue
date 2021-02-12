<template>
    <a-layout-header class="header">
        <a-modal
            :confirm-loading="isChanging"
            :mask-closable="false"
            :keyboard="false"
            :visible="visible"
            :title="$t('changeConnectedServerUrl')"
            @ok="changeServer"
            @cancel="closeChangeServerModal"
        >
            <div class="header__current-server-url">
                {{ $t('currentServerUrl') }}: {{ globalUrl }}
            </div>
            <custom-input
                v-model:value="url"
                :placeholder="$t('serverUrl')"
            />
        </a-modal>
        <a-popover :title="$t('adminInfo')">
            <div class="header__admin">
                {{ admin.name }}
            </div>
            <template #content>
                <div class="header__admin-info">
                    {{ `${$t('account')}: ${admin.account}` }}
                </div>
                <div class="header__admin-info">
                    {{ `${$t('name')}: ${admin.name}` }}
                </div>
                <div class="header__admin-info">
                    {{ `${$t('roleId')}: ${admin.roleId}` }}
                </div>
                <div class="header__admin-info">
                    {{ `${$t('registrationDate')}: ${$moment(admin.registrationDate).format()}` }}
                </div>
            </template>
        </a-popover>
        <div
            class="header__change-server"
            @click="openChangeServerModal"
        >
            <icon type="database" />
        </div>
        <a-popconfirm
            :title="$t('confirmLogout')"
            @confirm="logout"
        >
            <div class="header__logout">
                <icon type="logout" />
            </div>
        </a-popconfirm>
        <a-select
            default-value="chinese"
            class="header__language-select"
            size="small"
            @change="changeLanguage"
        >
            <a-select-option value="chinese">
                {{ $t('chinese') }}
            </a-select-option>
        </a-select>
    </a-layout-header>
</template>

<script>
import axios from 'axios';
import CustomInput from '../common/custom-input';
import Icon from '../common/icon';

const JSONbig = require('json-bigint');

export default {
    name: 'layout-header',
    components: {
        CustomInput,
        Icon
    },
    data() {
        return {
            isChanging: false,
            visible: false,
            url: ''
        };
    },
    computed: {
        admin() {
            return this.$store.getters.admin || {};
        },
        globalUrl() {
            return this.$store.getters.url;
        }
    },
    watch: {
        globalUrl() {
            this.url = this.globalUrl.toString();
        }
    },
    methods: {
        changeLanguage() {
            //TODO: v1.0.x
        },
        openChangeServerModal() {
            this.visible = true;
        },
        closeChangeServerModal() {
            this.visible = false;
        },
        changeServer() {
            this.isChanging = true;
            const url = this.url.replace(/\/$/, '');
            this.$http.head(`${url}/admins`)
                .then(() => {
                    axios.defaults.transformResponse = [(data) => data && JSONbig.parse(data)];
                    this.$http.defaults.baseURL = url;
                    this.$http.defaults.headers.common = {
                        account: this.$store.getters.admin.account,
                        password: this.$store.getters.admin.password
                    };
                    this.$store.setUrl(url);
                    this.$message.success(this.$t('changedServerSuccessfully'));
                    this.visible = false;
                }).catch(error => {
                    this.$error(this.$t('failedToChangedServer'), error);
                }).finally(() => {
                    this.isChanging = false;
                });
        },
        logout() {
            this.$store.clearAdmin();
            this.$message.success(this.$t('loggedOut'));
        }
    }
};
</script>

<style lang="scss" scoped>
.header {
    color: whitesmoke;
    padding-right: 50px;
    display: flex;
    justify-content: flex-end;
    align-items: center;

    .header__admin {
        display: flex;
        flex-direction: column;
        align-items: flex-end;
        justify-content: center;

        .header__admin-info {
            line-height: 28px;
        }
    }

    .header__language-select {
        margin-left: 36px;
        width: 75px;
    }

    .header__change-server,
    .header__logout {
        font-size: 24px;
        margin-left: 36px;
        cursor: pointer;
    }
}

.header__current-server-url {
    margin-bottom: 16px;
}
</style>

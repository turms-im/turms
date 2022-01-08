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
            v-model:value="locale"
            class="header__language-select"
            size="small"
        >
            <a-select-option
                v-for="lang in locales"
                :key="lang.value"
                :value="lang.value"
            >
                {{ lang.label }}
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
            url: '',
            locale: this.$store.getters.locale,
            locales: [{
                label: 'English',
                value: this.$locales.enUS.locale
            }, {
                label: '中文',
                value: this.$locales.zhCN.locale
            }]
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
            this.url = this.globalUrl;
        },
        locale(locale) {
            this.locale = locale;
            this.$store.setLocale(locale);
            localStorage.setItem(this.$rs.storage.locale, locale);
        }
    },
    methods: {
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
                    const token = btoa(`${this.$store.getters.admin.account}:${this.$store.getters.admin.password}`);
                    this.$http.defaults.headers.common.Authorization = `Basic ${token}`;
                    this.$http.defaults.baseURL = url;
                    this.$store.setUrl(url);
                    this.$message.success(this.$t('changedServerSuccessfully'));
                    this.visible = false;
                }).catch(error => {
                    this.$error(this.$t('failedToChangeServer'), error);
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
    display: flex;
    align-items: center;
    justify-content: flex-end;
    padding-right: 50px;
    color: whitesmoke;

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
        width: 96px;
        margin-left: 36px;
    }

    .header__change-server,
    .header__logout {
        margin-left: 36px;
        font-size: 24px;
        cursor: pointer;
    }
}

.header__current-server-url {
    margin-bottom: 16px;
}
</style>

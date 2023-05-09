<template>
    <a-layout-header class="layout-header">
        <a-modal
            :confirm-loading="isChanging"
            :mask-closable="false"
            :keyboard="false"
            :visible="visible"
            :title="$t('changeTargetServerUrl')"
            @ok="changeServer"
            @cancel="closeChangeServerModal"
        >
            <div class="layout-header__current-server-url">
                {{ $t('currentServerUrl') }}: {{ globalUrl }}
            </div>
            <custom-input
                v-model:value="url"
                :placeholder="$t('serverUrl')"
            />
        </a-modal>
        <a-popover :title="$t('adminInfo')">
            <div class="layout-header__admin">
                {{ admin.name }}
            </div>
            <template #content>
                <div class="layout-header__admin-info">
                    {{ `${$t('account')}: ${admin.account}` }}
                </div>
                <div class="layout-header__admin-info">
                    {{ `${$t('name')}: ${admin.name}` }}
                </div>
                <div class="layout-header__admin-info">
                    {{ `${$t('roleId')}: ${admin.roleId}` }}
                </div>
                <div class="layout-header__admin-info">
                    {{ `${$t('registrationDate')}: ${$date(admin.registrationDate).format()}` }}
                </div>
            </template>
        </a-popover>
        <div
            class="layout-header__change-server"
            @click="openChangeServerModal"
        >
            <icon type="database" />
        </div>
        <a-popconfirm
            :title="$t('confirmLogout')"
            @confirm="logout"
        >
            <div class="layout-header__logout">
                <icon type="logout" />
            </div>
        </a-popconfirm>
        <a-select
            v-model:value="locale"
            class="layout-header__language-select"
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
import CustomInput from '../common/custom-input.vue';
import Icon from '../common/icon.vue';

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
                label: '日本語',
                value: this.$locales.jaJP.locale
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

<style lang="scss">
.layout-header {
    &__current-server-url {
        margin-bottom: 16px;
    }
}

#app .layout-header {
    display: flex;
    align-items: center;
    justify-content: flex-end;
    padding-right: 50px;
    color: #f5f5f5ff;

    &__admin {
        display: flex;
        flex-direction: column;
        align-items: flex-end;
        justify-content: center;

        &-info {
            line-height: 28px;
        }
    }

    &__language-select {
        width: 96px;
        margin-left: 36px;
    }

    &__change-server,
    &__logout {
        margin-left: 36px;
        font-size: 24px;
        cursor: pointer;
    }
}

</style>

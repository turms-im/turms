<template>
    <a-modal
        :closable="false"
        :footer="null"
        :keyboard="false"
        :mask-closable="false"
        :visible="visible"
        :title="$t('adminLogin')"
    >
        <logo class="login-modal__logo" />
        <!-- TODO: "validate" mentioned in antdv 2 is missing -->
        <a-form
            ref="form"
            :wrapper-col="{span: 24}"
            :model="form"
            :rules="$validator.parseRules(rules)"
            class="login-form"
            @finish="onValidationPass"
            @finishFailed="onValidationFailed"
        >
            <a-form-item name="url">
                <a-input
                    v-model:value="form.url"
                    class="login-modal__url"
                >
                    <template #prefix>
                        <span class="login-form__input-icon">
                            <icon type="link" />
                        </span>
                    </template>
                </a-input>
            </a-form-item>
            <a-form-item name="account">
                <a-input
                    ref="accountInput"
                    v-model:value="form.account"
                    autocomplete="username"
                    class="login-modal__account"
                    :placeholder="$t('adminAccount')"
                >
                    <template #prefix>
                        <span class="login-form__input-icon">
                            <icon type="user" />
                        </span>
                    </template>
                </a-input>
            </a-form-item>
            <a-form-item name="password">
                <a-input
                    v-model:value="form.password"
                    autocomplete="current-password"
                    class="login-modal__password"
                    :placeholder="$t('adminPassword')"
                    type="password"
                >
                    <template #prefix>
                        <span class="login-form__input-icon">
                            <icon type="lock" />
                        </span>
                    </template>
                </a-input>
            </a-form-item>
            <a-form-item>
                <a-button
                    :loading="confirmLoading"
                    class="login-form__submit"
                    html-type="submit"
                    type="primary"
                >
                    {{ $t('login') }}
                </a-button>
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
import Logo from '../common/logo.vue';
import Icon from '../common/icon.vue';

const DEFAULT_URL = `${window.location.protocol}//${window.location.hostname}:8510`;

export default {
    name: 'login-modal',
    components: {
        Logo,
        Icon
    },
    data() {
        return {
            form: {
                url: localStorage.getItem(this.$rs.storage.url) || DEFAULT_URL,
                account: '',
                password: ''
            },
            rules: {
                url: this.$validator.create({required: true, noBlank: true}),
                account: this.$validator.create({required: true, noBlank: true}),
                password: this.$validator.create({required: true, noBlank: true, maxNumber: 32})
            },
            confirmLoading: false
        };
    },
    computed: {
        admin() {
            return this.$store.getters.admin;
        },
        visible() {
            return !this.admin;
        }
    },
    mounted() {
        this.tryFocusAccountInput();
    },
    methods: {
        tryFocusAccountInput() {
            if (this.$refs.accountInput) {
                this.$refs.accountInput.focus();
            } else {
                setTimeout(() => {
                    this.tryFocusAccountInput();
                }, 100);
            }
        },
        async onValidationPass(values) {
            const url = this._getHttpUrl(values.url);
            if (url) {
                return this.login(values.url, values.account, values.password);
            } else {
                this.$error(this.$t('invalidUrl'));
            }
        },
        onValidationFailed(e) {
            this.$error(this.$t('loginFailed'), e);
        },
        async _getHttpUrl(candidate) {
            const startsWithHttp = /^https?:/i.test(candidate);
            if (startsWithHttp) {
                return candidate;
            }
            for (const url of [`https://${candidate}`, `http://${candidate}`]) {
                try {
                    await this.$http({method: 'head', url: `${url}${this.$rs.apis.admin}`});
                } catch (err) {
                    const serverExists = err.response?.status;
                    if (serverExists) {
                        return url;
                    }
                }
            }
        },
        login(url, account, password) {
            const token = btoa(`${account}:${password}`);
            this.confirmLoading = true;
            this.$http.defaults.baseURL = url;
            this.$http.defaults.headers.common.Authorization = `Basic ${token}`;
            this.$http.head(this.$rs.apis.admin)
                .then(() => {
                    return this.$http.get(this.$rs.apis.admin, {
                        params: {
                            accounts: account
                        }
                    }).then((response => {
                        const admin = response.data?.data?.[0];
                        if (admin) {
                            admin.password = password;
                            localStorage.setItem(this.$rs.storage.url, url);
                            this.$store.setAdmin(admin);
                            this.$store.setUrl(url);
                            this.$message.success(this.$t('loginSuccessfully'));
                        } else {
                            this.$message.error(`${this.$t('loginFailed')}: ${this.$t('missingAdminInfo')}`);
                        }
                    }));
                }).catch(error => {
                    this.$error(this.$t('loginFailed'), error);
                }).finally(() => {
                    this.confirmLoading = false;
                });
        }
    }
};
</script>

<style lang="scss" scoped>
.login-form {
    margin-top: 24px;

    &__input-icon {
        color: rgb(0 0 0 / 25%);
    }

    &__submit {
        width: 100%;
    }
}

.login-modal__logo {
    height: 84px;
    margin: 0 0 24px;
}
</style>

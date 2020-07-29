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
        <a-form
            :form="form"
            class="login-form"
            @submit="handleSubmit"
        >
            <a-form-item>
                <a-input v-decorator="$validator.create('url', {required: true, noBlank: true})">
                    <a-icon
                        slot="prefix"
                        class="login-form__input-icon"
                        type="link"
                    />
                </a-input>
            </a-form-item>
            <a-form-item>
                <a-input
                    ref="accountInput"
                    v-decorator="$validator.create('account', {required: true, noBlank: true})"
                    :placeholder="$t('adminAccount')"
                >
                    <a-icon
                        slot="prefix"
                        class="login-form__input-icon"
                        type="user"
                    />
                </a-input>
            </a-form-item>
            <a-form-item>
                <a-input
                    v-decorator="$validator.create('password', {required: true, noBlank: true, maxNumber: 32})"
                    :placeholder="$t('adminPassword')"
                    type="password"
                >
                    <a-icon
                        slot="prefix"
                        class="login-form__input-icon"
                        type="lock"
                    />
                </a-input>
            </a-form-item>
            <a-form-item>
                <a-checkbox v-decorator="['remember', {valuePropName: 'checked', initialValue: true}]">
                    {{ $t('remember') }}
                </a-checkbox>
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
import Logo from '../common/logo';
import Vue from 'vue';
import axios from 'axios';
const JSONbig = require('json-bigint');
const DEFAULT_URL = 'http://localhost:8510';
export default {
    name: 'login-modal',
    components: {
        Logo
    },
    data() {
        if (!this.form.getFieldValue('url')) {
            this.form.setFieldsValue({
                url: DEFAULT_URL
            });
        }
        return {
            confirmLoading: false,
            url: DEFAULT_URL
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
    beforeCreate() {
        this.form = this.$form.createForm(this);
    },
    mounted() {
        if (this.$refs.accountInput) {
            this.$refs.accountInput.focus();
        } else {
            setTimeout(() => {
                this.$refs.accountInput.focus();
            });
        }
    },
    methods: {
        handleSubmit(e) {
            e.preventDefault();
            this.form.validateFields((err, values) => {
                if (!err) {
                    if (/^https?:/i.test(values.url)) {
                        this.login(values.url, values.account, values.password);
                    } else {
                        let url = `https://${values.url}`;
                        axios({method: 'head', url: `${url}${this.$rs.apis.admin}`})
                            .catch(httpsError => {
                                if (httpsError.response && httpsError.response.status) {
                                    this.login(url, values.account, values.password);
                                } else {
                                    url = `http://${values.url}`;
                                    axios({method: 'head', url: `${url}${this.$rs.apis.admin}`})
                                        .catch(httpError => {
                                            if (httpError.response && httpError.response.status) {
                                                this.login(url, values.account, values.password);
                                            } else {
                                                this.$error(this.$t('loginFailed'), httpError);
                                            }
                                        });
                                }
                            });
                    }
                }
            });
        },
        login(url, account, password) {
            if (this.url !== url || !Vue.prototype.$client) {
                this.url = url;
                axios.defaults.transformResponse =  [function (data) {
                    if (data) {
                        return JSONbig.parse(data);
                    } else {
                        return data;
                    }
                }];
                Vue.prototype.$client = axios.create({
                    baseURL: url,
                    timeout: 10 * 1000
                });
            }
            this.confirmLoading = true;
            this.$client.defaults.headers.common = {
                account,
                password
            };
            this.$client.head(this.$rs.apis.admin)
                .then(() => {
                    return this.$client.get(this.$rs.apis.admin, {
                        params: {
                            accounts: account
                        }
                    }).then((response => {
                        if (response.data && response.data.data && response.data.data[0]) {
                            const admin = response.data.data[0];
                            admin.password = password;
                            this.$store.dispatch('setAdmin', admin);
                            this.$store.dispatch('setUrl', url);
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
            color: rgba(0, 0, 0, 0.25);
        }

        &__submit {
            width: 100%;
        }
    }

    .login-modal__logo {
        height: 54px;
        margin: 0 0 24px 0;
    }
</style>

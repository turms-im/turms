<template>
    <content-template
        :record-key="recordKey"
        :query-key="queryKey"
        :url="url"
        :initial-data-urls="initialDataUrls"
        :filters="filters"
        :action-groups="actionGroups"
        :table="table"
        @afterDataDeleted="afterDataDeleted"
        @afterDataUpdated="afterDataUpdated"
        @afterDataInitialized="afterDataInitialized"
    />
</template>

<script>
import ContentTemplate from '../content/template/content-template';

export default {
    name: 'access-control-admin-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            url: this.$rs.apis.admin,
            initialDataUrls: [this.$rs.apis.adminRole],
            recordKey: 'account',
            queryKey: 'accounts',
            filters: [
                {
                    type: 'INPUT',
                    model: '',
                    name: 'accounts',
                    placeholder: this.$t('adminAccount'),
                    decorator: {
                        nonSpace: true
                    }
                },
                {
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'roleIds',
                    url: '',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: this.$t('allRoles')
                        }]
                    }
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('addAdmin'),
                    type: 'CREATE',
                    fields: [
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('account', {required: true, noBlank: true, maxNumber: 32})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('password', {required: true, noBlank: true, maxNumber: 32})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('name', {required: true, noBlank: true, maxNumber: 32})
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('roleId', {required: true})
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedAdmins'),
                    type: 'UPDATE',
                    fields: [
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('password', {noBlank: true, maxNumber: 32})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('name', {noBlank: true, maxNumber: 32})
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('roleId')
                        }
                    ]
                }]
            ],
            table: {
                columns: [{
                    key: 'account',
                    width: '20%'
                },
                {
                    key: 'name',
                    width: '20%'
                },
                {
                    key: 'password',
                    width: '15%'
                },
                {
                    key: 'roleId',
                    width: '15%'
                },
                {
                    key: 'registrationDate',
                    width: '15%'
                },
                {
                    key: 'operation',
                    width: '15%'
                }]}
        };
    },
    methods: {
        afterDataDeleted(accounts) {
            if (accounts && accounts.includes(this.$store.getters.admin.account)) {
                this.$store.dispatch('setAdmin', null);
            }
        },
        afterDataUpdated(accounts, value) {
            if (accounts.includes(this.$store.getters.admin.account)) {
                if (value.name) {
                    this.$store.getters.admin.name = name;
                }
                if (value.password) {
                    this.$store.getters.admin.password = value.password;
                }
                if (value.roleId) {
                    this.$store.getters.admin.roleId = value.roleId;
                }
                this.$store.dispatch('setAdmin', this.$store.getters.admin);
            }
        },
        afterDataInitialized(responseList) {
            const data = responseList[0].data.data.map(item => {
                item.label = `${item.name}(${item.id})`;
                return item;
            });
            this.$ui.fillSelectsWithValues(['roleId', 'roleIds'], data, this.actionGroups, this.filters);
        }
    }
};
</script>

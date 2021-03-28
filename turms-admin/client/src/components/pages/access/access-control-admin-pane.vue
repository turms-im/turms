<template>
    <content-template
        :name="name"
        :record-key="recordKey"
        :query-key="queryKey"
        :url="url"
        :initial-data-urls="initialDataUrls"
        :filters="filters"
        :action-groups="actionGroups"
        :table="table"
        @onDataDeleted="onDataDeleted"
        @onDataUpdated="onDataUpdated"
        @onDataInited="onDataInited"
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
            name: 'admin-info',
            url: this.$rs.apis.admin,
            initialDataUrls: [this.$rs.apis.adminRole],
            recordKey: 'account',
            queryKey: 'accounts',
            filters: [
                {
                    type: 'INPUT',
                    model: '',
                    name: 'accounts',
                    placeholder: 'adminAccount',
                    rules: {
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
                            label: 'allRoles'
                        }]
                    }
                }
            ],
            actionGroups: [
                [{
                    title: 'addAdmin',
                    type: 'CREATE',
                    fields: [
                        {
                            id: 'account',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, noBlank: true, maxNumber: 32})
                        },
                        {
                            id: 'password',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, noBlank: true, maxNumber: 32})
                        },
                        {
                            id: 'name',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, noBlank: true, maxNumber: 32})
                        },
                        {
                            id: 'roleId',
                            type: 'SELECT',
                            rules: this.$validator.create({required: true})
                        }
                    ]
                },
                {
                    title: 'updateSelectedAdmins',
                    type: 'UPDATE',
                    fields: [
                        {
                            id: 'password',
                            type: 'INPUT',
                            rules: this.$validator.create({noBlank: true, maxNumber: 32})
                        },
                        {
                            id: 'name',
                            type: 'INPUT',
                            rules: this.$validator.create({noBlank: true, maxNumber: 32})
                        },
                        {
                            id: 'roleId',
                            type: 'SELECT'
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
                    width: '20%'
                },
                {
                    key: 'operation',
                    width: '10%'
                }]
            }
        };
    },
    methods: {
        onDataDeleted(accounts) {
            if (accounts?.includes(this.$store.getters.admin.account)) {
                this.$store.setAdmin();
            }
        },
        onDataUpdated(accounts, value) {
            const admin = this.$store.getters.admin;
            if (accounts.includes(admin.account)) {
                if (value.name) {
                    admin.name = value.name;
                }
                if (value.password) {
                    admin.password = value.password;
                }
                if (value.roleId) {
                    admin.roleId = value.roleId;
                }
                this.$store.setAdmin(admin);
            }
        },
        onDataInited(responseList) {
            const data = responseList[0].data.data.map(item => {
                item.label = `${item.name}(${item.id})`;
                return item;
            });
            this.$ui.fillSelectsWithValues(['roleId', 'roleIds'], data, this.actionGroups, this.filters);
        }
    }
};
</script>

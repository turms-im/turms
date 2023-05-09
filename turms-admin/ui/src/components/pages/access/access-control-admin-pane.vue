<template>
    <content-template
        :name="name"
        :record-key="recordKey"
        :query-key="queryKey"
        :url="url"
        :initial-data-urls="initialDataUrls"
        :filters="filters"
        :actions="actions"
        :table="table"
        @onDataInited="onDataInited"
        @onRecordsDeleted="onRecordsDeleted"
        @onRecordsUpdated="onRecordsUpdated"
    />
</template>

<script>
import ContentTemplate from '../content/template/content-template.vue';

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
            actions: [
                {
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
                }
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
        onRecordsDeleted(accounts) {
            if (accounts?.includes(this.$store.getters.admin.account)) {
                this.$store.setAdmin();
            }
        },
        onRecordsUpdated(accounts, updatedFields) {
            const admin = this.$store.getters.admin;
            if (accounts.includes(admin.account)) {
                Object.assign(admin, updatedFields);
                this.$store.setAdmin(admin);
            }
        },
        onDataInited(responseList) {
            const data = responseList[0].data.data.map(item => {
                item.label = `${item.name}(${item.id})`;
                return item;
            });
            this.$ui.fillSelectsWithValues(['roleId', 'roleIds'], data, this.actions, this.filters);
        }
    }
};
</script>

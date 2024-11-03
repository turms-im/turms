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
            filters: [
                {
                    type: 'INPUT',
                    name: 'ids',
                    placeholder: 'adminId'
                },
                {
                    type: 'INPUT',
                    name: 'loginNames',
                    placeholder: 'loginName',
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
                            id: 'loginName',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, noBlank: true, maxNumber: 32})
                        },
                        {
                            id: 'password',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, noBlank: true, maxNumber: 32})
                        },
                        {
                            id: 'displayName',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, noBlank: true, maxNumber: 32})
                        },
                        {
                            id: 'roleIds',
                            type: 'SELECT',
                            mode: 'multiple',
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
                            id: 'displayName',
                            type: 'INPUT',
                            rules: this.$validator.create({noBlank: true, maxNumber: 32})
                        },
                        {
                            id: 'roleIds',
                            type: 'SELECT',
                            mode: 'multiple'
                        }
                    ]
                }
            ],
            table: {
                columns: [{
                    key: 'id',
                    width: '10%'
                },
                {
                    key: 'loginName',
                    width: '15%'
                },
                {
                    key: 'displayName',
                    width: '15%'
                },
                {
                    key: 'password',
                    width: '15%'
                },
                {
                    key: 'roleIds',
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
        onRecordsDeleted(ids) {
            if (ids?.includes(this.$store.getters.admin.id)) {
                this.$store.setAdmin();
            }
        },
        onRecordsUpdated(ids, updatedFields) {
            const admin = this.$store.getters.admin;
            if (ids.includes(admin.id)) {
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
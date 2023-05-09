<template>
    <content-template
        :name="name"
        :url="url"
        :initial-data-urls="initialDataUrls"
        :actions="actions"
        :table="table"
        :transform="transform"
        @onDataInited="onDataInited"
    />
</template>

<script>
import ContentTemplate from '../content/template/content-template.vue';

let ALL_PERMISSIONS_TREE;
let ALL_PERMISSIONS_ARRAY;

export default {
    name: 'access-control-admin-role-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'admin-role',
            url: this.$rs.apis.adminRole,
            initialDataUrls: [this.$rs.apis.adminRole],
            actions: [
                {
                    title: 'addRole',
                    type: 'CREATE',
                    fields: [
                        {
                            id: 'id',
                            type: 'INPUT',
                            label: 'roleId',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'name',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, noBlank: true, maxNumber: 32})
                        },
                        {
                            id: 'rank',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'permissions',
                            type: 'TREE',
                            label: 'permission',
                            data: {},
                            transform: this.transformParams,
                            rules: this.$validator.create({required: true})
                        }
                    ]
                },
                {
                    title: 'updateSelectedRoles',
                    type: 'UPDATE',
                    fields: [
                        {
                            id: 'name',
                            type: 'INPUT',
                            rules: this.$validator.create({noBlank: true, maxNumber: 32})
                        },
                        {
                            id: 'rank',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'permissions',
                            type: 'TREE',
                            label: 'permission',
                            data: {},
                            transform: this.transformParams
                        }
                    ]
                }
            ],
            table: {
                columns: [
                    {
                        title: 'roleId',
                        key: 'id',
                        width: '15%'
                    },
                    {
                        key: 'name',
                        width: '15%'
                    },
                    {
                        key: 'rank',
                        width: '15%'
                    },
                    {
                        key: 'creationDate',
                        width: '15%'
                    },
                    {
                        title: 'permission',
                        key: 'permissions',
                        type: 'TREE',
                        width: '30%'
                    },
                    {
                        key: 'operation',
                        width: '10%'
                    }
                ]
            }
        };
    },
    mounted() {
        if (!ALL_PERMISSIONS_TREE) {
            const permissions = this.$util.copy(this.$rs.permissions);
            ALL_PERMISSIONS_TREE = this.parseTree(permissions);
            ALL_PERMISSIONS_ARRAY = this.parseArray(permissions);
        }
    },
    methods: {
        onDataInited(responseList) {
            const data = responseList[0].data.data.map(item => {
                item.label = `${item.name}(${item.id})`;
                return item;
            });
            this.$ui.fillSelectsWithValues(['roleId', 'roleIds'], data, this.actions);
        },
        transform(data) {
            this.resetPermissionTreeData();
            for (const record of data.records) {
                const currentPermissions = this.$util.copy(ALL_PERMISSIONS_TREE);
                for (const ownedPermission of record.permissions) {
                    const foundRecord = this.$util.deepSearch(currentPermissions, 'key', (key, value) => value === ownedPermission);
                    if (foundRecord) {
                        foundRecord.slots = {
                            icon: 'check'
                        };
                    }
                }
                record.permissions = currentPermissions;
            }
            data.records.permissions = ALL_PERMISSIONS_TREE;
            return data;
        },
        resetPermissionTreeData() {
            for (const action of this.actions) {
                for (const field of action.fields) {
                    if (field.type.toUpperCase() === 'TREE' && field.id === 'permissions') {
                        field.data = ALL_PERMISSIONS_TREE;
                        return;
                    }
                }
            }
        },
        transformParams(checkedKeys) {
            return checkedKeys.filter(key => ALL_PERMISSIONS_ARRAY.includes(key));
        },
        parseTree(permissions) {
            if (permissions instanceof Array) {
                return permissions
                    .map(permission => ({
                        slots: {
                            icon: 'close'
                        },
                        selectable: false,
                        title: permission,
                        key: permission
                    }));
            } else {
                return Object.entries(permissions)
                    .map(([key, value]) => ({
                        slots: {
                            icon: 'bars'
                        },
                        selectable: false,
                        title: key,
                        key,
                        children: this.parseTree(value)
                    }));
            }
        },
        parseArray(permissions) {
            if (permissions instanceof Array) {
                return permissions;
            } else {
                return Object.entries(permissions)
                    .flatMap(entry => this.parseArray(entry[1]));
            }
        }
    }
};
</script>
<template>
    <content-template
        :name="name"
        :url="url"
        :initial-data-urls="initialDataUrls"
        :action-groups="actionGroups"
        :table="table"
        :transform="transform"
        @onDateInited="onDateInited"
    />
</template>

<script>
import ContentTemplate from '../content/template/content-template';

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
            actionGroups: [
                [{
                    title: this.$t('addRole'),
                    type: 'CREATE',
                    fields: [
                        {
                            id: 'id',
                            type: 'INPUT',
                            label: this.$t('roleId'),
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
                            label: this.$t('permission'),
                            checkedKeys: [],
                            data: {},
                            transform: this.transformParams,
                            rules: this.$validator.create({required: true})
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedRoles'),
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
                            label: this.$t('permission'),
                            checkedKeys: [],
                            data: {},
                            transform: this.transformParams
                        }
                    ]
                }]
            ],
            table: {
                columns: [
                    {
                        title: this.$t('roleId'),
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
                        title: this.$t('permission'),
                        key: 'permissions',
                        type: 'tree',
                        width: '45%'
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
            const permissions = JSON.parse(JSON.stringify(this.$rs.permissions));
            ALL_PERMISSIONS_TREE = this.parseTree(permissions);
            ALL_PERMISSIONS_ARRAY = this.parseArray(permissions);
        }
    },
    methods: {
        onDateInited(responseList) {
            const data = responseList[0].data.data.map(item => {
                item.label = `${item.name}(${item.id})`;
                return item;
            });
            this.$ui.fillSelectsWithValues(['roleId', 'roleIds'], data, this.actionGroups);
        },
        transform(data) {
            this.fillTreeData(ALL_PERMISSIONS_TREE);
            for (const record of data.records) {
                const currentPermissions = JSON.parse(JSON.stringify(ALL_PERMISSIONS_TREE));
                for (const permission of record.permissions) {
                    const foundRecord = this.deepSearch(currentPermissions, 'key', (key, value) => value === permission);
                    if (foundRecord) {
                        foundRecord.scopedSlots = {
                            icon: 'check'
                        };
                    }
                }
                record.permissions = currentPermissions;
            }
            data.records.permissions = ALL_PERMISSIONS_TREE;
            return data;
        },
        fillTreeData(treeData) {
            for (const actions of this.actionGroups) {
                for (const action of actions) {
                    for (const field of action.fields) {
                        if (field.type.toUpperCase() === 'TREE' && field.id === 'permissions') {
                            field.data = treeData;
                        }
                    }
                }
            }
        },
        deepSearch(object, key, predicate) {
            if (Object.hasOwnProperty.call(object, key) && predicate(key, object[key])) {
                return object;
            }
            for (const val of Object.values(object)) {
                if (typeof val === 'object') {
                    const o = this.deepSearch(val, key, predicate);
                    if (o != null) {
                        return o;
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
                        scopedSlots: {
                            icon: 'close'
                        },
                        selectable: false,
                        title: permission,
                        key: permission
                    }));
            } else {
                return Object.entries(permissions)
                    .map(([key, value]) => ({
                        scopedSlots: {
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
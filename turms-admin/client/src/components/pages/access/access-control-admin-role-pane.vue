<template>
    <content-template
        :name="name"
        :url="url"
        :initial-data-urls="initialDataUrls"
        :action-groups="actionGroups"
        :table="table"
        :transform="transform"
        @afterDataInitialized="afterDataInitialized"
    />
</template>

<script>
import ContentTemplate from '../content/template/content-template';
let allPermissionsTree;
let allPermissionsArray;
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
                            type: 'INPUT',
                            label: this.$t('roleId'),
                            decorator: this.$validator.create('id', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('name', {required: true, noBlank: true, maxNumber: 32})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('rank', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'TREE',
                            label: this.$t('permission'),
                            checkedKeys: [],
                            data: {},
                            transform: this.transformParams,
                            decorator: this.$validator.create('permissions', {required: true})
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedRoles'),
                    type: 'UPDATE',
                    fields: [
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('name', {noBlank: true, maxNumber: 32})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('rank', {onlyNumber: true})
                        },
                        {
                            type: 'TREE',
                            label: this.$t('permission'),
                            checkedKeys: [],
                            data: {},
                            transform: this.transformParams,
                            decorator: this.$validator.create('permissions')
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
                ]}
        };
    },
    methods: {
        afterDataInitialized(responseList) {
            const data = responseList[0].data.data.map(item => {
                item.label = `${item.name}(${item.id})`;
                return item;
            });
            this.$ui.fillSelectsWithValues(['roleId', 'roleIds'], data, this.actionGroups);
        },
        transform(data) {
            if (!allPermissionsTree) {
                const permissions = JSON.parse(JSON.stringify(this.$rs.permissions));
                allPermissionsTree = this.parseTree(permissions);
                allPermissionsArray = this.parseArray(permissions);
            }
            this.fillTreeData(allPermissionsTree);
            for (const record of data.records) {
                const currentPermissions = JSON.parse(JSON.stringify(allPermissionsTree));
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
            data.records.permissions = allPermissionsTree;
            return data;
        },
        fillTreeData(treeData) {
            for (const actions of this.actionGroups) {
                for (const action of actions) {
                    for (const field of action.fields) {
                        if (field.type.toUpperCase() === 'TREE' && field.decorator[0] === 'permissions') {
                            field.data = treeData;
                        }
                    }
                }
            }
        },
        deepSearch (object, key, predicate) {
            if (Object.hasOwnProperty.call(object, key) && predicate(key, object[key]) === true) {
                return object;
            }
            for (let i = 0; i < Object.keys(object).length; i++) {
                const nextObject = object[Object.keys(object)[i]];
                if (nextObject && typeof nextObject === 'object') {
                    const o = this.deepSearch(nextObject, key, predicate);
                    if (o != null) return o;
                }
            }
            return null;
        },
        transformParams(checkedKeys) {
            return checkedKeys.filter(key => allPermissionsArray.includes(key));
        },
        parseTree(permissions) {
            if (permissions instanceof Array) {
                const records = [];
                for (const permission of permissions) {
                    records.push({
                        scopedSlots: {
                            icon: 'close'
                        },
                        selectable: false,
                        title: permission,
                        key: permission});
                }
                return records;
            } else {
                const records = [];
                for (const entry of Object.entries(permissions)) {
                    records.push({
                        scopedSlots: {
                            icon: 'bars'
                        },
                        selectable: false,
                        title: entry[0],
                        key: entry[0],
                        children: this.parseTree(entry[1])
                    });
                }
                return records;
            }
        },
        parseArray(permissions) {
            if (permissions instanceof Array) {
                return permissions;
            } else {
                const records = [];
                for (const entry of Object.entries(permissions)) {
                    records.push(...this.parseArray(entry[1]));
                }
                return records;
            }
        }
    }
};
</script>
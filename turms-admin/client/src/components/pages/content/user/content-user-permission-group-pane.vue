<template>
    <content-template
        :name="name"
        :url="url"
        :actions="actions"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../template/content-template.vue';

export default {
    name: 'content-user-permission-group-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'user-permission-group',
            url: this.$rs.apis.userPermissionGroup,
            actions: [
                {
                    title: 'addPermissionGroup',
                    type: 'CREATE',
                    size: 'XL',
                    fields: [
                        {
                            id: 'id',
                            type: 'INPUT',
                            label: 'permissionGroupId',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'creatableGroupTypeIds',
                            type: 'INPUT',
                            label: 'creatableGroupTypeId',
                            rules: this.$validator.create({required: true})
                        },
                        {
                            id: 'ownedGroupLimit',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'ownedGroupLimitForEachGroupType',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'groupTypeIdToLimit',
                            type: 'INPUT',
                            label: 'groupTypeIdToLimit',
                            rules: this.$validator.create({required: true})
                        }
                    ]
                },
                {
                    title: 'updateSelectedPermissionGroups',
                    type: 'UPDATE',
                    size: 'XL',
                    fields: [
                        {
                            id: 'creatableGroupTypeIds',
                            type: 'INPUT',
                            label: 'creatableGroupTypeId'
                        },
                        {
                            id: 'ownedGroupLimit',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'ownedGroupLimitForEachGroupType',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'groupTypeIdToLimit',
                            type: 'INPUT',
                            label: 'groupTypeIdToLimit'
                        }
                    ]
                }
            ],
            table: {
                columns: [
                    {
                        title: 'permissionGroupId',
                        key: 'id',
                        width: '20%'
                    },
                    {
                        title: 'creatableGroupTypeId',
                        key: 'creatableGroupTypeIds',
                        width: '20%'
                    },
                    {
                        key: 'ownedGroupLimit',
                        width: '15%'
                    },
                    {
                        key: 'ownedGroupLimitForEachGroupType',
                        width: '15%'
                    },
                    {
                        title: 'groupTypeIdToLimit',
                        key: 'groupTypeIdToLimit',
                        width: '20%'
                    },
                    {
                        key: 'operation',
                        width: '10%'
                    }
                ]
            }
        };
    }
};
</script>
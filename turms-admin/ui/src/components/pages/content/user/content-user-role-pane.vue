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
    name: 'content-user-role-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'user-role',
            url: this.$rs.apis.userRole,
            actions: [
                {
                    title: 'addUserRole',
                    type: 'CREATE',
                    size: 'XL',
                    fields: [
                        {
                            id: 'id',
                            type: 'INPUT',
                            label: 'userRoleId',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'name',
                            type: 'INPUT'
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
                    title: 'updateSelectedUserRoles',
                    type: 'UPDATE',
                    size: 'XL',
                    fields: [
                        {
                            id: 'name',
                            type: 'INPUT'
                        },
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
                        title: 'userRoleId',
                        key: 'id',
                        width: '15%'
                    },
                    {
                        key: 'name',
                        width: '15%'
                    },
                    {
                        title: 'creatableGroupTypeId',
                        key: 'creatableGroupTypeIds',
                        width: '20%'
                    },
                    {
                        key: 'ownedGroupLimit',
                        width: '10%'
                    },
                    {
                        key: 'ownedGroupLimitForEachGroupType',
                        width: '10%'
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
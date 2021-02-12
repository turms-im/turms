<template>
    <content-template
        :name="name"
        :url="url"
        :action-groups="actionGroups"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../template/content-template';

export default {
    name: 'content-user-permission-group-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'user-permission-group',
            url: this.$rs.apis.userPermissionGroup,
            actionGroups: [
                [{
                    title: this.$t('addPermissionGroup'),
                    type: 'CREATE',
                    size: 'XL',
                    fields: [
                        {
                            id: 'id',
                            type: 'INPUT',
                            label: this.$t('permissionGroupId'),
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'creatableGroupTypeIds',
                            type: 'INPUT',
                            label: this.$t('creatableGroupTypeId'),
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
                            id: 'groupTypeLimits',
                            type: 'INPUT',
                            label: this.$t('groupTypeLimit'),
                            rules: this.$validator.create({required: true})
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedPermissionGroups'),
                    type: 'UPDATE',
                    size: 'XL',
                    fields: [
                        {
                            id: 'creatableGroupTypeIds',
                            type: 'INPUT',
                            label: this.$t('creatableGroupTypeId')
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
                            id: 'groupTypeLimits',
                            type: 'INPUT',
                            label: this.$t('groupTypeLimit')
                        }
                    ]
                }]
            ],
            table: {
                columns: [
                    {
                        title: this.$t('permissionGroupId'),
                        key: 'id',
                        width: '20%'
                    },
                    {
                        title: this.$t('creatableGroupTypeId'),
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
                        title: this.$t('groupTypeLimit'),
                        key: 'groupTypeLimits',
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
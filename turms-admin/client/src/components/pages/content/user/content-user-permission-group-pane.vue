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
                            type: 'INPUT',
                            label: this.$t('permissionGroupId'),
                            decorator: this.$validator.create('id', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            label: this.$t('creatableGroupTypeId'),
                            decorator: this.$validator.create('creatableGroupTypeIds', {required: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('ownedGroupLimit', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('ownedGroupLimitForEachGroupType', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            label: this.$t('groupTypeLimit'),
                            decorator: this.$validator.create('groupTypeLimits', {required: true})
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedPermissionGroups'),
                    type: 'UPDATE',
                    size: 'XL',
                    fields: [
                        {
                            type: 'INPUT',
                            label: this.$t('creatableGroupTypeId'),
                            decorator: this.$validator.create('creatableGroupTypeIds')
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('ownedGroupLimit', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('ownedGroupLimitForEachGroupType', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            label: this.$t('groupTypeLimit'),
                            decorator: this.$validator.create('groupTypeLimits')
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
                ]}
        };
    }
};
</script>
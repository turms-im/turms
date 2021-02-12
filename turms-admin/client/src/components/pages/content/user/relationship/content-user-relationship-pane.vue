<template>
    <content-template
        :name="name"
        :record-key="recordKey"
        :query-key="queryKey"
        :url="url"
        :params="params"
        :filters="filters"
        :action-groups="actionGroups"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../../template/content-template';

export default {
    name: 'content-user-relationship-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'user-relationship',
            url: this.$rs.apis.userRelationship,
            params: {
                withGroupIndexes: true
            },
            recordKey: 'key',
            queryKey: 'keys',
            filters: [
                {
                    type: 'INPUT',
                    model: '',
                    name: 'ownerIds',
                    placeholder: this.$t('ownerId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'relatedUserIds',
                    placeholder: this.$t('relatedUserId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'groupIndexes',
                    placeholder: this.$t('relationshipGroupIndex')
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'blockDate'
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'establishmentDate'
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('addUserRelationship'),
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'ownerId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'relatedUserId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'blockDate',
                            type: 'DATE'
                        },
                        {
                            id: 'establishmentDate',
                            type: 'DATE'
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedUserRelationships'),
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'blockDate',
                            type: 'DATE'
                        },
                        {
                            id: 'establishmentDate',
                            type: 'DATE'
                        }
                    ]
                }]
            ],
            table: {
                columns: [
                    {
                        key: 'key.ownerId',
                        width: '20%'
                    },
                    {
                        key: 'key.relatedUserId',
                        width: '20%'
                    },
                    {
                        key: 'blockDate',
                        width: '17.5%'
                    },
                    {
                        key: 'establishmentDate',
                        width: '17.5%'
                    },
                    {
                        title: this.$t('relationshipGroupIndex'),
                        key: 'groupIndexes',
                        width: '15%'
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
<template>
    <content-template
        :name="name"
        :record-key="recordKey"
        :query-key="queryKey"
        :url="url"
        :filters="filters"
        :action-groups="actionGroups"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../../template/content-template';

export default {
    name: 'content-user-relationship-group-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'user-relationship-group',
            url: this.$rs.apis.userRelationshipGroup,
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
                    name: 'indexes',
                    placeholder: this.$t('groupIndex')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'names',
                    placeholder: this.$t('name'),
                    rules: {
                        nonSpace: true
                    }
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'creationDate'
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('addUserRelationshipGroup'),
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'ownerId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'index',
                            type: 'INPUT',
                            label: this.$t('groupIndex'),
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'name',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true})
                        },
                        {
                            id: 'creationDate',
                            type: 'DATE'
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedUserRelationshipGroups'),
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'name',
                            type: 'INPUT'
                        },
                        {
                            id: 'creationDate',
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
                        title: this.$t('relationshipGroupIndex'),
                        key: 'key.index',
                        width: '20%'
                    },
                    {
                        key: 'name',
                        width: '25%'
                    },
                    {
                        key: 'creationDate',
                        width: '25%'
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
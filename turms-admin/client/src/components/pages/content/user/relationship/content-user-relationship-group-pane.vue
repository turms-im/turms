<template>
    <content-template
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
                    decorator: {
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
                            type: 'INPUT',
                            decorator: this.$validator.create('ownerId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            label: this.$t('groupIndex'),
                            decorator: this.$validator.create('index', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('name', {required: true})
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('creationDate')
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedUserRelationshipGroups'),
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('name')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('creationDate')
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
                ]}
        };
    }
};
</script>
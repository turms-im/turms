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
                            type: 'INPUT',
                            decorator: this.$validator.create('ownerId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('relatedUserId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('blockDate')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('establishmentDate')
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedUserRelationships'),
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('blockDate')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('establishmentDate')
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
                ]}
        };
    }
};
</script>
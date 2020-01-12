<template>
    <content-template
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
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'isBlocked',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: this.$t('friendAndBlacklistedUser')
                        },
                        {
                            id: false,
                            label: this.$t('friend')
                        },
                        {
                            id: true,
                            label: this.$t('blacklistedUser')
                        }]
                    }
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
                            type: 'SWITCH',
                            decorator: this.$validator.create('isBlocked')
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
                            type: 'SELECT',
                            decorator: this.$validator.create('isBlocked'),
                            options: {
                                values: [
                                    {
                                        label: this.$t('yes'),
                                        id: true
                                    },
                                    {
                                        label: this.$t('no'),
                                        id: false
                                    }
                                ]
                            }
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
                        key: 'isBlocked',
                        width: '20%'
                    },
                    {
                        key: 'establishmentDate',
                        width: '15%'
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
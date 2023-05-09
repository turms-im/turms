<template>
    <content-template
        :name="name"
        :record-key="recordKey"
        :query-key="queryKey"
        :query-params="queryParams"
        :url="url"
        :filters="filters"
        :actions="actions"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../../template/content-template.vue';

export default {
    name: 'content-user-relationship-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'user-relationship',
            url: this.$rs.apis.userRelationship,
            queryParams: {
                withGroupIndexes: true
            },
            recordKey: 'key',
            queryKey: 'keys',
            filters: [
                {
                    type: 'INPUT',
                    name: 'ownerIds',
                    placeholder: 'ownerId'
                },
                {
                    type: 'INPUT',
                    name: 'relatedUserIds',
                    placeholder: 'relatedUserId'
                },
                {
                    type: 'INPUT',
                    name: 'groupIndexes',
                    placeholder: 'relationshipGroupIndex'
                },
                {
                    type: 'DATE-RANGE',
                    name: 'blockDate'
                },
                {
                    type: 'DATE-RANGE',
                    name: 'establishmentDate'
                }
            ],
            actions: [
                {
                    title: 'addUserRelationship',
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
                    title: 'updateSelectedUserRelationships',
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
                }
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
                        title: 'relationshipGroupIndex',
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
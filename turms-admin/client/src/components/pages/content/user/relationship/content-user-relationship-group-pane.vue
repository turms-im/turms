<template>
    <content-template
        :name="name"
        :record-key="recordKey"
        :query-key="queryKey"
        :url="url"
        :filters="filters"
        :actions="actions"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../../template/content-template.vue';

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
                    name: 'ownerIds',
                    placeholder: 'ownerId'
                },
                {
                    type: 'INPUT',
                    name: 'indexes',
                    placeholder: 'groupIndex'
                },
                {
                    type: 'INPUT',
                    name: 'names',
                    placeholder: 'name',
                    rules: {
                        nonSpace: true
                    }
                },
                {
                    type: 'DATE-RANGE',
                    name: 'creationDate'
                }
            ],
            actions: [
                {
                    title: 'addUserRelationshipGroup',
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
                            label: 'groupIndex',
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
                    title: 'updateSelectedUserRelationshipGroups',
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
                }
            ],
            table: {
                columns: [
                    {
                        key: 'key.ownerId',
                        width: '20%'
                    },
                    {
                        title: 'relationshipGroupIndex',
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
<template>
    <content-template
        :name="name"
        :url="url"
        :deletion="deletion"
        :filters="filters"
        :actions="actions"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../../template/content-template.vue';

export default {
    name: 'content-group-info-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'group-info',
            url: this.$rs.apis.group,
            deletion: {
                refresh: true
            },
            filters: [
                {
                    type: 'INPUT',
                    name: 'ids',
                    placeholder: 'groupId'
                },
                {
                    type: 'INPUT',
                    name: 'typeIds',
                    placeholder: 'groupTypeId'
                },
                {
                    type: 'INPUT',
                    name: 'creatorIds',
                    placeholder: 'creatorId'
                },
                {
                    type: 'INPUT',
                    name: 'ownerIds',
                    placeholder: 'ownerId'
                },
                {
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'isActive',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: 'activeAndInactive'
                        },
                        {
                            id: true,
                            label: 'isActive'
                        },
                        {
                            id: false,
                            label: 'inactive'
                        }]
                    }
                },
                {
                    type: 'DATE-RANGE',
                    name: 'creationDate'
                },
                {
                    type: 'DATE-RANGE',
                    name: 'deletionDate'
                },
                {
                    type: 'DATE-RANGE',
                    name: 'lastUpdatedDate'
                },
                {
                    type: 'DATE-RANGE',
                    name: 'muteEndDate'
                }
            ],
            actions: [
                {
                    title: 'addGroup',
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'typeId',
                            type: 'INPUT',
                            label: 'groupTypeId',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'creatorId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'ownerId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'name',
                            type: 'INPUT'
                        },
                        {
                            id: 'intro',
                            type: 'TEXTAREA',
                            rows: 4
                        },
                        {
                            id: 'announcement',
                            type: 'TEXTAREA',
                            rows: 4
                        },
                        {
                            id: 'minimumScore',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'creationDate',
                            type: 'DATE'
                        },
                        {
                            id: 'deletionDate',
                            type: 'DATE'
                        },
                        {
                            id: 'lastUpdatedDate',
                            type: 'DATE'
                        },
                        {
                            id: 'muteEndDate',
                            type: 'DATE'
                        },
                        {
                            id: 'isActive',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        id: true,
                                        label: 'isActive'
                                    },
                                    {
                                        id: false,
                                        label: 'inactive'
                                    }]
                            }
                        }
                    ]
                },
                {
                    title: 'updateSelectedGroups',
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'typeId',
                            type: 'INPUT',
                            label: 'groupTypeId',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'creatorId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'ownerId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'name',
                            type: 'INPUT'
                        },
                        {
                            id: 'intro',
                            type: 'TEXTAREA',
                            rows: 4
                        },
                        {
                            id: 'announcement',
                            type: 'TEXTAREA',
                            rows: 4
                        },
                        {
                            id: 'minimumScore',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'creationDate',
                            type: 'DATE'
                        },
                        {
                            id: 'deletionDate',
                            type: 'DATE'
                        },
                        {
                            id: 'lastUpdatedDate',
                            type: 'DATE'
                        },
                        {
                            id: 'muteEndDate',
                            type: 'DATE'
                        },
                        {
                            id: 'isActive',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        id: true,
                                        label: 'isActive'
                                    },
                                    {
                                        id: false,
                                        label: 'inactive'
                                    }]
                            }
                        }
                    ]
                }
            ],
            table: {
                columns: [
                    {
                        title: 'groupId',
                        key: 'id',
                        width: '8%'
                    },
                    {
                        title: 'groupTypeId',
                        key: 'typeId',
                        width: '8%'
                    },
                    {
                        key: 'creatorId',
                        width: '8%'
                    },
                    {
                        key: 'ownerId',
                        width: '8%'
                    },
                    {
                        key: 'name',
                        width: '8%'
                    },
                    {
                        key: 'intro',
                        width: '8%'
                    },
                    {
                        key: 'announcement',
                        width: '10%'
                    },
                    {
                        key: 'minimumScore',
                        width: '7.5%'
                    },
                    {
                        key: 'creationDate',
                        width: '7.5%'
                    },
                    {
                        key: 'deletionDate',
                        width: '7.5%'
                    },
                    {
                        key: 'lastUpdatedDate',
                        width: '7.5%'
                    },
                    {
                        key: 'muteEndDate',
                        width: '7.5%'
                    },
                    {
                        key: 'isActive',
                        width: '6%'
                    },
                    {
                        key: 'operation',
                        width: '6%'
                    }
                ]
            }
        };
    }
};
</script>
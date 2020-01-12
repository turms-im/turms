<template>
    <content-template
        :url="url"
        :deletion="deletion"
        :filters="filters"
        :action-groups="actionGroups"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../../template/content-template';

export default {
    name: 'content-group-info-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            url: this.$rs.apis.group,
            deletion: {
                refresh: true
            },
            filters: [
                {
                    type: 'INPUT',
                    model: '',
                    name: 'ids',
                    placeholder: this.$t('groupId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'typeIds',
                    placeholder: this.$t('groupTypeId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'creatorIds',
                    placeholder: this.$t('creatorId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'ownerIds',
                    placeholder: this.$t('ownerId')
                },
                {
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'isActive',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: this.$t('activeAndInactive')
                        },
                        {
                            id: true,
                            label: this.$t('isActive')
                        },
                        {
                            id: false,
                            label: this.$t('inactive')
                        }]
                    }
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'creationDate'
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'deletionDate'
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'muteEndDate'
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('addGroup'),
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            type: 'INPUT',
                            label: this.$t('groupTypeId'),
                            decorator: this.$validator.create('typeId', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('creatorId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('ownerId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('name')
                        },
                        {
                            type: 'TEXTAREA',
                            rows: 4,
                            decorator: this.$validator.create('intro')
                        },
                        {
                            type: 'TEXTAREA',
                            rows: 4,
                            decorator: this.$validator.create('announcement')
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('profilePictureUrl', {isUrl: true, maxNumber: 256})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('minimumScore', {onlyNumber: true})
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('creationDate')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('deletionDate')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('muteEndDate')
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('isActive'),
                            options: {
                                values: [
                                    {
                                        id: true,
                                        label: this.$t('isActive')
                                    },
                                    {
                                        id: false,
                                        label: this.$t('inactive')
                                    }]
                            }
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedGroups'),
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            type: 'INPUT',
                            label: this.$t('groupTypeId'),
                            decorator: this.$validator.create('typeId', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('creatorId', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('ownerId', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('name')
                        },
                        {
                            type: 'TEXTAREA',
                            rows: 4,
                            decorator: this.$validator.create('intro')
                        },
                        {
                            type: 'TEXTAREA',
                            rows: 4,
                            decorator: this.$validator.create('announcement')
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('profilePictureUrl', {isUrl: true, maxNumber: 256})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('minimumScore', {onlyNumber: true})
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('creationDate')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('deletionDate')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('muteEndDate')
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('isActive'),
                            options: {
                                values: [
                                    {
                                        id: true,
                                        label: this.$t('isActive')
                                    },
                                    {
                                        id: false,
                                        label: this.$t('inactive')
                                    }]
                            }
                        }
                    ]
                }]
            ],
            table: {
                columns: [
                    {
                        title: this.$t('groupId'),
                        key: 'id',
                        width: '7.5%'
                    },
                    {
                        title: this.$t('groupTypeId'),
                        key: 'typeId',
                        width: '7.5%'
                    },
                    {
                        key: 'creatorId',
                        width: '7.5%'
                    },
                    {
                        key: 'ownerId',
                        width: '7.5%'
                    },
                    {
                        key: 'name',
                        width: '7.5%'
                    },
                    {
                        key: 'intro',
                        width: '7.5%'
                    },
                    {
                        key: 'announcement',
                        width: '10%'
                    },
                    {
                        key: 'profilePictureUrl',
                        width: '10%'
                    },
                    {
                        key: 'minimumScore',
                        width: '6%'
                    },
                    {
                        key: 'creationDate',
                        width: '6%'
                    },
                    {
                        key: 'deletionDate',
                        width: '6%'
                    },
                    {
                        key: 'muteEndDate',
                        width: '6%'
                    },
                    {
                        title: this.$t('isActive'),
                        key: 'active',
                        width: '6%'
                    },
                    {
                        key: 'operation',
                        width: '5%'
                    }
                ]}
        };
    }
};
</script>
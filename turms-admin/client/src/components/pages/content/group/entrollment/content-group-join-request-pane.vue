<template>
    <content-template
        :name="name"
        :url="url"
        :filters="filters"
        :action-groups="actionGroups"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../../template/content-template';

export default {
    name: 'content-group-join-request-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'group-join-request',
            url: this.$rs.apis.joinRequest,
            filters: [
                {
                    type: 'INPUT',
                    model: '',
                    name: 'ids',
                    placeholder: this.$t('joinRequestId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'groupIds',
                    placeholder: this.$t('groupId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'requesterIds',
                    placeholder: this.$t('requesterId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'responderIds',
                    placeholder: this.$t('responderId')
                },
                {
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'statuses',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: this.$t('allStatuses')
                        },
                        {
                            id: 'PENDING',
                            label: this.$t('pending')
                        },
                        {
                            id: 'ACCEPTED',
                            label: this.$t('accepted')
                        },
                        {
                            id: 'ACCEPTED_WITHOUT_CONFIRM',
                            label: this.$t('acceptedWithoutConfirm')
                        },
                        {
                            id: 'DECLINED',
                            label: this.$t('declined')
                        },
                        {
                            id: 'IGNORED',
                            label: this.$t('ignored')
                        },
                        {
                            id: 'EXPIRED',
                            label: this.$t('expired')
                        },
                        {
                            id: 'CANCELED',
                            label: this.$t('canceled')
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
                    name: 'responseDate'
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'expirationDate'
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('addJoinRequest'),
                    type: 'CREATE',
                    fields: [
                        {
                            id: 'requesterId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'responderId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'groupId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'content',
                            type: 'TEXTAREA',
                            rows: 4,
                            rules: this.$validator.create({required: true})
                        },
                        {
                            id: 'status',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        id: 'PENDING',
                                        label: this.$t('pending')
                                    },
                                    {
                                        id: 'ACCEPTED',
                                        label: this.$t('accepted')
                                    },
                                    {
                                        id: 'ACCEPTED_WITHOUT_CONFIRM',
                                        label: this.$t('acceptedWithoutConfirm')
                                    },
                                    {
                                        id: 'DECLINED',
                                        label: this.$t('declined')
                                    },
                                    {
                                        id: 'IGNORED',
                                        label: this.$t('ignored')
                                    },
                                    {
                                        id: 'EXPIRED',
                                        label: this.$t('expired')
                                    },
                                    {
                                        id: 'CANCELED',
                                        label: this.$t('canceled')
                                    }]
                            }
                        },
                        {
                            id: 'creationDate',
                            type: 'DATE'
                        },
                        {
                            id: 'responseDate',
                            type: 'DATE'
                        },
                        {
                            id: 'expirationDate',
                            type: 'DATE'
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedJoinRequests'),
                    type: 'UPDATE',
                    fields: [
                        {
                            id: 'requesterId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'responderId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'groupId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'content',
                            type: 'TEXTAREA',
                            rows: 4
                        },
                        {
                            id: 'status',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        id: 'PENDING',
                                        label: this.$t('pending')
                                    },
                                    {
                                        id: 'ACCEPTED',
                                        label: this.$t('accepted')
                                    },
                                    {
                                        id: 'ACCEPTED_WITHOUT_CONFIRM',
                                        label: this.$t('acceptedWithoutConfirm')
                                    },
                                    {
                                        id: 'DECLINED',
                                        label: this.$t('declined')
                                    },
                                    {
                                        id: 'IGNORED',
                                        label: this.$t('ignored')
                                    },
                                    {
                                        id: 'EXPIRED',
                                        label: this.$t('expired')
                                    },
                                    {
                                        id: 'CANCELED',
                                        label: this.$t('canceled')
                                    }]
                            }
                        },
                        {
                            id: 'creationDate',
                            type: 'DATE'
                        },
                        {
                            id: 'responseDate',
                            type: 'DATE'
                        },
                        {
                            id: 'expirationDate',
                            type: 'DATE'
                        }
                    ]
                }]
            ],
            table: {
                columns: [
                    {
                        title: this.$t('joinRequestId'),
                        key: 'id',
                        width: '10%'
                    },
                    {
                        key: 'groupId',
                        width: '10%'
                    },
                    {
                        key: 'requesterId',
                        width: '10%'
                    },
                    {
                        key: 'responderId',
                        width: '10%'
                    },
                    {
                        key: 'content',
                        width: '10%'
                    },
                    {
                        key: 'status',
                        width: '10%'
                    },
                    {
                        key: 'creationDate',
                        width: '10%'
                    },
                    {
                        key: 'responseDate',
                        width: '10%'
                    },
                    {
                        key: 'expirationDate',
                        width: '10%'
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
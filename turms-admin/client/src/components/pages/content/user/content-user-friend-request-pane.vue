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
import ContentTemplate from '../template/content-template';

export default {
    name: 'content-user-friend-request-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'user-friend-request',
            url: this.$rs.apis.friendRequest,
            filters: [
                {
                    type: 'INPUT',
                    model: '',
                    name: 'ids',
                    placeholder: this.$t('friendRequestId')
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
                    name: 'recipientIds',
                    placeholder: this.$t('recipientId')
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
                    name: 'expirationDate'
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('addFriendRequest'),
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'id',
                            type: 'INPUT',
                            label: this.$t('friendRequestId'),
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'requesterId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'recipientId',
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
                            id: 'reason',
                            type: 'TEXTAREA',
                            label: this.$t('requestReason'),
                            placeholder: this.$t('requestReason'),
                            rows: 4
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
                    title: this.$t('updateSelectedFriendRequests'),
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'requesterId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'recipientId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'content',
                            type: 'TEXTAREA',
                            rows: 4
                        },
                        {
                            id: 'reason',
                            type: 'TEXTAREA',
                            label: this.$t('requestReason'),
                            placeholder: this.$t('requestReason'),
                            rows: 4
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
                        title: this.$t('friendRequestId'),
                        key: 'id',
                        width: '10%'
                    },
                    {
                        key: 'content',
                        width: '10%'
                    },
                    {
                        title: this.$t('requestStatus'),
                        key: 'status',
                        width: '10%'
                    },
                    {
                        title: this.$t('requestReason'),
                        key: 'reason',
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
                        key: 'requesterId',
                        width: '10%'
                    },
                    {
                        key: 'recipientId',
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
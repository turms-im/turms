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
                            type: 'INPUT',
                            label: this.$t('friendRequestId'),
                            decorator: this.$validator.create('id', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('requesterId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('recipientId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'TEXTAREA',
                            rows: 4,
                            decorator: this.$validator.create('content', {required: true})
                        },
                        {
                            type: 'TEXTAREA',
                            label: this.$t('requestReason'),
                            placeholder: this.$t('requestReason'),
                            rows: 4,
                            decorator: this.$validator.create('reason')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('creationDate')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('responseDate')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('expirationDate')
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedFriendRequests'),
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('requesterId', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('recipientId', {onlyNumber: true})
                        },
                        {
                            type: 'TEXTAREA',
                            rows: 4,
                            decorator: this.$validator.create('content')
                        },
                        {
                            type: 'TEXTAREA',
                            label: this.$t('requestReason'),
                            placeholder: this.$t('requestReason'),
                            rows: 4,
                            decorator: this.$validator.create('reason')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('creationDate')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('responseDate')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('expirationDate')
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
                ]}
        };
    }
};
</script>
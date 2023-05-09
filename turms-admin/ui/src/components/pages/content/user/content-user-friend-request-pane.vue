<template>
    <content-template
        :name="name"
        :url="url"
        :filters="filters"
        :actions="actions"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../template/content-template.vue';

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
                    name: 'ids',
                    placeholder: 'friendRequestId'
                },
                {
                    type: 'INPUT',
                    name: 'requesterIds',
                    placeholder: 'requesterId'
                },
                {
                    type: 'INPUT',
                    name: 'recipientIds',
                    placeholder: 'recipientId'
                },
                {
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'statuses',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: 'allStatuses'
                        },
                        {
                            id: 'PENDING',
                            label: 'pending'
                        },
                        {
                            id: 'ACCEPTED',
                            label: 'accepted'
                        },
                        {
                            id: 'ACCEPTED_WITHOUT_CONFIRM',
                            label: 'acceptedWithoutConfirm'
                        },
                        {
                            id: 'DECLINED',
                            label: 'declined'
                        },
                        {
                            id: 'IGNORED',
                            label: 'ignored'
                        },
                        {
                            id: 'EXPIRED',
                            label: 'expired'
                        },
                        {
                            id: 'CANCELED',
                            label: 'canceled'
                        }]
                    }
                },
                {
                    type: 'DATE-RANGE',
                    name: 'creationDate'
                }
            ],
            actions: [
                {
                    title: 'addFriendRequest',
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'id',
                            type: 'INPUT',
                            label: 'friendRequestId',
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
                            label: 'requestReason',
                            placeholder: 'requestReason',
                            rows: 4
                        },
                        {
                            id: 'creationDate',
                            type: 'DATE'
                        },
                        {
                            id: 'responseDate',
                            type: 'DATE'
                        }
                    ]
                },
                {
                    title: 'updateSelectedFriendRequests',
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
                            label:'requestReason',
                            placeholder: 'requestReason',
                            rows: 4
                        },
                        {
                            id: 'creationDate',
                            type: 'DATE'
                        },
                        {
                            id: 'responseDate',
                            type: 'DATE'
                        }
                    ]
                }
            ],
            table: {
                columns: [
                    {
                        title: 'friendRequestId',
                        key: 'id',
                        width: '10%'
                    },
                    {
                        key: 'content',
                        width: '10%'
                    },
                    {
                        title: 'requestStatus',
                        key: 'status',
                        width: '10%'
                    },
                    {
                        title: 'requestReason',
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
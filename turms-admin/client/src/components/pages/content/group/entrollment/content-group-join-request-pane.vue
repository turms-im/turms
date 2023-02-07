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
import ContentTemplate from '../../template/content-template.vue';

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
                    name: 'ids',
                    placeholder: 'joinRequestId'
                },
                {
                    type: 'INPUT',
                    name: 'groupIds',
                    placeholder: 'groupId'
                },
                {
                    type: 'INPUT',
                    name: 'requesterIds',
                    placeholder: 'requesterId'
                },
                {
                    type: 'INPUT',
                    name: 'responderIds',
                    placeholder: 'responderId'
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
                },
                {
                    type: 'DATE-RANGE',
                    name: 'responseDate'
                }
            ],
            actions: [
                {
                    title: 'addJoinRequest',
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
                    title: 'updateSelectedJoinRequests',
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
                        title: 'joinRequestId',
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
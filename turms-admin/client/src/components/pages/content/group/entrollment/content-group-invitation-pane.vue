<template>
    <content-template
        :url="url"
        :filters="filters"
        :action-groups="actionGroups"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../../template/content-template';

export default {
    name: 'content-group-invitation-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            url: this.$rs.apis.groupInvitation,
            filters: [
                {
                    type: 'INPUT',
                    model: '',
                    name: 'ids',
                    placeholder: this.$t('invitationId')
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
                    name: 'inviterIds',
                    placeholder: this.$t('inviterId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'inviteeIds',
                    placeholder: this.$t('inviteeId')
                },
                {
                    type: 'SELECT',
                    decorator: this.$validator.create('status'),
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
                    title: this.$t('addInvitation'),
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            type: 'INPUT',
                            label: this.$t('groupInvitationId'),
                            decorator: this.$validator.create('id', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('inviterId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('inviteeId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('groupId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'TEXTAREA',
                            rows: 4,
                            decorator: this.$validator.create('content', {required: true})
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('status'),
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
                    title: this.$t('updateSelectedInvitations'),
                    type: 'UPDATE',
                    fields: [
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('inviterId', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('inviteeId', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('groupId', {onlyNumber: true})
                        },
                        {
                            type: 'TEXTAREA',
                            rows: 4,
                            decorator: this.$validator.create('content')
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('status'),
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
                        title: this.$t('invitationId'),
                        key: 'id',
                        width: '10%'
                    },
                    {
                        key: 'groupId',
                        width: '10%'
                    },
                    {
                        key: 'inviterId',
                        width: '10%'
                    },
                    {
                        key: 'inviteeId',
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
                ]}
        };
    }
};
</script>
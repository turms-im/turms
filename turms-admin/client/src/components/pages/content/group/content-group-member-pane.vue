<template>
    <content-template
        :record-key="recordKey"
        :query-key="queryKey"
        :url="url"
        :filters="filters"
        :action-groups="actionGroups"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../template/content-template';

export default {
    name: 'content-group-member-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            url: this.$rs.apis.groupMember,
            recordKey: 'key',
            queryKey: 'keys',
            filters: [
                {
                    type: 'INPUT',
                    model: '',
                    name: 'groupIds',
                    placeholder: this.$t('groupId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'userIds',
                    placeholder: this.$t('userId')
                },
                {
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'roles',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: this.$t('allRoles')
                        },
                        {
                            id: 'OWNER',
                            label: this.$t('owner')
                        },
                        {
                            id: 'MANAGER',
                            label: this.$t('manager')
                        },
                        {
                            id: 'MEMBER',
                            label: this.$t('member')
                        },
                        {
                            id: 'GUEST',
                            label: this.$t('guest')
                        },
                        {
                            id: 'ANONYMOUS_GUEST',
                            label: this.$t('anonymousGuest')
                        }]
                    }
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'joinDate'
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'muteEndDate'
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('addGroupMember'),
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('groupId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('userId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('name')
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('role', {required: true}),
                            options: {
                                values: [
                                    {
                                        id: 'OWNER',
                                        label: this.$t('owner')
                                    },
                                    {
                                        id: 'MANAGER',
                                        label: this.$t('manager')
                                    },
                                    {
                                        id: 'MEMBER',
                                        label: this.$t('member')
                                    },
                                    {
                                        id: 'GUEST',
                                        label: this.$t('guest')
                                    },
                                    {
                                        id: 'ANONYMOUS_GUEST',
                                        label: this.$t('anonymousGuest')
                                    }
                                ]
                            }
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('joinDate')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('muteEndDate')
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedGroupMembers'),
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('name')
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('role'),
                            options: {
                                values: [
                                    {
                                        id: 'OWNER',
                                        label: this.$t('owner')
                                    },
                                    {
                                        id: 'MANAGER',
                                        label: this.$t('manager')
                                    },
                                    {
                                        id: 'MEMBER',
                                        label: this.$t('member')
                                    },
                                    {
                                        id: 'GUEST',
                                        label: this.$t('guest')
                                    },
                                    {
                                        id: 'ANONYMOUS_GUEST',
                                        label: this.$t('anonymousGuest')
                                    }
                                ]
                            }
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('joinDate')
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('muteEndDate')
                        }
                    ]
                }]
            ],
            table: {
                columns: [
                    {
                        key: 'key.groupId',
                        width: '15%'
                    },
                    {
                        key: 'key.userId',
                        width: '15%'
                    },
                    {
                        key: 'name',
                        width: '15%'
                    },
                    {
                        key: 'role',
                        width: '15%'
                    },
                    {
                        key: 'joinDate',
                        width: '15%'
                    },
                    {
                        key: 'muteEndDate',
                        width: '15%'
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
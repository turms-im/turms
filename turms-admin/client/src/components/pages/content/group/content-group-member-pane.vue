<template>
    <content-template
        :name="name"
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
            name: 'group-member',
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
                            id: 'groupId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'userId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'name',
                            type: 'INPUT'
                        },
                        {
                            id: 'role',
                            type: 'SELECT',
                            rules: this.$validator.create({required: true}),
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
                            id: 'joinDate',
                            type: 'DATE'
                        },
                        {
                            id: 'muteEndDate',
                            type: 'DATE'
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedGroupMembers'),
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'name',
                            type: 'INPUT'
                        },
                        {
                            id: 'role',
                            type: 'SELECT',
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
                            id: 'joinDate',
                            type: 'DATE'
                        },
                        {
                            id: 'muteEndDate',
                            type: 'DATE'
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
                ]
            }
        };
    }
};
</script>
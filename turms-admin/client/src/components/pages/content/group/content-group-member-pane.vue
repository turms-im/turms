<template>
    <content-template
        :name="name"
        :record-key="recordKey"
        :query-key="queryKey"
        :url="url"
        :filters="filters"
        :actions="actions"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../template/content-template.vue';

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
                    name: 'groupIds',
                    placeholder: 'groupId'
                },
                {
                    type: 'INPUT',
                    name: 'userIds',
                    placeholder: 'userId'
                },
                {
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'roles',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: 'allRoles'
                        },
                        {
                            id: 'OWNER',
                            label: 'owner'
                        },
                        {
                            id: 'MANAGER',
                            label: 'manager'
                        },
                        {
                            id: 'MEMBER',
                            label: 'member'
                        },
                        {
                            id: 'GUEST',
                            label: 'guest'
                        },
                        {
                            id: 'ANONYMOUS_GUEST',
                            label: 'anonymousGuest'
                        }]
                    }
                },
                {
                    type: 'DATE-RANGE',
                    name: 'joinDate'
                },
                {
                    type: 'DATE-RANGE',
                    name: 'muteEndDate'
                }
            ],
            actions: [
                {
                    title: 'addGroupMember',
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
                                        label: 'owner'
                                    },
                                    {
                                        id: 'MANAGER',
                                        label: 'manager'
                                    },
                                    {
                                        id: 'MEMBER',
                                        label: 'member'
                                    },
                                    {
                                        id: 'GUEST',
                                        label: 'guest'
                                    },
                                    {
                                        id: 'ANONYMOUS_GUEST',
                                        label: 'anonymousGuest'
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
                    title: 'updateSelectedGroupMembers',
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
                                        label: 'owner'
                                    },
                                    {
                                        id: 'MANAGER',
                                        label: 'manager'
                                    },
                                    {
                                        id: 'MEMBER',
                                        label: 'member'
                                    },
                                    {
                                        id: 'GUEST',
                                        label: 'guest'
                                    },
                                    {
                                        id: 'ANONYMOUS_GUEST',
                                        label: 'anonymousGuest'
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
                }
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
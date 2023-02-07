<template>
    <content-template
        :name="name"
        :url="url"
        :deletion="deletion"
        :filters="filters"
        :actions="actions"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../template/content-template.vue';

export default {
    name: 'content-user-info-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'user-info',
            url: this.$rs.apis.user,
            deletion: {
                refresh: true
            },
            filters: [
                {
                    type: 'INPUT',
                    name: 'ids',
                    placeholder: 'userId'
                },
                {
                    type: 'DATE-RANGE',
                    name: 'registrationDate'
                },
                {
                    type: 'DATE-RANGE',
                    name: 'deletionDate'
                },
                {
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'isActive',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: 'activeAndInactive'
                        },
                        {
                            id: true,
                            label: 'isActive'
                        },
                        {
                            id: false,
                            label: 'inactive'
                        }]
                    }
                }
            ],
            actions: [
                {
                    title: 'addUser',
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'id',
                            type: 'INPUT',
                            label: 'userId',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'password',
                            type: 'INPUT'
                        },
                        {
                            id: 'name',
                            type: 'INPUT'
                        },
                        {
                            id: 'intro',
                            type: 'TEXTAREA',
                            label: 'userIntro',
                            placeholder: 'userIntro',
                            rows: 4
                        },
                        {
                            id: 'profilePicture',
                            type: 'INPUT'
                        },
                        {
                            id: 'permissionGroupId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'profileAccessStrategy',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        label: 'ALL',
                                        id: 'ALL'
                                    },
                                    {
                                        label: 'ALL_EXCEPT_BLOCKED_USERS',
                                        id: 'ALL_EXCEPT_BLOCKED_USERS'
                                    },
                                    {
                                        label: 'FRIENDS',
                                        id: 'FRIENDS'
                                    }
                                ]
                            }
                        },
                        {
                            id: 'registrationDate',
                            type: 'DATE'
                        },
                        {
                            id: 'isActive',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        id: true,
                                        label: 'isActive'
                                    },
                                    {
                                        id: false,
                                        label: 'inactive'
                                    }]
                            }
                        }
                    ]
                },
                {
                    title: 'updateSelectedUsers',
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'id',
                            type: 'INPUT',
                            label: 'userId',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'password',
                            type: 'INPUT'
                        },
                        {
                            id: 'name',
                            type: 'INPUT'
                        },
                        {
                            id: 'intro',
                            type: 'TEXTAREA',
                            label: 'userIntro',
                            placeholder: 'userIntro',
                            rows: 4
                        },
                        {
                            id: 'profilePicture',
                            type: 'INPUT'
                        },
                        {
                            id: 'permissionGroupId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'profileAccessStrategy',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        label: 'ALL',
                                        id: 'ALL'
                                    },
                                    {
                                        label: 'ALL_EXCEPT_BLOCKED_USERS',
                                        id: 'ALL_EXCEPT_BLOCKED_USERS'
                                    },
                                    {
                                        label: 'FRIENDS',
                                        id: 'FRIENDS'
                                    }
                                ]
                            }
                        },
                        {
                            id: 'registrationDate',
                            type: 'DATE',
                            label: 'registrationDate'
                        },
                        {
                            id: 'isActive',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        id: true,
                                        label: 'isActive'
                                    },
                                    {
                                        id: false,
                                        label: 'inactive'
                                    }]
                            }
                        }
                    ]
                }
            ],
            table: {
                columns: [
                    {
                        title: 'userId',
                        key: 'id',
                        width: '10%'
                    },
                    {
                        key: 'password',
                        width: '10%'
                    },
                    {
                        title: 'username',
                        key: 'name',
                        width: '7.5%'
                    },
                    {
                        key: 'intro',
                        width: '10%'
                    },
                    {
                        key: 'profilePicture',
                        width: '7.5%'
                    },
                    {
                        key: 'profileAccessStrategy',
                        width: '10%'
                    },
                    {
                        key: 'permissionGroupId',
                        width: '7.5%'
                    },
                    {
                        key: 'registrationDate',
                        width: '10%'
                    },
                    {
                        key: 'deletionDate',
                        width: '10%'
                    },
                    {
                        key: 'isActive',
                        width: '7.5%'
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
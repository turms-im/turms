<template>
    <content-template
        :name="name"
        :url="url"
        :deletion="deletion"
        :filters="filters"
        :action-groups="actionGroups"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../template/content-template';

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
                    model: '',
                    name: 'ids',
                    placeholder: this.$t('userId')
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'registrationDate'
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'deletionDate'
                },
                {
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'isActive',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: this.$t('activeAndInactive')
                        },
                        {
                            id: true,
                            label: this.$t('isActive')
                        },
                        {
                            id: false,
                            label: this.$t('inactive')
                        }]
                    }
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('addUser'),
                    type: 'CREATE',
                    fields: [
                        {
                            id: 'id',
                            type: 'INPUT',
                            label: this.$t('userId'),
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
                            label: this.$t('userIntro'),
                            placeholder: this.$t('userIntro'),
                            rows: 4
                        },
                        {
                            id: 'permissionGroupId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'profileAccess',
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
                                        label: this.$t('isActive')
                                    },
                                    {
                                        id: false,
                                        label: this.$t('inactive')
                                    }]
                            }
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedUsers'),
                    type: 'UPDATE',
                    fields: [
                        {
                            id: 'id',
                            type: 'INPUT',
                            label: this.$t('userId'),
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
                            label: this.$t('userIntro'),
                            placeholder: this.$t('userIntro'),
                            rows: 4
                        },
                        {
                            id: 'permissionGroupId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'profileAccess',
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
                            label: this.$t('registrationDate')
                        },
                        {
                            id: 'isActive',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        id: true,
                                        label: this.$t('isActive')
                                    },
                                    {
                                        id: false,
                                        label: this.$t('inactive')
                                    }]
                            }
                        }
                    ]
                }]
            ],
            table: {
                columns: [
                    {
                        title: this.$t('userId'),
                        key: 'id',
                        width: '12.5%'
                    },
                    {
                        key: 'password',
                        width: '10%'
                    },
                    {
                        title: this.$t('username'),
                        key: 'name',
                        width: '10%'
                    },
                    {
                        key: 'intro',
                        width: '10%'
                    },
                    {
                        key: 'profileAccess',
                        width: '10%'
                    },
                    {
                        key: 'permissionGroupId',
                        width: '10%'
                    },
                    {
                        key: 'registrationDate',
                        width: '10%'
                    },
                    {
                        title: this.$t('cancelDate'),
                        key: 'deletionDate',
                        width: '10%'
                    },
                    {
                        title: this.$t('isActive'),
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
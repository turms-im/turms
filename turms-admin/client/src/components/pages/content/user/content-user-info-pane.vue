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
                            type: 'INPUT',
                            label: this.$t('userId'),
                            decorator: this.$validator.create('id', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('password')
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('name')
                        },
                        {
                            type: 'TEXTAREA',
                            label: this.$t('userIntro'),
                            placeholder: this.$t('userIntro'),
                            rows: 4,
                            decorator: this.$validator.create('intro')
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('permissionGroupId', {onlyNumber: true})
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('profileAccess'),
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
                            type: 'DATE',
                            decorator: this.$validator.create('registrationDate')
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('isActive'),
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
                            type: 'INPUT',
                            label: this.$t('userId'),
                            decorator: this.$validator.create('id', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('password')
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('name')
                        },
                        {
                            type: 'TEXTAREA',
                            label: this.$t('userIntro'),
                            placeholder: this.$t('userIntro'),
                            rows: 4,
                            decorator: this.$validator.create('intro')
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('permissionGroupId', {onlyNumber: true})
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('profileAccess'),
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
                            type: 'DATE',
                            label: this.$t('registrationDate'),
                            decorator: this.$validator.create('registrationDate')
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('isActive'),
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
                        key: 'active',
                        width: '7.5%'
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
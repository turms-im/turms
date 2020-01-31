<template>
    <content-template
        :name="name"
        :url="url"
        :action-groups="actionGroups"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../template/content-template';

export default {
    name: 'content-group-type-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'group-type',
            url: this.$rs.apis.groupType,
            actionGroups: [
                [{
                    title: this.$t('addGroupType'),
                    type: 'CREATE',
                    size: 'XL',
                    fields: [
                        {
                            type: 'INPUT',
                            label: this.$t('typeName'),
                            decorator: this.$validator.create('name', {required: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('groupSizeLimit', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'SWITCH',
                            decorator: this.$validator.create('guestSpeakable', {required: true})
                        },
                        {
                            type: 'SWITCH',
                            decorator: this.$validator.create('selfInfoUpdatable', {required: true})
                        },
                        {
                            type: 'SWITCH',
                            decorator: this.$validator.create('enableReadReceipt', {required: true})
                        },
                        {
                            type: 'SWITCH',
                            decorator: this.$validator.create('messageEditable', {required: true})
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('invitationStrategy', {required: true}),
                            options: {
                                values: this.$rs.groupInvitationStrategies.map(name => {
                                    return {
                                        label: name,
                                        id: name
                                    };
                                })
                            }
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('joinStrategy', {required: true}),
                            options: {
                                values: this.$rs.groupJoinStrategies.map(name => {
                                    return {
                                        label: name,
                                        id: name
                                    };
                                })
                            }
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('groupInfoUpdateStrategy', {required: true}),
                            options: {
                                values: this.$rs.groupUpdateStrategies.map(name => {
                                    return {
                                        label: name,
                                        id: name
                                    };
                                })
                            }
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('memberInfoUpdateStrategy', {required: true}),
                            options: {
                                values: this.$rs.groupUpdateStrategies.map(name => {
                                    return {
                                        label: name,
                                        id: name
                                    };
                                })
                            }
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedGroupTypes'),
                    type: 'UPDATE',
                    size: 'XL',
                    fields: [
                        {
                            type: 'INPUT',
                            label: this.$t('typeName'),
                            decorator: this.$validator.create('name')
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('groupSizeLimit', {onlyNumber: true})
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('guestSpeakable'),
                            options: {
                                values: [
                                    {
                                        label: this.$t('yes'),
                                        id: true
                                    },
                                    {
                                        label: this.$t('no'),
                                        id: false
                                    }
                                ]
                            }
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('selfInfoUpdatable'),
                            options: {
                                values: [
                                    {
                                        label: this.$t('yes'),
                                        id: true
                                    },
                                    {
                                        label: this.$t('no'),
                                        id: false
                                    }
                                ]
                            }
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('enableReadReceipt'),
                            options: {
                                values: [
                                    {
                                        label: this.$t('yes'),
                                        id: true
                                    },
                                    {
                                        label: this.$t('no'),
                                        id: false
                                    }
                                ]
                            }
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('messageEditable'),
                            options: {
                                values: [
                                    {
                                        label: this.$t('yes'),
                                        id: true
                                    },
                                    {
                                        label: this.$t('no'),
                                        id: false
                                    }
                                ]
                            }
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('invitationStrategy'),
                            options: {
                                values: this.$rs.groupInvitationStrategies.map(name => {
                                    return {
                                        label: name,
                                        id: name
                                    };
                                })
                            }
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('joinStrategy'),
                            options: {
                                values: this.$rs.groupJoinStrategies.map(name => {
                                    return {
                                        label: name,
                                        id: name
                                    };
                                })
                            }
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('groupInfoUpdateStrategy'),
                            options: {
                                values: this.$rs.groupUpdateStrategies.map(name => {
                                    return {
                                        label: name,
                                        id: name
                                    };
                                })
                            }
                        },
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('memberInfoUpdateStrategy'),
                            options: {
                                values: this.$rs.groupUpdateStrategies.map(name => {
                                    return {
                                        label: name,
                                        id: name
                                    };
                                })
                            }
                        }
                    ]
                }]
            ],
            table: {
                columns: [
                    {
                        title: this.$t('groupTypeId'),
                        key: 'id',
                        width: '10%'
                    },
                    {
                        key: 'name',
                        width: '10%'
                    },
                    {
                        key: 'invitationStrategy',
                        width: '10%'
                    },
                    {
                        key: 'joinStrategy',
                        width: '10%'
                    },
                    {
                        key: 'groupInfoUpdateStrategy',
                        width: '10%'
                    },
                    {
                        key: 'memberInfoUpdateStrategy',
                        width: '10%'
                    },
                    {
                        key: 'guestSpeakable',
                        width: '8%'
                    },
                    {
                        key: 'selfInfoUpdatable',
                        width: '8%'
                    },
                    {
                        key: 'enableReadReceipt',
                        width: '8%'
                    },
                    {
                        key: 'messageEditable',
                        width: '8%'
                    },
                    {
                        key: 'operation',
                        width: '8%'
                    }
                ]}
        };
    }
};
</script>
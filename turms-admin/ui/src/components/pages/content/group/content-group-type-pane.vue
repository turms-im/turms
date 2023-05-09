<template>
    <content-template
        :name="name"
        :url="url"
        :actions="actions"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../template/content-template.vue';

export default {
    name: 'content-group-type-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'group-type',
            url: this.$rs.apis.groupType,
            actions: [
                {
                    title: 'addGroupType',
                    type: 'CREATE',
                    size: 'XL',
                    fields: [
                        {
                            id: 'name',
                            type: 'INPUT',
                            label: 'typeName',
                            rules: this.$validator.create({required: true})
                        },
                        {
                            id: 'groupSizeLimit',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'guestSpeakable',
                            type: 'SWITCH'
                        },
                        {
                            id: 'selfInfoUpdatable',
                            type: 'SWITCH'
                        },
                        {
                            id: 'enableReadReceipt',
                            type: 'SWITCH'
                        },
                        {
                            id: 'messageEditable',
                            type: 'SWITCH'
                        },
                        {
                            id: 'invitationStrategy',
                            type: 'SELECT',
                            rules: this.$validator.create({required: true}),
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
                            id: 'joinStrategy',
                            type: 'SELECT',
                            rules: this.$validator.create({required: true}),
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
                            id: 'groupInfoUpdateStrategy',
                            type: 'SELECT',
                            rules: this.$validator.create({required: true}),
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
                            id: 'memberInfoUpdateStrategy',
                            type: 'SELECT',
                            rules: this.$validator.create({required: true}),
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
                    title: 'updateSelectedGroupTypes',
                    type: 'UPDATE',
                    size: 'XL',
                    fields: [
                        {
                            id: 'name',
                            type: 'INPUT',
                            label: 'typeName'
                        },
                        {
                            id: 'groupSizeLimit',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'guestSpeakable',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        label: 'yes',
                                        id: true
                                    },
                                    {
                                        label: 'no',
                                        id: false
                                    }
                                ]
                            }
                        },
                        {
                            id: 'selfInfoUpdatable',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        label: 'yes',
                                        id: true
                                    },
                                    {
                                        label: 'no',
                                        id: false
                                    }
                                ]
                            }
                        },
                        {
                            id: 'enableReadReceipt',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        label: 'yes',
                                        id: true
                                    },
                                    {
                                        label: 'no',
                                        id: false
                                    }
                                ]
                            }
                        },
                        {
                            id: 'messageEditable',
                            type: 'SELECT',
                            options: {
                                values: [
                                    {
                                        label: 'yes',
                                        id: true
                                    },
                                    {
                                        label: 'no',
                                        id: false
                                    }
                                ]
                            }
                        },
                        {
                            id: 'invitationStrategy',
                            type: 'SELECT',
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
                            id: 'joinStrategy',
                            type: 'SELECT',
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
                            id: 'groupInfoUpdateStrategy',
                            type: 'SELECT',
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
                            id: 'memberInfoUpdateStrategy',
                            type: 'SELECT',
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
                }
            ],
            table: {
                columns: [
                    {
                        title: 'groupTypeId',
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
                ]
            }
        };
    }
};
</script>
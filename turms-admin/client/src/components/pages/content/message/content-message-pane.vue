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
    name: 'content-message-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'message',
            url: this.$rs.apis.message,
            deletion: {
                refresh: true
            },
            filters: [
                {
                    type: 'INPUT',
                    model: '',
                    name: 'ids',
                    placeholder: this.$t('messageId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'senderIds',
                    placeholder: this.$t('senderId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'targetIds',
                    placeholder: this.$t('targetId')
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'deliveryDate'
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'deletionDate'
                },
                {
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'areGroupMessages',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: this.$t('userMessageAndSystemMessage')
                        },
                        {
                            id: false,
                            label: this.$t('privateChatMessage')
                        },
                        {
                            id: true,
                            label: this.$t('groupChatMessage')
                        }]
                    }
                },
                {
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'areSystemMessages',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: this.$t('userMessageAndSystemMessage')
                        },
                        {
                            id: false,
                            label: this.$t('userMessage')
                        },
                        {
                            id: true,
                            label: this.$t('systemMessage')
                        }]
                    }
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('addMessage'),
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'isGroupMessage',
                            type: 'SWITCH',
                            rules: this.$validator.create({required: true})
                        },
                        {
                            id: 'isSystemMessage',
                            type: 'SWITCH',
                            rules: this.$validator.create({required: true})
                        },
                        {
                            id: 'senderId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'targetId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'text',
                            type: 'TEXTAREA',
                            rows: 4
                        },
                        {
                            id: 'burnAfter',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'referenceId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedMessages'),
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'isSystemMessage',
                            type: 'SELECT',
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
                            id: 'text',
                            type: 'TEXTAREA',
                            rows: 4
                        },
                        {
                            id: 'burnAfter',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        }
                    ]
                }]
            ],
            table: {
                columns: [
                    {
                        title: this.$t('messageId'),
                        key: 'id',
                        width: '10%'
                    },
                    {
                        key: 'senderId',
                        width: '10%'
                    },
                    {
                        key: 'targetId',
                        width: '10%'
                    },
                    {
                        key: 'isGroupMessage',
                        width: '5%'
                    },
                    {
                        key: 'isSystemMessage',
                        width: '5%'
                    },
                    {
                        key: 'deliveryDate',
                        width: '10%'
                    },
                    {
                        key: 'modificationDate',
                        width: '10%'
                    },
                    {
                        key: 'deletionDate',
                        width: '10%'
                    },
                    {
                        key: 'text',
                        width: '20%'
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
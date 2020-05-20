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
                },
                {
                    type: 'SELECT',
                    model: 'ALL',
                    name: 'deliveryStatuses',
                    options: {
                        base: [{
                            id: 'ALL',
                            label: this.$t('allDeliveryStatuses')
                        },
                        {
                            id: 'READY',
                            label: this.$t('ready')
                        },
                        {
                            id: 'SENDING',
                            label: this.$t('sending')
                        },
                        {
                            id: 'RECEIVED',
                            label: this.$t('received')
                        },
                        {
                            id: 'RECALLING',
                            label: this.$t('recalling')
                        },
                        {
                            id: 'RECALLED',
                            label: this.$t('recalled')
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
                            type: 'SWITCH',
                            decorator: this.$validator.create('isGroupMessage', {required: true})
                        },
                        {
                            type: 'SWITCH',
                            decorator: this.$validator.create('isSystemMessage', {required: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('senderId', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('targetId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'TEXTAREA',
                            rows: 4,
                            decorator: this.$validator.create('text')
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('burnAfter', {onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('referenceId', {onlyNumber: true})
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedMessages'),
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            type: 'SELECT',
                            decorator: this.$validator.create('isSystemMessage'),
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
                            type: 'TEXTAREA',
                            rows: 4,
                            decorator: this.$validator.create('text')
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('burnAfter', {onlyNumber: true})
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
                        width: '10%'
                    },
                    {
                        key: 'isSystemMessage',
                        width: '10%'
                    },
                    {
                        key: 'deliveryDate',
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
                ]}
        };
    }
};
</script>
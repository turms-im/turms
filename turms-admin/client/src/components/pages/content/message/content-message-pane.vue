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
                    placeholder: 'messageId'
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'senderIds',
                    placeholder: 'senderId'
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'targetIds',
                    placeholder: 'targetId'
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
                            label: 'userMessageAndSystemMessage'
                        },
                        {
                            id: false,
                            label: 'privateMessage'
                        },
                        {
                            id: true,
                            label: 'groupMessage'
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
                            label: 'userMessageAndSystemMessage'
                        },
                        {
                            id: false,
                            label: 'userMessage'
                        },
                        {
                            id: true,
                            label: 'systemMessage'
                        }]
                    }
                }
            ],
            actionGroups: [
                [{
                    title: 'addMessage',
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
                    title: 'updateSelectedMessages',
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'isSystemMessage',
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
                        title: 'messageId',
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
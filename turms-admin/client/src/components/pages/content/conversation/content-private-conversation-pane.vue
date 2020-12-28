<template>
    <content-template
        :name="name"
        :url="url"
        :record-key="recordKey"
        :query-key="queryKey"
        :filters="filters"
        :action-groups="actionGroups"
        :table="table"
        :pageable="pageable"
        :transform="transform"
    />
</template>

<script>
import ContentTemplate from '../template/content-template';

export default {
    name: 'content-private-conversation-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'private-conversation',
            url: this.$rs.apis.conversation,
            pageable: false,
            recordKey: 'key',
            queryKey: {
                name: 'privateConversationKeys',
                isCompositeKey: true
            },
            filters: [
                {
                    type: 'INPUT',
                    model: '',
                    name: 'ownerIds',
                    placeholder: this.$t('ownerId')
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('updateSelectedConversationReadDate'),
                    type: 'UPDATE',
                    fields: [
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('readDate')
                        }
                    ]
                }]
            ],
            table: {
                columns: [
                    {
                        key: 'key.ownerId',
                        width: '30%'
                    },
                    {
                        key: 'key.targetId',
                        title: this.$t('recipientId'),
                        width: '30%'
                    },
                    {
                        key: 'readDate',
                        title: this.$t('lastReadDate'),
                        width: '30%'
                    },
                    {
                        key: 'operation',
                        width: '10%'
                    }
                ]},
            transform: (data) => {
                const conversations = data.privateConversations || [];
                return {
                    total: conversations.length,
                    records: conversations
                };
            }
        };
    }
};
</script>
<template>
    <content-template
        :name="name"
        :url="url"
        :record-key="recordKey"
        :query-key="queryKey"
        :filters="filters"
        :actions="actions"
        :table="table"
        :pageable="pageable"
        :transform="transform"
        :use-table-data-for-export="true"
    />
</template>

<script>
import ContentTemplate from '../template/content-template.vue';

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
                    name: 'ownerIds',
                    placeholder: 'ownerId'
                }
            ],
            actions: [
                {
                    title: 'updateSelectedConversationReadDate',
                    type: 'UPDATE',
                    fields: [
                        {
                            id: 'readDate',
                            type: 'DATE'
                        }
                    ]
                }
            ],
            table: {
                columns: [
                    {
                        key: 'key.ownerId',
                        width: '30%'
                    },
                    {
                        key: 'key.targetId',
                        title: 'recipientId',
                        width: '30%'
                    },
                    {
                        key: 'readDate',
                        title: 'lastReadDate',
                        width: '30%'
                    },
                    {
                        key: 'operation',
                        width: '10%'
                    }
                ]
            },
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
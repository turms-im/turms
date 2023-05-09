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
    name: 'content-group-conversation-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'group-conversation',
            url: this.$rs.apis.conversation,
            pageable: false,
            recordKey: 'groupId',
            queryKey: 'groupIds',
            filters: [
                {
                    type: 'INPUT',
                    name: 'groupIds',
                    placeholder: 'groupId'
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
                        key: 'groupId',
                        width: '33%'
                    },
                    {
                        key: 'memberId',
                        title: 'memberId',
                        width: '33%'
                    },
                    {
                        key: 'readDate',
                        title: 'lastReadDate',
                        width: '34%'
                    }
                ]
            },
            transform: (data) => {
                let conversations = data.groupConversations || [];
                conversations = conversations.flatMap(conversation => Object.entries(conversation.memberIdAndReadDate)
                    .map(([memberId, date]) => ({
                        groupId: conversation.groupId,
                        memberId,
                        readDate: new Date(date)
                    })));
                return {
                    total: conversations.length,
                    records: conversations
                };
            }
        };
    }
};
</script>
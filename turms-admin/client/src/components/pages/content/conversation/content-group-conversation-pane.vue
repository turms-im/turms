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
                    model: '',
                    name: 'groupIds',
                    placeholder: this.$t('groupId')
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('updateSelectedConversationReadDate'),
                    type: 'UPDATE',
                    fields: [
                        {
                            id: 'readDate',
                            type: 'DATE'
                        }
                    ]
                }]
            ],
            table: {
                columns: [
                    {
                        key: 'groupId',
                        width: '33%'
                    },
                    {
                        key: 'memberId',
                        title: this.$t('memberId'),
                        width: '33%'
                    },
                    {
                        key: 'readDate',
                        title: this.$t('lastReadDate'),
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
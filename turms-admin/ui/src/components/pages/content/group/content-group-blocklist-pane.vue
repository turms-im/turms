<template>
    <content-template
        :name="name"
        :record-key="recordKey"
        :query-key="queryKey"
        :url="url"
        :filters="filters"
        :actions="actions"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../template/content-template.vue';

export default {
    name: 'content-group-blocklist-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'group-blocklist',
            url: this.$rs.apis.groupBlocklist,
            recordKey: 'key',
            queryKey: 'keys',
            filters: [
                {
                    type: 'INPUT',
                    name: 'groupIds',
                    placeholder: 'groupId'
                },
                {
                    type: 'INPUT',
                    name: 'userIds',
                    placeholder: 'userId'
                },
                {
                    type: 'INPUT',
                    name: 'requesterIds',
                    placeholder: 'requesterId'
                },
                {
                    type: 'DATE-RANGE',
                    name: 'blockDate'
                }
            ],
            actions: [
                {
                    title: 'addBlockedUser',
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'groupId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'userId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'requesterId',
                            type: 'INPUT',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'blockDate',
                            type: 'DATE'
                        }
                    ]
                },
                {
                    title: 'updateSelectedBlockedUsers',
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'requesterId',
                            type: 'INPUT',
                            rules: this.$validator.create({onlyNumber: true})
                        },
                        {
                            id: 'blockDate',
                            type: 'DATE'
                        }
                    ]
                }
            ],
            table: {
                columns: [
                    {
                        key: 'key.groupId',
                        width: '20%'
                    },
                    {
                        key: 'key.userId',
                        width: '20%'
                    },
                    {
                        key: 'blockDate',
                        width: '25%'
                    },
                    {
                        key: 'requesterId',
                        width: '20%'
                    },
                    {
                        key: 'operation',
                        width: '15%'
                    }
                ]
            }
        };
    }
};
</script>
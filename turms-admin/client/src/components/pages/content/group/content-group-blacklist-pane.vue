<template>
    <content-template
        :record-key="recordKey"
        :query-key="queryKey"
        :url="url"
        :filters="filters"
        :action-groups="actionGroups"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../template/content-template';

export default {
    name: 'content-group-blacklist-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            url: this.$rs.apis.groupBlacklist,
            recordKey: 'key',
            queryKey: 'keys',
            filters: [
                {
                    type: 'INPUT',
                    model: '',
                    name: 'groupIds',
                    placeholder: this.$t('groupId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'userIds',
                    placeholder: this.$t('userId')
                },
                {
                    type: 'INPUT',
                    model: '',
                    name: 'requesterIds',
                    placeholder: this.$t('requesterId')
                },
                {
                    type: 'DATE-RANGE',
                    model: [],
                    name: 'blockDate'
                }
            ],
            actionGroups: [
                [{
                    title: this.$t('addBlacklistedUser'),
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('groupId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('userId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('requesterId', {required: true, onlyNumber: true})
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('blockDate')
                        }
                    ]
                },
                {
                    title: this.$t('updateSelectedBlacklistedUsers'),
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            type: 'INPUT',
                            decorator: this.$validator.create('requesterId', {onlyNumber: true})
                        },
                        {
                            type: 'DATE',
                            decorator: this.$validator.create('blockDate')
                        }
                    ]
                }]
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
                        width: '20%'
                    },
                    {
                        key: 'requesterId',
                        width: '20%'
                    },
                    {
                        key: 'operation',
                        width: '20%'
                    }
                ]}
        };
    }
};
</script>
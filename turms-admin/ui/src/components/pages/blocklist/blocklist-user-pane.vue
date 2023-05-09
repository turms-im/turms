<template>
    <content-template
        :name="name"
        :url="url"
        :disable-pagination-if-filter-exists="disablePaginationIfFilterExists"
        :filters="filters"
        :actions="actions"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../content/template/content-template.vue';

export default {
    name: 'blocklist-user-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'user-blocklist',
            url: this.$rs.apis.blocklistUser,
            disablePaginationIfFilterExists: true,
            filters: [
                {
                    type: 'INPUT',
                    name: 'ids',
                    placeholder: 'userId'
                }
            ],
            actions: [
                {
                    title: 'addBlockedUserId',
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'ids',
                            type: 'DYNAMIC-INPUT',
                            label: 'userId',
                            addButtonLabel: 'addBlockedUserId',
                            rules: this.$validator.create({required: true, onlyNumber: true})
                        },
                        {
                            id: 'blockDurationSeconds',
                            type: 'INPUT-NUMBER',
                            value: 1,
                            min: 1,
                            max: 9007199254740992 // 2^53
                        }
                    ]
                }
            ],
            table: {
                columns: [
                    {
                        title: 'userId',
                        key: 'id',
                        width: '40%'
                    },
                    {
                        key: 'blockEndTime',
                        width: '60%'
                    }
                ]
            }
        };
    }
};
</script>
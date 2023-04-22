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
    name: 'blocklist-ip-pane',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'ip-blocklist',
            url: this.$rs.apis.blocklistIp,
            disablePaginationIfFilterExists: true,
            filters: [
                {
                    type: 'INPUT',
                    name: 'ids',
                    placeholder: 'ip',
                    rules: {
                        nonSpace: true
                    }
                }
            ],
            actions: [
                {
                    title: 'addBlockedIp',
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'ids',
                            type: 'DYNAMIC-INPUT',
                            label: 'ip',
                            placeholder: 'ipV4OrV6',
                            addButtonLabel: 'addBlockedIp',
                            rules: this.$validator.create({required: true, isIp: true})
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
                        title: 'ip',
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
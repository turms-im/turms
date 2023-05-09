<template>
    <content-template
        :name="name"
        :url="url"
        :cluster-mode="clusterMode"
        :pageable="pageable"
        :use-table-data-for-export="useTableDataForExport"
        :filters="filters"
        :actions="actions"
        :table="table"
    />
</template>

<script>
import ContentTemplate from '../../content/template/content-template.vue';

export default {
    name: 'flight-recorder',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'flight-recorder',
            url: this.$rs.apis.flightRecordings,
            clusterMode: true,
            pageable: false,
            useTableDataForExport: true,
            filters: [
                {
                    type: 'INPUT',
                    name: 'ids',
                    placeholder: 'recordingId',
                    rules: {
                        nonSpace: true
                    }
                }
            ],
            actions: [
                {
                    title: 'addFlightRecording',
                    type: 'CREATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'durationSeconds',
                            type: 'INPUT-NUMBER',
                            min: 0
                        },
                        {
                            id: 'maxAgeSeconds',
                            type: 'INPUT-NUMBER',
                            min: 0
                        },
                        {
                            id: 'maxSizeBytes',
                            type: 'INPUT-NUMBER',
                            min: 0
                        },
                        {
                            id: 'delaySeconds',
                            type: 'INPUT-NUMBER',
                            value: 0,
                            min: 0
                        },
                        {
                            id: 'description',
                            type: 'INPUT'
                        },
                        {
                            id: 'customSettings',
                            label: 'customSettingsJson',
                            type: 'TEXTAREA'
                        }
                    ]
                }
            ],
            table: {
                columns: [
                    {
                        title: 'recordingId',
                        key: 'id',
                        width: '20%'
                    },
                    {
                        key: 'state',
                        width: '20%'
                    },
                    {
                        key: 'startDate',
                        width: '20%'
                    },
                    {
                        key: 'closeDate',
                        width: '20%'
                    },
                    {
                        key: 'description',
                        width: '20%'
                    }
                ]
            }
        };
    }
};
</script>
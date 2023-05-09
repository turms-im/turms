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
        :transform="transform"
    />
</template>

<script>
import ContentTemplate from '../../content/template/content-template.vue';

export default {
    name: 'plugin',
    components: {
        ContentTemplate
    },
    data() {
        return {
            name: 'plugin',
            url: this.$rs.apis.plugin,
            clusterMode: true,
            pageable: false,
            useTableDataForExport: true,
            filters: [
                {
                    type: 'INPUT',
                    name: 'ids',
                    placeholder: 'pluginId',
                    rules: {
                        nonSpace: true
                    }
                }
            ],
            actions: [
                {
                    title: 'addJavaPlugin',
                    type: 'CREATE',
                    size: 'L',
                    url: this.$rs.apis.pluginJava,
                    fields: [
                        {
                            id: 'save',
                            type: 'SWITCH'
                        },
                        {
                            id: 'files',
                            label: 'javaPluginJarFiles',
                            type: 'UPLOAD',
                            supportedFileTypes: ['.jar', '.zip']
                        }
                    ]
                },
                {
                    title: 'addJavaScriptPlugin',
                    type: 'CREATE',
                    size: 'L',
                    url: this.$rs.apis.pluginJavaScript,
                    fields: [
                        {
                            id: 'scripts',
                            label: 'javaScriptScript',
                            dynamicFieldGroups: true,
                            fields: [
                                {
                                    id: 'fileName',
                                    type: 'INPUT',
                                    rules: this.$validator.create({required: true, noBlank: true, maxNumber: 32})
                                },
                                {
                                    id: 'code',
                                    type: 'TEXTAREA',
                                    rows: 12,
                                    rules: this.$validator.create({required: true})
                                }
                            ]
                        }
                    ]
                },
                {
                    title: 'updateSelectedPlugins',
                    type: 'UPDATE',
                    size: 'L',
                    fields: [
                        {
                            id: 'status',
                            type: 'SELECT',
                            value: 'STARTED',
                            rules: this.$validator.create({required: true}),
                            options: {
                                values: [
                                    {
                                        id: 'STARTED',
                                        label: 'start'
                                    },
                                    {
                                        id: 'STOPPED',
                                        label: 'stop'
                                    },
                                    {
                                        id: 'RESUMED',
                                        label: 'resume'
                                    },
                                    {
                                        id: 'PAUSED',
                                        label: 'pause'
                                    }
                                ]
                            }
                        }
                    ]
                }
            ],
            table: {
                columns: [
                    {
                        title: 'nodeId',
                        key: 'nodeId',
                        width: '10%'
                    },
                    {
                        title: 'pluginId',
                        key: 'id',
                        width: '10%'
                    },
                    {
                        key: 'version',
                        width: '10%'
                    },
                    {
                        key: 'provider',
                        width: '10%'
                    },
                    {
                        key: 'license',
                        width: '10%'
                    },
                    {
                        key: 'description',
                        width: '25%'
                    },
                    {
                        title: 'extension',
                        key: 'extensions',
                        width: '25%'
                    }
                ]
            },
            transform(data) {
                for (const record of data) {
                    record.extensions = JSON.stringify(record.extensions);
                }
                return data;
            }
        };
    }
};
</script>
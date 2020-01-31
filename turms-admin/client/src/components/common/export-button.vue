<template>
    <a-button
        :disabled="exporting"
        type="primary"
        @click.stop="exportDate"
    >
        {{ text }}
    </a-button>
</template>
<script>
import exportExcel from '../../utils/excel-export-util';

export default {
    name: 'export-button',
    props: {
        fileName: {
            type: String,
            required: true
        },
        worksheetName: {
            type: String,
            required: true
        },
        headers: {
            type: Array,
            required: true
        },
        rows: {
            type: Array,
            required: true
        },
        text: {
            type: String,
            default: function() {
                return this.$t('exportData');
            }
        }
    },
    data() {
        return {
            exporting: false
        };
    },
    methods: {
        exportDate() {
            if (this.exporting) {
                return;
            }
            this.exporting = true;
            const hide = this.$message.loading(this.$t('exportingData'), 0);
            exportExcel(this.fileName, this.worksheetName, this.headers, this.rows)
                .then(() => {
                    this.$message.success(this.$t('exportSuccessfully'));
                })
                .catch(() => {
                    this.$message.error(this.$t('failedToExport'));
                })
                .finally(() => {
                    setTimeout(hide);
                    this.exporting = false;
                });
        }
    }
};
</script>
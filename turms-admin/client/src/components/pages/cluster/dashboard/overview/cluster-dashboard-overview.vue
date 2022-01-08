<template>
    <div
        class="cluster-dashboard-overview"
    >
        <a-table
            class="cluster-dashboard-overview__table turms-card"
            :columns="columns"
            :data-source="members"
            :pagination="false"
            :scroll="{ x: 2400, y: 500 }"
            bordered
            size="small"
        >
            <template
                v-for="column in columns"
                #[column.dataIndex]="{ text, record }"
            >
                <template v-if="column.dataIndex === 'nodeId'">
                    <a
                        :key="column.dataIndex"
                        @click="selectNodeId(text)"
                    >
                        {{ text }}
                    </a>
                </template>
                <template v-else-if="column.dataIndex === 'healthy'">
                    <span :key="column.dataIndex">
                        <icon
                            class="cluster-dashboard-overview__health-status-icon"
                            :type="record.status.healthy ? 'check-circle' : 'close-circle'"
                        />
                        <span> {{ $t(record.status.healthy ? 'healthy' : 'unhealthy') }}</span>
                    </span>
                </template>
                <template v-else-if="column.dataIndex === 'resources'">
                    <table
                        :key="column.dataIndex"
                        class="cluster-dashboard-overview__metrics-container"
                    >
                        <tr
                            v-for="metric in (record.resources || [])"
                            :key="metric.title"
                            class="cluster-dashboard-overview__metric"
                        >
                            <th class="cluster-dashboard-overview__metric-name">
                                {{ $t(metric.title) }}
                            </th>
                            <td>
                                {{
                                    `${metric.used?.toFixed(2) ?? '-'} / ${metric.max?.toFixed(2) ?? '-'} ${metric.unit}`
                                }}
                            </td>
                            <td>
                                <progress-bar :progress="metric.usedPercentage" />
                            </td>
                            <td> {{ `${metric.usedPercentage?.toFixed(2) ?? '-'}%` }}</td>
                        </tr>
                    </table>
                </template>
                <template v-else>
                    <span :key="column.dataIndex">{{ text }}</span>
                </template>
            </template>
        </a-table>
    </div>
</template>

<script>
import icon from '../../../../common/icon';
import COLUMNS from './default-overview-table-columns';
import ProgressBar from '../progress-bar';

export default {
    name: 'cluster-dashboard-overview',
    components: {
        icon,
        ProgressBar
    },
    props: {
        members: {
            type: Array,
            required: true
        }
    },
    computed: {
        columns() {
            return COLUMNS.map(column => {
                column = {
                    ...column,
                    title: this.$t(column.title || column.dataIndex),
                    sorter: this.members.length > 1 ? this.$util.sort : null,
                    // To ensure boolean values can display
                    slots: {customRender: column.dataIndex}
                };
                if (column.needFilters) {
                    const filters = this.members.flatMap(item => {
                        const value = item[column.dataIndex];
                        if (value == null) {
                            return [];
                        }
                        if (typeof value === 'boolean') {
                            return {
                                text: this.$t(value ? 'yes' : 'no'),
                                value
                            };
                        }
                        return {
                            text: value,
                            value
                        };
                    });
                    if (filters.length > 1) {
                        column.filters = filters;
                    }
                }
                return column;
            });
        }
    },
    methods: {
        selectNodeId(nodeId) {
            this.$emit('selectNodeId', nodeId);
        }
    }
};
</script>

<style lang="scss">
.cluster-dashboard-overview {
    &__health-status-icon {
        margin-right: 4px;
    }

    &__metrics-container {
        width: 370px !important;
        min-width: inherit !important;

        // Don't use "table-layout: auto !important;"
        // because it may have different padding on different browsers
        table-layout: fixed;

        tr th {
            width: 64px;
        }

        tr td:nth-child(2) {
            width: 135px;
        }

        tr td:nth-child(3) {
            width: 110px;
        }

        tr td:nth-child(4) {
            width: 60px;
        }
    }

    &__metric-name {
        font-weight: 600;
    }
}
</style>
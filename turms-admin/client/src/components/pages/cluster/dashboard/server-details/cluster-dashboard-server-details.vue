<template>
    <skeleton v-if="!metrics" />
    <div
        v-else
        class="cluster-dashboard-server-details"
    >
        <a-row :gutter="16">
            <a-col :span="6">
                <a-card
                    :title="$t('details')"
                    size="small"
                    class="turms-card"
                >
                    <div class="cluster-dashboard-server-details__metrics">
                        <div
                            v-for="item in detailItems"
                            :key="item.nodeId"
                            class="cluster-dashboard-server-details__metric-item"
                        >
                            <div class="cluster-dashboard-server-details__metric-item-title">
                                {{ item.name }}
                            </div>
                            <div>
                                {{ item.value }}
                            </div>
                        </div>
                    </div>
                </a-card>
            </a-col>
            <a-col :span="18">
                <a-card
                    :title="$t('metrics')"
                    size="small"
                    class="cluster-dashboard-server-details__chart turms-card"
                >
                    <template #extra>
                        <a-tree-select
                            v-model:value="selectedMetrics"
                            class="cluster-dashboard-server-details__metrics-select"
                            dropdown-class-name="cluster-dashboard-server-details__metrics-select-dropdown"
                            :tree-data="metricsTree"
                            tree-checkable
                            allow-clear
                            :placeholder="$t('metrics')"
                            :max-tag-count="1"
                        />
                    </template>
                    <a-card
                        v-for="chartData in chartDataList"
                        :key="chartData.group"
                        :title="$t(chartData.group)"
                        class="cluster-dashboard-server-details__chart-metrics"
                    >
                        <area-chart
                            :data="chartData.metrics"
                            :scale="chartData.scale"
                            position="timestamp*value"
                        />
                    </a-card>
                </a-card>
            </a-col>
        </a-row>
    </div>
</template>

<script>
import AreaChart from '../../../../common/chart-area.vue';
import Skeleton from '../../../../common/skeleton.vue';
import METRICS from '../metrics';
import SERVER_DETAILS_ITEMS from './server-details-items';

const DATA_FORMATTER = {
    'bytes': value => `${(value / 1024 / 1024 / 1024).toFixed(2)}GiB`,
    '‱': value => `${(value * 100).toFixed(2)}%`
};

export default {
    name: 'cluster-dashboard-server-details',
    components: {
        AreaChart,
        Skeleton
    },
    props: {
        nodeInfo: {
            type: Object,
            required: true
        },
        metrics: {
            type: Array,
            default: null
        }
    },
    data: function () {
        const selectedMetrics = METRICS.commonMetricsGroups[0].groups
            .flatMap(group => group.metrics);
        return {
            selectedMetrics
        };
    },
    computed: {
        detailItems() {
            return SERVER_DETAILS_ITEMS.flatMap(item => {
                const value = item.formatter?.(this.nodeInfo) ?? this.nodeInfo[item.dataIndex];
                if (value == null) {
                    return [];
                }
                return ({
                    name: this.$t(item.title || item.dataIndex),
                    value: value
                });
            });
        },
        chartDataList() {
            const list = this.metrics || [];
            if (!list.length || !this.selectedMetrics.length) {
                return [];
            }
            const groupMetrics = {};
            // We loop over "this.metrics" instead of "this.selectedGroups"
            // for better performance when "this.metrics" is large
            list.forEach(({timestamp, metrics}) => {
                timestamp = this.$date(timestamp).format('HH:mm:ss');
                this.selectedGroups.forEach(({group, metrics: selectedMetrics}) => {
                    const metric = metrics.find(record => selectedMetrics.includes(record.name));
                    if (!metric) {
                        return;
                    }
                    const data = groupMetrics[group] || {};
                    groupMetrics[group] = data;
                    const metricList = data.metrics || [];
                    const measurements = this.parseMeasurements(metric.name, timestamp, metric.measurements);
                    data.metrics = metricList.concat(measurements);
                    if (!data.scale) {
                        data.scale = {
                            timestamp: {
                                range: [0, 1]
                            },
                            value: {
                                nice: true,
                                formatter: (val) => DATA_FORMATTER[metric.baseUnit]?.(val) ?? (metric.baseUnit ? `${val} ${metric.baseUnit}` : val)
                            }
                        };
                    }
                });
            });
            return Object.entries(groupMetrics)
                .map(([group, {metrics, scale}]) => ({ group, metrics, scale }));
        },
        metaMetrics() {
            const specificMetrics = this.nodeInfo.nodeType.toUpperCase() === 'GATEWAY'
                ? METRICS.turmsGatewayMetricsGroups
                : METRICS.turmsServiceMetricsGroups;
            return [... METRICS.commonMetricsGroups, ... specificMetrics];
        },
        metricsTree() {
            return this.parseMetricsTree(this.metaMetrics);
        },
        metricsGroups() {
            return this.parseMetricsGroups(this.metaMetrics);
        },
        selectedGroups() {
            const groups = this.selectedMetrics.reduce((result, metric) => {
                const group = this.metricsGroups[metric];
                const list = result[group] || [];
                list.push(metric);
                result[group] = list;
                return result;
            }, {});
            return Object.entries(groups)
                .map(([group, metrics]) => ({ group, metrics }));
        }
    },
    methods: {
        parseMetricsTree(metrics) {
            if (metrics instanceof Array || metrics.groups) {
                const groups = metrics.groups || metrics;
                return groups.map(group => {
                    const children = this.parseMetricsTree(group);
                    return {
                        title: this.$t(group.name || group.id),
                        value: group.id,
                        key: group.id,
                        children: children instanceof Array ? children : [ children ]
                    };
                });
            }
            return metrics.metrics.map(metric => ({
                title: metric,
                value: metric,
                key: metric
            }));
        },
        parseMetricsGroups(metrics, result = {}) {
            if (metrics instanceof Array || metrics.groups) {
                const groups = metrics.groups || metrics;
                groups.forEach(group => this.parseMetricsGroups(group, result));
            } else {
                metrics.metrics.forEach(metric => result[metric] = metrics.id);
            }
            return result;
        },
        parseMeasurements(metricsName, timestamp, measurements) {
            return measurements.flatMap(measurement => {
                let name = metricsName;
                if (measurement.tags?.length) {
                    name += `[${measurement.tags.join(',')}]`;
                }
                const entries = Object.entries(measurement.measurements);
                return entries.flatMap(([key, value]) => {
                    let newName = name;
                    if (entries.length > 1) {
                        newName += `:${key}`;
                    }
                    return {
                        timestamp,
                        type: newName,
                        value: value.toNumber?.() ?? value
                    };
                });
            });
        }
    }
};
</script>

<style lang="scss">
.cluster-dashboard-server-details {
    &__metric-item {
        margin-bottom: 12px !important;

        &-title {
            font-weight: 600;
        }
    }

    &__metrics-select {
        width: 300px;
        max-width: 300px;

        &-dropdown {
            max-height: 50vh !important;
        }
    }

    &__chart-metrics {
        margin-bottom: 12px;
    }
}

</style>
<template>
    <skeleton v-if="!initialized" />
    <div
        v-else
        class="cluster-dashboard-overview"
    >
        <div>
            <div class="cluster-dashboard-overview__header">
                <div class="cluster-dashboard-overview__header-card-container">
                    <div
                        v-for="card in overviewCards"
                        :key="card.title"
                        class="cluster-dashboard-overview__header-card"
                    >
                        <div class="cluster-dashboard-overview__header-card-title">
                            {{ card.title }}
                            <a-tooltip
                                v-if="card.info"
                                placement="top"
                                :title="card.info"
                            >
                                <span class="cluster-config-item__info">
                                    <icon type="question-circle" />
                                </span>
                            </a-tooltip>
                        </div>
                        <div class="cluster-dashboard-overview__header-card-content">
                            {{ card.content }}
                        </div>
                    </div>
                </div>
                <div class="cluster-dashboard-overview__header-last-updated-time">
                    <div class="cluster-dashboard-overview__header-card-title">
                        {{ $t('lastUpdatedTime') }}
                    </div>
                    <div class="cluster-dashboard-overview__header-card-content">
                        {{ lastUpdatedTime }}
                    </div>
                </div>
            </div>
        </div>
        <a-table
            class="cluster-dashboard-overview__table"
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
                            v-for="metric in (record?.resources || [])"
                            :key="metric.title"
                            class="cluster-dashboard-overview__metric"
                        >
                            <th class="cluster-dashboard-overview__metric-name">
                                {{ metric.title }}
                            </th>
                            <td>{{ `${metric.used?.toFixed(2) ?? '-'} / ${metric.max?.toFixed(2) ?? '-'} ${metric.unit}` }}</td>
                            <td> <progress-bar :progress="metric.usedPercentage" /> </td>
                            <td> {{ `${metric.usedPercentage?.toFixed(2) ?? '-'}%` }} </td>
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
import icon from '../../../common/icon';
import Skeleton from '../../../common/skeleton';
import COLUMNS from './default-overview-table-columns';
import ProgressBar from './progress-bar';

const UPDATE_METRICS_INTERVAL = 30 * 1000;
const METRICS_NAMES = {
    SYSTEM_CPU_USAGE: 'system.cpu.usage',
    SYSTEM_MEMORY_TOTAL: 'system.memory.total',
    SYSTEM_MEMORY_FREE: 'system.memory.free',
    STORAGE_TOTAL: 'disk.total',
    STORAGE_FREE: 'disk.free'
};

export default {
    name: 'cluster-dashboard-overview',
    components: {
        icon,
        ProgressBar,
        Skeleton
    },
    data() {
        return {
            members: [],
            initialized: false,
            updating: false,
            lastUpdatedTime: '',
            onlineUsers: 0
        };
    },
    computed: {
        isLoggedIn() {
            return this.$store.getters.admin;
        },
        overviewCards() {
            let gatewayNumber = 0;
            let serviceNumber = 0;
            this.members.forEach(member => {
                if (member.nodeType.toUpperCase() === 'GATEWAY') {
                    gatewayNumber++;
                } else {
                    serviceNumber++;
                }
            });
            return [{
                title: this.$t('nodeNumber'),
                content: `${gatewayNumber} / ${serviceNumber}`,
                info: 'turms-gateway / turms-service'
            },
            {
                title: this.$t('onlineUserNumber'),
                content: this.onlineUsers
            }];
        },
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
    watch: {
        isLoggedIn(val) {
            if (val) {
                this.updateDashboard();
            }
        }
    },
    unmounted() {
        clearInterval(this.timer);
    },
    mounted() {
        this.timer = setInterval(() => {
            if (!this.isLoggedIn) {
                return;
            }
            this.updateDashboard();
        }, UPDATE_METRICS_INTERVAL);
        this.updateDashboard();
    },
    methods: {
        // eslint-disable-next-line no-unused-vars
        selectNodeId(nodeId) {
            // TODO: Enable once we finish the dashboard server details page
            // this.selectedNodeId = nodeId;
            // this.$emit('selectNodeId', this.selectedNodeId);
            this.$message.info('TODO: The server details page will be released later');
        },
        updateDashboard() {
            if (!this.isLoggedIn || this.updating) {
                return;
            }
            this.updating = true;
            this.lastUpdatedTime = this.$moment().format('YYYY-MM-DD HH:mm:ss');
            Promise.all([this.updateOnlineUsers(), this.updateMembersInfo()])
                .then(() => this.initialized = true)
                .catch(error => this.$error(this.$t('failedToRefreshData'), error))
                .finally(() => this.updating = false);
        },
        updateOnlineUsers() {
            return this.$http.get(this.$rs.apis.userOnline)
                .then(response => this.onlineUsers = response.data.total || 0);
        },
        updateMembersInfo() {
            return this.$http.get(this.$rs.apis.clusterMember)
                .then(async response => {
                    this.members = JSON.parse(JSON.stringify(response.data.data))
                        .map(item => ({
                            ...item,
                            ...item.key,
                            ...item.status,
                            key: JSON.stringify(item.key), // as the row key
                            memberAddress: `${item.memberHost}:${item.memberPort}`
                        }));
                    const metricsRequests = this.members
                        .map(member => this.fetchResourceUtilization(member));
                    try {
                        await Promise.allSettled(metricsRequests);
                        // eslint-disable-next-line no-empty
                    } catch (e) {
                    }
                    this.$emit('updateMembersInfo', this.members);
                });
        },
        fetchResourceUtilization(member) {
            const params = new URLSearchParams();
            params.append('names', METRICS_NAMES.SYSTEM_CPU_USAGE);
            params.append('names', METRICS_NAMES.SYSTEM_MEMORY_TOTAL);
            params.append('names', METRICS_NAMES.SYSTEM_MEMORY_FREE);
            params.append('names', METRICS_NAMES.STORAGE_TOTAL);
            params.append('names', METRICS_NAMES.STORAGE_FREE);
            return this.$http.get(`${member.adminApiAddress}${this.$rs.apis.metrics}`, {params})
                .then(response => {
                    const data = response.data.data || [];
                    const metrics = data
                        .reduce((pre, metric) => {
                            const value = metric.measurements[0]?.measurements?.value;
                            if (value >= 0) {
                                pre[metric.name] = value;
                            }
                            return pre;
                        }, {});
                    let cpuUsage = metrics[METRICS_NAMES.SYSTEM_CPU_USAGE] * 100;
                    cpuUsage = isNaN(cpuUsage) ? null : cpuUsage;
                    const totalMemory = metrics[METRICS_NAMES.SYSTEM_MEMORY_TOTAL];
                    const freeMemory = metrics[METRICS_NAMES.SYSTEM_MEMORY_FREE];
                    const usedMemory = totalMemory - freeMemory;
                    const totalStorage = metrics[METRICS_NAMES.STORAGE_TOTAL];
                    const freeStorage = metrics[METRICS_NAMES.STORAGE_FREE];
                    const usedStorage = totalStorage - freeStorage;
                    member.resources = [{
                        title: 'CPU',
                        max: 100,
                        used: cpuUsage,
                        usedPercentage: cpuUsage,
                        unit: '%'
                    }, {
                        title: 'Memory',
                        max: this.formatBytesToGiB(totalMemory),
                        used: this.formatBytesToGiB(usedMemory),
                        usedPercentage: totalMemory ? usedMemory / totalMemory * 100 : null,
                        unit: 'GiB'
                    }, {
                        title: 'Storage',
                        max: this.formatBytesToGiB(totalStorage),
                        used: this.formatBytesToGiB(usedStorage),
                        usedPercentage: totalStorage ? usedStorage / totalStorage * 100 : null,
                        unit: 'GiB'
                    }];
                });
        },
        formatBytesToGiB(bytes) {
            return bytes / 1024 / 1024 / 1024;
        }
    }
};
</script>

<style lang="scss">
.cluster-dashboard-overview {
    &__table {
        margin-top: 12px;
        box-shadow: rgb(0 0 0 / 8%) 0 1px 4px;
    }

    &__header {
        display: flex;
        height: 82px;
        justify-content: space-between;
        // Disable the default padding of the container
        // TODO: use variables
        margin-top: -24px;
        margin-left: -24px;
        margin-right: -24px;
        padding: 12px 32px;
        background: #FFF;
        box-shadow: rgb(0 0 0 / 8%) 0 1px 4px;
    }

    &__header-card-container {
        display: flex;
    }

    &__header-card {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-between;
        margin-right: 64px;

        &-title {
            font-size: 16px;
            color: #697b85;
        }

        &-content {
            font-size: 18px;
            font-weight: 600;
        }
    }

    &__header-last-updated-time {
        display: flex;
        flex-direction: column;
        align-items: end;
        justify-content: space-between;

        .cluster-dashboard-overview__header-card-content {
            font-size: 16px;
        }
    }

    &__health-status-icon {
        margin-right: 4px;
    }

    &__metrics-container {
        // Don't use "table-layout: auto !important;"
        // because it may have different padding on different browsers
        table-layout: fixed;
        width: 370px !important;
        min-width: inherit !important;
        tr th { width: 64px; }
        tr td:nth-child(2) { width: 135px; }
        tr td:nth-child(3) { width: 110px; }
        tr td:nth-child(4) { width: 60px; }
    }

    &__metric-name {
        font-weight: 600;
    }
}
</style>
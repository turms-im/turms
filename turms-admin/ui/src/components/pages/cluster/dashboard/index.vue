<template>
    <skeleton v-if="!initialized" />
    <template v-else>
        <dashboard-header
            :last-updated-time="lastUpdatedTime"
            :members="members"
            :online-users="onlineUsers"
        />
        <dashboard-actions
            :node-id="selectedNodeId"
            :exporting="exporting"
            :refreshing="updating"
            @export="exportMembers"
            @goBack="goBack"
            @refresh="updateDashboard"
        />
        <dashboard-server-details
            v-if="selectedNodeInfo"
            :node-info="selectedNodeInfo"
            :metrics="metrics[selectedNodeId]"
            @goBack="goBack"
        />
        <dashboard-overview
            v-else
            :members="members"
            @selectNodeId="selectNodeId"
            @updateMembersInfo="updateMembersInfo"
            @generateHeapDump="generateHeapDump"
            @generateThreadDump="generateThreadDump"
        />
    </template>
</template>

<script>
import exportExcel from '../../../../utils/excel-export-util';
import Skeleton from '../../../common/skeleton.vue';
import DashboardHeader from './cluster-dashboard-header.vue';
import DashboardActions from './cluster-dashboard-actions.vue';
import DashboardOverview from './overview/cluster-dashboard-overview.vue';
import DashboardServerDetails from './server-details/cluster-dashboard-server-details.vue';
import SERVER_DETAILS_ITEMS from './server-details/server-details-items';
import METRICS from './metrics';

const UPDATE_DASHBOARD_INTERVAL = 30 * 1000;
const UPDATE_METRICS_INTERVAL = 10 * 1000;

const getMetricNames = groups => groups.flatMap(group => group.metrics || [... group.groups.flatMap(g => g.metrics)]);
const COMMON_METRICS = getMetricNames(METRICS.commonMetricsGroups);
const ALL_METRICS_FOR_GATEWAY = [... COMMON_METRICS, ... getMetricNames(METRICS.turmsGatewayMetricsGroups)];
const ALL_METRICS_FOR_SERVICE = [... COMMON_METRICS, ... getMetricNames(METRICS.turmsServiceMetricsGroups)];

export default {
    name: 'cluster-dashboard',
    components: {
        DashboardHeader,
        DashboardActions,
        DashboardOverview,
        DashboardServerDetails,
        Skeleton
    },
    data() {
        return {
            members: [],
            // member ID -> metrics
            metrics: {},
            selectedNodeId: '',
            initialized: false,
            exporting: false,
            updating: false,
            lastUpdatedTime: '',
            onlineUsers: 0
        };
    },
    computed: {
        isLoggedIn() {
            return this.$store.getters.admin;
        },
        selectedNodeInfo() {
            return this.members.find(member => member.nodeId === this.selectedNodeId);
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
        clearInterval(this.updateDashboardTimer);
        clearInterval(this.updateMetricsTimer);
    },
    mounted() {
        this.updateDashboardTimer = setInterval(() => this.isLoggedIn && this.updateDashboard(),
            UPDATE_DASHBOARD_INTERVAL);
        this.updateDashboard();
    },
    methods: {
        goBack() {
            this.selectedNodeId = null;
        },
        exportMembers() {
            // TODO: Support exporting metrics
            if (this.exporting) {
                return;
            }
            const headers = SERVER_DETAILS_ITEMS
                .map(item => ({
                    header: this.$t(item.title || item.dataIndex),
                    key: item.dataIndex,
                    width: 15
                }));
            const rows = this.members;
            this.exporting = true;
            const hide = this.$message.loading(this.$t('exportingDataAsExcel'), 0);
            exportExcel('turms-nodes', this.$t('overview'), headers, rows)
                .then(() => {
                    this.$message.success(this.$t('exportSuccessfully'));
                })
                .catch(error => {
                    this.$error(this.$t('failedToExport'), error);
                })
                .finally(() => {
                    setTimeout(hide);
                    this.exporting = false;
                });
        },
        selectNodeId(id) {
            this.selectedNodeId = id;
        },
        updateDashboard() {
            if (!this.isLoggedIn || this.updating) {
                return;
            }
            this.updating = true;
            this.lastUpdatedTime = this.$date().format('YYYY-MM-DD HH:mm:ss');
            Promise.all([this.updateOnlineUsers(), this.updateMembersInfo()])
                .then(() => {
                    this.initialized = true;
                    if (!this.updateMetricsTimer) {
                        this.updateMetrics();
                        this.updateMetricsTimer = setInterval(() => this.isLoggedIn && this.updateMetrics(),
                            UPDATE_METRICS_INTERVAL);
                    }
                })
                .catch(error => {
                    this.$error(this.$t('failedToRefreshData'), error);
                })
                .finally(() => this.updating = false);
        },
        updateMetrics() {
            this.members.forEach(member => {
                const metrics = this.isMemberGateway(member)
                    ? ALL_METRICS_FOR_GATEWAY
                    : ALL_METRICS_FOR_SERVICE;
                this.$apis.fetchMemberMetrics(member, metrics)
                    .then(res => {
                        const list = [...this.metrics[member.nodeId] || [], {
                            timestamp: res.timestamp,
                            metrics: res.data
                        }];
                        this.metrics[member.nodeId] = list
                            .slice(-100)
                            .sort((m1, m2) => m1.timestamp - m2.timestamp);
                    });
            });
        },
        updateOnlineUsers() {
            return this.$apis.fetchOnlineUsers()
                .then(count => this.onlineUsers = count);
        },
        updateMembersInfo() {
            return this.$apis.fetchMembersInfo(true)
                .then(members => this.members = members);
        },
        generateHeapDump(member) {
            if (this.generatingHeapDump) {
                return;
            }
            this.generatingHeapDump = true;
            this.$loading({
                promise: this.$apis.fetchHeapDump(member),
                loading: 'generatingHeapDump',
                success: 'generatedHeapDump',
                error: 'failedToGenerateHeapDump',
                successCb: data => this.$fs.saveAs({
                    fileName: `Heap Dump - ${member.nodeId}.hprof`,
                    data
                }),
                finallyCb: () => this.generatingHeapDump = false
            });
        },
        generateThreadDump(member) {
            if (this.generatingThreadDump) {
                return;
            }
            this.generatingThreadDump = true;
            this.$loading({
                promise: this.$apis.fetchThreadDump(member),
                loading: 'generatingThreadDump',
                success: 'generatedThreadDump',
                error: 'failedToGenerateThreadDump',
                successCb: data => this.$fs.saveAs({
                    fileName: `Thread Dump - ${member.nodeId}`,
                    data
                }),
                finallyCb: () => this.generatingThreadDump = false
            });
        },
        isMemberGateway(member) {
            return member.nodeType.toUpperCase() === 'GATEWAY';
        }
    }
};
</script>

<style lang="scss">
.cluster-dashboard-actions {
    margin-top: 12px;
    margin-bottom: 12px;
}

</style>
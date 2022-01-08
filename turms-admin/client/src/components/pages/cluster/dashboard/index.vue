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
            @goBack="goBack"
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
        />
    </template>
</template>

<script>
import DashboardHeader from './cluster-dashboard-header';
import DashboardActions from './cluster-dashboard-actions';
import DashboardOverview from './overview/cluster-dashboard-overview';
import DashboardServerDetails from './server-details/cluster-dashboard-server-details';
import Skeleton from '../../../common/skeleton';
import ResourceRequestMixin from './resource-request-mixin';
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
    mixins: [ResourceRequestMixin],
    data() {
        return {
            members: [],
            // member ID -> metrics
            metrics: {},
            selectedNodeId: '',
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
        selectNodeId(id) {
            this.selectedNodeId = id;
        },
        updateDashboard() {
            if (!this.isLoggedIn || this.updating) {
                return;
            }
            this.updating = true;
            this.lastUpdatedTime = this.$moment().format('YYYY-MM-DD HH:mm:ss');
            Promise.all([this.updateOnlineUsers(), this.updateMembersInfo()])
                .then(() => {
                    this.initialized = true;
                    if (!this.updateMetricsTimer) {
                        this.updateMetrics();
                        this.updateMetricsTimer = setInterval(() => this.isLoggedIn && this.updateMetrics(),
                            UPDATE_METRICS_INTERVAL);
                    }
                })
                .catch(error => this.$error(this.$t('failedToRefreshData'), error))
                .finally(() => this.updating = false);
        },
        updateMetrics() {
            this.members.forEach(member => {
                const metrics = this.isMemberGateway(member)
                    ? ALL_METRICS_FOR_GATEWAY
                    : ALL_METRICS_FOR_SERVICE;
                this.fetchMemberMetrics(member, metrics)
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
            return this.fetchOnlineUsers()
                .then(count => this.onlineUsers = count);
        },
        updateMembersInfo() {
            return this.fetchMembersInfo()
                .then(members => this.members = members);
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
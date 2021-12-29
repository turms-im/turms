<template>
    <div v-if="selectedNodeInfo">
        <dashboard-server-details
            :node-info="selectedNodeInfo"
            @goBack="goBack"
        />
    </div>
    <div v-else>
        <dashboard-overview
            @selectNodeId="selectNodeId"
            @updateMembersInfo="updateMembersInfo"
        />
    </div>
</template>

<script>
import DashboardOverview from './cluster-dashboard-overview';
import DashboardServerDetails from './cluster-dashboard-server-details';

export default {
    name: 'cluster-dashboard',
    components: {
        DashboardOverview,
        DashboardServerDetails
    },
    layoutContent: {
        useDefault: true
    },
    data() {
        return {
            members: [],
            selectedNodeId: ''
        };
    },
    computed: {
        selectedNodeInfo() {
            return this.members.filter(member => member.key.nodeId === this.selectedNodeId)[0];
        }
    },
    methods: {
        goBack() {
            this.selectedNodeId = null;
        },
        selectNodeId(id) {
            this.selectedNodeId = id;
        },
        updateMembersInfo(members) {
            this.members = members;
        }
    }
};
</script>

<style scoped>

</style>
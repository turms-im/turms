export default [
    // ID
    {
        dataIndex: 'clusterId',
        fixed: 'left'
    },
    {
        dataIndex: 'nodeId',
        fixed: 'left'
    },
    // Status
    {
        dataIndex: 'healthy',
        title: 'healthStatus',
        needFilters: true
    },
    {
        dataIndex: 'active',
        title: 'activeStatus',
        needFilters: true
    },
    {
        dataIndex: 'hasJoinedCluster',
        needFilters: true
    },
    {
        dataIndex: 'lastHeartbeatDate'
    },
    // Resource Utilization
    {
        title: 'resourceUtilization',
        dataIndex: 'resources',
        width: 380
    },
    // Info
    {
        dataIndex: 'nodeType',
        needFilters: true
    },
    {
        dataIndex: 'nodeVersion',
        needFilters: true
    },
    {
        dataIndex: 'zone',
        needFilters: true
    },
    {
        dataIndex: 'registrationDate'
    },
    {
        dataIndex: 'priority'
    },
    {
        dataIndex: 'memberAddress'
    },
    {
        dataIndex: 'adminApiAddress'
    },
    {
        dataIndex: 'tcpAddress'
    },
    {
        dataIndex: 'wsAddress'
    },
    {
        dataIndex: 'udpAddress'
    },
    {
        dataIndex: 'seed',
        needFilters: true
    },
    {
        dataIndex: 'leaderEligible',
        needFilters: true
    },
    {
        dataIndex: 'operation',
        fixed: 'right'
    }
];
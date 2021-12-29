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
        dataIndex: 'metricsApiAddress'
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
    }
];
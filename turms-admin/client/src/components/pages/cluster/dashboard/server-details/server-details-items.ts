export default [
    // ID
    {
        dataIndex: 'clusterId'
    },
    {
        dataIndex: 'nodeId'
    },
    // Status
    {
        dataIndex: 'healthy',
        title: 'healthStatus'
    },
    {
        dataIndex: 'active',
        title: 'activeStatus'
    },
    {
        dataIndex: 'hasJoinedCluster'
    },
    {
        dataIndex: 'lastHeartbeatDate'
    },
    // Info
    {
        dataIndex: 'nodeType'
    },
    {
        dataIndex: 'nodeVersion'
    },
    {
        dataIndex: 'zone'
    },
    {
        dataIndex: 'registrationDate'
    },
    {
        dataIndex: 'priority'
    },
    {
        dataIndex: 'memberAddress',
        formatter: (item) => `${item.memberHost}:${item.memberPort}`
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
        dataIndex: 'seed'
    },
    {
        dataIndex: 'leaderEligible'
    }
];
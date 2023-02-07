export default {
    commonMetricsGroups: [
        {
            id: 'system',
            groups: [
                {
                    id: 'cpu',
                    metrics: [
                        'process.cpu.usage',
                        'system.cpu.usage'
                    ]
                },
                {
                    id: 'memory',
                    metrics: [
                        'system.memory.total',
                        'system.memory.free',
                        'system.memory.swap.total',
                        'system.memory.swap.free'
                    ]
                },
                {
                    id: 'disk',
                    metrics: [
                        'disk.total',
                        'disk.free'
                    ]
                }]
        },
        {
            id: 'jvm',
            groups: [
                {
                    id: 'buffer',
                    metrics: [
                        'jvm.buffer.count',
                        'jvm.buffer.memory.used',
                        'jvm.buffer.total.capacity'
                    ]
                },
                {
                    id: 'class',
                    metrics: [
                        'jvm.classes.loaded',
                        'jvm.classes.unloaded'
                    ]
                },
                {
                    id: 'gcMemory',
                    metrics: [
                        'jvm.gc.live.data.size',
                        'jvm.gc.max.data.size',
                        'jvm.gc.memory.allocated',
                        'jvm.gc.memory.promoted'
                    ]
                },
                {
                    id: 'gcPause',
                    metrics: [
                        'jvm.gc.pause'
                    ]
                },
                {
                    id: 'jvmMemory',
                    metrics: [
                        'jvm.memory.committed',
                        'jvm.memory.max',
                        'jvm.memory.used'
                    ]
                },
                {
                    id: 'jvmThread',
                    metrics: [
                        'jvm.threads.daemon',
                        'jvm.threads.live',
                        'jvm.threads.peak'
                    ]
                }
            ]
        },
        {
            id: 'http',
            name: 'httpAdminApi',
            metrics: [
                'http.server.requests'
            ]
        },
        {
            id: 'rpc',
            metrics: [
                'rpc.request'
            ]
        },
        {
            id: 'clientRequest',
            metrics: [
                'turms.client.request'
            ]
        }],
    turmsServiceMetricsGroups: [{
        id: 'business',
        groups: [
            {
                id: 'user',
                metrics: [
                    'user.registered',
                    'user.deleted'
                ]
            },
            {
                id: 'group',
                metrics: [
                    'group.created',
                    'group.deleted'
                ]
            },
            {
                id: 'message',
                metrics: [
                    'message.sent'
                ]
            }
        ]
    }],
    turmsGatewayMetricsGroups: [{
        id: 'business',
        groups: [
            {
                id: 'user',
                metrics: [
                    'user.online',
                    'user.logged_in'
                ]
            }
        ]
    }]
};
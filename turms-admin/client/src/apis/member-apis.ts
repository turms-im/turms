import type {ComponentCustomProperties} from '@vue/runtime-core';

const RESOURCE_UTILIZATION_METRICS_NAMES = {
    SYSTEM_CPU_USAGE: 'system.cpu.usage',
    SYSTEM_MEMORY_TOTAL: 'system.memory.total',
    SYSTEM_MEMORY_FREE: 'system.memory.free',
    STORAGE_TOTAL: 'disk.total',
    STORAGE_FREE: 'disk.free'
};

const formatBytesToGiB = bytes => bytes / 1024 / 1024 / 1024;

const getMemberAdminBaseUrl = member => new URL(member.adminApiAddress).origin;

interface Api {
    fetchMembersInfo(fetchMetrics?: Array<string>): Promise<any>;

    fetchMemberMetrics(member, metrics): Promise<any>;

    fetchResourceUtilization(member): Promise<void>;

    fetchHeapDump(member): Promise<any>;

    fetchThreadDump(member): Promise<any>;
}

export default {
    fetchMembersInfo(fetchMetrics?: Array<string>): Promise<any> {
        return this.$http.get(this.$rs.apis.clusterMember)
            .then(async response => {
                const members = JSON.parse(JSON.stringify(response.data.data))
                    .map(item => ({
                        ...item,
                        ...item.key,
                        ...item.status,
                        key: JSON.stringify(item.key), // as the row key
                        memberAddress: `${item.memberHost}:${item.memberPort}`,
                        nodeVersion: item.nodeVersion.version
                    }));
                if (fetchMetrics) {
                    const metricsRequests = members
                        .map(member => this.fetchResourceUtilization(member));
                    try {
                        await Promise.allSettled(metricsRequests);
                    } catch (e) {
                        // If we have fetched the member info,
                        // but failed to fetch the metrics,
                        // just display the info without metrics
                        console.error('Failed to fetch metrics', e);
                    }
                }
                return members;
            });
    },
    fetchMemberMetrics(member, metrics) {
        const params = this.$qs.encode({
            'names': metrics
        });
        const baseUrl = getMemberAdminBaseUrl(member);
        return this.$http.get(`${baseUrl}${this.$rs.apis.metrics}?${params}`)
            .then(response => response.data || {});
    },
    fetchResourceUtilization(member) {
        const params = this.$qs.encode({
            'names': [
                RESOURCE_UTILIZATION_METRICS_NAMES.SYSTEM_CPU_USAGE,
                RESOURCE_UTILIZATION_METRICS_NAMES.SYSTEM_MEMORY_TOTAL,
                RESOURCE_UTILIZATION_METRICS_NAMES.SYSTEM_MEMORY_FREE,
                RESOURCE_UTILIZATION_METRICS_NAMES.STORAGE_TOTAL,
                RESOURCE_UTILIZATION_METRICS_NAMES.STORAGE_FREE
            ]
        });
        const baseUrl = getMemberAdminBaseUrl(member);
        return this.$http.get(`${baseUrl}${this.$rs.apis.metrics}?${params}`)
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
                let cpuUsage = metrics[RESOURCE_UTILIZATION_METRICS_NAMES.SYSTEM_CPU_USAGE] * 100;
                cpuUsage = isNaN(cpuUsage) ? null : cpuUsage;
                const totalMemory = metrics[RESOURCE_UTILIZATION_METRICS_NAMES.SYSTEM_MEMORY_TOTAL];
                const freeMemory = metrics[RESOURCE_UTILIZATION_METRICS_NAMES.SYSTEM_MEMORY_FREE];
                const usedMemory = totalMemory - freeMemory;
                const totalStorage = metrics[RESOURCE_UTILIZATION_METRICS_NAMES.STORAGE_TOTAL];
                const freeStorage = metrics[RESOURCE_UTILIZATION_METRICS_NAMES.STORAGE_FREE];
                const usedStorage = totalStorage - freeStorage;
                member.resources = [{
                    title: 'cpu',
                    max: 100,
                    used: cpuUsage,
                    usedPercentage: cpuUsage,
                    unit: '%'
                }, {
                    title: 'memory',
                    max: formatBytesToGiB(totalMemory),
                    used: formatBytesToGiB(usedMemory),
                    usedPercentage: totalMemory ? usedMemory / totalMemory * 100 : null,
                    unit: 'GiB'
                }, {
                    title: 'storage',
                    max: formatBytesToGiB(totalStorage),
                    used: formatBytesToGiB(usedStorage),
                    usedPercentage: totalStorage ? usedStorage / totalStorage * 100 : null,
                    unit: 'GiB'
                }];
            });
    },
    fetchHeapDump(member) {
        const baseUrl = getMemberAdminBaseUrl(member);
        return this.$http.get(`${baseUrl}${this.$rs.apis.heapdump}`, {
            headers: {
                Accept: 'application/octet-stream'
            },
            responseType: 'blob'
        }).then(response => response.data);
    },
    fetchThreadDump(member) {
        const baseUrl = getMemberAdminBaseUrl(member);
        return this.$http.get(`${baseUrl}${this.$rs.apis.threaddump}`, {
            headers: {
                Accept: 'text/plain;charset=UTF-8'
            },
            responseType: 'blob'
        }).then(response => response.data);
    }
} as ThisType<ComponentCustomProperties & Api>;
<template>
    <div
        class="content-online-user"
    >
        <div>
            <custom-input
                v-model:value="ids"
                :placeholder="$t('userId')"
                class="search-filter search-filter-id"
                :only-number-and-comma="true"
            />
            <a-button
                type="primary"
                class="search-button"
                :disabled="!ids"
                @click="search"
            >
                {{ $t('search') }}
            </a-button>
        </div>
        <div>
            <a-button
                type="primary"
                class="search-button"
                @click="setSelectedDevicesOffline"
            >
                {{ $t('disconnectSelectedDevices') }}
            </a-button>
        </div>
        <a-table
            ref="table"
            bordered
            class="content-table"
            size="small"
            :columns="columns"
            :data-source="tableData"
            :loading="loading"
            :scroll="{ y: scrollMaxHeight }"
            :row-selection="rowSelection"
            :pagination="false"
        >
            <template
                #bodyCell="{ column, record, text }"
            >
                <div
                    v-if="column.dataIndex === 'operation'"
                    class="editable-row-operations"
                >
                    <span v-if="!['OFFLINE', 'NONEXISTENT'].includes(record.status)">
                        <a-popconfirm
                            :title="$t('confirmDisconnect')"
                            @confirm="() => confirmDisconnectUsers([record.userId], [record.deviceType])"
                        >
                            <a>{{ $t('disconnect') }}</a>
                        </a-popconfirm>
                    </span>
                </div>
                <div v-else>
                    {{ text }}
                </div>
            </template>
        </a-table>
    </div>
</template>

<script>
import formatCoords from 'formatcoords';
import CustomInput from '../../../common/custom-input.vue';
import UiMixin from '../template/ui-mixin';

const isOnlineStatus = (status) => status && !['OFFLINE', 'NONEXISTENT'].includes(status);

export default {
    name: 'content-online-user-info-pane',
    components: {
        CustomInput
    },
    mixins: [UiMixin],
    data() {
        return {
            isSearchById: false,
            records: [],
            ids: '',
            loading: false,
            scrollMaxHeight: '100%',
            selectedRowKeys: [],
            selectedRows: [],
            rowSelection: {
                onChange: (selectedRowKeys) => {
                    this.updateSelectedRecordKeys(selectedRowKeys);
                }
            }
        };
    },
    computed: {
        admin() {
            return this.$store.getters.admin;
        },
        columns() {
            const columns = [{
                dataIndex: 'userId',
                width: '10%'
            },
            {
                title: 'onlineDevice',
                dataIndex: 'deviceType',
                width: '5%'
            },
            {
                dataIndex: 'deviceDetails',
                width: '10%'
            },
            {
                title: 'userStatus',
                dataIndex: 'status',
                width: '5%'
            },
            {
                dataIndex: 'sessionId',
                width: '10%'
            },
            {
                dataIndex: 'ip',
                width: '10%'
            },
            {
                title: this.$t('loginDateAndOnlineTime'),
                dataIndex: 'loginDate',
                width: '10%'
            },
            {
                dataIndex: 'loginLocation',
                width: '10%'
            },
            {
                title: 'lastHeartbeatDate',
                dataIndex: 'lastHeartbeatRequestDate',
                width: '10%'
            },
            {
                dataIndex: 'lastRequestDate',
                width: '10%'
            },
            {
                dataIndex: 'isSessionOpen',
                width: '5%'
            },
            {
                dataIndex: 'operation',
                width: '5%'
            }];
            return columns.map(column => {
                if (column.key !== 'operation' && !['TREE'].includes(String(column.type).toUpperCase())) {
                    column.sorter = (a, b) => this.$util.sort(a[column.key], b[column.key]);
                }
                column.title = this.$t(column.title || column.dataIndex);
                return column;
            });
        },
        tableData() {
            this.records.forEach(record => {
                if (record.loginDate) {
                    record.rawLoginDate = record.rawLoginDate || record.loginDate;
                    const fromNow = this.$date(record.rawLoginDate).fromNow();
                    // "this.$t('')" is used to listen to the changes of i18n
                    record.loginDate = `${record.rawLoginDate} (${fromNow})${this.$t('')}`;
                }
            });
            return this.records;
        }
    },
    watch: {
        dataSource: {
            handler() {
                // refresh in the next tick to ensure table is loaded first
                this.$nextTick(() => this.refreshTableUi());
            },
            immediate: true
        },
        records() {
            this.updateSelectedRecordKeys();
        },
        '$store.getters.tab'(val) {
            if (this.myTab === val) {
                setTimeout(() => this.refreshTableUi());
            }
        }
    },
    mounted() {
        this.myTab = this.$store.getters.tab;
        if (!this.myTab) {
            throw new Error('Failed to get the tab of the current page');
        }
        window.addEventListener('resize', this.refreshTableUi);
    },
    unmounted() {
        window.removeEventListener('resize', this.refreshTableUi);
    },
    methods: {
        search() {
            this.loading = true;
            const userIds = this.ids.split(',').filter(Boolean);
            const params = {
                ids: userIds.join(',')
            };
            this.$http.get(this.$rs.apis.userSession, {params})
                .then(response => {
                    const existingUserIds = {};
                    const sessions = response.data.data.flatMap(record => {
                        existingUserIds[record.userId] = true;
                        const allSessions = record.sessions?.length ? record.sessions : [{}];
                        return allSessions.map(session => {
                            const loginLocation = session.loginLocation;
                            if (loginLocation?.longitude && loginLocation?.latitude) {
                                loginLocation.coordinates = formatCoords(loginLocation.latitude, loginLocation.longitude).format();
                            }
                            const isValidDeviceDetails = session.deviceDetails && Object.keys(session.deviceDetails).length;
                            return {
                                key: `${record.userId}-${session.deviceType || ''}`,
                                userId: record.userId,
                                status: record.status,
                                deviceType: session.deviceType,
                                deviceDetails: isValidDeviceDetails ? session.deviceDetails : null,
                                sessionId: session.id,
                                ip: session.ip,
                                lastHeartbeatRequestDate: session.lastHeartbeatRequestDate,
                                lastRequestDate: session.lastRequestDate,
                                isSessionOpen: session.isSessionOpen,
                                loginDate: session.loginDate,
                                loginLocation
                            };
                        });
                    });
                    for (const userId of userIds) {
                        if (!existingUserIds[userId]) {
                            sessions.push({
                                key: userId,
                                userId,
                                status: 'NONEXISTENT'
                            });
                        }
                    }
                    this.records = sessions;
                })
                .catch(error => {
                    this.$error(this.$t('updateFailed'), error);
                })
                .finally(() => this.loading = false);
        },
        setSelectedDevicesOffline() {
            if (!this.selectedRows.length) {
                this.$message.error(this.$t('noRecordsSelected'));
                return;
            }
            if (!this.selectedRows.some(value => isOnlineStatus(value.status))) {
                this.$message.success(this.$t('disconnectSuccessfully'));
                return;
            }
            const deviceTypeToUserIds = {};
            this.selectedRows.forEach(record => {
                if (isOnlineStatus(record.status)) {
                    const userIds = deviceTypeToUserIds[record.deviceType] || [];
                    userIds.push(record.userId);
                    deviceTypeToUserIds[record.deviceType] = userIds;
                }
            });
            this.loading = true;
            const requests = Object.entries(deviceTypeToUserIds)
                .map(([deviceType, userIds]) => this.disconnectUsers(userIds, [deviceType]));
            Promise.all(requests)
                .then(() => {
                    this.$message.success(this.$t('disconnectSuccessfully'));
                })
                .catch(error => {
                    this.$error(this.$t('failedToDisconnect'), error);
                })
                .finally(() => this.loading = false);
        },
        confirmDisconnectUsers(userIds, deviceTypes) {
            this.loading = true;
            this.disconnectUsers(userIds, deviceTypes)
                .then(() => {
                    this.$message.success(this.$t('disconnectSuccessfully'));
                })
                .catch(error => {
                    this.$error(this.$t('failedToDisconnect'), error);
                })
                .finally(() => this.loading = false);
        },
        disconnectUsers(userIds, deviceTypes) {
            if (!deviceTypes.length) {
                console.error('The device types to disconnect must not be empty');
                return;
            }
            const params = {
                ids: userIds.join(','),
                deviceTypes: deviceTypes.join(',')
            };
            return this.$http.put(`${this.$rs.apis.userStatus}?${this.$qs.encode(params)}`, {
                onlineStatus: 'OFFLINE'
            })
                .then(() => {
                    const records = this.records.map(record => {
                        if (userIds.includes(record.userId)) {
                            return {
                                key: `${record.userId}-`,
                                userId: record.userId,
                                status: 'OFFLINE'
                            };
                        }
                        return record;
                    });
                    this.records = this.$util.unique(records, v => v.key);
                });
        },
        updateSelectedRecordKeys(keys) {
            if (!keys) {
                keys = this.selectedRowKeys
                    .filter(key => this.records.some(record => record.key === key));
            }
            this.selectedRowKeys = keys;
            this.selectedRows = keys.map(key => this.records.find(record => record.key === key));
            this.rowSelection.selectedRowKeys = keys;
        }
    }
};
</script>
<style scoped>
.content-online-user {
    display: flex;
    flex-direction: column;
}

.search-filter-id {
    max-width: 200px;
}

.search-button {
    margin-bottom: 18px;
}
</style>
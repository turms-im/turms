<template>
    <div
        class="content-online-user"
    >
        <div>
            <custom-input
                v-model="id"
                :placeholder="$t('userId')"
                class="search-filter search-filter-id"
                :only-number-and-comma="true"
            />
            <a-button
                type="primary"
                class="search-button"
                :disabled="!id"
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
        <a-spin :spinning="loading">
            <a-table
                size="middle"
                :columns="columns"
                :data-source="records"
                bordered
                :scroll="{ y: 400 }"
                :row-selection="rowSelection"
                :pagination="false"
                class="content-table"
            >
                <template
                    slot="operation"
                    slot-scope="text, record"
                >
                    <div class="editable-row-operations">
                        <span v-if="record.userStatus !== 'OFFLINE'">
                            <a-popconfirm
                                :title="$t('confirmDisconnect')"
                                @confirm="() => onDelete(record.id)"
                            >
                                <a>{{ $t('disconnect') }}</a>
                            </a-popconfirm>
                        </span>
                    </div>
                </template>
            </a-table>
        </a-spin>
    </div>
</template>

<script>
const JSONbig = require('json-bigint');
import CustomInput from '../../../common/custom-input';
import formatCoords from 'formatcoords';

export default {
    name: 'content-online-user-info-pane',
    components: {
        CustomInput
    },
    data() {
        return {
            isSearchById: false,
            records: [],
            columns: [{
                title: this.$t('userId'),
                dataIndex: 'userId',
                width: '20%',
                scopedSlots: {customRender: 'userId'}
            },
            {
                title: this.$t('userStatus'),
                dataIndex: 'userStatus',
                width: '15%',
                scopedSlots: {customRender: 'userStatus'}
            },
            {
                title: this.$t('onlineDevice'),
                dataIndex: 'deviceType',
                width: '15%',
                scopedSlots: {customRender: 'deviceType'}
            },
            {
                title: this.$t('loginDateAndOnlineTime'),
                dataIndex: 'loginDate',
                width: '20%',
                scopedSlots: {customRender: 'loginDate'}
            },
            {
                title: this.$t('currentLocation'),
                dataIndex: 'location',
                width: '20%',
                scopedSlots: {customRender: 'location'}
            },
            {
                title: this.$t('operation'),
                dataIndex: 'operation',
                width: '10%',
                scopedSlots: {customRender: 'operation'}
            }],
            id: null,
            loading: false,
            selectedRowKeys: [],
            selectedRows: [],
            rowSelection: {
                onChange: (selectedRowKeys, selectedRows) => {
                    this.selectedRowKeys = selectedRowKeys;
                    this.selectedRows = selectedRows;
                }
            }
        };
    },
    computed: {
        admin() {
            return this.$store.getters.admin;
        }
    },
    methods: {
        search() {
            this.loading = true;
            this.selectedRowKeys.splice(0, this.selectedRowKeys.length);
            this.$client.get(this.$rs.apis.userStatus, {
                params: {
                    ids: this.id || undefined
                }
            })
                .then(response => {
                    if (response.status === 204) {
                        this.records = [];
                    } else {
                        this.loaded = true;
                        this.records = response.data.data.flatMap(record => {
                            const array = [];
                            record.key = record.userId;
                            if (record.sessionMap) {
                                const keys = Object.keys(record.sessionMap);
                                for (let i = 0; i < keys.length; i++) {
                                    const target = JSONbig.parse(JSONbig.stringify(record));
                                    const source = target.sessionMap[keys[i]];
                                    const friendlyDate = this.$moment(source.loginDate).fromNow();
                                    source.loginDate = `${source.loginDate} (${friendlyDate})`;
                                    if (source.location && source.location.longitude && source.location.latitude) {
                                        source.location = formatCoords(source.location.latitude, source.location.longitude).format();
                                    }
                                    delete target.sessionMap;
                                    array.push(Object.assign(target, source));
                                }
                            } else {
                                array.push(record);
                            }
                            return array;
                        });
                    }
                })
                .catch(error => {
                    if (error.response && error.response.status === 404) {
                        this.records = [];
                        this.total = 0;
                    }
                    this.$error(this.$t('updateFailed'), error);
                })
                .finally(() => this.loading = false);
        },
        setSelectedDevicesOffline() {
            this.onDelete();
        },
        onDelete(ids) {
            if (!this.selectedRows.length) {
                this.$message.error(this.$t('noRecordsSelected'));
                return;
            }
            const deviceTypes = this.selectedRows
                .filter(record => record.deviceType)
                .map(record => record.deviceType);
            if (!deviceTypes.length) {
                this.$message.error(this.$t('noOnlineDevices'));
                return;
            }
            ids = ids || this.selectedRowKeys.filter((v, i, a) => a.indexOf(v) === i);
            this.loading = true;
            const params = {
                ids: ids.join(','),
                deviceTypes: deviceTypes ? deviceTypes.join(',') : undefined
            };
            this.$client.put(`${this.$rs.apis.userStatus}?${this.$qs.encode(params)}`, {
                onlineStatus: 'OFFLINE'
            })
                .then(() => {
                    this.$message.success(this.$t('disconnectSuccessfully'));
                    this.records = this.records.filter(record => !ids.includes(record.id));
                })
                .catch(error => {
                    this.$error(this.$t('failedToDisconnect'), error);
                })
                .finally(() => this.loading = false);
        }
    }
};
</script>
<style scoped>
.content-online-user {
    display: flex;
    flex-direction: column;
}

.search-button {
    margin-bottom: 18px;
}
</style>
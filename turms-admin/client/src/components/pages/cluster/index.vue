<template>
    <skeleton v-if="!initialized && loading" />
    <a-spin
        v-else
        :spinning="initialized && loading"
    >
        <div
            class="cluster-management-tabs"
        >
            <a-tabs>
                <a-tab-pane
                    v-for="title in tabTitles"
                    :key="title.key"
                    :tab="title.text"
                >
                    <cluster-config-pane :properties="currentConfig[title.key]" />
                </a-tab-pane>
            </a-tabs>
            <div class="cluster-management-tabs__action-group">
                <a-popconfirm
                    :title="$t('confirmRefresh')"
                    :visible="isRefreshPopVisible"
                    @visibleChange="onRefreshPopVisibleChanged"
                    @confirm="requestRefresh"
                >
                    <a-button
                        type="primary"
                        class="cluster-management-tabs__refresh"
                    >
                        {{ $t('refresh') }}
                    </a-button>
                </a-popconfirm>
                <a-popconfirm
                    :title="$t('confirmReset')"
                    @confirm="requestResetToDefault"
                >
                    <div
                        class="cluster-management-tabs__action-group__item"
                    >
                        <a-button
                            type="danger"
                        >
                            {{ $t('resetToDefault') }}
                        </a-button>
                    </div>
                </a-popconfirm>
                <a-popconfirm
                    :title="$t('confirmRemoveChanges')"
                    :visible="isRemoveChangesPopVisible"
                    @confirm="removeChanges"
                    @visibleChange="onRemoveChangesPopVisibleChanged"
                >
                    <div
                        class="cluster-management-tabs__action-group__item"
                    >
                        <a-button
                            :disabled="!changedNumber"
                        >
                            {{ $t('removeChanges') }}
                        </a-button>
                    </div>
                </a-popconfirm>
                <a-popconfirm
                    :title="$t('confirmApplyChanges')"
                    :visible="isApplyChangesPopVisible"
                    @confirm="requestApplyChanges"
                    @visibleChange="onApplyChangesPopVisibleChanged"
                >
                    <div
                        class="cluster-management-tabs__action-group__item"
                    >
                        <a-button
                            type="primary"
                            :disabled="!changedNumber"
                        >
                            {{ $t('applyChanges') }} ({{ changedNumber }})
                        </a-button>
                    </div>
                </a-popconfirm>
            </div>
        </div>
    </a-spin>
</template>

<script>
import diff from 'deep-diff';
import ClusterConfigPane from './config/index';
import Skeleton from '../../common/skeleton';

export default {
    name: 'cluster-management-tabs',
    components: {
        ClusterConfigPane,
        Skeleton
    },
    data() {
        return {
            initialized: false,
            loading: false,
            defaultConfig: {},
            currentConfig: {},
            changedNumber: 0,
            diffs: [],
            isRemoveChangesPopVisible: false,
            isRefreshPopVisible: false,
            isApplyChangesPopVisible: false
        };
    },
    computed: {
        admin() {
            return this.$store.getters.admin;
        },
        tabTitles() {
            return Object.entries(this.currentConfig)
                .filter(entry => Object.keys(entry[1]).length)
                .map(entry => ({
                    text: this.$util.splitByCapitals(this.$util.upperFirst(entry[0])),
                    key: entry[0]
                }));
        }
    },
    watch: {
        admin() {
            if (this.admin) {
                this.fetchData();
            }
        },
        currentConfig: {
            handler() {
                this.updateDiff();
            },
            deep: true
        }
    },
    mounted() {
        if (this.admin) {
            this.fetchData();
        }
    },
    methods: {
        updateDiff() {
            const result = diff(this.currentConfig, this.defaultConfig);
            if (result) {
                this.diffs = result.filter(difference => difference.path[difference.path.length - 1] === 'value');
                for (const difference of this.diffs) {
                    let config = this.currentConfig;
                    for (const currentPath of difference.path) {
                        if (currentPath !== 'value') {
                            config = config[currentPath];
                        }
                    }
                    config.defaultValue = difference.rhs;
                }
                if (this.diffs) {
                    this.changedNumber = this.diffs.length;
                } else {
                    this.changedNumber = 0;
                }
            } else {
                this.changedNumber = 0;
            }
        },
        requestRefresh() {
            this.fetchData();
        },
        fetchData() {
            this.loading = true;
            return this.fetchConfig()
                .then(response => {
                    this.defaultConfig = response.data.data;
                    this.currentConfig = JSON.parse(JSON.stringify(this.defaultConfig));
                    if (this.initialized) {
                        this.$message.success(this.$t('refreshedDataSuccessfully'));
                    } else {
                        this.initialized = true;
                    }
                })
                .catch(error => {
                    this.$error(this.$t('failedToFetchData'), error);
                })
                .finally(() => {
                    this.loading = false;
                });
        },
        fetchConfig() {
            return this.$client.get('/cluster/config/metadata?withValue=true')
                .catch(error => {
                    this.$error(this.$t('failedToFetchData'), error);
                });
        },
        requestResetToDefault() {
            return this.$client.put('/cluster/config?shouldReset=true')
                .then(() => {
                    this.$message.success(this.$t('updatedSuccessfully'));
                })
                .catch(error => {
                    this.$error(this.$t('updateFailed'), error);
                });
        },
        removeChanges() {
            this.currentConfig = JSON.parse(JSON.stringify(this.defaultConfig));
            this.$message.success(this.$t('removeChangesSuccessfully'));
        },
        requestApplyChanges() {
            const config = this.extractValues(this.currentConfig, true);
            return this.$client.put('/cluster/config', config)
                .then(() => {
                    this.$message.success(this.$t('updatedSuccessfully'));
                    this.fetchData();
                })
                .catch(error => {
                    this.$error(this.$t('updateFailed'), error);
                });
        },
        extractValues(config, copy) {
            if (copy) {
                config = JSON.parse(JSON.stringify(this.currentConfig));
            }
            Object.entries(config).forEach(entry => {
                if (typeof entry[1].mutable !== 'undefined') {
                    config[entry[0]] = entry[1].value;
                } else {
                    config[entry[0]] = this.extractValues(entry[1]);
                }
            });
            return config;
        },
        onRemoveChangesPopVisibleChanged() {
            if (this.changedNumber) {
                this.isRemoveChangesPopVisible = !this.isRemoveChangesPopVisible;
            } else {
                this.isRemoveChangesPopVisible = false;
            }
        },
        onApplyChangesPopVisibleChanged() {
            if (this.changedNumber) {
                this.isApplyChangesPopVisible = !this.isApplyChangesPopVisible;
            } else {
                this.isApplyChangesPopVisible = false;
            }
        },
        onRefreshPopVisibleChanged(visible) {
            if (visible) {
                if (this.changedNumber) {
                    this.isRefreshPopVisible = true;
                } else {
                    this.isRefreshPopVisible = false;
                    this.fetchData();
                }
            } else {
                this.isRefreshPopVisible = false;
            }
        }
    }
};
</script>
<style lang="scss" scoped>
    .cluster-management-tabs {
        display: flex;
        flex-direction: column;

        &__refresh {
            margin-right: 36px;
        }

        &__action-group {
            display: flex;
            position: fixed;
            bottom: 36px;
            right: 48px;

            &__item {
                margin-left: 12px;
            }
        }
    }
</style>
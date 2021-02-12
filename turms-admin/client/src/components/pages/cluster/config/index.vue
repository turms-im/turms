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
                    <cluster-config-pane :property-groups="currentConfig[title.key]" />
                </a-tab-pane>
            </a-tabs>
            <div class="cluster-management-tabs__action-group">
                <a-popconfirm
                    :title="$t('confirmRefresh')"
                    :visible="!!isRefreshPopVisible"
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
                    :visible="!!isRemoveChangesPopVisible"
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
                    :visible="!!isApplyChangesPopVisible"
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
import ClusterConfigPane from './cluster-config-pane';
import Skeleton from '../../../common/skeleton';

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
                .flatMap(([title, properties]) => {
                    if (Object.keys(properties).length) {
                        return {
                            text: this.$util.splitByCapitals(this.$util.upperFirst(title)),
                            key: title
                        };
                    } else {
                        return [];
                    }
                });
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
            const differences = diff(this.currentConfig, this.defaultConfig) || [];
            const diffs = differences.filter(difference => difference.path[difference.path.length - 1] === 'value');
            for (const difference of diffs) {
                let config = this.currentConfig;
                for (const currentPath of difference.path) {
                    if (currentPath !== 'value') {
                        config = config[currentPath];
                    }
                }
                config.defaultValue = difference.rhs;
            }
            this.changedNumber = diffs?.length || 0;
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
            return this.$http.get('/cluster/config/metadata?withValue=true')
                .catch(error => {
                    this.$error(this.$t('failedToFetchData'), error);
                });
        },
        requestResetToDefault() {
            return this.$http.put('/cluster/config?reset=true&updateGlobalProperties=true')
                .then(() => {
                    this.$message.success(this.$t('updatedSuccessfully'));
                    this.fetchData();
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
            // const configWithValue = this.extractValues(this.currentConfig, true);
            const diffs = (diff(this.currentConfig, this.defaultConfig) || [])
                .filter(difference => difference.path[difference.path.length - 1] === 'value');
            const config = {};
            for (const difference of diffs) {
                let targetIndex = config;
                let valueIndex = this.currentConfig;
                for (let i = 0; i < difference.path.length - 1; i++) {
                    const currentPath = difference.path[i];
                    if (!targetIndex[currentPath]) {
                        targetIndex[currentPath] = {};
                    }
                    if (i === difference.path.length - 2) {
                        targetIndex[currentPath] = valueIndex[currentPath].value;
                    } else {
                        targetIndex = targetIndex[currentPath];
                        valueIndex = valueIndex[currentPath];
                    }
                }
            }
            return this.$http.put('/cluster/config', config)
                .then(() => {
                    this.$message.success(this.$t('updatedSuccessfully'));
                    this.fetchData();
                })
                .catch(error => {
                    this.$error(this.$t('updateFailed'), error);
                });
        },
        onRemoveChangesPopVisibleChanged() {
            this.isRemoveChangesPopVisible = this.changedNumber && !this.isRemoveChangesPopVisible;
        },
        onApplyChangesPopVisibleChanged() {
            this.isApplyChangesPopVisible = this.changedNumber && !this.isApplyChangesPopVisible;
        },
        onRefreshPopVisibleChanged(visible) {
            this.isRefreshPopVisible = visible && this.changedNumber;
            if (visible && !this.changedNumber) {
                this.fetchData();
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
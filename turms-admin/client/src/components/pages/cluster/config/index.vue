<template>
    <skeleton v-if="!initialized && loading" />
    <a-spin
        v-else
        :spinning="initialized && loading"
    >
        <div
            class="cluster-config"
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
            <div class="cluster-config__action-group">
                <a-popconfirm
                    :title="$t('confirmRefresh')"
                    :visible="!!isRefreshPopVisible"
                    @visibleChange="onRefreshPopVisibleChanged"
                    @confirm="requestRefresh"
                >
                    <a-button
                        type="primary"
                        class="cluster-config__refresh"
                    >
                        {{ $t('refresh') }}
                    </a-button>
                </a-popconfirm>
                <a-popconfirm
                    :title="$t('confirmReset')"
                    @confirm="requestResetToDefault"
                >
                    <div
                        class="cluster-config__action-group__item"
                    >
                        <a-button
                            type="danger"
                        >
                            {{ $t('resetToDefault') }}
                        </a-button>
                    </div>
                </a-popconfirm>
                <a-popconfirm
                    :title="$t('confirmDiscardChanges')"
                    :visible="!!isDiscardChangesPopVisible"
                    @confirm="discardChanges"
                    @visibleChange="onDiscardChangesPopVisibleChanged"
                >
                    <div
                        class="cluster-config__action-group__item"
                    >
                        <a-button
                            :disabled="!changedNumber"
                        >
                            {{ $t('discardChanges') }}
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
                        class="cluster-config__action-group__item"
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
    name: 'cluster-config',
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
            isDiscardChangesPopVisible: false,
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
            const diffs = (diff(this.currentConfig, this.defaultConfig) || [])
                .filter(difference => difference.path[difference.path.length - 1] === 'value');
            for (const difference of diffs) {
                const paths = difference.path;
                const targetPaths = paths.slice(0, paths.length - 1).concat('defaultValue');
                this.$_.set(this.currentConfig, targetPaths, difference.rhs);
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
                    this.currentConfig = this.$util.copy(this.defaultConfig);
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
            return this.$http.get('/cluster/settings/metadata?withValue=true')
                .catch(error => {
                    this.$error(this.$t('failedToFetchData'), error);
                });
        },
        requestResetToDefault() {
            return this.$http.put('/cluster/settings?reset=true&updateGlobalSettings=true')
                .then(() => {
                    this.$message.success(this.$t('updatedSuccessfully'));
                    this.fetchData();
                })
                .catch(error => {
                    this.$error(this.$t('updateFailed'), error);
                });
        },
        discardChanges() {
            this.currentConfig = this.$util.copy(this.defaultConfig);
            this.$message.success(this.$t('discardChangesSuccessfully'));
        },
        requestApplyChanges() {
            const diffs = (diff(this.currentConfig, this.defaultConfig) || [])
                .filter(difference => difference.path[difference.path.length - 1] === 'value');
            const config = {};
            for (const difference of diffs) {
                const paths = difference.path;
                const value = this.$_.get(this.currentConfig, paths);
                this.$_.set(config, paths.slice(0, paths.length - 1), value);
            }
            return this.$http.put('/cluster/settings', config)
                .then(() => {
                    this.$message.success(this.$t('updatedSuccessfully'));
                    this.fetchData();
                })
                .catch(error => {
                    this.$error(this.$t('updateFailed'), error);
                });
        },
        onDiscardChangesPopVisibleChanged() {
            this.isDiscardChangesPopVisible = this.changedNumber && !this.isDiscardChangesPopVisible;
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
.cluster-config {
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
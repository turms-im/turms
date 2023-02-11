<template>
    <skeleton v-if="!initialized" />
    <a-spin
        v-else
        :spinning="loading"
    >
        <div
            class="cluster-config"
        >
            <a-tabs :animated="true">
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
                            type="primary"
                            danger
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
import Skeleton from '../../../common/skeleton.vue';
import ClusterConfigPane from './cluster-config-pane.vue';

const WORDS_TO_UPPERCASE = ['ip'];

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
                            text: WORDS_TO_UPPERCASE.includes(title)
                                ? title.toUpperCase()
                                : this.$util.splitByCapitals(this.$util.upperFirst(title)),
                            key: title
                        };
                    }
                    return [];
                })
                .sort((title1, title2) => title1.text.localeCompare(title2.text));
        }
    },
    watch: {
        admin() {
            this.initDataOnLogin();
        },
        currentConfig: {
            handler() {
                this.updateDiff();
            },
            deep: true
        }
    },
    mounted() {
        this.initDataOnLogin();
    },
    methods: {
        initDataOnLogin() {
            if (this.admin) {
                this.fetchData();
            }
        },
        updateDiff() {
            const diffs = (diff(this.currentConfig, this.defaultConfig) || [])
                .filter(difference => difference.path[difference.path.length - 1] === 'value');
            for (const difference of diffs) {
                const paths = difference.path;
                const targetPaths = paths.slice(0, paths.length - 1).concat('defaultValue');
                this.$util.set(this.currentConfig, targetPaths, difference.rhs);
            }
            this.changedNumber = diffs?.length || 0;
        },
        requestRefresh() {
            this.fetchData();
        },
        fetchData(retryTimes = 0) {
            this.loading = true;
            return this.fetchConfig()
                .then(response => {
                    this.defaultConfig = response.data.data.settings;
                    this.currentConfig = this.$util.copy(this.defaultConfig);
                    if (this.initialized) {
                        this.$message.success(this.$t('refreshedDataSuccessfully'));
                    }
                })
                .catch(async error => {
                    if (this.initialized) {
                        this.$error(this.$t('failedToFetchData'), error);
                    } else {
                        this.$error(this.$t('failedToFetchDataWithRetry', {
                            number: ++retryTimes
                        }), error);
                        await this.$sleep(10 * 1000);
                        await this.fetchData(retryTimes);
                    }
                })
                .finally(() => {
                    this.loading = false;
                    this.initialized = true;
                });
        },
        fetchConfig() {
            return this.$http.get('/cluster/settings/metadata?withValue=true')
                .catch(error => {
                    this.$error(this.$t('failedToFetchData'), error);
                });
        },
        requestResetToDefault() {
            return this.$http.put('/cluster/settings?reset=true')
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
                const value = this.$util.get(this.currentConfig, paths);
                this.$util.set(config, paths.slice(0, paths.length - 1), value);
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
<style lang="scss">
.cluster-config {
    display: flex;
    flex-direction: column;

    &__refresh {
        margin-right: 36px;
    }

    &__action-group {
        position: fixed;
        right: 48px;
        bottom: 36px;
        display: flex;

        &__item {
            margin-left: 12px;
        }
    }
}
</style>
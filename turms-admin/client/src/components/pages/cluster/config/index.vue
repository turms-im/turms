<template>
    <cluster-config-wrapper
        :title="upperFirstTitle"
    >
        <template
            v-for="entry in Object.entries(properties)"
        >
            <cluster-config-pane
                v-if="typeof entry[1].type === 'undefined'"
                :key="entry[0]"
                :title="entry[0]"
                :properties="entry[1]"
                class="cluster-config-pane"
            />
            <cluster-config-item
                v-else
                :key="entry[0]"
                :pair="entry"
            />
        </template>
    </cluster-config-wrapper>
</template>

<script>
import ClusterConfigWrapper from './cluster-config-wrapper';
import ClusterConfigItem from './cluster-config-item';

export default {
    name: 'cluster-config-pane',
    components: {
        ClusterConfigWrapper,
        ClusterConfigItem
    },
    props: {
        title: {
            type: String,
            default: 'Common'
        },
        properties: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            initialized: false,
            loading: false
        };
    },
    computed: {
        admin() {
            return this.$store.getters.admin;
        },
        upperFirstTitle() {
            return this.$util.upperFirst(this.title);
        }
    },
    watch: {
        admin() {
            if (this.admin) {
                this.fetchConfig();
            }
        }
    },
    methods: {
        isValueChanged(item) {
            return typeof item.defaultValue !== 'undefined' && item.defaultValue !== item.value;
        },
        onRollbackClicked(item) {
            item.value = JSON.parse(JSON.stringify(item.defaultValue));
        }
    }
};
</script>

<style lang="scss" scoped>
    .cluster-config-pane {
        width: 100%;
    }
</style>

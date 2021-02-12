<template>
    <cluster-config-wrapper
        :title="upperFirstTitle"
    >
        <template
            v-for="groupOrItem in groups"
        >
            <cluster-config-pane
                v-if="groupOrItem.isPropertyGroup"
                :key="groupOrItem.title"
                :title="groupOrItem.title"
                :property-groups="groupOrItem.properties"
                class="cluster-config-pane"
            />
            <cluster-config-item
                v-else
                :key="groupOrItem.name"
                :name="groupOrItem.name"
                :property="groupOrItem.property"
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
        propertyGroups: {
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
        },
        groups() {
            return Object.entries(this.propertyGroups)
                .map(([name, value]) => {
                    const isPropertyGroup = value.type == null;
                    if (isPropertyGroup) {
                        return {
                            isPropertyGroup,
                            title: name,
                            properties: value
                        };
                    } else {
                        return {
                            isPropertyGroup,
                            name,
                            property: value
                        };
                    }
                });
        }
    },
    methods: {
        isValueChanged(item) {
            return item.defaultValue != null && item.defaultValue !== item.value;
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

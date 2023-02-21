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
import ClusterConfigWrapper from './cluster-config-wrapper.vue';
import ClusterConfigItem from './cluster-config-item.vue';

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
                    const isPropertyGroup = this.isPropertyGroup(value);
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
                })
                .sort((property1, property2) => {
                    if (property1.properties) {
                        return property2.properties
                            ? property1.title.localeCompare(property2.title)
                            : 1;
                    }
                    return property2.properties
                        ? -1
                        : property1.name.localeCompare(property2.name);
                });
        }
    },
    methods: {
        isPropertyGroup(value) {
            return value.type == null || value.global == null || value.mutable == null;
        },
        isValueChanged(item) {
            return item.defaultValue != null && item.defaultValue !== item.value;
        },
        onRollbackClicked(item) {
            item.value = this.$util.copy(item.defaultValue);
        }
    }
};
</script>

<style lang="scss">
    .cluster-config-pane {
        width: 100%;
    }
</style>

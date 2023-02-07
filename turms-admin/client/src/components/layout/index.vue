<template>
    <a-layout class="layout">
        <login-modal />
        <layout-sider />
        <a-layout>
            <layout-header />
            <a-layout-content class="layout__content">
                <router-view v-slot="{ Component }">
                    <keep-alive>
                        <component :is="Component" />
                    </keep-alive>
                </router-view>
            </a-layout-content>
        </a-layout>
    </a-layout>
</template>
<script>
import LoginModal from '../modals/login-modal.vue';
import LayoutHeader from './layout-header.vue';
import LayoutSider from './layout-sider.vue';

export default {
    components: {
        LayoutHeader,
        LayoutSider,
        LoginModal
    }
};
</script>
<style lang="scss">
.layout {
    height: 100%;

    &__content {
        padding: 24px;
        overflow-y: scroll;
        background-color: white;
    }
}
</style>

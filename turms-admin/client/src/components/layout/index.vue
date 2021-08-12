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
import LayoutHeader from './layout-header';
import LayoutSider from './layout-sider';
import LoginModal from '../modals/login-modal';

export default {
    components: {
        LayoutHeader,
        LayoutSider,
        LoginModal
    }
};
</script>
<style lang="scss" scoped>
.layout {
    height: 100%;

    &__content {
        overflow-y: scroll;
        padding: 24px 24px 24px 24px;
        background-color: white;
    }
}
</style>

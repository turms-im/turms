<template>
    <a-layout class="layout">
        <login-modal />
        <layout-sider ref="layoutSider" />
        <a-layout>
            <layout-header ref="layoutHeader" />
            <a-layout-content class="layout__content">
                <keep-alive>
                    <router-view />
                </keep-alive>
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
            min-width: 1024px;
            overflow-y: scroll;
            padding: 24px 24px 24px 24px;
            background-color: white;
        }
    }
</style>

<template>
    <section class="cluster-config-wrapper">
        <section class="cluster-config-wrapper__content">
            <h4>
                {{ title }}
            </h4>
            <slot />
        </section>
    </section>
</template>

<script>
export default {
    name: 'cluster-config-wrapper',
    props: {
        title: {
            type: String,
            default: ''
        }
    }
};
</script>

<style lang="scss" scoped>
.cluster-config-wrapper {
    border: 1px solid #ebedf0;
    border-radius: 0 0 2px 2px;
    display: inline-block;
    width: 100%;
    position: relative;
    margin: 0 0 16px;
    padding: 26px 32px;
    font-size: 14px;
    color: #314659;
    line-height: 2;

    h4 {
        position: absolute;
        top: -14px;
        padding: 1px 8px;
        margin-left: -8px;
        color: #777;
        border-radius: 2px 2px 0 0;
        background: #fff;
        font-size: 14px;
        width: auto;
    }

    p {
        font-size: 12px;
        margin: 0.5em 0;
        padding-right: 25px;
        width: 100%;
        word-break: break-word;
    }
}
</style>

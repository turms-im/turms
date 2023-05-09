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

<style lang="scss">
.cluster-config-wrapper {
    position: relative;
    display: inline-block;
    width: 100%;
    padding: 26px 32px;
    margin: 0 0 16px;
    font-size: 14px;
    line-height: 2;
    color: #314659;
    border: 1px solid #ebedf0;
    border-radius: 0 0 2px 2px;

    h4 {
        position: absolute;
        top: -14px;
        width: auto;
        padding: 1px 8px;
        margin-left: -8px;
        font-size: 14px;
        color: #777;
        background: #fff;
        border-radius: 2px 2px 0 0;
    }

    p {
        width: 100%;
        padding-right: 25px;
        margin: 0.5em 0;
        font-size: 12px;
        word-break: break-word;
    }
}
</style>

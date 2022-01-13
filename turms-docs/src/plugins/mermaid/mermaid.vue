<template>
    <figure
        class="mermaid-diagram"
        :class="{ zoomed }"
        @click="toggleZoom"
        v-html="svg"
    />
</template>

<script>

let uuid = 1;

export default {
    props: {
        dsl: {
            type: String,
            required: true
        }
    },
    data: () => ({
        svg: '',
        mermaid: null,
        zoomed: false,
        darkModeObserver: null
    }),
    async mounted() {
        this.mermaid = (await import('mermaid')).default;
        this.darkModeObserver = new MutationObserver(this.render);
        this.darkModeObserver.observe(document.documentElement, {
            attributeFilter: ['class']
        });
        this.render();
    },
    unmounted() {
        this.darkModeObserver.disconnect();
    },
    methods: {
        isDarkMode() {
            return document.documentElement.classList.contains('dark');
        },
        render() {
            this.mermaid.initialize({
                startOnLoad: false,
                theme: this.isDarkMode() ? 'dark' : 'default'
            });
            this.mermaid.render(`diagram_${uuid++}`, this.dsl, (svg) => this.svg = svg);
        },
        toggleZoom() {
            this.zoomed = !this.zoomed;
        }
    }
};
</script>

<style lang="scss">
.mermaid-diagram {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 1rem 0;
    cursor: pointer;

    &.zoomed {
        position: fixed;
        z-index: 100;
        top: 0;
        left: 0;
        height: 100vh;
        width: 100vw;
        background: var(--c-bg);
        margin: 0;
    }
}
</style>
<template>
    <figure
        class="mermaid-diagram"
        :class="{
            zoomed,
            dark: isDarkMode
        }"
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
        darkModeObserver: null,
        isDarkMode: false
    }),
    async mounted() {
        this.mermaid = (await import('mermaid')).default;
        this.darkModeObserver = new MutationObserver(this.render);
        this.darkModeObserver.observe(document.documentElement, {
            attributeFilter: ['class']
        });
        await this.render();
    },
    unmounted() {
        this.darkModeObserver.disconnect();
    },
    methods: {
        async render() {
            const isDarkMode = document.documentElement.classList.contains('dark');
            this.isDarkMode = isDarkMode;
            const theme = isDarkMode ? 'dark' : 'default';
            this.mermaid.initialize({
                startOnLoad: false,
                theme
            });
            const { svg } = await this.mermaid.render(`diagram_${uuid++}`, this.dsl);
            this.svg = svg;
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
        background-color: #FFFFFF;
        margin: 0;

        &.dark {
            background-color: #1E1E1E;
        }
    }
}
</style>
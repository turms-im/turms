<template>
    <div
        class="progress-bar"
        :style="`width: ${width}px`"
    >
        <div
            ref="progress"
            class="progress-bar__progress"
        />
    </div>
</template>

<script>
export default {
    name: 'progress-bar',
    props: {
        width: {
            type: Number,
            default: 100
        },
        progress: {
            type: Number,
            default: 0
        }
    },
    watch: {
        progress: {
            handler() {
                this.updateProgress();
            }
        }
    },
    mounted() {
        this.updateProgress();
    },
    methods: {
        updateProgress() {
            this.$refs.progress.style.width = `${this.progress.toFixed()}%`;
        }
    }
};
</script>

<style lang="scss">
.progress-bar {
    height: 1.2rem;
    background: #d1e1eb;

    &__progress {
        height: 100%;
        background: #4adf73;
    }
}

</style>
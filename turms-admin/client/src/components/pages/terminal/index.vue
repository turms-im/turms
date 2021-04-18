<template>
    <div class="client-terminal-pane">
        <client-terminal />
    </div>
</template>

<script>
import ClientTerminal from './client-terminal';

export default {
    name: 'client-terminal-pane',
    components: {
        ClientTerminal
    }
};
</script>
<style lang="scss" scoped>
.client-terminal-pane {
    height: 100%;
}
</style>
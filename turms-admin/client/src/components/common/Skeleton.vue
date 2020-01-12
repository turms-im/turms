<template>
    <div>
        <a-skeleton
            v-for="index in 3"
            :key="index"
            active
        />
    </div>
</template>

<script>
export default {
    name: 'skeleton'
};
</script>
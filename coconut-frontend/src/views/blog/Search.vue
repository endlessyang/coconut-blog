<template>
    <div>
        <BlogList opt="title" :val="$route.params.val"/>
    </div>
</template>

<script>
    import BlogList from "./List"

    export default {
        components: { BlogList }
    }
</script>

<style scoped>

</style>
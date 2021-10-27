<template>
    <div>
        <div class="box" align="left">🔔 Beta Version</div>
        <div class="columns">
            <div class="column is-three-quarters">
                <BlogList></BlogList>
            </div>
            <div class="column">
                <Sidebar></Sidebar>
            </div>
        </div>
    </div>
</template>

<script>
    import BlogList from "../views/blog/List"
    import Sidebar from '../views/sidebar/Index'

    export default {
        name: "Home",
        components: { BlogList, Sidebar }
    }
</script>

<style scoped>

</style>
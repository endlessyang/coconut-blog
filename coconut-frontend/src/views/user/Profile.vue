<template>
    <div class="member">
        <div class="columns">
            <div class="column is-one-quarter">
                <el-card shadow="never">
                    <div slot="header" class="has-text-centered">
                        <el-avatar :size="64"> {{ user.username[0] }} </el-avatar>
                        <p class="mt-3">{{ user.username }}</p>
                    </div>
                    <div>
                        <p class="content">Blogs：{{ user.blogCount }}</p>
                        <p class="content">Followers：{{ user.followerCount }}</p>
                    </div>
                </el-card>
            </div>

            <div class="column">
                <BlogList opt="userId" :val="this.$route.params.id"/>
            </div>
        </div>
    </div>
</template>

<script>
    import BlogList from "../blog/List"

    export default {
        components: { BlogList },
        data() {
            return {
                user: ''
            }
        },
        created() {
            this.$axios.get("/user/" + this.$route.params.id).then(res => {
                this.user = res.data.data
            })
        }
    }
</script>

<style scoped>

</style>
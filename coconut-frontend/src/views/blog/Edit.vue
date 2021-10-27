<template>
    <section>
        <div class="columns">
            <div class="column is-full">
                <el-card class="box-card" shadow="never">
                    <div slot="header" class="clearfix">
                        <span><i class="fa fa fa-book">Create / Edit</i></span>
                    </div>
                    <div>
                        <el-form :model="blog" ref="topic" class="demo-topic">
                            <el-form-item prop="title">
                                <el-input
                                    v-model="blog.title"
                                    placeholder="Title"
                                ></el-input>
                            </el-form-item>

                            <mavon-editor v-model="blog.content" language="en"></mavon-editor>

                            <b-taginput
                                v-model="blog.tagNames"
                                class="my-3"
                                maxlength="15"
                                maxtags="3"
                                ellipsis
                                placeholder="Please input the tag name, limited to 15 characters and 3 tags"
                            />
                            <el-form-item class="mt-3">
                                <el-button type="primary" @click="handleUpdate()">
                                    Update
                                </el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-card>
            </div>
        </div>
    </section>
</template>

<script>

    export default {
        data() {
            return {
                blog: {
                    blogId: null,
                    title: "",
                    content: "",
                    tagNames: []
                }
            };
        },
        created() {
            this.blog.blogId = this.$route.params.id
            if(this.blog.blogId == null) return
            this.$axios.get("/blog/" + this.blog.blogId).then(res => {
                const data = res.data.data
                this.blog.title = data.title
                this.blog.content = data.content
                this.blog.tagNames = data.tagNames
            })
        },
        methods: {
            handleUpdate: function () {
                this.$axios.post("/blog/edit", this.blog).then(res => {
                    this.$router.push({
                        name: "BlogDetail",
                        params: {id : res.data.data.id}
                    })
                })
            }
        },
    };
</script>

<style>

</style>
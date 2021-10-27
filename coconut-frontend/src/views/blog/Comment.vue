<template>
    <section class="box comments">
        <hr />
        <h3 class="title is-5">Comments</h3>
        <article class="media">
            <div class="media-content">
                <form @submit.prevent="onSubmit">
                    <b-field>
                        <b-input
                            v-model.lazy="curComment.content"
                            type="textarea"
                            maxlength="400"
                            placeholder="Add a comment..."
                        ></b-input>
                    </b-field>
                    <nav class="level">
                        <div class="level-left">
                            <b-button
                                type="is-primary"
                                native-type="submit"
                                class="level-item"
                            >
                                Comment
                            </b-button>
                        </div>
                    </nav>
                </form>
            </div>
        </article>

        <article v-for="(comment, index) in prevComments" :key="index"  class="media">
            <figure class="media-left image is-48x48">
                <img src="@/assets/logo.png">
            </figure>
            <div class="media-content">
                <div class="content" align="left">
                    <p>
                        <strong>{{ comment.username }}</strong>
                        <small class="ml-2">{{ comment.createTime.substr(0, 10) }}</small>
                        <br />
                        {{ comment.content }}
                    </p>
                </div>
            </div>
        </article>
    </section>
</template>

<script>
    export default {
        props: {
            blogId: {
                type: String,
                required: true
            }
        },
        data() {
            return {
                prevComments: '',
                curComment: {
                    content: '',
                    blogId: ''
                }
            }
        },
        created() {
            this.$axios.get("/comment/blog/" + this.blogId).then(res => {
                this.prevComments = res.data.data
            })
        },
        methods: {
            onSubmit() {
                this.curComment.blogId = this.blogId
                this.$axios.post("/comment/add", this.curComment).then(res => {
                    location.reload()
                })
            }
        }
    }
</script>

<style scoped>

</style>
<template>
    <div class="columns">
        <div class="column is-three-quarters">
            <el-card
                class="box-card"
                shadow="never"
            >
                <div
                    slot="header"
                    class="has-text-centered"
                >
                    <p class="is-size-5 has-text-weight-bold">{{ blog.title }}</p>
                    <div class="has-text-grey is-size-7 mt-3">
                        <span>{{ blog.createTime.substr(0, 10) }}</span>
                        <el-divider direction="vertical" />
                        <span>Blogger: {{ blog.username }}</span>
                        <el-divider direction="vertical" />
                        <span>View: {{ blog.view }}</span>
                    </div>
                </div>

                <div class="markdown-body" align="left" v-html="blog.content" />

                <nav class="level has-text-grey is-size-7 mt-6">
                    <div class="level-left">
                        <p class="level-item">
                            <b-taglist>
                                <router-link
                                    v-for="(tagName, index) in blog.tagNames"
                                    :key="index"
                                    :to="{ name: 'tag', params: { name: tagName } }"
                                >
                                    <b-tag type="is-info is-light mr-1">
                                        {{ "#" + tagName }}
                                    </b-tag>
                                </router-link>
                            </b-taglist>
                        </p>
                    </div>
                    <div
                            v-show="user != null && user.username == blog.username"
                            class="level-right"
                    >
                        <router-link
                            class="level-item"
                            :to="{name:'BlogUpdate',params: {id:blog.blogId}}"
                        >
                            <span class="tag">Edit</span>
                        </router-link>
                        <a class="level-item">
                            <span class="tag" @click="handleDelete(blog.blogId)">Delete</span>
                        </a>
                    </div>
                </nav>
            </el-card>

            <Comment :blogId="blog.blogId"/>
        </div>
        <div class="column">
            <section>
                <el-card class="" shadow="never">
                    <div slot="header">
                        <span class="has-text-weight-bold">👨‍💻 About Blogger</span>
                    </div>
                    <div class="has-text-centered">
                        <p class="is-size-5 mb-5">
                            <router-link :to="{ path: `/member/${author.username}/home` }">
                                {{ author.username }}
                            </router-link>
                        </p>
                        <div class="columns is-mobile">
                            <div class="column is-half">
                                <code>{{ author.blogCount }}</code>
                                <p>Blogs</p>
                            </div>
                            <div class="column is-half">
                                <code>{{ author.followerCount }}</code>
                                <p>Followers</p>
                            </div>
                        </div>
                        <div>
                            <button
                                v-if="isFollow"
                                class="button is-success button-center is-fullwidth"
                                @click="follow"
                            >
                                Unfollow
                            </button>
                            <button v-else class="button is-link button-center is-fullwidth" @click="follow()">
                                Follow
                            </button>
                        </div>
                    </div>
                </el-card>
            </section>
        </div>
    </div>
</template>

<script>
    import 'github-markdown-css/github-markdown.css'
    import { mapGetters } from 'vuex'
    import Comment from "./Comment"
    export default {
        components: { Comment },
        data() {
            return {
                blog: "",
                author: "",
                isFollow: false
            }
        },
        computed: {
            ...mapGetters([
                'token',
                'user'
            ])
        },
        created() {
            this.$axios.get("/blog/" + this.$route.params.id).then(res => {
                this.blog = res.data.data
                var MarkdownIt = require("markdown-it")
                var md = new MarkdownIt()
                this.blog.content = md.render(this.blog.content)
                if(this.token != null) {
                    this.$axios.get("/follow/isFollow/" + this.blog.userId).then(res => {
                        this.isFollow = res.data.data
                    })
                }
                this.$axios.get("/user/" + this.blog.userId).then(res => {
                    this.author = res.data.data
                })
            })
        },
        methods: {
            handleDelete(id) {
                this.$axios.delete("/blog/delete/" + id).then(res => {
                    console.log(res)
                })
            },
            follow() {
                this.$axios.get("/follow/" + this.blog.userId).then(res => {
                    this.isFollow = 1 - this.isFollow
                })
            }
        }
    }
</script>

<style>

</style>
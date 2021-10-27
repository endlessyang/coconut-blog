<template>
    <div>
        <el-card shadow="never">
            <div slot="header" class="clearfix">
                <article v-for="(blog, index) in blogs" :key="index" class="media">
                    <div class="media-left">
                        <figure class="image is-48x48">
                            <el-avatar style="border-radius: 5px;"> {{ blog.title[0] }} </el-avatar>
                        </figure>
                    </div>
                    <div class="media-content">
                        <div class="">
                            <p class="ellipsis is-ellipsis-1">
                                <el-tooltip class="item" effect="dark" :content="blog.title" placement="top">
                                    <router-link :to="{ name:'BlogDetail', params:{ id: blog.blogId }}">
                                        <span class="level-left">{{ blog.title }}</span>
                                    </router-link>
                                </el-tooltip>
                            </p>
                        </div>
                        <nav class="level has-text-grey is-mobile  is-size-7 mt-2">
                            <div class="level-left">
                                <div class="level-left">
                                    <router-link class="level-item" :to="{ path: `/user/${blog.userId}` }">
                                        {{ blog.username }}
                                    </router-link>

                                    <span class="mr-1">
                                        Posted On:{{ blog.createTime.substr(0, 10) }}
                                    </span>

                                    <span
                                        v-for="(tagName, index) in blog.tagNames"
                                        :key="index"
                                        class="tag is-hidden-mobile is-success is-light mr-1"
                                    >
                                        <router-link :to="{ name: 'tag', params: { name: tagName } }">
                                            {{ "#" + tagName }}
                                        </router-link>
                                    </span>

                                    <span class="is-hidden-mobile">View:{{ blog.view }}</span>
                                </div>
                            </div>
                        </nav>
                    </div>
                    <div class="media-right" />
                </article>
            </div>

            <el-pagination
                v-show="page.total > 0"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="page.current"
                :page-size="page.size"
                layout="total, sizes, prev, pager, next, jumper"
                :total="page.total">
            </el-pagination>
        </el-card>
    </div>
</template>

<script>
    export default {
        props: {
            opt: {
                type: String,
                required: true
            },
            val: {
                type: String,
                required: true
            }
        },
        data() {
            return {
                blogs: [],
                page: {
                    current: 1,
                    size: 10,
                    total: 0
                }
            }
        },
        created() {
            this.getPage()
        },
        methods: {
            getPage() {
                this.$axios.get("/blog/list?pageNum=" + this.page.current + "&pageSize=" + this.page.size + "&opt=" + this.opt + "&val=" + this.val).then(res => {
                    const data = res.data.data
                    this.page.current = data.current
                    this.page.total = data.total
                    this.page.size = data.size
                    this.blogs = data.records
                })
            },
            handleSizeChange(size) {
                this.page.size = size
                this.getPage()
            },
            handleCurrentChange(current) {
                this.page.current = current
                this.getPage()
            }
        }
    }
</script>

<style scoped>

</style>
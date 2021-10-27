<template>
    <header class="header has-background-white has-text-black">
        <b-navbar
            class="container is-white"
            :fixed-top="true"
        >
            <template slot="start">
                <b-navbar-item
                    tag="router-link"
                    :to="{ path: '/' }"
                >
                    🏠 Main Page
                </b-navbar-item>
            </template>

            <template slot="end">
                <b-navbar-item tag="div">
                    <b-field position="is-centered">
                        <b-input
                            v-model="val"
                            class="s_input"
                            width="80%"
                            placeholder="Title"
                            rounded
                            clearable
                        />
                        <p class="control">
                            <b-button
                                class="is-info"
                                tag="router-link"
                                :to="{ path: `/blog/search/${val}` }"
                            >
                                Search
                            </b-button>
                        </p>
                    </b-field>
                </b-navbar-item>

                <b-navbar-item
                    v-if="token == null"
                    tag="div"
                >
                    <div class="buttons">
                        <b-button
                            class="is-light"
                            tag="router-link"
                            :to="{ path: '/register' }"
                        >
                            Sign Up
                        </b-button>
                        <b-button
                            class="is-light"
                            tag="router-link"
                            :to="{ path: '/login' }"
                        >
                            Sign In
                        </b-button>
                    </div>
                </b-navbar-item>

                <b-navbar-dropdown
                    v-else
                    :label="user.username"
                >
                    <b-navbar-item
                        tag="router-link"
                        :to="{ path: `/user/${user.userId}` }"
                    >
                        🧘 Profile
                    </b-navbar-item>
                    <hr class="dropdown-divider">
                    <b-navbar-item
                        tag="router-link"
                        :to="{ path: `` }"
                    >
                        ⚙ Setting
                    </b-navbar-item>
                    <hr class="dropdown-divider">
                    <b-navbar-item
                        tag="a"
                        @click="logout"
                    >
                        👋 Log Out
                    </b-navbar-item>
                </b-navbar-dropdown>
            </template>
        </b-navbar>
    </header>
</template>

<script>
    import { mapGetters } from 'vuex'

    export default {
        data() {
            return {
                val: ''
            }
        },
        computed: {
            ...mapGetters([
                'token',
                'user'
            ])
        },
        methods: {
            logout() {
                this.$store.commit("REMOVE_INFO")
                location.reload()
            }
        }
    }
</script>

<style scoped>
    input {
        width: 80%;
        height: 86%;
    }
</style>
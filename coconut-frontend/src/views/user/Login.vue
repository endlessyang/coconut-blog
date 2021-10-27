<template>
    <div class="columns py-6">
        <div class="column is-half is-offset-one-quarter">
            <el-card shadow="never">
                <div slot="header" class="has-text-centered has-text-weight-bold">
                    Sign In
                </div>
                <div>
                    <el-form
                        :model="ruleForm"
                        status-icon
                        :rules="rules"
                        ref="ruleForm"
                        label-width="100px"
                        class="demo-ruleForm"
                    >
                        <el-form-item label="Username" prop="name">
                            <el-input v-model="ruleForm.username"></el-input>
                        </el-form-item>

                        <el-form-item label="Password" prop="password">
                            <el-input
                                type="password"
                                v-model="ruleForm.password"
                                autocomplete="off"
                            ></el-input>
                        </el-form-item>

                        <el-form-item>
                            <el-button type="primary" @click="submitForm('ruleForm')">Submit</el-button>
                            <el-button @click="resetForm('ruleForm')">Reset</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {
                    username: "",
                    password: "",
                },
                rules: {
                    username: [
                        { required: true, message: "请输入账号", trigger: "blur" },
                        {
                            min: 2,
                            max: 15,
                            message: "长度在 2 到 15 个字符",
                            trigger: "blur",
                        },
                    ],
                    password: [
                        { required: true, message: "请输入密码", trigger: "blur" },
                        {
                            min: 6,
                            max: 20,
                            message: "长度在 6 到 20 个字符",
                            trigger: "blur",
                        },
                    ],
                },
            }
        },
        methods: {
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.post("/user/login", this.ruleForm).then(res => {
                            const data = res.data.data.split(' ')
                            const userId = data[0]
                            const token = data[1]
                            const username = this.ruleForm.username
                            _this.$store.commit("SET_TOKEN", token)
                            _this.$store.commit("SET_USER", { 'username': username, 'userId': userId })
                            _this.$router.push("/")
                            location.reload()
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped>

</style>
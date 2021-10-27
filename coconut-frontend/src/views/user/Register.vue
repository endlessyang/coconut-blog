<template>
    <div class="columns py-6">
        <div class="column is-half is-offset-one-quarter">
            <el-card shadow="never">
                <div slot="header" class="has-text-centered has-text-weight-bold">
                    Sign Up
                </div>
                <div>
                    <el-form
                        ref="ruleForm"
                        :model="ruleForm"
                        status-icon
                        :rules="rules"
                        label-width="100px"
                        class="demo-ruleForm"
                    >
                        <el-form-item label="Username" prop="name">
                            <el-input v-model="ruleForm.username" />
                        </el-form-item>

                        <el-form-item label="Password" prop="password">
                            <el-input
                                v-model="ruleForm.password"
                                type="password"
                                autocomplete="off"
                            />
                        </el-form-item>

                        <el-form-item label="Confirm Password" prop="checkPassword">
                            <el-input
                                v-model="ruleForm.checkPassword"
                                type="password"
                                autocomplete="off"
                            />
                        </el-form-item>

                        <el-form-item label="Email" prop="email">
                            <el-input v-model="ruleForm.email" autocomplete="off" />
                        </el-form-item>

                        <el-form-item>
                            <el-button
                                type="primary"
                                @click="submitForm('ruleForm')"
                            >
                                Submit
                            </el-button>
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
            const validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'))
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('两次输入密码不一致'))
                } else {
                    callback()
                }
            }
            return {
                ruleForm: {
                    username: '',
                    password: '',
                    checkPassword: '',
                    email: ''
                },
                rules: {
                    username: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                        {
                            min: 2,
                            max: 10,
                            message: '长度在 2 到 10 个字符',
                            trigger: 'blur'
                        }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        {
                            min: 6,
                            max: 20,
                            message: '长度在 6 到 20 个字符',
                            trigger: 'blur'
                        }
                    ],
                    checkPassword: [
                        { required: true, message: '请再次输入密码', trigger: 'blur' },
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '请输入邮箱地址', trigger: 'blur' },
                        {
                            type: 'email',
                            message: '请输入正确的邮箱地址',
                            trigger: ['blur', 'change']
                        }
                    ]
                }
            }
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/user/register', this.ruleForm).then(res => {
                            this.$router.push("/login")
                        })
                    } else {
                        return false
                    }
                })
            },
            resetForm(formName) {
                this.$refs[formName].resetFields()
            }
        }
    }
</script>

<style scoped>

</style>
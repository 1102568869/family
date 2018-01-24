<template>
    <div>
        <el-dialog title="请登录" :visible="dialogFormVisible" width="30%">
            <el-form :model="form" ref="loginForm" :rules="rules">
                <el-form-item label="成员账号" label-width="150" prop="account">
                    <el-input v-model="form.account" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="登录密码" label-width="150" prop="password">
                    <el-input v-model="form.password" auto-complete="off" type="password"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer">
                <el-button @click="reset('loginForm')">重置</el-button>
                <el-button type="primary" @click="login('loginForm')">登录</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    /*eslint-disable*/
    import ElForm from "element-ui/packages/form/src/form";

    import {ajaxGet, ajaxPost} from "@/common/httpUtil";
    import {setCookie} from "@/common/commonUtil";


    export default {
        components: {ElForm},
        name: "login",
        data() {
            return {
                loading: false,
                isSubmitting: false,
                dialogFormVisible: true,
                form: {
                    account: undefined,
                    password: undefined
                },
                rules: {
                    account: [{required: true, message: '请输入账号', trigger: 'blur'}],
                    password: [{required: true, message: '请输入密码', trigger: 'blur'}]
                }
            }
        },
        methods: {
            reset(formName) {
                this.$refs[formName].resetFields();
                this.isSubmitting = false;

            },
            login(formName) {
                var vm = this;
                if (vm.isSubmitting) {
                    vm.$message.warning('请勿重复提交!!!');
                    return false;
                }
                vm.isSubmitting = true;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        ajaxPost(apis._login, this.form, (result) => {
                            if (result===true) {
                                vm.$message({
                                    message: '登陆成功,页面跳转中!',
                                    duration: 1000,
                                    type: 'success',
                                    onClose: function (msg) {
                                        vm.$router.push(vm.$route.query.redirect || '/bill');
                                    }
                                });
                            } else {
                                vm.$message.error('账号或者密码错误!');
                                vm.isSubmitting = false;
                            }
                        }, (e) => {
                            vm.$message.error('系统异常,请联系管理员!');
                            vm.isSubmitting = false;
                        });

                    } else {
                        console.log('error submit!!');
                        vm.isSubmitting = false;
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>

</style>
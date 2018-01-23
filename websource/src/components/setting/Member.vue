<template>
    <div>
        <div class="list">
            <el-row>
                <el-col :span="4" v-for="(m, index) in members" :key="m.id" :offset="index % 5 == 0 ? 0 : 1"
                        style="margin-bottom: 20px;">
                    <el-card>
                        <img :src="m.image" class="image">
                        <div style="padding: 14px;height: 180px">
                            <span><strong>姓名:{{m.name}}</strong></span>
                            <span>QQ:{{m.qq}}</span>
                            <span><i class="el-icon-phone"></i>{{m.mobile}}</span>
                            <span><i class="el-icon-message"></i>{{m.email}}</span>
                            <div class="bottom">
                                <el-button type="text" class="button" @click="showDialog(m.id)">编辑</el-button>&nbsp;
                                <!--<el-button type="text" class="button">删除</el-button>-->
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
            <el-button type="primary" @click="showDialog">添加成员</el-button>
            <el-button type="primary" @click="changePwdDialogFormVisible=true">修改密码</el-button>
        </div>
        <div class="dialog">
            <el-dialog :title="dialogTitle" :visible="dialogFormVisible" width="30%">
                <el-form :model="formMember" ref="memberForm" :rules="rules">
                    <form-item :data="formMember" :items="itemsMap"></form-item>
                </el-form>
                <div slot="footer">
                    <el-button @click="dialogFormVisible=false">关闭</el-button>
                    <el-button type="primary" @click="submitData('memberForm')">提交</el-button>
                </div>
            </el-dialog>
            <el-dialog title="修改密码" :visible="changePwdDialogFormVisible" width="30%">
                <el-form :model="pwdForm" ref="passwordForm" :rules="pwdRules">
                    <form-item :data="pwdForm" :items="changePwdItems"></form-item>
                </el-form>
                <div slot="footer">
                    <el-button @click="changePwdDialogFormVisible=false">关闭</el-button>
                    <el-button type="primary" @click="changePwd('passwordForm')">提交</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>


<script>
    import {ajaxGet, ajaxPost} from "@/common/httpUtil";
    import FormItem from "@/components/base/FormItem";
    import ElForm from "element-ui/packages/form/src/form";
    import {removeCookie} from "@/common/commonUtil";

    export default {
        name: 'member',
        components: {
            ElForm,
            FormItem
        },
        created() {
            this.getMmebers();
        },
        data() {
            return {
                dialogTitle: '',
                dialogFormVisible: false,
                changePwdDialogFormVisible: false,
                isSubmitting: false,
                members: [],
                formMember: {
                    password: '123456'
                    // name: '',
                    // qq: null,
                    // email: '',
                    // mobile: '',
                    // account: ''
                },
                pwdForm: {
                    password: '',
                    oldPassword: '',
                    newPassword: '',
                },
                changePwdItems: {
                    oldPassword: {
                        type: 'password',
                        label: '原密码',
                        placeholder: '请输入原登录密码',
                    },
                    password: {
                        type: 'password',
                        label: '新密码',
                        placeholder: '请输入新登录密码',
                    },
                    newPassword: {
                        type: 'password',
                        label: '确认密码',
                        placeholder: '请确认新登录密码',
                    },
                },
                itemsMap: {
                    name: {
                        label: '姓名',
                        placeholder:
                            '请输入姓名',
                    },
                    account: {
                        label: '账号',
                        placeholder:
                            '请输入账号,账号唯一,创建后不可修改!初始密码123456',
                        disabled:
                            false
                    },
                    mobile: {
                        label: '手机号',
                        placeholder:
                            '请输入手机号码',
                    },
                    email: {
                        label: '邮箱地址',
                        placeholder:
                            '请输入邮箱地址',
                    },
                    qq: {
                        label: 'QQ号',
                        placeholder:
                            '请输入QQ号',
                    },
                    image: {
                        label: '头像地址',
                        placeholder:
                            '请输入头像Url',
                    },
                },
                rules: {
                    name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
                    account:
                        [{required: true, message: '请输入账号', trigger: 'blur'}],
                }
                ,
                pwdRules: {
                    oldPassword: [
                        {validator: this.validateOldPass, trigger: 'blur'}
                    ],
                    password: [
                        {validator: this.validatePass, trigger: 'blur'}
                    ],
                    newPassword: [
                        {validator: this.validateNewPass, trigger: 'blur'}
                    ]
                },

            }
        },
        watch: {
            dialogFormVisible: function (newValue, oldValue) {
                if (newValue === false && oldValue === true) {
                    this.formMember = {};
                    this.isSubmitting = false;
                    this.itemsMap.account.disabled = false;
                }
            },
            changePwdDialogFormVisible: function (newValue, oldValue) {
                if (newValue === false && oldValue === true) {
                    this.pwdForm = {};
                    this.isSubmitting = false;
                }
            },
        },
        methods: {
            validateOldPass(rule, value, callback) {
                var vm = this;
                setTimeout(() => {
                    ajaxPost(apis._verifyPassword, {password: this.pwdForm.oldPassword}, (result) => {
                        if (!!result) {
                            callback();
                        } else {
                            callback(new Error('原密码错误!'));
                        }
                    }, (e) => {
                        callback(new Error(e));
                    });
                }, 1500);
            },
            validatePass(rule, value, callback) {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.pwdForm.newPassword !== '') {
                        this.$refs['passwordForm'].validateField('newPassword');
                    }
                    callback();
                }
            },
            validateNewPass(rule, value, callback) {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.pwdForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            },
            getMmebers() {
                ajaxGet(apis._familymember_get_all, (data) => this.members = data);
            },
            showDialog(id) {
                let title = "添加成员";
                if (typeof id === 'number') {
                    title = "编辑成员";
                    this.itemsMap.account.disabled = true;
                    ajaxGet(apis._familymember_get_item + '?id=' + id, (data) => this.formMember = data);
                }
                this.dialogTitle = title;
                this.dialogFormVisible = true;
            },
            submitData(formName) {
                if (this.formMember.id) {
                    this.editMember(formName);
                } else {
                    this.addMember(formName);
                }
            },
            changePwd(formName) {
                var vm = this;
                if (vm.isSubmitting) {
                    vm.$message.warning('请勿重复提交!!!');
                    return false;
                }
                vm.isSubmitting = true;
                vm.$refs[formName].validate((valid) => {
                    if (valid) {
                        ajaxPost(apis._familymember_post_changePassword, this.pwdForm, (result) => {
                            if (!!result) {
                                vm.$message({
                                    message: '密码修改成功!请重新登陆',
                                    duration: 1000,
                                    type: 'success',
                                    onClose: function (msg) {
                                        vm.changePwdDialogFormVisible = false;
                                        vm.isSubmitting = false;
                                        removeCookie('family_token');
                                        vm.$router.push({
                                            path: '/login',
                                            query: {redirect: vm.fullPath}
                                        });
                                    }
                                });
                            } else {
                                vm.isSubmitting = false;
                                vm.$message.error('密码修改失败!');
                            }
                        }, (msg) => {
                            vm.$message.error(msg);
                            vm.isSubmitting = false;
                        });
                    } else {
                        console.log('error submit!!');
                        vm.isSubmitting = false;
                        return false;
                    }
                });
            },
            editMember: function (formName) {
                var vm = this;
                if (vm.isSubmitting) {
                    vm.$message.warning('请勿重复提交!!!');
                    return false;
                }
                vm.isSubmitting = true;
                vm.$refs[formName].validate((valid) => {
                    if (valid) {
                        ajaxPost(apis._familymember_post_update, this.formMember, (result) => {
                            if (!!result) {
                                vm.$message({
                                    message: '成员资料编辑成功!',
                                    type: 'success',
                                    onClose: function (msg) {
                                        vm.dialogFormVisible = false;
                                        vm.isSubmitting = false;
                                        vm.getMmebers();
                                    }
                                });
                            } else {
                                vm.isSubmitting = false;
                                vm.$message.error('成员资料编辑失败!');
                            }
                        }, (msg) => {
                            vm.$message.error(msg);
                            vm.isSubmitting = false;
                        });

                    } else {
                        console.log('error submit!!');
                        vm.isSubmitting = false;
                        return false;
                    }
                });
            },

            addMember(formName) {
                var vm = this;
                if (vm.isSubmitting) {
                    vm.$message.warning('请勿重复提交!!!');
                    return false;
                }
                vm.isSubmitting = true;
                vm.$refs[formName].validate((valid) => {
                    if (valid) {
                        ajaxPost(apis._familymember_post_add, this.formMember, (result) => {
                            if (!!result) {
                                vm.$message({
                                    message: '成员添加成功!',
                                    type: 'success',
                                    onClose: function (msg) {
                                        vm.dialogFormVisible = false;
                                        vm.isSubmitting = false;
                                        vm.formMember = {};
                                        vm.getMmebers();
                                    }
                                });
                            } else {
                                vm.isSubmitting = false;
                                vm.$message.error('成员添加失败!');
                            }
                        }, (msg) => {

                            vm.$message.error(msg);
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

    span {
        word-wrap: break-word;
        display: block;
        margin-bottom: 10px;
    }

    .time {
        font-size: 13px;
        color: #999;
    }

    .bottom {
        margin-top: 50px;
        line-height: 12px;
    }

    .button {
        padding: 5px;
        float: right;
    }

    .image {
        width: 100%;
        min-height: 232px;
        display: block;
    }


</style>

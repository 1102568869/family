<template>
    <div>
        <div class="list">
            <el-row>
                <el-col :span="4" v-for="(m, index) in members" :key="m.id" :offset="index % 5!=0 ? 1 : 0"
                        style="margin-bottom: 20px;">
                    <el-card>
                        <img :src="m.image" class="image">
                        <div style="padding: 14px;height: 180px">
                            <span><strong>姓名:{{m.name}}</strong></span>
                            <span>QQ:{{m.qq}}</span>
                            <span><i class="el-icon-phone"></i>{{m.mobile}}</span>
                            <span><i class="el-icon-message"></i>{{m.email}}</span>
                            <div class="bottom">
                                <el-button type="text" class="button">编辑</el-button>&nbsp;
                                <!--<el-button type="text" class="button">删除</el-button>-->
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
            <el-button type="primary" @click="showDiaLog('添加新成员')">添加新成员</el-button>
        </div>
        <div class="dialog">
            <el-dialog :title="dialogTitle" :visible="dialogFormVisible" width="30%">
                <el-form :model="formMember" ref="memberForm" :rules="rules">
                    <form-item :data="formMember" :items="items"></form-item>

                    <!--<el-form-item label="成员账号" label-width="150" prop="account">-->
                    <!--<el-input v-model="formMember.name" auto-complete="off"></el-input>-->
                    <!--</el-form-item>-->
                    <!--<el-form-item label="登录密码" label-width="150" prop="password">-->
                    <!--<el-input v-model="formMember.account" auto-complete="off"></el-input>-->
                    <!--</el-form-item>-->
                </el-form>
                <div slot="footer">
                    <el-button @click="dialogFormVisible=false">关闭</el-button>
                    <el-button type="primary" @click="submitData('memberForm')">提交</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>


<script>
    import {ajaxGet, ajaxPost} from "@/common/httpUtil";
    import FormItem from "@/components/base/FormItem";
    import ElForm from "element-ui/packages/form/src/form";

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
                isSubmitting: false,
                members: [],
                formMember: {
                    // name: '',
                    // qq: null,
                    // email: '',
                    // mobile: '',
                    // account: ''
                },
                items: [{
                    label: '姓名',
                    placeholder: '请输入姓名',
                    modelName: 'name',
                }, {
                    label: '账号',
                    placeholder: '请输入账号',
                    modelName: 'account',
                }, {
                    label: '密码',
                    placeholder: '请输入密码',
                    modelName: 'password',
                }, {
                    label: '头像地址',
                    placeholder: '请输入头像Url',
                    modelName: 'image',
                }],
                rules: {
                    name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
                    account: [{required: true, message: '请输入账号', trigger: 'blur'}],
                    password: [{required: true, message: '请输入密码', trigger: 'blur'}],
                },

            }
        },
        methods: {
            getMmebers() {
                ajaxGet('/familymember/get/all', (data) => this.members = data);
            },
            showDiaLog(title) {
                this.dialogTitle = title;
                this.dialogFormVisible = true;
            },
            submitData(formName) {
                if (this.formMember.id) {
                    //this.editMember(formName);
                } else {
                    this.addMember(formName);
                }
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
                        ajaxPost('/familymember/post/add', this.formMember, (result) => {
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
                        }, (e) => {
                            console.error(e);
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

<template>
    <div class="hello">
        <el-form :model="bill" :rules="rules" ref="billForm" label-width="100px" style="width: 600px">
            <bill-form :bill="bill"></bill-form>
            <el-form-item>
                <el-button type="primary" @click="onSubmit('billForm')">立即创建</el-button>
                <el-button @click="resetFrom('billForm')">重置内容</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    /*eslint-disable*/
    import ElForm from "element-ui/packages/form/src/form";

    import {ajaxGet, ajaxPost} from "@/common/httpUtil";
    import BillForm from "@/components/bill/BillForm";

    export default {
        components: {ElForm,BillForm},
        name: 'editBill',
        data() {
            return {
                bill: {
                    name: '',
                    balance: 0,
                    money: null,
                    comment: '',
                    member: null,
                    recordtime: null,
                    type: '',
                },
                rules: {
                    money: [
                        {required: true, message: '请输入金额', trigger: 'blur'},
                        {type: 'number', message: '金额必须为数字值', trigger: 'blur'}
                    ],
                    name: [{required: true, message: '请输入事项内容', trigger: 'blur'}],
                    type: [{required: true, message: '请选择事项类型', trigger: 'blur'}],
                    balance: [{required: true, message: '请选择收支类型', trigger: 'blur'}],
                    member: [{required: true, message: '请选择经手人', trigger: 'blur'}],
                    recordtime: [{type: 'date', required: true, message: '请选择时间', trigger: 'change'}
                    ]
                },
                types: [],
                members: [],
                balanceTypes: []
            }
        },
        methods: {
            resetFrom(formName) {
                this.$refs[formName].resetFields();
            },
            onSubmit(formName) {
                var vm = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        ajaxPost('http://localhost:8888/bill/post/update', this.bill, (result) => {
                            if (!!result) {
                                this.$message({
                                    message: '账单更新成功!',
                                    type: 'success'
                                });
                            } else {
                                this.$message.error('提交账单失败!');
                            }
                        }, (e) => this.$message.error('提交账单失败!'));
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
        ,
        beforeRouteEnter(to, from, next) {
            // 在渲染该组件的对应路由被 confirm 前调用
            // 不！能！获取组件实例 `this`
            // 因为当守卫执行前，组件实例还没被创建
            next((vm) => {
                let id = vm.$route.params.id;
                ajaxGet('http://localhost:8888/bill/get/item?id=' + id, (data) => vm.bill = data)
            });
        },
        beforeRouteUpdate(to, from, next) {
            // react to route changes...
            // don't forget to call next()
            let id = to.params.id;
            console.info("-----", id);
            ajaxGet('http://localhost:8888/bill/get/item?id=' + id, (data) => this.bill = data);
            next();
        }
    }
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    td {
        text-align: right;
        padding-top: 20px;
    }
</style>

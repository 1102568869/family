<template>
    <div id="add">
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

    import BillForm from "@/components/bill/BillForm";
    import {ajaxGet, ajaxPost} from "@/common/httpUtil";


    export default {
        components: {
            ElForm,
            BillForm
        },
        name: 'addBill',
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
        created() {
        },
        methods: {
            resetFrom(formName) {
                this.$refs[formName].resetFields();
                this.isSubmitting = false;

            },
            onSubmit(formName) {
                var vm = this;
                if (vm.isSubmitting) {
                    vm.$message.warning('请勿重复提交!!!');
                    return false;
                }
                vm.isSubmitting = true;
                vm.$refs[formName].validate((valid) => {
                    if (valid) {
                        ajaxPost('/bill/post/add', this.bill, (result) => {
                            if (!!result) {
                                vm.$message({
                                    message: '账单添加成功!',
                                    type: 'success',
                                    duration: 1000,
                                    onClose: function (msg) {
                                        vm.$router.push({name: 'bill'});
                                    }
                                });
                            } else {
                                vm.$message.error('提交账单失败!');
                                vm.isSubmitting = false;
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
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    td {
        text-align: right;
        padding-top: 20px;
    }
</style>

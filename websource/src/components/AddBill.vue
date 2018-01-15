<template>
    <div class="hello">
        <el-form :model="bill" :rules="rules" ref="billForm" label-width="100px" style="width: 600px">
            <el-form-item label="金额" prop="money">
                <el-input placeholder="请输入金额" v-model.number="bill.money" auto-complete="off">
                    <el-select v-model="bill.balance" prop="balance" slot="prepend" placeholder="请选择"
                               style="width: 120px;">
                        <el-option
                                v-for="type in balanceTypes"
                                :key="type.value"
                                :label="type.name"
                                :value="type.value">
                        </el-option>
                    </el-select>
                </el-input>
            </el-form-item>
            <el-form-item label="事项" prop="name">
                <el-input placeholder="请输入内容" v-model="bill.name" clearable>
                    <el-select v-model="bill.type" prop="type" slot="prepend" placeholder="请选择类型" style="width: 120px;">
                        <el-option
                                v-for="type in types"
                                :key="type.id"
                                :label="type.name"
                                :value="type.id">
                        </el-option>
                    </el-select>
                </el-input>
            </el-form-item>
            <el-form-item label="说明">
                <el-input placeholder="请输入内容" v-model="bill.comment" clearable
                          type="textarea"
                          autosize
                          :rows="6">
                </el-input>
            </el-form-item>
            <el-form-item label="经手人" prop="member">
                <el-select v-model="bill.member" placeholder="请选择">
                    <el-option
                            v-for="member in members"
                            :key="member.id"
                            :label="member.name"
                            :value="member.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="经手时间" prop="recordtime">
                <el-date-picker
                        v-model="bill.recordtime"
                        type="datetime"
                        placeholder="选择日期时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit('billForm')">立即创建</el-button>
                <el-button @click="resetFrom('billForm')">重置内容</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    /*eslint-disable*/
    import axios from "axios";
    import ElForm from "element-ui/packages/form/src/form";

    export default {
        components: {ElForm},
        name: 'addBill',
        created() {
            this.getTypes();
            this.getMmebers();
            this.getBalanceTypes();
            console.info("页面初始化完成!");
        },
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
                balanceTypes: [],
                msg: 'Welcome to Your Vue.js App'
            }
        },
        methods: {
            getTypes() {
                var vm = this;
                axios.get('http://localhost:8888/billtype/get/all')
                    .then(function (response) {
                        vm.types = response.data
                        console.info(vm.types);
                    })
                    .catch(function (error) {
                        console.error("请求API失败!");
                    })
            },
            getMmebers() {
                var vm = this;
                axios.get('http://localhost:8888/familymember/get/all')
                    .then(function (response) {
                        vm.members = response.data
                        console.info(vm.members);
                    })
                    .catch(function (error) {
                        console.error("请求API失败!");
                    })
            },
            getBalanceTypes() {
                var vm = this;
                axios.get('http://localhost:8888/bill/get/balanceTypes')
                    .then(function (response) {
                        vm.balanceTypes = response.data
                        console.info(vm.balanceTypes);
                    })
                    .catch(function (error) {
                        console.error("请求API失败!");
                    })
            },
            resetFrom(formName) {
                this.$refs[formName].resetFields();
            },
            onSubmit(formName) {
                var vm = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('http://localhost:8888/bill/post/add', this.bill)
                            .then(function (response) {
                                if (response.data === true) {
                                    vm.$message({
                                        message: '账单提交成功!',
                                        type: 'success'
                                    });
                                } else {
                                    vm.$message.error('提交账单失败!');
                                }
                                console.info(response.data);
                            })
                            .catch(function (error) {
                                vm.$message.error('提交账单失败!');
                            })
                    } else {
                        console.log('error submit!!');
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

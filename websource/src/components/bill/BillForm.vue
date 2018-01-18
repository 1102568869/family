<template>
    <div class="hello">
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

    </div>
</template>

<script>
    import ElForm from "element-ui/packages/form/src/form";
    import {ajaxGet} from "@/common/httpUtil";

    export default {
        components: {ElForm},
        props: {bill: Object},
        name: 'BillForm',
        componentName: 'BillForm',
        created() {
            this.getTypes();
            this.getMmebers();
            this.getBalanceTypes();
        },
        data() {
            return {
                types: [],
                members: [],
                balanceTypes: []
            }
        },
        methods: {
            getTypes() {
                ajaxGet('/billtype/get/all', (data) => this.types = data);
            },
            getMmebers() {
                ajaxGet('/familymember/get/all', (data) => this.members = data);
            },
            getBalanceTypes() {
                ajaxGet('/bill/get/balanceTypes', (data) => this.balanceTypes = data);
            }
        }
    }
</script>


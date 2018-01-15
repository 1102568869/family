<template>
    <div class="manageBill">
        <el-table v-loading="loading"
                  :data="page.list"
                  style="width: 100%">
            <el-table-column
                    prop="memberName"
                    label="经手人"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="事项"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="money"
                    label="金额"
                    :formatter="formatMoney"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="typeName"
                    label="类型"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="balanceName"
                    label="收支"
                    width="80">
            </el-table-column>
            <el-table-column
                    prop="recordtime"
                    :formatter="formatDate"
                    label="经手日期"
                    width="180">
            </el-table-column>
            <el-table-column
                    label="事项说明">
                <template slot-scope="scope">
                    <span v-html="formatComment(scope.row.comment)"></span>
                </template>
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="150">
                <template slot-scope="scope">
                    <i class="el-icon-edit" @click="editBill(scope.row.id)"></i>
                    &nbsp;
                    <i class="el-icon-delete" @click="deleteBill(scope.row.id)"></i>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                @size-change="changePageSize"
                @current-change="changePageNo"
                :current-page="params.pageNo"
                :page-sizes="[10,20,50,100]"
                :page-size="params.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="page.totalCount">
        </el-pagination>
    </div>
</template>

<script>
    /*eslint-disable*/
    import axios from "axios";
    import ElForm from "element-ui/packages/form/src/form";
    // import Common from '@/components/Common';

    import {formatDate} from "../common/commonUtil";

    export default {
        components: {ElForm},
        name: 'addBill',
        created() {
            this.searchPage();
            console.info("页面初始化完成!");
        },
        data() {
            return {
                loading: false,
                params: {
                    pageSize: 10,
                    pageNo: 1
                },
                page: {
                    list: [],
                    totalCount: 0,
                }
            }
        },
        methods: {
            changePageSize(size) {
                this.params.pageSize = size;
                this.searchPage();
            },
            changePageNo(no) {
                this.params.pageNo = no;
                this.searchPage();
            },
            searchPage() {
                var vm = this;
                vm.loading = true;
                console.info(vm.params.pageSize);
                console.info(vm.params.pageNo);
                axios.get('http://localhost:8888/bill/get/page?pageSize=' + vm.params.pageSize + '&pageNo=' + vm.params.pageNo)
                    .then(function (response) {
                        vm.page = response.data
                        console.info(vm.page);
                        vm.loading = false;
                    })
                    .catch(function (error) {
                        console.error("请求API失败!");
                        vm.loading = false;
                    })
            },
            onSubmit() {

            },
            formatDate(row, column, cellValue) {
                return formatDate(new Date(cellValue));
            },
            formatMoney(row, column, cellValue) {
                console.info(row, column, cellValue);
                return Math.abs(cellValue);
            },
            formatComment(comment) {
                //let comment = row.comment;
                return comment != null ? comment.replace(/\n/g, '<br/>') : '';
            },
            editBill(id) {
                console.info(id)
                this.$router.push({name: 'editBill', params: {"id": id}});
            },
            deleteBill(id) {

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

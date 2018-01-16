<template>
    <div class="manageBill">
        <el-table v-loading="loading"
                  :data="page.list"
                  stripe
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

    import {formatDate} from "@/common/commonUtil";
    import {ajaxGet} from "@/common/httpUtil";

    export default {
        components: {ElForm},
        name: 'addBill',
        created() {
            this.searchPage();
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
                this.loading = true;
                ajaxGet('http://localhost:8888/bill/get/page?pageSize=' + this.params.pageSize + '&pageNo=' + this.params.pageNo, (data) => {
                    this.page = data;
                    this.loading = false;
                }, (e) => {
                    console.error("请求API失败!", e);
                    this.loading = false;
                });
            },
            formatMoney(row, column, cellValue) {
                return cellValue;
            },
            formatComment(comment) {
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

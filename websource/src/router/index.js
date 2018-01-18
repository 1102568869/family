import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import AddBill from '@/components/bill/AddBill'
import ManageBill from '@/components/bill/ManageBill'
import EditBill from '@/components/bill/EditBill'
import Login from '@/components/Login'
import Member from '@/components/setting/Member'


import {getCookie} from '@/common/commonUtil'
import {ajaxGet, ajaxPost} from "@/common/httpUtil";


Vue.use(Router)
const router = new Router({
    routes: [
        {
            path: '/',
            component: Main,
            children: [
                {
                    path: '',
                    component: ManageBill
                },
                {
                    path: 'bill',
                    name: 'bill',
                    component: ManageBill
                },
                {
                    path: 'bill/add',
                    name: 'addBill',
                    component: AddBill
                },
                {
                    path: 'bill/edit/:id',
                    name: 'editBill',
                    component: EditBill
                },
                {
                    path: 'member',
                    name: 'member',
                    component: Member
                }
            ]
        },
        {
            path: '/login',
            name: 'login',
            component: Login,
            meta: {
                loginNeedless: true
            }
        }


    ]
})
export default router;

router.beforeEach((to, from, next) => {
    if (!to.matched.some(record => record.meta.loginNeedless)) {  // 判断该路由是否需要登录权限
        let token = getCookie("family_token");
        if (token || token.length > 0) {
            next();
        } else {
            next({
                path: '/login',
                query: {redirect: to.fullPath}  // 将跳转的路由path作为参数，登录成功后跳转到该路由
            })
        }
    } else {
        next();
    }
});



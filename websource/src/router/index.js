import Vue from 'vue'
import Router from 'vue-router'
import AddBill from '@/components/bill/AddBill'
import ManageBill from '@/components/bill/ManageBill'
import EditBill from '@/components/bill/EditBill'

import {getCookie} from '@/common/commonUtil'
import {ajaxGet, ajaxPost} from "@/common/httpUtil";

Vue.use(Router)
const router = new Router({
    routes: [
        {
            path: '/',
            name: 'index',
            component: ManageBill
        },
        {
            path: '/bill/add',
            name: 'addBill',
            component: AddBill
        },
        {
            path: '/bill/edit/:id',
            name: 'editBill',
            component: EditBill
        },
        {
            path: '/bill/manage',
            name: 'manageBill',
            component: ManageBill
        }
    ]
})
export default router;

// router.beforeEach((to, from, next) => {
//     if (getCookie("family_token") != null) {
//         console.info("checkLogin", true);
//         next();
//     }else{
//         console.info("checkLogin", false);
//         console.info("location", location.href, location.protocol, location.hostname, location.port);
//         window.top.location.href = location.protocol + "//" + location.hostname + ":8888/toLogin";
//     }
//
// })



import Vue from 'vue'
import Router from 'vue-router'
import AddBill from '@/components/bill/AddBill'
import ManageBill from '@/components/bill/ManageBill'
import EditBill from '@/components/bill/EditBill'

Vue.use(Router)

export default new Router({
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

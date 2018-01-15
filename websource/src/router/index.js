import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import AddBill from '@/components/AddBill'
import ManageBill from '@/components/ManageBill'
import EditBill from '@/components/EditBill'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'HelloWorld',
            component: HelloWorld
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

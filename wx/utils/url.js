const HOST = 'http://localhost:8888';
const apis = {
    _bill_post_add: HOST + '/bill/post/add',
    _bill_post_update: HOST + '/bill/post/update',
    _bill_post_delete: HOST + '/bill/post/delete',
    _bill_get_page: HOST + '/bill/get/page',
    _bill_get_item: HOST + '/bill/get/item',
    _bill_get_tags: HOST + '/bill/get/tags',
    _bill_get_balanceTypes: HOST + '/bill/get/balanceTypes',
    _billtag_get_top10: HOST + '/billtag/get/top10',
    _billtype_get_all: HOST + '/billtype/get/all',
    _familymember_post_add: HOST + '/familymember/post/add',
    _familymember_post_update: HOST + '/familymember/post/update',
    _familymember_get_all: HOST + '/familymember/get/all',
    _familymember_get_first: HOST + '/familymember/get/first',
    _familymember_get_item: HOST + '/familymember/get/item',
    _familymember_post_changePassword: HOST + '/familymember/post/changePassword',
    _login: HOST + '/login',
    _login4Wx: HOST + '/login4Wx',
    _verifyToken: HOST + '/verifyToken',
    _verifyPassword: HOST + '/verifyPassword',
    _: HOST + '/'
}
export default {
    apis
}
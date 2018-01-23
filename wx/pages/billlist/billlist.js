import apis from '../../utils/url.js'
const app = getApp()
// pages/billlist/billlist.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    page:{
      list:[]
    },
    pageSize: 1000,
    pageNo: 1
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.searchPage();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  searchPage: function () {
    var vm = this;
    wx.request({
      url: apis.apis._bill_get_page + '?pageNo=' + vm.data.pageNo + '&pageSize=' + vm.data. pageSize ,
      method: 'GET',
      header: {
        'family_token': app.globalData.token
      },
      success: function (res) {
        console.info(res)
        vm.setData({ page:res.data })
      },
      fail: function (e) {
        console.info(e)
        if (e.statusCode == 401) {

        }
      }
    })
  }
})
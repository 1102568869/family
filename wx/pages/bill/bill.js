import apis from '../../utils/url.js'
const app = getApp()
// pages/bill/bill.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bill:{
      id:null
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({bill:{id:options.id}});
    if(this.data.bill.id){
      wx.request({
        url: apis.apis._bill_get_item + '?id=' + options.id ,
        method: 'GET',
        header: {
          'family_token': app.globalData.token
        },
        success: function (res) {
          console.info(res);
          //vm.setData({ page: res.data })
        },
        fail: function (e) {
          console.info(e)
          if (e.statusCode == 401) {

          }
        }
      })
    }
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
  
  }
})
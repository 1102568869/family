import apis  from '../../utils/url.js'
import { ajaxGet } from '../../utils/httpUtil.js'
const app = getApp()
// pages/billlist/billlist.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    page: {
    },
    list: [],
    pageSize: 8,
    pageNo: 1,
    isloading: false,
    moreMsg: '',
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
  toEditBill: function (event) {
    wx.navigateTo({
      url: '../bill/bill?id=' + event.currentTarget.dataset.billid
    })
  },
  loadMore: function (event) {
    console.info("拉到底了");
    if (this.data.page.lastPage === true) {
      this.setData({ moreMsg: '没有更多了%>_<%' });
      return;
    } else {
      this.setData({ moreMsg: '正在加载更多...' });
    }
    this.searchPage();
  },
  searchPage: function () {
    if (this.data.isloading === true) {
      return;
    }
    wx.showNavigationBarLoading();
    this.setData({ isloading: true });
    ajaxGet(apis._bill_get_page + '?pageNo=' + this.data.pageNo + '&pageSize=' + this.data.pageSize, (data) => {
      let more = this.data.list;
      for (var k in data.list) {
        more.push(data.list[k]);
      }
      this.setData({ page: data, list: more });
      if (this.data.page.lastPage === false) {
        this.setData({ pageNo: data.nextPageNo, moreMsg: '' });
      } else {
        this.setData({ moreMsg: '没有更多了%>_<%' });
      }
    }, null, (req) => {
      this.setData({ isloading: false });
      wx.hideNavigationBarLoading();
    })
  }
})
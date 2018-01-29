//index.js
//获取应用实例
const app = getApp()
import { login } from '../../utils/httpUtil.js'

Page({
  data: {
    motto: '记账之前好好想想钱是怎么飞的...',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    var vm = this;
    // 登录
    if (app.globalData.token){
      //啥也没发生
    }else{
      login((data) => {
        if (data) {
          app.globalData.token = data;
        } else {
          //TODO 微信账号与本系统用户打通
          wx.navigateTo({
            url: '../noauth/noauth'
          })
        }
      });
    }

    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  //事件处理函数
  billManage: function () {
    wx.navigateTo({
      url: '../billlist/billlist'
    })
  },
  mySetting: function () {
    wx.showToast({
      title: '敬请期待',
      image: '/images/failed.png'
    })
  },
  toAddBill: function (event) {
    wx.navigateTo({
      url: '../bill/bill'
    })
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})

//app.js
App({
  onLaunch: function () {
    // // 展示本地存储能力
    // var logs = wx.getStorageSync('logs') || []
    // logs.unshift(Date.now())
    // wx.setStorageSync('logs', logs)
    var app = this;

    let getUser =function(){
      wx.getUserInfo({
        success: res => {
          // 可以将 res 发送给后台解码出 unionId
          app.globalData.userInfo = res.userInfo

          // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
          // 所以此处加入 callback 以防止这种情况
          if (app.userInfoReadyCallback) {
            app.userInfoReadyCallback(res)
          }
        }
      })
    }
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
      // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          getUser();
        }else{
          wx.authorize({
            scope: 'scope.userInfo',
            success() {
              getUser();
            },
            fail() {
              wx.showLoading({
                mask: true,
                title: '不授权怎么玩!!!!!!!!!!!'
              })
            },
            complete(res){

            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,
    token: null
  }
})
import apis from './url.js'
const app = getApp()

export function ajaxGet(url, success, fail, complete) {
  wx.request({
    url: url,
    method: 'GET',
    header: {
      'family_token': app.globalData.token || ''
    },
    success: function (res) {
      console.info(res);
      success(res.data);
    },
    fail: function (e) {
      if (fail) {
        fail(e);
      }
    },
    complete: function (req) {
      console.info(req);
      if (req.statusCode == 401) {
        relogin();
      }
      if (complete) {
        complete(req);
      }
    },
  })
};
export function ajaxPost(url, data, success, fail, complete) {
  wx.request({
    url: url,
    method: 'POST',
    data: data || {},
    header: {
      'family_token': app.globalData.token || ''
    },
    success: function (res) {
      console.info(res);
      success(res.data);
    },
    fail: function (e) {
      if (fail) {
        fail(e);
      }
      console.info(e)
    },
    complete: function (req) {
      console.info(req);
      if (req.statusCode == 401) {
        relogin();
      }
      if (complete) {
        complete(req);
      }
    },
  })
};

function relogin() {
  wx.showToast({
    mask: true,
    title: '登录失效重连!',
    icon: 'loading',
    duration: 3000,
    success: function () {
      // 登录
      wx.login({
        success: res => {
          // 发送 res.code 到后台换取 openId, sessionKey, unionId
          ajaxPost(apis._login4Wx + '?code=' + res.code,{},(data)=>{
            if (data) {
              app.globalData.token = data
              wx.showToast({
                mask: true,
                title: '重新登陆成功!',
                icon: 'success',
                duration: 3000,
                success: function () {
                  wx.reLaunch({
                    url: '../index/index',
                  })
                }
              })
            } else {
              //TODO 微信账号与本系统用户打通
              wx.navigateTo({
                url: '..//noauth/noauth'
              })
            }
          })
        }
      })
    }
  })
}
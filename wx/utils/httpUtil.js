import apis from './url.js'

const app = getApp()

export function ajaxGet(url, success, fail, complete) {
  wx.request({
    url: url,
    method: 'GET',
    header: {
      'family_token': app && app.globalData.token || ''
    },
    success: function (res) {
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
      'family_token': app && app.globalData.token || ''
    },
    success: function (res) {
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

var isRelogin = false;

function relogin() {
  if (isRelogin === true) {
    return;
  }
  isRelogin = true;
  //在没有成功重登陆的情况下,保障重置isRelogin的标志
  setTimeout(() => isRelogin = false, 5000);
  wx.showToast({
    mask: true,
    title: '登录失效重连!',
    icon: 'loading',
    duration: 3000,
    success: function () {
      login();
    }
  })
}

export function login(callback) {
  // 登录
  wx.login({
    success: res => {
      // 发送 res.code 到后台换取 openId, sessionKey, unionId
      ajaxPost(apis._login4Wx + '?code=' + res.code, {}, (data) => {
        if (callback && typeof callback == 'function') {
          callback(data);
        } else {
          if (data) {
            app.globalData.token = data;
            wx.showToast({
              mask: true,
              title: '登陆成功!',
              icon: 'success',
              duration: 3000,
              success: function () {
                isRelogin = false;
                wx.reLaunch({
                  url: '../index/index',
                })
              }
            })
          } else {
            //TODO 微信账号与本系统用户打通
            wx.navigateTo({
              url: '../noauth/noauth'
            })
          }
        }
      })
    }
  })
}
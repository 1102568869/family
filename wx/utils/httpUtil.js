const app = getApp()

export function ajaxGet(url, success, fail, complete) {
  wx.request({
    url: url,
    method: 'GET',
    header: {
      'family_token': app.globalData.token||''
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
      if (e.statusCode == 401) {

      }
    },
    complete: function (req) {
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
    data:data||{},
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
      if (e.statusCode == 401) {

      }
    },
    complete: function (req) {
      if (complete) {
        complete(req);
      }
    },
  })
};

function getDate(str) {
  try {
    return new Date(Date.parse(new Date(str)));
  } catch (e) {
  }
  return null;
}

function forEachReplaceDateByKey(obj) {
  if (obj instanceof Array) {
    for (var i in obj) {
      forEachReplaceDateByKey(obj[i]);
    }
  } else if (obj instanceof Object) {
    for (var k in obj) {
      if (dateReg.test(obj[k])) {
        obj[k] = getDate(obj[k]) || obj[k];
      }
    }
  }
}


function getFinalRequestUrl(url) {
  console.info('process.env.NODE_ENV', process.env.NODE_ENV, "url", url);
  if (url == null || url.length == 0) {
    throw "url is null or empty!";
  }
  if (url.indexOf('http') == 0) {
    return url;
  }
  if (url.indexOf('/') != 0) {
    url = '/' + url;
  }
  return pre + url;
}

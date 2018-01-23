//
// export function ajaxGet(url, success, fail) {
//   wx.request({
//     url: url,
//     method: 'GET',
//     success: function (res) {
//       console.info(res)
//     },
//     fail: function (e) {
//       console.info(e)
//       if (e.statusCode == 401) {
//
//       }
//     }
//   })
// };
//
// export function ajaxPost(url, data, success, fail) {
//   axios.post(getFinalRequestUrl(url), data || {}, { withCredentials: true })
//     .then(function (response) {
//       success(response.data);
//     })
//     .catch(function (error) {
//       if (fail) {
//         fail(error);
//       }
//     });
// };
//
//
// function getDate(str) {
//   try {
//     return new Date(Date.parse(new Date(str)));
//   } catch (e) {
//   }
//   return null;
// }
//
// function forEachReplaceDateByKey(obj) {
//   if (obj instanceof Array) {
//     for (var i in obj) {
//       forEachReplaceDateByKey(obj[i]);
//     }
//   } else if (obj instanceof Object) {
//     for (var k in obj) {
//       if (dateReg.test(obj[k])) {
//         obj[k] = getDate(obj[k]) || obj[k];
//       }
//     }
//   }
// }
//
//
// function getFinalRequestUrl(url) {
//   console.info('process.env.NODE_ENV', process.env.NODE_ENV, "url", url);
//   if (url == null || url.length == 0) {
//     throw "url is null or empty!";
//   }
//   if (url.indexOf('http') == 0) {
//     return url;
//   }
//   if (url.indexOf('/') != 0) {
//     url = '/' + url;
//   }
//   return pre + url;
// }

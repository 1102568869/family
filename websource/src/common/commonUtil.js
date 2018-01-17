export function formatDate(date, fmt) {
    if (date == null) {
        return null;
    }
    fmt = fmt || 'yyyy/MM/dd hh:mm:ss';
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    let o = {
        'M+': date.getMonth() + 1,
        'd+': date.getDate(),
        'h+': date.getHours(),
        'm+': date.getMinutes(),
        's+': date.getSeconds()
    };
    for (let k in o) {
        if (new RegExp(`(${k})`).test(fmt)) {
            let str = o[k] + '';
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : padLeftZero(str));
        }
    }
    return fmt;
};

function padLeftZero(str) {
    return ('00' + str).substr(str.length);
}

export function setCookie(name, value, expires, domain, path, secure) {
    var cookieText = "";
    cookieText += encodeURIComponent(name) + "=" + encodeURIComponent(value);
    if (expires instanceof Date) {
        cookieText += "; expires=" + expires.toGMTString();
    }
    if (path) {
        cookieText += "; path=" + path;
    }
    if (domain) {
        cookieText += "; domain=" + domain;
    }
    if (secure) {
        cookieText += "; secure";
    }
    document.cookie = cookieText;
};

// name=value; expires=expiration_time; path=domain_path; domain=domain_name; secure
// 获取cookie
export function getCookie(name) {
    var cookieName = encodeURIComponent(name) + "=",
        cookieStart = document.cookie.indexOf(cookieName),
        cookieValue = "";
    if (cookieStart > -1) {
        var cookieEnd = document.cookie.indexOf(";", cookieStart);
        if (cookieEnd == -1) {
            cookieEnd = document.cookie.length;
        }
        cookieValue = decodeURIComponent(document.cookie.substring(cookieStart + cookieName.length, cookieEnd));
    }
    return cookieValue;
};

// 删除cookie
export function removeCookie(name, domain, path, secure) {
    this.set(name, "", Date(0), domain, path, secure);
};

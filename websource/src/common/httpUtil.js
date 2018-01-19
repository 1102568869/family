import axios from "axios";


const dateReg = /\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}/;

const protocol = window.top.location.protocol;
const hostname = window.top.location.hostname;
const port = window.top.location.port;

const prodPrefix = protocol + "//" + hostname + ":" + port;
const devPrefix = protocol + "//" + hostname + ":8888";
const pre = process.env.NODE_ENV === 'production' ? prodPrefix : devPrefix;


const msgRex = /"message":"([\S|\s]*)",/;

export function ajaxGet(url, success, fail) {
    axios.get(getFinalRequestUrl(url), {withCredentials: true})
        .then(function (response) {
            let result = response.data;
            forEachReplaceDateByKey(result);
            success(result);
        })
        .catch(function (error) {
            console.log(error);
            console.log(error.config);
            let msg = '';
            if (error.response) {
                console.log(error.response.data);
                msg = error.response.data.message || error.response.data.match(msgRex)[1];
                if (error.response.status == 401 || error.response.status == 403) {
                    window.top.location.href = prodPrefix + '/#/login';
                    return;
                }
            } else if (error.request) {
                msg = JSON.stringify(error.request);
            } else {
                msg = error.message;
            }
            if (fail) {
                fail(msg);
            }
        });
    // .catch(function (error) {
    //     // if (error.response) {
    //     //     console.log(error.response.data);
    //     //     console.log(error.response.status);
    //     //     console.log(error.response.headers);
    //     //     // The request was made and the server responded with a status code
    //     //     // that falls out of the range of 2xx
    //     //     if (error.response.status == 401 || error.response.status == 403) {
    //     //         window.top.location.href = prodPrefix+"/#/login" ;
    //     //         return;
    //     //     }
    //     //
    //     // } else if (error.request) {
    //     //     // The request was made but no response was received
    //     //     // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
    //     //     // http.ClientRequest in node.js
    //     //     console.log(error.request);
    //     // } else {
    //     //     // Something happened in setting up the request that triggered an Error
    //     //     console.log('Error', error.message);
    //     // }
    //     // console.log(error.config);
    //     if (fail) {
    //         fail(error);
    //     }
    // });
};

export function ajaxPost(url, data, success, fail) {
    axios.post(getFinalRequestUrl(url), data || {}, {withCredentials: true})
        .then(function (response) {
            success(response.data);
        })
        .catch(function (error) {
            console.log(error);
            console.log(error.config);
            let msg = '';
            if (error.response) {
                console.log(error.response.data);
                msg = error.response.data.message || error.response.data.match(msgRex)[1];
                if (error.response.status == 401 || error.response.status == 403) {
                    window.top.location.href = prodPrefix + '/#/login';
                    return;
                }
            } else if (error.request) {
                msg = JSON.stringify(error.request);
            } else {
                msg = error.message;
            }
            if (fail) {
                fail(msg);
            }
        });
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
            if (dateReg.test(obj [k])) {
                obj [k] = getDate(obj [k]) || obj[k];
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

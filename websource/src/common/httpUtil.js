import axios from "axios";

export function ajaxGet(url, success, fail) {
    axios.get(url)
        .then(function (response) {
            let result = response.data;
            forEachReplaceDateByKey(result);
            success(result);
        })
        .catch(function (error) {
            console.error("GET请求API失败!", url, error);
            if (fail) {
                fail(error);
            }
        })
};

export function ajaxPost(url, data, success, fail) {
    axios.post(url, data || {})
        .then(function (response) {
            success(response.data);
        })
        .catch(function (error) {
            console.error("POST请求API失败!", url, error);
            if (fail) {
                fail(error);
            }
        })
};

const dateReg = /\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}/;

function getDate(str) {
    try {
        return new Date(Date.parse(new Date(str)));
    } catch (e) {
    }
    console.info("lalala");
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
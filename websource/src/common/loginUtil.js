import {ajaxGet, ajaxPost} from "@/common/httpUtil";
import {getCookie} from "@/common/commonUtil";


export function checkLogin() {
    let token = getCookie('family_token');
    if (!token) {
        return false;
    }
    return false;
};

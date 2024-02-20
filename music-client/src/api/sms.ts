import {post} from "./request";

const SmsManager = {
    //获取验证码
    getCode: (phone, smsType) => post(`/sms/generateCode?phone=${phone}&type=${smsType}`),
    //验证码登录
    codeLogin: (phone, code, smsType) => post(`/sms/login?phone=${phone}&code=${code}&type=${smsType}`),
};

export {SmsManager};

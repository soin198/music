// 登录规则
const validateName = (rule, value, callback) => {
    if (!value) {
        return callback(new Error("用户名不能为空"));
    } else {
        callback();
    }
};

export const validatePassword = (rule, value, callback) => {
    if (value === "") {
        callback(new Error("密码不能为空"));
    } else {
        callback();
    }
};

export const SignInRules = {
    username: [{validator: validateName, trigger: "blur", min: 3}],
    password: [{validator: validatePassword, trigger: "blur", min: 3}],
};

// 注册规则
export const SignUpRules = {
    username: [{required: true, trigger: "blur", min: 3}],
    password: [{required: true, trigger: "blur", min: 3}],
    sex: [{required: true, message: "请选择性别", trigger: "change"}],
    phone: [{message: "请选择日期", trigger: "blur"}],
    email: [
        {message: "请输入邮箱地址", trigger: "blur"},
        {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
        },
    ],
    birth: [{required: true, type: "date", message: "请选择日期", trigger: "change"}],
    resume: [{message: "请输入介绍", trigger: "blur"}],
    location: [{message: "请输入地区", trigger: "change"}],
};

// 手机号登录验证码
export const CodeLoginRules = {
    phone: [{required: true, message: "请输入手机号", trigger: "blur", max: 11}],
    validCode: [{required: true, message: "请输入验证码", trigger: "blur", max: 6}]
};

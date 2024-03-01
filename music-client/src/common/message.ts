import Swal from 'sweetalert2'

/**
 * 成功
 * @param message 展示信息
 */
async function success(message: string) {
    message = (null != message && message != "") ? message : "操作成功 🎉";
    await Swal.fire({
        // 出现位置
        position: "top",
        //展示内容
        title: message,
        // 不显示确认按钮
        showConfirmButton: false,
        //弹出颜色
        background: "#FFFFFF",
        //toast为false的话是弹窗形式弹出，有遮罩层。默认为false
        toast: true,
        //宽度
        width: "300px",
        //自适应高度
        heightAuto: true,
        //外部边距
        padding: "15px",
        //成功
        icon: "success",
        //图标颜色
        iconColor: "#22AA59",
        //展示时间
        timer: 1800,

    });
}

/**
 * 失败
 * @param message 展示信息
 */
async function error(message: string) {
    message = (null != message && message != "") ? message : "操作失败 😢";
    await Swal.fire({
        // 出现位置
        position: "top",
        //展示内容
        title: message,
        // 不显示确认按钮
        showConfirmButton: false,
        //弹出颜色
        background: "#FFFFFF",
        //toast为false的话是弹窗形式弹出，有遮罩层。默认为false
        toast: true,
        //宽度
        width: "300px",
        //自适应高度
        heightAuto: true,
        //外部边距
        padding: "15px",
        //成功
        icon: "error",
        //图标颜色
        iconColor: "#CC0000",
        //展示时间
        timer: 1800,

    });
}


/**
 * 警告
 * @param message 展示信息
 */
async function warning(message: string) {
    if (null == message || message == ""){
        return;
    }
    await Swal.fire({
        // 出现位置
        position: "top",
        //展示内容
        title: message,
        // 不显示确认按钮
        showConfirmButton: false,
        //弹出颜色
        background: "#FFFFFF",
        //toast为false的话是弹窗形式弹出，有遮罩层。默认为false
        toast: true,
        //宽度
        width: "300px",
        //自适应高度
        heightAuto: true,
        //外部边距
        padding: "15px",
        //成功
        icon: "warning",
        //图标颜色
        iconColor: "#CC0000",
        //展示时间
        timer: 1800,
    });
}

export {success, error}

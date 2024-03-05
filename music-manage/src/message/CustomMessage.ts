import Swal from 'sweetalert2'
import {SuccessEmoji} from "@/enums/emoji";

class CustomMessage {

    /**
     * 成功
     * @param message 展示信息
     */
    static success(message: string) {
        message = (null != message && message != "") ? message : "操作成功";
        const index = Math.floor(Math.random() * SuccessEmoji.length);
        void Swal.fire({
            // show address
            position: "top",
            //show content
            title: (message + " " + SuccessEmoji[index]),
            //not show button & show button
            showConfirmButton: false,
            //content color
            background: "#FFFFFF",
            //toast type
            toast: true,
            //width
            width: "300px",
            //auto height
            heightAuto: true,
            //outer margin
            padding: "15px",
            //icon
            icon: "success",
            //icon color
            iconColor: "#22AA59",
            //show time
            timer: 1800
        });
    }


    /**
     * 失败0
     * @param message 展示信息
     */
    static error(message: string) {
        message = (null != message && message != "") ? message : "操作失败";
        void Swal.fire({
            // 出现位置
            position: "top",
            //展示内容
            title: (message),
            // 不显示确认按钮
            showConfirmButton: false,
            //content color
            background: "#FFFFFF",
            //toast为false的话是弹窗形式弹出，有遮罩层。默认为false
            toast: true,
            //width
            width: "300px",
            //自适应高度
            heightAuto: true,
            //外部边距
            padding: "15px",
            //icon
            icon: "error",
            //icon color
            iconColor: "#CC0000",
            //show time
            timer: 1800
        });
    }

    /**
     * 警告
     * @param message 展示信息
     */
    static warning(message: string) {
        if (null == message || message == "") {
            return;
        }
        void Swal.fire({
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
}

const Message = CustomMessage;

export {Message}

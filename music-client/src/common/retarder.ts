/**
 * 阻塞器
 * @param millisecond 阻塞毫秒
 */
export function sleep(millisecond: number) {
    return new Promise(resolve =>
        setTimeout(resolve, millisecond));
}


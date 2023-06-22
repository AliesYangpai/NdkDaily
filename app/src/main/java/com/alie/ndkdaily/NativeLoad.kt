package com.alie.ndkdaily


object NativeLoad {
    init {
        System.loadLibrary("ndkdaily")
    }

    external fun dailyWork01(dst: ByteArray, src: ByteArray) // 加载一张图片
    external fun dailyWork02(dst: ByteArray, src: ByteArray) // 加载一张图片，并读取像素信息
    external fun dailyWork03(dst: ByteArray, rows: Int, cols: Int) // 创建一个8位单通道的图片
    external fun dailyWork04(dst: ByteArray, dstRow: Int, dstCol: Int, src: ByteArray) //加载一张图片
    external fun dailyWork05(dst: ByteArray,src: ByteArray) // 按照原图加载一张图片
    external fun dailyWork06(dst: ByteArray,src: ByteArray) // 按照原图加载一张图片
    external fun dailyWork07(dst: ByteArray,src: ByteArray) // 1/25 平均卷积
    external fun dailyWork08(dst: ByteArray,src: ByteArray) // blur 平均滤波 平均卷积
    external fun dailyWork09(dst: ByteArray,src: ByteArray) // blur 平均滤波 平均卷积
    external fun dailyWork10(dst: ByteArray,src: ByteArray) // blur 平均滤波 平均卷积
    external fun dailyWork11(dst: ByteArray,src: ByteArray) // blur 平均滤波 平均卷积
}
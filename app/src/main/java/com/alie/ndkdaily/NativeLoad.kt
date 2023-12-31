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
    external fun dailyWork12(dst: ByteArray,src: ByteArray) // blur 平均滤波 平均卷积
    external fun dailyWork13(dst: ByteArray,src: ByteArray) // 1/25 filter2D 平均卷积
    external fun dailyWork14(dst: ByteArray,src: ByteArray) // 1/25 filter2D 平均卷积
    external fun dailyWork15(dst: ByteArray,src: ByteArray) // 高斯滤波
    external fun dailyWork16(dst: ByteArray,src: ByteArray) // 高斯滤波
    external fun dailyWork17(dst: ByteArray,src: ByteArray) // 高斯滤波
    external fun dailyWork18(dst: ByteArray,src: ByteArray) // 高斯滤波
    external fun dailyWork19(dst: ByteArray,src: ByteArray) // 高斯滤波
    external fun dailyWork20(dst: ByteArray,d:Int,sigmaColor:Double,sigmaSpace: Double,src: ByteArray) // 双边滤波
    external fun dailyWork21(dst: ByteArray,d:Int,sigmaColor: Double,sigmaSpace: Double,src: ByteArray) // 双边滤波
    external fun dailyWork22(dst: ByteArray,d:Int,sigmaColor: Double,sigmaSpace: Double,src: ByteArray) // 双边滤波
    external fun dailyWork23(dst: ByteArray,d:Int,sigmaColor: Double,sigmaSpace: Double,src: ByteArray) // 双边滤波
    external fun dailyWork24(dst: ByteArray,d:Int,sigmaColor: Double,sigmaSpace: Double,src: ByteArray) // 双边滤波
    external fun dailyWork25(dst: ByteArray,d:Int,sigmaColor: Double,sigmaSpace: Double,src: ByteArray) // 双边滤波
    external fun dailyWork26(dst: ByteArray,d: Int,sigmaColor: Double,sigmaSpace: Double,src: ByteArray) // 双边滤波
    external fun dailyWork27(dst: ByteArray,d:Int,sigmaColor: Double,sigmaSpace: Double,src: ByteArray) // 双边滤波
    external fun dailyWork28(dst: ByteArray,src: ByteArray,threshold1:Double,threshold2:Double) // Canny算子 边缘检测 噪西抑阈
    external fun dailyWork29(dst: ByteArray,src: ByteArray,threshold1: Double,threshold2: Double) // Canny算子 边缘检测 噪西抑阈
    external fun dailyWork30(dst: ByteArray,src: ByteArray,d:Int,sigmaColor: Double,sigmaSpace: Double) // 双边滤波
    external fun dailyWork31(dst: ByteArray,src: ByteArray,threshold1: Double,threshold2: Double) // Canny算子 边缘检测 噪西抑阈
    external fun dailyWork32(dst: ByteArray,src: ByteArray,d:Int,sigmaColor: Double,sigmaSpace: Double) // 双边滤波
}
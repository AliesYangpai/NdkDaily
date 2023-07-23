package com.alie.ndkdaily

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alie.ndkdaily.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.nio.Buffer
import java.nio.ByteBuffer

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val girl1 = "girl1.jpeg"
    private val girl2 = "girl2.jpeg"
    private val libingbing = "libingbing.jpg"
    private val britney01 = "britney01.jpg"
    private val britney02 = "britney02.jpeg"
    private val qiushuzhen01 = "qiushuzhen.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        dailyWork01() // 字节数组加载一张图片
//        dailyWork02() // 字节数组加载一张图片并读取
//        dailyWork03()  // 自行构造一个8位单通道图片
//        dailyWork04() // 字节数组加载一张图片
//        dailyWork05() // 字节数组加载一张图片
//        dailyWork06() // 字节数组加载一张图片
//        dailyWork07() // 1/25 平均卷积
//        dailyWork08() // blur平均卷积 （也可使用boxFilter）
//        dailyWork09() // blur平均卷积（也可使用boxFilter）
//        dailyWork10() // blur平均卷积
//        dailyWork11() // blur平均卷积
//        dailyWork12() // blur平均卷积
//        dailyWork13() // 1/25 平均卷积
//        dailyWork14() // 1/25 平均卷积
//        dailyWork15() // 高斯滤波 与卷积核size & sigmaX标准差有关
//        dailyWork16() // 高斯滤波 与卷积核size & sigmaX标准差有关
//        dailyWork17() // 高斯滤波 与卷积核size & sigmaX标准差有关
//        dailyWork18() // 高斯滤波 与卷积核size & sigmaX标准差有关
//        dailyWork19() // 高斯滤波 与卷积核size & sigmaX标准差有关
//        dailyWork20() // 双边滤波 美颜 半径-d sigmaColor sigmaSpace
//        dailyWork21() // 双边滤波 美颜 半径-d sigmaColor sigmaSpace
//        dailyWork22() // 双边滤波 美颜 半径-d sigmaColor sigmaSpace
//        dailyWork23() // 双边滤波 美颜 半径-d sigmaColor sigmaSpace
//        dailyWork24() // 双边滤波 美颜 半径-d sigmaColor sigmaSpace
//        dailyWork25() // 双边滤波 美颜 半径-d sigmaColor sigmaSpace
        dailyWork26() // 双边滤波 美颜 半径-d sigmaColor sigmaSpace
    }

    private fun dailyWork26() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when(it) {
                        true-> {
                            println("work dailyWork26 surfaceViewEnable")
                            val srcByteArray  = assets.open(girl2).readBytes()
                            val dstWidth = 1200
                            val dstHeight = 800
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork26(dstByteArray,11,20.00,20.00,srcByteArray)
                            val bitmap = Bitmap.createBitmap(dstWidth,dstHeight,Bitmap.Config.ARGB_8888).also {
                                bitmap ->
                                val buffer = ByteBuffer.wrap(dstByteArray).also { byteBuffer -> byteBuffer.rewind() }
                                bitmap.copyPixelsFromBuffer(buffer)
                            }
                            if (bitmap == null) {
                                println("work dailyWork26 bitmap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap,0F,0F,null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }
                        else-> println("work dailyWork26 surfaceViewUnEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork25() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work dailyWork25 surfaceViewUnEnable")
                            val srcByteArray = assets.open(girl2).readBytes()
                            val dstWidth = 1200
                            val dstHeight = 800
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork25(dstByteArray, 15, 20.00, 20.00, srcByteArray)
                            val bitmap = Bitmap.createBitmap(dstWidth,dstHeight,Bitmap.Config.ARGB_8888).also {
                                bitmap ->
                                val buffer = ByteBuffer.wrap(dstByteArray)
                                bitmap.copyPixelsFromBuffer(buffer)
                            }
                            if (bitmap == null) {
                                println("work dailyWork25 bitmap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap,0F,0F,null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> println("work dailyWork25 surfaceViewUnEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork24() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work dailyWork24 surfaceViewEnable")
                            val srcByteArray = assets.open(girl2).readBytes()
                            val dstWidth = 1200
                            val dstHeight = 800
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork24(dstByteArray, 7, 10.00, 10.00, srcByteArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork24")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> println("work dailyWork24 surfaceViewEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork23() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work dailyWork23 surfaceViewEnable")
                            val srcByteArray = assets.open(girl2).readBytes()
                            val dstWidth = 1200
                            val dstHeight = 800
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork23(dstByteArray, 25, 20.00, 20.00, srcByteArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork23 bitmap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else ->
                            println("work dailyWork23 surfaceViewUnEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork22() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work dailyWork22 surfaceViewEnable")
                            val srcByteArray = assets.open(girl2).readBytes()
                            val dstWidth = 1200
                            val dstHeight = 800
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork22(dstByteArray, 25, 10.00, 10.00, srcByteArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork22 bitmap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> println("work dailyWork22 surfaceViewUnEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork21() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work dailyWork21 surfaceViewEnable")
                            val srcByteArray = assets.open(girl2).readBytes()
                            val dstWidth = 1200
                            val dstHeight = 800
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork21(dstByteArray, 7, 20.0, 20.0, srcByteArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork21 bitmap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)

                        }

                        else -> println("work dailyWork21 surfaceViewUnEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork20() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work dailyWork20 surfaceViewEnable")
                            val srcByteArray = assets.open(girl2).readBytes()
                            val dstWidth = 1200
                            val dstHeight = 800
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork20(dstByteArray, 7, 20.0, 20.0, srcByteArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork03 bitmap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> println("work dailyWork20 surfaceViewUnEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork19() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work dailyWork19 surfaceViewEnable")
                            val srcByteArray = assets.open(qiushuzhen01).readBytes()
                            val dstWidth = 540
                            val dstHeight = 540
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork19(dstByteArray, srcByteArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork19 bitmap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> println("work dailyWork19 surfaceViewUnEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork18() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work dailyWork18 surfaceViewUnEnable")
                            val srcByteArray = assets.open(qiushuzhen01).readBytes()
                            val dstWidth = 540
                            val dstHeight = 540
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork18(dstByteArray, srcByteArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork18 bitmap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> println("work dailyWork18 surfaceViewUnEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork17() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work dailyWork17 surfaceViewEnable")
                            val srcByteArray = assets.open(qiushuzhen01).readBytes()
                            val dstWidth = 540
                            val dstHeight = 540
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork17(dstByteArray, srcByteArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork17 bitmap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> println("work dailyWork17 surfaceViewUnEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork16() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work dailyWork16 surfaceViewEnable")
                            val srcByteArray = assets.open(qiushuzhen01).readBytes()
                            val dstWidth = 540
                            val dstHeight = 540
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork16(dstByteArray, srcByteArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork16 bitmap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> println("work dailyWork16 surfaceViewUnEnable")
                    }
                }
            }
        }
    }


    private fun dailyWork15() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work dailyWork15 surfaceViewEnable")
                            val srcByteArray = assets.open(britney02).readBytes()
                            val dstWidth = 720
                            val dstHeight = 720
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork15(dstByteArray, srcByteArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork15 bitmap is null")
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> println("work dailyWork15 surfaceViewEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork14() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collect {
                    when (it) {
                        true -> {
                            println("work dailyWork14 surfaceViewEnable")
                            val srcByteArray = assets.open(britney02).readBytes()
                            val dstWidth = 720
                            val dstHeight = 720
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork14(dstByteArray, srcByteArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork14 bitmap is null")
                                return@collect
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> println("work dailyWork14 surfaceViewUnEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork13() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collect {
                    when (it) {
                        true -> {
                            println("work dailyWork13 surfaceViewEnable")
                            val srcByeArray = assets.open(britney02).readBytes()
                            val dstWidth = 720
                            val dstHeight = 720
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork13(dstByteArray, srcByeArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork13 bitmap is null")
                                return@collect
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)

                        }

                        else -> {
                            println("work dailyWork13 surfaceViewUnEnable")
                        }
                    }
                }
            }
        }
    }

    private fun dailyWork12() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work dailyWork12 surfaceViewEnable")
                            val srcByteArray = assets.open(britney02).readBytes()
                            val dstWidth = 720
                            val dstHeight = 720
                            val dstChannel = 4
                            val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                            NativeLoad.dailyWork12(dstByteArray, srcByteArray)
                            val bitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { byteBuffer -> byteBuffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (bitmap == null) {
                                println("work dailyWork12 bitmap is null")
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(bitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> {
                            println("work dailyWork12 surfaceViewUnEnable")
                        }
                    }
                }
            }
        }
    }

    private fun dailyWork11() {
        lifecycleScope.launch {
            binding.mySurfaceView.surfaceViewStateFlow.collect {
                when (it) {
                    true -> {
                        println("work dailyWork11 surfaceViewEnable")
                        val srcByteArray = assets.open(britney02).readBytes()
                        val dstWidth = 720
                        val dstHeight = 720
                        val dstChannel = 4
                        val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                        NativeLoad.dailyWork11(dstByteArray, srcByteArray)
                        val bitmap =
                            Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                .also { bitmap ->
                                    val buffer = ByteBuffer.wrap(dstByteArray)
                                        .also { byteBuffer -> byteBuffer.rewind() }
                                    bitmap.copyPixelsFromBuffer(buffer)
                                }
                        if (bitmap == null) {
                            println("work dailyWork11 bitmap is null")
                            return@collect
                        }
                        val canvas = binding.mySurfaceView.holder.lockCanvas()
                        canvas.drawBitmap(bitmap, 0F, 0F, null)
                        binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                    }

                    else ->
                        println("work dailyWork11 surfaceViewUnEnable")
                }
            }
        }
    }


    private fun dailyWork10() {
        lifecycleScope.launch {
            binding.mySurfaceView.surfaceViewStateFlow.collect {
                when (it) {
                    true -> {
                        println("work dailyWork10 surfaceViewEnable")
                        val srcByteArray = assets.open(britney02).readBytes()
                        val dstWidth = 720
                        val dstHeight = 720
                        val dstChannel = 4
                        val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                        NativeLoad.dailyWork10(dstByteArray, srcByteArray)
                        val bitmap =
                            Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                .also { bitmap ->
                                    val buffer = ByteBuffer.wrap(dstByteArray)
                                        .also { byteBuffer -> byteBuffer.rewind() }
                                    bitmap.copyPixelsFromBuffer(buffer)
                                }
                        if (bitmap == null) {
                            println("work dailyWork10 bitmap is null")
                            return@collect
                        }

                        val canvas = binding.mySurfaceView.holder.lockCanvas()
                        canvas.drawBitmap(bitmap, 0F, 0F, null)
                        binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                    }

                    else ->
                        println("work dailyWork10 surfaceViewUnEnable")
                }
            }
        }
    }

    private fun dailyWork09() {
        lifecycleScope.launch {
            binding.mySurfaceView.surfaceViewStateFlow.collect {
                when (it) {
                    true -> {
                        println("work dailyWork09 surfaceViewEnable")
                        val srcByteArray = assets.open(britney02).readBytes()
                        val dstWidth = 720
                        val dstHeight = 720
                        val dstChannel = 4
                        val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                        NativeLoad.dailyWork09(dstByteArray, srcByteArray)
                        val bitmap =
                            Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                .also { bitmap ->
                                    val buffer = ByteBuffer.wrap(dstByteArray)
                                        .also { buffer -> buffer.rewind() }
                                    bitmap.copyPixelsFromBuffer(buffer)
                                }
                        if (bitmap == null) {
                            println("work dailyWork09 bitmap is null")
                            return@collect
                        }
                        val canvas = binding.mySurfaceView.holder.lockCanvas()
                        canvas.drawBitmap(bitmap, 0F, 0F, null)
                        binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)

                    }

                    else -> println("work dailyWork09 surfaceViewUnEnable")
                }
            }
        }
    }

    private fun dailyWork08() {
        lifecycleScope.launch {
            binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                when (it) {
                    true -> {
                        println("work dailyWork08 surfaceViewEnable")
                        val srcByteArray = assets.open(britney02).readBytes()
                        val dstWidth = 720
                        val dstHeight = 720
                        val dstChannel = 4
                        val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                        NativeLoad.dailyWork08(dstByteArray, srcByteArray)
                        val bitmap =
                            Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                .also { bitmap ->
                                    val buffer = ByteBuffer.wrap(dstByteArray)
                                        .also { byteBuffer -> byteBuffer.rewind() }
                                    bitmap.copyPixelsFromBuffer(buffer)
                                }
                        val canvas = binding.mySurfaceView.holder.lockCanvas()
                        canvas.drawBitmap(bitmap, 0F, 0F, null)
                        binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                    }

                    else -> {
                        println("work dailyWork08 surfaceViewUnEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork07() {
        lifecycleScope.launch {
            binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                when (it) {
                    true -> {
                        println("work dailyWork07 surfaceViewEnable")
                        val srcByteArray = assets.open(britney02).readBytes()
                        val dstWidth = 720
                        val dstHeight = 720
                        val dstChannel = 4
                        val dstByteArray = ByteArray(dstWidth * dstHeight * dstChannel)
                        NativeLoad.dailyWork07(dstByteArray, srcByteArray)
                        val bitmap =
                            Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                .also { bitmap ->
                                    val buffer = ByteBuffer.wrap(dstByteArray)
                                        .also { byteBuffer -> byteBuffer.rewind() }
                                    bitmap.copyPixelsFromBuffer(buffer)
                                }
                        if (bitmap == null) {
                            println("work dailyWork07 bitmap is null")
                            return@collectLatest
                        }
                        val canvas = binding.mySurfaceView.holder.lockCanvas()
                        canvas.drawBitmap(bitmap, 0F, 0F, null)
                        binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)

                    }

                    else -> {
                        println("work dailyWork07 surfaceViewUnEnable")
                    }
                }
            }
        }
    }

    private fun dailyWork06() {
        lifecycleScope.launch {
            binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                when (it) {
                    true -> {
                        println("work dailyWork06 surfaceViewEnable")
                        val srcByteArray = assets.open(britney01).readBytes()
                        val dstWidth = 480
                        val dstHeight = 480
                        val dstChannel = 4
                        val dstByteArray = ByteArray(dstHeight * dstWidth * dstChannel)
                        NativeLoad.dailyWork06(dstByteArray, srcByteArray)
                        val bitmap =
                            Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                .also { bitmap ->
                                    val buffer = ByteBuffer.wrap(dstByteArray)
                                        .also { byteBuffer -> byteBuffer.rewind() }
                                    bitmap.copyPixelsFromBuffer(buffer)
                                }
                        if (bitmap == null) {
                            println("work dailyWork06 bitmap is null")
                            return@collectLatest
                        }
                        val canvas = binding.mySurfaceView.holder.lockCanvas()
                        canvas.drawBitmap(bitmap, 0F, 0F, null)
                        binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                    }

                    else -> {
                        println("work dailyWork06 surfaceViewUnEnable")
                    }
                }
            }
        }
    }


    private fun dailyWork05() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work surfaceViewEnable")
                            val srcByteArray = assets.open(britney01).readBytes()
                            val dstWidth = 480
                            val dstHeight = 480
                            val dstChannel = 4
                            val dstByteArraySize = dstWidth * dstHeight * dstChannel
                            val dstByteArray = ByteArray(dstByteArraySize)
                            NativeLoad.dailyWork05(dstByteArray, srcByteArray)
                            val dstBitmap =
                                Bitmap.createBitmap(dstWidth, dstHeight, Bitmap.Config.ARGB_8888)
                                    .also { bitmap ->
                                        val buffer = ByteBuffer.wrap(dstByteArray)
                                            .also { buffer -> buffer.rewind() }
                                        bitmap.copyPixelsFromBuffer(buffer)
                                    }
                            if (dstBitmap == null) {
                                println("work dailyWork05 dstBimap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(dstBitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> {
                            println("work surfaceViewUnEnable")
                        }
                    }
                }
            }
        }
    }

    private fun dailyWork04() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work surfaceViewEnable")
                            val srcByteArray = assets.open(girl1).readBytes()
                            val dstWidth = 1080 / 2
                            val dstHeight = 1080 / 2
                            val dstChannel = 4 // 这里我使用rgba-8888的4通道来定义
                            val dstSize = dstWidth * dstHeight * dstChannel
                            val dstByteArray = ByteArray(dstSize)
                            NativeLoad.dailyWork04(dstByteArray, dstHeight, dstWidth, srcByteArray)
                            val dstBitmap = Bitmap.createBitmap(
                                dstWidth, dstHeight, Bitmap.Config.ARGB_8888
                            ).also { bitmap ->
                                val buffer = ByteBuffer.wrap(dstByteArray)
                                buffer.rewind()
                                bitmap.copyPixelsFromBuffer(buffer)
                            }
                            if (dstBitmap == null) {
                                println("work dstBimap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(dstBitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> {
                            println("work surfaceViewUnEnable")
                        }
                    }
                }
            }
        }
    }

    private fun dailyWork03() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work surfaceViewEnable")

                            val dstWidth = 540
                            val dstHeight = 540
                            val dstChannel = 4
                            val dstSize = dstWidth * dstHeight * dstChannel
                            val dstByteArray = ByteArray(dstSize)
                            NativeLoad.dailyWork03(dstByteArray, dstHeight, dstWidth)
                            val dstBitmap = Bitmap.createBitmap(
                                dstWidth,
                                dstHeight,
                                Bitmap.Config.ARGB_8888
                            ).also { bitmap ->
                                val buffer = ByteBuffer.wrap(dstByteArray)
                                buffer.rewind()
                                bitmap.copyPixelsFromBuffer(buffer)
                            }
                            if (dstBitmap == null) {
                                println("work dstBimap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(dstBitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> {
                            println("work surfaceViewUnEnable")
                        }
                    }
                }
            }
        }
    }

    private fun dailyWork02() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work surfaceViewEnable")
                            val srcByteArray = assets.open(libingbing).readBytes()
                            val dstWidth = 540
                            val dstHeight = 540
                            val dstChannel = 4
                            val dstSize = dstWidth * dstHeight * dstChannel
                            val dstByteArray = ByteArray(dstSize)
                            NativeLoad.dailyWork02(dstByteArray, srcByteArray)
                            val dstBitmap = Bitmap.createBitmap(
                                dstWidth,
                                dstHeight,
                                Bitmap.Config.ARGB_8888
                            ).also { bitmap ->
                                val buffer = ByteBuffer.wrap(dstByteArray)
                                buffer.rewind()
                                bitmap.copyPixelsFromBuffer(buffer)
                            }
                            if (dstBitmap == null) {
                                println("work dstBimap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(dstBitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)
                        }

                        else -> {
                            println("work surfaceViewUnEnable")
                        }
                    }
                }
            }
        }
    }

    private fun dailyWork01() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                binding.mySurfaceView.surfaceViewStateFlow.collectLatest {
                    when (it) {
                        true -> {
                            println("work surfaceViewEnable")
                            val srcByteArray = assets.open(girl1).readBytes()
                            val dstWidth = 1080
                            val dstHeight = 1080
                            val dstChannel = 4
                            val dstSize = dstWidth * dstHeight * dstChannel
                            val dstByteArray = ByteArray(dstSize)
                            NativeLoad.dailyWork01(dstByteArray, srcByteArray)
                            val dstBitmap = Bitmap.createBitmap(
                                dstWidth, dstHeight, Bitmap.Config.ARGB_8888
                            ).also { bitmap ->
                                val buffer = ByteBuffer.wrap(dstByteArray)
                                buffer.rewind()
                                bitmap.copyPixelsFromBuffer(buffer)
                            }
                            if (dstBitmap == null) {
                                println("work dstBimap is null")
                                return@collectLatest
                            }
                            val canvas = binding.mySurfaceView.holder.lockCanvas()
                            canvas.drawBitmap(dstBitmap, 0F, 0F, null)
                            binding.mySurfaceView.holder.unlockCanvasAndPost(canvas)

                        }

                        else -> {
                            println("work surfaceViewUnEnable")
                        }
                    }
                }
            }
        }
    }
}
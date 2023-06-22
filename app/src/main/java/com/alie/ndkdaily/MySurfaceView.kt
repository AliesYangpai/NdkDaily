package com.alie.ndkdaily

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceHolder.Callback
import android.view.SurfaceView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MySurfaceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SurfaceView(context, attrs), Callback {

    private val _surfaceViewStateFlow = MutableStateFlow(false)
    val surfaceViewStateFlow:StateFlow<Boolean> = _surfaceViewStateFlow
    init {
        this.holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        _surfaceViewStateFlow.value = true
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        _surfaceViewStateFlow.value = false
    }
}
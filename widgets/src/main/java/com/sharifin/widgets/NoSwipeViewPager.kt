package com.sharifin.widgets

import android.annotation.SuppressLint
import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class NoSwipeViewPager @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null
) : ViewPager(context, attrs) {

    private var isTouchEnabled: Boolean = false

    init {
        this.isTouchEnabled = true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return this.isTouchEnabled && super.onTouchEvent(event)

    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return this.isTouchEnabled && super.onInterceptTouchEvent(event)

    }

    fun setPagingEnabled(enabled: Boolean) {
        this.isTouchEnabled = enabled
    }
}

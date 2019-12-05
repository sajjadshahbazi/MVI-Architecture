package com.sharifin.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.WindowManager
import android.view.animation.*
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.item_bubble.view.*
import java.lang.Math.abs


class BubbleHead @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var animateMidYValue = 0.0f
    private var animateMidXValue = 0.0f
    private var dialog: AlertDialog? = null
    var dialogView: View? = null
        set(value) {
            createDialog(value)
        }

    private fun createDialog(value: View?) {
        val alert = AlertDialog.Builder(context, R.style.BubbleHeadDialog)
                .setView(value)
                .setOnDismissListener {
                    bloomAnimation()
                }
                .create()
        alert.setOnShowListener {
            alert.window?.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        this.dialog = alert
    }

    private var initialTouchX: Float = 0.toFloat()
    private var initialTouchY: Float = 0.toFloat()
    var initX = 0.0f
    var initY = 0.0f
    private var halfWidth = 0.0f
    private var width = 0.0f
    private var height = 0.0f
    private var parentWidth: Float = 0.0f
    private var parentHeight: Float = 0.0f
    private var lastAction = -1
    private var downActionTimeMili = -1L

    private val sharedPrefHelper: SharedPrefHelper

    private class SharedPrefHelper(context: Context) {
        companion object {
            const val SHARED_PREF_NAME = "BUBBLE_HEAD"
            const val BUBBLE_POSITION_X = "BUBBLE_POSITION_X"
            const val BUBBLE_POSITION_Y = "BUBBLE_POSITION_Y"
        }

        private val sharedPrefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        fun saveCoor(x: Float, y: Float) {
            sharedPrefs.edit()
                    .putFloat(BUBBLE_POSITION_X, x)
                    .putFloat(BUBBLE_POSITION_Y, y)
                    .apply()
        }

        fun getLastX(): Float =
                sharedPrefs
                        .getFloat(BUBBLE_POSITION_X, 0F)


        fun getLastY(): Float =
                sharedPrefs
                        .getFloat(BUBBLE_POSITION_Y, 300F)


    }

    init {
        sharedPrefHelper = SharedPrefHelper(context)
        init(context, attrs)
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (visibility == View.VISIBLE) {
            setInitialPosition()
        }
    }

    fun jiggleAnimation() {
        postDelayed({
            val anim = RotateAnimation(
                    8f,
                    -8f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f
            ).apply {
                duration = 60
                repeatMode = Animation.REVERSE
                repeatCount = 5
                fillAfter = false
                interpolator = AccelerateDecelerateInterpolator()
            }
            bubble.startAnimation(anim)
        }, 3000)
    }

    private fun setInitialPosition() {
        this.x = sharedPrefHelper.getLastX()
        this.y = sharedPrefHelper.getLastY()
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        inflate(context, R.layout.item_bubble, this)
        if (visibility == View.VISIBLE) {
            setInitialPosition()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = measuredWidth.toFloat()
        halfWidth = width / 2
        height = measuredHeight.toFloat()
        val parentTreeObserver = (parent as View).viewTreeObserver
        if (parentTreeObserver.isAlive) {
            parentTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    parentTreeObserver.removeOnGlobalLayoutListener(this)
                    parentWidth = (parent as View).measuredWidth.toFloat()
                    parentHeight = (parent as View).measuredHeight.toFloat()
                    animateMidXValue = parentWidth / 2
                    animateMidYValue = parentHeight / 2
                }
            })
        }
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

    private fun foldAnimation() {
        val rotateAnim = RotateAnimation(
                0f,
                270f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        ).apply {
            duration = 300
            fillAfter = true
            interpolator = AccelerateDecelerateInterpolator()
        }
        bubble.startAnimation(rotateAnim)

        animate().translationY(animateMidYValue - halfWidth)
                .apply {
                    interpolator = AccelerateInterpolator()
                    duration = 300
                }.withLayer()
                .start()
        animate().translationX(animateMidXValue - halfWidth)
                .apply {
                    interpolator = AccelerateInterpolator()
                    duration = 300
                }.withLayer()
                .start()
        animate().scaleY(0.0f)
                .apply {
                    interpolator = AccelerateInterpolator()
                    duration = 300
                }.start()
        animate().scaleX(0.0f)
                .apply {
                    interpolator = AccelerateInterpolator()
                    duration = 300
                }.start()

    }

    private fun bloomAnimation() {
        val rotateAnim = RotateAnimation(
                270f,
                0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        ).apply {
            duration = 300
            fillAfter = true
            interpolator = AccelerateDecelerateInterpolator()
        }


        animate().translationY(sharedPrefHelper.getLastY())
                .apply {
                    interpolator = DecelerateInterpolator()
                    duration = 300
                }.start()
        bubble.startAnimation(rotateAnim)
        animate().translationX(sharedPrefHelper.getLastX())
                .apply {
                    interpolator = DecelerateInterpolator()
                    duration = 300
                }.start()
        animate().scaleY(1f)
                .apply {
                    interpolator = AccelerateInterpolator()
                    duration = 300
                }.start()
        animate().scaleX(1f)
                .apply {
                    interpolator = AccelerateInterpolator()
                    duration = 300
                }.start()
    }

    private fun handleClick() {
        foldAnimation()
        dialog?.show()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {

                initX = this.x
                initY = this.y
                initialTouchX = event.rawX
                initialTouchY = event.rawY
                lastAction = event.action
                downActionTimeMili = System.currentTimeMillis()
                return true
            }
            MotionEvent.ACTION_UP -> {
                //As we implemented on touch listener with ACTION_MOVE,
                //we have to check if the previous action was ACTION_DOWN
                //to identify if the user clicked the view or not.
                val currentTime = System.currentTimeMillis()
                if (lastAction == MotionEvent.ACTION_DOWN
                        && currentTime - downActionTimeMili < 1000) {
                    handleClick()
                    sharedPrefHelper.saveCoor(x, y)
                    return true
                }
                if (lastAction == MotionEvent.ACTION_MOVE
                        && abs(initialTouchY - event.rawY) < 30
                        && abs(initialTouchX - event.rawX) < 30) {
                    if ((this.x + halfWidth) / parentWidth > 0.5) {
                        sharedPrefHelper.saveCoor(parentWidth - width, y)
                    } else {
                        sharedPrefHelper.saveCoor(0f, y)
                    }
                    handleClick()
                    return true
                }

                if ((this.x + halfWidth) / parentWidth > 0.5) {
                    sharedPrefHelper.saveCoor(parentWidth - width, y)
                    animate().translationX(parentWidth - width)
                            .apply {
                                interpolator = AccelerateDecelerateInterpolator()
                            }.start()
                } else {
                    sharedPrefHelper.saveCoor(0f, y)
                    animate().translationX(0f)
                            .apply {
                                interpolator = AccelerateDecelerateInterpolator()
                            }.start()
                }
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                //Calculate the X and Y coordinates of the view.
                val moveX = event.rawX - initialTouchX
                val moveY = event.rawY - initialTouchY
                val nextX = initX + moveX
                val nextY = initY + moveY
                when {
                    nextX < 0 -> this.x = 0.0f
                    nextX > parentWidth - width -> this.x = parentWidth - width
                    else -> this.x = nextX
                }
                when {
                    nextY < 0 -> this.y = 0.0f
                    nextY > parentHeight - height -> this.y = parentHeight - height
                    else -> this.y = nextY
                }
                lastAction = event.action
                return true
            }

        }
        return false
    }

}
package com.gymix.training

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import java.util.*
import kotlin.math.roundToInt

class TallyCounterView : View, TallyCounter {

    private lateinit var backgroundPaint: Paint
    private lateinit var linePaint: Paint
    private lateinit var numberPaint: Paint

    private lateinit var backgroundRect: RectF

    private var cornerRadius: Float = 0f
    private var displayText = ""
    private var counter: Int = 0

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context)
    }

    private fun init(context: Context) {
        backgroundPaint = Paint()
        backgroundPaint.isAntiAlias = true
        backgroundPaint.color = ContextCompat.getColor(context, R.color.red)

        linePaint = Paint()
        linePaint.isAntiAlias = true
        linePaint.color = ContextCompat.getColor(context, R.color.blue)
        linePaint.strokeWidth = 1f

        numberPaint = Paint()
        numberPaint.isAntiAlias = true
        numberPaint.color = ContextCompat.getColor(context, R.color.blue)
        numberPaint.textSize = 64f * resources.displayMetrics.density

        backgroundRect = RectF()

        cornerRadius = 16f * resources.displayMetrics.density

        setCount(0)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {

    }
    override fun onDraw(canvas: Canvas) {
        val canvasWidth = canvas.width.toFloat()
        val canvasHeight = canvas.height.toFloat()

        val centerX = canvasWidth * 0.5f

        //Draw the background
        backgroundRect.set(0f, 0f, canvasWidth, canvasHeight)
        canvas.drawRoundRect(backgroundRect, cornerRadius, cornerRadius, backgroundPaint)

        //Draw the base line
        val baseLineY: Float = (canvasHeight * 0.6f).roundToInt().toFloat()
        canvas.drawLine(0f, baseLineY, canvasWidth, baseLineY, linePaint)

        //Draw the text
        val textWidth: Float = numberPaint.measureText(displayText)
        val textX: Float = centerX - 0.5f * textWidth
        canvas.drawText(displayText, textX, baseLineY, numberPaint)
    }

    override fun reset() {
        setCount(0)
    }

    override fun increment() {
        setCount(++counter)
    }

    override fun getCount(): Int {
        return counter
    }

    override fun setCount(count: Int) {
        counter = count.coerceAtMost(MAX_COUNT)
        this.displayText = String.format(Locale.getDefault(), "%d", count)
        invalidate()
    }

    override fun isActive(): Boolean {
        return counter < MAX_COUNT
    }

    companion object{
        const val MAX_COUNT = 1000
    }
}
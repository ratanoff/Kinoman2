package ru.ratanov.kinoman.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

class TopItemView(context: Context) : ImageView(context) {

    private val SPANS_COUNT = 3
    private val PADDING = 1

    init {
        scaleType = ScaleType.CENTER_CROP
        layoutParams = LinearLayout.LayoutParams(measuredWidth, measuredHeight).apply {
            setPadding(1, 1, 1, 1)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val parentWidth = (parent as View).measuredWidth

        val preferedWidth = (parentWidth / SPANS_COUNT) - PADDING * 2
        val preferedHeight = (preferedWidth * 1.5).toInt()

        val width = MeasureSpec.makeMeasureSpec(preferedWidth, MeasureSpec.EXACTLY)
        val height = MeasureSpec.makeMeasureSpec(preferedHeight, MeasureSpec.EXACTLY)

        setMeasuredDimension(width, height)
    }
}
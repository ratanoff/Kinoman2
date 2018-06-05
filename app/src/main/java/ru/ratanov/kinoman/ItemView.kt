package ru.ratanov.kinoman

import android.content.Context
import android.widget.ImageView

class ItemView(context: Context) : ImageView(context) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.makeMeasureSpec(360, MeasureSpec.EXACTLY)
        val height = MeasureSpec.makeMeasureSpec(540, MeasureSpec.EXACTLY)

        setMeasuredDimension(width, height)
    }
}
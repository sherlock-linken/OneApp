package com.witbit.sherlock.single_picker

// CenterItemDecorator.kt
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class CenterItemDecorator(
    private var textSize: Float = 16f,
    private var itemHeight: Float = 50f,
    private var isCircular: Boolean = false
) : RecyclerView.ItemDecoration() {

    private val centerLinePaint = Paint().apply {
        color = Color.GRAY
        strokeWidth = 2f
        style = Paint.Style.STROKE
    }

    private val dimPaint = Paint().apply {
        color = Color.GRAY
        alpha = 150
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        // 绘制中心线
        val centerY = parent.height / 2f
        c.drawLine(0f, centerY, parent.width.toFloat(), centerY, centerLinePaint)

        // 绘制渐变效果
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val childCenterY = child.top + child.height / 2f
            val distanceFromCenter = abs(childCenterY - centerY)

            // 根据距离中心的远近设置透明度
            val maxDistance = itemHeight * 1.5f
            if (distanceFromCenter > maxDistance) {
                child.alpha = 0.3f
            } else {
                child.alpha = 1f - (distanceFromCenter / maxDistance) * 0.7f
            }
        }
    }

    fun setTextSize(size: Float) {
        textSize = size
    }

    fun setItemHeight(height: Float) {
        itemHeight = height
    }

    fun setCircular(circular: Boolean) {
        isCircular = circular
    }
}
package com.yazao.wan.weight

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.widget.TextView

object TextViewCompoundDrawableHelper {

    private const val DRAWABLE_LEFT: Int = 0
    private const val DRAWABLE_TOP: Int = 1
    private const val DRAWABLE_RIGHT: Int = 2
    private const val DRAWABLE_BOTTOM: Int = 3

    fun preDraw(view: TextView, canvas: Canvas?) {
        canvas?.let {

            val compoundDrawables = view.compoundDrawables

            when {
                compoundDrawables[DRAWABLE_LEFT] != null -> {
                    view.gravity = Gravity.CENTER_VERTICAL or Gravity.LEFT

                    onCompoundDrawableDraw(
                        view,
                        it,
                        compoundDrawables[DRAWABLE_LEFT],
                        Gravity.LEFT
                    )
                }
                compoundDrawables[DRAWABLE_TOP] != null -> {
                    view.gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                    onCompoundDrawableDraw(
                        view,
                        it,
                        compoundDrawables[DRAWABLE_TOP],
                        Gravity.TOP
                    )
                }
                compoundDrawables[DRAWABLE_RIGHT] != null -> {
                    view.gravity = Gravity.CENTER_VERTICAL or Gravity.RIGHT
                    onCompoundDrawableDraw(
                        view,
                        it,
                        compoundDrawables[DRAWABLE_RIGHT],
                        Gravity.RIGHT
                    )
                }
                compoundDrawables[DRAWABLE_BOTTOM] != null -> {
                    view.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
                    onCompoundDrawableDraw(
                        view,
                        it,
                        compoundDrawables[DRAWABLE_BOTTOM],
                        Gravity.BOTTOM
                    )
                }

                else -> {
                }
            }
        }
    }

    private fun onCompoundDrawableDraw(
        view: TextView,
        canvas: Canvas,
        drawable: Drawable,
        gravity: Int
    ) {

        val compoundDrawablePadding = view.compoundDrawablePadding
        val fontMetrics = view.paint.fontMetrics

        val textWidth = view.paint.measureText(view.text.toString()) + drawable.intrinsicWidth
        val textHeight = fontMetrics.descent - fontMetrics.ascent + drawable.intrinsicHeight

        val total: Float

        when (gravity) {
            Gravity.LEFT -> {
                total = textWidth + compoundDrawablePadding + view.paddingLeft + view.paddingRight
                canvas.translate((view.width - total) / 2, 0f)
            }
            Gravity.TOP -> {
                total = textHeight + compoundDrawablePadding + view.paddingTop + view.paddingBottom
                canvas.translate(0f, (view.height - total) / 2)
            }
            Gravity.RIGHT -> {
                total = textWidth + compoundDrawablePadding + view.paddingLeft + view.paddingRight
                canvas.translate(-(view.width - total) / 2, 0f)
            }
            Gravity.BOTTOM -> {
                total = textHeight + compoundDrawablePadding + view.paddingTop + view.paddingBottom
                canvas.translate(0f, -(view.height - total) / 2)
            }
        }
    }


}

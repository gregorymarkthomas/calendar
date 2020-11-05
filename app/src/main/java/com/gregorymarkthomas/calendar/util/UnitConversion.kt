package com.gregorymarkthomas.calendar.util

import android.content.Context
import android.util.TypedValue



object UnitConversion {
    fun dpToPX(dp: Int, context: Context): Int = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                context.resources.displayMetrics
        ).toInt()
}
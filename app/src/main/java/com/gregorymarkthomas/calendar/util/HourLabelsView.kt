package com.gregorymarkthomas.calendar.util

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.LinearLayout
import com.gregorymarkthomas.calendar.R

class HourLabelsView: LinearLayout {
    constructor(context: Context): super(context) {
        inflate()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        inflate()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        inflate()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        inflate()
    }

    private fun inflate() {
        inflate(context, R.layout.hour_labels, this)
        orientation = VERTICAL
    }

}
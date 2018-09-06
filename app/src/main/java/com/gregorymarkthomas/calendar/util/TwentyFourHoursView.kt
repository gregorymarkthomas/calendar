package com.gregorymarkthomas.calendar.util

import android.content.Context
import android.graphics.Canvas
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.gregorymarkthomas.calendar.R

class TwentyFourHoursView: LinearLayout {
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
        inflate(context, R.layout.twentyfour_hours_view, this)

        /** Expand 'this' LinearLayout to use all of the usable space. **/
        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}
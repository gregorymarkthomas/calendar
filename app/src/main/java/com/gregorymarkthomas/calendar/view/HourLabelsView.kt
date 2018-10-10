package com.gregorymarkthomas.calendar.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.gregorymarkthomas.calendar.R

class HourLabelsView: ViewGroup {
    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes)

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        /** Do nothing **/
    }

    /**
     * We use visibility GONE. The EventContainerView will still have the height.
     */
    fun initialise(root: ViewGroup, height: Int, visible: Boolean) {
        inflate(context, R.layout.hour_labels, root)
        layoutParams.height = height
        visibility = if(visible) View.VISIBLE else View.GONE
    }
}
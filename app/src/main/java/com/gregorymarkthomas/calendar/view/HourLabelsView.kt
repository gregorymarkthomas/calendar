package com.gregorymarkthomas.calendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.util.UnitConversion

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
    fun initialise(root: ViewGroup, heightDP: Int, visible: Boolean) {
        inflate(context, R.layout.hour_label, root)
        layoutParams.height = heightDP
        visibility = if(visible) View.VISIBLE else View.GONE

        createLabels(heightDP, context)
    }


    /**
     * We want labels at each of the 24 equal sections of the view.
     * Setting margins programmatically is done in px and not DP. This is why we convert from DP to px.
     */
    private fun createLabels(heightDP: Int, context: Context) {
        val spacingPx = UnitConversion.dpToPX(heightDP, context) / 24

        for(i in 0..24) {
            val label = inflate(context, R.layout.hour_label, null) as TextView

            val params = MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            params.setMargins(0, 0, 0, spacingPx)
            label.layoutParams = params

            label.text = "$i:00"

            addView(label)
        }
    }
}
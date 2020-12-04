package com.gregorymarkthomas.calendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.util.UnitConversion

/**
 * This is the space where Events are shown. It represents 00:00 to 23:59.
 * Using the height of this inflated view, we need to calculate how many density pixels represent 1 minute of time.
 * This is so we can draw the AppEvent view and ensure it expands in height to the correct number of minutes.
 */
class EventContainerView: ViewGroup {
    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes)

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        /** Do nothing **/
    }

    /**
     * We use visibility INVISIBLE. This is so we can still see Events.
     */
    fun initialise(root: ViewGroup, definedHeight: Int, visible: Boolean) {
        inflate(context, R.layout.event_container, root)
        layoutParams.height = definedHeight
        visibility = if(visible) View.VISIBLE else View.INVISIBLE

        createDividers(definedHeight, context)
    }

    // TODO: dpPerMinute is 0
    // TODO: day.events is empty
    // TODO: is Float correct here?
    fun setEvents(day: AppDay) {
        val dpPerMinute = calculateDensityPixelsPerMinute()

        for(event in day.events) {
            val eventView = EventBlockView(context)
            eventView.set(event, dpPerMinute)
            addView(eventView)
        }
    }

    /**
     * We want dividers at each of the 24 equal sections of the view.
     * Setting margins programmatically is done in px and not DP. This is why we convert from DP to px.
     */
    private fun createDividers(heightDP: Int, context: Context) {
        val spacingPx = UnitConversion.dpToPX(heightDP, context) / 24

        for(i in 0..24) {
            val divider = inflate(context, R.layout.divider, null)

            val params = MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            params.setMargins(0, 0, 0, spacingPx)
            divider.layoutParams = params
            addView(divider)
        }
    }

    /**
     * TODO: is this layoutParams.height, or height? height is 0 because the view has not been inflated properly yet.
     * // TODO: is Float correct here?
     * Calculates how many pixels there are in a minute.
     * There are 1440 minutes in a 24 hour period.
     * Our view represents 1439 minutes (00:00 to 23:59)
     */
    private fun calculateDensityPixelsPerMinute(): Float = (layoutParams.height / 1439.0).toFloat()
}
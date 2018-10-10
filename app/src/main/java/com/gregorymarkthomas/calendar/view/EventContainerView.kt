package com.gregorymarkthomas.calendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.view.EventBlockView

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
    fun initialise(root: ViewGroup, height: Int, visible: Boolean) {
        inflate(context, R.layout.event_container, root)
        layoutParams.height = height
        visibility = if(visible) View.VISIBLE else View.INVISIBLE
    }

    fun setEvents(day: AppDay) {
        val dpPerMinute = calculateDensityPixelsPerMinute()

        for(event in day.events) {
            val eventView = EventBlockView(context)
            eventView.set(event, dpPerMinute)
            addView(eventView)
        }
    }


    /**
     * Calculates how many pixels there are in a minute.
     * There are 1440 minutes in a 24 hour period.
     * Our view represents 1439 minutes (00:00 to 23:59)
     */
    private fun calculateDensityPixelsPerMinute() = height / 1439
}
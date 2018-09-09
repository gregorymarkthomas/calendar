package com.gregorymarkthomas.calendar.util

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.view.EventView

/**
 * This is the space where Events are shown. It represents 00:00 to 23:59.
 * Using the height of this inflated view, we need to calculate how many density pixels represent 1 minute of time.
 * This is so we can draw the AppEvent view and ensure it expands in height to the correct number of minutes.
 */
class TimelineView: LinearLayout {
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
        inflate(context, R.layout.timeline_view, this)

        /** Expand 'this' LinearLayout to use all of the usable space. **/
        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    /**
     * Calculates how many pixels there are in a minute.
     * There are 1440 minutes in a 24 hour period.
     * Our view represents 1439 minutes (00:00 to 23:59)
     */
    private fun calculateDensityPixelsPerMinute() = height / 1439

    fun setEvents(day: AppDay) {
        val dpPerMinute = calculateDensityPixelsPerMinute()

        for(event in day.events) {
            val eventView = EventView(context)
            eventView.set(event, dpPerMinute)
            addView(eventView)
        }
    }

}
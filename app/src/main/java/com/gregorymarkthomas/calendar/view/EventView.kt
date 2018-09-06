package com.gregorymarkthomas.calendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.util.Event
import java.util.*

/**
 * View to represent a single Event.
 * Its colourResource depends on the Calendar the Event is under.
 */
class EventView: LinearLayout {
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
        inflate(context, R.layout.event_view, this)

        /** Expand 'this' LinearLayout to use all of the usable space. **/
        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    /**
     * Set colourResource, height, event name, etc
     */
    fun set(event: Event, dpPerMinute: Int) {
        setBackgroundResource(event.calendar.colourResource)
        minimumHeight = calculateHeight(event.startDate, event.endDate, dpPerMinute)
    }

    private fun calculateHeight(startDate: Date, endDate: Date, dpPerMinute: Int): Int {
        val eventDurationMs = endDate.time - startDate.time
        val eventDurationMins: Int = ((eventDurationMs / 1000) * 60).toInt()
        return eventDurationMins * dpPerMinute
    }

}
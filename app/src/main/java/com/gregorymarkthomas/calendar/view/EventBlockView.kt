package com.gregorymarkthomas.calendar.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.model.AppEvent
import kotlinx.android.synthetic.main.event_block_view.view.*
import java.util.*
import kotlin.math.roundToInt

/**
 * View to represent a single AppEvent.
 * Its colourResource depends on the AppCalendar the AppEvent is under.
 */
class EventBlockView: LinearLayout {
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

    /********** public */
    /**
     * Set colourResource, height, event name, etc
     */
    // TODO: is Float correct here?
    fun set(event: AppEvent, dpPerMinute: Float) {
        setBackgroundResource(event.calendar.colourResource)
        minimumHeight = calculateHeight(event.startDate, event.endDate, dpPerMinute)
        setName(event.title)
    }

    /********** private */
    private fun inflate() {
        inflate(context, R.layout.event_block_view, this)

        /** Expand 'this' LinearLayout to use all of the usable space. **/
        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    // TODO: is Float correct here?
    private fun calculateHeight(startDate: Date, endDate: Date, dpPerMinute: Float): Int {
        val eventDurationMs = endDate.time - startDate.time
        val eventDurationMins: Int = ((eventDurationMs / 1000) * 60).toInt()
        return (eventDurationMins * dpPerMinute).roundToInt()
    }

    private fun setName(name: String) {
        eventTitle.text = name
    }
}
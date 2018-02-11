package com.gregorymarkthomas.calendar.util

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gregorymarkthomas.calendar.R
import kotlinx.android.synthetic.main.row_day.view.*

/** 'days' is empty by default **/
class CalendarAdapter(private val context: Context, var callback: CalendarAdapterInterface, private var days: MutableList<Day> = mutableListOf()): RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    private val TAG = "CalendarAdapter"
    
    var addAll: (MutableList<Day>) -> Unit = {
        days = it
    }

    override fun getItemCount(): Int {
        return days.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_day, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(days[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        /** 'with' keeps 'itemView' as the context, meaning we do not have to keep calling itemView **/
        fun bind(day: Day) = with(itemView) {
            tag = day.dayOfMonth

            dayNumberView.text = "$(day.dayOfMonth)"

            /** Manually add 24 'views' to the hours container
             * Ensure each view can be pressed. **/
            addHourViews(day)
        }

        private fun addHourViews(day: Day) {
            for(i in 1..24) {
                val hourView = ConstraintLayout.inflate(context, R.layout.row_hour, itemView as ViewGroup?)

                setTimeView(hourView, i)
                setEventsButtons(day)

                /** Expand 'this' ConstraintLayout to use all of the usable space. **/
                hourView.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

                /** Set onClickListener for view itself **/
                hourView.setOnClickListener {
                    callback.onTimeClick(day, i)
                }

                itemView?.addView(hourView)
            }
        }

        private fun setTimeView(hourView: View, hour: Int) {
            val timeView = hourView.findViewById<TextView>(R.id.timeView)
            timeView.text = "$hour:00"
        }

        /**
         * There may be multiple buttons for this hour depending on how many events there are that overlap.
         * There is an underlying button that will create a calendar-agnostic event.
         * When it comes to showing all the current events, that button will be overwritten by event buttons (?)
         * TODO - get all the events for this day and fill in the button **/
        private fun setEventsButtons(day: Day) {
            TODO()
        }
    }
    
    interface CalendarAdapterInterface {
        fun onTimeClick(day: Day, hour: Int)
    }
}
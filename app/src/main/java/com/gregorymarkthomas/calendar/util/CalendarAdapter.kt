package com.gregorymarkthomas.calendar.util

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.R.id.dayNumberView
import kotlinx.android.synthetic.main.row_day.view.*

/** 'days' is empty by default **/
class CalendarAdapter(private val context: Context,
                      var callback: CalendarAdapterInterface,
                      private var availableSpaceDP: Int,
                      private var days: MutableList<Day> = mutableListOf()): RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

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

            setEventsViews(day)
        }

        private fun setEventsViews(day: Day) {
            var timelineView: TimelineView = itemView.findViewById(R.id.timelineView)
            timelineView.setEvents(day)
        }
    }
    
    interface CalendarAdapterInterface {
        fun onTimeClick(day: Day, hour: Int)
    }
}
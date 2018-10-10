package com.gregorymarkthomas.calendar.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.model.AppDay
import kotlinx.android.synthetic.main.row_day.view.*

/** 'days' is empty by default **/
class CalendarAdapter(var callback: CalendarAdapterInterface,
                      private var dayViewHeightDP: Int,
                      private var showLabels: Boolean = true,
                      private var days: List<AppDay> = listOf()): RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    private val TAG = "CalendarAdapter"
    
    var addAll: (List<AppDay>) -> Unit = {
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
        fun bind(day: AppDay) = with(itemView) {
            tag = day.dayOfMonth
            dayNumberView.text = "${day.dayOfMonth}"
            eventContainerView.initialise(dayLayout, dayViewHeightDP, showLabels)
            hourLabelsView.initialise(dayLayout, dayViewHeightDP, showLabels)
            setEventsViews(day)
        }

        private fun setEventsViews(day: AppDay) {
            val eventContainerView: EventContainerView = itemView.findViewById(R.id.eventContainerView)
            eventContainerView.setEvents(day)
        }
    }
    
    interface CalendarAdapterInterface {
        fun onTimeClick(day: AppDay, hour: Int)
    }
}
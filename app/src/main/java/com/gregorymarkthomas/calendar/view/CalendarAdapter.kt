package com.gregorymarkthomas.calendar.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.model.AppDay
import kotlinx.android.synthetic.main.day_grid_item.view.*

/** 'days' is empty by default **/
class CalendarAdapter(var callback: CalendarAdapterInterface,
                      private val availableHeightDP: Int,
                      private val columnCount: Int,
                      private val showLabels: Boolean = true,
                      private var days: List<AppDay> = listOf()): RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    private val TAG = "CalendarAdapter"
    private var dayViewHeight: Int = 0

    var addAll: (List<AppDay>) -> Unit = {
        days = it
        dayViewHeight = getDayViewHeight(availableHeightDP, columnCount, days.size)
    }

    override fun getItemCount(): Int {
        return days.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.day_grid_item, parent, false),
                dayViewHeight,
                showLabels
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(days[position])
    }

    inner class ViewHolder(itemView: View, private val dayViewHeight: Int, private val showLabels: Boolean): RecyclerView.ViewHolder(itemView) {
        /** 'with' keeps 'itemView' as the context, meaning we do not have to keep calling itemView **/
        fun bind(day: AppDay) = with(itemView) {
            tag = day.dayOfMonth
            dayNumberView.text = "${day.dayOfMonth}"
            eventContainerView.initialise(dayLayout, dayViewHeight, showLabels)
            hourLabelsView.initialise(dayLayout, dayViewHeight, showLabels)
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


    private fun getDayViewHeight(availableHeightDP: Int, columnCount: Int, dayCount: Int): Int {
        val rowCount: Double = Math.ceil(dayCount.toDouble() / columnCount.toDouble())
        return (availableHeightDP / rowCount).toInt()
    }
}
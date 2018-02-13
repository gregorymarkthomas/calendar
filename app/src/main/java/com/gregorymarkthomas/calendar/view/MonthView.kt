package com.gregorymarkthomas.calendar.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.gregorymarkthomas.calendar.MainActivity
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.presenter.CalendarPresenterInterface
import com.gregorymarkthomas.calendar.util.CalendarAdapter
import com.gregorymarkthomas.calendar.util.Day
import com.gregorymarkthomas.calendar.util.LifeCycleView
import kotlinx.android.synthetic.main.month_view.view.*

class MonthView: LifeCycleView, CalendarViewInterface, CalendarAdapter.CalendarAdapterInterface, View.OnClickListener {
    private val TAG = "MonthView"

    private var presenter: CalendarPresenterInterface = CalendarPresenter(this, backstack)
    private lateinit var adapter: CalendarAdapter

    companion object {
        const val DATE_ARG = "date"
    }
    constructor(activity: MainActivity, args: Bundle): super(activity) {
        setupTodayButton()
        setupAdapter()

        /** This must be called last. **/
        presenter.onViewCreated(args)
    }

    override fun getLayout(): Int = R.layout.month_view

    override fun showDates(days: MutableList<Day>) {
        adapter.addAll(days)
        adapter.notifyDataSetChanged()
    }

    override fun setDateView(dayOfMonth: Int, monthOfYear: String, year: Int) {
        dateTextView.text = "$dayOfMonth $monthOfYear $year"
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.viewTodayButton -> presenter.onTodayButtonPress()
        }
    }

    override fun onTimeClick(day: Day, hour: Int) {
        presenter.onDayPress(day, hour)
    }

    private fun setupTodayButton() {
        viewTodayButton.setOnClickListener(this)
    }

    private fun setupAdapter() {
        calendarRecyclerView.layoutManager = GridLayoutManager(context, 1)
        adapter = CalendarAdapter(context, this, calendarRecyclerView.height)
        calendarRecyclerView.adapter = adapter
    }
}
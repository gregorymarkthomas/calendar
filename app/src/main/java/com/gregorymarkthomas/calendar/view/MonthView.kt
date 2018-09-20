package com.gregorymarkthomas.calendar.view

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.presenter.CalendarPresenterInterface
import com.gregorymarkthomas.calendar.util.CalendarAdapter
import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.interfaces.LayoutContextInterface
import kotlinx.android.synthetic.main.month_view.view.*
import java.util.*

class MonthView(backstack: BackStackInterface, model: ModelInterface, layout: LayoutContextInterface, date: Date): LifeCycleView(layout), CalendarViewInterface, CalendarAdapter.CalendarAdapterInterface, View.OnClickListener {
    private val TAG = "MonthView"

    private var presenter: CalendarPresenterInterface
    private lateinit var adapter: CalendarAdapter

    companion object {
        const val DATE_ARG = "date"
    }

    init {
        setupTodayButton()
        setupAdapter()

        /** This must be called last. **/
        this.presenter = CalendarPresenter(this, model, backstack, date)
    }

    override fun getLayout(): Int = R.layout.month_view

    override fun showDates(days: List<AppDay>) {
        adapter.addAll(days)
        adapter.notifyDataSetChanged()
    }

    /** TODO() - rename to setSelectedDateView() **/
    override fun setDateView(dayOfMonth: Int, monthOfYear: String, year: Int) {
        dateTextView.text = "$dayOfMonth $monthOfYear $year"
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.viewTodayButton -> presenter.onTodayButtonPress()
        }
    }

    override fun onTimeClick(day: AppDay, hour: Int) {
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
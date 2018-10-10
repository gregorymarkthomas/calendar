package com.gregorymarkthomas.calendar.view

import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.presenter.CalendarPresenterInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.interfaces.AndroidContextInterface
import kotlinx.android.synthetic.main.month_view.view.*
import java.util.*

class MonthView(private val date: Date): LifeCycleView(), CalendarViewInterface, CalendarAdapter.CalendarAdapterInterface, View.OnClickListener {
    private lateinit var presenter: CalendarPresenterInterface
    private lateinit var adapter: CalendarAdapter

    override fun onInitialise(backstack: BackStackInterface, model: ModelInterface, context: AndroidContextInterface) {
        setupTodayButton()
        setupAdapter(context)

        /** This must be called last. **/
        this.presenter = CalendarPresenter(this, model, backstack, date)
    }

    override fun getTag(): String = "MonthView"

    override fun getLayout(): Int = R.layout.month_view

    override fun showDates(days: List<AppDay>) {
        adapter.addAll(days)
        adapter.notifyDataSetChanged()
    }

    override fun setSelectedDateView(dayOfMonth: Int, monthOfYear: String, year: Int) {
        view!!.dateTextView.text = "$monthOfYear $year"
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
        view!!.viewTodayButton.setOnClickListener(this)
    }

    /**
     * We want to show 7 days per row, so we need 7 columns for the adapter.
     */
    private fun setupAdapter(context: AndroidContextInterface) {
        view!!.calendarRecyclerView.layoutManager = GridLayoutManager(context.getContext(), 7)
        adapter = CalendarAdapter(this, view!!.calendarRecyclerView.height, false)
        view!!.calendarRecyclerView.adapter = adapter
    }
}
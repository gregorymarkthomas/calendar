package com.gregorymarkthomas.calendar.view

import android.view.View
import com.gregorymarkthomas.calendar.MainActivity
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.presenter.CalendarPresenterInterface
import com.gregorymarkthomas.calendar.util.LifeCycleView
import kotlinx.android.synthetic.main.month_view.view.*
import java.util.*

class MonthView: LifeCycleView, CalendarViewInterface, View.OnClickListener {

    private val TAG = "MonthView"
    private var presenter: CalendarPresenterInterface
    private var date: Date

    constructor(activity: MainActivity, date: Date = Date()): super(activity) {
        this.presenter = CalendarPresenter(this, backstack)
        this.date = date

        presenter.onViewCreated(date)
        viewTodayButton.setOnClickListener(this)
    }

    override fun getLayout(): Int = R.layout.month_view

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.viewTodayButton -> presenter.onTodayButtonPress()
        }
    }

    override fun setDateView(date: String) {
        dateTextView.text = date
    }
}
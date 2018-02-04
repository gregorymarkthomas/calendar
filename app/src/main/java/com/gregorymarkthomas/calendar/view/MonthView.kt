package com.gregorymarkthomas.calendar.view

import android.content.Context
import android.view.View
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.presenter.CalendarPresenterInterface
import com.gregorymarkthomas.calendar.util.LifeCycleView
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import kotlinx.android.synthetic.main.month_view.view.*
import java.util.*


class MonthView(context: Context, var backstack: BackStackInterface, private var date: Date = Date()): LifeCycleView(context), CalendarViewInterface, View.OnClickListener {

    private val TAG = "MonthView"
    private lateinit var presenter: CalendarPresenterInterface

    override fun getLayout(): Int = R.layout.month_view

    /**
     * 'presenter' has to be defined here due to how LifeCycleView inflates the view
     */
    override fun onViewReady() {
        presenter = CalendarPresenter(this, backstack)
        presenter.onViewCreated(date)

        viewTodayButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.viewTodayButton -> presenter.onTodayButtonPress()
        }
    }

    override fun setDateView(date: String) {
        dateTextView.text = date
    }
}
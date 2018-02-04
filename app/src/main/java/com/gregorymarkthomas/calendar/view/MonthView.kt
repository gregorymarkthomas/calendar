package com.gregorymarkthomas.calendar.view

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.presenter.CalendarPresenterInterface
import com.gregorymarkthomas.calendar.util.LifeCycleView
import kotlinx.android.synthetic.main.month_view.view.*


class MonthView(context: Context): LifeCycleView(context), CalendarViewInterface, View.OnClickListener {

    private val TAG = "MonthView"
    private lateinit var presenter: CalendarPresenterInterface

    override fun getLayout(): Int = R.layout.month_view

    /**
     * 'presenter' has to be defined here due to how LifeCycleView inflates the view
     */
    override fun onViewReady() {
        presenter = CalendarPresenter(this)
        presenter.onViewCreated()
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDateView(date: String) {
        dateTextView.text = date
    }
}
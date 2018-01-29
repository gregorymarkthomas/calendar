package com.gregorymarkthomas.calendar.view

import android.content.Context
import android.graphics.Canvas
import android.view.View
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.presenter.CalendarPresenterInterface

import kotlinx.android.synthetic.main.month_view.view.*

class MonthView(context: Context): View(context), CalendarViewInterface, View.OnClickListener {

    private var presenter: CalendarPresenterInterface = CalendarPresenter(this)

    init {
        inflate(context, R.layout.month_view, null)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        presenter.onViewCreated()
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDateView(date: String) {
        dateTextView.text = date
    }

}
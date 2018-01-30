package com.gregorymarkthomas.calendar.view

import android.content.Context
import android.graphics.Canvas
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.presenter.CalendarPresenterInterface

import kotlinx.android.synthetic.main.month_view.view.*

class MonthView: View, CalendarViewInterface, View.OnClickListener {

    private val TAG = "MonthView"
    private var presenter: CalendarPresenterInterface = CalendarPresenter(this)

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of MonthView.
         */
        fun newInstance(context: Context, viewGroup: ViewGroup): MonthView = MonthView(context, viewGroup)
    }

    constructor(context: Context, viewGroup: ViewGroup) : super(context) {
        inflate(context, R.layout.month_view, viewGroup)
    }

//    init {
//        inflate(context, R.layout.month_view, viewGroup)
//    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        presenter.onViewCreated()
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDateView(date: String) {
        dateTextView.text = date
    }

}
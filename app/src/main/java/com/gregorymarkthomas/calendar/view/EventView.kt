package com.gregorymarkthomas.calendar.view

import android.view.View
import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.interfaces.AndroidContextInterface

class EventView(): LifeCycleView(), CalendarViewInterface, View.OnClickListener {
    override fun getTag(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onViewInitialised(backstack: BackStackInterface, model: ModelInterface, context: AndroidContextInterface) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setSelectedDateView(dayOfMonth: Int, monthOfYear: String, year: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDates(days: List<AppDay>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
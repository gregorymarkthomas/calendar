package com.gregorymarkthomas.calendar.view

import android.view.View
import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.util.CalendarAdapter
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.interfaces.AndroidContextInterface
import java.util.*

class DayView(private val date: Date): LifeCycleView(), CalendarViewInterface, CalendarAdapter.CalendarAdapterInterface, View.OnClickListener {
    override fun getTag(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInitialise(backstack: BackStackInterface, model: ModelInterface, context: AndroidContextInterface) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setSelectedDateView(dayOfMonth: Int, monthOfYear: String, year: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDates(days: List<AppDay>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTimeClick(day: AppDay, hour: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
package com.gregorymarkthomas.calendar.presenter

import com.gregorymarkthomas.calendar.model.Model
import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.view.CalendarViewInterface
import java.util.*

class CalendarPresenter(private var view: CalendarViewInterface, private var backstack: BackStackInterface): CalendarPresenterInterface {

    private var model: ModelInterface = Model()

    override fun onViewCreated(date: Date) {
        view.setDateView(date.toString())
    }

    override fun onDayPress(dayOfMonth: Int, monthOfYear: Int, year: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTodayButtonPress() {
        TODO()
    }

    override fun onEventPress(hours: Int, minutes: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
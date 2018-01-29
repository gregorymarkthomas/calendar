package com.gregorymarkthomas.calendar.presenter

import com.gregorymarkthomas.calendar.model.Model
import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.view.CalendarViewInterface

class CalendarPresenter(var view: CalendarViewInterface): CalendarPresenterInterface {

    private var model: ModelInterface = Model()
    private var todayDate = model.getTodayDate()

    override fun onViewCreated() {
        view.setDateView(todayDate.toString())
    }

    override fun onDayPress(dayOfMonth: Int, monthOfYear: Int, year: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTodayButtonPress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEventPress(hours: Int, minutes: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
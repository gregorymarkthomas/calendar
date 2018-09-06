package com.gregorymarkthomas.calendar.model

import java.text.DateFormatSymbols
import java.util.*

class Model: ModelInterface {

    val calendar: CalendarRepository = AndroidCalendarProvider()

    override fun getTodayDate(): Date {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return Date()
    }

    override fun getEvents(dayInMonth: Int, month: Int, year: Int, numberOfDays: Int, callback: Callback.GetEventsCallback) {
        calendar.getEvents(dayInMonth, month, year, numberOfDays, callback)
    }

    override fun getMonthString(month: Int) = DateFormatSymbols().months[month-1]

    override fun getDaysInMonth(month: Int, year: Int): Int {
        // TODO()
        return 30
    }
}
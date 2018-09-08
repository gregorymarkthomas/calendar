package com.gregorymarkthomas.calendar.model

import java.text.DateFormatSymbols
import java.util.*

class Model: ModelInterface {

    val repository: CalendarRepository = AndroidCalendarProvider()

    override fun getTodayDate(): Date {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return Date()
    }

    override fun getEvents(dayInMonth: Int, month: Int, year: Int, numberOfDays: Int, calendarsToShow: IntArray?, callback: Callback.GetEventsCallback) {
        repository.getEvents(dayInMonth, month, year, numberOfDays, calendarsToShow, callback)
    }

    override fun getCalendars(callback: Callback.GetCalendarsCallback) {
        repository.getCalendars(callback)
    }

    override fun getMonthString(month: Int) = DateFormatSymbols().months[month-1]

    override fun getDaysInMonth(month: Int, year: Int): Int {
        // TODO - Perhaps create individual Month objects that have noOfDays field.
        return 30
    }
}
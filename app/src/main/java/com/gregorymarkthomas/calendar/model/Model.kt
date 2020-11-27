package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface
import java.util.*

class Model(private val repository: CalendarRepository, preferences: GetSharedPreferencesInterface): ModelInterface {
    private val calendarVisibiltyOption = VisibleCalendarsOption(preferences)

    override fun getTodayDate(): Date {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return Date()
    }

    override fun getEvents(dayInMonth: Int, month: Int, year: Int, numberOfDays: Int, callback: Callback.GetEventsCallback, calendarsToShow: IntArray) {
        repository.getEvents(dayInMonth, month, year, numberOfDays, callback, calendarsToShow)
    }

    override fun getCalendars(callback: Callback.GetCalendarsCallback) {
        repository.getCalendars(callback)
    }

    override fun getVisibleCalendars(): IntArray {
        return calendarVisibiltyOption.get()
    }

    override fun setVisibleCalendars(calendarsToShow: IntArray) {
        calendarVisibiltyOption.set(calendarsToShow)
    }
}
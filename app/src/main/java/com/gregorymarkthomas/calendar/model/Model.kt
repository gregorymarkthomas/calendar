package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.util.interfaces.ContentResolverInterface
import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface
import java.text.DateFormatSymbols
import java.util.*

class Model(resolver: ContentResolverInterface, preferences: GetSharedPreferencesInterface): ModelInterface {

    private val repository: CalendarRepository = AndroidCalendarProvider(resolver)
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

    override fun getDaysInMonth(month: Int, year: Int): Int {
        // TODO - Perhaps create individual Month objects that have noOfDays field.
        return 30
    }

    override fun getVisibleCalendars(): IntArray {
        return calendarVisibiltyOption.get()
    }

    override fun setVisibleCalendars(calendarsToShow: IntArray) {
        calendarVisibiltyOption.set(calendarsToShow)
    }
}
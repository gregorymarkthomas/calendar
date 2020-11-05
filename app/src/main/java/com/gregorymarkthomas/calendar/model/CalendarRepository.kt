package com.gregorymarkthomas.calendar.model

/**
 * Calendar data could come from Android's Calendar Provider, or perhaps the Google Calendar API.
 */
interface CalendarRepository {
    fun getEvents(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, callback: Callback.GetEventsCallback, calendarsToShow: IntArray)
    fun getCalendars(callback: Callback.GetCalendarsCallback)
}
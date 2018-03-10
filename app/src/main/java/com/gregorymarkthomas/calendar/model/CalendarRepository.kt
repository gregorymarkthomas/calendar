package com.gregorymarkthomas.calendar.model

/**
 * Calendar data could come from Android's Calendar Provider, or perhaps the Google Calendar API.
 */
interface CalendarRepository {
    fun getEvents(dayInMonth: Int, month: Int, year: Int, numberOfDays: Int, callback: Callback.GetEventsCallback)
}
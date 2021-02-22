package com.gregorymarkthomas.calendar.model.repository

interface CalendarVisibilityRepository {
    fun getVisibleCalendars(): IntArray
    fun setVisibleCalendars(visibleCalendars: IntArray)
}
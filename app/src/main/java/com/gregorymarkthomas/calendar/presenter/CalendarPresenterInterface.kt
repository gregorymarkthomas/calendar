package com.gregorymarkthomas.calendar.presenter

import com.gregorymarkthomas.calendar.model.AppDay

interface CalendarPresenterInterface {
    fun onDayPress(day: AppDay, hour: Int)
    fun onTodayButtonPress()
    fun onEventPress(hours: Int, minutes: Int)
}
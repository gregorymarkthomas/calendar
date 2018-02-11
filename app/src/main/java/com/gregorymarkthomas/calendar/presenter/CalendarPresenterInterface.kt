package com.gregorymarkthomas.calendar.presenter

import android.os.Bundle
import com.gregorymarkthomas.calendar.util.Day
import java.util.*

interface CalendarPresenterInterface {
    fun onViewCreated(args: Bundle)
    fun onDayPress(day: Day, hour: Int)
    fun onTodayButtonPress()
    fun onEventPress(hours: Int, minutes: Int)
}
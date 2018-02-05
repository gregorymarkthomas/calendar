package com.gregorymarkthomas.calendar.presenter

import android.os.Bundle
import java.util.*

interface CalendarPresenterInterface {
    fun onViewCreated(args: Bundle)
    fun onDayPress(dayOfMonth: Int, monthOfYear: Int, year: Int)
    fun onTodayButtonPress()
    fun onEventPress(hours: Int, minutes: Int)
}
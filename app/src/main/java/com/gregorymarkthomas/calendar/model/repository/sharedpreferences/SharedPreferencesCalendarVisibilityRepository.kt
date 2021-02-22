package com.gregorymarkthomas.calendar.model.repository.sharedpreferences

import com.gregorymarkthomas.calendar.model.repository.CalendarVisibilityRepository
import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface

class SharedPreferencesCalendarVisibilityRepository(preferences: GetSharedPreferencesInterface): CalendarVisibilityRepository {

    override fun getVisibleCalendars(): IntArray {
        TODO("Not yet implemented")
    }

    override fun setVisibleCalendars(visibleCalendars: IntArray) {
        TODO("Not yet implemented")
    }
}
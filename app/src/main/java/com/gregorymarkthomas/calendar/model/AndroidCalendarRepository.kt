package com.gregorymarkthomas.calendar.model

import android.Manifest
import com.gregorymarkthomas.calendar.model.interfaces.NeedsPermission
import com.gregorymarkthomas.calendar.model.interfaces.Resolver
import com.gregorymarkthomas.calendar.presenter.CalendarPermission
import com.gregorymarkthomas.calendar.presenter.contracts.AndroidPermissionContract

class AndroidCalendarRepository(private val contentResolver: Resolver, private val permissionContract: AndroidPermissionContract): NeedsPermission(permissionContract) {

    fun getCalendars(callback: Callback.GetCalendarsCallback) {
        requestPermissions(object: OnAllGranted {
            override fun onAllGranted() {
                callback.onGetCalendars(AndroidCalendarRetriever(contentResolver).get())
            }
        })
    }

    /**
     * This function retrieves the Days that (could) contain Events. The inputs are used to retrieve a span of days in a month and year.
     * This needs to take the CalendarProvider calendar data (via the cursor) and convert it into a list of the app's 'AppDay' objects
     */
    fun getEvents(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, callback: Callback.GetEventsCallback, calendarsToShow: IntArray) {
        requestPermissions(object: OnAllGranted {
            override fun onAllGranted() {
                callback.onGetEvents(AndroidCalendarEventResolver(contentResolver).get(fromDayInMonth, month, year, specifiedNoOfDays, calendarsToShow))
            }
        })
    }

    override fun getRequiredPermissions(): List<CalendarPermission> {
        return listOf(
                CalendarPermission(Manifest.permission.READ_CALENDAR),
                CalendarPermission(Manifest.permission.WRITE_CALENDAR)
        )
    }
}
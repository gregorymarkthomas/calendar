package com.gregorymarkthomas.calendar.model

import android.Manifest
import com.gregorymarkthomas.calendar.model.interfaces.NeedsPermission
import com.gregorymarkthomas.calendar.model.interfaces.Resolver
import com.gregorymarkthomas.calendar.presenter.CalendarPermission
import com.gregorymarkthomas.calendar.presenter.contracts.AndroidPermissionContract

/**
 * Here is the implementation to query the AppCalendar Provider for calendar data.
 * TODO - cache AppCalendar object?
 *
 * TODO() FRIDAY 14th Sept - read this to complete this. We want to pass in a contexted class that can receive a ContentResolver. This should originate from MainActivity and be required by Model (because Model might need some Androidy things).
 * https://stackoverflow.com/questions/39100105/need-context-in-model-in-mvp
 */
class AndroidCalendarRepository(private val contentResolver: Resolver, private val permissionContract: AndroidPermissionContract): CalendarRepository, NeedsPermission(permissionContract) {

    override fun getCalendars(callback: Callback.GetCalendarsCallback) {
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
    override fun getEvents(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, callback: Callback.GetEventsCallback, calendarsToShow: IntArray) {
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
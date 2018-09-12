package com.gregorymarkthomas.calendar.util

import android.database.Cursor
import android.provider.CalendarContract.Events
import android.provider.CalendarContract.Calendars
import com.gregorymarkthomas.calendar.model.AppCalendar
import java.util.*

object CursorExtractor {
    object Event {
        /**
         * Cursor will throw an Exception if any of these are null.
         */
        fun getCalendar(cursor: Cursor): AppCalendar {
            val calendarId = cursor.getInt(cursor.getColumnIndex(Events.CALENDAR_ID))
            val calendarDisplayName = cursor.getString(cursor.getColumnIndex(Events.CALENDAR_DISPLAY_NAME))
            val calendarColour = cursor.getInt(cursor.getColumnIndex(Events.CALENDAR_COLOR))
            val calendarAccessLevel = cursor.getInt(cursor.getColumnIndex(Events.CALENDAR_ACCESS_LEVEL))
            val calendarTimezone = cursor.getString(cursor.getColumnIndex(Events.CALENDAR_TIME_ZONE))
            val accountName = cursor.getString(cursor.getColumnIndex(Events.ACCOUNT_NAME))
            val ownerAccount = cursor.getInt(cursor.getColumnIndex(Events.OWNER_ACCOUNT))
            return AppCalendar(calendarId, calendarDisplayName, calendarColour, calendarAccessLevel, calendarTimezone, accountName, ownerAccount)
        }

        fun getStartDate(cursor: Cursor): Date {
            val startDate = cursor.getLong(cursor.getColumnIndex(Events.DTSTART))
            return Date(startDate)
        }

        fun getEndDate(cursor: Cursor): Date {
            val endDate = cursor.getLong(cursor.getColumnIndex(Events.DTEND))
            return Date(endDate)
        }

        fun getIsAllDay(cursor: Cursor): Boolean {
            val isAllDay = cursor.getInt(cursor.getColumnIndex(Events.ALL_DAY))
            return isAllDay == 1
        }
    }

    object Calendar {
        fun getId(cursor: Cursor): Int {
            return cursor.getInt(cursor.getColumnIndex(Calendars._ID))
        }

        fun getDisplayName(cursor: Cursor): String {
            return cursor.getString(cursor.getColumnIndex(Calendars.CALENDAR_DISPLAY_NAME))
        }

        fun getColour(cursor: Cursor): Int {
            return cursor.getInt(cursor.getColumnIndex(Calendars.CALENDAR_COLOR))
        }

        fun getAccessLevel(cursor: Cursor): Int {
            return cursor.getInt(cursor.getColumnIndex(Calendars.CALENDAR_ACCESS_LEVEL))
        }

        fun getTimezone(cursor: Cursor): String {
            return cursor.getString(cursor.getColumnIndex(Calendars.CALENDAR_TIME_ZONE))
        }

        fun getAccountName(cursor: Cursor): String {
            return cursor.getString(cursor.getColumnIndex(Calendars.ACCOUNT_NAME))
        }

        fun getOwnerAccount(cursor: Cursor): Int {
            return cursor.getInt(cursor.getColumnIndex(Calendars.OWNER_ACCOUNT))
        }
    }
}
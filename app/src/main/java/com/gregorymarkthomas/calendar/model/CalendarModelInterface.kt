package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.backstack.interfaces.ModelInterface
import java.util.*

interface CalendarModelInterface : ModelInterface {
    fun getTodayDate(): Date
    fun getEvents(properties: GetEventProperties, callback: ModelCallback.GetEventsCallback)
    fun getCalendars(accountName: String, callback: ModelCallback.GetCalendarsCallback)
    fun getAvailableAccounts(callback: ModelCallback.GetAccountsCallback)
    fun getAccount(accountName: String, callback: ModelCallback.GetAccountCallback)
    fun getSavedAccount(callback: ModelCallback.GetAccountCallback)
    fun setAccount(accountName: String)
    fun getVisibleCalendars(): IntArray
    fun setVisibleCalendars(calendarsToShow: IntArray)
}
package com.gregorymarkthomas.calendar.model

import java.util.*

interface ModelInterface {
    fun getTodayDate(): Date
    fun getEvents(account: AppAccount, dayInMonth: Int, month: Int, year: Int, numberOfDays: Int, callback: Callback.GetEventsCallback, calendarsToShow: IntArray = IntArray(0))
    fun getCalendars(account: AppAccount, callback: Callback.GetCalendarsCallback)
    fun getAvailableAccounts(callback: Callback.GetAccountsCallback)
    fun getAccount(accountName: String, callback: Callback.GetAccountCallback)
    fun getSavedAccount(callback: Callback.GetAccountCallback)
    fun setAccount(accountName: String)
    fun getVisibleCalendars(): IntArray
    fun setVisibleCalendars(calendarsToShow: IntArray)
}
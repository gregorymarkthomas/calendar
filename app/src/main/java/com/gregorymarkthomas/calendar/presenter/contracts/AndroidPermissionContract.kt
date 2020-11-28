package com.gregorymarkthomas.calendar.presenter.contracts

import com.gregorymarkthomas.calendar.presenter.CalendarPermission

interface AndroidPermissionContract {
    fun isPermissionGranted(permission: CalendarPermission): Boolean
    fun showPermissionDialog(permissions: List<CalendarPermission>)
}
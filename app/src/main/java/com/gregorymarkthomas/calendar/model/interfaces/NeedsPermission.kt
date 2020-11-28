package com.gregorymarkthomas.calendar.model.interfaces

import com.gregorymarkthomas.calendar.presenter.CalendarPermission

interface NeedsPermission {
    fun getRequiredPermissions(): MutableList<CalendarPermission>
}
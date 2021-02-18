package com.gregorymarkthomas.calendar.model.interfaces

import com.gregorymarkthomas.calendar.presenter.CalendarPermission
import com.gregorymarkthomas.calendar.presenter.contracts.ActivityInterface

abstract class NeedsPermission(private val activity: ActivityInterface.PermissionChecker) {

    fun checkPermissions(callback: OnPermissionCheck) {
        val deniedPermissions = mutableListOf<CalendarPermission>()
        for(permission in getRequiredPermissions()) {
            if (!this.activity.isPermissionGranted(permission)) {
                deniedPermissions.add(permission)
            }
        }
        if(deniedPermissions.isNotEmpty()) {
            callback.onFoundDenied(deniedPermissions)
        } else
            callback.onAllGranted()
    }

    protected abstract fun getRequiredPermissions(): List<CalendarPermission>

    interface OnPermissionCheck {
        fun onAllGranted()
        fun onFoundDenied(deniedPermissions: List<CalendarPermission>)
    }
}
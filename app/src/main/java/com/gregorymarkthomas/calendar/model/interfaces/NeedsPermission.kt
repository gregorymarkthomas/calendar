package com.gregorymarkthomas.calendar.model.interfaces

import com.gregorymarkthomas.calendar.presenter.CalendarPermission
import com.gregorymarkthomas.calendar.presenter.contracts.AndroidPermissionContract

abstract class NeedsPermission(private val permissionContract: AndroidPermissionContract) {

    fun checkPermissions(callback: OnPermissionCheck) {
        val deniedPermissions = mutableListOf<CalendarPermission>()
        for(permission in getRequiredPermissions()) {
            if (!this.permissionContract.isPermissionGranted(permission)) {
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
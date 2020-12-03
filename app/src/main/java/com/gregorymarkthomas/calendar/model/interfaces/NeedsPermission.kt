package com.gregorymarkthomas.calendar.model.interfaces

import com.gregorymarkthomas.calendar.presenter.CalendarPermission
import com.gregorymarkthomas.calendar.presenter.contracts.AndroidPermissionContract

abstract class NeedsPermission(private val permissionContract: AndroidPermissionContract) {

    fun requestPermissions(callback: OnAllGranted) {
        val deniedPermissions = getRequiredPermissions().toMutableList()
        for(permission in deniedPermissions) {
            if (this.permissionContract.isPermissionGranted(permission)) {
                deniedPermissions.remove(permission)
            }
        }
        if(deniedPermissions.isNotEmpty()) {
            this.permissionContract.showPermissionDialog(deniedPermissions)
        } else
            callback.onAllGranted()
    }

    protected abstract fun getRequiredPermissions(): List<CalendarPermission>

    interface OnAllGranted {
        fun onAllGranted()
    }
}
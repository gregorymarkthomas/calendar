package com.gregorymarkthomas.calendar.mock

import com.gregorymarkthomas.calendar.presenter.CalendarPermission
import com.gregorymarkthomas.calendar.presenter.contracts.AndroidPermissionContract

class MockGrantedPermissionContract: AndroidPermissionContract {
    override fun isPermissionGranted(permission: CalendarPermission): Boolean {
        return true
    }

    override fun showPermissionDialog(permissions: List<CalendarPermission>) {
        TODO("Not yet implemented")
    }
}
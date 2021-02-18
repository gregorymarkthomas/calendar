package com.gregorymarkthomas.calendar.mock

import com.gregorymarkthomas.calendar.presenter.CalendarPermission
import com.gregorymarkthomas.calendar.presenter.contracts.ActivityInterface

class MockGrantedPermissionContract: ActivityInterface {
    override fun isPermissionGranted(permission: CalendarPermission): Boolean {
        return true
    }

    override fun showPermissionDialog(permissions: List<CalendarPermission>) {
        TODO("Not yet implemented")
    }
}
package com.gregorymarkthomas.calendar.mock

import com.gregorymarkthomas.calendar.presenter.CalendarPermission
import com.gregorymarkthomas.calendar.presenter.contracts.ActivityInterface

class MockGrantedPermissionContract: ActivityInterface.PermissionChecker {
    override fun isPermissionGranted(permission: CalendarPermission): Boolean {
        return true
    }
}
package com.gregorymarkthomas.calendar.presenter.contracts

import com.gregorymarkthomas.calendar.model.AppAccount
import com.gregorymarkthomas.calendar.presenter.CalendarPermission

class ActivityInterface {

    interface PermissionChecker {
        fun isPermissionGranted(permission: CalendarPermission): Boolean
    }

    interface DialogViewer {
        fun showPermissionDialog(permissions: List<CalendarPermission>)
        fun showAccountsDialog(accounts: List<AppAccount>)
    }
}


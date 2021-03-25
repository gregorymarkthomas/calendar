package com.gregorymarkthomas.calendar.presenter.contracts

import com.gregorymarkthomas.calendar.ActivityCallback
import com.gregorymarkthomas.calendar.presenter.CalendarPermission

class ActivityInterface {

    interface PermissionChecker {
        fun isPermissionGranted(permission: CalendarPermission): Boolean
    }

    interface DialogViewer {
        fun showPermissionDialog(permissions: List<CalendarPermission>)
        fun showAccountsDialog(accounts: Array<String>, callback: ActivityCallback.GetChosenAccount)
        fun showNoAccountsDialog()
    }
}


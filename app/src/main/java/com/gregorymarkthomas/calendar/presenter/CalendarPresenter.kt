package com.gregorymarkthomas.calendar.presenter

import android.Manifest
import com.gregorymarkthomas.calendar.model.*
import com.gregorymarkthomas.calendar.model.interfaces.NeedsPermission
import com.gregorymarkthomas.calendar.presenter.contracts.AndroidPermissionContract
import com.gregorymarkthomas.calendar.util.CalendarHelper
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.view.CalendarViewInterface
import com.gregorymarkthomas.calendar.view.MonthView
import java.util.*

/**
 * The View that creates the Presenter will already be initialised/viewable before Presenter initialisation.
 * There should be NO Android stuff in the PresenterInterface.
 * TODO() - How do we deal with timezones of Events?
 */
class CalendarPresenter(private val view: CalendarViewInterface,
                        private val model: ModelInterface,
                        private val backstack: BackStackInterface,
                        private val permissionContract: AndroidPermissionContract,
                        date: Date): CalendarPresenterInterface, NeedsPermission(permissionContract) {

    /** Get the day of month, month and year that has been specified.
     * Show Today's date if no date was supplied.
     * MonthView is always the default, so get CURRENT MONTH'S events.
     * **/
    init {
        val calendar = CalendarHelper.getNewCalendar()
        calendar.time = date
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        view.setSelectedDateView(dayOfMonth, CalendarHelper.getMonthString(month), year)

        checkPermissions(object: OnPermissionCheck {
            override fun onAllGranted() {
                val account = model.getSavedAccount()
                if(account != null) {
                    model.getEvents(account, 1, month, year, CalendarHelper.getDaysInMonth(month, year), object : Callback.GetEventsCallback {
                        override fun onGetEvents(days: List<AppDay>) {
                            view.showDates(days)
                        }
                    })
                } else {
                    model.getAvailableAccounts(object : Callback.GetAccountsCallback {
                        override fun onGetAccounts(accounts: List<AppAccount>) {
                            permissionContract.showAccountsDialog(accounts)
                        }
                    })
                }
            }

            override fun onFoundDenied(deniedPermissions: List<CalendarPermission>) {
                permissionContract.showPermissionDialog(deniedPermissions)
            }
        })
    }

    override fun onDayPress(day: AppDay, hour: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTodayButtonPress() {
        backstack.goTo(MonthView(Date(), permissionContract))
    }

    override fun onEventPress(hours: Int, minutes: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRequiredPermissions(): List<CalendarPermission> {
        return listOf(
                CalendarPermission(Manifest.permission.READ_CALENDAR),
                CalendarPermission(Manifest.permission.WRITE_CALENDAR)
        )
    }
}
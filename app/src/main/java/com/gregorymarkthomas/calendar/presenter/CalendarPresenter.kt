package com.gregorymarkthomas.calendar.presenter

import com.gregorymarkthomas.calendar.model.Callback
import com.gregorymarkthomas.calendar.model.Model
import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.util.AppCalendar
import com.gregorymarkthomas.calendar.util.AppDay
import com.gregorymarkthomas.calendar.util.VisibleCalendarsPreference
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackItem
import com.gregorymarkthomas.calendar.view.CalendarViewInterface
import com.gregorymarkthomas.calendar.view.MonthView
import java.util.*


/**
 * There should be NO Android stuff in the Presenter.
 */
class CalendarPresenter(view: CalendarViewInterface, var backstack: BackStackInterface, date: Date? = null): CalendarPresenterInterface {
    private var model: ModelInterface = Model()

    /** Get the day of month, month and year that has been specified.
     * Show Today's date if no date was supplied. **/
    init {
        val calendar = Calendar.getInstance()
        if(date != null)
            calendar.time = date
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        view.setDateView(dayOfMonth, model.getMonthString(month), year)

        /** MonthView is always the default, so get CURRENT MONTH'S events **/
        model.getCalendars(object: Callback.GetCalendarsCallback {
            override fun onGetCalendars(calendars: MutableList<AppCalendar>) {
                model.getEvents(1, month, year, model.getDaysInMonth(month, year), VisibleCalendarsPreference.get(calendars), object: Callback.GetEventsCallback {
                    override fun onGetEvents(days: MutableList<AppDay>) {
                        view.showDates(days)
                    }
                })
            }
        })
    }

    override fun onDayPress(day: AppDay, hour: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTodayButtonPress() {
        backstack.goTo(BackStackItem(MonthView::class.java))
    }

    override fun onEventPress(hours: Int, minutes: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
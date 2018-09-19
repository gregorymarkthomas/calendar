package com.gregorymarkthomas.calendar.presenter

import com.gregorymarkthomas.calendar.model.*
import com.gregorymarkthomas.calendar.util.CalendarHelper
import com.gregorymarkthomas.calendar.util.interfaces.ContentResolverInterface
import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackItem
import com.gregorymarkthomas.calendar.view.CalendarViewInterface
import com.gregorymarkthomas.calendar.view.MonthView
import java.util.*


/**
 * There should be NO Android stuff in the Presenter.
 * TODO() - How do we deal with timezones of Events?
 * TODO() - make date = Date() if not set, instead of null
 */
class CalendarPresenter(view: CalendarViewInterface, private val backstack: BackStackInterface,
                        resolver: ContentResolverInterface,
                        preferences: GetSharedPreferencesInterface,
                        date: Date): CalendarPresenterInterface {
    private var model: ModelInterface = Model(resolver, preferences)

    /** Get the day of month, month and year that has been specified.
     * Show Today's date if no date was supplied. **/
    init {
        /** TODO - use CalendarHelper. Add a test to check 32nd December 2018 actually shows 1st January 2019 **/
        val calendar = CalendarHelper.getNewCalendar()
        calendar.time = date
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        view.setDateView(dayOfMonth, model.getMonthString(month), year)

        /** MonthView is always the default, so get CURRENT MONTH'S events **/
        model.getEvents(1, month, year, model.getDaysInMonth(month, year), object: Callback.GetEventsCallback {
            override fun onGetEvents(days: List<AppDay>) {
                view.showDates(days)
            }
        })
    }

    override fun onDayPress(day: AppDay, hour: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTodayButtonPress() {
        backstack.goTo(BackStackItem(MonthView::class.java, Date()))
    }

    override fun onEventPress(hours: Int, minutes: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
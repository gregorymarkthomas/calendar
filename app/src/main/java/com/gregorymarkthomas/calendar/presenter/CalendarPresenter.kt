package com.gregorymarkthomas.calendar.presenter

import com.gregorymarkthomas.calendar.model.Callback
import com.gregorymarkthomas.calendar.model.Model
import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.util.Day
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackItem
import com.gregorymarkthomas.calendar.view.CalendarViewInterface
import com.gregorymarkthomas.calendar.view.MonthView
import java.util.*


/**
 * NOTE: if we want to change the screen from a presenter, but we want to supply arguments, you do it like this:
 *    var args = Bundle()
 *    args.putSerializable(MonthView.DATE_ARG, Date())
 *    backstack.goTo(BackStackItem(MonthView::class.java, args))
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
        model.getEvents(1, month, year, model.getDaysInMonth(month, year), object: Callback.GetEventsCallback {
            override fun onGetEvents(days: MutableList<Day>) {
                view.showDates(days)
            }
        })
    }

    override fun onDayPress(day: Day, hour: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTodayButtonPress() {
        backstack.goTo(BackStackItem(MonthView::class.java))
    }

    override fun onEventPress(hours: Int, minutes: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
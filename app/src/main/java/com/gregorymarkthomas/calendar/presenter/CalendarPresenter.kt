package com.gregorymarkthomas.calendar.presenter

import android.os.Bundle
import com.gregorymarkthomas.calendar.model.Model
import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackItem
import com.gregorymarkthomas.calendar.view.CalendarViewInterface
import com.gregorymarkthomas.calendar.view.MonthView
import java.text.DateFormatSymbols;
import java.util.*


/**
 * NOTE: if we want to change the screen from a presenter, but we want to supply arguments, you do it like this:
 *    var args = Bundle()
 *    args.putSerializable(MonthView.DATE_ARG, Date())
 *    backstack.goTo(BackStackItem(MonthView::class.java, args))
 */

class CalendarPresenter(private var view: CalendarViewInterface, private var backstack: BackStackInterface): CalendarPresenterInterface {

    private var model: ModelInterface = Model()

    override fun onViewCreated(args: Bundle) {
        val date = args.get(MonthView.DATE_ARG) as Date? ?: Date()

        val calendar = Calendar.getInstance()
        calendar.time = date
        view.setDateView(calendar.get(Calendar.DAY_OF_MONTH), getMonthString(calendar.get(Calendar.MONTH)), calendar.get(Calendar.YEAR))
    }

    override fun onDayPress(dayOfMonth: Int, monthOfYear: Int, year: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTodayButtonPress() {
        backstack.goTo(BackStackItem(MonthView::class.java))
    }

    override fun onEventPress(hours: Int, minutes: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getMonthString(month: Int) = DateFormatSymbols().months[month-1]
}
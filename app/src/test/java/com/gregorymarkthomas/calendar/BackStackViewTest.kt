package com.gregorymarkthomas.calendar

import android.os.Build
import com.gregorymarkthomas.calendar.presenter.contracts.ActivityInterface
import com.gregorymarkthomas.calendar.util.CalendarHelper
import com.gregorymarkthomas.calendar.view.BackStackView
import com.gregorymarkthomas.calendar.view.MonthView
import io.mockk.mockk
import kotlinx.android.synthetic.main.month_view.view.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*
import kotlin.test.assertEquals

/**
 * This is a BackStack-View hybrid test suite.
 * This (tries to) uses jUnit5 + RoboElectric
 * I have this to test that input args for re-used views apply
 *
 * This is considered an INTEGRATION test suite - it utilises the Activity, therefore forcing the 'real' Model
 * TODO() - Crash in Model
 * We want to shy away from Instrumented tests, if possible.
 * This is because we can effectively test each View/PresenterInterface anyway.
 * But the test below is an example that it works.
 *
 * Roboelectric allows us to mock the Android code for use in JUnit tests.
 */
@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P) // Value of Build.VERSION_CODES.P is 28
class BackStackViewTest {

    /**
     * 'relaxUnitFun = true' will stop 'io.mockk.MockKException: no answer found for...' error for our simple use of a mocked BackStackCallback
     */
    lateinit var view: BackStackView
    lateinit var activity: MainActivity
    private val permissionsChecker: ActivityInterface.PermissionChecker = mockk(relaxUnitFun = true)
    private val dialogViewer: ActivityInterface.DialogViewer = mockk(relaxUnitFun = true)

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(MainActivity::class.java)

        /** Test on MonthView. BackStack has already been created with a 'today' date here **/
        view = activity.getMostRecentView() as MonthView
    }

    @Test
    fun reused_view_with_new_input_data_has_new_data() {

        /** Check view has today in its inner view - 'old' is the same default date for the View **/
        val old = Date()
        val calendar = CalendarHelper.getNewCalendar()
        calendar.time = old
        val oldDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val oldMonthOfYear = CalendarHelper.getMonthString(calendar.get(Calendar.MONTH))
        val oldYear = calendar.get(Calendar.YEAR)
        assertEquals((view as MonthView).view!!.dateTextView.text, "$oldDayOfMonth $oldMonthOfYear $oldYear")

        /** Create old date **/
        val new = Date(0)

        /** Re-use MonthView with a new date **/
        activity.goTo(MonthView(new, permissionsChecker, dialogViewer))

        /** Check view has the NEW date **/
        calendar.time = new
        val newDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val newMonthOfYear = CalendarHelper.getMonthString(calendar.get(Calendar.MONTH))
        val newYear = calendar.get(Calendar.YEAR)
        assertEquals((view as MonthView).view!!.dateTextView.text, "$newDayOfMonth $newMonthOfYear $newYear")
    }
}
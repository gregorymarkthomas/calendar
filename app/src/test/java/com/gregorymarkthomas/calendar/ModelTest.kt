package com.gregorymarkthomas.calendar

import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.util.backstack.BackStackItem
import com.gregorymarkthomas.calendar.util.interfaces.SharedPreferencesInterface
import com.gregorymarkthomas.calendar.view.MonthView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.util.*

/**
 * TODO
 * We want to test the responses of the functions we have in the Model
 */
class ModelTest {

    /** Ensure all mocks will be properly initialized before each test */
    @JvmField @Rule
    var mockitoRule = MockitoJUnit.rule()

    /** The objects that this class we are testing relies on require mocking **/

    @Before
    fun setUp() {
        // Do nothing
    }

    // TODO - edit from here onwards
    
}
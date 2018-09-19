package com.gregorymarkthomas.calendar

import com.gregorymarkthomas.calendar.util.interfaces.SharedPreferencesInterface
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit


class VisibleCalendarsPreferenceTest {

    /** Ensure all mocks will be properly initialized before each test */
    @JvmField @Rule
    var mockitoRule = MockitoJUnit.rule()

    /** The objects that this class we are testing relies on require mocking **/
    @Mock
    private lateinit var preferences: SharedPreferencesInterface

    @Before
    fun setUp() {
        // Do nothing
    }

    // TODO - edit from here onwards

}
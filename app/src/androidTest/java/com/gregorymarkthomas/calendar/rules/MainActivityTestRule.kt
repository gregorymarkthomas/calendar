package com.gregorymarkthomas.calendar.rules

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import android.util.Log
import com.gregorymarkthomas.backstack.view.BackStackView
import com.gregorymarkthomas.backstack.view.BackstackActivity
import com.gregorymarkthomas.calendar.MainActivity
import com.gregorymarkthomas.calendar.model.repository.AccountRepository
import com.gregorymarkthomas.calendar.model.repository.SharedPrefsBackendRepository

class MainActivityTestRule(var viewClass: Class<out BackStackView>?): ActivityTestRule<MainActivity>(MainActivity::class.java, true, true) {
    val TAG = "MainActivityTestRule"

    var context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    internal lateinit var view: BackStackView

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()

        // Maybe prepare some mock service calls
        // Maybe override some dependency injection modules with mocks
        Log.i(TAG, "beforeActivityLaunched()")

        /** Ensure this matches what is done in MainActivity/AccountRepository */
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit().putString("account_key", "test@test.com").apply()
    }

    override fun getActivityIntent(): Intent? {
        var customIntent = Intent(context, MainActivity::class.java)
        customIntent.putExtra(BackstackActivity.INITIAL_VIEW_EXTRA, viewClass)
        return customIntent
    }

    override fun afterActivityLaunched() {
        super.afterActivityLaunched()

        // maybe you want to do something here
        Log.i(TAG, "afterActivityLaunched()")
        view = activity.getMostRecentView()
    }

    override fun afterActivityFinished() {
        super.afterActivityFinished()
        Log.i(TAG, "afterActivityFinished()")
    }
}
package com.gregorymarkthomas.calendar.rules

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.util.Log
import com.gregorymarkthomas.calendar.MainActivity
import com.gregorymarkthomas.calendar.view.LifeCycleView

class MainActivityTestRule(var viewClass: Class<out LifeCycleView>?): ActivityTestRule<MainActivity>(MainActivity::class.java, true, true) {
    val TAG = "MainActivityTestRule"

    var context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    internal lateinit var view: LifeCycleView

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()

        // Maybe prepare some mock service calls
        // Maybe override some dependency injection modules with mocks
        Log.i(TAG, "beforeActivityLaunched()")
    }

    override fun getActivityIntent(): Intent? {
        var customIntent = Intent(context, MainActivity::class.java)
        customIntent.putExtra(MainActivity.INITIAL_VIEW_EXTRA, viewClass)
        return customIntent
    }

    override fun afterActivityLaunched() {
        super.afterActivityLaunched()

        // maybe you want to do something here
        Log.i(TAG, "afterActivityLaunched()")
        view = activity.getCurrentView()
    }

    override fun afterActivityFinished() {
        super.afterActivityFinished()
        Log.i(TAG, "afterActivityFinished()")
    }
}
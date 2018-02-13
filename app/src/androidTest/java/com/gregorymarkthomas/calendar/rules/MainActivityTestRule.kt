package com.gregorymarkthomas.calendar.rules

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.util.Log
import com.gregorymarkthomas.calendar.MainActivity

class MainActivityTestRule: ActivityTestRule<MainActivity>(MainActivity::class.java, true, true) {
    val TAG = "MainActivityTestRule"

    var context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched();

        // Maybe prepare some mock service calls
        // Maybe override some dependency injection modules with mocks
        Log.i(TAG, "beforeActivityLaunched()")
    }

    override fun getActivityIntent(): Intent? {
        var customIntent = Intent(context, MainActivity::class.java)
        return customIntent
    }

    override fun afterActivityLaunched() {
        super.afterActivityLaunched()

        // maybe you want to do something here
        Log.i(TAG, "afterActivityLaunched()")
    }

    override fun afterActivityFinished() {
        super.afterActivityFinished()
        Log.i(TAG, "afterActivityFinished()")
    }

}
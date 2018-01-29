package com.gregorymarkthomas.calendar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.gregorymarkthomas.calendar.util.backstack.BackStack
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.view.MonthView

class MainActivity: AppCompatActivity(), BackStackCallback {
    private var backstack: BackStackInterface = BackStack.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        backstack.goTo(MonthView(this))
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        backstack.goBack()
    }

    override fun onViewChanged(view: View) {
        setContentView(view)
    }
}
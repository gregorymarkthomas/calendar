package com.gregorymarkthomas.calendar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gregorymarkthomas.calendar.util.LifeCycleView
import com.gregorymarkthomas.calendar.util.backstack.BackStack2
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.view.MonthView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), BackStackCallback {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        BackStack2.setCallback(this)
        BackStack2.goTo(MonthView(this))
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        BackStack2.goBack()
    }

    override fun onViewChanged(view: LifeCycleView) {
        main_content.removeAllViews()
        main_content.addView(view)
    }
}
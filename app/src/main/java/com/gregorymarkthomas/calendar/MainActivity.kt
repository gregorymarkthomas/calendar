package com.gregorymarkthomas.calendar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gregorymarkthomas.calendar.util.LifeCycleView
import com.gregorymarkthomas.calendar.util.backstack.BackStack2
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackItem
import com.gregorymarkthomas.calendar.view.MonthView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity: AppCompatActivity(), BackStackInterface, BackStackCallback {

    private val TAG = "MainActivity"
    private val backstack = BackStack2(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        backstack.goTo(BackStackItem(MonthView::class.java))
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

    override fun goTo(item: BackStackItem) {
        backstack.goTo(item)
    }

    override fun goBack(): Boolean {
        return backstack.goBack()
    }

    override fun getView(): BackStackItem {
        return backstack.getView()
    }

    override fun onViewChanged(item: BackStackItem) {
        main_content.removeAllViews()
        main_content.addView(item.instantiateView(this))
    }
}
package com.gregorymarkthomas.calendar

import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.gregorymarkthomas.calendar.util.interfaces.ContentResolverInterface
import com.gregorymarkthomas.calendar.util.LifeCycleView
import com.gregorymarkthomas.calendar.util.interfaces.SharedPreferencesInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStack2
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackItem
import com.gregorymarkthomas.calendar.util.interfaces.LayoutContextInterface
import com.gregorymarkthomas.calendar.view.MonthView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), BackStackInterface, BackStackCallback,
        ContentResolverInterface, SharedPreferencesInterface, LayoutContextInterface {
    override fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    private val TAG = "MainActivity"

    companion object {

        val INITIAL_VIEW_EXTRA = "initial_view_extra"
    }
    private val backstack = BackStack2(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        goTo(BackStackItem(getInitialView()))
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

    override fun getCurrentView(): LifeCycleView {
        return backstack.getCurrentView()
    }

    override fun onViewChanged(item: BackStackItem) {
        main_content.removeAllViews()
        main_content.addView(item.instantiateView(this, this, this))
    }

    override fun getResolver(): ContentResolver {
        return contentResolver
    }

    override fun getContext(): Context {
        return this
    }

    private fun getInitialView(): Class<out LifeCycleView> {
        var initialViewClass: Class<out LifeCycleView>? = intent.getSerializableExtra(INITIAL_VIEW_EXTRA) as Class<out LifeCycleView>?
        if(initialViewClass == null)
            initialViewClass = MonthView::class.java
        return initialViewClass
    }
}
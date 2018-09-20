package com.gregorymarkthomas.calendar

import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.gregorymarkthomas.calendar.util.backstack.BackStack
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.interfaces.ContentResolverInterface
import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface
import com.gregorymarkthomas.calendar.util.interfaces.LayoutContextInterface
import com.gregorymarkthomas.calendar.view.LifeCycleView
import com.gregorymarkthomas.calendar.view.MonthView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity: AppCompatActivity(), BackStackInterface, BackStackCallback,
        ContentResolverInterface, GetSharedPreferencesInterface, LayoutContextInterface {

    private val TAG = "MainActivity"
    private val backstack = BackStack(this, getInitialView(), Date(), this, this, this, this)

    companion object {
        val INITIAL_VIEW_EXTRA = "initial_view_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** TODO() - this may crash - backstack is trying to set the initial view BEFORE setContentView() **/
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

    override fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun getResolver(): ContentResolver {
        return contentResolver
    }

    override fun getContext(): Context {
        return this
    }

    /********* BackStack - these can be called by Presenters via an interface */
    override fun goTo(viewClass: Class<out LifeCycleView>, selectedDate: Date) {
        backstack.goTo(viewClass, selectedDate)
    }

    override fun goBack(): Boolean {
        return backstack.goBack()
    }

    override fun getCurrentView(): LifeCycleView {
        return backstack.getCurrentView()
    }

    /**
     * Callback from BackStack - when the view has changed, this Activity sets the view.
     */
    override fun onViewChanged(view: LifeCycleView) {
        main_content.removeAllViews()
        main_content.addView(view)
    }

    /********** private */
    private fun getInitialView(): Class<out LifeCycleView> {
        var initialViewClass: Class<out LifeCycleView>? = intent.getSerializableExtra(INITIAL_VIEW_EXTRA) as Class<out LifeCycleView>?
        if(initialViewClass == null)
            initialViewClass = MonthView::class.java
        return initialViewClass
    }
}
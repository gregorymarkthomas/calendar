package com.gregorymarkthomas.calendar

import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.gregorymarkthomas.calendar.model.Model
import com.gregorymarkthomas.calendar.util.backstack.BackStack
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.interfaces.ContentResolverInterface
import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface
import com.gregorymarkthomas.calendar.util.interfaces.AndroidContextInterface
import com.gregorymarkthomas.calendar.view.DayView
import com.gregorymarkthomas.calendar.view.LifeCycleView
import com.gregorymarkthomas.calendar.view.MonthView
import com.gregorymarkthomas.calendar.view.WeekView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity: AppCompatActivity(), BackStackInterface, BackStackCallback,
        ContentResolverInterface, GetSharedPreferencesInterface, AndroidContextInterface {

    private val TAG = "MainActivity"
    private lateinit var backstack: BackStack

    companion object {
        val INITIAL_VIEW_EXTRA = "initial_view_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_content.viewTreeObserver.addOnGlobalLayoutListener {
            if(!::backstack.isInitialized)
                backstack = BackStack(this@MainActivity, getInitialView())
        }
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
    override fun goTo(view: LifeCycleView) {
        backstack.goTo(view)
    }

    override fun goBack(): Boolean {
        return backstack.goBack()
    }

    override fun getMostRecentView(): LifeCycleView {
        return backstack.getMostRecentView()
    }

    override fun getCurrentViewClasses(): List<String> {
        return backstack.getCurrentViewClasses()
    }

    /**
     * Callback from BackStack - when the view has changed, this Activity sets the view.
     * TODO() - do we need 'main_content.removeOnAttachStateChangeListener()' here?
     */
    override fun onViewChanged(view: LifeCycleView) {
        main_content.removeAllViews()
        main_content.addView(view.initialise(this), main_content.width, main_content.height)

        /** TODO() - this does not work. Back to the drawing board? **/
        view.view!!.addOnAttachStateChangeListener(object: View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View?) {
                view.onInitialised(
                        this@MainActivity,
                        Model(this@MainActivity, this@MainActivity),
                        this@MainActivity,
                        main_content.width,
                        main_content.height
                )
            }

            override fun onViewDetachedFromWindow(v: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    /********** private */
    private fun getInitialView(): LifeCycleView {
        val viewClass: Class<out LifeCycleView>? = intent.getSerializableExtra(INITIAL_VIEW_EXTRA) as Class<out LifeCycleView>?
        val today = Date()
        return when (viewClass) {
            DayView::class.java -> DayView(today)
            WeekView::class.java -> WeekView(today)
            else -> MonthView(today)
        }
    }
}
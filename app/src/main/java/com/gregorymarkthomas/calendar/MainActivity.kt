package com.gregorymarkthomas.calendar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.gregorymarkthomas.calendar.util.backstack.BackStack
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.view.MonthView

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), BackStackCallback {
    private var backstack: BackStackInterface = BackStack.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        backstack.goTo(MonthView.newInstance(this, main_content))
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
//        main_content.removeViewAt(0)

        main_content.setOnHierarchyChangeListener(object: ViewGroup.OnHierarchyChangeListener {
            override fun onChildViewRemoved(parent: View?, child: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildViewAdded(parent: View?, child: View?) {
                child.onInflated
            }

        })
        main_content.addView(view)
    }

}
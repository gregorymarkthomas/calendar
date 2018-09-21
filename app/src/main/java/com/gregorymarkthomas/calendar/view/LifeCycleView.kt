package com.gregorymarkthomas.calendar.view

import android.support.constraint.ConstraintLayout
import android.view.ViewGroup
import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.interfaces.AndroidContextInterface
import java.lang.NullPointerException


abstract class LifeCycleView {

    /********** public */
    var view: ConstraintLayout? = null
        get() {
            return field ?: throw NullPointerException("'view' object has not yet been drawn. Use initialise() on the LifeCycleView.")
        }

    /**
     * TODO() - edit this description as function has changed since written
     * The <merge> tag has been used in each view that uses the LifeCycleView.
     * This is because LifeCycleView already subclasses ConstraintLayout.
     *      If month_view.xml also has a ConstraintLayout as the root, then there will be too many ViewGroups.
     *      We now use <merge> to use the higher level ViewGroup.
     */
    fun initialise(backstack: BackStackInterface, model: ModelInterface, context: AndroidContextInterface): ConstraintLayout {
        this.view = createLayout(context)
        onInitialise(backstack, model, context)
        return this.view!!
    }

    /********** protected */
    protected abstract fun getTag(): String
    protected abstract fun getLayout(): Int
    protected abstract fun onInitialise(backstack: BackStackInterface, model: ModelInterface, context: AndroidContextInterface)

    /********** private */
    private fun createLayout(context: AndroidContextInterface): ConstraintLayout {
        val layout = ConstraintLayout(context.getContext())
        ConstraintLayout.inflate(context.getContext(), getLayout(), layout)

        /** Expand 'this' ConstraintLayout to use all of the usable space. **/
        layout.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.tag = getTag()
        return layout
    }
}
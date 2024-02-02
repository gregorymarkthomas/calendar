package com.gregorymarkthomas.calendar.view

import com.gregorymarkthomas.backstack.interfaces.AndroidContextInterface
import com.gregorymarkthomas.backstack.interfaces.BackStackInterface
import com.gregorymarkthomas.backstack.interfaces.ModelInterface
import com.gregorymarkthomas.backstack.view.BackStackView
import com.gregorymarkthomas.calendar.R

class PermissionDeniedView(): BackStackView() {

    /********** public */
    override fun getTag(): String = "MessageView"

    override fun getLayout(): Int = R.layout.permission_denied_view

    override fun onViewInitialised(backstack: BackStackInterface, model: ModelInterface, context: AndroidContextInterface) {}
}
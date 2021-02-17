package com.gregorymarkthomas.calendar.view

import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.interfaces.AndroidContextInterface

class PermissionDeniedView(): BackStackView() {

    /********** public */
    override fun getTag(): String = "MessageView"

    override fun getLayout(): Int = R.layout.permission_denied_view

    override fun onViewInitialised(backstack: BackStackInterface, model: ModelInterface, context: AndroidContextInterface) {}
}
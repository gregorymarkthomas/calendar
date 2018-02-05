package com.gregorymarkthomas.calendar.util.backstack

class BackStack2(private var callback: BackStackCallback): BackStackInterface {

    private var stack: ArrayList<BackStackItem> = ArrayList()

    fun indexOf(item: BackStackItem): Int {
        return stack.indexOfLast { it.klass == item.klass }
    }

    override fun goTo(item: BackStackItem) {
        val index = indexOf(item)
        if(index == -1) {
            stack.add(item)
        } else {
            stack.subList(index, stack.size - 1).clear()

            /** Refresh the input parameters of the view we are 're-using' **/
            getView().args = item.args
        }

        callback.onViewChanged(item)
    }

    override fun goBack(): Boolean {
        var success: Boolean
        try {
            stack.removeAt(stack.size - 1)
            success = true
        } catch (e: IndexOutOfBoundsException) {
            success = false
        }

        callback.onViewChanged(getView())

        return success
    }

    override fun getView(): BackStackItem {
        return stack.get(stack.size - 1)
    }

}
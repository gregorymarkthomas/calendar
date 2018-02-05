package com.gregorymarkthomas.calendar.util.backstack

class BackStack2(private var callback: BackStackCallback): BackStackInterface {

    private var stack: ArrayList<BackStackItem> = ArrayList()

    /** TODO() - .contains() needs to compare the klass with the other klasses in the stack **/
    override fun goTo(item: BackStackItem) {
        if(stack.contains(item)) {
            stack.subList(stack.indexOf(item), stack.size - 1).clear()
        } else {
            stack.add(item)
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
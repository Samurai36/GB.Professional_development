package viktor.khlebnikov.gb.utils

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewByIDDelegate<out T: View>(
    private val rootGetter: () -> View?,
    private val viewID: Int
) : ReadOnlyProperty<Any?, T> {

    private var rootRef: WeakReference<View>? = null
    private var viewRef: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        var view = viewRef
        val cacheRoot = rootRef?.get()
        val currentRoot = rootGetter()

        if(currentRoot != cacheRoot || view == null) {
            if (currentRoot == null) {
                if (view != null) {
                    return view
                }
                throw IllegalStateException("Cannot get View, there is no root yet")
            }
            view = currentRoot.findViewById(viewID)
            viewRef = view
            rootRef = WeakReference(currentRoot)
        }
        checkNotNull(view) {
            "View with id \"$viewID\" not found in root"
        }
        return view
    }
}

fun <T: View> Activity.viewByID(@IdRes viewId: Int): ViewByIDDelegate<T> {
    return ViewByIDDelegate({ window.decorView.findViewById(android.R.id.content) }, viewId)
}
fun <T: View> Fragment.viewByID(@IdRes viewId: Int): ViewByIDDelegate<T> {
    return ViewByIDDelegate({ view }, viewId)
}
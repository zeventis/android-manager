package br.com.zeventis.producer.core.utils.extensions

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <V : View> Fragment.bindView(id: Int): ReadOnlyProperty<Fragment, V> = required(id, viewFinder)

private val Fragment.viewFinder: Fragment.(Int) -> View?
    get() = { view?.findViewById(it) }

fun <V : View> Activity.bindView(id: Int): ReadOnlyProperty<Fragment, V> = required(id, viewFinder)

private val Activity.viewFinder: Fragment.(Int) -> View?
    get() = { view?.findViewById(it) }

fun <V : View> RecyclerView.ViewHolder.bindView(id: Int): ReadOnlyProperty<Fragment, V> =
    required(id, viewFinder)

private val RecyclerView.ViewHolder.viewFinder: Fragment.(Int) -> View?
    get() = { view?.findViewById(it) }

@Suppress("UNCHECKED_CAST")
private fun <T, V : View> required(id: Int, finder: T.(Int) -> View?) = Lazy { t: T, desc ->
    t.finder(id) as V? ?: viewNotFound(id, desc)
}

@Suppress("UNCHECKED_CAST")
class Lazy<T, V>(private val initializer: (T, KProperty<*>) -> V) : ReadOnlyProperty<T, V>,
    LifecycleObserver {

    private object EMPTY

    private var value: Any? = EMPTY
    private var attachedToLifecycleOwner = false

    override fun getValue(thisRef: T, property: KProperty<*>): V {
        checkAddToLifecycleOwner(thisRef)
        if (value == EMPTY) {
            value = initializer(thisRef, property)
        }

        return value as V
    }

    private fun checkAddToLifecycleOwner(thisRef: T) {
        if (!attachedToLifecycleOwner && thisRef is LifecycleOwner) {
            thisRef.lifecycle.addObserver(this)
            attachedToLifecycleOwner = true
        }
    }

}

private fun viewNotFound(id: Int, desc: KProperty<*>): Nothing =
    throw IllegalStateException("View ID $id for '${desc.name}' not found")

fun View.onClick(listenerFunctions: () -> Unit) {
    this.setOnClickListener { listenerFunctions.invoke() }
}
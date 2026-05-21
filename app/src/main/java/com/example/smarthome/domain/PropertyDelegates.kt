package com.example.smarthome.domain

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class RangeDelegate(
    private val min: Int,
    private val max: Int,
    initialValue: Int
) {
    private var value = initialValue.coerceIn(min, max)
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: Int) {
        value = newValue.coerceIn(min, max)
    }
}

class ObservableLogDelegate<T>(
    initialValue: T,
    private val onChanged: (old: T, new: T) -> Unit
) {
    private var value: T by Delegates.observable(initialValue) { _, old, new ->
        if (old != new) {
            onChanged(old, new)
        }
    }

    operator fun getValue(thisRef: Any?, propery: KProperty<*>): T {
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: T) {
        value = newValue
    }
}
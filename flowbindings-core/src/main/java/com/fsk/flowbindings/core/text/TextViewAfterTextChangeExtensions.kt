package com.fsk.flowbindings.core.text

import android.text.Editable
import android.widget.TextView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


/**
 * Create a flow for the after text changed event.
 *
 * @return a flow for the after text changed event.
 */
@ExperimentalCoroutinesApi
fun TextView.afterTextChanges(): Flow<AfterTextChangedEvent> = callbackFlow {
    val watcher = AfterTextWatcher(
        this@afterTextChanges,
        this
    )

    this@afterTextChanges.addTextChangedListener(watcher)
    awaitClose { this@afterTextChanges.removeTextChangedListener(watcher) }
}

/**
 * Event for the after text changed
 *
 * @property textView the view that changed
 * @property editable the after text value
 */
data class AfterTextChangedEvent(
    val textView: TextView,
    val editable: Editable?
)

@ExperimentalCoroutinesApi
private class AfterTextWatcher constructor(
    val view: TextView,
    val producerScope: ProducerScope<AfterTextChangedEvent>
) : SimpleTextWatcher() {

    override fun afterTextChanged(s: Editable?) {
        producerScope.offer(AfterTextChangedEvent(view, s))
    }
}

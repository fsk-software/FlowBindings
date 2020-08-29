package com.fsk.flowbindings.core

import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Create a flow for the view focus change event
 *
 * @return a flow for the view focus change event
 */
@ExperimentalCoroutinesApi
fun View.focusChanged(): Flow<OnFocusChangeEvent> = callbackFlow {
    this@focusChanged.setOnFocusChangeListener { view, b ->
        offer(OnFocusChangeEvent(view, b))
    }

    awaitClose { this@focusChanged.onFocusChangeListener = null }
}

/**
 * Event for the focus change event.
 *
 * @property view the view whose focus changed
 * @property isFocused true if the view is focused
 */
data class OnFocusChangeEvent(
    val view: View,
    val isFocused: Boolean
)

package fsk.com.flowbindings.core

import android.view.KeyEvent
import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Create a flow for the on Key Pressed Event
 *
 * @return a flow for the on Key Pressed Event
 */
@ExperimentalCoroutinesApi
fun View.keyPresses(): Flow<OnKeyEvent> = callbackFlow {
    this@keyPresses.setOnKeyListener { view, keyCode, keyEvent ->
        offer(OnKeyEvent(view, keyCode, keyEvent))
    }

    awaitClose { this@keyPresses.setOnKeyListener(null) }
}

/**
 * Event for the key press.
 *
 * @property view the view associated with the key press.
 * @property keyCode the code of the pressed key
 * @property keyEvent the key event
 */
data class OnKeyEvent(
    val view: View,
    val keyCode: Int,
    val keyEvent: KeyEvent
)

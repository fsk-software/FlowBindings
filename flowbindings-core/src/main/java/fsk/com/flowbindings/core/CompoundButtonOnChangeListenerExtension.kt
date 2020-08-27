package fsk.com.flowbindings.core

import android.widget.CompoundButton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Create a flow for check changed events.
 *
 * @return a flow for check changed events.
 */
@ExperimentalCoroutinesApi
fun CompoundButton.onCheckedChanged(): Flow<OnCheckedChangedEvent> = callbackFlow {

    this@onCheckedChanged.setOnCheckedChangeListener { compoundButton, b ->
        offer(OnCheckedChangedEvent(compoundButton, b))
    }

    awaitClose { this@onCheckedChanged.setOnCheckedChangeListener(null) }
}


/**
 * Event for the check changed
 *
 * @property button the button triggering the event
 * @property isChecked true if the button is checked.
 */
data class OnCheckedChangedEvent(
    val button: CompoundButton,
    val isChecked: Boolean
)
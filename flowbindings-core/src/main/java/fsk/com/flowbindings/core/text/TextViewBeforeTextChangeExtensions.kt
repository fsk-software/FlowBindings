package fsk.com.flowbindings.core.text

import android.widget.TextView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Create a flow for the before text changed event.
 *
 * @return a flow for the before text changed event.
 */
@ExperimentalCoroutinesApi
fun TextView.beforeTextChanges(): Flow<BeforeTextChangedEvent> = callbackFlow {
    val watcher = BeforeTextWatcher(
        this@beforeTextChanges,
        this
    )

    this@beforeTextChanges.addTextChangedListener(watcher)
    awaitClose { this@beforeTextChanges.removeTextChangedListener(watcher) }
}

/**
 * Event for the after text changed
 *
 * @property textView the view that changed
 * @property text the text value
 * @property start the start index of the change
 * @property count the number of characters changed
 * @property after the new text length
 */
data class BeforeTextChangedEvent(
    val textView: TextView,
    val text: CharSequence?,
    val start: Int,
    val count: Int,
    val after: Int
)

@ExperimentalCoroutinesApi
private class BeforeTextWatcher constructor(
    val view: TextView,
    val producerScope: ProducerScope<BeforeTextChangedEvent>
) : SimpleTextWatcher() {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        producerScope.offer(
            BeforeTextChangedEvent(
                view,
                s,
                start,
                count,
                after
            )
        )
    }
}

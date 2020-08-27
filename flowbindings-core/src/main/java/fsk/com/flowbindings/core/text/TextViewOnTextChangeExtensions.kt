package fsk.com.flowbindings.core.text

import android.widget.TextView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Create a flow for the on text changed event.
 *
 * @return a flow for the on text changed event.
 */
@ExperimentalCoroutinesApi
fun TextView.onTextChanged(): Flow<OnTextChangedEvent> = callbackFlow {
    val watcher =
        OnTextWatcher(this@onTextChanged, this)

    this@onTextChanged.addTextChangedListener(watcher)
    awaitClose { this@onTextChanged.removeTextChangedListener(watcher) }
}

/**
 * Event for the on text changed
 *
 * @property textView the view that changed
 * @property text the text value
 * @property start the start index of the change
 * @property count the number of characters changed
 * @property before the old text length
 */
data class OnTextChangedEvent(
    val textView: TextView,
    val text: CharSequence?,
    val start: Int,
    val before: Int,
    val count: Int
)

@ExperimentalCoroutinesApi
private class OnTextWatcher constructor(
    val view: TextView,
    val producerScope: ProducerScope<OnTextChangedEvent>
) : SimpleTextWatcher() {
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        producerScope.offer(
            OnTextChangedEvent(
                view,
                s,
                start,
                before,
                count
            )
        )
    }
}

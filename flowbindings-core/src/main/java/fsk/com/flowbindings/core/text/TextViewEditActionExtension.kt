package fsk.com.flowbindings.core.text

import android.view.KeyEvent
import android.widget.TextView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


/**
 * Create a flow for the editor action events
 *
 * @return a flow for the editor action events.
 */
@ExperimentalCoroutinesApi
fun TextView.editorEvents(callback: (Int) -> Boolean = { true }): Flow<EditorActionEvent> =
    callbackFlow {
        this@editorEvents.setOnEditorActionListener { textView, i, keyEvent ->
            if (callback(i)) {
                offer(EditorActionEvent(textView, i, keyEvent))
                true
            } else {
                false
            }
        }

        awaitClose { this@editorEvents.setOnEditorActionListener(null) }
    }

/**
 * Event for the editor action
 *
 * @property textView the view that changed
 * @property action the action performed
 * @property keyEvent the key event for the action.
 */
data class EditorActionEvent(
    val textView: TextView,
    val action: Int,
    val keyEvent: KeyEvent
)

package fsk.com.flowbindings.core

import android.view.View
import androidx.appcompat.widget.Toolbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Create a flow for the toolbar navigation item click event.
 *
 * @return a flow for the toolbar navigation item click event.
 */
@ExperimentalCoroutinesApi
fun Toolbar.navigationClicks(): Flow<View> = callbackFlow {

    this@navigationClicks.setNavigationOnClickListener {
        offer(it)
    }

    awaitClose { this@navigationClicks.setOnMenuItemClickListener(null) }
}

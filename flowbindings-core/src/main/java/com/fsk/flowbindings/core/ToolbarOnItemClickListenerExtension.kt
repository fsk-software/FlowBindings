package com.fsk.flowbindings.core

import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Create a flow for the on menu item click event.
 *
 * @return a flow for the on menu item click event.
 */
@ExperimentalCoroutinesApi
fun Toolbar.menuItemClicks(): Flow<MenuItem> = callbackFlow {

    this@menuItemClicks.setOnMenuItemClickListener {
        offer(it)
        true
    }

    awaitClose { this@menuItemClicks.setOnMenuItemClickListener(null) }
}

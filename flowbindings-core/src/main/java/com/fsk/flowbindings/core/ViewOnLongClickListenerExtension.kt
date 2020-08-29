package com.fsk.flowbindings.core

import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Create a flow for the on long press event
 *
 * @return a flow for the on long press event
 */
@ExperimentalCoroutinesApi
fun View.longClicks(): Flow<View> = callbackFlow {

    this@longClicks.setOnLongClickListener {
        offer(it)
        true
    }

    awaitClose { this@longClicks.setOnLongClickListener(null) }
}

package com.fsk.flowbindings.core

import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Create a flow for the view click event
 *
 * @return a flow for the view click event
 */
@ExperimentalCoroutinesApi
fun View.clicks(): Flow<View> = callbackFlow {

    this@clicks.setOnClickListener {
        offer(it)
    }

    awaitClose { this@clicks.setOnClickListener(null) }
}

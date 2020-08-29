package com.fsk.flowbindings.core

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
fun CompoundButton.onCheckedChanged(): Flow<Boolean> = callbackFlow {

    this@onCheckedChanged.setOnCheckedChangeListener { compoundButton, b ->
        offer(b)
    }

    awaitClose { this@onCheckedChanged.setOnCheckedChangeListener(null) }
}
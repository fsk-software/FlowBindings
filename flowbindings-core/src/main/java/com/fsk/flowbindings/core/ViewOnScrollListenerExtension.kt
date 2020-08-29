package com.fsk.flowbindings.core

import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Create a flow for the scroll change event
 *
 * @return a flow for the scroll change event
 */
@ExperimentalCoroutinesApi
fun View.scrolls(): Flow<OnScrollChangeEvent> = callbackFlow {

    this@scrolls.setOnScrollChangeListener { view, scrollX, scrollY, oldScrollX, oldScrollY ->
        offer(
            OnScrollChangeEvent(
                view = view,
                scrollX = scrollX,
                scrollY = scrollY,
                oldScrollX = oldScrollX,
                oldScrollY = oldScrollY
            )
        )
    }

    awaitClose { this@scrolls.setOnScrollChangeListener(null) }
}

/**
 * The scroll change event
 *
 * @property view the view associated with the scroll change
 * @property scrollX the new scroll position along the x-axis.
 * @property scrollY the new scroll position along the y-axis.
 * @property oldScrollX the old scroll position along the x-axis.
 * @property oldScrollY the old scroll position along the y-axis.
 */
data class OnScrollChangeEvent(
    val view: View,
    val scrollX: Int,
    val scrollY: Int,
    val oldScrollX: Int,
    val oldScrollY: Int
)
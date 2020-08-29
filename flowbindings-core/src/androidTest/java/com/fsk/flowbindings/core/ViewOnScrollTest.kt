package com.fsk.flowbindings.core

import android.content.Context
import android.view.View
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import dev.olog.flow.test.observer.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class ViewOnScrollTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val view = View(context)

    @ExperimentalCoroutinesApi
    @Test
    @UiThreadTest
    fun onScrollTest() {
        runBlockingTest {
            view.scrolls().test(this) {
                assertValues(
                    OnScrollChangeEvent(
                        view,
                        10,
                        15,
                        0,
                        0
                    ),
                    OnScrollChangeEvent(
                        view,
                        20,
                        30,
                        10,
                        15
                    )
                )
            }.invokeOnCompletion {
                //trigger the event one more time to verify that the listener is gone
                view.scrollBy(-20, -30)
            }

            view.scrollBy(10, 15)
            view.scrollBy(10, 15)
        }

    }


}
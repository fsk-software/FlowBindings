package com.fsk.flowbindings.core

import android.content.Context
import android.view.View
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import dev.olog.flow.test.observer.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class ViewOnLongClickTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val view = View(context)

    @ExperimentalCoroutinesApi
    @Test
    @UiThreadTest
    fun onLongClickTest() {
        runBlockingTest {
            view.longClicks().test(this) {
                assertValues(view)
            }.invokeOnCompletion {
                //trigger the event one more time to verify that the listener is gone

                //this is commented out because the source code wants to call through to context menu which is not supported by this test yet.
                //     view.performLongClick()
            }

            view.performLongClick()
        }

    }


}
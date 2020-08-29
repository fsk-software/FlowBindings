package com.fsk.flowbindings.core.text

import android.content.Context
import android.widget.TextView
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import dev.olog.flow.test.observer.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class TextViewBeforeTextChangeTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val view = TextView(context)

    @ExperimentalCoroutinesApi
    @Test
    @UiThreadTest
    fun onBeforeTextChange() {
        val text1 = "hello world"
        val text2 = "goodbye cruel world"

        runBlockingTest {
            view.beforeTextChanges().test(this) {
                assertValues(
                    BeforeTextChangedEvent(
                        view,
                        "",
                        0,
                        0,
                        11
                    )
                )
            }.invokeOnCompletion {
                //trigger the event one more time to verify that the listener is gone
                view.text = text2
            }

            view.text = text1
        }

    }


}
package fsk.com.flowbindings.core

import android.content.Context
import android.view.View
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import dev.olog.flow.test.observer.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class ViewOnClickTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val view = View(context)

    @ExperimentalCoroutinesApi
    @Test
    @UiThreadTest
    fun onClickTest() {
        runBlockingTest {
            view.clicks().test(this) {
                assertValues(view)
            }.invokeOnCompletion {
                //trigger the event one more time to verify that the listener is gone
                view.performClick()
            }

            view.performClick()
        }

    }


}
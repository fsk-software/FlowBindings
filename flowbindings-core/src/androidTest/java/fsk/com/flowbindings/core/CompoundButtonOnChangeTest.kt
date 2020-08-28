package fsk.com.flowbindings.core

import android.content.Context
import android.widget.ToggleButton
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import dev.olog.flow.test.observer.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class CompoundButtonOnChangeTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val view = ToggleButton(context)

    @ExperimentalCoroutinesApi
    @Test
    @UiThreadTest
    fun checkedChanges() {
        view.isChecked = false

        runBlockingTest {
            view.onCheckedChanged().test(this) {
                assertValues(true, false)
            }.invokeOnCompletion {
                //fire the checked listener one more time to verify the listener was released.
                view.isChecked = true
            }

            view.isChecked = true
            view.isChecked = false
        }

    }


}
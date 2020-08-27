package fsk.com.flowbindings.core.text

import android.content.Context
import android.text.Editable
import android.widget.TextView
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import dev.olog.flow.test.observer.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class TextViewAfterTextChangeTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val view = TextView(context)

    @ExperimentalCoroutinesApi
    @Test
    @UiThreadTest
    fun onAfterTextChange() {
        val editable1 = Editable.Factory.getInstance().newEditable("hello world")
        val editable2 = Editable.Factory.getInstance().newEditable("goodbye cruel world")

        runBlockingTest {
            view.afterTextChanged().test(this) {
                assertValue {
                    it.textView == view && it.editable?.toString() == editable1.toString()
                }
            }.invokeOnCompletion {
                //trigger the event one more time to verify that the listener is gone
                view.text = editable2
            }

            view.text = editable1
        }

    }


}
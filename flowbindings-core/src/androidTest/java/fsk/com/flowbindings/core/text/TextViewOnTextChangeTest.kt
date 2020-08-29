package fsk.com.flowbindings.core.text

import android.content.Context
import android.widget.TextView
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import dev.olog.flow.test.observer.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class TextViewOnTextChangeTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val view = TextView(context)

    @ExperimentalCoroutinesApi
    @Test
    @UiThreadTest
    fun onTextChange() {
        val text1 = "hello world"
        val text2 = "goodbye cruel world"

        runBlockingTest {
            view.onTextChanges().test(this) {
                assertValue {
                    it.textView == view &&
                            it.text?.toString() == text1 &&
                            it.start == 0 &&
                            it.before == 0 &&
                            it.count == text1.length
                }
            }.invokeOnCompletion {
                //trigger the event one more time to verify that the listener is gone
                view.text = text2
            }

            view.text = text1
        }

    }


}
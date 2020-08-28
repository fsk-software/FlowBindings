package fsk.com.flowbindings.core.text

import android.content.Context
import android.view.KeyEvent
import android.widget.TextView
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import dev.olog.flow.test.observer.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class TextViewEditorActionTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val view = TextView(context)

    @ExperimentalCoroutinesApi
    @Test
    @UiThreadTest
    fun editorTestSendsWhenCallbackTrue() {
        runBlockingTest {
            view.editorEvents().test(this) {
                assertValue {
                    it.textView == view && it.action == KeyEvent.ACTION_DOWN
                }
            }.invokeOnCompletion {
                //trigger the event one more time to verify that the listener is gone
                view.onKeyDown(
                    KeyEvent.KEYCODE_ENTER,
                    KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER)
                )
            }

            view.onKeyDown(
                KeyEvent.KEYCODE_ENTER,
                KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER)
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    @UiThreadTest
    fun editorTestNeverSendsWhenCallbackFalse() {
        runBlockingTest {
            view.editorEvents { false }.test(this) {
                assertNoValues()
            }.invokeOnCompletion {
                //trigger the event one more time to verify that the listener is gone
                view.onKeyDown(
                    KeyEvent.KEYCODE_ENTER,
                    KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER)
                )
            }

            view.onKeyDown(
                KeyEvent.KEYCODE_ENTER,
                KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER)
            )
        }

    }


}
package fsk.com.flowbindings.core.text

import android.text.Editable
import android.text.TextWatcher

/**
 * Simple TextWatcher that allows children to only override the methods they need.
 */
open class SimpleTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}
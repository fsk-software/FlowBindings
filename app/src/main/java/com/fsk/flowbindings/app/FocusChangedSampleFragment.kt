package com.fsk.flowbindings.app

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fsk.flowbindings.core.focusChanged
import kotlinx.android.synthetic.main.fragment_focus_changed_sample.inputEditText1
import kotlinx.android.synthetic.main.fragment_focus_changed_sample.inputEditText2
import kotlinx.android.synthetic.main.fragment_focus_changed_sample.inputLayout1
import kotlinx.android.synthetic.main.fragment_focus_changed_sample.inputLayout2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FocusChangedSampleFragment : Fragment(R.layout.fragment_focus_changed_sample) {

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputEditText1.focusChanged().onEach {
            inputLayout1.setBackgroundResource(if (it.isFocused) R.color.light_yellow else android.R.color.transparent)
        }.flowOn(Dispatchers.Main)
            .launchIn(lifecycleScope)

        inputEditText2.focusChanged().onEach {
            inputLayout2.setBackgroundResource(if (it.isFocused) R.color.light_yellow else android.R.color.transparent)
        }.flowOn(Dispatchers.Main)
            .launchIn(lifecycleScope)

    }
}
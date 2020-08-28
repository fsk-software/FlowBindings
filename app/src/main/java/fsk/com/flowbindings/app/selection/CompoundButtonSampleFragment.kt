package fsk.com.flowbindings.app.selection

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import fsk.com.flowbindings.app.R
import fsk.com.flowbindings.core.onCheckedChanged
import kotlinx.android.synthetic.main.fragment_compoundbutton_sample.toggleButton
import kotlinx.android.synthetic.main.fragment_toolbar_sample.image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CompoundButtonSampleFragment : Fragment(R.layout.fragment_compoundbutton_sample) {

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toggleButton.onCheckedChanged().onEach {
            image.isVisible = it
        }.flowOn(Dispatchers.Main)
            .launchIn(lifecycleScope)

    }
}
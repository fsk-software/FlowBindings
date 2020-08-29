package com.fsk.flowbindings.app

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fsk.flowbindings.core.scrolls
import kotlinx.android.synthetic.main.fragment_scrolling_sample.loremIpsumScroll
import kotlinx.android.synthetic.main.fragment_scrolling_sample.scrollEventText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ScrollingSampleFragment : Fragment(R.layout.fragment_scrolling_sample) {

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loremIpsumScroll.scrolls().onEach {
            scrollEventText.text = requireContext().getString(
                R.string.scroll_log_entry,
                it.oldScrollX,
                it.oldScrollY,
                it.scrollX,
                it.scrollY,
                if (scrollEventText.text.isNotEmpty()) "\n" else "",
                scrollEventText.text
            )
        }.flowOn(Dispatchers.Main)
            .launchIn(lifecycleScope)
    }
}
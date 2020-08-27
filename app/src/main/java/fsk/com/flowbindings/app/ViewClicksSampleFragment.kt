package fsk.com.flowbindings.app

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import fsk.com.flowbindings.core.clicks
import fsk.com.flowbindings.core.longClicks
import kotlinx.android.synthetic.main.fragment_view_clicks_sample.clickButton
import kotlinx.android.synthetic.main.fragment_view_clicks_sample.clicksCounts
import kotlinx.android.synthetic.main.fragment_view_clicks_sample.longClickButton
import kotlinx.android.synthetic.main.fragment_view_clicks_sample.longClickCounts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ViewClicksSampleFragment : Fragment(R.layout.fragment_view_clicks_sample) {

    private var buttonClickCounts = 0
    private var buttonLongClickCounts = 0

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickButton.clicks().onEach {
            clicksCounts.text = getString(R.string.button_click_counts, ++buttonClickCounts)
        }.flowOn(Dispatchers.Main)
            .launchIn(lifecycleScope)

        longClickButton.longClicks().onEach {
            longClickCounts.text =
                getString(R.string.button_long_click_counts, ++buttonLongClickCounts)
        }.flowOn(Dispatchers.Main)
            .launchIn(lifecycleScope)
    }
}
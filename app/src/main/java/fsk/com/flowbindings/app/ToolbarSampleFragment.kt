package fsk.com.flowbindings.app

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import fsk.com.flowbindings.core.menuItemClicks
import fsk.com.flowbindings.core.navigationClicks
import kotlinx.android.synthetic.main.fragment_toolbar_sample.image
import kotlinx.android.synthetic.main.fragment_toolbar_sample.toolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ToolbarSampleFragment : Fragment(R.layout.fragment_toolbar_sample) {

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.inflateMenu(R.menu.toolbar_menu)

        toolbar.apply {
            navigationClicks().onEach {
                image.setImageResource(R.drawable.ic_android)
            }.flowOn(Dispatchers.Main)
                .launchIn(lifecycleScope)

            menuItemClicks().onEach {
                if (it.itemId == R.id.clear) {
                    image.setImageResource(android.R.color.transparent)
                }
            }.flowOn(Dispatchers.Main)
                .launchIn(lifecycleScope)
        }
    }
}
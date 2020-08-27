package fsk.com.flowbindings.app.selection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fsk.com.flowbindings.app.R
import kotlinx.android.synthetic.main.fragment_sample_selection.recyclerview

class SampleSelectionFragment : Fragment(R.layout.fragment_sample_selection) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerview.apply {
            adapter = SampleTypeAdapter().apply {
                onItemClickListener = object : SampleTypeAdapter.OnItemClickListener {
                    override fun onItemClick(item: Sample) {
                        navigateToSample(item)
                    }
                }
            }

            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun navigateToSample(sample: Sample) {
        when (sample) {
            Sample.COMPOUND_BUTTON -> {
            }
            Sample.FOCUS_CHANGE ->
                findNavController().navigate(R.id.focusChangedSampleFragment)

            Sample.KEY_PRESS -> {
            }
            Sample.SCROLL_LISTENER ->
                findNavController().navigate(R.id.scrollingSampleFragment)

            Sample.TEXT_CHANGES -> {
            }
            Sample.TOOLBAR -> {
            }
            Sample.VIEW_CLICKS_AND_FOCUS_CHANGE ->
                findNavController().navigate(R.id.viewClicksSampleFragment)
        }
    }
}
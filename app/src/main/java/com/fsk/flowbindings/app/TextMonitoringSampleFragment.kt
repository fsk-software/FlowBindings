package com.fsk.flowbindings.app

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fsk.flowbindings.core.text.afterTextChanges
import com.fsk.flowbindings.core.text.beforeTextChanges
import com.fsk.flowbindings.core.text.editorActions
import com.fsk.flowbindings.core.text.onTextChanges
import kotlinx.android.synthetic.main.fragment_text_monitoring_sample.afterTextChangeLog
import kotlinx.android.synthetic.main.fragment_text_monitoring_sample.beforeTextChangeLog
import kotlinx.android.synthetic.main.fragment_text_monitoring_sample.editorActionLog
import kotlinx.android.synthetic.main.fragment_text_monitoring_sample.inputEditText
import kotlinx.android.synthetic.main.fragment_text_monitoring_sample.onTextChangeLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TextMonitoringSampleFragment : Fragment(R.layout.fragment_text_monitoring_sample) {

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputEditText.apply {
            afterTextChanges().onEach {
                afterTextChangeLog.text =
                    getString(R.string.after_text_change_log_enty, it.editable?.toString() ?: "")
            }.flowOn(Dispatchers.Main)
                .launchIn(lifecycleScope)

            beforeTextChanges().onEach {
                beforeTextChangeLog.text =
                    getString(
                        R.string.before_text_change_log_entry,
                        it.text?.toString() ?: "",
                        it.start,
                        it.count,
                        it.after
                    )
            }.flowOn(Dispatchers.Main)
                .launchIn(lifecycleScope)

            onTextChanges().onEach {
                onTextChangeLog.text =
                    getString(
                        R.string.on_text_change_log_entry,
                        it.text?.toString() ?: "",
                        it.start,
                        it.count,
                        it.before
                    )
            }.flowOn(Dispatchers.Main)
                .launchIn(lifecycleScope)

            editorActions().onEach {
                editorActionLog.text =
                    getString(R.string.editor_actions_log_entry, it.action, it.keyEvent.toString())

            }.flowOn(Dispatchers.Main)
                .launchIn(lifecycleScope)

        }
    }
}
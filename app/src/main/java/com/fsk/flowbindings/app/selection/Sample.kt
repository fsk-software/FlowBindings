package com.fsk.flowbindings.app.selection

import androidx.annotation.StringRes
import com.fsk.flowbindings.app.R

enum class Sample(
    @StringRes val titleStringResId: Int
) {
    VIEW_CLICKS_AND_FOCUS_CHANGE(R.string.view_clicks_title),
    FOCUS_CHANGE(R.string.focus_changed_title),
    SCROLL_LISTENER(R.string.scroll_listener_title),
    TOOLBAR(R.string.toolbar_title),
    COMPOUND_BUTTON(R.string.compound_button_title),
    TEXT_CHANGES(R.string.text_changes_title)
}
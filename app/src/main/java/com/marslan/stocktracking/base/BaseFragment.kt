package com.marslan.stocktracking.base

import android.text.Editable
import com.marslan.stocktracking.core.extension.toast
import com.marslan.stocktracking.ui.main.component.MainToolbar
import com.marslan.stocktracking.ui.main.view.MainActivity
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment(), MainToolbar.MainToolbarListener {
    override fun menuButton() {
        (activity as MainActivity).toggleMenu()
    }

    override fun addButton() {
        requireActivity().toast("coming soon")
    }

    override fun searchChange(search: Editable?) {
        requireActivity().toast("we will searching coming soon to $search")
    }
}
package com.marslan.stocktracking.base

import android.os.Bundle
import android.text.Editable
import android.view.View
import com.marslan.stocktracking.core.extension.toast
import com.marslan.stocktracking.core.helper.DataHelper
import com.marslan.stocktracking.core.helper.DataLiveHelper
import com.marslan.stocktracking.ui.main.component.MainToolbar
import com.marslan.stocktracking.ui.main.view.MainActivity
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment(), MainToolbar.MainToolbarListener, DataLiveHelper {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataHelper.onChangeListener = this
    }
    override fun menuButton() {
        (activity as MainActivity).toggleMenu()
    }

    override fun addButton() {
        requireActivity().toast("coming soon")
    }

    override fun searchChange(search: Editable?) {
        requireActivity().toast("we will searching coming soon to $search")
    }

    override fun onChangeCustomer() {}

    override fun onChangeProduct() {}
}
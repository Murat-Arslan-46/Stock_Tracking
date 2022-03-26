package com.marslan.stocktracking.base

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.marslan.stocktracking.core.extension.toast
import com.marslan.stocktracking.core.helper.DataHelper
import com.marslan.stocktracking.core.helper.DataLiveHelper
import com.marslan.stocktracking.ui.main.view.MainActivity
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment(), DataLiveHelper {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataHelper.onChangeListener = this
    }

    fun hideKeyboard(v: View){
        val manager = requireActivity().getSystemService(InputMethodManager::class.java) as InputMethodManager
        manager.hideSoftInputFromWindow(v.windowToken,0)
    }

}
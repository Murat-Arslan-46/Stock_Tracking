package com.marslan.stocktracking.ui.home.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.marslan.stocktracking.R
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutId = R.layout.fragment_home

    private lateinit var bottomSheet: BottomSheetBehavior<LinearLayout>

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheet = BottomSheetBehavior.from(binding.homeBottomSheet)
        bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
        binding.appHeader.postDelayed({
            bottomSheet.peekHeight = binding.root.height - binding.appHeader.height
            bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
        },200)
        bottomSheet.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    binding.homeToolbarBack.alpha = 1f
                    binding.homeTitle.alpha = 1f
                }
                else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    binding.homeToolbarBack.alpha = 0f
                    binding.homeTitle.alpha = 0f
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                hideKeyboard(binding.homeSearch)
                binding.homeSearch.clearFocus()
                binding.homeSearch.text = null
                binding.homeToolbarBack.alpha = slideOffset
                if (slideOffset > 0.8f)
                    binding.homeTitle.alpha = (slideOffset - 0.8f) * 5
                else
                    binding.homeTitle.alpha = 0f
            }
        })
    }
}
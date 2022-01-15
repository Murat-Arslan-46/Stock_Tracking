package com.marslan.stocktracking.base

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.marslan.stocktracking.R
import com.marslan.stocktracking.core.extension.scaleDownVertical
import com.marslan.stocktracking.core.extension.scaleUpVertical
import com.marslan.stocktracking.databinding.MainBottombarBinding

class MainBottomBar : ConstraintLayout {
    private var binding: MainBottombarBinding
    private var current: Int = 0
    private var tabs: List<AppCompatTextView>

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, style: Int) : super(context, attrs, style) {
        val inflater = LayoutInflater.from(context)
        binding = MainBottombarBinding.inflate(inflater, this, true)
        tabs = listOf(
            binding.bottomItemText1,
            binding.bottomItemText2,
            binding.bottomItemText3,
            binding.bottomItemText4
        )
        binding.color = context.getColor(R.color.color_secondary)
        binding.itemColor = context.getColor(R.color.black)
        binding.executePendingBindings()
        setListener()
    }

    private fun setListener() {
        binding.bottomItemIcon1.setOnClickListener {
            if (current != 0) {
                tabs[current].scaleDownVertical(Gravity.BOTTOM)
                current = 0
                tabs[current].scaleUpVertical(Gravity.BOTTOM)
            }
        }
        binding.bottomItemIcon2.setOnClickListener {
            if (current != 1) {
                tabs[current].scaleDownVertical(Gravity.BOTTOM)
                current = 1
                tabs[current].scaleUpVertical(Gravity.BOTTOM)
            }
        }
        binding.bottomItemIcon3.setOnClickListener {
            if (current != 2) {
                tabs[current].scaleDownVertical(Gravity.BOTTOM)
                current = 2
                tabs[current].scaleUpVertical(Gravity.BOTTOM)
            }
        }
        binding.bottomItemIcon4.setOnClickListener {
            if (current != 3) {
                tabs[current].scaleDownVertical(Gravity.BOTTOM)
                current = 3
                tabs[current].scaleUpVertical(Gravity.BOTTOM)
            }
        }
    }

    companion object{

        @JvmStatic
        @BindingAdapter("customBackgroundTint")
        fun customBackgroundTint(v: MainBottomBar, color: Int?){
            color?.let {
                v.binding.color = it
                v.binding.executePendingBindings()
            }
        }
    }
}
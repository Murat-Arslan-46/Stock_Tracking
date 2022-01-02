package com.marslan.stocktracking.ui.product.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.marslan.stocktracking.core.extension.visible
import com.marslan.stocktracking.databinding.ItemViewProductBinding

class ItemViewProduct : LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, style: Int) : super(context, attrs, style)

    var binding = ItemViewProductBinding.inflate(LayoutInflater.from(context),this,true)

}
package com.marslan.stocktracking.ui.customer.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.marslan.stocktracking.databinding.ItemViewCustomerBinding

class ItemViewCustomer : LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, style: Int) : super(context, attrs, style)

    var binding = ItemViewCustomerBinding.inflate(LayoutInflater.from(context), this, true)

}
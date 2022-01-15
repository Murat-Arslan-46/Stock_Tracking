package com.marslan.stocktracking.core.component

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.marslan.stocktracking.services.model.Product

class ProductSpinnerAdapter(context: Context, id: Int, val list: Array<Product>) :
    ArrayAdapter<Product>(context, id, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getView(position, convertView, parent) as TextView
        label.text = list[position].name
        return label
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val label = super.getDropDownView(position, convertView, parent) as TextView
        label.text = list[position].name
        return label
    }
}
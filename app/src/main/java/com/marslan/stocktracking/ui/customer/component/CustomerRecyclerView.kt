package com.marslan.stocktracking.ui.customer.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Product

class CustomerRecyclerView : RecyclerView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, style: Int) : super(context, attrs, style) {
        layoutManager = LinearLayoutManager(context)
        adapter = CustomerAdapter()
    }

    companion object {

        @SuppressLint("NotifyDataSetChanged")
        @JvmStatic
        @BindingAdapter("customer:loadData")
        fun loadData(v: CustomerRecyclerView, list: ArrayList<Customer>?) {
            v.currentList = list ?: arrayListOf()
            v.adapter?.notifyDataSetChanged()
        }

    }

    private var currentList = arrayListOf<Customer>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(value: Customer, flag: Boolean?) {
        val index = currentList.indexOf(value)
        when (flag) {
            // edit
            null -> {
                if (index >= 0) {
                    currentList[index] = value
                    adapter?.notifyItemChanged(index)
                }
            }
            // add
            true -> {
                currentList.add(value)
                currentList.sortBy { it.name }
                adapter?.notifyDataSetChanged()
            }
            // remove
            false -> {
                if (index >= 0) {
                    currentList.remove(value)
                    adapter?.notifyItemRemoved(index)
                }
            }
        }
    }

    inner class CustomerAdapter : Adapter<CustomerViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
            val view = ItemViewCustomer(parent.context)
            view.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            return CustomerViewHolder(view)
        }

        override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
            holder.view.binding.apply {
                model = currentList[position]
                executePendingBindings()
            }
        }

        override fun getItemCount() = currentList.size

    }

    inner class CustomerViewHolder(val view: ItemViewCustomer) : ViewHolder(view)
}
package com.marslan.stocktracking.ui.order.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marslan.stocktracking.core.helper.DataHelper
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Order

class OrderRecyclerView : RecyclerView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, style: Int) : super(context, attrs, style) {
        layoutManager = LinearLayoutManager(context)
        adapter = OrderAdapter()
    }

    companion object {

        @SuppressLint("NotifyDataSetChanged")
        @JvmStatic
        @BindingAdapter("order:loadData")
        fun loadData(v: OrderRecyclerView, list: ArrayList<Order>?) {
            v.currentList = list ?: arrayListOf()
            v.adapter?.notifyDataSetChanged()
        }

    }

    private var currentList = arrayListOf<Order>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(value: Order, flag: Boolean?) {
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
                currentList.sortBy { it.date }
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

    inner class OrderAdapter : Adapter<OrderViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
            val view = ItemViewOrder(parent.context)
            view.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            return OrderViewHolder(view)
        }

        override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
            holder.view.binding.apply {
                model = currentList[position]
                customers = DataHelper.getCustomers() as java.util.ArrayList<Customer>
                executePendingBindings()
            }
        }

        override fun getItemCount() = currentList.size

    }

    inner class OrderViewHolder(val view: ItemViewOrder) : ViewHolder(view)
}
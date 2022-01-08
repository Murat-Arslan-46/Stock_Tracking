package com.marslan.stocktracking.ui.product.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marslan.stocktracking.services.model.Product

class ProductRecyclerView : RecyclerView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, style: Int) : super(context, attrs, style) {
        layoutManager = LinearLayoutManager(context)
        adapter = ProductAdapter()
    }

    interface ItemEventListener{
        fun clickConf(product: Product)
    }

    companion object {

        @SuppressLint("NotifyDataSetChanged")
        @JvmStatic
        @BindingAdapter("product:loadData")
        fun loadData(v: ProductRecyclerView, list: ArrayList<Product>?) {
            v.currentList = list ?: arrayListOf()
            v.adapter?.notifyDataSetChanged()
        }

    }

    var listener: ItemEventListener? = null
    private var currentList = arrayListOf<Product>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(product: Product, flag: Boolean?) {
        val index = currentList.indexOf(product)
        when (flag) {
            // edit
            null -> {
                if (index >= 0) {
                    currentList[index] = product
                    adapter?.notifyItemChanged(index)
                }
            }
            // add
            true -> {
                currentList.add(product)
                currentList.sortBy { it.name }
                adapter?.notifyDataSetChanged()
            }
            // remove
            false -> {
                if (index >= 0) {
                    currentList.remove(product)
                    adapter?.notifyItemRemoved(index)
                }
            }
        }
    }

    inner class ProductAdapter : Adapter<ProductViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val view = ItemViewProduct(parent.context)
            view.layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            return ProductViewHolder(view)
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            holder.view.binding.apply {
                product = currentList[position]
                executePendingBindings()
                itemViewProductConf.setOnClickListener {
                    listener?.clickConf(currentList[position])
                }
            }
        }

        override fun getItemCount() = currentList.size

    }

    inner class ProductViewHolder(val view: ItemViewProduct) : ViewHolder(view)
}
package com.marslan.stocktracking.ui.main.component

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import com.marslan.stocktracking.core.extension.scaleDownHorizontal
import com.marslan.stocktracking.core.extension.scaleUpHorizontal
import com.marslan.stocktracking.databinding.MainToolbarBinding

class MainToolbar : LinearLayout {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, style: Int) : super(context, attrs, style){
        val inflater = LayoutInflater.from(context)
        binding = MainToolbarBinding.inflate(inflater,this,true)
        setListeners()
    }

    interface MainToolbarListener{
        fun menuButton()
        fun addButton()
        fun searchChange(search: Editable?)
    }

    private var binding: MainToolbarBinding
    private var listener: MainToolbarListener? = null
    private var searchIsOpen: Boolean = false

    fun addListener(listener: MainToolbarListener){
        this.listener = listener
    }

    fun setTitle(title: CharSequence){
        binding.titleToolbar.text = title
    }

    private fun setListeners(){
        binding.menuButton.setOnClickListener {
            listener?.menuButton()
        }
        binding.searchButton.setOnClickListener {
            if (searchIsOpen) {
                binding.searchInput.text?.clear()
                binding.searchInput.scaleDownHorizontal(Gravity.END)
            }
            else
                binding.searchInput.scaleUpHorizontal(Gravity.END)
            searchIsOpen = !searchIsOpen
        }
        binding.addButton.setOnClickListener {
            listener?.addButton()
        }
        binding.searchInput.addTextChangedListener {
            listener?.searchChange(it)
        }
    }
}
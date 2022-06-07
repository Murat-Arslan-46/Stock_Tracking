package com.marslan.stocktracking.ui.main.component

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.marslan.stocktracking.R
import com.marslan.stocktracking.core.extension.gone
import com.marslan.stocktracking.core.extension.visible
import com.marslan.stocktracking.databinding.BottomBarBinding

class BottomBar : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, style: Int)
            : super(context, attrs, style)

    private var binding: BottomBarBinding = BottomBarBinding.inflate(LayoutInflater.from(context),this,true)
    private var passiveTextColor = context.getColor(R.color.grey_text)
    private var activeTextColor = context.getColor(R.color.color_primary)
    private var clickCallback: ((Int) -> Unit)? = null
    /**
     * bottom bar dinamik olarak en fazla 5 ekrana destek verir
     * **/
    fun init(menu: List<FragmentNavigator.FragmentModel>){
        binding.LL0.gone()
        binding.LL0.setOnClickListener { clickCallback?.invoke(0) }
        binding.LL1.gone()
        binding.LL1.setOnClickListener { clickCallback?.invoke(1) }
        binding.LL2.gone()
        binding.LL2.setOnClickListener { clickCallback?.invoke(2) }
        binding.LL3.gone()
        binding.LL3.setOnClickListener { clickCallback?.invoke(3) }
        binding.LL4.gone()
        binding.LL4.setOnClickListener { clickCallback?.invoke(4) }
        if (menu.count() > 0){
            binding.LL0.visible()
            try { binding.IV0.setImageDrawable(menu.first().icon) }
            catch (e: IndexOutOfBoundsException){ }
            try { binding.TV0.text = menu.first().title }
            catch (e: IndexOutOfBoundsException){ }
        }
        if (menu.count() > 1){
            binding.LL1.visible()
            binding.IV1.setImageDrawable(try { menu[1].icon }
            catch (e: IndexOutOfBoundsException){ menu.last().icon })
            binding.TV1.text = try { menu[1].title }
            catch (e: IndexOutOfBoundsException){ menu.last().title }
        }
        if (menu.count() > 2){
            binding.LL2.visible()
            binding.IV2.setImageDrawable(try { menu[2].icon }
            catch (e: IndexOutOfBoundsException){ menu.last().icon })
            binding.TV2.text = try { menu[2].title }
            catch (e: IndexOutOfBoundsException){ menu.last().title }
        }
        if (menu.count() > 3){
            binding.LL3.visible()
            binding.IV3.setImageDrawable(try { menu[3].icon }
            catch (e: IndexOutOfBoundsException){ menu.last().icon })
            binding.TV3.text = try { menu[3].title }
            catch (e: IndexOutOfBoundsException){ menu.last().title }
        }
        if (menu.count() > 4){
            binding.LL4.visible()
            binding.IV4.setImageDrawable(try { menu[4].icon }
            catch (e: IndexOutOfBoundsException){ menu.last().icon })
            binding.TV4.text = try { menu[4].title }
            catch (e: IndexOutOfBoundsException){ menu.last().title }
        }
    }

    fun setBottomSelectItem(index : Int){
        binding.IV0.imageTintList = ColorStateList.valueOf(passiveTextColor)
        binding.TV0.setTextColor(passiveTextColor)
        binding.IV1.imageTintList = ColorStateList.valueOf(passiveTextColor)
        binding.TV1.setTextColor(passiveTextColor)
        binding.IV2.imageTintList = ColorStateList.valueOf(passiveTextColor)
        binding.TV2.setTextColor(passiveTextColor)
        binding.IV3.imageTintList = ColorStateList.valueOf(passiveTextColor)
        binding.TV3.setTextColor(passiveTextColor)
        binding.IV4.imageTintList = ColorStateList.valueOf(passiveTextColor)
        binding.TV4.setTextColor(passiveTextColor)
        when(index){
            0 -> {
                binding.IV0.imageTintList = ColorStateList.valueOf(activeTextColor)
                binding.TV0.setTextColor(activeTextColor)
            }
            1 -> {
                binding.IV1.imageTintList = ColorStateList.valueOf(activeTextColor)
                binding.TV1.setTextColor(activeTextColor)
            }
            2 -> {
                binding.IV2.imageTintList = ColorStateList.valueOf(activeTextColor)
                binding.TV2.setTextColor(activeTextColor)
            }
            3 -> {
                binding.IV3.imageTintList = ColorStateList.valueOf(activeTextColor)
                binding.TV3.setTextColor(activeTextColor)
            }
            4 -> {
                binding.IV4.imageTintList = ColorStateList.valueOf(activeTextColor)
                binding.TV4.setTextColor(activeTextColor)
            }
        }
    }

    fun addClickListener(listener: (Int) -> Unit){
        clickCallback = listener
    }
}
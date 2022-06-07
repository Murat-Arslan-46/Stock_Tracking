package com.marslan.stocktracking.ui.main.component

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlin.properties.Delegates

class FragmentNavigator {

    private lateinit var fragmentManager: FragmentManager
    private var containerId by Delegates.notNull<Int>()
    private var fragments = arrayListOf<FragmentModel>()
    private var fragmentChangeCallback: ((FragmentModel) -> Unit)? = null
    private var fragmentStock = arrayListOf<FragmentModel>()

    fun start() {
        fragmentManager.beginTransaction().add(containerId, fragments.first().fragment).commitNow()
        fragmentStock.add(fragments.first())
        fragmentChangeCallback?.invoke(fragmentStock.last())
    }

    fun open(index: Int){
        fragmentStock.remove(fragments[index])
        fragmentManager.beginTransaction().replace(containerId,fragments[index].fragment).commit()
        fragmentStock.add(fragments[index])
        fragmentChangeCallback?.invoke(fragmentStock.last())
    }

    fun onBackPressed(): Boolean {
        fragmentStock.removeLast()
        fragmentManager.popBackStack()
        return if (fragmentStock.isEmpty()) {
            start()
            true
        } else {
            fragmentChangeCallback?.invoke(fragmentStock.last())
            false
        }
    }

    companion object {

        @JvmStatic
        private fun newInstance(
            containerId: Int,
            fragmentManager: FragmentManager,
            fragments: List<FragmentModel>,
            fragmentChangeCallback: ((FragmentModel) -> Unit)?
        ) = FragmentNavigator().apply {
            this.containerId = containerId
            this.fragmentManager = fragmentManager
            this.fragments.addAll(fragments)
            this.fragmentChangeCallback = fragmentChangeCallback
        }
    }

    data class FragmentModel(
        val fragment: Fragment,
        val title: String? = null,
        val icon: Drawable? = null
    )

    class Builder(private val manager: FragmentManager, private val containerId: Int) {

        private var fragments = arrayListOf<FragmentModel>()
        private var fragmentChangeCallback: ((FragmentModel) -> Unit)? = null

        fun setFragments(list: List<FragmentModel>): Builder {
            fragments.clear()
            fragments.addAll(list)
            return this
        }

        fun setFragmentChangeCallback(callback: (FragmentModel) -> Unit): Builder {
            fragmentChangeCallback = callback
            return this
        }

        fun build(): FragmentNavigator {
            if (fragments.isEmpty())
                throw Throwable("the fragment list can not be empty")
            return newInstance(containerId, manager, fragments, fragmentChangeCallback)
        }

    }

}
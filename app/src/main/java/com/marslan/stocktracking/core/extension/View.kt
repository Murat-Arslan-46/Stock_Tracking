package com.marslan.stocktracking.core.extension

import android.view.View

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.visible(visible: Boolean?){
    if (visible == true)
        visible()
    else
        invisible()
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.gone(){
    visibility = View.GONE
}
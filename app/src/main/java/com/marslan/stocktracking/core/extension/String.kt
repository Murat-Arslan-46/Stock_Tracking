package com.marslan.stocktracking.core.extension

fun String?.ignoreNull() = this ?: kotlin.run { "" }
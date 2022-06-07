package com.marslan.stocktracking.core.model

sealed class Resource<out T : Any> {
    data class success<out T : Any>(val result: T, val msg: ResultMessage?) : Resource<T>()
    data class error(val msg: ResultMessage) : Resource<Nothing>()
    object loading : Resource<Nothing>()
}

package com.marslan.stocktracking.core.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("result_message")
    val result_message: ResultMessage?,
    @SerializedName("result")
    val result: T?
)

data class ResultMessage(
    @SerializedName("message")
    val message: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
)

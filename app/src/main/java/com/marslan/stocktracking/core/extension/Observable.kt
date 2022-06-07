package com.marslan.stocktracking.core.extension

import androidx.lifecycle.MutableLiveData
import com.marslan.stocktracking.core.model.BaseResponse
import com.marslan.stocktracking.core.model.Resource
import com.marslan.stocktracking.core.model.ResultMessage
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

inline fun <reified T : Any> Observable<BaseResponse<T>>.subscribes(
    emitter: ObservableEmitter<Resource<T>>,
    methodName: String
): Disposable {
    val onNext: Consumer<BaseResponse<T>> = Consumer {
        it.result_message?.type.apply {
            if (this == "success"){
                it.result?.let { data ->
                    emitter.onNext(Resource.success(data, it.result_message))
                } ?: emitter.onNext(Resource.error(it.result_message!!))
            }else
                emitter.onNext(Resource.error(it.result_message!!))
        }
    }

    val onError: Consumer<Throwable> = Consumer {
        val model = ResultMessage("Bir hata oluştu",methodName,"fail")
        emitter.onNext(Resource.error(model))
    }
    return this.subscribe(onNext, onError)
}

inline fun <reified T : Any> Observable<Resource<T>>.subs(
    status: MutableLiveData<Resource<T>>,
    errorMessage: String
): Disposable {
    return this.subscribe(
        {
            status.postValue(it)
        },
        {
            val model = ResultMessage("Bir hata oluştu",errorMessage,"fail")
            status.postValue(Resource.error(model))
        }
    )
}

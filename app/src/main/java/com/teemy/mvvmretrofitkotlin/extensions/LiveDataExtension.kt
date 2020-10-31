package com.teemy.mvvmretrofitkotlin.extensions

import androidx.lifecycle.MutableLiveData
import com.teemy.mvvmretrofitkotlin.resource.Resource
import com.teemy.mvvmretrofitkotlin.resource.ResourceState

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T? = null) {
    value = Resource(ResourceState.SUCCESS, data)
}

fun <T> MutableLiveData<Resource<T>>.setLoading() {
    value = Resource(ResourceState.LOADING, value?.data)
}

fun <T> MutableLiveData<Resource<T>>.setError(message: String? = null) {
    value = Resource(ResourceState.ERROR, value?.data, message)
}

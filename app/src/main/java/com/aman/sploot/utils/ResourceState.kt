package com.aman.sploot.utils


sealed class ResourceState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : ResourceState<T>(data = data)
    class Error<T>(message: String?) : ResourceState<T>(message = message)
}

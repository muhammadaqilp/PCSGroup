package com.example.pcsgroup.util

sealed class Resources<T>(val data: T? = null, val throwable: Throwable? = null) {
    class Success<T>(data: T) : Resources<T>(data)
    class Loading<T>(data: T? = null) : Resources<T>(data)
    class Empty<T>(data: T? = null) : Resources<T>(data)
    class Error<T>(throwable: Throwable?) : Resources<T>(throwable = throwable)
}
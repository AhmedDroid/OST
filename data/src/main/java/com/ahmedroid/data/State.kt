package com.ahmedroid.data

sealed class State<T> {

    data class Loading<T>(val isLoading: Boolean) : State<T>()

    data class Success<T>(val response: T?) : State<T>()

    data class Error<T>(val errMsg: String?) : State<T>()
}
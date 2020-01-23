@file:Suppress("unused")

package com.gregor.anaya.data.domain.repository

sealed class Result<out T : Any> {

    class Success<out T : Any>(val data: T) : Result<T>()

    class Error(val exception: Throwable) : Result<Nothing>()
}
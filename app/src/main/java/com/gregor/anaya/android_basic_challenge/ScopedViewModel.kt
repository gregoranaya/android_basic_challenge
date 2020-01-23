package com.gregor.anaya.android_basic_challenge

import androidx.annotation.CallSuper
import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class ScopedViewModel<N> : ViewModel(), Scope by Scope.Impl() {

    private var navigator: WeakReference<N>? = null

    fun getNavigator(): N? {
        return navigator?.get()
    }

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference<N>(navigator)
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
    }
}
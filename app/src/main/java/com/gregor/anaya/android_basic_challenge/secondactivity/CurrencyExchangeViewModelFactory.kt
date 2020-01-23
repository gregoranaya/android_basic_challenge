package com.gregor.anaya.android_basic_challenge.secondactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gregor.anaya.android_basic_challenge.splash.viewmodel.MainViewModel
import com.gregor.anaya.data.domain.api.CurrencyDataSource
import com.gregor.anaya.data.domain.repository.CurrencyDataRepository

class CurrencyExchangeViewModelFactory : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CurrencyExchangeViewModel::class.java)) {
            CurrencyExchangeViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }    }
}
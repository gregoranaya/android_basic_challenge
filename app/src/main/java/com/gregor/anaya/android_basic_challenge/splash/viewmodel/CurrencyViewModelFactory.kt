package com.gregor.anaya.android_basic_challenge.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gregor.anaya.data.domain.api.Api
import com.gregor.anaya.data.domain.api.CurrencyDataSource
import com.gregor.anaya.data.domain.repository.CurrencyDataRepository

class CurrencyViewModelFactory : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val api = Api.create()
            val repository = CurrencyDataRepository(CurrencyDataSource(api))

            MainViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }    }

}
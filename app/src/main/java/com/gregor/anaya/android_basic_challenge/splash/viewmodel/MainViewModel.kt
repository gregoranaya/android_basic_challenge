package com.gregor.anaya.android_basic_challenge.splash.viewmodel

import androidx.lifecycle.ViewModel
import com.gregor.anaya.data.domain.repository.CurrencyDataRepository
import kotlinx.coroutines.*

class MainViewModel (private val currencyDataRepository: CurrencyDataRepository) : ViewModel(){



    val currency = currencyDataRepository.currencyLiveData


    fun getCurrency(){

        GlobalScope.launch(Dispatchers.IO) {
            currencyDataRepository.getCurrency()

        }
    }
}
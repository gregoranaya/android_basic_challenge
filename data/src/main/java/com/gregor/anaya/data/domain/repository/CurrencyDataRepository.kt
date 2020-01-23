package com.gregor.anaya.data.domain.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gregor.anaya.data.data.repository.CurrencyRepository
import com.gregor.anaya.data.domain.api.DataSource
import com.gregor.anaya.data.entity.CurrencyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CurrencyDataRepository(val currencyDataSource: DataSource) : CurrencyRepository{


    val currencyLiveData: MutableLiveData<CurrencyResponse> by lazy {
        MutableLiveData<CurrencyResponse>()
    }

    override suspend fun getCurrency() {
        try {
            withContext(Dispatchers.IO) {
                val result = currencyDataSource.getCurrency()
                if (result is Result.Success){
                    currencyLiveData.postValue(result.data)
                }

            }
        } catch (e: HttpException) {
            Log.e("REQUEST", "Exception ${e.message}")
        }

    }





}
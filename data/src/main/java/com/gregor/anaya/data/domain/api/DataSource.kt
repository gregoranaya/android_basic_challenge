package com.gregor.anaya.data.domain.api

import com.gregor.anaya.data.domain.repository.Result
import com.gregor.anaya.data.entity.CurrencyResponse


interface DataSource {

    suspend fun getCurrency(): Result<CurrencyResponse>



}
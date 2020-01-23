package com.gregor.anaya.data.data.repository

import com.gregor.anaya.data.entity.CurrencyResponse

interface CurrencyRepository {

    suspend fun getCurrency ()
}
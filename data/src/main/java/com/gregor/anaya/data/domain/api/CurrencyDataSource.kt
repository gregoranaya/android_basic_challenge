package com.gregor.anaya.data.domain.api

import com.gregor.anaya.data.entity.CurrencyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import com.gregor.anaya.data.domain.repository.Result


class CurrencyDataSource(val apiService: Api):
    DataSource {

    override suspend fun getCurrency(): Result<CurrencyResponse> = withContext(Dispatchers.IO) {
        val request = apiService.getCurrency()
        try {
            val response = request.await()
            if (response.list.isNotEmpty()) {
                Result.Success(response)
            } else {
                Result.Error(RemoteDataNotFoundException())
            }
        }
        catch (ex: HttpException) {
            Result.Error(RemoteDataNotFoundException())
        }
        catch (ex: Throwable) {
            Result.Error(RemoteDataNotFoundException())
        }

    }

    companion object {
        fun newInstance() = CurrencyDataSource(Api.create())
    }
}

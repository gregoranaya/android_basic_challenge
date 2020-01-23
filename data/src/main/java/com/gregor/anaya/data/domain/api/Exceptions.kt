package com.gregor.anaya.data.domain.api

open class DataSourceException(message: String? = null) : Exception(message)

class RemoteDataNotFoundException : DataSourceException("Data not found in remote data source")
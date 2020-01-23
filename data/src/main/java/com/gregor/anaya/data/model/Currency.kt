package com.gregor.anaya.data.model

import java.math.BigDecimal

data class Currency(

    var name : String? = null,
    var currency: String? = null,
    var source : String? = null,
    var img : String? = null,
    var quotes: Map<String,Int> = mapOf()
)
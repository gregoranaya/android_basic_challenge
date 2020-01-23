package com.gregor.anaya.android_basic_challenge.secondactivity

import androidx.lifecycle.MutableLiveData
import com.gregor.anaya.android_basic_challenge.ScopedViewModel
import com.gregor.anaya.data.model.Currency
import java.math.BigDecimal

class CurrencyExchangeViewModel :  ScopedViewModel<Navigator>() {


    val currencyMutable = MutableLiveData<Currency>()


    fun calculate(int: Int, list : List<Currency>, source : String, secondSource : String){

        val currency = getCurrency(list,source)
        if (currency.quotes.isNotEmpty()) {
            val priceSales = currency.quotes[secondSource]
            val result = priceSales?.times(int)

            getNavigator()?.updateAmount(result!!)
        }
    }


    fun updateSalePurchase(list : List<Currency>, source : String, secondSource : String){

        val currency = getCurrency(list, source)
        val currencySecond = getCurrency(list,secondSource)

        val purchase = currency.quotes[secondSource].toString()
        val sales = currencySecond.quotes[source].toString()

        val text = StringBuffer()
        text.append("Compra: " ).append(purchase).append(" | " ).append("Venta: ").append(sales)

        getNavigator()?.updatePurchaseSales(text.toString())
    }


    private fun getCurrency(list : List<Currency>, source : String) : Currency{
        var currency = Currency()
        for (i in list){
            if (i.source == source){
                currency =  i
            }
        }
        return currency
    }


    fun reloadData(oldCurrency: Currency){
        currencyMutable.postValue(oldCurrency)

    }
}
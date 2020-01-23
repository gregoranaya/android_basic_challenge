package com.gregor.anaya.android_basic_challenge.list

import androidx.lifecycle.ViewModel
import com.gregor.anaya.android_basic_challenge.ScopedViewModel
import com.gregor.anaya.data.model.Currency

class ListViewModel : ScopedViewModel<ListNavigator>(){

        fun onClickCurrency(currency: Currency){
            getNavigator()?.goToBack(currency)
        }
}
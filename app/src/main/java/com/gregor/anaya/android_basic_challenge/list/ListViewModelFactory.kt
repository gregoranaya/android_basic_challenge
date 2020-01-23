package com.gregor.anaya.android_basic_challenge.list

import android.widget.ListView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gregor.anaya.android_basic_challenge.secondactivity.CurrencyExchangeViewModel

class ListViewModelFactory : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            ListViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }    }
}
package com.gregor.anaya.android_basic_challenge.splash.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.gregor.anaya.android_basic_challenge.R
import com.gregor.anaya.android_basic_challenge.secondactivity.CurrencyExchangeActivity
import com.gregor.anaya.android_basic_challenge.splash.viewmodel.CurrencyViewModelFactory
import com.gregor.anaya.android_basic_challenge.splash.viewmodel.MainViewModel
import com.gregor.anaya.data.entity.CurrencyResponse

class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel =
            ViewModelProviders.of(this, CurrencyViewModelFactory())
                .get(MainViewModel::class.java)


        viewModel.getCurrency()


        viewModel.currency.observe(this, Observer {
            it?.let{
                val intent = intentTo(this)
                val bundle = Bundle()
                val gson = Gson()
                bundle.putString("currency",gson.toJson(it))
                intent?.putExtras(bundle)
                startActivity(intent)
                finish()
            }
        })

    }



    private fun intentTo(intentActivity: Activity): Intent? =
        Intent(intentActivity, CurrencyExchangeActivity::class.java)


}

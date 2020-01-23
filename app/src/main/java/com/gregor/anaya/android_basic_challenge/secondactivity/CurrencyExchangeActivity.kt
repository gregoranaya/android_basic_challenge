package com.gregor.anaya.android_basic_challenge.secondactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.gregor.anaya.android_basic_challenge.list.ListActivity
import com.gregor.anaya.android_basic_challenge.R
import com.gregor.anaya.data.entity.CurrencyResponse
import com.gregor.anaya.data.model.Currency
import kotlinx.android.synthetic.main.activity_currency_exchange.*

class CurrencyExchangeActivity : AppCompatActivity(), Navigator{

    lateinit var currencyR: CurrencyResponse
    lateinit var viewModel: CurrencyExchangeViewModel
      var currencyM = Currency()
      var secondcurrency = Currency()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_exchange)

        val data = intent.getStringExtra("currency")
        val gson = Gson()
        currencyR = gson.fromJson(data, CurrencyResponse::class.java)

        viewModel =
            ViewModelProviders.of(this, CurrencyExchangeViewModelFactory())
                .get(CurrencyExchangeViewModel::class.java)
        viewModel.setNavigator(this)


        viewModel.currencyMutable.observe(this, Observer {
            secondcurrency = currencyM
            currencyM = it
            updateCurrencyName()
            viewModel.updateSalePurchase(currencyR.list,currencyM.source.toString(),secondcurrency.source.toString())

        })

        initViews()
        initWatcher()
        initListener()
        changeCurrencyObject()
    }

    private fun changeCurrencyObject() {
        val stringCurremcy = intent.getStringExtra("object")
        val value = intent.getIntExtra("position",3)
        val oldCurrencyString = intent.getStringExtra("oldCurrency")
        val gson = Gson()
        val newCurrency = gson.fromJson(stringCurremcy, Currency::class.java)
        val oldCurrency = gson.fromJson(oldCurrencyString, Currency::class.java)

        if (value == 0){
            currencyM = newCurrency
            secondcurrency = oldCurrency
        }else if (value == 1){
            secondcurrency = newCurrency
            currencyM = oldCurrency
        }

        viewModel.updateSalePurchase(currencyR.list,currencyM.source.toString(),secondcurrency.source.toString())
        updateCurrencyName()

    }


    private fun initViews() {
        currencyM = currencyR.list[0]
        secondcurrency = currencyR.list[1]
        updateCurrencyName()
        viewModel.updateSalePurchase(currencyR.list,currencyM.source.toString(),secondcurrency.source.toString())


    }

    private fun initWatcher() {
        sendAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(sendAmount.text.isNotEmpty()) {
                    val amount = sendAmount.text.toString().toInt()
                    viewModel.calculate(
                        amount,
                        currencyR.list,
                        currencyM.source.toString(),
                        secondcurrency.source.toString()
                    )
                }else {
                    sendAmount_second.text = "0.00"
                }
            }
        })
    }

    override fun updateAmount(result : Int) {
        sendAmount_second.text = result.toString()
    }

    override fun updatePurchaseSales(text: String) {
        purchaseSale.text = text
    }

    private fun initListener() {
        changeCurrency.setOnClickListener {
            viewModel.reloadData(secondcurrency)
        }

        currency.setOnLongClickListener {
            goToActivity(currencyR,0)

        }

        currency_second.setOnLongClickListener {
            goToActivity(currencyR,1)

        }
    }

    private fun goToActivity(list: CurrencyResponse,position: Int): Boolean {

        val intent = intentTo(this)
        val bundle = Bundle()
        val gson = Gson()
        if (position == 1){
            bundle.putString("oldCurrency",gson.toJson(currencyM))
        }else bundle.putString("oldCurrency",gson.toJson(secondcurrency))
        bundle.putString("list",gson.toJson(list))
        bundle.putInt("position",position)

        intent?.putExtras(bundle)
        startActivity(intent)
        finish()
        return true
    }




    private fun intentTo(intentActivity: Activity): Intent? =
    Intent(intentActivity, ListActivity::class.java)

    private fun updateCurrencyName(){
        currency.text = currencyM.currency
        currency_second.text = secondcurrency.currency

        if (sendAmount.text.isNotEmpty()){
            sendAmount.setText("")
            sendAmount_second.text = ""
        }
    }


}

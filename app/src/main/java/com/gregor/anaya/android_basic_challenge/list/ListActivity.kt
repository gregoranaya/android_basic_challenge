package com.gregor.anaya.android_basic_challenge.list

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.gregor.anaya.android_basic_challenge.R
import com.gregor.anaya.android_basic_challenge.secondactivity.CurrencyExchangeActivity
import com.gregor.anaya.android_basic_challenge.secondactivity.CurrencyExchangeViewModel
import com.gregor.anaya.android_basic_challenge.secondactivity.CurrencyExchangeViewModelFactory
import com.gregor.anaya.data.entity.CurrencyResponse
import com.gregor.anaya.data.model.Currency
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), ListNavigator{


    lateinit var viewModel: ListViewModel
    lateinit var data : String
    var position : Int = 0
    lateinit var currencyResponse: CurrencyResponse


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        data = intent.getStringExtra("list")

        position = intent.getIntExtra("position",0)


        viewModel =
            ViewModelProviders.of(this, ListViewModelFactory())
                .get(ListViewModel::class.java)
        viewModel.setNavigator(this)

        val adapter = ListAdapter(viewModel::onClickCurrency)

        val gson = Gson()
        currencyResponse = gson.fromJson(data, CurrencyResponse::class.java)

        adapter.list = currencyResponse.list

            recycler.setHasFixedSize(true)
        val gridLayoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recycler.layoutManager = gridLayoutManager
        recycler.adapter = adapter
    }


    override fun goToBack(currency: Currency) {
        val intent = intentTo(this)
        val bundle = Bundle()
        val gson = Gson()
        bundle.putString("currency",gson.toJson(currencyResponse))
        bundle.putString("object",gson.toJson(currency))
        bundle.putString("oldCurrency",this.intent.getStringExtra("oldCurrency"))
        bundle.putInt("position",position)
        intent?.putExtras(bundle)
        startActivity(intent)
        finish()
    }


    private fun intentTo(intentActivity: Activity): Intent? =
        Intent(intentActivity, CurrencyExchangeActivity::class.java)
}

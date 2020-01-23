package com.gregor.anaya.android_basic_challenge.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.gregor.anaya.android_basic_challenge.R
import com.gregor.anaya.data.model.Currency
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter(private val onClickCurrency: (Currency) -> Unit) : RecyclerView.Adapter<ListAdapter.CurrencyHolder>() {


    var list : List<Currency> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder =
        CurrencyHolder(parent.inflate(R.layout.item_list, false))

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val currency = list[position]
        holder.bind(currency)
        holder.itemView.cardView.setOnClickListener {
            onClickCurrency(currency)
        }
    }


    class CurrencyHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind( currency: Currency ) {
            itemView.nameCurrency.text = currency.name
            itemView.subTitle.text = currency.source

        }
    }



    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
        LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
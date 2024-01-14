package com.example.lokal_assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lokal_assignment.data.model.CryptoCurr
import com.example.lokal_assignment.databinding.ItemFileCurrencyBinding
import java.math.BigDecimal
import java.math.RoundingMode

class CurrencyAdapter(
    val context: Context,
    val cryptoCurrList: List<CryptoCurr>,
    private val listener: (cryptoCurr: CryptoCurr) -> Unit
) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyAdapter.CurrencyViewHolder {
        val binding = ItemFileCurrencyBinding.inflate(LayoutInflater.from(context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyAdapter.CurrencyViewHolder, position: Int) {
        val cryptoCurrency = cryptoCurrList[position]
        holder.bind(cryptoCurrency, listener)
    }

    override fun getItemCount(): Int {
        return cryptoCurrList.size
    }

    inner class CurrencyViewHolder(val binding: ItemFileCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            cryptoCurr: CryptoCurr,
            listener: (cryptoCurr: CryptoCurr) -> Unit
        ) {
            binding.apply {
                cryptoName.text = cryptoCurr.name
                cryptoSymbol.text = cryptoCurr.symbol
                cryptoValue.text = roundTo6DecimalPlaces(cryptoCurr.crypto_rate) + "$"
                Glide.with(context).load(
                    cryptoCurr.image_url
                ).into(image)
            }
            binding.root.setOnClickListener {
                listener(cryptoCurr)
            }
        }
    }


    fun roundTo6DecimalPlaces(value: Double): String {
        return BigDecimal(value).setScale(6, RoundingMode.HALF_EVEN).toString()
    }
}
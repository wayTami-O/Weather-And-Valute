package com.example.viewpagerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpagerapp.R
import com.example.viewpagerapp.databinding.ItemMoneyBinding
import com.example.viewpagerapp.model.valute.Valute

class ValuteAdapter: RecyclerView.Adapter<ValuteAdapter.ValuteHolder>() {

    var valuteList = ArrayList<Valute>()

    inner class ValuteHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ItemMoneyBinding.bind(item)
        fun bind(valute: Valute) = with(binding) {
            var item = ""
            when(valute.CharCode) {
                "AUD", "CAD", "NZD" -> item = "$"
                "AZN" -> item = "₼"
                "GBP" -> item =  "£"
                "AMD" -> item =  "֏"
                "BYN" -> item =  "Br"
                "BGN" -> item =  "Bg"
                "BRL" -> item =  "Brl"
                "HUF" -> item =  "ƒ"
                "VND" -> item =  "₫"
                "HKD" -> item =  "HK $"
                "GEL" -> item =  "₾"
                "DKK" -> item =  "kr"
                "AED" -> item =  "د.إ"
                "USD" -> item =  "$"
                "EUR" -> item =  "€"
                "EGP" -> item =  ".ج.م"
                "INR" -> item =  "₹"
                "IDR" -> item =  "र"
                "KZT" -> item =  "₸"
                "QAR" -> item =  "ر."
                "KGS" -> item =  "с"
                "CNY" -> item =  "¥"
                "MDL" -> item =  "L"
                "NOK" -> item =  "кр"
                "PLN" -> item =  "zł"
                "RON" -> item =  "lei"
                "XDR" -> item =  "XDR"
                "SGD" -> item =  "S$"
                "TJS" -> item =  "с"
                "THB" -> item =  "฿"
                "TRY" -> item =  "₺"
                "TMT" -> item =  "m"
                "UZS" -> item =  "Soʻm"
                "UAH" -> item =  "₴"
                "CZK" -> item =  "Kč"
                "SEK" -> item =  "kr"
                "CHF" -> item =  "₣"
                "RSD" -> item =  "din"
                "ZAR" -> item =  "R"
                "KRW" -> item =  "원"
                "JPY" -> item = "¥"
                else -> "Хуйня валютка"
            }
            if (valute.Previous > valute.Value) {
                binding.imageView.setImageResource(R.drawable.baseline_arrow_drop_down_24)
            } else {
                binding.imageView.setImageResource(R.drawable.baseline_arrow_drop_up_24)
            }
            binding.firstStroke.text = "${valute.CharCode} = ${valute.Name}"
            binding.secondStroke.text = "${valute.Nominal} ₽ = ${valute.Value} $item"
            //imgOper.setImageResource(oper.imageId)
            //titleOper.text = oper.title
            //imgOper.setOnClickListener {
            //    listener.invoke(titleOper.text.toString(), oper.imageId, adapterPosition, oper.count)
            //}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValuteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_money, parent, false)
        return ValuteHolder(view)
    }

    override fun getItemCount(): Int {
        return valuteList.size
    }

    override fun onBindViewHolder(holder: ValuteHolder, position: Int) {
        holder.bind(valuteList[position])
    }

    fun addOper(valute: Valute) {
        valuteList.add(valute)
        notifyDataSetChanged()
    }
}
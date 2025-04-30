package com.example.practiceem.practice3.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceem.databinding.ItemCurrencyBinding
import com.example.practiceem.practice3.model.Currency

class CurrencyAdapter(
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {
    private val currencies = mutableListOf<Currency>()

    inner class CurrencyViewHolder(val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = ItemCurrencyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CurrencyViewHolder,
        position: Int
    ) {
        val currency = currencies[position]
        holder.binding.apply {
            currencyName.text = currency.name
            currencyValue.text = currency.value.toString()
            root.setOnClickListener {
                onItemClick(position)
            }
        }
    }

    override fun getItemCount() = currencies.size

    fun submitList(newList: List<Currency>) {
        currencies.clear()
        currencies.addAll(newList)
        notifyDataSetChanged()
    }
}
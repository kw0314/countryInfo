package com.mingolab.countryinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mingolab.countryinfo.databinding.ItemCountryinfoBinding


class CountryInfoAdapter (countries: List<Country>): RecyclerView.Adapter< CountryInfoAdapter.CountryInfoViewHolder>() {

    private val body = countries

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryInfoViewHolder {
        val binding = ItemCountryinfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryInfoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return body.size
    }

    override fun onBindViewHolder(holder: CountryInfoViewHolder, position: Int) {
        val item = body!![position]
        holder.bind(item)
    }


    class CountryInfoViewHolder(private val binding: ItemCountryinfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Country) {
            with(binding){
                tvCountry.text = item.name
                tvRegion.text = item.region
                tvCode.text = item.code
                tvCapital.text = item.capital
            }
        }
    }


}

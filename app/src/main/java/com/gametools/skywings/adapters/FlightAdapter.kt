package com.gametools.skywings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gametools.skywings.databinding.RecyclerItemFlightBinding

class FlightAdapter(private val onItemClicked: () -> Unit): RecyclerView.Adapter<FlightAdapter.FlightViewHolder>() {
    class FlightViewHolder(val binding: RecyclerItemFlightBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        return FlightViewHolder(RecyclerItemFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = 10

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.binding.apply {
            listOf(economy,business).forEach {
                it.setOnClickListener {
                    onItemClicked()
                }
            }
        }
    }
}
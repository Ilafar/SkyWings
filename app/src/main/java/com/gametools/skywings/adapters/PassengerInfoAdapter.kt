package com.gametools.skywings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gametools.skywings.databinding.RecyclerItemPassengerInfoBinding

class PassengerInfoAdapter: RecyclerView.Adapter<PassengerInfoAdapter.PassengerInfoViewHolder>() {
    class PassengerInfoViewHolder(val binding: RecyclerItemPassengerInfoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerInfoViewHolder {
        return PassengerInfoViewHolder(RecyclerItemPassengerInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = 3

    override fun onBindViewHolder(holder: PassengerInfoViewHolder, position: Int) {
    }
}
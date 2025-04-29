package com.gametools.skywings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gametools.skywings.databinding.RecyclerItemMyflightBinding

class MyFlightsAdapter:RecyclerView.Adapter<MyFlightsAdapter.MyViewHolder>() {
    private var isExpanded = false
    class MyViewHolder(val binding: RecyclerItemMyflightBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RecyclerItemMyflightBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = 10

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.passengerRecycler.adapter = PassengerInfoAdapter()
        holder.binding.viewGroup.visibility = if (isExpanded) ViewGroup.VISIBLE else ViewGroup.GONE
        holder.binding.root.setOnClickListener {
            isExpanded = !isExpanded
            notifyItemChanged(position)
        }
    }
}
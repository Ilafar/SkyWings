package com.gametools.skywings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gametools.skywings.databinding.RecyclerItemHistoryBinding

class HistoryAdapter:RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(val binding:RecyclerItemHistoryBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount() = 10

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}
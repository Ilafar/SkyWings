package com.gametools.skywings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gametools.skywings.databinding.RecyclerItemPassengerBinding

class PassengerAdapter(
    private val onDateOfBirthClick: () -> Unit,
    private val onChooseSeatClick: () -> Unit
): RecyclerView.Adapter<PassengerAdapter.PassengerViewHolder>() {
    private var isExpanded = false
    private var selectedSeat: String? = null
    class PassengerViewHolder(val binding: RecyclerItemPassengerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassengerViewHolder {
        return PassengerViewHolder(RecyclerItemPassengerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = 3

    override fun onBindViewHolder(holder: PassengerViewHolder, position: Int) {
        holder.binding.apply {
            textView10.text = "Passenger - ${position + 1}"
            viewGroup.visibility = if (isExpanded) ViewGroup.VISIBLE else ViewGroup.GONE
            if (selectedSeat != null) {
                choseSeat.apply {
                    text = selectedSeat
                    setCompoundDrawables(null,null,null,null)
                }
            }
        }
        holder.binding.dateOfBirth.setOnClickListener {
            onDateOfBirthClick()
        }
        holder.binding.chooseSeat.setOnClickListener {
            onChooseSeatClick()
        }
        holder.binding.cardView.setOnClickListener {
            isExpanded = !isExpanded
            notifyItemChanged(position)
        }
    }
    fun updateSeat(seat: String) {
        selectedSeat = seat
        notifyDataSetChanged()
    }
}
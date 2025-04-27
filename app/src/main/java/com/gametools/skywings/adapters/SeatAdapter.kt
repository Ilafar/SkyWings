package com.gametools.skywings.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gametools.skywings.databinding.RecyclerItemSeatBinding
import com.google.android.material.radiobutton.MaterialRadioButton
import kotlin.random.Random

class SeatAdapter : RecyclerView.Adapter<SeatAdapter.SeatViewHolder>() {
    private var lastSelected: MaterialRadioButton? = null
    var selectedSeat: String? = null
    private val rows = 11
    private val columns = 6
    private val seats = Array(rows) { row ->
        Array(columns) { column ->
            val columnLetter = ('A'.code + column).toChar()
            val rowNumber = row + 1
            "$columnLetter$rowNumber"
        }
    }

    class SeatViewHolder(val binding: RecyclerItemSeatBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        return SeatViewHolder(
            RecyclerItemSeatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = 66
    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        val columnsPerRow = 6
        val columnPosition = position % columnsPerRow
        val seatView = holder.binding.root

        if (Random.nextBoolean())
            holder.binding.radio.isEnabled = false
        when (columnPosition) {
            in 0..2 -> {
                seatView.gravity = Gravity.START
            }
            in 3..5 -> {
                seatView.gravity = Gravity.END
            }
        }
        holder.binding.radio.setOnClickListener {
            if(it != lastSelected){
                lastSelected?.isChecked = false
                lastSelected = it as MaterialRadioButton
                selectedSeat = seats[position / columnsPerRow][columnPosition]
            }
        }
    }
}
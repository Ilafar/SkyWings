package com.gametools.skywings.view.seat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResult
import com.gametools.skywings.adapters.SeatAdapter
import com.gametools.skywings.databinding.FragmentSeatDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SeatSelectionDialog : BottomSheetDialogFragment() {

    private var _binding: FragmentSeatDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeatDialogBinding.inflate(inflater, container, false)
        val adapter = SeatAdapter()
        binding.seatRecycler.adapter = adapter
        binding.confirmButton.setOnClickListener {
            setFragmentResult("seatSelectionRequestKey", Bundle().apply {
              putString("selectedSeat", adapter.selectedSeat.toString())
            })
            dismiss()
        }

        return binding.root

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
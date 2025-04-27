package com.gametools.skywings.view.flights

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gametools.skywings.R
import com.gametools.skywings.adapters.FlightAdapter
import com.gametools.skywings.databinding.FragmentFlightsBinding
import com.gametools.skywings.databinding.ViewChipBinding
import java.text.SimpleDateFormat
import java.util.Locale

class FlightsFragment : Fragment() {

    private var _binding: FragmentFlightsBinding? = null
    private val binding get() = _binding!!
    private var selectedDate: Int = 0
    private var numChipsAdded = 0
    private var isLoadingMoreChips = false

    private val viewModel: FlightsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightsBinding.inflate(inflater, container, false)
        binding.dateChipGroup.addView(View(requireContext()))
        binding.flightRecycler.adapter = FlightAdapter { findNavController().navigate(R.id.action_flightsFragment2_to_confirmFlightFragment) }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().finish()
        }
        addChips(0, 10)
        binding.horizontalScrollView.viewTreeObserver.addOnScrollChangedListener {
            val scrollView = binding.horizontalScrollView
            val view = scrollView.getChildAt(scrollView.childCount - 1)
            val diff = (view.right - (scrollView.scrollX + scrollView.width))
            if (diff == 0 && !isLoadingMoreChips) {
                isLoadingMoreChips = true
                addChips(numChipsAdded, 5)
                isLoadingMoreChips = false
            }
        }

        binding.dateChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            checkedIds.forEach {
                group.findViewById<View>(it)?.let { chip ->
                    selectedDate = group.indexOfChild(chip)-1
                }
            }
        }
        return binding.root
    }

    private fun addChips(start: Int, count: Int) {
        val next = start + count
        for (i in start until next){
            val chipBinding = ViewChipBinding.inflate(layoutInflater)
            chipBinding.chip.text = getDateFormatted(i)
            chipBinding.chip.id = View.generateViewId()
            if (i == selectedDate) chipBinding.chip.isChecked = true
            binding.dateChipGroup.addView(chipBinding.chip)
            numChipsAdded++
        }
    }

    private fun getDateFormatted(position: Int): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, position)
        val dateFormat = SimpleDateFormat("EEE, dd MMM", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}
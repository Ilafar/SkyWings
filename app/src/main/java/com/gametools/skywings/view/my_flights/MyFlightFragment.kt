package com.gametools.skywings.view.my_flights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gametools.skywings.adapters.MyFlightsAdapter
import com.gametools.skywings.databinding.FragmentMyFlightsBinding

class MyFlightFragment : Fragment() {

    private var _binding: FragmentMyFlightsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myFlightViewModel = ViewModelProvider(this)[MyFlightViewModel::class.java]
        _binding = FragmentMyFlightsBinding.inflate(inflater, container, false)

        binding.recyclerMyFlights.adapter = MyFlightsAdapter()

        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
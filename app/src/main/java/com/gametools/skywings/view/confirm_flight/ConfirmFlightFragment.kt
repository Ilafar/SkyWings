package com.gametools.skywings.view.confirm_flight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gametools.skywings.R
import com.gametools.skywings.databinding.FragmentConfirmFlightBinding

class ConfirmFlightFragment : Fragment() {

    private var _binding: FragmentConfirmFlightBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConfirmFlightViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left,0, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmFlightBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.editButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.confirmButton.setOnClickListener {
            findNavController().navigate(R.id.action_confirmFlightFragment_to_passengerFragment)
        }
        return binding.root
    }
}
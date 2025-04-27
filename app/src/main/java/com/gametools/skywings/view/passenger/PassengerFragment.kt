package com.gametools.skywings.view.passenger

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gametools.skywings.R
import com.gametools.skywings.adapters.PassengerAdapter
import com.gametools.skywings.databinding.AlertSuccessBinding
import com.gametools.skywings.databinding.FragmentPassengerBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PassengerFragment : Fragment() {

    private var _binding: FragmentPassengerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PassengerViewModel by viewModels()

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
        _binding = FragmentPassengerBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.confirmBtn.setOnClickListener {
            showSuccessAlertDialog()
        }
        val adapter = PassengerAdapter (
            { showCenteredCalendar(requireContext()) },
            { findNavController().navigate(R.id.action_passengerFragment_to_seatSelectionDialog) }
        )
        binding.passengersRecycler.adapter =adapter
        setFragmentResultListener("seatSelectionRequestKey"){
                _, bundle ->
            val selectedSeat = bundle.getString("selectedSeat")
            if (selectedSeat != null) {
                adapter.updateSeat(selectedSeat)
            }
        }
        return binding.root
    }

    private fun showCenteredCalendar(context: Context) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Handle the selected date (e.g., update a TextView)
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                // Do something with selectedDate
            },
            year,
            month,
            day
        )

        datePickerDialog.window?.apply {
            setGravity(Gravity.CENTER)
        }

        datePickerDialog.show()
    }
    private fun showSuccessAlertDialog() {
        val dialogBinding = AlertSuccessBinding.inflate(layoutInflater)
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(dialogBinding.root)
            .create()
        dialogBinding.closeButton.setOnClickListener {
            requireActivity().finish()
            dialog.dismiss()
        }
        dialog.setOnDismissListener {
            requireActivity().finish()
        }
        dialog.show()
    }
}
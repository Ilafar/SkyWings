package com.gametools.skywings.view.buy

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gametools.skywings.BookingActivity
import com.gametools.skywings.databinding.FragmentBuyBinding

class BuyFragment : Fragment() {

    private var _binding: FragmentBuyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val buyViewModel =
            ViewModelProvider(this).get(BuyViewModel::class.java)

        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.dateEditText.setOnClickListener {
            showCenteredCalendar(requireContext())
        }
        binding.searchTickets.setOnClickListener {
            val intent = Intent(requireContext(), BookingActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

        // Customize the dialog to center it
        datePickerDialog.window?.apply {
            setGravity(Gravity.CENTER)
            // You can also adjust the width and height if needed:
            // setLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        datePickerDialog.show()
    }
}
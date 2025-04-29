package com.gametools.skywings.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gametools.skywings.R
import com.gametools.skywings.databinding.FragmentProfileBinding
import com.gametools.skywings.view.galleryDialog.GalleryDialogFragment

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.editPhoto.setOnClickListener {
            findNavController().navigate(R.id.action_nav_profile_to_galleryDialogFragment)
        }
        binding.darkMode.setOnCheckedChangeListener { _, isChecked ->
            viewModel.toggleDarkMode(isChecked)
        }
        binding.darkMode.isChecked = viewModel.getDarkMode()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.photo.setImageURI(null)
        binding.photo.setImageURI(GalleryDialogFragment.selectedImageUri)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
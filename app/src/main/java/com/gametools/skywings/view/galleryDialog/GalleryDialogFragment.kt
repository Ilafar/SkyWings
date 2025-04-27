package com.gametools.skywings.view.galleryDialog

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.gametools.skywings.databinding.FragmentGalleryDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.io.File

class GalleryDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentGalleryDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var takePicture: ActivityResultLauncher<Intent>
    private lateinit var getContent: ActivityResultLauncher<Intent>
    private val bundle = Bundle()

    companion object{
        var selectedImageUri: Uri? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                selectedImageUri = data?.data
            }
            dismiss()
        }

        takePicture = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGalleryDialogBinding.inflate(inflater, container, false)
        binding.chooseFromGallery.setOnClickListener {
            openGallery()
        }
        binding.takePhoto.setOnClickListener {
            openCamera()
        }
        return binding.root

    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        getContent.launch(intent)
    }


    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, createImageUri())
        takePicture.launch(takePictureIntent)
    }

    private fun createImageUri(): Uri {
        val image = File(requireContext().filesDir, "camera_photo.png")
        selectedImageUri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.Fileprovider",
            image
        )
        return selectedImageUri!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.passwordx.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import com.example.passwordx.R
import com.example.passwordx.database.PassportDatabase
import com.example.passwordx.databinding.FragmentAddEditBinding
import com.example.passwordx.model.Passport
import com.example.passwordx.util.snackBar
import com.example.passwordx.util.toByteArray

class AddEditFragment : Fragment() {
    private var _binding: FragmentAddEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var region: String
    private lateinit var sex: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = PassportDatabase(requireContext())
        binding.btnSaveEdit.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val lastname = binding.lastname.text.toString().trim()
            val fatName = binding.faName.text.toString().trim()
            val city = binding.city.text.toString().trim()
            val address = binding.address.text.toString().trim()
            val gotDate = binding.getDate.text.toString().trim()
            val lifeTime = binding.lifeTime.text.toString().trim()
            database.savePassport(
                Passport(
                    name = name,
                    lastName = lastname,
                    fatName = fatName,
                    region = region,
                    city = city,
                    address = address,
                    gotDate = gotDate,
                    lifeTime = lifeTime,
                    sex = sex,
                    image = binding.imageView.toByteArray()
                )
            )
            snackBar("Saved")
        }
        binding.imageView.setOnClickListener {
            launcher.launch("image/*")
        }
        val regions = arrayOf("Andijon", "Farg'ona", "Namangan", "Toshkent")
        val genders = arrayOf("Male", "Female", "Unknown")
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, regions)
        val arrayAdapter2 =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, genders)
        binding.region.setAdapter(arrayAdapter)
        binding.region.setAdapter(arrayAdapter2)
        binding.region.setOnItemClickListener { parent, view, position, id ->
            region = regions[position]
        }
        binding.region.setOnItemClickListener { parent, view, position, id ->
            sex = genders[position]
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.imageView.setImageURI(it)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
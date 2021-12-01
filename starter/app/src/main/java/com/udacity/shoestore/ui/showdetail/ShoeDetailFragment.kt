package com.udacity.shoestore.ui.showdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.transition.TransitionInflater
import com.udacity.shoestore.R
import com.udacity.shoestore.data.viewmodel.ShoeListViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    private var shoe = Shoe("", 0.0, "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Transition: https://developer.android.com/guide/fragments/animate#set-transitions
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_in_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        binding.cancelButton.setOnClickListener { v: View ->
            v.findNavController().navigateUp()
        }

        binding.saveButton.setOnClickListener { v: View ->
            if (shoe.name.isEmpty() && shoe.company.isEmpty()
                && shoe.size == 0.0 && shoe.description.isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.validation_error),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.saveShoe(shoe)
                v.findNavController().navigateUp()
            }
        }

        binding.shoe = shoe

        binding.lifecycleOwner = this

        return binding.root
    }
}
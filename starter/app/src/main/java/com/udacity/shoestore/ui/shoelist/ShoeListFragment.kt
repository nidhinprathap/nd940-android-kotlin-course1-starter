package com.udacity.shoestore.ui.shoelist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.udacity.shoestore.R
import com.udacity.shoestore.data.viewmodel.ShoeListViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val shoeListViewModel: ShoeListViewModel by activityViewModels()
    private lateinit var shoes: MutableList<Shoe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_in_right)
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )

        binding.shoeListFab.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        setHasOptionsMenu(true)
        createObservers()
        return binding.root
    }

    private fun createObservers() {
        shoeListViewModel.shoes.observe(viewLifecycleOwner, Observer { list ->
            binding.shoeItem.removeAllViews()
            list.forEach { shoe ->
                val inflater = LayoutInflater.from(binding.shoeItem.context)
                val binding: ItemShoeBinding =
                    ItemShoeBinding.inflate(inflater, binding.shoeItem, true)
                binding.shoe = shoe
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                shoeListViewModel.clearShowList()
                findNavController()
                    .navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
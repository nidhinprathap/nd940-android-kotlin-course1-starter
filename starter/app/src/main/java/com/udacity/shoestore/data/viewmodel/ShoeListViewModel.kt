package com.udacity.shoestore.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {
    private val _shoes = MutableLiveData<ArrayList<Shoe>>()

    val shoes: LiveData<ArrayList<Shoe>>
        get() = _shoes

    init {
        _shoes.value = arrayListOf()
    }

    fun saveShoe(shoe: Shoe) {
        val shoes = _shoes.value
        shoes?.add(shoe)
        _shoes.value = shoes
    }

    fun clearShowList() {
        _shoes.value = arrayListOf()
    }
}
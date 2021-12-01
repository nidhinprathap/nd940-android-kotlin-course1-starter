package com.udacity.shoestore.utilities

import androidx.databinding.InverseMethod

object Converters {
    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(value: Double): String {
        return value.toString()
    }

    @JvmStatic
    fun stringToDouble(value: String): Double {
        return value.toDoubleOrNull() ?: 0.0
    }
}
package com.example.namebattler.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.namebattler.R
import com.example.namebattler.viewModel.CharacterViewModel

object ColorBindingAdapter {
    @BindingAdapter("inactive_color", "active_color", "excess_color", "button_counter")
    @JvmStatic
    fun setBindingColor(view: View, inactiveColor :Int, activeColor: Int, ExcessColor: Int ,buttonCounter: Int){

        when{
            buttonCounter < 3 -> view.setBackgroundColor(inactiveColor)
            buttonCounter == 3 -> view.setBackgroundColor(activeColor)
            buttonCounter > 3 -> view.setBackgroundColor(ExcessColor)
        }
    }
}
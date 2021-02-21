package com.example.namebattler.presentation.dataBindingUtils.party

import android.view.View
import androidx.databinding.BindingAdapter

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
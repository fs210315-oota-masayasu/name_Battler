/** ラジオボタンのリソースIDをEnum（RadioType）に変換させる **/

@file:JvmName("TestBindingUtils")

package com.example.namebattler.util

import androidx.databinding.InverseMethod
import com.example.namebattler.R

@InverseMethod("TestButtonIdToType")

fun TestTypeToButtonId(radioType: RadioType): Int{
    var selectedButtonId = -1

    radioType.run {

        selectedButtonId = when(this){
            RadioType.TYPE_A -> R.id.radioA
            RadioType.TYPE_B -> R.id.radioB
            RadioType.TYPE_C -> R.id.radioC
        }
    }

    return selectedButtonId
}

fun TestButtonIdToType(selectedButtonId: Int): RadioType?{
    var type: RadioType? = null
    when(selectedButtonId){
        R.id.radioA -> {
            type = RadioType.TYPE_A
        }
        R.id.radioB -> {
            type = RadioType.TYPE_B
        }
        R.id.radioC ->{
            type = RadioType.TYPE_C
        }
    }
    return type
}


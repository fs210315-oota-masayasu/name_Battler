
/** ラジオボタンのリソースIDをEnum（RadioType）に変換させる **/

@file:JvmName("BindingUtils")

package com.example.namebattler.presentation.dataBindingUtils.characters

import androidx.databinding.InverseMethod
import com.example.namebattler.R
import com.example.namebattler.function.JobEnum
import com.example.namebattler.function.OperationIdEnum

/** ジョブ選択 **/
//EnumをリソースIDに変換する
@InverseMethod("buttonIdToType")
fun typeToButtonId(jobEnum: JobEnum): Int{
    var selectedButtonId: Int

    jobEnum.run {
        selectedButtonId = when(this){
            JobEnum.WARRIOR -> R.id.rbt_select_warrior
            JobEnum.SPELL_CASTER -> R.id.rbt_select_wizard
            JobEnum.PRIEST -> R.id.rbt_select_priest
            JobEnum.BERSERK -> R.id.rbt_select_berserker
            else -> -1
        }
    }

    return selectedButtonId
}

fun buttonIdToType(selectedButtonId: Int): JobEnum {
    val type: JobEnum?
    when(selectedButtonId){
        R.id.rbt_select_warrior -> {
            type = JobEnum.WARRIOR
        }
        R.id.rbt_select_wizard -> {
            type = JobEnum.SPELL_CASTER
        }
        R.id.rbt_select_priest ->{
            type = JobEnum.PRIEST
        }
        R.id.rbt_select_berserker ->{
            type = JobEnum.BERSERK
        }
        else ->{
            type = JobEnum.NONE
        }

    }
    return type
}

/** 作戦選択 **/
//EnumをリソースIDに変換する
@InverseMethod("operationButtonIdToType")
fun typeToOperationButtonId(operationIdEnum: OperationIdEnum): Int{
    var selectedButtonId: Int

    operationIdEnum.run {
        selectedButtonId = when(this){
            OperationIdEnum.OFFENSIVE -> R.id.btn_offensive
            OperationIdEnum.DEFENSIVE -> R.id.btn_defensive
            OperationIdEnum.FLEXIBLE -> R.id.btn_flexible
            else -> -1
        }
    }

    return selectedButtonId
}

fun operationButtonIdToType(selectedButtonId: Int): OperationIdEnum {
    return when(selectedButtonId){
        R.id.btn_offensive -> {
            OperationIdEnum.OFFENSIVE
        }
        R.id.btn_defensive -> {
            OperationIdEnum.DEFENSIVE
        }
        R.id.btn_flexible ->{
            OperationIdEnum.FLEXIBLE
        }
        else ->{
            OperationIdEnum.ERROR
        }
    }
}

/** ラジオボタンのリソースIDをEnum（RadioType）に変換させる **/

@file:JvmName("BindingUtils")

package com.example.namebattler.util

import androidx.databinding.InverseMethod
import com.example.namebattler.R

/** ジョブ選択 **/
//EnumをリソースIDに変換する
@InverseMethod("buttonIdToType")
fun typeToButtonId(jobEnum: JobEnum): Int{
    var selectedButtonId = -1

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
    var type: JobEnum? = null
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
    var selectedButtonId = -1

    operationIdEnum.run {
        selectedButtonId = when(this){
            OperationIdEnum.OFFENSIVE-> R.id.btn_offensive
            OperationIdEnum.DEFENSIVE -> R.id.btn_defensive
            OperationIdEnum.FLEXIBLE -> R.id.btn_flexible
            else -> -1
        }
    }

    return selectedButtonId
}

fun operationButtonIdToType(selectedButtonId: Int): OperationIdEnum{
    var type: OperationIdEnum? = null
    when(selectedButtonId){
        R.id.btn_offensive -> {
            type = OperationIdEnum.OFFENSIVE
        }
        R.id.btn_defensive -> {
            type = OperationIdEnum.DEFENSIVE
        }
        R.id.btn_flexible ->{
            type = OperationIdEnum.FLEXIBLE
        }
        else ->{
            type = OperationIdEnum.ERROR
        }
    }
    return type
}
package com.example.namebattler.viewModel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    var isClickButton: MutableLiveData<Boolean> = MutableLiveData(false)
    var isEditCharacter: MutableLiveData<Boolean> = MutableLiveData(false)

    fun onClickEditCharacterButton(){
        Log.d("*** tag ***", "click!!")


        isClickButton.postValue(true)
        isEditCharacter.postValue(true)
    }

    fun onClickBattleButton(){
        isClickButton.postValue(true)
    }

}
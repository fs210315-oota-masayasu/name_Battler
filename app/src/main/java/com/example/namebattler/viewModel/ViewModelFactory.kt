package com.example.namebattler.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory: ViewModelProvider.NewInstanceFactory(){
    companion object{
        var characterViewModel = CharacterViewModel()
    }
    override fun<T: ViewModel>create(modelClass: Class<T>) =
        with(modelClass){
            when{
                isAssignableFrom(CharacterViewModel::class.java) ->
                    characterViewModel

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }as T
    }

fun Fragment.getViewModelFactory(): ViewModelFactory{
    return ViewModelFactory()
}
package com.example.namebattler.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory(): ViewModelProvider.NewInstanceFactory(){
    companion object{
        var characterViewModel = CharacterViewModel()
        var partyFormationViewModel = PartyFormationViewModel()
        var enemyViewModel = EnemyViewModel()
        var battleViewModel = BattleViewModel()
        var headerViewModel = HeaderViewModel()


    }
    override fun<T: ViewModel>create(modelClass: Class<T>) =
        with(modelClass){
            when{
                isAssignableFrom(CharacterViewModel::class.java) ->
                    characterViewModel

                isAssignableFrom(PartyFormationViewModel::class.java) ->
                    partyFormationViewModel
                isAssignableFrom(EnemyViewModel::class.java) ->
                    enemyViewModel

                isAssignableFrom(BattleViewModel::class.java) ->
                    battleViewModel

                isAssignableFrom(HeaderViewModel::class.java) ->
                    headerViewModel

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }as T
    }

fun getViewModelFactory( ): ViewModelFactory{
    return ViewModelFactory()
}
package com.example.namebattler.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.namebattler.data.database.AppDatabase
import com.example.namebattler.data.database.Characters
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


class MainViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository: CharactersRepository
    val allCharacters: LiveData<List<Characters>>
    //private lateinit var areYouThere: LiveData<List<Characters>>
    var characterList : LiveData<String>? = null

    val countOverlap = MutableLiveData<Int>()
    val numOfRegistrations = MutableLiveData<Int>()

    init {
        val charactersDao = AppDatabase.getInstance(application, viewModelScope).charactersDao()
        repository = CharactersRepository(charactersDao)
        allCharacters = repository.allCharacters

    }

    fun update(characters: Characters) = viewModelScope.launch{
        repository.update(characters)
    }

    fun insert(characters: Characters) = viewModelScope.launch {
        repository.insert(characters)
    }

    fun delete(characters: Characters) = viewModelScope.launch {
        repository.delete(characters)
    }

    private fun countOverlap(searchName: String):Int?{
        return repository.countOverlap(searchName)
    }

    private fun numOfRegistrations():Int{
        return repository.numOfRegistrations()
    }

    fun confirmNumOfRegistrations(){
        //非同期処理でMutableLiveDataに登録件数を格納
        thread {
            numOfRegistrations.postValue(numOfRegistrations())
        }
    }

    fun confirm(searchName: String){
        countOverlap.postValue(countOverlap(searchName))
    }

}
package com.example.namebattler.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.namebattler.data.database.AppDatabase
import com.example.namebattler.data.database.Characters
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository: CharactersRepository
    val allCharacters: LiveData<List<Characters>>
    //private lateinit var areYouThere: LiveData<List<Characters>>
    var test : Int? = null
    var characterList : LiveData<String>? = null



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


    fun countOverlap(searchName: String):Int{
        return repository.countOverlap(searchName)
    }


/*    fun serchYourName(name :String): Job = viewModelScope.launch {
        return repository.areYouThere(name) as LiveData<List<Characters>>
    }*/





}
package com.example.namebattler.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.namebattler.data.AppDatabase
import com.example.namebattler.data.Characters
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository: CharactersRepository
    val allCharacters: LiveData<List<Characters>>

    init {


        val charactersDao = AppDatabase.getInstance(application, viewModelScope).charactersDao()
        repository = CharactersRepository(charactersDao)
        allCharacters = repository.allCharacters

    }

    fun insert(characters: Characters) = viewModelScope.launch {
        repository.insert(characters)
    }
}